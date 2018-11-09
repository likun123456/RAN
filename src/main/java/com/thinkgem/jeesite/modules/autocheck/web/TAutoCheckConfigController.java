/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.web;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JavaType;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.constant.DictConstant;
import com.thinkgem.jeesite.common.entity.Client;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.socket.SocketObj;
import com.thinkgem.jeesite.common.socket.subentity.ParamCollectConfigSocket;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckConfig;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoTemplateCheckConfig;
import com.thinkgem.jeesite.modules.autocheck.service.AutoTemplateCheckConfigService;
import com.thinkgem.jeesite.modules.autocheck.service.TAutoCheckConfigService;
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;
import com.thinkgem.jeesite.modules.collectset.entity.TElementcollect;
import com.thinkgem.jeesite.modules.collectset.service.TElementcollectService;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamCollectConfig;
import com.thinkgem.jeesite.modules.paramconfig.service.ParamCollectConfigService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;


/**
 * 自动巡检配置Controller
 * @author zhuguangrui
 * @version 2017-08-05
 */
@Controller
@RequestMapping(value = "${adminPath}/autocheck/tAutoCheckConfig")
public class TAutoCheckConfigController extends BaseController {

	@Autowired
	private TAutoCheckConfigService tAutoCheckConfigService;
	
	@Autowired
	private ParamCollectConfigService paramCollectConfigService;
	
	@Autowired
	private TElementcollectService elementcollectService;
	
	@Autowired
	private TVisExcelService tVisExcelService;
	
	@Autowired
	private TVisExcelModuleService tVisExcelModuleService;
	
	@Autowired
	private AutoTemplateCheckConfigService autoTemplateCheckConfigService;
	
