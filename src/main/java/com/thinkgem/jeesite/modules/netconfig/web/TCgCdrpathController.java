/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TCg;
import com.thinkgem.jeesite.modules.netconfig.entity.TCgCdrpath;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.service.TCgCdrpathService;
import com.thinkgem.jeesite.modules.netconfig.service.TCgService;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;

/**
 * 单表生成Controller
 * @author wangjingshi
 * @version 2017-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tCgCdrpath")
public class TCgCdrpathController extends BaseController {

	@Autowired
	private TCgCdrpathService tCgCdrpathService;
	@Autowired
	private TCgService tCgService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@ModelAttribute
	public TCgCdrpath get(@RequestParam(required=false) String id) {
		TCgCdrpath entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCgCdrpathService.get(id);
		}
		if (entity == null){
			entity = new TCgCdrpath();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tCgCdrpath:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tCgCdrpath")TCgCdrpath tCgCdrpath, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TCgCdrpath> page = tCgCdrpathService.findPage(new Page<TCgCdrpath>(request, response), tCgCdrpath); 
		model.addAttribute("page", page);
		return "modules/netconfig/tCgCdrpathList";
	}

	@RequiresPermissions("netconfig:tCgCdrpath:view")
	@RequestMapping(value = "form")
	public String form(TCgCdrpath tCgCdrpath, Model model) {
		List<TCg> cgList = tCgService.findList(null);
		model.addAttribute("cgList", cgList);
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setType("2");//type设置为2代表SAEGW类型网元
		List<TNewnetelement> netList = tNewnetelementService.findList(tNewnetelement);
		model.addAttribute("netList", netList);
		model.addAttribute("tCgCdrpath", tCgCdrpath);
		return "modules/netconfig/tCgCdrpathForm";
	}

	@RequiresPermissions("netconfig:tCgCdrpath:edit")
	@RequestMapping(value = "save")
	public String save(TCgCdrpath tCgCdrpath, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tCgCdrpath)){
			return form(tCgCdrpath, model);
		}
		tCgCdrpathService.save(tCgCdrpath);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tCgCdrpath/?repage";
	}
	
	@RequiresPermissions("netconfig:tCgCdrpath:edit")
	@RequestMapping(value = "delete")
	public String delete(TCgCdrpath tCgCdrpath, RedirectAttributes redirectAttributes) {
		tCgCdrpathService.delete(tCgCdrpath);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tCgCdrpath/?repage";
	}

}