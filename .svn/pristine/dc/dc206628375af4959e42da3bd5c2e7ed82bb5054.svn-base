package com.thinkgem.jeesite.modules.collectset.web;

import java.util.List;

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
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;
import com.thinkgem.jeesite.modules.collectset.entity.TElementcollect;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.collectset.service.TElementcollectService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TOss;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TOssService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;

/**
 * 采集配置Controller
 * @author yanghai
 * @version 2017-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/collectset/elementcollect/")
public class TElementcollectController extends BaseController {

	@Autowired
	private TElementcollectService tElementcollectService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@Autowired
	private TOssService tOssService;
	
	@ModelAttribute
	public TElementcollect get(@RequestParam(required=false) String id) {
		TElementcollect tElementcollect = null;
		if (StringUtils.isNotBlank(id)){
			tElementcollect = tElementcollectService.get(id);
		}
		if (tElementcollect == null){
			tElementcollect = new TElementcollect();
		}
		return tElementcollect;
	}
	
	@RequiresPermissions("collectset:elementcollect:configtjview")
	@RequestMapping(value = "configtj")
	public String list(@Valid @ModelAttribute("tCollectordeploy")TCollectordeploy tCollectordeploy,Model model,@RequestParam String type){
		List<TCollectordeploy> list=tCollectordeployService.findList(new TCollectordeploy());
        List<TElementcollect> netelementList=tElementcollectService.findTreeDataList(tPoolService.findList(new TPool()),tNewnetelementService.findList(new TNewnetelement()),type);
        //设置采集类型
        tCollectordeploy.setTemp_field2(type);
        //设置tab标签显示中文
        switch(type){
        	case "1":
        		model.addAttribute("tabName", "网元统计");//网元统计
        	break;
        	case "2":
        		model.addAttribute("tabName", "网元话单");//网元话单
        	break;
        	case "4":
        		model.addAttribute("tabName", "EbmLog解析");//EbmLog
        	break;
        	case "5":
        		model.addAttribute("tabName", "单用户话单");//单用户话单
        	break;
        	case "6":
        		model.addAttribute("tabName", "计费欺诈");//计费欺诈
        		break;
        	case "7":
        		model.addAttribute("tabName", "网元参数");//网元参数
        		break;
        	case "8":
        		model.addAttribute("tabName", "自动巡检");//自动巡检
        	break;  
        	case "9":
        		model.addAttribute("tabName", "EbmLog文件");//EbmLog文件下载
        	break;
        }
        if("7".equals(type)) {
        	String collectorIp = Global.getConfig("paramcollector");
        	String collectorId = tElementcollectService.getCollectorIdByIp(collectorIp);
        	model.addAttribute("collectorIp", collectorIp);
        	model.addAttribute("collectorId", collectorId);
        }
        if("8".equals(type)) {
        	String collectorIp = Global.getConfig("atuocheckcollector");
        	String collectorId = tElementcollectService.getCollectorIdByIp(collectorIp);
        	model.addAttribute("collectorIp", collectorIp);
        	model.addAttribute("collectorId", collectorId);
        }
        model.addAttribute("type", type);
        model.addAttribute("listTCollectordeploy", list);
		model.addAttribute("netelementList", netelementList);
		return "modules/collectset/configtjList";
	}
	
	@RequiresPermissions("collectset:elementcollect:configtjview")
	@RequestMapping(value = "configoss")
	public String getOssTreeData(@Valid @ModelAttribute("tCollectordeploy")TCollectordeploy tCollectordeploy,Model model,@RequestParam String type) {
		List<TCollectordeploy> list=tCollectordeployService.findList(new TCollectordeploy());
		List<TOss> ossList = tElementcollectService.findOssTreeDataList(tOssService.findList(new TOss()), type);
		tCollectordeploy.setTemp_field2(type);
		model.addAttribute("tabName", "oss告警");
		model.addAttribute("listTCollectordeploy", list);
		model.addAttribute("netelementList", ossList);
		return "modules/collectset/configossList";
	}
	
	@RequiresPermissions("collectset:elementcollect:configtjview")
	@RequestMapping(value = "saveOss")
	public String saveOss(TCollectordeploy tCollectordeploy, Model model, RedirectAttributes redirectAttributes) {
		tElementcollectService.save(tCollectordeploy);
		addMessage(redirectAttributes, "保存采集器配置成功");
		return "redirect:"+Global.getAdminPath()+"/collectset/elementcollect/configoss?type=3";
	}
	
	/**
	 * 读取详细数据
	 * @param TElementcollect
	 * @return
	 */
	@RequestMapping(value = "showTreeByCollector")
	@ResponseBody
	public String showTreeByCollector(TElementcollect telementcollect) {
		List<TElementcollect> netelementList=tElementcollectService.findList(telementcollect);
		return JsonMapper.getInstance().toJson(netelementList);
	}
	
	@RequiresPermissions("collectset:elementcollect:configtjview")
	@RequestMapping(value = "save")
	public String save(TCollectordeploy tCollectordeploy, Model model, RedirectAttributes redirectAttributes) {
		tElementcollectService.save(tCollectordeploy);
		addMessage(redirectAttributes, "保存采集器配置成功");
		return "redirect:"+Global.getAdminPath()+"/collectset/elementcollect/configtj?type="+tCollectordeploy.getTemp_field2();
	}
	
}