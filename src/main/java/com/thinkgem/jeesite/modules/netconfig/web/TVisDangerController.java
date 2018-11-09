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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisDanger;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisDangerStatus;
import com.thinkgem.jeesite.modules.netconfig.service.TVisDangerService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisDangerStatusService;

/**
 * 危险指令Controller
 * @author 王晶石
 * @version 2018-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tVisDanger")
public class TVisDangerController extends BaseController {

	@Autowired
	private TVisDangerService tVisDangerService;
	@Autowired
	private TVisDangerStatusService tVisDangerStatusService;
	
	@ModelAttribute
	public TVisDanger get(@RequestParam(required=false) String id) {
		TVisDanger entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tVisDangerService.get(id);
		}
		if (entity == null){
			entity = new TVisDanger();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tVisDanger:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tVisDanger")TVisDanger tVisDanger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TVisDanger> page = tVisDangerService.findPage(new Page<TVisDanger>(request, response), tVisDanger); 
		model.addAttribute("page", page);
		TVisDangerStatus tVisDangerStatus = tVisDangerStatusService.get("1");
		model.addAttribute("tVisDangerStatus", tVisDangerStatus);
		return "modules/netconfig/tVisDangerList";
	}

	@RequiresPermissions("netconfig:tVisDanger:view")
	@RequestMapping(value = "form")
	public String form(TVisDanger tVisDanger, Model model) {
		model.addAttribute("tVisDanger", tVisDanger);
		return "modules/netconfig/tVisDangerForm";
	}

	@RequiresPermissions("netconfig:tVisDanger:edit")
	@RequestMapping(value = "save")
	public String save(TVisDanger tVisDanger, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tVisDanger)){
			return form(tVisDanger, model);
		}
		tVisDangerService.save(tVisDanger);
		addMessage(redirectAttributes, "保存危险指令成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tVisDanger/?repage";
	}
	
	@RequiresPermissions("netconfig:tVisDanger:edit")
	@RequestMapping(value = "delete")
	public String delete(TVisDanger tVisDanger, RedirectAttributes redirectAttributes) {
		tVisDangerService.delete(tVisDanger);
		addMessage(redirectAttributes, "删除危险指令成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tVisDanger/?repage";
	}
	
	@RequestMapping(value = "updateStatus")
	public String updateStatus(String status) {
		TVisDangerStatus tVisDangerStatus = new TVisDangerStatus();
		tVisDangerStatus.setId("1");
		tVisDangerStatus.setStatus(status);
		tVisDangerStatusService.save(tVisDangerStatus);
		return "success";
	}

}