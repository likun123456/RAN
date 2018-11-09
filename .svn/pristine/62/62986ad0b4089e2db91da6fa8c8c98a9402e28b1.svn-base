/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckConfig;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckLog;
import com.thinkgem.jeesite.modules.autocheck.service.TAutoCheckLogService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;

/**
 * 智能巡检Controller
 * @author zhuguangrui
 * @version 2017-08-04
 */
@Controller
@RequestMapping(value = "${adminPath}/autocheck/tAutoCheckLog")
public class TAutoCheckLogController extends BaseController {

	@Autowired
	private TAutoCheckLogService tAutoCheckLogService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@ModelAttribute
	public TAutoCheckLog get(@RequestParam(required=false) String id) {
		TAutoCheckLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tAutoCheckLogService.get(id);
		}
		if (entity == null){
			entity = new TAutoCheckLog();
		}
		return entity;
	}
	
	@RequiresPermissions("autocheck:tAutoCheckLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tAutoCheckLog") TAutoCheckLog tAutoCheckLog,HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//model.addAttribute("checkItemList", checkItemList);
		return "modules/autocheck/tAutoCheckLogList";
	}
	
	@ResponseBody
	@RequestMapping(value = {"queryCheckItems"})
	public String queryCheckItems(String netType) {
		List<TAutoCheckConfig> checkItemList = tAutoCheckLogService.queryCheckItems(netType);
		return JsonMapper.getInstance().toJson(checkItemList);
	}
	
	@ResponseBody
	@RequiresPermissions("autocheck:tAutoCheckLog:view")
	@RequestMapping(value = {"queryList"})
	public String queryList(@Valid @ModelAttribute("tAutoCheckLog") TAutoCheckLog tAutoCheckLog,int limit, int offset,
			String sortName,String sortOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		ServerPagination<TAutoCheckLog> page = new ServerPagination<TAutoCheckLog>();
		List<TAutoCheckLog> list = tAutoCheckLogService.queryList(tAutoCheckLog.getNetType(),tAutoCheckLog.getNetid(),tAutoCheckLog.getItemName(),tAutoCheckLog.getCheckResult(),tAutoCheckLog.getBeginDate(),
				tAutoCheckLog.getEndDate(),offset,limit,sortName,sortOrder);
		int total = tAutoCheckLogService.queryListCount(tAutoCheckLog.getNetType(),tAutoCheckLog.getNetid(),tAutoCheckLog.getItemName(),tAutoCheckLog.getCheckResult(),tAutoCheckLog.getBeginDate(),
				tAutoCheckLog.getEndDate());
		page.setTotal(total);
		page.setRows(list);
		return JsonMapper.getInstance().toJson(page);
	}

	@RequiresPermissions("autocheck:tAutoCheckLog:view")
	@RequestMapping(value = {"showCheckLog"})
	public String showCheckLog(String id, Model model) {
		TAutoCheckLog  checkLog = tAutoCheckLogService.get(id);
		if(checkLog != null && checkLog.getCheckLog()!=null){
			String[] logDetail = checkLog.getCheckLog().split("\\|\\|");
			ArrayList<String> headlist = new ArrayList<>();
			ArrayList<String> bodylist = new ArrayList<>();
			int count=0;
			if(logDetail != null && logDetail.length>0) {
				for(String s : logDetail) {
					if(s.equals("==================================================")) {
						count ++;
					}
					if(count < 2) {
						bodylist.add(s);
					}else {
						headlist.add(s);
					}
				}
			}
			
			//model.addAttribute("checkLog", Arrays.asList(logDetail));
			model.addAttribute("bodylist", bodylist);
			model.addAttribute("headlist", headlist);
		}
		return "modules/autocheck/tAutoCheckLogDetail";
	}
	
	@RequiresPermissions("autocheck:tAutoCheckLog:view")
	@RequestMapping(value = {"downloadCheckLog"})
	public void downloadCheckLog(String id,HttpServletRequest request,HttpServletResponse response){
		
		TAutoCheckLog  checkLog = tAutoCheckLogService.get(id);
		response.reset();
        response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename="+checkLog.getNetName()+checkLog.getCheckTime().replaceAll("[-:\\s]+", "")+".log");
		OutputStream ros = null;
		BufferedWriter bw = null;
	       try {
			ros = response.getOutputStream();
			bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
			if(checkLog != null && checkLog.getCheckLog()!=null){
				String[] logDetail = checkLog.getCheckLog().split("\\|\\|");
				for(String logLine : logDetail){
					bw.write(logLine);
					bw.newLine();
				}
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				if(bw != null){
					bw.close();
				}
				if(ros != null){
					ros.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@ResponseBody
	@RequiresPermissions("autocheck:tAutoCheckLog:view")
	@RequestMapping(value = {"queryMaxCheckTime"})
	public String queryMaxCheckTime(HttpServletRequest request, HttpServletResponse response, Model model) {
		return tAutoCheckLogService.queryMaxCheckTime();
	}

}