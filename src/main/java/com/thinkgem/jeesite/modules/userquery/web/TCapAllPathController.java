/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.web;

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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.userquery.entity.TCapAllPath;
import com.thinkgem.jeesite.modules.userquery.service.TCapAllPathService;

/**
 * 单用户信令追踪历史查询Controller
 * @author 王晶石
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/userquery/tCapAllPath")
public class TCapAllPathController extends BaseController {

	@Autowired
	private TCapAllPathService tCapAllPathService;
	
	@ModelAttribute
	public TCapAllPath get(@RequestParam(required=false) String id) {
		TCapAllPath entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCapAllPathService.get(id);
		}
		if (entity == null){
			entity = new TCapAllPath();
		}
		return entity;
	}
	
	@RequiresPermissions("userquery:tCapAllPath:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tCapAllPath")TCapAllPath tCapAllPath, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TCapAllPath> p = new Page<TCapAllPath>(request, response);
		p.setOrderBy("startTime desc");
		Page<TCapAllPath> page = tCapAllPathService.findPage(p, tCapAllPath); 
		model.addAttribute("page", page);
		return "modules/userquery/tCapAllPathList";
	}
	
	@RequestMapping(value = {"pcrfList"})
	public String showPcrfList(String id, Model model){
		List<TCapAllPath> list = tCapAllPathService.queryPcrfList(id);
		model.addAttribute("list",list);
		model.addAttribute("pid",id);
		return "modules/userquery/userSinglePcrf";
	}


}