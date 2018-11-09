package com.thinkgem.jeesite.modules.cheat.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.CaptureParamConfig;
import com.thinkgem.jeesite.modules.cheat.service.CaptureParamConfigService;

/**
 * 计费欺诈抓包参数设置Controller
 * @author zhuguangrui
 * @version 2017-08-21
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/captureParam")
public class CaptureParamConfigController extends BaseController {
	
	@Autowired
	private CaptureParamConfigService captureParamConfigService;

	@RequiresPermissions("cheat:captureParam:view")
	@RequestMapping(value = {"config", ""})
	public String config(Model model) {
		
		CaptureParamConfig captureParamConfig = null;
		List<CaptureParamConfig> list = captureParamConfigService.queryConfig();
		if(list != null && list.size() > 0){
			captureParamConfig = list.get(0);
		}else{
			captureParamConfig = new CaptureParamConfig();
		}
		model.addAttribute("captureParamConfig", captureParamConfig);
		return "modules/cheat/captureParamConfig";
	}
	
	@RequiresPermissions("cheat:captureParam:edit")
	@RequestMapping(value = "update")
	public String update(CaptureParamConfig captureParamConfig, Model model, RedirectAttributes redirectAttributes) {
		
		captureParamConfigService.save(captureParamConfig);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/cheat/captureParam/config/?repage";
	}
	
}
