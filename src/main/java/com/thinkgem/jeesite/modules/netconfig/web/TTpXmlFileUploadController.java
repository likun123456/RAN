package com.thinkgem.jeesite.modules.netconfig.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.ExcelTemplate;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetail;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetailJson;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleDetailService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;


/**
 * 拓扑xml文件上传
 * 
 * @author wangjingshi
 * @version 2018-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tTpXmlFileUpload")
@Scope("prototype")
public class TTpXmlFileUploadController extends BaseController {

	@Autowired
	private TVisExcelService tVisExcelService;
	@Autowired
	private TVisExcelModuleService tVisExcelModuleService;
	@Autowired
	private TVisExcelModuleDetailService tVisExcelModuleDetailService;

	private File upload;
	private String localPath = Global.getConfig("TpXmlPath");
	
	@RequiresPermissions("netconfig:tPXmlFileUpload:view")
	@RequestMapping(value = "form")
	public String form(@Valid @ModelAttribute("tVisExcel") TVisExcel tVisExcel, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "modules/netconfig/tPXmlFileUploadForm";
	}
	
	@RequestMapping(value = "queryVisExcelListByType")
	@ResponseBody
	public String queryVisExcelListByType(String type,String tpType) {
		TVisExcel tve=new TVisExcel();
		if(null!=type&&!"".equals(type)){
			tve.setType(type);
		}
		if(null!=tpType&&!"".equals(tpType)){
			tve.setTemplatetype(tpType);
		}
		List<TVisExcel> list = tVisExcelService.findList(tve);
		return JsonMapper.getInstance().toJson(list);
	}

	@RequiresPermissions("netconfig:tPXmlFileUpload:view")
	@RequestMapping(value = { "list", "" })
	public String list(@Valid @ModelAttribute("tVisExcel") TVisExcel tVisExcel, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/*Page<TVisExcel> page = tVisExcelService.findPage(new Page<TVisExcel>(request, response), tVisExcel);
		model.addAttribute("page", page);*/
		List<TVisExcel> visExcelList = tVisExcelService.findList(tVisExcel);
		List<TVisExcelModule> tVisExcelModuleList = tVisExcelModuleService.findList(new TVisExcelModule());
		List<TVisExcelModuleDetail> TVisExcelModuleDetailList = tVisExcelModuleDetailService
				.findList(new TVisExcelModuleDetail());
		List<ExcelTemplate> list = ExcelTemplate.sortList(visExcelList, tVisExcelModuleList, TVisExcelModuleDetailList);
		model.addAttribute("list", list);
		return "modules/netconfig/tPXmlFileUploadList";
	}
	
	
	@RequiresPermissions("netconfig:tPXmlFileUpload:view")
	@RequestMapping(value = { "downloadExcelTemplatelist", "" })
	public String downloadExcelTemplatelist(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "modules/netconfig/downloadExcelTemplatelist";
	}
	
	
	@RequestMapping(value = { "downloadExcelTemplate" })
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) {
		String path = Global.getConfig("TpXmlTemplateDown");
		File f = new File(path);
		FileUtils.downFile(f, request, response);
	}

	@RequestMapping(value = "uploadFile")
	public String uploadFile(@Valid @ModelAttribute("tVisExcel") TVisExcel tVisExcel,
			@RequestParam(value = "upload", required = false) MultipartFile file) {
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		List<TVisExcel> list = tVisExcelService.findList(tVisExcel);
		for(int i=0;i<list.size();i++) {
			if(fileName.substring(0,fileName.lastIndexOf(".")).equals(list.get(i).getName())) {
				TVisExcel tVisExcel0 = new TVisExcel();
				tVisExcel0.setId(list.get(i).getId());
				tVisExcelService.delete(tVisExcel0);
				//删除该模板下的所有模块
				TVisExcelModule tVisExcelModule0 = new TVisExcelModule();
				tVisExcelModule0.setExcelId(Integer.valueOf(list.get(i).getId()));
				tVisExcelModuleService.delete(tVisExcelModule0);
				//删除该模板下的所有小命令
				TVisExcelModuleDetail tVisExcelModuleDetail0 = new TVisExcelModuleDetail();
				tVisExcelModuleDetail0.setExcelId(Integer.valueOf(list.get(i).getId()));
				tVisExcelModuleDetailService.delete(tVisExcelModuleDetail0);
			}
		}
		String filePath = localPath + File.separator + fileName;
		try {
			upload = new File(filePath);
			file.transferTo(upload);
			try {
				ImportExcel ei = new ImportExcel(upload, 2);
				TVisExcelModuleDetail tmd;
				TVisExcelModule tem = null;
				TVisExcel tve = new TVisExcel();
				tve.setName(fileName.substring(0, fileName.indexOf(".")));
				if(tVisExcel != null){
					tve.setType(tVisExcel.getType());
					tve.setTemplatetype(tVisExcel.getTemplatetype());
				}
				List<TVisExcel> tveList = tVisExcelService.findList(tve);
				if (tveList.size() > 0) {// 如果数据库中存在该文档名，先删除
					tem = new TVisExcelModule();
					tem.setExcelId(Integer.parseInt(tveList.get(0).getId()));
					tVisExcelService.delete(tve);
					tVisExcelModuleService.delete(tem);
					tmd = new TVisExcelModuleDetail();
					tmd.setExcelId(Integer.parseInt(tveList.get(0).getId()));
					tVisExcelModuleDetailService.delete(tmd);
				}

				tVisExcelService.saveTVisExcel(tve);
				int executeNo = 0;
				for (int i = ei.getDataRowNum(); i < ei.getLastDataRowNum(); i++) {
					Row row = ei.getRow(i);
					if (null != ei.getCellValue(row, 6) && !"".equals(ei.getCellValue(row, 6))) {
						Object val = ei.getCellValue(row, 0);
						if (null != val && !"".equals(val.toString().trim())) {
							tem = new TVisExcelModule();
							tem.setExcelId(Integer.parseInt(tve.getId()));
							tem.setModuleName(val.toString());
							if (null != ei.getCellValue(row, 1)
									&& !"".equals(ei.getCellValue(row, 1).toString().trim())) {
								tem.setModuleType(ei.getCellValue(row, 1).toString());
							} else {
								tem.setModuleType("");
							}
							if (null != ei.getCellValue(row, 2)
									&& !"".equals(ei.getCellValue(row, 2).toString().trim())) {
								tem.setNetType(ei.getCellValue(row, 2).toString());
							} else {
								tem.setNetType("");
							}
							tVisExcelModuleService.saveTVisExcelModule(tem);
							executeNo = 0;
						}
						tmd = new TVisExcelModuleDetail();
						tmd.setExcelId(Integer.parseInt(tve.getId()));
						tmd.setModuleId(Integer.parseInt(tem.getId()));
						if (null != ei.getCellValue(row, 6) && !"".equals(ei.getCellValue(row, 6).toString().trim())) {
							if (ei.getCellValue(row, 6).equals("SYSTEM-INPUT")) {
								tmd.setParamType(0);
								tmd.setExecuteNo(0);
							} else {
								executeNo++;
								tmd.setParamType(1);
								tmd.setExecuteNo(executeNo);
							}
						} else {
							tmd.setParamType(1);
						}
						if (null != ei.getCellValue(row, 3) && !"".equals(ei.getCellValue(row, 3).toString().trim())) {
							tmd.setCommandName(ei.getCellValue(row, 3).toString());
						} else {
							tmd.setCommandName("");
						}
						if (null != ei.getCellValue(row, 4) && !"".equals(ei.getCellValue(row, 4).toString().trim())) {
							tmd.setFormRemark(ei.getCellValue(row, 4).toString());
						} else {
							tmd.setFormRemark("");
						}
						if (null != ei.getCellValue(row, 5) && !"".equals(ei.getCellValue(row, 5).toString().trim())) {
							tmd.setDebugRemark(ei.getCellValue(row, 5).toString());
						} else {
							tmd.setDebugRemark("");
						}
						if (null != ei.getCellValue(row, 6) && !"".equals(ei.getCellValue(row, 6).toString().trim())) {
							String confCmd = ei.getCellValue(row, 6).toString();
							if(confCmd.startsWith("SYSTEM-INPUT")) {
								tmd.setConfCmd("SYSTEM-INPUT");
							}else {
								tmd.setConfCmd(confCmd);
							}
						} else {
							tmd.setConfCmd("");
						}
						if (null != ei.getCellValue(row, 7) && !"".equals(ei.getCellValue(row, 7).toString().trim())) {
							String beforePrompt = ei.getCellValue(row, 7).toString();
							if(beforePrompt.equals("N/A")) {
								tmd.setBeforePrompt("");
							}else {
								tmd.setBeforePrompt(beforePrompt);
							}
						} else {
							tmd.setBeforePrompt("");
						}
						if (null != ei.getCellValue(row, 8) && !"".equals(ei.getCellValue(row, 8).toString().trim())) {
							String afterPrompt = ei.getCellValue(row, 8).toString();
							if(afterPrompt.equals("N/A")) {
								tmd.setAfterPrompt("");
							}else {
								tmd.setAfterPrompt(afterPrompt);
							}
						} else {
							tmd.setAfterPrompt("");
						}
						if (null != ei.getCellValue(row, 9) && !"".equals(ei.getCellValue(row, 9).toString().trim())) {
							String checkRegexp = ei.getCellValue(row, 9).toString();
							if(checkRegexp.equals("N/A")) {
								tmd.setCheckRegexp("");
							}else {
								tmd.setCheckRegexp(checkRegexp);
							}
							
						} else {
							tmd.setCheckRegexp("");
						}
						if (null != ei.getCellValue(row, 10)
								&& !"".equals(ei.getCellValue(row, 10).toString().trim())) {
							String timeout = ei.getCellValue(row, 10).toString();
							if(timeout.contains(".0")) {
								timeout = timeout.substring(0, timeout.length()-2);
								tmd.setTimeout(timeout);
							}else if(timeout.equals("N/A")) {
								tmd.setTimeout("");
							}else {
								tmd.setTimeout(timeout);
							}
						} else {
							tmd.setTimeout("");
						}
						if (null != ei.getCellValue(row, 11)
								&& !"".equals(ei.getCellValue(row, 11).toString().trim())) {
							String errorHandleMode = ei.getCellValue(row, 11).toString();
							if(errorHandleMode.equals("N/A")) {
								tmd.setErrorHandleMode("");
							}else {
								tmd.setErrorHandleMode(errorHandleMode);
							}
						} else {
							tmd.setErrorHandleMode("");
						}
						if (null != ei.getCellValue(row, 12)
								&& !"".equals(ei.getCellValue(row, 12).toString().trim())) {
							String varArrayRegexp = ei.getCellValue(row, 12).toString();
							if(varArrayRegexp.equals("N/A")) {
								tmd.setVarArrayRegexp("");
							}else{
								tmd.setVarArrayRegexp(varArrayRegexp);
							}
						} else {
							tmd.setVarArrayRegexp("");
						}
						if (null != ei.getCellValue(row, 13)
								&& !"".equals(ei.getCellValue(row, 13).toString().trim())) {
							String varArray = ei.getCellValue(row, 13).toString();
							if(varArray.equals("N/A")) {
								tmd.setVarArray("");
							}else{
								tmd.setVarArray(varArray);
							}
						} else {
							tmd.setVarArray("");
						}
						if (null != ei.getCellValue(row, 14)
								&& !"".equals(ei.getCellValue(row, 14).toString().trim())) {
							tmd.setRemark(ei.getCellValue(row, 14).toString());
						} else {
							tmd.setRemark("");
						}
						tVisExcelModuleDetailService.save(tmd);
					}
				}

			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return "redirect:" + Global.getAdminPath() + "/netconfig/tTpXmlFileUpload/list?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		//如果ids长度等于1，代表删除的是excel模板，最大的模板
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
		    addMessage(redirectAttributes, "删除模板成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/tTpXmlFileUpload/list?repage";
	}

	public void writeToFile(List<TVisExcelModuleDetailJson> content, File file) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			if (file.exists()) {
				fw = new FileWriter(file, true);
				pw = new PrintWriter(fw);
				pw.println(JsonMapper.toJsonString(content));
				pw.flush();
				fw.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	@RequestMapping(value = "export")
	public void export(String id,String templatename,HttpServletRequest request, HttpServletResponse response) {
		//template文件存在服务器tomcat下的路径
		String path = request.getSession().getServletContext().getRealPath("/")+"file/template.xlsm";
		File file = new File(path);
		InputStream inputStream = null;  
        Workbook workbook = null; 
        OutputStream fos = null;
        response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+new String(templatename.getBytes("GB2312"),"iso-8859-1")+".xlsm");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        try {
        	fos = response.getOutputStream();
			inputStream = new FileInputStream(file);
			workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0); 
			/*--------------------查询模块----------------------------*/
			TVisExcelModule tVisExcelModule = new TVisExcelModule();
			tVisExcelModule.setExcelId(Integer.valueOf(id));
			List<TVisExcelModule> tVisExcelModuleList = tVisExcelModuleService.findList(tVisExcelModule);
			Map<String,TVisExcelModule> map = new HashMap<String,TVisExcelModule>();
			for(int x=0;x<tVisExcelModuleList.size();x++) {
				map.put(tVisExcelModuleList.get(x).getExcelId()+"-"+tVisExcelModuleList.get(x).getId(), tVisExcelModuleList.get(x));
			}
			/*--------------------查询模块详细----------------------------*/
			TVisExcelModuleDetail tVisExcelModuleDetail = new TVisExcelModuleDetail();
			tVisExcelModuleDetail.setExcelId(Integer.valueOf(id));
			List<TVisExcelModuleDetail> tVisExcelModuleDetailList = tVisExcelModuleDetailService.findList(tVisExcelModuleDetail);
			/*------------------------------------------------*/
			TVisExcelModuleDetail tempDetailVO;
			for(int i=0;i<tVisExcelModuleDetailList.size();i++) {
				tempDetailVO = tVisExcelModuleDetailList.get(i);
				Row row = sheet.createRow(3+i);
				Cell cell0 = row.createCell(0);
				Cell cell1 = row.createCell(1);
				Cell cell2 = row.createCell(2);
				if(i==0) {
					cell0.setCellValue(map.get(tempDetailVO.getExcelId()+"-"+tempDetailVO.getModuleId()).getModuleName());
					cell1.setCellValue(map.get(tempDetailVO.getExcelId()+"-"+tempDetailVO.getModuleId()).getModuleType());
					cell2.setCellValue(map.get(tempDetailVO.getExcelId()+"-"+tempDetailVO.getModuleId()).getNetType());
				}else if(tempDetailVO.getModuleId()==tVisExcelModuleDetailList.get(i-1).getModuleId()) {
					cell0.setCellValue("");
					cell1.setCellValue("");
					cell2.setCellValue("");
				}else {
					cell0.setCellValue(map.get(tempDetailVO.getExcelId()+"-"+tempDetailVO.getModuleId()).getModuleName());
					cell1.setCellValue(map.get(tempDetailVO.getExcelId()+"-"+tempDetailVO.getModuleId()).getModuleType());
					cell2.setCellValue(map.get(tempDetailVO.getExcelId()+"-"+tempDetailVO.getModuleId()).getNetType());
				}
				Cell cell3 = row.createCell(3);
				cell3.setCellValue(tempDetailVO.getCommandName());
				Cell cell4 = row.createCell(4);
				cell4.setCellValue(tempDetailVO.getFormRemark());
				Cell cell5 = row.createCell(5);
				cell5.setCellValue(tempDetailVO.getDebugRemark());
				Cell cell6 = row.createCell(6);
				cell6.setCellValue(tempDetailVO.getConfCmd());
				Cell cell7 = row.createCell(7);
				cell7.setCellValue(tempDetailVO.getBeforePrompt());
				Cell cell8 = row.createCell(8);
				cell8.setCellValue(tempDetailVO.getAfterPrompt());
				Cell cell9 = row.createCell(9);
				cell9.setCellValue(tempDetailVO.getCheckRegexp());
				Cell cell10 = row.createCell(10);
				cell10.setCellValue(tempDetailVO.getTimeout());
				Cell cell11 = row.createCell(11);
				cell11.setCellValue(tempDetailVO.getErrorHandleMode());
				Cell cell12 = row.createCell(12);
				cell12.setCellValue(tempDetailVO.getVarArrayRegexp());
				Cell cell13 = row.createCell(13);
				cell13.setCellValue(tempDetailVO.getVarArray());
				Cell cell14 = row.createCell(14);
				cell14.setCellValue(tempDetailVO.getRemark());
			}
            workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}