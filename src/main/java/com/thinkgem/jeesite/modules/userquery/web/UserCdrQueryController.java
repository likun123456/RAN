/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.entity.StreamGobbler;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CdrDecoder;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.TimeUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.userquery.entity.TAllRatinggroupConfig;
import com.thinkgem.jeesite.modules.userquery.entity.TCdrTreeMould;
import com.thinkgem.jeesite.modules.userquery.entity.TImeitacTranslate;
import com.thinkgem.jeesite.modules.userquery.entity.UserCdrDetail;
import com.thinkgem.jeesite.modules.userquery.entity.UserCdrQueryEntity;
import com.thinkgem.jeesite.modules.userquery.service.TAllRatinggroupConfigService;
import com.thinkgem.jeesite.modules.userquery.service.TImeitacTranslateService;



/**
 * 单表生成Controller
 * @author zhuguangrui
 * @version 2017-05-25
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "${adminPath}/userquery/cdr")
public class UserCdrQueryController extends BaseController {

	private String cdrPath = Global.getConfig("cdrExcelPath");
	@Autowired
	private TImeitacTranslateService imeitacTranslateService;
	
	@Autowired
	private TAllRatinggroupConfigService allRatinggroupService;
	
	@ModelAttribute
	public UserCdrQueryEntity get(@RequestParam(required=false) String id) {
		return new UserCdrQueryEntity();
	}
	
	/**
	 * 显示话单查询列表页面
	 * @return
	 */
	@RequiresPermissions("userquery:cdr:view")
	@RequestMapping(value = {"show", ""})
	public String show() {
		return "modules/userquery/userCdrQueryList";
	}
	
	/**
	 * 执行查询操作
	 * @param userCdrQueryEntity
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("userquery:cdr:view")
	@RequestMapping(value = "list")
	public String list(UserCdrQueryEntity userCdrQueryEntity, HttpServletRequest request) {
		
		/*//清除上一次查询的目录
		if(request != null && request.getSession().getAttribute("UUID") != null){
			FileUtils.deleteDirectory("/opt/Ericsson/core/FILE/search/"+request.getSession().getAttribute("UUID"));
		}
		String[] numbers = userCdrQueryEntity.getNumber().split(",");
		List<UserCdrDetail> userCDRDetailList = new ArrayList<UserCdrDetail>();
		String UUID =IdGen.uuid();
		if(request != null){
			request.getSession().setAttribute("UUID", UUID);
		}
		List<TAllRatinggroupConfig> allRatinggroup = allRatinggroupService.findList(null);
		Map<String,String> rgMap = new HashMap<String,String>();
		for(TAllRatinggroupConfig rg : allRatinggroup){
			rgMap.put(rg.getRatinggroup(), rg.getName());
		}
		int index = 0;
		for(String number : numbers){
			userCdrQueryEntity.setNumber(number);
			List<UserCdrDetail> oneUserCDRDetails = getDetailList(UUID,userCdrQueryEntity,request,rgMap,index);
			if(oneUserCDRDetails != null){
				userCDRDetailList.addAll(oneUserCDRDetails);
			}
		}
		//过滤掉非时间段范围的数据
		if(request != null){
			request.getSession().setAttribute("userCDRDetailList", userCDRDetailList);
		}
		
		return JsonMapper.getInstance().toJson(userCDRDetailList);*/
		
		/*if(request != null && request.getSession().getAttribute("UUID") != null){
			FileUtils.deleteDirectory("/opt/Ericsson/core/FILE/search/"+request.getSession().getAttribute("UUID"));
		}
		String UUID =IdGen.uuid();
		if(request != null){
			request.getSession().setAttribute("UUID", UUID);
		}
		List<TAllRatinggroupConfig> allRatinggroup = allRatinggroupService.findList(null);
		Map<String,String> rgMap = new HashMap<String,String>();
		for(TAllRatinggroupConfig rg : allRatinggroup){
			rgMap.put(rg.getRatinggroup(), rg.getName());
		}
		int index = 0;
		List<UserCdrDetail> userCDRDetailList = analysis("E:/6578.txt",userCdrQueryEntity.getBeginDate(),userCdrQueryEntity.getEndDate(),rgMap,index);
		System.out.println(userCDRDetailList.size());
		//过滤掉非时间段范围的数据
		if(request != null){
			request.getSession().setAttribute("userCDRDetailList", userCDRDetailList);
		}
		return JsonMapper.getInstance().toJson(userCDRDetailList);*/
		
		if(request.getSession().getAttribute("UUID") != null){
			FileUtils.deleteDirectory("/opt/Ericsson/core/FILE/search/"+request.getSession().getAttribute("UUID"));
		}
        String[] numbers = new String[]{"8613708983827"};
        		
		//String[] numbers = userCdrQueryEntity.getNumber().split(",");
		List<UserCdrDetail> userCDRDetailList = new ArrayList<UserCdrDetail>();
		String UUID =IdGen.uuid();
		request.getSession().setAttribute("UUID", UUID);
		List<TAllRatinggroupConfig> allRatinggroup = allRatinggroupService.findList(null);
		Map<String,String> rgMap = new HashMap<String,String>();
		for(TAllRatinggroupConfig rg : allRatinggroup){
			rgMap.put(rg.getRatinggroup(), rg.getName());
		}
		int index = 0;
		for(String number : numbers){
			userCdrQueryEntity.setNumber(number);
			List<UserCdrDetail> oneUserCDRDetails = null;
			if(number.toLowerCase().contains("x")){
				oneUserCDRDetails = analysis("D:/cdr_file/xxx.txt",userCdrQueryEntity.getBeginDate(),userCdrQueryEntity.getEndDate(),rgMap,index);
			}else{
				oneUserCDRDetails = analysis("D:/cdr_file/"+number+".txt","2017-10-01 17:19:52","2017-10-15 17:19:52",rgMap,index);
			}
			if(oneUserCDRDetails != null){
				userCDRDetailList.addAll(oneUserCDRDetails);
			}
		}
		//过滤掉非时间段范围的数据
		request.getSession().setAttribute("userCDRDetailList", userCDRDetailList);
		return JsonMapper.getInstance().toJson(userCDRDetailList);
		
	}
	
	
	@RequiresPermissions("userquery:cdr:view")
	@RequestMapping(value = {"downloadZip"})
	public void downloadZip(UserCdrQueryEntity userCdrQueryEntity,HttpServletRequest request,HttpServletResponse response){
		if(request.getSession().getAttribute("UUID") != null){
			String[] numbers = userCdrQueryEntity.getNumber().split(",");
			File binFilePath = new File("D:/cdr_file/bin/");
			File[] files = binFilePath.listFiles();
			if(files != null){
				String[] filePaths = new String[numbers.length];
				for(int j=0;j<numbers.length;j++){
					for(int i=0;i<files.length;i++){
						if(files[i].getAbsolutePath().contains(numbers[j])&& !numbers[j].toLowerCase().contains("x") ){
							filePaths[j] = files[i].getAbsolutePath();
						}
						if(numbers[j].toLowerCase().contains("x")){
							filePaths[j] = "D:/cdr_file/bin/xxx";
						}
					}
				}
				try {
					FileUtils.writeZipAndDownload(response, filePaths,"cdrBinary");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	
	private List<UserCdrDetail> getDetailList(String UUID,UserCdrQueryEntity userCdrQueryEntity, HttpServletRequest request, Map<String, String> rgMap,int index) {
		List<UserCdrDetail> userCDRDetailList = null;
		FileUtils.createDirectory("/opt/Ericsson/core/FILE/search/"+UUID+"/out");
		FileUtils.createDirectory("/opt/Ericsson/core/FILE/search/"+UUID+"/EndOutPut");
		//1、根据前台输入的日期间隔拿到这些日期,并且遍历这些日期
		String[] beginDateAndTime = userCdrQueryEntity.getBeginDate().split(" ");
		String[] endDateAndTime = userCdrQueryEntity.getEndDate().split(" ");
		String beginDate = beginDateAndTime[0];
		String endDate = endDateAndTime[0];
		String number = userCdrQueryEntity.getNumber();
		List<String> datelist = DateUtils.getDateList(beginDate, endDate,"yyyyMMdd");	
		if(number.toLowerCase().contains("x") || "unknown".equalsIgnoreCase(number)){
			//解析UNKNOWN的话单数据
			executeCatFolder(UUID,number,datelist);
		}else{
			executeGetCdrCmd(UUID,number,datelist);
		}
		//获取文件名
		File binFilePath = new File("/opt/Ericsson/core/FILE/search/"+UUID+"/out");
		File[] files = binFilePath.listFiles();
		if(files!= null && files.length > 0){
			executeDecoderCmd(UUID, number);
		    //解析文件，拿到结果集
			userCDRDetailList = analysis("/opt/Ericsson/core/FILE/search/"+UUID+"/EndOutPut/cdrDetail.txt",userCdrQueryEntity.getBeginDate(),userCdrQueryEntity.getEndDate(),rgMap,index);
		}
		return userCDRDetailList;
	}
	

	/**
	 * 查看单条话单详情（详细信息）
	 * @param request
	 * @param index
	 * @param model
	 * @return
	 */
	@RequiresPermissions("userquery:cdr:view")
	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request,int index,int tableIndex,Model model){
		List<UserCdrDetail> userCDRSearchList = (List<UserCdrDetail>) request.getSession().getAttribute("userCDRDetailList");
		UserCdrDetail userCdrDetail = userCDRSearchList.get(index);
		userCdrDetail.setTableIndex(tableIndex);
		userCdrDetail.setLength(userCDRSearchList.size());
		model.addAttribute("userCdrDetail", userCdrDetail);
		return "modules/userquery/userCdrDetail";
	}
	
	/**
	 * 查看单条话单详情(上一条，下一条)
	 * @param request
	 * @param index
	 * @param model
	 * @return
	 */
	@RequiresPermissions("userquery:cdr:view")
	@RequestMapping(value = "goOther")
	public String goOther(HttpServletRequest request,int index,Model model){
		List<UserCdrDetail> userCDRSearchList = (List<UserCdrDetail>) request.getSession().getAttribute("userCDRDetailList");
		UserCdrDetail userCdrDetail = userCDRSearchList.get(index);
		userCdrDetail.setLength(userCDRSearchList.size());
		model.addAttribute("userCdrDetail", userCdrDetail);
		return "modules/userquery/userCdrDetail";
	}
	
	
	@RequestMapping(value = { "exportCdr" })
	public void exportCdr(UserCdrQueryEntity userCdrQueryEntity, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		try {
			//导出数据
			List<UserCdrDetail> list = (List<UserCdrDetail>)request.getSession().getAttribute("userCDRDetailList");
			Map<String,List<UserCdrDetail>> cdrMap = parseCdrList(list);
			String[] headers = { "", "" , "" };
			String[] filePath = new String[cdrMap.size()];
			int index = 0;
			for(String key : cdrMap.keySet()) {
				List<UserCdrDetail> cdrList = cdrMap.get(key);
				//为当前号码每个时间生成sheet页
				ExportExcel ee = new ExportExcel();
				HSSFWorkbook workbook = new HSSFWorkbook();
				String fileName = "" + key +".xls";
				OutputStream out = new FileOutputStream(cdrPath + "/"+fileName);
				filePath[index ++] = cdrPath + "/"+fileName;
				for(int i=0; i<cdrList.size(); i++) {
					UserCdrDetail userCdr = cdrList.get(i);
					List<TCdrTreeMould> mouldList = userCdr.getCdrDetail();
					List<Object[]> rowList = new ArrayList<Object[]>();
					for(TCdrTreeMould mouid : mouldList) {
						Object[] o = new Object[3];
						o[0] = mouid.getName();
						o[1] = DictUtils.getDictLabel(mouid.getName(), "biz_cdr_tree_node", mouid.getName());
						o[2] = mouid.getValue()==null?"":mouid.getValue();
						rowList.add(o);
					}
					ee.createMultiSheetExcel(workbook, i, TimeUtils.parseDateTime(userCdr.getRecordopeningtime()), headers, rowList, out);
				}
				workbook.write(out);
			}
			FileUtils.writeZipAndDownload(response,filePath,"cdrReport");
			
			FileUtils.deleteFile(filePath);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private Map<String,List<UserCdrDetail>> parseCdrList(List<UserCdrDetail> list) {
		Map<String,List<UserCdrDetail>> map = new LinkedHashMap<String,List<UserCdrDetail>>();
		Map<String, Integer> sameTimeMap = new HashMap<String, Integer>();
		for(UserCdrDetail detail : list) {
			if(null == detail.getServedmsisdn()) {
				if(map.containsKey("unknown")) {
					List<UserCdrDetail> l = map.get("unknown");
					//判断时间是否重复
					for(UserCdrDetail ucd : l) {
						if(ucd.getRecordopeningtime().equals(detail.getRecordopeningtime())) {
							int count = sameTimeMap.get("unknown").intValue();
							detail.setRecordopeningtime(detail.getRecordopeningtime()+"@"+(count));
							sameTimeMap.put("unknown", count+1);
							break;
						}
					}
					l.add(detail);
					
				} else {
					List<UserCdrDetail> l = new ArrayList<UserCdrDetail>();
					l.add(detail);
					sameTimeMap.put("unknown", 1);
					map.put("unknown", l);
				}
			} else {
				if(map.containsKey(detail.getServedmsisdn())) {
					List<UserCdrDetail> l = map.get(detail.getServedmsisdn());
					for(UserCdrDetail ucd : l) {
						if(ucd.getRecordopeningtime().equals(detail.getRecordopeningtime())) {
							int count = sameTimeMap.get(detail.getServedmsisdn()).intValue();
							detail.setRecordopeningtime(detail.getRecordopeningtime()+"@"+(count));
							sameTimeMap.put(detail.getServedmsisdn(), count+1);
							break;
						}
					}
					l.add(detail);
				} else {
					List<UserCdrDetail> l = new ArrayList<UserCdrDetail>();
					l.add(detail);
					sameTimeMap.put(detail.getServedmsisdn(), 1);
					map.put(detail.getServedmsisdn(), l);
				}
			}
		}
		return map;
	}
	
	
	/**
	 * 用decoder脚本解析二进制话单文件
	 * @param request
	 * @param redirectAttributes
	 * @param UUID
	 * @param FileName
	 */
	private void executeDecoderCmd(String UUID,String number) {
		String[] command = new String[3];
		command[0] = "sh";
		command[1] = "-c";
		try {
			command[2] = "/opt/Ericsson/core/bin/" + "cdr_decoder -a /opt/Ericsson/core/bin/hljmcc -b " 
					+ "/opt/Ericsson/core/FILE/search/"+UUID+"/out/" + number 
					+ " >> /opt/Ericsson/core/FILE/search/"+UUID+"/EndOutPut/cdrDetail.txt";
			System.out.println("==================execute  cdr_decoder======== begin===============");
			System.out.println(command[2]);
			Process proc = Runtime.getRuntime().exec(command);
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"output",logger);
			outputGobbler.start();
		    proc.waitFor();
			proc.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----!!!----"+e.getMessage());
		}
	}
	
	/**
	 * 执行get_cdr脚本查询话单数据，生成二进制文件
	 * @param UUID
	 * @param number
	 * @param dateList
	 */
	private void executeGetCdrCmd(String UUID, String number,List<String> dateList) {
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		try {
			cmd[2] = "/opt/Ericsson/core/bin/get_cdr " + number + " " + "/opt/Ericsson/core/FILE/search/"+UUID+"/out/" + number;
			String[] factory = Global.getConfig("factory").split(",");
			for(String facItem : factory){
				for(String dateFolder :dateList){
					cmd[2] += " /opt/Ericsson/core/FILE/usercdrdown/"+facItem+"/cdrfiledone/desc/" + dateFolder + "/";
				}
			}
			Process proc = Runtime.getRuntime().exec(cmd);
			System.out.println(cmd[2]);
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"output",logger);
			outputGobbler.start();
			proc.waitFor();
			proc.destroy();
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}
	
	/**
	 * 执行cat脚本查询unknown的话单数据，生成二进制文件
	 * @param UUID
	 * @param datelist
	 */
	private void executeCatFolder(String UUID,String number, List<String> datelist) {
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		try {
			cmd[2] = "cat";
			String[] factory = Global.getConfig("factory").split(",");
			for(String facItem : factory){
				for(String dateFolder :datelist){
					cmd[2] += " /opt/Ericsson/core/FILE/usercdrdown/"+facItem+"/cdrfiledone/desc/" + dateFolder + "/aaaa";
				}
			}
			cmd[2] += " >> /opt/Ericsson/core/FILE/search/"+UUID+"/out/"+number;
			Process proc = Runtime.getRuntime().exec(cmd);
			System.out.println(cmd[2]);
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"output",logger);
			outputGobbler.start();
			proc.waitFor();
			proc.destroy();
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}
	
	/**
	 * 解析话单文件
	 * @param sourcePath
	 * @param rgMap 
	 * @param index 
	 * @return
	 */
	private List<UserCdrDetail> analysis(String sourcePath,String beginTime,String endTime, Map<String, String> rgMap, int index){
		String UTF_8 = "UTF-8";
		BufferedReader reader = null;
		FileInputStream fileInputStream;
		int recordCount = 0;
		String rootId = "0";
		Map<Integer,String> levelIds = new HashMap<Integer,String>();//缓存节点level及对应ID
		Map<String,String> servedImeisvMap = new HashMap<String,String>();//缓存servedImeisv及手机型号
		List<UserCdrDetail> cdrDetailList = null;
		try {
			fileInputStream = new FileInputStream(sourcePath);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, UTF_8);
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			cdrDetailList = new ArrayList<UserCdrDetail>();
			UserCdrDetail cdrDetailItem = null;
            while ((line = reader.readLine()) != null) {
				recordCount++;
				if (line.trim().equals("")) {
					continue;
				}
		   outer:if (line.contains("pgwRecord")) {
					cdrDetailItem = new UserCdrDetail();
					cdrDetailItem.setCdrtype("PGW话单");
					long total_datavolumeFBCUplink = 0;
					long total_datavolumeFBCDownlink = 0;
					List<TCdrTreeMould> cdrTree = new ArrayList<TCdrTreeMould>();
					int level = CdrDecoder.getNodeLevel(line);
					TCdrTreeMould treeNode = new TCdrTreeMould();
					treeNode.setId(level + "" + recordCount);
					levelIds.put(level, treeNode.getId());
					if(level == 1){
						treeNode.setPid(rootId);
					}else{
						treeNode.setPid(levelIds.get(level-1));
					}
					String nodeKey = CdrDecoder.getNodeKey(line);
					treeNode.setName(nodeKey);
					cdrTree.add(treeNode);
					while ((line = reader.readLine()) != null) {
					    recordCount++;
						if (line.trim().equals("")) {
						continue;
						}
						level = CdrDecoder.getNodeLevel(line);
						treeNode = new TCdrTreeMould();
						treeNode.setId(level + "" + recordCount);
						levelIds.put(level, treeNode.getId());
						if(level == 1){
							treeNode.setPid(rootId);
						}else{
							treeNode.setPid(levelIds.get(level-1));
						}
						nodeKey = CdrDecoder.getNodeKey(line);
						String nodeValue = CdrDecoder.getNodeValue(line);
						String nodeFullValue = CdrDecoder.getNodeFullValue(line);
						treeNode.setName(nodeKey);
						treeNode.setValue(CdrDecoder.getTransformValue(DictUtils.getDictLabel(nodeKey, "biz_cdr_node_value_translate", nodeKey),nodeValue,nodeFullValue,false));
						if("servedIMEISV".equals(nodeKey)){
							String imeitacTranslate = null;
							String imeisvKey = nodeValue.substring(0, 8);
							if(servedImeisvMap.containsKey(imeisvKey)){
								imeitacTranslate = servedImeisvMap.get(imeisvKey);
							}else{
								TImeitacTranslate imeitacObj = imeitacTranslateService.get(imeisvKey);
								if(imeitacObj != null){
									imeitacTranslate = imeitacObj.getPhonename();
								}
								servedImeisvMap.put(imeisvKey, imeitacTranslate);
							}
							if(imeitacTranslate != null){
								treeNode.setValue(nodeValue + " (" +imeitacTranslate + ")");
							}else{
								treeNode.setValue(nodeFullValue);
							}
						}
						if("ratingGroup".equals(nodeKey) || "serviceIdentifier".equals(nodeKey)){
							if(rgMap.containsKey(nodeValue)){
								treeNode.setValue(nodeValue + " (" + rgMap.get(nodeValue) + ")");
							}else{
								treeNode.setValue(nodeFullValue);
							}
						}
						cdrTree.add(treeNode);
						if (line.contains("servedIMSI")) {
							cdrDetailItem.setServedimsi(treeNode.getValue());
						}
						if (line.contains("servedMSISDN")) {
							cdrDetailItem.setServedmsisdn(treeNode.getValue());
						}
						if (line.contains("rATType")) {
							cdrDetailItem.setRattype(treeNode.getValue());
						}
						if (line.contains("recordOpeningTime")) {
							cdrDetailItem.setRecordopeningtime(treeNode.getValue());
							if(treeNode.getValue().compareTo(beginTime) < 0 || endTime.compareTo(treeNode.getValue()) < 0){
								break outer;
							}
						}
						if (line.contains("datavolumeFBCUplink")) {
							total_datavolumeFBCUplink = total_datavolumeFBCUplink + Long.parseLong(nodeValue);
						}
						if (line.contains("datavolumeFBCDownlink")) {
							total_datavolumeFBCDownlink = total_datavolumeFBCDownlink + Long.parseLong(nodeValue);
						}
						if (line.contains("consolidationResult")||
								(line.contains("localSequenceNumber") && level==2 && line.trim().split(" ").length>1)) {
							break;
						}
					}
					cdrDetailItem.setIndex(index++);
					cdrDetailItem.setCdrDetail(cdrTree);
					cdrDetailItem.setTotal_datavolumefbcuplink(total_datavolumeFBCUplink);
					cdrDetailItem.setTotal_datavolumefbcdownlink(total_datavolumeFBCDownlink);
					cdrDetailList.add(cdrDetailItem);
				}
			}
			reader.close();
			inputStreamReader.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cdrDetailList;
	}
	
	@RequestMapping(value = "redirectCdr")
	public String redirectCdr(String msisdn, String pastScope,String beginDate, String endDate, Model model) {
		model.addAttribute("msisdn", msisdn);
		model.addAttribute("pastScope", pastScope);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("isRedirect", "true");
		return "modules/userquery/userCdrQueryList";
	}
}