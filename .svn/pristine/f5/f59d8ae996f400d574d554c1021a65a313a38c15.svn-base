/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.web;

import java.util.List;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TEpcDic;
import com.thinkgem.jeesite.modules.netconfig.service.TEpcDicService;

/**
 * 授权码管理Controller
 * @author yanghai
 * @version 2018-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/authCode")
public class TAuthCodeController extends BaseController {

	@Autowired
	private TEpcDicService tAuthCodeService;
	private static final String EPC_DIC_AUTHCODEType="AUTHCODE";
	@ModelAttribute
	public TEpcDic get(@RequestParam(required=false) String id) {
		TEpcDic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tAuthCodeService.get(id);
		}
		if (entity == null){
			entity = new TEpcDic();
		}
		return entity;
	}

	@RequiresPermissions("netconfig:authCode:form")
	@RequestMapping(value = "form")
	public String form(@Valid @ModelAttribute("tAuthCode")TEpcDic entity, Model model) {
		entity.setType(EPC_DIC_AUTHCODEType);
		List<TEpcDic> tEpcDicList=tAuthCodeService.findList(entity);
		if(tEpcDicList.size()>0){
		model.addAttribute("tAuthCode", tEpcDicList.get(0));
		}
		return "modules/netconfig/tAuthCodeForm";
	}

	@RequiresPermissions("netconfig:authCode:form")
	@RequestMapping(value = "save")
	public String save(TEpcDic entity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, entity)){
			return form(entity, model);
		}
		entity.setType(EPC_DIC_AUTHCODEType);
		entity.setRemark("授权码管理");
		tAuthCodeService.save(entity);
		addMessage(redirectAttributes, "保存授权码成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/authCode/form";
	}

}