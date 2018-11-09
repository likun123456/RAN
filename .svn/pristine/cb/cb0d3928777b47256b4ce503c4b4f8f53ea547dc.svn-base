/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.web;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.CheatHistory;
import com.thinkgem.jeesite.modules.cheat.entity.CheatLog;
import com.thinkgem.jeesite.modules.cheat.entity.TCheatChinese;
import com.thinkgem.jeesite.modules.cheat.service.CheatHistoryService;
import com.thinkgem.jeesite.modules.cheat.service.CheatLogService;
import com.thinkgem.jeesite.modules.cheat.service.TCheatChineseService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;



/**
 * 计费欺诈抓包历史记录Controller
 * @author zhuguangrui
 * @version 2017-09-04
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatHistory")
public class CheatHistoryController extends BaseController {

	@Autowired
	private CheatHistoryService cheatHistoryService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCheatChineseService tCheatChineseService;
	@Autowired
	private CheatLogService cheatLogService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	
	@ModelAttribute
	public CheatHistory get(@RequestParam(required=false) String id) {
		CheatHistory entity = null;
		if (entity == null){
			entity = new CheatHistory();
		}
		return entity;
	}
	
	@RequiresPermissions("cheat:cheatHistory:view")
	@RequestMapping(value = {"show",""})
	public String show(Model model) {
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setTemp_field1("6");//6代表采集类型为防欺诈的业务类型
		List<TPool> poolList = tPoolService.queryPoolListByType("SAEGW");
		List<TNewnetelement> netListAll = tNewnetelementService.findListByServiceType(tNewnetelement);
		List<PoolNetVO>  poolNetList = new ArrayList<PoolNetVO>();
		for(TPool pool : poolList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		model.addAttribute("poolNetList", poolNetList);
		return "modules/cheat/cheatHistoryList";
	}
	
	@RequiresPermissions("cheat:cheatHistory:view")
	@RequestMapping(value = "list")
	public String list(CheatHistory cheatHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(cheatHistory.getNetid());
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			
			Page<CheatHistory> page = cheatHistoryService.findPage(new Page<CheatHistory>(request, response), cheatHistory);
			List<TCheatChinese> cheatChineseList = tCheatChineseService.findList(null);
			for(CheatHistory hItem : page.getList()){
				hItem.setName(hItem.getCheatCase());
				for(TCheatChinese cItem : cheatChineseList){
					if(StringUtils.isNotBlank(cItem.getCheatCase()) && cItem.getCheatCase().trim().equalsIgnoreCase(hItem.getCheatCase().trim())){
						hItem.setName(cItem.getCheatCaseChinese());
						break;
					}
				}
			}
			model.addAttribute("page", page);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return show(model);
		 
	}

	@RequiresPermissions("cheat:cheatHistory:view")
	@RequestMapping(value = "showCheatlog")
	public String showCheatlog(String netid,String imsi,String recordtime, Model model) {
		CheatLog cheatLog = new CheatLog();
		cheatLog.setNetid(netid);
		cheatLog.setImsi(imsi);
		cheatLog.setDateTime(recordtime.replaceAll("[-:\\s]+", ""));
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			List<CheatLog> cheatloglist = cheatLogService.findList(cheatLog);
			List<TCheatChinese> cheatChineseList = tCheatChineseService.findList(null);
			for(CheatLog hItem : cheatloglist){
				for(TCheatChinese cItem : cheatChineseList){
					if(StringUtils.isNotBlank(cItem.getCheatCase()) && cItem.getCheatCase().trim().equalsIgnoreCase(hItem.getCheatCase().trim())){
						hItem.setCheatCase(cItem.getCheatCaseChinese());
						break;
					}
				}
			}
			model.addAttribute("cheatloglist", cheatloglist);
			model.addAttribute("imsi", imsi);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return "modules/cheat/cheatLogDetail";
	}

	
	@RequiresPermissions("cheat:cheatHistory:view")
	@RequestMapping(value = "showPie")
	public String showPie(String netid,String imsi,String beginDate,String endDate,String recordtime,String cheatCase, Model model) {
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			List<CheatHistory> list = cheatHistoryService.queryListByImsi(netid, imsi, beginDate, endDate,recordtime.substring(0, 10).replaceAll("-", "_"));
			model.addAttribute("list", list);
			model.addAttribute("imsi",imsi);
			List<CheatHistory> flow = cheatHistoryService.queryFlowList(netid, imsi, recordtime,recordtime.substring(0, 10).replaceAll("-", "_"),cheatCase);
			if(flow != null && flow.size() > 0){
				model.addAttribute("freeTotal", flow.get(0).getFreeTotal());
				model.addAttribute("charge", flow.get(0).getPercent());
			}
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		return "modules/cheat/cheatHistoryPie";
	}
	
	@RequiresPermissions("cheat:cheatHistory:view")
	@RequestMapping(value = "downloadCap")
	public void downloadCap(String netid,String imsi,String recordTime,String cheatCase,Model model,HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			List<CheatHistory> record = cheatHistoryService.queryFlowList(netid, imsi, recordTime,recordTime.substring(0, 10).replaceAll("-", "_"),cheatCase);
			if(record != null && record.size() > 0){
				String proxyIp = record.get(0).getIp();
				if(StringUtils.isNotBlank(proxyIp)){
					String capName = cheatHistoryService.queryCapName(recordTime,imsi,proxyIp);
					if(StringUtils.isNotBlank(capName)){
						String timeStamp = recordTime.replaceAll("[-:\\s]+", "");
						String dateStamp = timeStamp.substring(0, 8);
						String path = Global.getConfig("localinexistenceIPCapFilePath")+File.separator+netid+File.separator+dateStamp+File.separator+capName;
						File f = new File(path);
						FileUtils.downFile(f, request, response);
					}
				}
			}
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
	}
	
	@RequiresPermissions("cheat:cheatHistory:view")
	@RequestMapping(value = "export")
	public void export(String netid,String beginDate,String endDate,Integer cheatStatus,Model model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			CheatHistory cheatHistory = new CheatHistory();
			cheatHistory.setNetid(netid);
			cheatHistory.setBeginDate(beginDate);
			cheatHistory.setEndDate(endDate);
			cheatHistory.setStatus(cheatStatus);
			Page<CheatHistory> page = cheatHistoryService.findPage(new Page<CheatHistory>(request, response), cheatHistory);
			List<TCheatChinese> cheatChineseList = tCheatChineseService.findList(null);
			for(CheatHistory hItem : page.getList()){
				for(TCheatChinese cItem : cheatChineseList){
					if(StringUtils.isNotBlank(cItem.getCheatCase()) && cItem.getCheatCase().trim().equalsIgnoreCase(hItem.getCheatCase().trim())){
						hItem.setCheatCase(cItem.getCheatCaseChinese());
						break;
					}
				}
			}
			
			String [] title = {"日期时间","欺诈用户IMSI","免费流量(Mb)","总流量(Mb)","免费流量占比","计费欺诈核实状态","计费欺诈类型"};
			String fileName = "cheatHistory_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcel ee = new ExportExcel("计费欺诈抓包历史记录", title);
			DecimalFormat df = new DecimalFormat("0.00");
            for (int i = 0; i < page.getList().size(); i++) {
 				Row row = ee.addRow();
 				CheatHistory t =page.getList().get(i);
				ee.addCell(row, 0,t.getRecordTime().substring(0, 19));
				ee.addCell(row, 1,t.getServedImsi());
				ee.addCell(row, 2,df.format(Double.valueOf(t.getFreeTotal())/(1024*1024)));
				ee.addCell(row, 3,df.format(Double.valueOf(t.getTotal())/(1024*1024)));
				ee.addCell(row, 4,df.format(Double.valueOf(t.getPercent())*100)+"%");
				ee.addCell(row, 5,DictUtils.getDictLabel(String.valueOf(t.getStatus()), "biz_cheat_verify_status", String.valueOf(t.getStatus())));
				ee.addCell(row, 6,t.getCheatCase());
 			}
            ee.write(response, fileName);
  		    ee.dispose();
            
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出数据失败！失败信息："+e.getMessage());
		}finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
	}

}