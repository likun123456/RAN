/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelExcuteLog;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelExcuteLogService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;

/**
 * 执行模板日志Controller
 * @author 王晶石
 * @version 2018-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tVisExcelExcuteLog")
public class TVisExcelExcuteLogController extends BaseController {

	@Autowired
	private TVisExcelExcuteLogService tVisExcelExcuteLogService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@ModelAttribute
	public TVisExcelExcuteLog get(@RequestParam(required=false) String id) {
		TVisExcelExcuteLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tVisExcelExcuteLogService.get(id);
		}
		if (entity == null){
			entity = new TVisExcelExcuteLog();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tVisExcelExcuteLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tVisExcelExcuteLog")TVisExcelExcuteLog tVisExcelExcuteLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PoolNetVO>  poolNetList = new ArrayList<PoolNetVO>();
		TNewnetelement tNewnetelementMME = new TNewnetelement();
		tNewnetelementMME.setType("1");//type设置为1代表MME类型网元
		List<TPool> poolMMEList = tPoolService.queryPoolListByType("MME");
		List<TNewnetelement> netMMEListAll = tNewnetelementService.findListByServiceType(tNewnetelementMME);
		for(TPool pool : poolMMEList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netMMEListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		TNewnetelement tNewnetelementSAEGW = new TNewnetelement();
		tNewnetelementSAEGW.setType("2");
		List<TPool> poolSAEGWList = tPoolService.queryPoolListByType("SAEGW");
		List<TNewnetelement> netSAEGWListAll = tNewnetelementService.findListByServiceType(tNewnetelementSAEGW);
		for(TPool pool : poolSAEGWList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netSAEGWListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		TNewnetelement tNewnetelementPCRF = new TNewnetelement();
		tNewnetelementPCRF.setType("3");
		List<TPool> poolPCRFList = tPoolService.queryPoolListByType("PCRF");
		List<TNewnetelement> netPCRFListAll = tNewnetelementService.findListByServiceType(tNewnetelementPCRF);
		for(TPool pool : poolPCRFList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netPCRFListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		Page<TVisExcelExcuteLog> page = tVisExcelExcuteLogService.findPage(new Page<TVisExcelExcuteLog>(request, response), tVisExcelExcuteLog); 
		model.addAttribute("page", page);
		model.addAttribute("poolNetList", poolNetList);
		return "modules/netconfig/tVisExcelExcuteLogList";
	}
	
	@RequestMapping(value = { "download" })
	public void download(String log,String excelname,String netname,String datetime,HttpServletRequest request, HttpServletResponse response){
		System.out.println(log);
		String logs[] = log.split("&lt;br&gt;");
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<logs.length;i++) {
			sb.append(logs[i]);
			sb.append("\n");
		}
		File f = new File(excelname+"_"+netname+"_"+datetime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ","")+".txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.write(sb.toString());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileUtils.downFile(f, request, response);
	}
	
	
	@RequestMapping(value = {"downLoadZip"})
	public void downLoadZip(String ids,HttpServletResponse response){
		if(ids.contains("on")) {
			ids = ids.substring(3, ids.length()-1);
		}else {
			ids = ids.substring(0,ids.length()-1);
		}
		List<TVisExcelExcuteLog> list = tVisExcelExcuteLogService.findListByIds(ids);
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		List<File> filelist = new ArrayList<File>();
		TVisExcelExcuteLog t;
		for(int i=0;i<list.size();i++) {
			t = list.get(i);
			String logs[] = t.getLog().split("<br>");
			StringBuffer sb = new StringBuffer();
			for(int j=0;j<logs.length;j++) {
				sb.append(logs[j]);
				sb.append("\n");
			}
			File f = new File(t.getExcelname()+"_"+t.getNetname()+"_"+t.getDatetime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ","")+".txt");
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				PrintWriter pw = new PrintWriter(f);
				pw.write(sb.toString());
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			filelist.add(f);
		}
		try {
			FileUtils.writeZipAndDownload(response,filelist,fileName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@RequiresPermissions("netconfig:tVisExcelExcuteLog:view")
	@RequestMapping(value = "form")
	public String form(TVisExcelExcuteLog tVisExcelExcuteLog, Model model) {
		model.addAttribute("tVisExcelExcuteLog", tVisExcelExcuteLog);
		return "modules/netconfig/tVisExcelExcuteLogForm";
	}

	@RequiresPermissions("netconfig:tVisExcelExcuteLog:edit")
	@RequestMapping(value = "save")
	public String save(TVisExcelExcuteLog tVisExcelExcuteLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tVisExcelExcuteLog)){
			return form(tVisExcelExcuteLog, model);
		}
		tVisExcelExcuteLogService.save(tVisExcelExcuteLog);
		addMessage(redirectAttributes, "保存执行模板日志成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tVisExcelExcuteLog/?repage";
	}
	
	@RequiresPermissions("netconfig:tVisExcelExcuteLog:edit")
	@RequestMapping(value = "delete")
	public String delete(TVisExcelExcuteLog tVisExcelExcuteLog, RedirectAttributes redirectAttributes) {
		tVisExcelExcuteLogService.delete(tVisExcelExcuteLog);
		addMessage(redirectAttributes, "删除执行模板日志成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tVisExcelExcuteLog/?repage";
	}

}