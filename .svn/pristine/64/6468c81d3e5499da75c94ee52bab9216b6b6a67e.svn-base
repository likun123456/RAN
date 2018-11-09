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
import com.thinkgem.jeesite.modules.netconfig.entity.TDns;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TDnsService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;

/**
 * EPA节点管理Controller
 * 
 * @author 王晶石
 * @version 2017-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tDns")
public class TDnsController extends BaseController {

	@Autowired
	private TDnsService tDnsService;
	@Autowired
	private TPoolService tPoolService;

	@ModelAttribute
	public TDns get(@RequestParam(required = false) String id) {
		TDns entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tDnsService.get(id);
		}
		if (entity == null) {
			entity = new TDns();
		}
		return entity;
	}

	@RequiresPermissions("netconfig:tDns:view")
	@RequestMapping(value = { "list", "" })
	public String list(@Valid @ModelAttribute("tDns") TDns tDns, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		tDns.setType(4);
		Page<TDns> page = tDnsService.findPage(new Page<TDns>(request, response), tDns);
		model.addAttribute("page", page);
		return "modules/netconfig/tDnsList";
	}

	@RequiresPermissions("netconfig:tDns:view")
	@RequestMapping(value = "form")
	public String form(TDns tDns, Model model) {
		model.addAttribute("tDns", tDns);
		return "modules/netconfig/tDnsForm";
	}

	@RequiresPermissions("netconfig:tDns:edit")
	@RequestMapping(value = "save")
	public String save(TDns tDns, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tDns)) {
			return form(tDns, model);
		}
		List<TPool> pList = tPoolService.queryPoolListByType("OTHER_NE");
		if (pList.size() > 0) {
			tDns.setFnid(Integer.parseInt(pList.get(0).getId()));
		}
		tDns.setType(4);
		tDns.setFactory(2);
		tDnsService.save(tDns);
		addMessage(redirectAttributes, "保存节点成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/tDns/?repage";
	}

	@RequiresPermissions("netconfig:tDns:edit")
	@RequestMapping(value = "delete")
	public String delete(TDns tDns, RedirectAttributes redirectAttributes) {
		tDnsService.delete(tDns);
		addMessage(redirectAttributes, "删除节点成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/tDns/?repage";
	}

}