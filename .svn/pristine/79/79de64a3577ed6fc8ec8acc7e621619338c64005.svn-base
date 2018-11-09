package com.thinkgem.jeesite.modules.userquery.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import com.thinkgem.jeesite.modules.userquery.entity.TAllRatinggroupConfig;
import com.thinkgem.jeesite.modules.userquery.service.TAllRatinggroupConfigService;

/**
 * 话单业务配置 Controller
 * @author chenhongbo
 * @version 2017-07-12
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "${adminPath}/userquery/ratingroupConfig")
public class UserRatingroupConfigController extends BaseController {
	
	private String savePath = Global.getConfig("savePath");
	
	@Autowired
	private TAllRatinggroupConfigService tAllRatinggroupConfigService;
	
	@ModelAttribute
	public TAllRatinggroupConfig get(@RequestParam(required=false) String id) {
		TAllRatinggroupConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tAllRatinggroupConfigService.get(id);
		}
		if (entity == null){
			entity = new TAllRatinggroupConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("userquery:ratingroupConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tRatingroupConfig")TAllRatinggroupConfig tRatingroupConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TAllRatinggroupConfig> page = tAllRatinggroupConfigService.findPage(new Page<TAllRatinggroupConfig>(request, response), tRatingroupConfig); 
		model.addAttribute("page", page);
		return "modules/userquery/tRatingroupConfigList";
	}
	
	@RequiresPermissions("userquery:ratingroupConfig:view")
	@RequestMapping(value = "form")
	public String form(TAllRatinggroupConfig tRatingroupConfig, Model model) {
		model.addAttribute("tRatingroupConfig", tRatingroupConfig);
		return "modules/userquery/tRatingroupConfigForm";
	}
	
	@RequiresPermissions("userquery:ratingroupConfig:edit")
	@RequestMapping(value = "save")
	public String save(TAllRatinggroupConfig tRatingroupConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tRatingroupConfig)){
			return form(tRatingroupConfig, model);
		}
		tAllRatinggroupConfigService.save(tRatingroupConfig);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/ratingroupConfig/?repage";
	}
	
	@RequiresPermissions("userquery:ratingroupConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(TAllRatinggroupConfig tRatingroupConfig, RedirectAttributes redirectAttributes) {
		tAllRatinggroupConfigService.delete(tRatingroupConfig);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/ratingroupConfig/?repage";
	}
	
	@RequiresPermissions("userquery:ratingroupConfig:import")
	@RequestMapping(value = "importForm")
	public String importForm() {
		return "modules/userquery/tRatingroupConfigImportForm";
	}
	
	/**
	 * 保存上传的文件
	 * 
	 * @return
	 */
	@RequiresPermissions("userquery:ratingroupConfig:import")
	@RequestMapping(value = "importAction")
	public String importAction(@RequestParam(value = "upload", required = false) MultipartFile upload, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {
			// 上传文件
			String oriFilePath = this.upload(upload, request);
			//解析excel文件
			ImportExcel ei = new ImportExcel(oriFilePath, 0);
			List<TAllRatinggroupConfig> list = new ArrayList<TAllRatinggroupConfig>();
			for (int i = ei.getDataRowNum(); i <= ei.getLastDataRowNum(); i++) {
				Row row = ei.getRow(i);
				int ratingGroup = (int)Double.parseDouble(ei.getCellValue(row, 0).toString());
				String name = ei.getCellValue(row, 1).toString();
				byte type = (byte)Double.parseDouble(ei.getCellValue(row, 2).toString());
				//String type = ei.getCellValue(row, 2).toString();
				TAllRatinggroupConfig tc = new TAllRatinggroupConfig();
				tc.setRatinggroup(""+ratingGroup);
				tc.setName(name);
				tc.setType(""+type);
				list.add(tc);
			}
			tAllRatinggroupConfigService.batchIntert(list);
			
			deleteExcel(oriFilePath);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "导入成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/ratingroupConfig/?repage";
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
