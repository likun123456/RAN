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
import com.thinkgem.jeesite.modules.netconfig.entity.TLockTemplate;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.service.TLockTemplateService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;

/**
 * 网元闭锁管理Controller
 * 
 * @author 杨海
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tLockTemplate")
public class TLockTemplateController extends BaseController {

	@Autowired
	private TLockTemplateService tLockTemplateService;
	@Autowired
	private TVisExcelService tVisExcelService;

	@ModelAttribute
	public TLockTemplate get(@RequestParam(required = false) String id) {
		TLockTemplate entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tLockTemplateService.get(id);
		}
		if (entity == null) {
			entity = new TLockTemplate();
		}
		return entity;
	}

	@RequiresPermissions("netconfig:tLockTemplate:view")
	@RequestMapping(value = { "list", "" })
	public String list(@Valid @ModelAttribute("tLockTemplate") TLockTemplate tLockTemplate, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<TLockTemplate> page = tLockTemplateService.findPage(new Page<TLockTemplate>(request, response),
				tLockTemplate);
		List<TVisExcel> tel = tVisExcelService.findList(new TVisExcel());
		for (int i = 0; i < page.getList().size(); i++) {
			for (int m = 0; m < tel.size(); m++) {
				if (Integer.parseInt(tel.get(m).getId()) == page.getList().get(i).getLockId()) {
					page.getList().get(i).setLockName(tel.get(m).getName());
				}
				if (Integer.parseInt(tel.get(m).getId()) == page.getList().get(i).getUnLockId()) {
					page.getList().get(i).setUnLockName(tel.get(m).getName());
				}
				if (Integer.parseInt(tel.get(m).getId()) == page.getList().get(i).getReBootId()) {
					page.getList().get(i).setReBootName(tel.get(m).getName());
				}
			}
		}
		model.addAttribute("page", page);
		return "modules/netconfig/tLockTemplateList";
	}

	@RequiresPermissions("netconfig:tLockTemplate:view")
	@RequestMapping(value = "form")
	public String form(TLockTemplate tLockTemplate, Model model) {
		TVisExcel te=new TVisExcel();
		if(null!=tLockTemplate){
			te.setType(tLockTemplate.getType());
		}
		List<TVisExcel> visExcelList = tVisExcelService.findList(te);
		model.addAttribute("visExcelList", visExcelList);
		model.addAttribute("tLockTemplate", tLockTemplate);
		return "modules/netconfig/tLockTemplateForm";
	}

	@RequiresPermissions("netconfig:tLockTemplate:view")
	@RequestMapping(value = "save")
	public String save(TLockTemplate tLockTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tLockTemplate)) {
			return form(tLockTemplate, model);
		}
		tLockTemplateService.save(tLockTemplate);
		addMessage(redirectAttributes, "保存网元闭锁模版成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/tLockTemplate/list/?repage";
	}

}