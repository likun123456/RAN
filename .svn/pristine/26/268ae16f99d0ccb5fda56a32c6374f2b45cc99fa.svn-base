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
import com.thinkgem.jeesite.modules.netconfig.entity.TEquipment;
import com.thinkgem.jeesite.modules.netconfig.service.TEquipmentService;

/**
 * 拓扑设备管理Controller
 * @author yh
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tEquipment")
public class TEquipmentController extends BaseController {

	@Autowired
	private TEquipmentService tEquipmentService;
	
	@ModelAttribute
	public TEquipment get(@RequestParam(required=false) String id) {
		TEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tEquipmentService.get(id);
		}
		if (entity == null){
			entity = new TEquipment();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tEquipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("tEquipment")TEquipment tEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TEquipment> page = tEquipmentService.findPage(new Page<TEquipment>(request, response), tEquipment); 
		model.addAttribute("page", page);
		return "modules/netconfig/tEquipmentList";
	}

	@RequiresPermissions("netconfig:tEquipment:view")
	@RequestMapping(value = "form")
	public String form(TEquipment tEquipment, Model model) {
		model.addAttribute("tEquipment", tEquipment);
		return "modules/netconfig/tEquipmentForm";
	}

	@RequiresPermissions("netconfig:tEquipment:edit")
	@RequestMapping(value = "save")
	public String save(TEquipment tEquipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tEquipment)){
			return form(tEquipment, model);
		}
		tEquipmentService.save(tEquipment);
		addMessage(redirectAttributes, "保存拓扑设备成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tEquipment/?repage";
	}
	
	@RequiresPermissions("netconfig:tEquipment:edit")
	@RequestMapping(value = "delete")
	public String delete(TEquipment tEquipment, RedirectAttributes redirectAttributes) {
		tEquipmentService.delete(tEquipment);
		addMessage(redirectAttributes, "删除拓扑设备成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tEquipment/?repage";
	}

}