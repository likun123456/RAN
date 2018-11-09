/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.paramconfig.web;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.paramconfig.entity.TParamExportedExcel;
import com.thinkgem.jeesite.modules.paramconfig.service.TParamExportedExcelService;

/**
 * 网元参数信息报表Controller
 * @author 王晶石
 * @version 2017-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/paramconfig/tParamExportedExcel")
public class TParamExportedExcelController extends BaseController {

	@Autowired
	private TParamExportedExcelService tParamExportedExcelService;
	
	@ModelAttribute
	public TParamExportedExcel get(@RequestParam(required=false) String id) {
		TParamExportedExcel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tParamExportedExcelService.get(id);
		}
		if (entity == null){
			entity = new TParamExportedExcel();
		}
		return entity;
	}
	
	@RequiresPermissions("paramconfig:tParamExportedExcel:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tParamExportedExcel")TParamExportedExcel tParamExportedExcel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TParamExportedExcel> page = tParamExportedExcelService.findPage(new Page<TParamExportedExcel>(request, response), tParamExportedExcel); 
		model.addAttribute("page", page);
		return "modules/paramconfig/tParamExportedExcelList";
	}
	
	@RequestMapping(value = { "downloadExcel" })
	public void downloadExcel(String path,HttpServletRequest request, HttpServletResponse response){
		File f = new File(path);
		FileUtils.downFile(f, request, response);
	}
	
	@RequestMapping(value = {"downLoadZip"})
	public void downLoadZip(String paths,HttpServletResponse response){
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		try {
			FileUtils.writeZipAndDownload(response, paths.split(","),fileName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}


}