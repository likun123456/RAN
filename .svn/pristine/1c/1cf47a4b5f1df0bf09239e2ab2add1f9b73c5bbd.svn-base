package com.thinkgem.jeesite.modules.userquery.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.userquery.entity.TUserSignalHostConfig;
import com.thinkgem.jeesite.modules.userquery.service.TUserSignalHostConfigService;

/**
 * 单用户信令追踪host配置Controller
 * @author 陈宏波
 * @version 2017-07-17
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "${adminPath}/userquery/signalHostConfig")
public class UserSignalHostConfigController extends BaseController  {
	
	private String savePath = Global.getConfig("savePath");
	private String hostFile = Global.getConfig("hostFile");
	private String hostFilePath = Global.getConfig("hostFilePath");
	
	@Autowired
	private TUserSignalHostConfigService userSignalHostConfigService;
	
	@ModelAttribute
	public TUserSignalHostConfig get(@RequestParam(required=false) String id) {
		TUserSignalHostConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userSignalHostConfigService.get(id);
		}
		if (entity == null){
			entity = new TUserSignalHostConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("userquery:signalHostConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tUserSignalHostConfig")TUserSignalHostConfig tUserSignalHostConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TUserSignalHostConfig> page = userSignalHostConfigService.findPage(new Page<TUserSignalHostConfig>(request, response), tUserSignalHostConfig); 
		model.addAttribute("page", page);
		return "modules/userquery/tSignalHostConfigList";
	}
	
	@RequiresPermissions("userquery:signalHostConfig:view")
	@RequestMapping(value = "form")
	public String form(TUserSignalHostConfig tUserSignalHostConfig, Model model) {
		model.addAttribute("tUserSignalHostConfig", tUserSignalHostConfig);
		return "modules/userquery/tSignalHostConfigForm";
	}
	
	@RequiresPermissions("userquery:signalHostConfig:edit")
	@RequestMapping(value = "save")
	public String save(TUserSignalHostConfig tUserSignalHostConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tUserSignalHostConfig)){
			return form(tUserSignalHostConfig, model);
		}
		userSignalHostConfigService.save(tUserSignalHostConfig);
		syncHostFile();
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/signalHostConfig/?repage";
	}
	
	@RequiresPermissions("userquery:signalHostConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(TUserSignalHostConfig tUserSignalHostConfig, RedirectAttributes redirectAttributes) {
		userSignalHostConfigService.delete(tUserSignalHostConfig);
		syncHostFile();
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/signalHostConfig/?repage";
	}
	
	@RequiresPermissions("userquery:signalHostConfig:import")
	@RequestMapping(value = "importForm")
	public String importForm() {
		return "modules/userquery/tsignalHostConfigImportForm";
	}
	
	@RequiresPermissions("userquery:ratingroupConfig:import")
	@RequestMapping(value = "importAction")
	public String importAction(@RequestParam(value = "upload", required = false) MultipartFile upload, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			// 上传文件
			String oriFilePath = this.upload(upload, request);
			fileInputStream = new FileInputStream(oriFilePath);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			List<TUserSignalHostConfig> list = new ArrayList<TUserSignalHostConfig>();
			while ((line = reader.readLine()) != null) {
				if(!line.trim().isEmpty()) {
					String[] arr = line.split("\\s+");
					TUserSignalHostConfig signal = new TUserSignalHostConfig();
					signal.setIp(arr[0].trim());
					signal.setName(arr[1].trim());
					list.add(signal);
				}
				
			}
			//清除表数据
			userSignalHostConfigService.delete(new TUserSignalHostConfig());
			userSignalHostConfigService.batchIntert(list);
			syncHostFile();
			deleteFile(oriFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) {
					reader.close();
					reader = null;
				}
				if(inputStreamReader != null) {
					inputStreamReader.close();
					inputStreamReader = null;
				}
				if(fileInputStream != null) {
					fileInputStream.close();
					fileInputStream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		addMessage(redirectAttributes, "导入成功");
		return "redirect:"+Global.getAdminPath()+"/userquery/signalHostConfig/?repage";
	}
	
	/**
	 * 执行插入，修改，删除操作 重新写入文件
	 */
	private void syncHostFile() {
		List<TUserSignalHostConfig> list = userSignalHostConfigService.findList(new TUserSignalHostConfig());
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter writer = null;
		try {
			//String file = hostFilePath + File.separator + hostFile;
			File dir = new File(hostFilePath);  
			if(!dir.exists()) {
				dir.mkdirs();  
			}
			
			//File target = new File(file);
			File target = new File(dir,hostFile);
			if(!target.exists()){  
		        target.createNewFile();  
		    } 
			fileOutputStream = new FileOutputStream(target.getAbsoluteFile());
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			writer = new BufferedWriter(outputStreamWriter);
			for(TUserSignalHostConfig config : list) {
				writer.write(config.getIp() + "  " + config.getName());
				writer.newLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer != null) {
					writer.close();
					writer = null;
				}
				if(outputStreamWriter != null) {
					outputStreamWriter.close();
					outputStreamWriter = null;
				}
				if(fileOutputStream != null) {
					fileOutputStream.close();
					fileOutputStream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
	
	private void deleteFile(String fileName) {
		File f = new File(fileName);
		f.delete();
	}
}
