/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.util.FileUtil;
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
import com.thinkgem.jeesite.modules.cms.utils.ModifyFileUtil;
import com.thinkgem.jeesite.modules.userquery.entity.TDiameterConfig;
import com.thinkgem.jeesite.modules.userquery.service.TDiameterConfigService;

/**
 * 信令追踪DIAMETER端口设置Controller
 * @author 王晶石
 * @version 2017-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/userquery/tDiameterConfig")
public class TDiameterConfigController extends BaseController {

	@Autowired
	private TDiameterConfigService tDiameterConfigService;
	
	@ModelAttribute
	public TDiameterConfig get(@RequestParam(required=false) String id) {
		TDiameterConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tDiameterConfigService.get(id);
		}
		if (entity == null){
			entity = new TDiameterConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("userquery:tDiameterConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(TDiameterConfig tDiameterConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TDiameterConfig> page = tDiameterConfigService.findPage(new Page<TDiameterConfig>(request, response), tDiameterConfig); 
		model.addAttribute("page", page);
		return "modules/userquery/tDiameterConfigList";
	}

	@RequiresPermissions("userquery:tDiameterConfig:view")
	@RequestMapping(value = "form")
	public String form(TDiameterConfig tDiameterConfig, Model model) {
		model.addAttribute("tDiameterConfig", tDiameterConfig);
		return "modules/userquery/tDiameterConfigForm";
	}

	@RequiresPermissions("userquery:tDiameterConfig:edit")
	@RequestMapping(value = "save")
	public String save(TDiameterConfig tDiameterConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tDiameterConfig)){
			return form(tDiameterConfig, model);
		}
		tDiameterConfigService.save(tDiameterConfig);
		String preferencesPath = Global.getConfig("preferencesPath");
		if(tDiameterConfig.getDiameterType().equals("TCP")){
			new ModifyFileUtil(preferencesPath, "diameter.tcp.ports", "diameter.tcp.ports: "+tDiameterConfig.getDiameterPort());
		}else{
			new ModifyFileUtil(preferencesPath, "diameter.sctp.ports", "diameter.sctp.ports: "+tDiameterConfig.getDiameterPort());
		}
		addMessage(redirectAttributes, "保存信令追踪DIAMETER端口设置成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/tDiameterConfig/?repage";
	}
	
	@RequiresPermissions("userquery:tDiameterConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(TDiameterConfig tDiameterConfig, RedirectAttributes redirectAttributes) {
		tDiameterConfigService.delete(tDiameterConfig);
		addMessage(redirectAttributes, "删除信令追踪DIAMETER端口设置成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/tDiameterConfig/?repage";
	}
	


}