	@ModelAttribute
	public TAutoCheckConfig get(@RequestParam(required=false) String id) {
		TAutoCheckConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tAutoCheckConfigService.get(id);
		}
		if (entity == null){
			entity = new TAutoCheckConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("autocheck:tAutoCheckConfig:view")
	@RequestMapping(value = {"autoCheckTemplateConfig", ""})
	public String autoCheckTemplateConfig(@ModelAttribute("tAutoTemplateCheckConfig") TAutoTemplateCheckConfig tAutoTemplateCheckConfig,Model model) {
		TVisExcel visexcel = new TVisExcel();
		visexcel.setType("TASK");
		List<TVisExcel> visExcelList = tVisExcelService.findList(visexcel);
		for(int i=0;i<visExcelList.size();i++){
			visExcelList.get(i).setName(visExcelList.get(i).getName()+"（"+visExcelList.get(i).getType()+"）");
		}
		List<TAutoTemplateCheckConfig> templateList = autoTemplateCheckConfigService.findList(new TAutoTemplateCheckConfig());
		for(TAutoTemplateCheckConfig template : templateList) {
			template.setIds(template.getExcelId()+"_"+template.getModuleId()+"_"+template.getNetType());
			TVisExcel excel = tVisExcelService.get(template.getExcelId()+"");
			TVisExcelModule module = tVisExcelModuleService.get(template.getModuleId()+"");
			String result = "";
			result = DictUtils.getDictLabel(template.getNetType()+"", "biz_net_type", result);
			template.setNames(excel.getName() + "（"+excel.getType()+"）" + "--" + module.getModuleName() + "--" + result);
		}
		model.addAttribute("visExcelList", visExcelList);
		model.addAttribute("templateList", templateList);
		return "modules/autocheck/tAutoCheckTemplateConfigList";
	}
	
	@RequestMapping(value = "saveTemplate")
	@ResponseBody
	public String saveTemplate(String dataArray) {
		//jackson 将字符串类型的json 转换成List类型\
		String jsonStr = StringEscapeUtils.unescapeHtml4(dataArray);
		String collecttime = "";
		try {
			//
			JavaType javaType = JsonMapper.getInstance().getTypeFactory().constructParametricType(ArrayList.class, TAutoTemplateCheckConfig.class);
			List<TAutoTemplateCheckConfig> tempList =  JsonMapper.getInstance().readValue(jsonStr, javaType);
			autoTemplateCheckConfigService.deleteAll();
			for(TAutoTemplateCheckConfig node : tempList) {
				TAutoTemplateCheckConfig tempObj = node;
				String is = tempObj.getIds();
				String ns = tempObj.getNames();
				node.setNetType(Integer.parseInt(is.split("_")[2]));
				node.setExcelId(Integer.parseInt(is.split("_")[0]));
				node.setModuleId(Integer.parseInt(is.split("_")[1]));
				node.setItemName(ns.split("--")[1]);
				node.setCollecttime(tempObj.getCollecttime());
				collecttime = tempObj.getCollecttime();
				autoTemplateCheckConfigService.save(node);
			}
			
			sendSocket(collecttime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 读取详细数据
	 * 
	 * @param TElementcollect
	 * @return
	 */
	@RequestMapping(value = "queryModuleList")
	@ResponseBody
	public String queryModuleList(int excelId) {
		TVisExcelModule tVisExcelModule = new TVisExcelModule();
		tVisExcelModule.setExcelId(excelId);
		List<TVisExcelModule> tVisExcelModuleList = tVisExcelModuleService.findList(tVisExcelModule);
		return JsonMapper.getInstance().toJson(tVisExcelModuleList);
	}
	
	@RequiresPermissions("autocheck:tAutoCheckConfig:view")
	@RequestMapping(value = {"config", ""})
	public String config(ParamCollectConfig paramCollectConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TAutoCheckConfig> list = tAutoCheckConfigService.findList(new TAutoCheckConfig());
		Map<String, List<TAutoCheckConfig>> map = new LinkedHashMap<String,List<TAutoCheckConfig>>();
		for(TAutoCheckConfig config : list) {
			if("1".equals(config.getNetType())) {
				List<TAutoCheckConfig> mmelist = map.get("mme");
				if(mmelist == null) {
					ArrayList<TAutoCheckConfig> l =  new ArrayList<TAutoCheckConfig>();
					l.add(config);
					map.put("mme", l);
				} else {
					mmelist.add(config);
				}
			} 
			else if("2".equals(config.getNetType())) {
				List<TAutoCheckConfig> saegwlist = map.get("saegw");
				if(saegwlist == null) {
					ArrayList<TAutoCheckConfig> l =  new ArrayList<TAutoCheckConfig>();
					l.add(config);
					map.put("saegw", l);
				} else {
					saegwlist.add(config);
				}
			} 
			else if("3".equals(config.getNetType())) {
				List<TAutoCheckConfig> pcrflist = map.get("pcrf");
				if(pcrflist == null) {
					ArrayList<TAutoCheckConfig> l =  new ArrayList<TAutoCheckConfig>();
					l.add(config);
					map.put("pcrf", l);
				} else {
					pcrflist.add(config);
				}
			} 
		}
		ParamCollectConfig config = paramCollectConfigService.queryAutoCheckConfig();
		model.addAttribute("paramCollectConfig", config);
		model.addAttribute("data", map);
		return "modules/autocheck/tAutoCheckConfigList";
	}
	
	public String sendSocket(String collecttime) {
		String resultStr = "success";
		
		ParamCollectConfig oldConfig = paramCollectConfigService.queryAutoCheckConfig();
		ParamCollectConfig config = new ParamCollectConfig();
		config.setCollecttime(collecttime);
		config.setBiztype(3);
		paramCollectConfigService.updateAutoCheckConfig(config);
		
		if(oldConfig.getCollecttime()!= null && !oldConfig.getCollecttime().equals(collecttime)){
			SocketObj socket = new SocketObj();
			ParamCollectConfigSocket ps = new ParamCollectConfigSocket();
			ps.setCollecttime(collecttime);
			socket.setFlag("updateAutoCheckCollector");
			socket.setParamConfig(ps);
			// 循环读取数据库表，启动采集器采集数据
			TElementcollect elementcollect = new TElementcollect();
			elementcollect.setType(DictConstant.COLLECT_TYPE_AUTOCHECK);
			List<TElementcollect> elementcollects = elementcollectService.findCollectorIp(elementcollect);
			for(TElementcollect item : elementcollects){
				try {
					TCollectordeploy collector = new TCollectordeploy();
					collector.setIp(item.getIp());
					Client socketClient = new Client(socket, collector);
					String result = socketClient.send(7781);
				
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					resultStr = "发送socket请求异常:"+e.getMessage();
				}
			}
		}
		return resultStr;
	}

	@RequiresPermissions("autocheck:tAutoCheckConfig:edit")
	@RequestMapping(value = "save")
	public String save(ParamCollectConfig paramCollectConfig, Model model, RedirectAttributes redirectAttributes) {
		
		ParamCollectConfig oldConfig = paramCollectConfigService.queryAutoCheckConfig();
		ParamCollectConfig config = new ParamCollectConfig();
		config.setCollecttime(paramCollectConfig.getCollecttime());
		config.setBiztype(3);
		paramCollectConfigService.updateAutoCheckConfig(config);
		
		List<TAutoCheckConfig> list = paramCollectConfig.getAutoCheckConfig();
		for(TAutoCheckConfig checkConfig : list) {
			tAutoCheckConfigService.updateConfig(checkConfig);
		}
		if(oldConfig.getCollecttime()!= null && !oldConfig.getCollecttime().equals(paramCollectConfig.getCollecttime())){
			SocketObj socket = new SocketObj();
			ParamCollectConfigSocket ps = new ParamCollectConfigSocket();
			ps.setCollecttime(paramCollectConfig.getCollecttime());
			socket.setFlag("updateAutoCheckCollector");
			socket.setParamConfig(ps);
			// 循环读取数据库表，启动采集器采集数据
			TElementcollect elementcollect = new TElementcollect();
			elementcollect.setType(DictConstant.COLLECT_TYPE_AUTOCHECK);
			List<TElementcollect> elementcollects = elementcollectService.findCollectorIp(elementcollect);
			for(TElementcollect item : elementcollects){
				try {
					TCollectordeploy collector = new TCollectordeploy();
					collector.setIp(item.getIp());
					Client socketClient = new Client(socket, collector);
					String result = socketClient.send(7782);
				
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					addMessage(redirectAttributes, "发送socket请求异常:"+e.getMessage());
				}
			}
		}
		addMessage(redirectAttributes, "保存自动巡检配置成功");
		return "redirect:"+Global.getAdminPath()+"/autocheck/tAutoCheckConfig/config/?repage";
	}
	
	
}