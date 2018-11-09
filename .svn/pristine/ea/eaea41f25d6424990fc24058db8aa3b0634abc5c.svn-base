package com.thinkgem.jeesite.modules.paramconfig.web;

import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;
import com.thinkgem.jeesite.modules.paramconfig.service.NetParamCompareService;

/**
 * 
 *	多网元参数一致性对比
 * @author chenhongbo
 * @version 2017-06-17
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/paramconfig/netParamCompare")
public class NetParamCompareController extends BaseController {
	
	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@Autowired
	private NetParamCompareService netParamCompareService;
	
	@RequiresPermissions("paramconfig:netParamCompare:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("paramData")ParamData paramData, HttpServletRequest request, HttpServletResponse response,Model model) {
		return "modules/paramconfig/netParamCompare";
	}
	
	@RequestMapping(value = "getNewNetelementsByType")
	@ResponseBody
	public String getNewNetelementsByType(String netType) {
		List<TNewnetelement> list = tNewnetelementService.getNewNetelementsByType(netType);
		return JsonMapper.getInstance().toJson(list);
	}
	
	@RequestMapping(value = "getParamTypeByNetId")
	@ResponseBody
	public String getParamTypeByNetId(String netid,String date,String nettype) {
		
		String nid ="";
		date = "2018-04-26";
		if(netid.contains(",")){
			nid = netid.split(",")[0];
		}else{
			nid =netid;
		}
		String netType="mme";
		if("2".equals(nettype)){
			netType="epg";
		}else if("3".equals(nettype)){
			netType="pcrf";
		}
		String version = tNewnetelementService.getVersionByNetIdAndDate(nid, date.replaceAll("-", ""));
		List<String> paramTypes = tNewnetelementService.getParamTypeByNet(netType, version);
		return JsonMapper.getInstance().toJson(paramTypes);
	}
	
	@RequestMapping(value = "queryList")
	@ResponseBody
	public String queryList(String netType, String netid, String date,String paramtype,String keyword,String removeFlag, int page, int rows) {
		date = "2018-04-26";
		int first = rows * (page - 1);
		ServerPagination<LinkedHashMap<String, Object>> serverPage = new ServerPagination<LinkedHashMap<String, Object>>();
		List<LinkedHashMap<String, Object>> list = netParamCompareService.searchMultiNetBySql(netType, netid, date, paramtype, keyword, removeFlag, first, rows);
		int count = netParamCompareService.searchMultiNetBySqlCount(netType, netid, date, paramtype, keyword, removeFlag, first, rows);
		serverPage.setTotal(count);
		serverPage.setRows(list);
		return JsonMapper.getInstance().toJson(serverPage);
	}
	
	@RequestMapping(value = "getDynamicColumns")
	@ResponseBody
	public String getDynamicColumns(String netType, String netid) {
		List<String> list = netParamCompareService.getDynamicColumns(netType, netid);
		return JsonMapper.getInstance().toJson(list);
	}
}
