package com.thinkgem.jeesite.modules.paramconfig.web;

import java.io.File;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.entity.FTPInfo;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.SFtpCarrierUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamPackage;
import com.thinkgem.jeesite.modules.paramconfig.service.ParamPackageManagerService;

/**
 * 网元参数更新包管理Controller
 * @author chenhongbo
 * @version 2017-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/paramconfig/paramPackage")
public class ParamPackageManagerController extends BaseController  {
	
	@Autowired
	private ParamPackageManagerService paramPackageManagerService;
	
	@Autowired
	private TPoolService tPoolService;
	
	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@Autowired
	private TCollectordeployService tCollectordeployService;
	
	//private String savePath = "/opt/Ericsson/core/ParamPackage";
	//private String savePath = Global.getConfig("savePath");
	
	private String remotePath = Global.getConfig("remoteParamPkgPath");
	
	private String localPath = Global.getConfig("localParamPkgPath");
	
	@RequiresPermissions("paramconfig:paramPackage:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("paramPackage")ParamPackage paramPackage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ParamPackage> page = paramPackageManagerService.findPage(new Page<ParamPackage>(request, response), paramPackage); 
		model.addAttribute("page", page);
		return "modules/paramconfig/paramPackageList";
	}
	
	/**
	 * 根据选择的类型，查询对应的网元
	 * 
	 * @param extId
	 * @param type
	 * @param grade
	 * @param isAll
	 * @param parameterEpc
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll,
			HttpServletResponse response) {
		
		List<TPool> poolList = tPoolService.queryPoolListByType(null);
		
		List<Map<String, Object>> mapList = Lists.newArrayList();

			TNewnetelement tnewnetelement = new TNewnetelement();
			//tnewnetelement.setType("1");
			List<TNewnetelement> list = tNewnetelementService.findList(tnewnetelement);
			
			for(TPool pool : poolList){
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "p"+pool.getId());
				mapParent.put("pId", "0");
				mapParent.put("pIds", "0");
				mapParent.put("name", pool.getFpoolname());
				mapList.add(mapParent);
			 }
			
			for (int i = 0; i < list.size(); i++) {
				TNewnetelement e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
			/*	if (i == 0) {
					Map<String, Object> mapParent = Maps.newHashMap();
					mapParent.put("id", "0");
					mapParent.put("pId", "0");
					mapParent.put("pIds", "0");
					mapParent.put("name", "网元");
					mapList.add(mapParent);
				}*/
				map.put("id", e.getId());
				map.put("pId", "p" + e.getFnid());
				map.put("pIds", "0,"+ "p" + e.getFnid());
				map.put("name", e.getFname());
				mapList.add(map);
			}
		
		return mapList;
	}
	
	@RequiresPermissions("paramconfig:paramPackage:export")
	@RequestMapping(value = "exportForm")
	public String form() {
		return "modules/paramconfig/paramPackageExport";
	}
	
	/**ParamPackageuploadPath
	 * 保存上传的文件
	 * 
	 * @return
	 */
	@RequiresPermissions("paramconfig:paramPackage:export")
	@RequestMapping(value = "export")
	public String export(@RequestParam(value = "upload", required = false) MultipartFile[] uploads, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		for(int i=0; i<uploads.length; i++) {
			MultipartFile upload = uploads[i];
			String fileName = upload.getOriginalFilename(); 
			// 上传文件
			File oriFile = this.upload(upload, request);
			
			//GZipUtils.unTargzFile(oriFilePath, savePath);
			FileUtils.unTargzFile(oriFile.getAbsolutePath(), localPath);
			
			// 删除压缩包
			deleteTar(oriFile.getAbsolutePath());
			
			try {
				paramPackageManagerService.readParamCSV(localPath +  File.separator + fileName.split("_")[0], fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 //上传到远程服务器
			List<FTPInfo> ftpInfos = tCollectordeployService.queryFTPInfoList();
			/*for(FTPInfo ftpInfo : ftpInfos){
				SFtpCarrierUtil ftpCarrier = new SFtpCarrierUtil(ftpInfo);
				ftpCarrier.connect();
				boolean flag = ftpCarrier.bacthUploadFile(remotePath + File.separator + fileName.split("_")[0] + File.separator, localPath + File.separator + fileName.split("_")[0] + File.separator, false);
				ftpCarrier.disconnect();
			}*/
			
			for(int j=0; j<ftpInfos.size(); j++) {
				FTPInfo ftpInfo = ftpInfos.get(j);
				SFtpCarrierUtil ftpCarrier = new SFtpCarrierUtil(ftpInfo);
				ftpCarrier.connect();
				boolean flag = false;
				if(j == ftpInfos.size() -1) {
					flag = true;
				}
				boolean result = ftpCarrier.bacthUploadFile(remotePath + File.separator + fileName.split("_")[0] + File.separator, localPath + File.separator + fileName.split("_")[0] + File.separator, flag);
				ftpCarrier.disconnect();
			}
		}
		addMessage(redirectAttributes, "导入成功");
		return "redirect:"+Global.getAdminPath()+"/paramconfig/paramPackage/?repage";
	}

	/**
	 * 上传文件方法
	 */
	private File upload(MultipartFile upload, HttpServletRequest request) {
        String fileName = upload.getOriginalFilename();  
        String dir = localPath +  File.separator + fileName.split("_")[0];
        File dirFile = new File(dir);  
        if(!dirFile.exists()){  
        	dirFile.mkdirs();  
        } else {
        	deleteDir(dirFile);
        }
        //保存  
        String filePath = dir + File.separator + fileName;
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

	/**
	 * 删除已解压的压缩包
	 * 
	 * @param fileName
	 */
	private void deleteTar(String fileName) {
		File f = new File(fileName);
		f.delete();
	}
}
