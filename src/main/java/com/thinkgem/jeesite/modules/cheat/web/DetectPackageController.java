package com.thinkgem.jeesite.modules.cheat.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.entity.FTPInfo;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.SFtpCarrierUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.DetectPackage;
import com.thinkgem.jeesite.modules.cheat.service.DetectPackageService;


/**
 * 计费欺诈更新包管理Controller
 * @author zhuguangrui
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/detectPackage")
public class DetectPackageController extends BaseController  {
	
	@Autowired
	private DetectPackageService detectPackageService;
	
	
	private String remotePath = Global.getConfig("remoteScriptPath");
	
	private String localPath = Global.getConfig("localParamPkgPath");
	
	@RequiresPermissions("cheat:detectPackage:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("detectPackage")DetectPackage detectPackage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DetectPackage> page = detectPackageService.findPage(new Page<DetectPackage>(request, response), detectPackage);
		if(page == null){
			page = new Page<DetectPackage>();
		}
		model.addAttribute("page", page);
		return "modules/cheat/detectPackageList";
	}
	
	
	@RequiresPermissions("cheat:detectPackage:import")
	@RequestMapping(value = "importForm")
	public String importForm() {
		return "modules/cheat/detectPackageImport";
	}
	
	/**ParamPackageuploadPath
	 * 保存上传的文件
	 * 
	 * @return
	 */
	@RequiresPermissions("cheat:detectPackage:import")
	@RequestMapping(value = "import")
	public String importAction(@RequestParam(value = "upload", required = false) MultipartFile[] uploads, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		for(int i=0; i<uploads.length; i++) {
			MultipartFile upload = uploads[i];
			String fileName = upload.getOriginalFilename(); 
			// 上传文件
			File oriFile = this.upload(upload, request);
			
			 //上传到远程服务器
			FTPInfo ftpInfo = new FTPInfo();
			ftpInfo.setHost(Global.getConfig("cheatftp.host"));
			ftpInfo.setUserName(Global.getConfig("cheatftp.name"));
			ftpInfo.setPassword(Global.getConfig("cheatftp.pwd"));
			
			SFtpCarrierUtil ftpCarrier = new SFtpCarrierUtil(ftpInfo);
			try {
				ftpCarrier.connect();
				boolean flag = ftpCarrier.bacthUploadFile(remotePath, localPath, false);
				ftpCarrier.disconnect();
				
				if(flag){
					DetectPackage detectPackage = new DetectPackage();
					detectPackage.setName(fileName);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					detectPackage.setUpdateTime(sdf.format(new Date()));
					detectPackageService.save(detectPackage);
					addMessage(redirectAttributes, "导入成功");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				addMessage(redirectAttributes, "导入失败，请联系管理员");
			}finally{
				// 删除文件
				oriFile.delete();
				if(ftpCarrier != null){
					ftpCarrier.disconnect();
				}
			}
		}
		return "redirect:"+Global.getAdminPath()+"/cheat/detectPackage/list/?repage";
	}

	/**
	 * 上传文件方法
	 */
	private File upload(MultipartFile upload, HttpServletRequest request) {
        String fileName = upload.getOriginalFilename();  
        File dirFile = new File(localPath);  
        if(!dirFile.exists()){  
        	dirFile.mkdirs();  
        } else {
        	deleteDir(dirFile);
        }
        //保存  
        String filePath = localPath + File.separator + fileName;
        File targetFile = new File(filePath);  
        try {  
        	upload.transferTo(targetFile);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        return targetFile;
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

}
