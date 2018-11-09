/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.performance.entity.TCaseLibrary;
import com.thinkgem.jeesite.modules.performance.service.TCaseLibraryService;

/**
 * 异常原因多维分析(ebmlog案例库管理)Controller
 * @author 王晶石
 * @version 2017-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/tCaseLibrary")
public class TCaseLibraryController extends BaseController {

	@Autowired
	private TCaseLibraryService tCaseLibraryService;
	
	@ModelAttribute
	public TCaseLibrary get(@RequestParam(required=false) String id) {
		TCaseLibrary entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCaseLibraryService.get(id);
		}
		if (entity == null){
			entity = new TCaseLibrary();
		}
		return entity;
	}
	
	
	@RequiresPermissions("performance:tCaseLibrary:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tCaseLibrary")TCaseLibrary tCaseLibrary,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TCaseLibrary> page = tCaseLibraryService.findPage(new Page<TCaseLibrary>(request, response), tCaseLibrary); 
		model.addAttribute("page", page);
		if(tCaseLibrary.getCc()==null||tCaseLibrary.getCc().equals("")){
			return "modules/performance/tCaseLibraryList";
		}else{
			return "modules/performance/tEbmlogStatisticsCase";
		}
	}

	@RequiresPermissions("performance:tCaseLibrary:view")
	@RequestMapping(value = "form")
	public String form(TCaseLibrary tCaseLibrary, Model model) {
		model.addAttribute("tCaseLibrary", tCaseLibrary);
		return "modules/performance/tCaseLibraryForm";
	}

	@RequiresPermissions("performance:tCaseLibrary:edit")
	@RequestMapping(value = "save")
	public String save(TCaseLibrary tCaseLibrary, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tCaseLibrary)){
			return form(tCaseLibrary, model);
		}
		tCaseLibraryService.save(tCaseLibrary);
		addMessage(redirectAttributes, "保存案例库成功");
		return "redirect:"+Global.getAdminPath()+"/performance/tCaseLibrary/?repage";
	}
	
	@RequiresPermissions("performance:tCaseLibrary:edit")
	@RequestMapping(value = "delete")
	public String delete(TCaseLibrary tCaseLibrary, RedirectAttributes redirectAttributes) {
		tCaseLibraryService.delete(tCaseLibrary);
		addMessage(redirectAttributes, "删除案例库成功");
		return "redirect:"+Global.getAdminPath()+"/performance/tCaseLibrary/?repage";
	}
	
	

}