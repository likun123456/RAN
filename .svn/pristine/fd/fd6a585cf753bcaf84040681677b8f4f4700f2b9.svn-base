package com.thinkgem.jeesite.modules.cheat.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.TFreeRatingGroup;
import com.thinkgem.jeesite.modules.cheat.service.TFreeRatingGroupService;

/**
 * 免费业务代码配置 Controller
 * @author zhuguangrui
 * @version 2017-08-22
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "${adminPath}/cheat/freeRatingGroup")
public class FreeRatingGroupConfigController extends BaseController {
	
	private String savePath = Global.getConfig("savePath");
	
	@Autowired
	private TFreeRatingGroupService freeRatingGroupService;
	
	@ModelAttribute
	public TFreeRatingGroup get(@RequestParam(required=false) String id) {
		TFreeRatingGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = freeRatingGroupService.get(id);
		}
		if (entity == null){
			entity = new TFreeRatingGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("cheat:freeRatingGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("freeRatingGroup")TFreeRatingGroup freeRatingGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TFreeRatingGroup> page = freeRatingGroupService.findPage(new Page<TFreeRatingGroup>(request, response), freeRatingGroup);
		if(page == null){
			page = new Page<TFreeRatingGroup>();
		}
		model.addAttribute("page", page);
		return "modules/cheat/freeRatingGroupList";
	}
	
	@RequiresPermissions("cheat:freeRatingGroup:add")
	@RequestMapping(value = "form")
	public String form(TFreeRatingGroup freeRatingGroup, Model model) {
		model.addAttribute("freeRatingGroup", freeRatingGroup);
		return "modules/cheat/freeRatingGroupForm";
	}
	
	@RequiresPermissions("cheat:freeRatingGroup:edit")
	@RequestMapping(value = "save")
	public String save(TFreeRatingGroup freeRatingGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, freeRatingGroup)){
			return form(freeRatingGroup, model);
		}
		freeRatingGroupService.save(freeRatingGroup);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/cheat/freeRatingGroup/list/?repage";
	}
	
	@RequiresPermissions("cheat:freeRatingGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(TFreeRatingGroup freeRatingGroup, RedirectAttributes redirectAttributes) {
		freeRatingGroupService.delete(freeRatingGroup);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/cheat/freeRatingGroup/list/?repage";
	}
	
	@RequiresPermissions("cheat:freeRatingGroup:import")
	@RequestMapping(value = "importForm")
	public String importForm() {
		return "modules/cheat/freeRatingGroupImportForm";
	}
	
	/**
	 * 保存上传的文件
	 * 
	 * @return
	 */
	@RequiresPermissions("cheat:freeRatingGroup:import")
	@RequestMapping(value = "import")
	public String importAction(@RequestParam(value = "upload", required = false) MultipartFile upload, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {
			// 上传文件
			String oriFilePath = this.upload(upload, request);
			//解析excel文件
			ImportExcel ei = new ImportExcel(oriFilePath, 0);
			List<TFreeRatingGroup> list = new ArrayList<TFreeRatingGroup>();
			for (int i = ei.getDataRowNum(); i <= ei.getLastDataRowNum(); i++) {
				Row row = ei.getRow(i);
				String ratingGroup = ei.getCellValue(row, 0).toString();
				String name = ei.getCellValue(row, 1).toString();
				TFreeRatingGroup tc = new TFreeRatingGroup();
				tc.setRatingGroup(ratingGroup);
				tc.setName(name);
				list.add(tc);
			}
			freeRatingGroupService.batchIntert(list);
			deleteExcel(oriFilePath);
			addMessage(redirectAttributes, "导入成功");
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
	    	addMessage(redirectAttributes, "导入失败，请联系管理员");
		} 
		return "redirect:"+Global.getAdminPath()+"/cheat/freeRatingGroup/list/?repage";
	}

	/**
	 * 上传文件方法
	 */
	private String upload(MultipartFile upload, HttpServletRequest request) {
        String fileName = upload.getOriginalFilename();  
        String dir = savePath;
        File dirFile = new File(dir);  
        if(!dirFile.exists()){  
        	dirFile.mkdirs();  
        } else {
        	deleteDir(dirFile);
        }
        //保存  
        File targetFile = new File(dir + File.separator + fileName);  
        try {  
        	upload.transferTo(targetFile);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return targetFile.getAbsolutePath();
	}
	
	/**
	 * 递归删除目录下文件
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				new File(dir, children[i]).delete();
			}
		}
		// 目录此时为空，可以删除
		return true;
	}

	private void deleteExcel(String fileName) {
		File f = new File(fileName);
		f.delete();
	}
}
