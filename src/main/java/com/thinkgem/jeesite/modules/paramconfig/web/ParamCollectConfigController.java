package com.thinkgem.jeesite.modules.paramconfig.web;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.constant.DictConstant;
import com.thinkgem.jeesite.common.entity.Client;
import com.thinkgem.jeesite.common.socket.SocketObj;
import com.thinkgem.jeesite.common.socket.subentity.ParamCollectConfigSocket;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;
import com.thinkgem.jeesite.modules.collectset.entity.TElementcollect;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.collectset.service.TElementcollectService;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamCollectConfig;
import com.thinkgem.jeesite.modules.paramconfig.service.ParamCollectConfigService;

/**
 * 网元参数提取设置Controller
 * @author chenhongbo
 * @version 2017-06-15
 */
@Controller
@RequestMapping(value = "${adminPath}/paramconfig/paramCollectConfig")
public class ParamCollectConfigController extends BaseController {
	
	@Autowired
	private ParamCollectConfigService paramCollectConfigService;
	@Autowired
	private TElementcollectService elementcollectService;

	@RequiresPermissions("paramconfig:paramCollectConfig:view")
	@RequestMapping(value = {"list", ""})
	public String show(Model model) {
		ParamCollectConfig config = paramCollectConfigService.queryParamConfig();
		model.addAttribute("paramCollectConfig", config);
		return "modules/paramconfig/paramCollectConfig";
	}
	
	@RequiresPermissions("paramconfig:paramCollectConfig:edit")
	@RequestMapping(value = "update")
	public String update(ParamCollectConfig paramCollectConfig, Model model, RedirectAttributes redirectAttributes) {
		paramCollectConfigService.save(paramCollectConfig);
		addMessage(redirectAttributes, "保存成功");
		
		ParamCollectConfig oldConfig = paramCollectConfigService.queryParamConfig();
		if(oldConfig.getCollecttime()!= null && (!oldConfig.getCollecttime().equals(paramCollectConfig.getCollecttime())
				||oldConfig.getType() != paramCollectConfig.getType())){
			SocketObj socket = new SocketObj();
			ParamCollectConfigSocket ps = new ParamCollectConfigSocket();
			ps.setType(paramCollectConfig.getType());
			ps.setBiztype(paramCollectConfig.getBiztype());
			ps.setCollecttime(paramCollectConfig.getCollecttime());
			socket.setFlag("startNetParamCollector");
			socket.setParamConfig(ps);
			// 循环读取数据库表，启动采集器采集数据
			TElementcollect elementcollect = new TElementcollect();
			elementcollect.setType(DictConstant.COLLECT_TYPE_PARAM);
			List<TElementcollect> elementcollects = elementcollectService.findCollectorIp(elementcollect);
			for(TElementcollect item : elementcollects){
				try {
					TCollectordeploy collector = new TCollectordeploy();
					collector.setIp(item.getIp());
					Client socketClient = new Client(socket, collector);
					String result = socketClient.send(7780);
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
		return "redirect:"+Global.getAdminPath()+"/paramconfig/paramCollectConfig/?repage";
	}
	
}
