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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormula;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormulaScripts;
import com.thinkgem.jeesite.modules.netconfig.service.TFormulaScriptsService;
import com.thinkgem.jeesite.modules.netconfig.service.TFormulaService;

/**
 * 公式配置Controller
 * @author liuliang
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tFormula")
public class TFormulaController extends BaseController {

	@Autowired
	private TFormulaService tFormulaService;
	
	@Autowired
	private TFormulaScriptsService formulaScriptsService;
	
	@ModelAttribute
	public TFormula get(@RequestParam(required=false) String id) {
		TFormula entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tFormulaService.get(id);
		}
		if (entity == null){
			entity = new TFormula();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tFormula:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tFormula")TFormula tFormula, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TFormula> page = tFormulaService.findPage(new Page<TFormula>(request, response), tFormula); 
		model.addAttribute("page", page);
		return "modules/netconfig/tFormulaList";
	}

	@RequiresPermissions("netconfig:tFormula:view")
	@RequestMapping(value = "form")
	public String form(TFormula tFormula, Model model) {
		//tFormula.setFlag("1");
		TFormulaScripts scripts = new TFormulaScripts();
		scripts.setScriptsType((byte)1);
		List<TFormulaScripts> scriptsList1 = formulaScriptsService.findList(scripts);
		scripts.setScriptsType((byte)2);
		List<TFormulaScripts> scriptsList2 = formulaScriptsService.findList(scripts);
		model.addAttribute("scriptsList1", scriptsList1);
		model.addAttribute("scriptsList2", scriptsList2);
		model.addAttribute("tFormula", tFormula);
		return "modules/netconfig/tFormulaForm";
	}

	@RequiresPermissions("netconfig:tFormula:edit")
	@RequestMapping(value = "save")
	public String save(TFormula tFormula, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tFormula)){
			return form(tFormula, model);
		}
		/*公式重新赋值*/
		tFormula.setFormula(tFormulaService.rebulidFormula(tFormula.getFormulatext()));
		
		if(null==tFormula.getFormulaType()||tFormula.getFormulaType().intValue() == 0) {
			
			tFormula.setTopThreshold(null);
			tFormula.setDownThreshold(null);
		}
//			
//			tFormula.setThresholdup(null);
//			tFormula.setThresholdup2(null);
//			tFormula.setResumedown(null);
//			tFormula.setResumedown2(null);
//			
//			tFormula.setThresholdupScript(null);
//			tFormula.setThresholdup2Script(null);
//			tFormula.setResumedownScript(null);
//			tFormula.setResumedown2Script(null);
//			
//			
//			tFormula.setThresholddown("".equals(tFormula.getThresholddown())?null:tFormula.getThresholddown());
//			tFormula.setThresholddown2("".equals(tFormula.getThresholddown2())?null:tFormula.getThresholddown2());
//			tFormula.setResumeup("".equals(tFormula.getResumeup())?null:tFormula.getResumeup());
//			tFormula.setResumeup2("".equals(tFormula.getResumeup2())?null:tFormula.getResumeup2());
//			tFormula.setThresholddownScript("".equals(tFormula.getThresholddownScript())?null:tFormula.getThresholddownScript());
//			tFormula.setThresholddown2Script("".equals(tFormula.getThresholddown2Script())?null:tFormula.getThresholddown2Script());
//			tFormula.setResumeupScript("".equals(tFormula.getResumeupScript())?null:tFormula.getResumeupScript());
//			tFormula.setResumeup2Script("".equals(tFormula.getResumeup2Script())?null:tFormula.getResumeup2Script());
//		} else {
//			tFormula.setThresholddown(null);
//			tFormula.setThresholddown2(null);
//			tFormula.setResumeup(null);
//			tFormula.setResumeup2(null);
//			
//			tFormula.setThresholddownScript(null);
//			tFormula.setThresholddown2Script(null);
//			tFormula.setResumeupScript(null);
//			tFormula.setResumeup2Script(null);
//			
//			
//			
//			tFormula.setThresholdup("".equals(tFormula.getThresholdup())?null:tFormula.getThresholdup());
//			tFormula.setThresholdup2("".equals(tFormula.getThresholdup2())?null:tFormula.getThresholdup2());
//			tFormula.setResumedown("".equals(tFormula.getResumedown())?null:tFormula.getResumedown());
//			tFormula.setResumedown2("".equals(tFormula.getResumedown2())?null:tFormula.getResumedown2());
//			tFormula.setThresholdupScript("".equals(tFormula.getThresholdupScript())?null:tFormula.getThresholdupScript());
//			tFormula.setThresholdup2Script("".equals(tFormula.getThresholdup2Script())?null:tFormula.getThresholdup2Script());
//			tFormula.setResumedownScript("".equals(tFormula.getResumedownScript())?null:tFormula.getResumedownScript());
//			tFormula.setResumedown2Script("".equals(tFormula.getResumedown2Script())?null:tFormula.getResumedown2Script());
//		}
		
		tFormulaService.save(tFormula);
		addMessage(redirectAttributes, "保存公式配置成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tFormula/?repage";
	}
	
	@RequiresPermissions("netconfig:tFormula:edit")
	@RequestMapping(value = "delete")
	public String delete(TFormula tFormula, RedirectAttributes redirectAttributes) {
		tFormulaService.delete(tFormula);
		addMessage(redirectAttributes, "删除公式配置成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tFormula/?repage";
	}
	
	
	

}