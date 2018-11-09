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
import com.thinkgem.jeesite.modules.netconfig.entity.TComputerroom;
import com.thinkgem.jeesite.modules.netconfig.entity.TEpa;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TOss;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TComputerroomService;
import com.thinkgem.jeesite.modules.netconfig.service.TEpaService;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TOssService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;

/**
 * 网元基本信息操作Controller
 * @author 王晶石
 * @version 2017-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tNewnetelement")
public class TNewnetelementController extends BaseController {

	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TOssService tOssService;
	@Autowired
	private TEpaService tEpaService;
	@Autowired
	private TComputerroomService tComputerroomService;
	
	@ModelAttribute
	public TNewnetelement get(@RequestParam(required=false) String id) {
		TNewnetelement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tNewnetelementService.get(id);
		}
		if (entity == null){
			entity = new TNewnetelement();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tNewnetelement:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tNewnetelement")TNewnetelement tNewnetelement,@RequestParam String poolType, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("poolType", poolType);
		List<TPool> poolList = tPoolService.queryPoolListByType(poolType);
		model.addAttribute("poolList",poolList);
		tNewnetelement.setTemp_field1(poolType);//临时变量存放池组类型
		Page<TNewnetelement> page = tNewnetelementService.findPage(new Page<TNewnetelement>(request, response), tNewnetelement); 
		model.addAttribute("page", page);
		return "modules/netconfig/tNewnetelementList";
	}

	@RequiresPermissions("netconfig:tNewnetelement:view")
	@RequestMapping(value = "form")
	public String form(TNewnetelement tNewnetelement, Model model,@RequestParam String poolType) {
		model.addAttribute("tNewnetelement", tNewnetelement);
		List<TPool> poolList = tPoolService.queryPoolListByType(poolType);
		model.addAttribute("poolList",poolList);
		List<TOss> ossList = tOssService.findList(null);
		model.addAttribute("ossList",ossList);
		List<TEpa> epaList = tEpaService.findList(null);
		model.addAttribute("epaList",epaList);
		List<TComputerroom> crList = tComputerroomService.findList(null);
		model.addAttribute("crList",crList);
		switch(poolType){
	    case "MME":poolType = "Mme";
	    break;
	    case "SAEGW":poolType = "Saegw";
	    break;
	    case "PCRF":poolType = "Pcrf";
	    break;
	}
		return "modules/netconfig/tNewnetelementForm"+poolType;
	}

	@RequiresPermissions("netconfig:tNewnetelement:edit")
	@RequestMapping(value = "save")
	public String save(TNewnetelement tNewnetelement, Model model, RedirectAttributes redirectAttributes,@RequestParam String poolType) {
		tNewnetelement.setPassword1(Encodes.unescapeHtml(tNewnetelement.getPassword1()));
		tNewnetelement.setPassword2(Encodes.unescapeHtml(tNewnetelement.getPassword2()));
		if (!beanValidator(model, tNewnetelement)){
			return form(tNewnetelement, model, poolType);
		}
		tNewnetelementService.save(tNewnetelement);
		addMessage(redirectAttributes, "保存网元信息维护成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tNewnetelement/?poolType="+poolType+"&repage";
	}
	
	@RequiresPermissions("netconfig:tNewnetelement:edit")
	@RequestMapping(value = "delete")
	public String delete(TNewnetelement tNewnetelement, RedirectAttributes redirectAttributes,@RequestParam String poolType) {
		tNewnetelementService.delete(tNewnetelement);
		addMessage(redirectAttributes, "删除网元信息维护成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tNewnetelement/?poolType="+poolType+"&repage";
	}

	/**
	 * 验证网元名是否有效
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
		} else if (fname!=null && tNewnetelementService.getNetByName(fname) == 0) {
			return "true";
		}
		return "false";
	}

}