/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.CheatReport;
import com.thinkgem.jeesite.modules.cheat.service.CheatReportService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;


/**
 * 话单欺诈业务报表导出Controller
 * @author zhuguangrui
 * @version 2017-08-30
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatReport")
public class CheatReportController extends BaseController {

	@Autowired
	private CheatReportService cheatReportService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	
	@ModelAttribute
	public CheatReport get(@RequestParam(required=false) String id) {
		CheatReport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cheatReportService.get(id);
		}
		if (entity == null){
			entity = new CheatReport();
		}
		return entity;
	}
	
	@RequiresPermissions("cheat:cheatReport:view")
	@RequestMapping(value = {"config", ""})
	public String config(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CheatReport> cheatReports = cheatReportService.findList(null); 
		model.addAttribute("cheatReports", cheatReports);
		List<TNewnetelement> netElements = queryNetElements();
		model.addAttribute("netElements", netElements);
		return "modules/cheat/cheatReportExport";
	}

	private List<TNewnetelement> queryNetElements() {
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setTemp_field1("6");//6代表采集类型为防欺诈的业务类型
		List<TNewnetelement> netElements = tNewnetelementService.findListByServiceType(tNewnetelement);
		return netElements;
	}


	@RequiresPermissions("cheat:cheatReport:export")
	@RequestMapping(value = "export")
	public void export(@RequestParam String cheatReports,@RequestParam String netElements,@RequestParam String timeElements,
			HttpServletRequest request,HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		
		if(cheatReports != null){
			Map<String,String> netMap = new HashMap<String,String>();
			Map<String,String> reportMap = new HashMap<String,String>();
			List<TNewnetelement> netList = queryNetElements();
			for(TNewnetelement netItem : netList){
				netMap.put(netItem.getId(), netItem.getFname());
			}
			List<CheatReport> reportList = cheatReportService.findList(null); 
			for(CheatReport reportItem : reportList){
				reportMap.put(reportItem.getCheatCase(), reportItem.getReportName());
			}
			Map<String,String> columnCNNames = loadColumnCNNameMap();
			String UUID =IdGen.uuid();
			String uploadPath = request.getSession().getServletContext().getRealPath("/") + File.separator + "file" + File.separator + "temp" +File.separator +UUID;
			String[] cheatReportArr = cheatReports.split(",");
			List<String> filePaths = new ArrayList<String>();
			for(String report : cheatReportArr){
				SXSSFWorkbook workBook = new SXSSFWorkbook();
				if(netElements != null){
					String[]  netIds = netElements.split(",");
					for(String netId : netIds){
						String ip = tCollectordeployService.getCheatCollectorIp(netId);
						SXSSFSheet sheet = (SXSSFSheet) workBook.createSheet(netMap.get(netId));
						if(timeElements != null){
							String[] timeElementArr = timeElements.split(",");
							boolean flag = false;
							StringBuffer times = new StringBuffer();
							String suffix = "_hour";
							for(String timeElement : timeElementArr){
								if(timeElement != null && timeElement.length() == 7){
									suffix = "_month";
									timeElement = timeElement.replaceAll("-", "");
								}else if(timeElement != null && timeElement.length() == 10){
									suffix = "_day";
								}
							   times.append("'");
							   times.append(timeElement);
							   times.append("'");
							   times.append(",");
							}
							List<Map<String,Object>> result = null;
							if(!"top100_customer_detail".equals(report) && !"customer_statistic".equals(report)) {
								try {
									DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
									result =  cheatReportService.queryCheatReport(netId,report,times.toString().substring(0,times.length()-1),suffix);
									System.out.println(result);
								} catch (Exception e) {
							        logger.error(e.getMessage(),e);
								} finally {
									DynamicDataSource.setCurrentLookupKey("dataSource");
								}
							}
							
							SXSSFRow row = null;
							if(result != null && result.size() > 0){
								//写入表头
								if(!flag){
									row = (SXSSFRow) sheet.createRow((int) 0); 
									Set<String> columns = result.get(0).keySet();
									int i=0;
									for(String column : columns){
										SXSSFCell cell = (SXSSFCell) row.createCell(i);    
							            cell.setCellValue(columnCNNames.get(column.toLowerCase()));    
							            i++;
									}
									flag = true;
								}
								//写入数据
								for (int j=0;j<result.size();j++) {    
						            row = (SXSSFRow) sheet.createRow(j + 1);
						            int k=0;
						            for(Map.Entry<String, Object> entry : result.get(j).entrySet()){
						            	Cell cell = row.createCell(k);
						            	if(entry.getKey().toLowerCase().equals("percent")){
						            		DecimalFormat df = new DecimalFormat("0.00%");  
						            		cell.setCellValue(df.format(entry.getValue()));
						            	}else{
						            		cell.setCellValue(entry.getValue().toString()); 
						            	}
						            	k++;
						            }
						        }    
							}
							
						}
					}
					if(!"top100_customer_detail".equals(report) && !"customer_statistic".equals(report)) {
						OutputStream ouputStream = null;
						try {
							File file = new File(uploadPath);
							if(!file.exists()){
								file.mkdirs();
							}
							ouputStream = new FileOutputStream(uploadPath + File.separator + report + ".xlsx");
							filePaths.add(uploadPath + File.separator + report + ".xlsx");
							workBook.write(ouputStream);    
					         ouputStream.flush();    
					         ouputStream.close();    
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			//将文件整体打成zip包。
			try {
				if(timeElements != null){ 
					if(cheatReports.contains("top100_customer_detail")) {
						String[] timeElementArr = timeElements.split(",");
						for(String timeElement : timeElementArr){
							if(timeElement != null && timeElement.length() == 10) {
								timeElement = timeElement.replaceAll("-", "_");
								filePaths.add("/opt/Ericsson/core/RatingGroupDownloadExcels/" + timeElement + "/top100_customer_detail_" + timeElement + ".xls");

							}
						}
					}
					
					if(cheatReports.contains("customer_statistic")) {
						String[] timeElementArr = timeElements.split(",");
						for(String timeElement : timeElementArr){
							if(timeElement != null && timeElement.length() == 10) {
								timeElement = timeElement.replaceAll("-", "_");
								filePaths.add("/opt/Ericsson/core/RatingGroupDownloadExcels/" + timeElement + "/customer_statistic_" + timeElement + ".xls");
							}
						}
					}
				}
				FileUtils.writeZipAndDownload(response, filePaths.toArray(new String[]{}),"cheatReport");
			} catch (Exception e) {
				e.printStackTrace();
			}
			FileUtils.deleteDirectory(uploadPath);
		}
	}
	

	private Map<String,String> loadColumnCNNameMap() {
		Map<String,String> columnCNNameMap = new HashMap<String,String>();
		columnCNNameMap.put("recordtime", "时间");
		columnCNNameMap.put("servedimsi", "欺诈用户IMSI");
		columnCNNameMap.put("freetotal", "免费流量");
		columnCNNameMap.put("total", "总流量");
		columnCNNameMap.put("percent", "免费流量占比");
		columnCNNameMap.put("cheatcase", "计费欺诈类型");
		columnCNNameMap.put("netname", "网元名称");
		columnCNNameMap.put("ratinggroup", "业务类型");
		columnCNNameMap.put("dataup", "上行流量");
		columnCNNameMap.put("datadown", "下行流量");
		columnCNNameMap.put("proxyip", "代理服务器");
		columnCNNameMap.put("countservedmsisdn", "欺诈用户手机号");
		columnCNNameMap.put("status", "审核状态");
		return columnCNNameMap;
	}

}