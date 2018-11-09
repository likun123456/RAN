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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TCg;
import com.thinkgem.jeesite.modules.netconfig.entity.TComputerroom;
import com.thinkgem.jeesite.modules.netconfig.service.TCgService;
import com.thinkgem.jeesite.modules.netconfig.service.TComputerroomService;

/**
 * 单表生成Controller
 * @author wangjingshi
 * @version 2017-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tCg")
public class TCgController extends BaseController {

	@Autowired
	private TComputerroomService tComputerroomService;
	@Autowired
	private TCgService tCgService;
	
	@ModelAttribute
	public TCg get(@RequestParam(required=false) String id) {
		TCg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCgService.get(id);
		}
		if (entity == null){
			entity = new TCg();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tCg:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tCg")TCg tCg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TCg> page = tCgService.findPage(new Page<TCg>(request, response), tCg); 
		model.addAttribute("page", page);
		return "modules/netconfig/tCgList";
	}

	@RequiresPermissions("netconfig:tCg:view")
	@RequestMapping(value = "form")
	public String form(TCg tCg, Model model) {
		List<TComputerroom> crList = tComputerroomService.findList(null);
		model.addAttribute("crList",crList);
		model.addAttribute("tCg", tCg);
		return "modules/netconfig/tCgForm";
	}

	@RequiresPermissions("netconfig:tCg:edit")
	@RequestMapping(value = "save")
	public String save(TCg tCg, Model model, RedirectAttributes redirectAttributes) {
		tCg.setFpassword(Encodes.unescapeHtml(tCg.getFpassword()));
		if (!beanValidator(model, tCg)){
			return form(tCg, model);
		}
		tCgService.save(tCg);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tCg/?repage";
	}
	
	@RequiresPermissions("netconfig:tCg:edit")
	@RequestMapping(value = "delete")
	public String delete(TCg tCg, RedirectAttributes redirectAttributes) {
		tCgService.delete(tCg);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tCg/?repage";
	}
	
	/**
	 * 验证CG名是否有效
	 * @param oldName
	 * @param fname
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String fname) {
		if (fname!=null && fname.equals(oldName)) {
			return "true";
		} else if (fname!=null && tCgService.getCgByName(fname) == 0) {
			return "true";
		}
		return "false";
	}

}