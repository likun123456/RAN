/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.web;

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
import com.thinkgem.jeesite.modules.cheat.entity.TConfig;
import com.thinkgem.jeesite.modules.cheat.service.TConfigService;

/**
 * 抓包参数设置Controller
 * @author 王晶石
 * @version 2017-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/tConfig")
public class TConfigController extends BaseController {

	@Autowired
	private TConfigService tConfigService;
	
	@ModelAttribute
	public TConfig get(@RequestParam(required=false) String id) {
		TConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tConfigService.get(id);
		}
		if (entity == null){
			entity = new TConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("cheat:tConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(TConfig tConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TConfig> page = tConfigService.findPage(new Page<TConfig>(request, response), tConfig); 
		model.addAttribute("page", page);
		return "modules/cheat/tConfigList";
	}

	@RequiresPermissions("cheat:tConfig:view")
	@RequestMapping(value = "form")
	public String form(TConfig tConfig, Model model) {
		model.addAttribute("tConfig", tConfig);
		return "modules/cheat/tConfigForm";
	}

	@RequiresPermissions("cheat:tConfig:edit")
	@RequestMapping(value = "save")
	public String save(TConfig tConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tConfig)){
			return form(tConfig, model);
		}
		tConfigService.save(tConfig);
		addMessage(redirectAttributes, "保存抓包参数设置成功");
		return "redirect:"+Global.getAdminPath()+"/cheat/tConfig/?repage";
	}
	
	@RequiresPermissions("cheat:tConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(TConfig tConfig, RedirectAttributes redirectAttributes) {
		tConfigService.delete(tConfig);
		addMessage(redirectAttributes, "删除抓包参数设置成功");
		return "redirect:"+Global.getAdminPath()+"/cheat/tConfig/?repage";
	}

}