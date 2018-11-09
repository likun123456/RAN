/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.netconfig.entity.TComputerroom;
import com.thinkgem.jeesite.modules.netconfig.service.TComputerroomService;

/**
 * 机房管理Controller
 * @author yh
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tComputerroom")
public class TComputerroomController extends BaseController {

	@Autowired
	private TComputerroomService tComputerroomService;
	
	@ModelAttribute
	public TComputerroom get(@RequestParam(required=false) String id) {
		TComputerroom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tComputerroomService.get(id);
		}
		if (entity == null){
			entity = new TComputerroom();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tComputerroom:view")
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("tComputerroom")TComputerroom tComputerroom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TComputerroom> page = tComputerroomService.findPage(new Page<TComputerroom>(request, response), tComputerroom); 
		model.addAttribute("page", page);
		return "modules/netconfig/tComputerroomList";
	}

	@RequiresPermissions("netconfig:tComputerroom:view")
	@RequestMapping(value = "form")
	public String form(TComputerroom tComputerroom, Model model) {
		model.addAttribute("tComputerroom", tComputerroom);
		return "modules/netconfig/tComputerroomForm";
	}

	@RequiresPermissions("netconfig:tComputerroom:edit")
	@RequestMapping(value = "save")
	public String save(TComputerroom tComputerroom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tComputerroom)){
			return form(tComputerroom, model);
		}
		tComputerroomService.save(tComputerroom);
		addMessage(redirectAttributes, "保存机房信息成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tComputerroom/?repage";
	}
	
	@RequiresPermissions("netconfig:tComputerroom:edit")
	@RequestMapping(value = "delete")
	public String delete(TComputerroom tComputerroom, RedirectAttributes redirectAttributes) {
		tComputerroomService.delete(tComputerroom);
		addMessage(redirectAttributes, "删除机房信息成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tComputerroom/?repage";
	}

}