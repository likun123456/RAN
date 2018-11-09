package com.thinkgem.jeesite.modules.collectset.web;

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
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;

/**
 * 采集器配置Controller
 * @author yanghai
 * @version 2017-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/collectset/tCollectordeploy")
public class TCollectordeployController extends BaseController {

	@Autowired
	private TCollectordeployService tCollectordeployService;
	
	@ModelAttribute
	public TCollectordeploy get(@RequestParam(required=false) String id) {
		TCollectordeploy tCollectordeploy = null;
		if (StringUtils.isNotBlank(id)){
			tCollectordeploy = tCollectordeployService.get(id);
		}
		if (tCollectordeploy == null){
			tCollectordeploy = new TCollectordeploy();
		}
		return tCollectordeploy;
	}
	
	@RequiresPermissions("collectset:tCollectordeploy:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tCollectordeploy")TCollectordeploy tCollectordeploy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TCollectordeploy> page = tCollectordeployService.findPage(new Page<TCollectordeploy>(request, response), tCollectordeploy); 
		model.addAttribute("page", page);
		return "modules/collectset/tCollectordeployList";
	}

	@RequiresPermissions("collectset:tCollectordeploy:view")
	@RequestMapping(value = "form")
	public String form(TCollectordeploy tCollectordeploy, Model model) {
		model.addAttribute("tCollectordeploy", tCollectordeploy);
		return "modules/collectset/tCollectordeployForm";
	}

	@RequiresPermissions("collectset:tCollectordeploy:edit")
	@RequestMapping(value = "save")
	public String save(TCollectordeploy tCollectordeploy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tCollectordeploy)){
			return form(tCollectordeploy, model);
		}
		tCollectordeployService.save(tCollectordeploy);
		addMessage(redirectAttributes, "保存采集器配置成功");
		return "redirect:"+Global.getAdminPath()+"/collectset/tCollectordeploy/?repage";
	}
	
	@RequiresPermissions("collectset:tCollectordeploy:edit")
	@RequestMapping(value = "delete")
	public String delete(TCollectordeploy tCollectordeploy, RedirectAttributes redirectAttributes) {
		tCollectordeployService.delete(tCollectordeploy);
		addMessage(redirectAttributes, "删除采集器配置成功");
		return "redirect:"+Global.getAdminPath()+"/collectset/tCollectordeploy/?repage";
	}

}