/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.web;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisDanger;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisDangerStatus;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetail;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetailJson;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisDangerService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisDangerStatusService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleDetailService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;
import com.thinkgem.jeesite.modules.netconfig.thread.ExecuteExcelTemplate;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.userquery.entity.CapText;

/**
 * 模版调试管理Controller
 * 
 * @author yh
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/excelTemplateDebug")
public class TExcelTemplateDebugController extends BaseController {

	public static String status = "finish";

	@Autowired
	private TVisExcelModuleDetailService tVisExcelModuleDetailService;
	@Autowired
	private TVisExcelService tVisExcelService;
	@Autowired
	private TVisExcelModuleService tVisExcelModuleService;
	@Autowired
	private TNewnetelementService tnewnetelementService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TVisDangerService tVisDangerService;
	@Autowired
	private TVisDangerStatusService tVisDangerStatusService;

	@ModelAttribute
	public TVisExcelModuleDetail get(@RequestParam(required = false) String id) {
		TVisExcelModuleDetail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tVisExcelModuleDetailService.get(id);
		}
		if (entity == null) {
			entity = new TVisExcelModuleDetail();
		}
		return entity;
	}

	/**
	 * 获取模版内容信息列表
	 * 
	 * @param tVisExcelModuleDetail
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("netconfig:excelTemplateDebug:view")
	@RequestMapping(value = { "list", "" })
	public String list(@ModelAttribute("tVisExcelModuleDetail") TVisExcelModuleDetail tVisExcelModuleDetail,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null != model) {
			Map<String, Object> redirectMap = model.asMap();
			if (redirectMap.size() != 0) {
				if (null != redirectMap.get("excelId") && !"".equals(redirectMap.get("excelId"))) {
					tVisExcelModuleDetail.setExcelId(Integer.parseInt(redirectMap.get("excelId").toString()));
				}
				if (null != redirectMap.get("moduleId") && !"".equals(redirectMap.get("moduleId"))) {
					tVisExcelModuleDetail.setModuleId(Integer.parseInt(redirectMap.get("moduleId").toString()));
				}
			}
		}
		Page<TVisExcelModuleDetail> page = tVisExcelModuleDetailService
				.findPage(new Page<TVisExcelModuleDetail>(request, response), tVisExcelModuleDetail);
		page.setOrderBy("executeNo desc");
		model.addAttribute("page", page);
		return "modules/netconfig/tVisExcelModuleDetailList";
	}

	@RequiresPermissions("netconfig:excelTemplateDebug:view")
	@RequestMapping(value = { "showlist", "" })
	public String showlist(String excelId, HttpServletRequest request, HttpServletResponse response, Model model) {
		TVisExcelModuleDetail tVisExcelModuleDetail = new TVisExcelModuleDetail();
		tVisExcelModuleDetail.setExcelId(Integer.parseInt(excelId));
		Page<TVisExcelModuleDetail> page = tVisExcelModuleDetailService
				.findPage(new Page<TVisExcelModuleDetail>(request, response), tVisExcelModuleDetail);
		page.setOrderBy("executeNo desc");
		model.addAttribute("page", page);
		return "modules/netconfig/tVisExcelModuleDetailDebugList";
	}

	/**
	 * 进入模版调试页面
	 * 
	 * @param tVisExcelModuleDetail
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("netconfig:excelTemplateDebug:view")
	@RequestMapping(value = "debug")
	public String debug(@ModelAttribute("tVisExcelModuleDetail") TVisExcelModuleDetail tVisExcelModuleDetail,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TVisExcel> visExcelList = tVisExcelService.findList(new TVisExcel());
		model.addAttribute("visExcelList", visExcelList);
		return "modules/netconfig/tVisExcelModuleDetailDebug";
	}

	/**
	 * 获取模版调试程序执行的状态
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkCaptureStatus")
	public String checkCaptureStatus() {
		return TExcelTemplateDebugController.status;
	}

	@RequiresPermissions("netconfig:excelTemplateDebug:view")
	@RequestMapping(value = "showModuleDetail")
	public String showModuleDetail(int excelId, int moduleId, Model model,
			@ModelAttribute("tVisExcelModuleDetail") TVisExcelModuleDetail tVisExcelModuleDetail) {
		tVisExcelModuleDetail.setExcelId(excelId);
		tVisExcelModuleDetail.setModuleId(moduleId);

		TVisExcelModule tVisExcelModule = tVisExcelModuleService.get(moduleId + "");
		if (null != tVisExcelModule) {
			String result = "";
			result = DictUtils.getDictValue(tVisExcelModule.getNetType(), "biz_net_type", result);

			tVisExcelModuleDetail.setExecuteNo(0);
			List<TVisExcelModuleDetail> ltem = tVisExcelModuleDetailService.findParamList(tVisExcelModuleDetail);

			tVisExcelModuleDetail.setTemp_field2(ltem.size() + "");

			if (tVisExcelModule.getModuleType().equals("NE")) {
				TNewnetelement tnewnetelement = new TNewnetelement();
				tnewnetelement.setType(result);
				List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
				// 选择单网元
				model.addAttribute("list", list);
				return "modules/netconfig/tsingleNet";
			} else {
				// 选择多网元
				tVisExcelModuleDetail.setTemp_field3(tVisExcelModule.getNetType());
				return "modules/netconfig/tmoreNet";
			}
		} else {
			return "modules/netconfig/terrorTips";
		}
	}

	/**
	 * 模版调试的选择条件数据
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
			@RequestParam(required = false) Boolean isAll, @RequestParam(required = false) String parameterEpc,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if (null != parameterEpc && !parameterEpc.equals("")) {

			String result = "";
			result = DictUtils.getDictValue(parameterEpc, "biz_net_type", result);
			List<TPool> poolList = tPoolService.queryPoolListByType(parameterEpc);

			TNewnetelement tnewnetelement = new TNewnetelement();
			tnewnetelement.setType(result);
			List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);

			for (TPool pool : poolList) {
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "p" + pool.getId());
				mapParent.put("pId", "0");
				mapParent.put("pIds", "0");
				mapParent.put("name", pool.getFpoolname());
				mapList.add(mapParent);
			}

			for (int i = 0; i < list.size(); i++) {
				TNewnetelement e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", "p" + e.getFnid());
				map.put("pIds", "0," + "p" + e.getFnid());
				map.put("name", e.getFname());
				mapList.add(map);
			}
		}
		return mapList;
	}

	@RequestMapping(value = "showParam")
	public String showParam(int excelId, int moduleId, Model model,
			@ModelAttribute("tVisExcelModuleDetail") TVisExcelModuleDetail tVisExcelModuleDetail, String cardLocal) {
		tVisExcelModuleDetail.setExcelId(excelId);
		tVisExcelModuleDetail.setModuleId(moduleId);
		tVisExcelModuleDetail.setExecuteNo(0);
		List<TVisExcelModuleDetail> ltem = tVisExcelModuleDetailService.findParamList(tVisExcelModuleDetail);
		model.addAttribute("list", ltem);
		if (null != cardLocal && !"".equals(cardLocal)) {
			String[] localArray = cardLocal.split(",");
			model.addAttribute("cardLocal", localArray);
		}
		return "modules/netconfig/tVisParam";
	}

	/**
	 * 调试命令下发前获取模版信息和网元名称信息
	 * 
	 * @param params
	 * @param netId
	 * @param excelId
	 * @param moduleId
	 * @param moduleIndex
	 * @param isDebug
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sendCommandBefore")
	@ResponseBody
	public String sendCommandBefore(String params, String netId, String excelId, String moduleId,
			@RequestParam(required = false) Integer moduleIndex, Boolean isDebug, HttpServletRequest request) {
		status = "running";
		Map<String, Object> resultMap = new HashMap<>();
		// send
		StringBuilder sbNetName = new StringBuilder();
		String[] netArr = netId.split(",");
		HttpSession session = request.getSession();
		// 获取模板
		TVisExcelModule visExcelModule = new TVisExcelModule();
		visExcelModule.setExcelId(Integer.parseInt(excelId));
		// 获取模板下的所有模块,并存入session
		List<TVisExcelModule> excelModuleList = tVisExcelModuleService.findList(visExcelModule);
		resultMap.put("lastNetId", netId);
		if (null != moduleIndex && StringUtils.isNotBlank(String.valueOf(moduleIndex))
				&& moduleIndex < excelModuleList.size()) {
			resultMap.put("nextModuleId", excelModuleList.get(moduleIndex).getId());
		}

		Map<TNewnetelement, String> logMap = new HashMap<TNewnetelement, String>();
		TVisExcel te = tVisExcelService.get(excelId);
		TVisExcelModule tem = tVisExcelModuleService.get(moduleId);

		for (int netRemark = 0; netRemark < netArr.length; netRemark++) {
			TNewnetelement net = tnewnetelementService.get(netArr[netRemark]);
			sbNetName.append(net.getFname()).append(",");
			if (null != te && null != tem) {
				net.setTemp_field4(te.getName());
				net.setTemp_field5(tem.getModuleName());
				logMap.put(net, "");
			}
		}
		Map debugLog = (HashMap) session.getAttribute("debugLog");
		if (null != debugLog) {
			debugLog.clear();
		}
		session.setAttribute("debugLog", logMap);
		resultMap.put("netNames", sbNetName.toString());
		return JsonMapper.getInstance().toJson(resultMap);
	}

	/**
	 * 拼接下发命令，并将命令生成文本，发送给perl脚本
	 * 
	 * @param params
	 * @param netId
	 * @param excelId
	 * @param moduleId
	 * @param moduleIndex
	 * @param isDebug
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sendCommand")
	@ResponseBody
	public String sendCommand(String params, String netId, String excelId, String moduleId,
			@RequestParam(required = false) Integer moduleIndex, Boolean isDebug, HttpServletRequest request) {
		status = "running";
		// command
		TVisExcelModuleDetail tVisExcelModuleDetail = new TVisExcelModuleDetail();
		tVisExcelModuleDetail.setExcelId(Integer.parseInt(excelId));
		tVisExcelModuleDetail.setModuleId(Integer.parseInt(moduleId));
		List<TVisExcelModuleDetail> ltemd = tVisExcelModuleDetailService.findList(tVisExcelModuleDetail);
		TVisExcelModuleDetail temdTemp;
		boolean hasNext = false;
		StringBuffer sb = new StringBuffer();
		for (int m = 0; m < ltemd.size(); m++) {
			temdTemp = ltemd.get(m);
			
			//生产环境代码，调测模板不走这段
			if(!isDebug && !hasNext && temdTemp.getConfCmd().contains("SYSTEM-STOP")){
				hasNext = true;
				int sleepSecond = 20;
				String systemStop = temdTemp.getConfCmd();
				if(systemStop.indexOf("(")>=0 && systemStop.indexOf(")")>=0){
					sleepSecond = Integer.parseInt(systemStop.substring(systemStop.indexOf("(")+1,systemStop.indexOf(")")).trim());
				}
				sb.append(sleepSecond);
			}
			
		}

		// send
		StringBuilder sbNetName = new StringBuilder();
		String[] netArr = netId.split(",");
		HttpSession session = request.getSession();
		CountDownLatch latch = new CountDownLatch(netArr.length);
		for (int netRemark = 0; netRemark < netArr.length; netRemark++) {
			TNewnetelement net = tnewnetelementService.get(netArr[netRemark]);
			sbNetName.append(net.getFname()).append(",");
			ExecuteExcelTemplate eet = new ExecuteExcelTemplate(net, null, session, latch, excelId, moduleId);
			Thread eetT = new Thread(eet);
			eetT.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(sb.length()>0){
				status = sb.toString();
			}else{
				status = "finish";
			}
		}
		return status;
	}
	
	private void liuliang(TVisExcelModuleDetail temdTemp,String debugRemark,List<Object> jsonList){
		TVisExcelModuleDetailJson temdj = new TVisExcelModuleDetailJson();
		temdj.setDebugRemark(debugRemark);
		temdj.setConfCmd(temdTemp.getConfCmd());
		temdj.setBeforePrompt(temdTemp.getBeforePrompt());
		temdj.setAfterPrompt(temdTemp.getAfterPrompt());
		temdj.setCheckRegexp(temdTemp.getCheckRegexp());
		temdj.setTimeout(temdTemp.getTimeout());
		temdj.setErrorHandleMode(temdTemp.getErrorHandleMode());
		temdj.setVarArrayRegexp(temdTemp.getVarArrayRegexp());
		temdj.setVarArray(temdTemp.getVarArray());
		jsonList.add(temdj);
	}

	/**
	 * 写JSON数据到文件
	 * 
	 * @param content
	 * @param file
	 */
	public void writeToFile(List<Object> content, File file) {
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

	/**
	 * 查询设备返回的数据
	 * 
	 * @param netIds
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryProcessText")
	public String queryProcessText(String netIds, HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes redirectAttributes) {
		String[] netid_arr = netIds.split(",");
		List<CapText> list = new ArrayList<CapText>();
		HttpSession session = request.getSession();
		for (int n = 0; n < netid_arr.length; n++) {
			Queue<String> queue = (Queue<String>) session.getAttribute("capText_" + netid_arr[n]);
			CapText capText = new CapText();
			List<String> text = new ArrayList<String>();
			capText.setNetId(netid_arr[n]);
			int qsize = queue.size();
			if (queue != null && qsize > 0) {
				for (int i = 0; i < qsize; i++) {
					String aa = queue.poll();
					text.add(aa);
				}
				StringBuffer sb = new StringBuffer("");
				for (String s : text) {
					sb.append(s);
				}
				capText.setText(sb.toString().replaceAll("null", ""));
			} else {
				capText.setText("");
			}
			list.add(capText);
		}
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 读取详细数据
	 * 
	 * @param TElementcollect
	 * @return
	 */
	@RequestMapping(value = "queryModuleList")
	@ResponseBody
	public String queryModuleList(int excelId) {
		TVisExcelModule tVisExcelModule = new TVisExcelModule();
		tVisExcelModule.setExcelId(excelId);
		List<TVisExcelModule> tVisExcelModuleList = tVisExcelModuleService.findList(tVisExcelModule);
		return JsonMapper.getInstance().toJson(tVisExcelModuleList);
	}

	/**
	 * 读取详细数据
	 * 
	 * @param TElementcollect
	 * @return
	 */
	@RequestMapping(value = "queryExcelList")
	@ResponseBody
	public String queryExcelList(String type,String tpType) {
		TVisExcel tve=new TVisExcel();
		if(null!=type&&!"".equals(type)){
			tve.setType(type);
		}
		if(null!=tpType&&!"".equals(tpType)){
			tve.setTemplatetype(tpType);
		}
		List<TVisExcel> visExcelList = tVisExcelService.findList(tve);
		return JsonMapper.getInstance().toJson(visExcelList);
	}
	
	/**
	 * 进入模版内容修改或添加页面
	 * 
	 * @param tVisExcelModuleDetail
	 * @param model
	 * @return
	 */
	@RequiresPermissions("netconfig:excelTemplateDebug:view")
	@RequestMapping(value = "form")
	public String form(TVisExcelModuleDetail tVisExcelModuleDetail, Model model) {
		model.addAttribute("tVisExcelModuleDetail", tVisExcelModuleDetail);
		if (null == tVisExcelModuleDetail.getId() || "".equals(tVisExcelModuleDetail.getId())) {
			List<TVisExcel> visExcelList = tVisExcelService.findList(new TVisExcel());
			model.addAttribute("visExcelList", visExcelList);
			return "modules/netconfig/tVisExcelModuleDetailFormAdd";
		} else {
			return "modules/netconfig/tVisExcelModuleDetailForm";
		}
	}

	/**
	 * 保存修改或添加的数据
	 * 
	 * @param tVisExcelModuleDetail
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("netconfig:excelTemplateDebug:view")
	@RequestMapping(value = "save")
	public String save(TVisExcelModuleDetail tVisExcelModuleDetail, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tVisExcelModuleDetail)) {
			return form(tVisExcelModuleDetail, model);
		}
		tVisExcelModuleDetailService.save(tVisExcelModuleDetail);
		addMessage(redirectAttributes, "模版信息保存成功");
		redirectAttributes.addFlashAttribute("excelId", tVisExcelModuleDetail.getExcelId());
		redirectAttributes.addFlashAttribute("moduleId", tVisExcelModuleDetail.getModuleId());
		return "redirect:" + Global.getAdminPath() + "/netconfig/excelTemplateDebug/list";
	}

	/**
	 * 删除模版数据
	 * 
	 * @param tVisExcelModuleDetail
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("netconfig:excelTemplateDebug:view")
	@RequestMapping(value = "delete")
	public String delete(TVisExcelModuleDetail tVisExcelModuleDetail, RedirectAttributes redirectAttributes) {
		tVisExcelModuleDetailService.delete(tVisExcelModuleDetail);
		addMessage(redirectAttributes, "删除模版信息成功");
		return "redirect:" + Global.getAdminPath() + "/netconfig/excelTemplateDebug/debug";
	}

	private String filePath = Global.getConfig("excelTemplateDebugFilePath");

	/**
	 * 将设备返回的数据生成到文件，并将文件打成压缩包
	 * 
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("netconfig:excelTemplateDebug:view")
	@RequestMapping(value = "downloadZip")
	public void downLoadZip(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String resultPath = filePath + File.separator + UserUtils.getPrincipal().getId() + File.separator;
		File fileResult = new File(resultPath);
		if (!fileResult.exists()) {
			fileResult.mkdir();
		} else {
			File[] files = fileResult.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
		}
		Map<TNewnetelement, String> debugLogMap = (Map<TNewnetelement, String>) session.getAttribute("debugLog");
		if (null != debugLogMap) {
			Iterator iter = debugLogMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<TNewnetelement, String> entry = (Map.Entry<TNewnetelement, String>) iter.next();

				File fileTxt = new File(resultPath + entry.getKey().getFname() + ".txt");
				try {
					if (!fileTxt.exists()) {
						fileTxt.createNewFile();
					} else {
						fileTxt.delete();
						fileTxt.createNewFile();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				String content = "网元名称:" + entry.getKey().getFname() + "\r\n" + "模版名称:"
						+ entry.getKey().getTemp_field4() + "\r\n" + "模块名称:" + entry.getKey().getTemp_field5() + "\r\n"
						+ entry.getValue();
				writeToDebugLog(content, fileTxt);
			}

			File[] files = fileResult.listFiles();
			if (files != null) {
				String[] filePaths = new String[files.length];
				for (int i = 0; i < files.length; i++) {
					filePaths[i] = files[i].getAbsolutePath();
				}
				try {
					FileUtils.writeZipAndDownload(response, filePaths,
							"debuglog_" + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将执行生成的log文件写入到LOG文件中
	 * 
	 * @param content
	 * @param file
	 */
	public static void writeToDebugLog(String content, File file) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			if (file.exists()) {
				fw = new FileWriter(file, true);
				pw = new PrintWriter(fw);
				pw.println(content);
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

	/**
	 * 模版中自定义参数类
	 * 
	 * @author Administrator
	 *
	 */
	class Param {
		private String confCmd;
		private String varArray;

		public String getConfCmd() {
			return confCmd;
		}

		public void setConfCmd(String confCmd) {
			this.confCmd = confCmd;
		}

		public String getVarArray() {
			return varArray;
		}

		public void setVarArray(String varArray) {
			this.varArray = varArray;
		}
	}

}