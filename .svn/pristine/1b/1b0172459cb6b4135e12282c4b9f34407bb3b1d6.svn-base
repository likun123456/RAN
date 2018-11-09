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
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.collectset.entity.TElementcollect;
import com.thinkgem.jeesite.modules.collectset.service.TElementcollectService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TOss;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TOssService;

/**
 * OSS节点基本信息操作Controller
 * @author 王晶石
 * @version 2017-05-27
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tOss")
public class TOssController extends BaseController {

	@Autowired
	private TOssService tOssService;
	@Autowired
	private TNewnetelementService netElementService;
	@Autowired
	private TElementcollectService elementcollectService;
	
	@ModelAttribute
	public TOss get(@RequestParam(required=false) String id) {
		TOss entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tOssService.get(id);
		}
		if (entity == null){
			entity = new TOss();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tOss:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tOss")TOss tOss, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TOss> page = tOssService.findPage(new Page<TOss>(request, response), tOss); 
		model.addAttribute("page", page);
		return "modules/netconfig/tOssList";
	}

	@RequiresPermissions("netconfig:tOss:view")
	@RequestMapping(value = "form")
	public String form(TOss tOss, Model model) {
		model.addAttribute("tOss", tOss);
		return "modules/netconfig/tOssForm";
	}

	@RequiresPermissions("netconfig:tOss:edit")
	@RequestMapping(value = "save")
	public String save(TOss tOss, Model model, RedirectAttributes redirectAttributes) {
		tOss.setFpassword(Encodes.unescapeHtml(tOss.getFpassword()));
		if (!beanValidator(model, tOss)){
			return form(tOss, model);
		}
		tOssService.save(tOss);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tOss/?repage";
	}
	
	@RequiresPermissions("netconfig:tOss:edit")
	@RequestMapping(value = "delete")
	public String delete(TOss tOss, RedirectAttributes redirectAttributes) {
		
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setOss(tOss.getId());
		List<TNewnetelement> list = netElementService.findList(tnewnetelement);
		if(list != null && list.size()>0){
			addMessage(redirectAttributes, "OSS节点：" + tOss.getFname() + "正在使用中，不允许删除！");
			return "redirect:"+Global.getAdminPath()+"/netconfig/tOss/?repage";
		}
		TElementcollect elementcollect = new TElementcollect();
		elementcollect.setType(3);
		elementcollect.setId(tOss.getId());
		List<TElementcollect> elements = elementcollectService.findList(elementcollect);
		if(elements != null && elements.size()>0){
			addMessage(redirectAttributes, "OSS节点：" + tOss.getFname() + "正在使用中，不允许删除！");
			return "redirect:"+Global.getAdminPath()+"/netconfig/tOss/?repage";
		}
		
		tOssService.delete(tOss);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tOss/?repage";
	}
	
	/**
	 * 验证OSS名是否有效
	 * @param oldName
	 * @param fpoolname
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String fname) {
		if (fname!=null && fname.equals(oldName)) {
			return "true";
		} else if (fname!=null && tOssService.getOssByName(fname) == 0) {
			return "true";
		}
		return "false";
	}

}