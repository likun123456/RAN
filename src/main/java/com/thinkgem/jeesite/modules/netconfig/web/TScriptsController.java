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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormulaScripts;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormulaScriptsCommands;
import com.thinkgem.jeesite.modules.netconfig.service.TFormulaScriptsCommandsService;
import com.thinkgem.jeesite.modules.netconfig.service.TFormulaScriptsService;

@Controller
@RequestMapping(value = "${adminPath}/netconfig/tScripts")
public class TScriptsController extends BaseController  {

	@Autowired
	private TFormulaScriptsService formulaScriptsService;
	
	@Autowired
	private TFormulaScriptsCommandsService formulaScriptsCommandsService;
	
	@ModelAttribute
	public TFormulaScripts get(@RequestParam(required=false) String id) {
		TFormulaScripts entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = formulaScriptsService.get(id);
		}
		if (entity == null){
			entity = new TFormulaScripts();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tFormulaScripts:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tFormulaScripts")TFormulaScripts tFormulaScripts, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TFormulaScripts> page = formulaScriptsService.findPage(new Page<TFormulaScripts>(request, response), tFormulaScripts); 
		model.addAttribute("page", page);
		return "modules/netconfig/tScriptsList";
	}
	
	@RequiresPermissions("netconfig:tFormulaScripts:view")
	@RequestMapping(value = "form")
	public String form(TFormulaScripts tFormulaScripts, Model model) {
		if(null != tFormulaScripts.getId() && !"".equals(tFormulaScripts.getId())) {
			TFormulaScriptsCommands command = new TFormulaScriptsCommands();
			command.setScriptsId(Integer.parseInt(tFormulaScripts.getId()));
			List<TFormulaScriptsCommands> commandList = formulaScriptsCommandsService.findList(command);
			tFormulaScripts.setCommandsList(commandList);
		}
		model.addAttribute("tFormulaScripts", tFormulaScripts);
		return "modules/netconfig/tScriptsForm";
	}
	
	@RequiresPermissions("netconfig:tFormulaScripts:edit")
	@RequestMapping(value = "save")
	public String save(TFormulaScripts tFormulaScripts, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tFormulaScripts)){
			return form(tFormulaScripts, model);
		}
		formulaScriptsService.save(tFormulaScripts);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tScripts/?repage";
	}
	
	@RequiresPermissions("netconfig:tFormulaScripts:edit")
	@RequestMapping(value = "delete")
	public String delete(TFormulaScripts tFormulaScripts, RedirectAttributes redirectAttributes) {
		formulaScriptsService.delete(tFormulaScripts);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tScripts/?repage";
	}
	
	/**
	 * 验证脚本名是否有效
	 * @param oldName
	 * @param fpoolname
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name) {
		if (name!=null && name.equals(oldName)) {
			return "true";
		} else if (name!=null && formulaScriptsService.getScriptsByName(name) == 0) {
			return "true";
		}
		return "false";
	}

}
