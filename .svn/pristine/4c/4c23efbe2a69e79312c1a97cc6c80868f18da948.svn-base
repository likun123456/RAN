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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;

/**
 * 池组生成Controller
 * @author wangjingshi
 * @version 2017-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tPool")
public class TPoolController extends BaseController {

	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService netElementService;
	
	@ModelAttribute
	public TPool get(@RequestParam(required=false) String id) {
		TPool entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tPoolService.get(id);
		}
		if (entity == null){
			entity = new TPool();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tPool:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tPool")TPool tPool, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TPool> page = tPoolService.findPage(new Page<TPool>(request, response), tPool); 
		model.addAttribute("page", page);
		return "modules/netconfig/tPoolList";
	}

	@RequiresPermissions("netconfig:tPool:view")
	@RequestMapping(value = "form")
	public String form(TPool tPool, Model model) {
		model.addAttribute("tPool", tPool);
		return "modules/netconfig/tPoolForm";
	}

	@RequiresPermissions("netconfig:tPool:edit")
	@RequestMapping(value = "save")
	public String save(TPool tPool, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tPool)){
			return form(tPool, model);
		}
		tPoolService.save(tPool);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tPool/?repage";
	}
	
	@RequiresPermissions("netconfig:tPool:edit")
	@RequestMapping(value = "delete")
	public String delete(TPool tPool, RedirectAttributes redirectAttributes) {
		
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setTemp_field2(tPool.getId());
		List<TNewnetelement> list = netElementService.findList(tnewnetelement);
		if(list != null && list.size()>0){
			addMessage(redirectAttributes, "池组：" + list.get(0).getPoolname() + "正在使用中，不允许删除！");
			return "redirect:"+Global.getAdminPath()+"/netconfig/tPool/?repage";
		}
		tPoolService.delete(tPool);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tPool/?repage";
	}
	
	/**
	 * 验证池组名是否有效
	 * @param oldName
	 * @param fpoolname
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String fpoolname) {
		if (fpoolname!=null && fpoolname.equals(oldName)) {
			return "true";
		} else if (fpoolname!=null && tPoolService.getPoolByName(fpoolname) == 0) {
			return "true";
		}
		return "false";
	}

}