/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.paramconfig.web;

import java.util.ArrayList;
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
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.paramconfig.entity.TParamCmdStatus;
import com.thinkgem.jeesite.modules.paramconfig.service.TParamCmdStatusService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.service.TTacSuccessRateService;

/**
 * 网元参数修改任务状态Controller
 * @author 王晶石
 * @version 2017-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/paramconfig/tParamCmdStatus")
public class TParamCmdStatusController extends BaseController {

	@Autowired
	private TParamCmdStatusService tParamCmdStatusService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TTacSuccessRateService tTacSuccessRateService;
	
	@Autowired
	private TPoolService tPoolService;
	
	@ModelAttribute
	public TParamCmdStatus get(@RequestParam(required=false) String id) {
		TParamCmdStatus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tParamCmdStatusService.get(id);
		}
		if (entity == null){
			entity = new TParamCmdStatus();
		}
		return entity;
	}
	
	@RequiresPermissions("paramconfig:tParamCmdStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tParamCmdStatus")TParamCmdStatus tParamCmdStatus, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TNewnetelement>  netListAll = tNewnetelementService.findList(new TNewnetelement());
		List<TPool> poolList = tPoolService.queryPoolListByType(null);
		List<PoolNetVO>  poolNetList = new ArrayList<PoolNetVO>();
		for(TPool pool : poolList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("MME");
		model.addAttribute("ebmEventlist", ebmEventlist);
		model.addAttribute("poolNetList", poolNetList);
		Page<TParamCmdStatus> page = tParamCmdStatusService.findPage(new Page<TParamCmdStatus>(request, response), tParamCmdStatus); 
		model.addAttribute("page", page);
		return "modules/paramconfig/tParamCmdStatusList";
	}


}