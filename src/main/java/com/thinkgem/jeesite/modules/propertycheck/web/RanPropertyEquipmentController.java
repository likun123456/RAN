/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.propertycheck.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.thinkgem.jeesite.modules.propertycheck.entity.RanPropertyEquipment;
import com.thinkgem.jeesite.modules.propertycheck.service.RanPropertyEquipmentService;

/**
 * 资产信息核查Controller
 * @author 李昆
 * @version 2018-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/propertycheck/ranPropertyEquipment")
public class RanPropertyEquipmentController extends BaseController {

	@Autowired
	private RanPropertyEquipmentService ranPropertyEquipmentService;
	
	@ModelAttribute
	public RanPropertyEquipment get(@RequestParam(required=false) String id) {
		RanPropertyEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ranPropertyEquipmentService.get(id);
		}
		if (entity == null){
			entity = new RanPropertyEquipment();
		}
		return entity;
	}
	
	@RequiresPermissions("propertycheck:ranPropertyEquipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(RanPropertyEquipment ranPropertyEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RanPropertyEquipment> page = ranPropertyEquipmentService.findPage(new Page<RanPropertyEquipment>(request, response), ranPropertyEquipment); 
		model.addAttribute("page", page);
		return "modules/propertycheck/ranPropertyEquipmentList";
	}

	@RequiresPermissions("propertycheck:ranPropertyEquipment:view")
	@RequestMapping(value = "form")
	public String form(RanPropertyEquipment ranPropertyEquipment, Model model) {
		model.addAttribute("ranPropertyEquipment", ranPropertyEquipment);
		return "modules/propertycheck/ranPropertyEquipmentForm";
	}

	@RequiresPermissions("propertycheck:ranPropertyEquipment:edit")
	@RequestMapping(value = "save")
	public String save(RanPropertyEquipment ranPropertyEquipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ranPropertyEquipment)){
			return form(ranPropertyEquipment, model);
		}
		ranPropertyEquipmentService.save(ranPropertyEquipment);
		addMessage(redirectAttributes, "保存对象类型成功");
		return "redirect:"+Global.getAdminPath()+"/propertycheck/ranPropertyEquipment/?repage";
	}
	
	@RequiresPermissions("propertycheck:ranPropertyEquipment:edit")
	@RequestMapping(value = "delete")
	public String delete(RanPropertyEquipment ranPropertyEquipment, RedirectAttributes redirectAttributes) {
		ranPropertyEquipmentService.delete(ranPropertyEquipment);
		addMessage(redirectAttributes, "删除对象类型成功");
		return "redirect:"+Global.getAdminPath()+"/propertycheck/ranPropertyEquipment/?repage";
	}
	
	@RequiresPermissions("propertycheck:ranPropertyEquipment:edit")
	@RequestMapping(value = "insertBatch")
	public String insertBatch(RedirectAttributes redirectAttributes) {
		System.out.println();
		List<RanPropertyEquipment> list=new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			RanPropertyEquipment r=new RanPropertyEquipment();
			r.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			r.setSerialnumber(""+i);
			r.setSitename("FJB");
			r.setProductname("");
			r.setProductnumber("123");
			r.setProductionrevision("2");
			r.setProductiondate("2015");
			r.setManagerobject("mo");
			r.setManufacturerid("mid");
			r.setManufacturerrevision("mr");
			r.setNegotiatedbitrate("neg");
			r.setStatus("0");
			r.setLogdate(new Date());
			r.setMocategory("1");
			list.add(r);
		}
		
		ranPropertyEquipmentService.insertBatch(list);
		addMessage(redirectAttributes, "删除对象类型成功");
		return "redirect:"+Global.getAdminPath()+"/propertycheck/ranPropertyEquipment/?repage";
	}

}