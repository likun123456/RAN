package com.thinkgem.jeesite.modules.paramconfig.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;
import com.thinkgem.jeesite.modules.paramconfig.service.ParamExportService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 网元参数定时导出Controller
 * @author yanghai
 * @version 2017-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/paramconfig/paramExport")
public class ParamExportController extends BaseController  {
	
	@Autowired
	private TNewnetelementService tnewnetelementService;
	@Autowired
	private ParamExportService paramExportService;
	
	@ModelAttribute
	public ParamData get(@RequestParam(required = false) String id) {
		ParamData entity =new ParamData();
		return entity;
	}
	@RequiresPermissions("paramconfig:paramExport:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("paramData")ParamData paramData, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/paramconfig/paramExportList";
	}
	
	@RequiresPermissions("paramconfig:paramExport:view")
	@RequestMapping(value = {"save"})
	public String save(@Valid @ModelAttribute("paramData")ParamData paramData, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		paramExportService.updateParamData(paramData);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/paramconfig/paramExport/?repage";
	}
	
	/**
	 * 根据选择的类型，查询对应的网元
	 * 
	 * @param extId
	 * @param type
	 * @param grade
	 * @param isAll
	 * @param parameterEpc
	 * @param response
	 * @return
	 */
	@RequiresPermissions("paramconfig:paramExport:view")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, @RequestParam(required = false) String parameterEpc,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if (null!=parameterEpc&&!parameterEpc.equals("")) {
			TNewnetelement tnewnetelement = new TNewnetelement();
			tnewnetelement.setType(parameterEpc);
			List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
			for (int i = 0; i < list.size(); i++) {
				TNewnetelement e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				if (i == 0) {
					Map<String, Object> mapParent = Maps.newHashMap();
					mapParent.put("id", "0");
					mapParent.put("pId", "0");
					mapParent.put("pIds", "0");
					mapParent.put("name", "网元");
					mapList.add(mapParent);
				}
				map.put("id", e.getId());
				map.put("pId", "0");
				map.put("pIds", "0,1");
				map.put("name", e.getFname());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 根据选择的类型，查询对应的网元
	 * 
	 * @param netType
	 * @param response
	 * @return
	 */
	@RequiresPermissions("paramconfig:paramExport:view")
	@ResponseBody
	@RequestMapping(value = "findSelectNetElement")
	public List<TNewnetelement> findSelectNetElement(@RequestParam(required = false) String netType,
			HttpServletResponse response) {
		List<TNewnetelement> list=null;
		if (null!=netType&&!netType.equals("")) {
			TNewnetelement tnewnetelement = new TNewnetelement();
			tnewnetelement.setType(netType);
			tnewnetelement.setParamexported("1");
			list = tnewnetelementService.findList(tnewnetelement);
		}
		return list;
	}
	/**
	 * 根据选择的类型，查询对应的参数标识
	 * 
	 * @param netType
	 * @param response
	 * @return
	 */
	@RequiresPermissions("paramconfig:paramExport:view")
	@ResponseBody
	@RequestMapping(value = "queryRemarkTree")
	public String queryRemarkTree(@RequestParam(required = false) String netType,
			HttpServletResponse response, Model model){
		String result="";
		if (null!=netType&&!netType.equals("")) {
			result=DictUtils.getDictLabel(netType,"biz_net_type",result);
			result ="SAEGW".equals(result)?"epg":result;
		}
		List<ParamData> paramDataList=paramExportService.queryParamConfig(result);
		List<TempJsonClass> tempJsonClassList=new ArrayList<TempJsonClass>();
		TempJsonClass tempJsonClass;
		for(int i=0;i<paramDataList.size();i++){
			tempJsonClass=new TempJsonClass();
			tempJsonClass.setId(paramDataList.get(i).getId());
			tempJsonClass.setpId(paramDataList.get(i).getPid()+"");
			tempJsonClass.setName(paramDataList.get(i).getName());
			tempJsonClass.setChecked(paramDataList.get(i).getTemp_field1());
			tempJsonClassList.add(tempJsonClass);
		}
		return JsonMapper.getInstance().toJson(tempJsonClassList);
	}
	
	class TempJsonClass{
		private String id;
		private String pId;
		private String name;
		private String checked;
		public String getChecked() {
			return checked;
		}
		public void setChecked(String checked) {
			this.checked = checked;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getpId() {
			return pId;
		}
		public void setpId(String pId) {
			this.pId = pId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
