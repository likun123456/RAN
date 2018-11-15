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
		List<RanPropertyEquipment> siteInfoList;
		String inputSiteName=request.getParameter("inputSiteName");
		Page<RanPropertyEquipment> page = ranPropertyEquipmentService.findPage(new Page<RanPropertyEquipment>(request, response), ranPropertyEquipment); 
		model.addAttribute("page", page);
		try{
			siteInfoList=ranPropertyEquipmentService.getInfoBySiteName(inputSiteName.trim());
			model.addAttribute("siteInfoList",siteInfoList);
			
		}catch(NullPointerException e) {
			//返回根据第一个站点名查询到的站点信息
			siteInfoList=ranPropertyEquipmentService.getInfoBySiteName(ranPropertyEquipmentService.getFirstSiteNameToShow());
			model.addAttribute("siteInfoList",siteInfoList);
			}
		//获取所有的站点名用来显示在下拉框里提供选择		
		List<String> allSiteName=ranPropertyEquipmentService.getAllSiteName();
		
		model.addAttribute("allSiteName",allSiteName);
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
		ranPropertyEquipmentService.batchInsert();
		addMessage(redirectAttributes, "添加对象类型成功");
		return "redirect:"+Global.getAdminPath()+"/propertycheck/ranPropertyEquipment/?repage";
	}

}