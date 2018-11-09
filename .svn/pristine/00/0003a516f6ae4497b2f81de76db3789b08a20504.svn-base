/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.TCheatChinese;
import com.thinkgem.jeesite.modules.cheat.service.TCheatChineseService;

/**
 * 话单欺诈类型中文设置Controller
 * @author zhuguangrui
 * @version 2017-08-24
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/tCheatChinese")
public class TCheatChineseController extends BaseController {

	@Autowired
	private TCheatChineseService tCheatChineseService;
	
	@ModelAttribute
	public TCheatChinese get(@RequestParam(required=false) String id) {
		TCheatChinese entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCheatChineseService.get(id);
		}
		if (entity == null){
			entity = new TCheatChinese();
		}
		return entity;
	}
	
	@RequiresPermissions("cheat:tCheatChinese:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tCheatChinese")TCheatChinese tCheatChinese, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TCheatChinese> page = tCheatChineseService.findPage(new Page<TCheatChinese>(request, response), tCheatChinese);
		if(page == null){
			page = new Page<TCheatChinese>();
		}
		model.addAttribute("page", page);
		return "modules/cheat/tCheatChineseList";
	}

	@RequiresPermissions("cheat:tCheatChinese:view")
	@RequestMapping(value = "form")
	public String form(TCheatChinese tCheatChinese, Model model) {
		model.addAttribute("tCheatChinese", tCheatChinese);
		return "modules/cheat/tCheatChineseForm";
	}

	@RequiresPermissions("cheat:tCheatChinese:edit")
	@RequestMapping(value = "save")
	public String save(TCheatChinese tCheatChinese, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tCheatChinese)){
			return form(tCheatChinese, model);
		}
		tCheatChineseService.save(tCheatChinese);
		addMessage(redirectAttributes, "保存欺诈类型成功");
		return "redirect:"+Global.getAdminPath()+"/cheat/tCheatChinese/?repage";
	}
	
	@RequiresPermissions("cheat:tCheatChinese:edit")
	@RequestMapping(value = "delete")
	public String delete(TCheatChinese tCheatChinese, RedirectAttributes redirectAttributes) {
		tCheatChineseService.delete(tCheatChinese);
		addMessage(redirectAttributes, "删除欺诈类型成功");
		return "redirect:"+Global.getAdminPath()+"/cheat/tCheatChinese/?repage";
	}
	
	
	/**
	 * 导出数据
	 * @param tCheatChinese
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cheat:tCheatChinese:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(@Valid @ModelAttribute("tCheatChinese")TCheatChinese tCheatChinese, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "cheatType"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TCheatChinese> page = tCheatChineseService.findPage(new Page<TCheatChinese>(request, response), tCheatChinese);
    		
    		String headStr[] = {"欺诈类型","欺诈类型中文翻译","漏洞类型","欺诈原理","欺诈场景","欺诈场景分析","解决方案"};
 		    ExportExcel ee = new ExportExcel("话单欺诈类型", headStr);
 		    for (int i = 0; i < page.getList().size(); i++) {
 				Row row = ee.addRow();
 				TCheatChinese t =page.getList().get(i);
				ee.addCell(row, 0,t.getCheatCase());
				ee.addCell(row, 1,t.getCheatCaseChinese());
				ee.addCell(row, 2,t.getLoopholeType());
				ee.addCell(row, 3,t.getCheatPrinciple());
				ee.addCell(row, 4,t.getCheatScene());
				ee.addCell(row, 5,t.getCheatSceneAnalysis());
				ee.addCell(row, 6,t.getSolution());
 			}
 		   ee.write(response, fileName);
 		   ee.dispose();
    	   return null;
 		   
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出数据失败！失败信息："+e.getMessage());
		} 
		return "redirect:"+Global.getAdminPath()+"/cheat/tCheatChinese/?repage";
    }

}