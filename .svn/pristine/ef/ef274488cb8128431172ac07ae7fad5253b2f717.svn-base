package com.thinkgem.jeesite.modules.netconfig.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.ExcelTemplate;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetail;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleDetailService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;
import com.thinkgem.jeesite.modules.sys.entity.Menu;

@Controller
@RequestMapping(value = "${adminPath}/netconfig/visExcelTemplate")
public class VisExcelTemplateController extends BaseController {

	@Autowired
	private TVisExcelModuleDetailService tVisExcelModuleDetailService;
	@Autowired
	private TVisExcelService tVisExcelService;
	@Autowired
	private TVisExcelModuleService tVisExcelModuleService;

	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		// List<TVisExcel> list = Lists.newArrayList();
		List<TVisExcel> visExcelList = tVisExcelService.findList(new TVisExcel());
		List<TVisExcelModule> tVisExcelModuleList = tVisExcelModuleService.findList(new TVisExcelModule());
		List<TVisExcelModuleDetail> TVisExcelModuleDetailList = tVisExcelModuleDetailService
				.findList(new TVisExcelModuleDetail());
		List<ExcelTemplate> list = ExcelTemplate.sortList(visExcelList, tVisExcelModuleList, TVisExcelModuleDetailList);
		model.addAttribute("list", list);

		return "modules/netconfig/tExcelTemplateList";
	}

	@RequestMapping(value = "form")
	public String form(String id, Model model) {
		// 添加
		if (id == null) {
			model.addAttribute("tVisExcel", new TVisExcel());
			return "modules/netconfig/tVisExcelTemplateForm";
		} else {
			String s[] = id.split("_");
			int len = s.length;
			switch (len) {
			case 1:
				model.addAttribute("id", id);
				model.addAttribute("tVisExcel", tVisExcelService.get(id));
				return "modules/netconfig/tVisExcelTemplateForm";
			case 2:
				List<TVisExcel> visExcelList = tVisExcelService.findList(new TVisExcel());
				model.addAttribute("visExcelList", visExcelList);
				model.addAttribute("id", s[1]);
				model.addAttribute("tVisExcelModule", tVisExcelModuleService.get(s[1]));
				return "modules/netconfig/tVisExcelModuleForm";

			case 3:
				TVisExcelModuleDetail temd=tVisExcelModuleDetailService.get(s[2]);
				model.addAttribute("id", s[2]);
				model.addAttribute("tVisExcelModuleDetail", temd);
				List<TVisExcel> visExcelList1 = tVisExcelService.findList(new TVisExcel());
				model.addAttribute("visExcelList", visExcelList1);
				TVisExcelModule temp=new TVisExcelModule();
				temp.setExcelId(temd.getExcelId());
				List<TVisExcelModule> tVisExcelModuleList = tVisExcelModuleService.findList(temp);
				model.addAttribute("tVisExcelModuleList", tVisExcelModuleList);
				return "modules/netconfig/tVisExcelModuleDetailFormAdd";
			}
			return "";	
		}	
	}
	
	/**
	 * 添加下级
	 * @param id
	 * @param parentId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "childform")
	public String childform( String parentId, Model model) {
			String s[] = parentId.split("_");
			int len = s.length;
			switch (len) {
			case 1:
				List<TVisExcel> visExcelList = tVisExcelService.findList(new TVisExcel());
				model.addAttribute("visExcelList", visExcelList);
				model.addAttribute("id", s[0]);
				model.addAttribute("tVisExcelModule", new TVisExcelModule());
				return "modules/netconfig/tVisExcelModuleForm";
			case 2:
				model.addAttribute("id", s[1]);
				model.addAttribute("tVisExcelModuleDetail", new TVisExcelModuleDetail());
				return "modules/netconfig/tVisExcelModuleDetailFormAdd";
			}
			return "";
	}

	/**
	 * 批量修改菜单排序
	 */
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
		for (int i = 0; i < ids.length; i++) {
			String s[] = ids[i].split("_");
			int len = s.length;
			switch (len) {
			case 1:
				tVisExcelService.updateSort(s[0], sorts[i]);
				break;
			case 2:
				tVisExcelModuleService.updateSort(s[1], sorts[i]);
				break;
			case 3:
				tVisExcelModuleDetailService.updateSort(s[2], sorts[i]);
				break;
			}
		}
		addMessage(redirectAttributes, "保存排序成功!");
		return "redirect:" + adminPath + "/netconfig/tTpXmlFileUpload/list/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "checkName1")
	public String checkName1(String oldName, String name) {
		if (name != null && name.equals(oldName)) {
			return "true";
		} else if (name != null && tVisExcelService.getByName(name) == 0) {
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "save1")
	public String save1(TVisExcel tVisExcel, Model model, RedirectAttributes redirectAttributes) {
		tVisExcelService.save(tVisExcel);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/tTpXmlFileUpload/list/?repage";
	}
	
	@RequestMapping(value = "save2")
	public String save2(TVisExcelModule tVisExcelModule, Model model, RedirectAttributes redirectAttributes) {
		tVisExcelModuleService.save(tVisExcelModule);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/tTpXmlFileUpload/list/?repage";
	}
	
	@RequestMapping(value = "save3")
	public String save2(TVisExcelModuleDetail tVisExcelModuleDetail, Model model, RedirectAttributes redirectAttributes) {
		tVisExcelModuleDetailService.save(tVisExcelModuleDetail);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/tTpXmlFileUpload/list/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		System.out.println(id);
		String[] ids = id.split("_");
		int len = ids.length;
		switch (len) {
		case 1://如果ids长度等于1，代表删除的是excel模板，最大的模板
			TVisExcel tVisExcel = new TVisExcel();
			tVisExcel.setId(id);
			tVisExcelService.delete(tVisExcel);
			//删除该模板下的所有模块
			TVisExcelModule tVisExcelModule0 = new TVisExcelModule();
			tVisExcelModule0.setExcelId(Integer.valueOf(id));
			tVisExcelModuleService.delete(tVisExcelModule0);
			//删除该模板下的所有小命令
			TVisExcelModuleDetail tVisExcelModuleDetail0 = new TVisExcelModuleDetail();
			tVisExcelModuleDetail0.setExcelId(Integer.valueOf(id));
			tVisExcelModuleDetailService.delete(tVisExcelModuleDetail0);
			break;
		case 2:
			TVisExcelModule tVisExcelModule = new TVisExcelModule();
			tVisExcelModule.setId(ids[1]);
			tVisExcelModuleService.delete(tVisExcelModule);
			TVisExcelModuleDetail tVisExcelModuleDetail = new TVisExcelModuleDetail();
			tVisExcelModuleDetail.setModuleId(Integer.valueOf(ids[1]));
			tVisExcelModuleDetailService.delete(tVisExcelModuleDetail);
			break;
		case 3:
			TVisExcelModuleDetail _tVisExcelModuleDetail = new TVisExcelModuleDetail();
			_tVisExcelModuleDetail.setId(ids[2]);
			tVisExcelModuleDetailService.delete(_tVisExcelModuleDetail);
			break;
		}
		addMessage(redirectAttributes, "删除模板成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/tTpXmlFileUpload/list/?repage";
	}

}
