/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.entity.TEpa;
import com.thinkgem.jeesite.modules.netconfig.entity.TOss;
import com.thinkgem.jeesite.modules.netconfig.service.TEpaService;

/**
 * EPA节点管理Controller
 * @author 王晶石
 * @version 2017-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tEpa")
public class TEpaController extends BaseController {

	@Autowired
	private TEpaService tEpaService;
	
	@ModelAttribute
	public TEpa get(@RequestParam(required=false) String id) {
		TEpa entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tEpaService.get(id);
		}
		if (entity == null){
			entity = new TEpa();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tEpa:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tEpa")TEpa tEpa, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TEpa> page = tEpaService.findPage(new Page<TEpa>(request, response), tEpa); 
		model.addAttribute("page", page);
		return "modules/netconfig/tEpaList";
	}

	@RequiresPermissions("netconfig:tEpa:view")
	@RequestMapping(value = "form")
	public String form(TEpa tEpa, Model model) {
		model.addAttribute("tEpa", tEpa);
		return "modules/netconfig/tEpaForm";
	}

	@RequiresPermissions("netconfig:tEpa:edit")
	@RequestMapping(value = "save")
	public String save(TEpa tEpa, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tEpa)){
			return form(tEpa, model);
		}
		tEpaService.save(tEpa);
		addMessage(redirectAttributes, "保存EPA节点成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tEpa/?repage";
	}
	
	@RequiresPermissions("netconfig:tEpa:edit")
	@RequestMapping(value = "delete")
	public String delete(TEpa tEpa, RedirectAttributes redirectAttributes) {
		tEpaService.delete(tEpa);
		addMessage(redirectAttributes, "删除EPA节点成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tEpa/?repage";
	}

}