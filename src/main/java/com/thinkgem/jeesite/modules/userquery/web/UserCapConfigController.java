package com.thinkgem.jeesite.modules.userquery.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.userquery.entity.TCapConfig;
import com.thinkgem.jeesite.modules.userquery.service.TCapConfigService;

/**
 * 单用户信令追踪配置Controller
 * @author chenhongbo
 * @version 2017-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/userquery/CapConfig")
public class UserCapConfigController extends BaseController {
	
	@Autowired
	private TCapConfigService capConfigService;

	@RequiresPermissions("userquery:capConfig:view")
	@RequestMapping(value = {"list", ""})
	public String show(Model model) {
		TCapConfig capConfig = capConfigService.get("0");
		model.addAttribute("capConfig", capConfig);
		return "modules/userquery/userCapConfig";
	}
	
	@RequiresPermissions("userquery:capConfig:edit")
	@RequestMapping(value = "update")
	public String update(TCapConfig capConfig, Model model, RedirectAttributes redirectAttributes) {
		capConfigService.save(capConfig);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/CapConfig/?repage";
	}
	
}
