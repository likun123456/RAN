/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisDanger;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisDangerStatus;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelExcuteLog;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetail;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisDangerService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisDangerStatusService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelExcuteLogService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleDetailService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
* EOPS模版自动化执行Controller
* 
* @author zhuguangrui
* @version 2018-04-13
*/
@Controller
@RequestMapping(value = "${adminPath}/netconfig/excelTemplateAuto")
public class TExcelTemplateAutoController extends BaseController {
	
	@Autowired
	private TVisExcelModuleDetailService tVisExcelModuleDetailService;
	@Autowired
	private TVisExcelService tVisExcelService;
	@Autowired
	private TVisExcelModuleService tVisExcelModuleService;
	@Autowired
	private TNewnetelementService tnewnetelementService;
	@Autowired
	private TVisExcelExcuteLogService tVisExcelExcuteLogService;
	@Autowired
	private TVisDangerService tVisDangerService;
	@Autowired
	private TVisDangerStatusService tVisDangerStatusService;

	@ModelAttribute
	public TVisExcelModuleDetail get(@RequestParam(required = false) String id) {
		TVisExcelModuleDetail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tVisExcelModuleDetailService.get(id);
		}
		if (entity == null) {
			entity = new TVisExcelModuleDetail();
		}
		return entity;
	}


	

	@ResponseBody
	@RequestMapping(value = "executeFlow")
	public String executeFlow(String excelId,int moduleIndex,boolean isChoice, Model model,
			@ModelAttribute("tVisExcelModuleDetail") TVisExcelModuleDetail tVisExcelModuleDetail,HttpSession session) {
		//获取模板
		TVisExcelModule visExcelModule = new TVisExcelModule();
		visExcelModule.setExcelId(Integer.parseInt(excelId));
		List<TVisExcelModule> excelModuleList = tVisExcelModuleService.findList(visExcelModule);
		
		if(excelModuleList != null && excelModuleList.size() > 0){
			
			//模块第一条的网元类型与模板中的网元类型不匹配，直接退出
			StringBuffer sb = new StringBuffer();
			if((moduleIndex+1) <= excelModuleList.size()){
				
				if(isChoice || moduleIndex == 0){
					sb.append("open");
				}
				TVisExcelModule tVisExcelModule = excelModuleList.get(moduleIndex);
				String currentModuleType = tVisExcelModule.getModuleType();
				String currentNetType = tVisExcelModule.getNetType();
				
				tVisExcelModuleDetail.setExcelId(Integer.parseInt(excelId));
				tVisExcelModuleDetail.setModuleId(Integer.parseInt(tVisExcelModule.getId()));
				tVisExcelModuleDetail.setExecuteNo(0);
				List<TVisExcelModuleDetail> paramList = tVisExcelModuleDetailService.findParamList(tVisExcelModuleDetail);
				
				if(paramList != null && paramList.size() > 0){
					sb.append("param");
				}
				
				tVisExcelModuleDetail.setExecuteNo(null);
				List<TVisExcelModuleDetail> commandList = tVisExcelModuleDetailService.findList(tVisExcelModuleDetail);
				
				TVisDanger tvd=new TVisDanger();
				tvd.setNettype(currentNetType);
				List<TVisDanger> visDangerList = tVisDangerService.findList(tvd);
				
				outer:for(TVisExcelModuleDetail command : commandList){
					for(TVisDanger visDanger :visDangerList){
						if(StringUtils.isNotBlank(command.getConfCmd()) && 
							command.getConfCmd().toUpperCase().contains(visDanger.getCommand().toUpperCase().trim())){
							sb.append("danger");
							break outer;
						}
					}
				}
				
				if(moduleIndex > 0){
					String lastModuleType = excelModuleList.get(moduleIndex-1).getModuleType();
					String lastNetType = excelModuleList.get(moduleIndex-1).getNetType();
		
					if("NE".equals(currentModuleType) && currentModuleType.equals(lastModuleType) && currentNetType.equals(lastNetType)){
						sb.append("next");
					}else{
						if(!sb.toString().contains("open")){
							sb.append("open");
						}
					}
				}
				return sb.toString();
			}
		}else{
			return "no_modules";
		}
		return "error";
	}
	
	@RequiresPermissions("netconfig:excelTemplateAuto:view")
	@RequestMapping(value = "showModuleDetail")
	public String showModuleDetail(String excelId,String netId,int moduleIndex, Model model,
			@ModelAttribute("tVisExcelModuleDetail") TVisExcelModuleDetail tVisExcelModuleDetail,HttpSession session) {
		//获取模板
		TVisExcelModule visExcelModule = new TVisExcelModule();
		visExcelModule.setExcelId(Integer.parseInt(excelId));
		//获取模板下的所有模块,并存入session
		List<TVisExcelModule> excelModuleList = tVisExcelModuleService.findList(visExcelModule);
		tVisExcelModuleDetail.setTemp_field4(""+excelModuleList.size());//模块总数
		if(excelModuleList != null && excelModuleList.size() > 0){
			
			if((moduleIndex+1) <= excelModuleList.size()){
				TVisExcelModule tVisExcelModule = excelModuleList.get(moduleIndex);
				tVisExcelModuleDetail.setExcelId(Integer.parseInt(excelId));
				tVisExcelModuleDetail.setModuleId(Integer.parseInt(tVisExcelModule.getId()));
				tVisExcelModuleDetail.setExecuteNo(0);
				List<TVisExcelModuleDetail> paramList = tVisExcelModuleDetailService.findParamList(tVisExcelModuleDetail);
	
				tVisExcelModuleDetail.setTemp_field2(paramList.size() + "");
				
				String netType = "";
				netType = DictUtils.getDictValue(tVisExcelModule.getNetType(), "biz_net_type", netType);
				
				
				//模块类型为NE,弹出单网元确认
				if (tVisExcelModule.getModuleType().equals("NE")) {
					TNewnetelement tnewnetelement = new TNewnetelement();
					tnewnetelement.setType(netType);
					List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
					
					// 选择单网元
					if(moduleIndex == 0){
						if(StringUtils.isNotBlank(netId)){
							TNewnetelement currentNetElement = tnewnetelementService.get(netId);
							model.addAttribute("netId",netId);
							model.addAttribute("netName",currentNetElement.getFname());
						}
					}
					model.addAttribute("list", list);
					return "modules/netconfig/tsingleNet";
				//模块类型为NES,弹出多选网元
				} else {
					// 选择多网元
					tVisExcelModuleDetail.setTemp_field3(tVisExcelModule.getNetType());
					return "modules/netconfig/tmoreNet";
				}
			}
		}
		return null;
	}
	
	@RequestMapping(value = "showParam")
	public String showParam(int excelId, int moduleIndex, Model model,
			@ModelAttribute("tVisExcelModuleDetail") TVisExcelModuleDetail tVisExcelModuleDetail,String cardLocal,HttpSession session) {
		//获取模板
		TVisExcelModule visExcelModule = new TVisExcelModule();
		visExcelModule.setExcelId(excelId);
		//获取模板下的所有模块,并存入session
		List<TVisExcelModule> excelModuleList = tVisExcelModuleService.findList(visExcelModule);
		tVisExcelModuleDetail.setTemp_field4(excelModuleList.size()+"");
		tVisExcelModuleDetail.setExcelId(excelId);
		
		if(excelModuleList != null && excelModuleList.size() > 0 && (moduleIndex+1) <= excelModuleList.size()){
			tVisExcelModuleDetail.setModuleId(Integer.parseInt(excelModuleList.get(moduleIndex).getId()));
			tVisExcelModuleDetail.setExecuteNo(0);
			List<TVisExcelModuleDetail> ltem = tVisExcelModuleDetailService.findParamList(tVisExcelModuleDetail);
			model.addAttribute("list", ltem);
		}
		String[] localArray = cardLocal.split(",");
		model.addAttribute("cardLocal", localArray);
		return "modules/netconfig/tVisParam";
	}
	
	
	@RequestMapping(value = "showCommand")
	public String showCommand(int excelId, int moduleIndex, Model model) {
		//获取模板
		TVisExcelModule visExcelModule = new TVisExcelModule();
		visExcelModule.setExcelId(excelId);
		//获取模板下的所有模块,并存入session
		List<TVisExcelModule> excelModuleList = tVisExcelModuleService.findList(visExcelModule);
		TVisExcelModuleDetail tVisExcelModuleDetail = new TVisExcelModuleDetail();
		tVisExcelModuleDetail.setExcelId(excelId);
		
		if(excelModuleList != null && excelModuleList.size() > 0 && (moduleIndex+1) <= excelModuleList.size()){
			
			
			TVisExcelModule tVisExcelModule = excelModuleList.get(moduleIndex);
			String currentNetType = tVisExcelModule.getNetType();
			
			tVisExcelModuleDetail.setModuleId(Integer.parseInt(tVisExcelModule.getId()));
			List<TVisExcelModuleDetail> list = tVisExcelModuleDetailService.findList(tVisExcelModuleDetail);
			
			TVisDanger tvd=new TVisDanger();
			tvd.setNettype(currentNetType);
			List<TVisDanger> visDangerList = tVisDangerService.findList(tvd);
			
			for(TVisExcelModuleDetail command : list){
				for(TVisDanger visDanger :visDangerList){
					if(StringUtils.isNotBlank(command.getConfCmd()) && 
						command.getConfCmd().toUpperCase().contains(visDanger.getCommand().toUpperCase().trim())){
						command.setTemp_field1("true");
						continue;
					}
				}
			}
			model.addAttribute("list", list);
		}
		return "modules/netconfig/tVisCommand";
	}
	
	
	@RequestMapping(value = "showModules")
	public String showModules(int excelId, Model model,
			@ModelAttribute("tVisExcelModule") TVisExcelModule tVisExcelModule) {
		//获取模板
		TVisExcelModule visExcelModule = new TVisExcelModule();
		visExcelModule.setExcelId(excelId);
		//获取模板下的所有模块
		List<TVisExcelModule> excelModuleList = tVisExcelModuleService.findList(visExcelModule);
		for(int i=0;i<excelModuleList.size();i++){
			excelModuleList.get(i).setTemp_field1(i+"");
		}
		model.addAttribute("list", excelModuleList);
		return "modules/netconfig/tExcelModules";
	}
	
	@RequiresPermissions("netconfig:excelTemplateAuto:view")
	@RequestMapping(value = "templateAutoExecute")
	public String templateAutoExecute(@Valid @ModelAttribute("tVisExcel") TVisExcel tVisExcel, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "modules/netconfig/tTemplateAutoExecute";
	}
	@RequestMapping(value = "saveLog")
	@ResponseBody
	public String saveLog(@RequestParam String excelId,@RequestParam Long datetime,@RequestParam String netIdLogs){
		TVisExcel visExcel = tVisExcelService.get(excelId);
		netIdLogs = StringEscapeUtils.unescapeHtml4(netIdLogs);
		String dateTimeStr = null;
		if(datetime != null){
			Date nowDate = new Date(datetime);
			dateTimeStr = DateUtils.formatDateTime(nowDate);
		}
		if(StringUtils.isNotBlank(netIdLogs)){
			JavaType javaType = JsonMapper.getInstance().getTypeFactory().constructParametricType(ArrayList.class, TVisExcelExcuteLog.class);
			List<TVisExcelExcuteLog> logList = null;
			try {
				logList = JsonMapper.getInstance().readValue(netIdLogs, javaType);
				for(TVisExcelExcuteLog excuteLog : logList){
					excuteLog.setDatetime(dateTimeStr);
					excuteLog.setNetid(excuteLog.getNetid());
					excuteLog.setLog(excuteLog.getLog());
					if(visExcel != null){
						excuteLog.setExcelid(visExcel.getId());
						excuteLog.setExcelname(visExcel.getName());
					}
					
					tVisExcelExcuteLogService.save(excuteLog);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			}
		}
		return null;
	}
	
	@RequiresPermissions("netconfig:excelTemplateAuto:view")
	@RequestMapping(value = "downloadZip")
	public void downLoadZip(@RequestParam String excelId,@RequestParam Long datetime,@RequestParam String netIdLogs,HttpServletRequest request, HttpServletResponse response) {
		
		String filePath = Global.getConfig("excelTemplateAutoFilePath");
		String resultPath = filePath + File.separator+UserUtils.getPrincipal().getId()+File.separator;
		File fileResult = new File(resultPath);
		if (!fileResult.exists()) {
			fileResult.mkdirs();
		}else{
			File[] files = fileResult.listFiles();
			for(int i=0;i<files.length;i++){
				files[i].delete();
			}
		}
		TVisExcel visExcel = tVisExcelService.get(excelId);
		netIdLogs = StringEscapeUtils.unescapeHtml4(netIdLogs);
		String dateTimeStr = null;
		if(datetime != null){
			Date nowDate = new Date(datetime);
			dateTimeStr = DateUtils.formatDateTime(nowDate);
		}
		List<TNewnetelement> netElements = tnewnetelementService.findList(null);
		Map<String,String> netElementMap = new HashMap<String,String>();
		for(TNewnetelement netElement : netElements){
			netElementMap.put(netElement.getId(), netElement.getFname());
		}
		
		if(StringUtils.isNotBlank(netIdLogs)){
			JavaType javaType = JsonMapper.getInstance().getTypeFactory().constructParametricType(ArrayList.class, TVisExcelExcuteLog.class);
			List<TVisExcelExcuteLog> logList = null;
			try {
				logList = JsonMapper.getInstance().readValue(netIdLogs, javaType);
				for(TVisExcelExcuteLog excuteLog : logList){
					excuteLog.setDatetime(dateTimeStr);
					excuteLog.setNetid(excuteLog.getNetid());
					excuteLog.setLog(excuteLog.getLog());
					if(visExcel != null){
						excuteLog.setExcelid(visExcel.getId());
						excuteLog.setExcelname(visExcel.getName());
					}
					File fileTxt = new File(resultPath + netElementMap.get(excuteLog.getNetid()) + ".txt");
					try {
						if (!fileTxt.exists()) {
							fileTxt.createNewFile();
						} else {
							fileTxt.delete();
							fileTxt.createNewFile();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					TExcelTemplateDebugController.writeToDebugLog(excuteLog.getLog().replaceAll("<br>", "\n"), fileTxt);
				}
				File[] files = fileResult.listFiles();
				if (files != null) {
					String[] filePaths = new String[files.length];
					for (int i = 0; i < files.length; i++) {
						filePaths[i] = files[i].getAbsolutePath();
					}
					try {
						FileUtils.writeZipAndDownload(response, filePaths, "autolog_"+DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}