package com.thinkgem.jeesite.modules.netconfig.web;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SSHUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.entity.CardInfoSaegw;
import com.thinkgem.jeesite.modules.netconfig.entity.THwStatus;
import com.thinkgem.jeesite.modules.netconfig.entity.THwStatusSaegw;
import com.thinkgem.jeesite.modules.netconfig.entity.TLockTemplate;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TNodeInfo;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetail;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisNetparamEpg;
import com.thinkgem.jeesite.modules.netconfig.entity.VisNodes;
import com.thinkgem.jeesite.modules.netconfig.service.TEquipmentStatusScheduledService;
import com.thinkgem.jeesite.modules.netconfig.service.TLockTemplateService;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TNodeInfoService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleDetailService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisNetparamEpgService;
import com.thinkgem.jeesite.modules.netconfig.service.VisNodesService;

/**
 * SAEGW节点管理Controller
 * @author zhuguangrui
 * @version 2018-03-30
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tSaegw")
public class TSaegwController {
	
	public static final String NODE_CACHE = "nodeCache";
	
	public static final String NODE_CRASH = "nodeCrash";
	
	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@Autowired
	private TEquipmentStatusScheduledService equipmentStatusScheduledService;
	
	@Autowired
	private VisNodesService visNodesService;
	
	@Autowired
	private TNodeInfoService nodeInfoService;
	
	@Autowired
	private TVisExcelService tVisExcelService;
	
	@Autowired
	private TVisNetparamEpgService visNetparamEpgService;
	
	@Autowired
	private TVisExcelModuleService tVisExcelModuleService;
	
	@Autowired
	private TVisExcelModuleDetailService tVisExcelModuleDetailService;
	
	@Autowired
	private TLockTemplateService tlockTemplateService;
	
	
	@RequiresPermissions("netconfig:tSaegw:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, String nodeid,String netid,String netType) {
		if(netid!=null&&!netid.equals("")) {
			nodeid = visNodesService.findnodeidByNidAndType(netid,netType);
		}
		//model.addAttribute("nodeid", nodeid);
		VisNodes visNodes = visNodesService.get(nodeid);
		List<THwStatusSaegw> list = new ArrayList<THwStatusSaegw>();
		List<String> crashlist = new ArrayList<String>();
		TNewnetelement tNewnetelement = null;
		if(null != visNodes) {
			tNewnetelement = tNewnetelementService.get(String.valueOf(visNodes.getOid()));
		} else {
			tNewnetelement = tNewnetelementService.get(netid);
		}
		if ("2".equals(tNewnetelement.getType())) {
			
			//getType=2代表网元类型为SAEGW
			Map<String, String> cpuMap = new HashMap<String, String>();
			Map<String, String> memoryMap = new HashMap<String, String>();
			Map<String, String> pgwUserCountMap = new HashMap<String, String>();
			Map<String, String> sgwUserCountMap = new HashMap<String, String>();
			Map<String,THwStatusSaegw> cardMap = new HashMap<String,THwStatusSaegw>();
			
			list = getHwStatus(tNewnetelement);
			crashlist = getCrash(tNewnetelement);
			Map<String,CardInfoSaegw> boardInfo = getBoardInfo(tNewnetelement);
			
			if(null != list) {
				for(THwStatusSaegw statusSaegw : list){
					if(boardInfo.containsKey(statusSaegw.getSlot())){
						statusSaegw.setCardInfo(boardInfo.get(statusSaegw.getSlot()));
					}
				}
				for(THwStatusSaegw saegw : list) {
					for(int i=0;i<crashlist.size();i++) {
						if(crashlist.get(i).equals(saegw.getSlot())) {
							saegw.setCrash("i");
						}
					}
					String name = saegw.getInstalledType();
					/*String adminState = saegw.getAdminState();
					String operState = saegw.getOperationalState();*/
					if(name.indexOf("10ge")> -1 && name.indexOf("10")> -1 && name.indexOf("port")> -1) {
						saegw.setInstalledType("10-port-10ge");
					}
					if(name.indexOf("ge")> -1 && name.indexOf("40")> -1 && name.indexOf("port")> -1) {
						saegw.setInstalledType("40-port-ge");
					}
					if(name.indexOf("40ge")> -1 && name.indexOf("2")> -1 && name.indexOf("port")> -1) {
						saegw.setInstalledType("2-port-40ge");
					}
					if(name.indexOf("ssc") > -1) {
						saegw.setInstalledType("ssc");
					}
					if(name.indexOf("rpsw") > -1) {
						saegw.setInstalledType("rpsw");
					}
					
					//判断指示灯颜色
					if("IS".equals(saegw.getOperationalState()) && "In Service".equals(saegw.getAdminState())) {
						saegw.setColor("green");
					} else {
						saegw.setColor("yellow");
					}
					
					if(null != saegw.getCardInfo()) {
						cpuMap.put(saegw.getSlot(), saegw.getCardInfo().getCpu());
						memoryMap.put(saegw.getSlot(), saegw.getCardInfo().getMemory());
						pgwUserCountMap.put(saegw.getSlot(), saegw.getCardInfo().getPgwUserCount());
						sgwUserCountMap.put(saegw.getSlot(), saegw.getCardInfo().getSgwUserCount());
					}
					cardMap.put("card_"+saegw.getSlot(), saegw);
				}
			}
			
			//model.addAttribute("THwStatusSaegw", list);
			model.addAttribute("cpuMap", JsonMapper.getInstance().toJson(cpuMap));
			model.addAttribute("memoryMap", JsonMapper.getInstance().toJson(memoryMap));
			model.addAttribute("pgwUserCountMap", JsonMapper.getInstance().toJson(pgwUserCountMap));
			model.addAttribute("sgwUserCountMap", JsonMapper.getInstance().toJson(sgwUserCountMap));
			model.addAttribute("cardMap", cardMap);
			model.addAttribute("cardMapJson", JsonMapper.getInstance().toJson(cardMap));
			model.addAttribute("netId",tNewnetelement.getId());
			model.addAttribute("nodeid",nodeid);
			
			
			TVisNetparamEpg visNetparamEpg = visNetparamEpgService.get(tNewnetelement.getId());
			if(visNetparamEpg != null) {
				visNetparamEpg.setNetName(tNewnetelement.getFname());
			}
			model.addAttribute("netParam", visNetparamEpg);
			
		}
		return "modules/netconfig/tSaegwConfigList";
	}
	
	private List<THwStatusSaegw> getHwStatus(TNewnetelement tNewnetelement) {
		List<THwStatusSaegw> hwStatusList = new ArrayList<THwStatusSaegw>();
		BufferedReader br = null;
		try {
			br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_hw_status");
			String line = null;
			while((line = br.readLine())!=null){
				THwStatusSaegw hwSaegw = new THwStatusSaegw();
				String[] item = line.split("\\s+");
				hwSaegw.setSlot(item[0]);
				hwSaegw.setConfiguredType(item[1]);
				hwSaegw.setInstalledType(item[2]);
				hwSaegw.setOperationalState(item[3]);
				hwSaegw.setAdminState(item[4] + " " + item[5]);
				hwStatusList.add(hwSaegw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return hwStatusList;
	}
	
	public BufferedReader readFile(String path) {
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			fileInputStream = new FileInputStream(path);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reader;
	}


	/*private List<THwStatusSaegw> getHwStatus(TNewnetelement tNewnetelement) {
		if(CacheUtils.get(NODE_CACHE, "hwStatus_"+tNewnetelement.getId())!=null){
			return (List<THwStatusSaegw>) CacheUtils.get(NODE_CACHE, "hwStatus_"+tNewnetelement.getId());
		}
		SSHUtil ssh = null;
		List<THwStatusSaegw> hwStatusList = null;
		try {
			ssh = new SSHUtil(tNewnetelement.getIpadr(), tNewnetelement.getUsername1(),tNewnetelement.getPassword1());
			ssh.connect();
			hwStatusList = equipmentStatusScheduledService.nodeLocationSaegw(ssh);
			CacheUtils.put(NODE_CACHE, "hwStatus_"+tNewnetelement.getId(), hwStatusList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ssh.disconnect();
		}
		return hwStatusList;
	}*/
	
	private List<String> getCrash(TNewnetelement tNewnetelement) {
		return new ArrayList<String>();
	}
	
	private Map<String,CardInfoSaegw> getBoardInfo(TNewnetelement tNewnetelement) {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		Map<String,CardInfoSaegw> cardMap = null;
		try {
			br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_hw_card");
			String line = null;
			while((line = br.readLine())!=null){
				sb.append(line + "\r\n");
			}
			String sshResult = sb.toString();
			if(StringUtils.isNotBlank(sshResult)){
				String[] temp = sshResult.split("board-information:");
				cardMap = new HashMap<String,CardInfoSaegw>();
				CardInfoSaegw cardInfo = null;
				for(String cardElement : temp){
					if(!cardElement.contains("board")){
						continue;
					}
					String[] lineArr = cardElement.split("\n");
					String[] functionArr = cardElement.split("function:");
					for(String item : lineArr){
						if(item.contains("board:")){
							cardInfo = new CardInfoSaegw();
							cardInfo.setBoardInfo("    board-information:\n" + cardElement);
							String[] info = item.split(":");
							String[] cardLocAndType = info[1].split("/");
							if(cardLocAndType.length>=2){
								if(cardLocAndType[0].toLowerCase().contains("c")){
									cardInfo.setCardType("C");
								}else if(cardLocAndType[0].toLowerCase().contains("u")){
									cardInfo.setCardType("U");
								}
								cardMap.put(cardLocAndType[1], cardInfo);
								continue;
							}
						}
						if(item.contains("cpu-utilization") && item.contains("Peak")){
							cardInfo.setCpu(item.substring(item.indexOf("Peak")+4,item.indexOf(",")).replace("%", "").trim());
							continue;
						}
						if(item.contains("memory") && !item.contains("estimated-free-memory")){
							NumberFormat nf = NumberFormat.getNumberInstance();
							nf.setMaximumFractionDigits(0);
							cardInfo.setMemory(nf.format(Double.valueOf(item.substring(item.indexOf("Used")+4).trim())
									/Double.valueOf(item.substring(item.indexOf("Total")+5,item.indexOf(",")).trim())*100));
							continue;
						}
					}
					for(String func : functionArr){
						if(cardInfo != null && func.contains("PGW") && func.contains("number-of-bearers:") ){
							int beginIndex = func.indexOf("number-of-bearers:") + "number-of-bearers:".length();
							cardInfo.setPgwUserCount(func.substring(beginIndex,func.indexOf("\n", beginIndex)).trim());
							continue;
						}
						if(cardInfo != null && func.contains("SGW") && func.contains("number-of-bearers:")){
							int beginIndex = func.indexOf("number-of-bearers:") + "number-of-bearers:".length();
							cardInfo.setSgwUserCount(func.substring(beginIndex,func.indexOf("\n", beginIndex)).trim());
							continue;
						}
					}
				}
			}
		} catch (Exception e) {
			
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return cardMap;
	}
	
	/*private Map<String,CardInfoSaegw> getBoardInfo(TNewnetelement tNewnetelement) {
		if(CacheUtils.get(NODE_CACHE, "boardInfo_"+tNewnetelement.getId())!=null){
			return (Map<String,CardInfoSaegw>) CacheUtils.get(NODE_CACHE, "boardInfo_"+tNewnetelement.getId());
		}
		SSHUtil ssh = null;
		Map<String,CardInfoSaegw> boardInfoMap = null;
		try {
			ssh = new SSHUtil(tNewnetelement.getIpadr(), tNewnetelement.getUsername1(),tNewnetelement.getPassword1());
			ssh.connect();
			boardInfoMap = equipmentStatusScheduledService.nodeCardInfoSaegw(ssh);
			CacheUtils.put(NODE_CACHE, "boardInfo_"+tNewnetelement.getId(), boardInfoMap);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ssh.disconnect();
		}
		return boardInfoMap;
	}*/
	
	@RequiresPermissions("netconfig:tSaegw:view")
	@RequestMapping(value = "showLog")
	public String showLog(String netId,String cmdType,String searchType,String dateTime, Model model) {
		List<String> logList =new ArrayList<String>();
		if("1".equals(searchType)){//历史
			TNodeInfo nodeInfo = new TNodeInfo();
			nodeInfo.setNetId(Integer.valueOf(netId));
			nodeInfo.setDateTime(dateTime);
			List<TNodeInfo> list = nodeInfoService.findList(nodeInfo);
			if(list != null && list.size()>0){
				TNodeInfo dbNodeInfo = list.get(0);
				switch(cmdType){
					case("status"):
						String status = dbNodeInfo.getEciStat();
						if(StringUtils.isNotBlank(status)){
							String[] logDetail = status.split("\n");
							for(String str : logDetail) {
								if(!"".equals(str.trim())){
									logList.add(str);
								}
							}
						}else{
							logList.add("未查询到节点状态信息");
						}
					break;
					case("kpi"):
						String kpi = dbNodeInfo.getPdcKpi();
						if(StringUtils.isNotBlank(kpi)){
							String[] logDetail = kpi.split("\n");
							for(String str : logDetail) {
								if(!"".equals(str.trim())){
									logList.add(str);
								}
							}
						}else{
							logList.add("未查询到节点性能指标");
						}
					break;
					case("health"):
						String health = dbNodeInfo.getHealthcheck();
						if(StringUtils.isNotBlank(health)){
							String[] logDetail = health.split("\n");
							for(String str : logDetail) {
								if(!"".equals(str.trim())){
									logList.add(str);
								}
							}
						}else{
							logList.add("未查询到节点健康检查结果");
						}
					break;
					case("alarm"):
						String alarm = dbNodeInfo.getListAlarms();
						if(StringUtils.isNotBlank(alarm)){
							String[] logDetail = alarm.split("\n");
							for(String str : logDetail) {
								if(!"".equals(str.trim())){
									logList.add(str);
								}
							}
						}else{
							logList.add("未查询到节点告警信息");
						}
					break;
					case("config"):
						String config = dbNodeInfo.getListParamConf();
						if(StringUtils.isNotBlank(config)){
							String[] logDetail = config.split("\n");
							for(String str : logDetail) {
								if(!"".equals(str.trim())){
									logList.add(str);
								}
							}
						}else{
							logList.add("未查询到节点配置信息");
						}
					break;
				}
			}else{
				logList.add("未查询到节点相关信息");
			}
		}else if("0".equals(searchType)){//当前
			BufferedReader br = null;
			String line = null;
			try {
				switch(cmdType){
				case("status"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_status");
					while((line = br.readLine())!=null){
						logList.add(line);
					}
					break;
				case("kpi"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_kpi");
					while((line = br.readLine())!=null){
						logList.add(line);
					}					
					break;
				case("health"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_health");
					while((line = br.readLine())!=null){
						logList.add(line);
					}					
					break;
				case("alarm"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_alarm");
					while((line = br.readLine())!=null){
						logList.add(line);
					}					
					break;
				case("config"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_config");
					while((line = br.readLine())!=null){
						logList.add(line);
					}
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				logList.add("获取信息异常，请检查网络是否正常，登录网元的ssh账号密码是否正确");
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("logList", logList);
		return "modules/netconfig/tMmeConfigLog";
	}
	
	@RequiresPermissions("netconfig:tSaegw:view")
	@RequestMapping(value = "refresh")
	public String refresh(HttpServletRequest request, HttpServletResponse response, Model model, String nodeid,String netId) {
		
		CacheUtils.remove(NODE_CACHE, "hwStatus_"+netId);
		CacheUtils.remove(NODE_CACHE, "boardInfo_"+netId);
		return this.list(request, response, model, nodeid,null,null);
	}
	
	@RequestMapping(value = "showNodeCardMemory")
	@ResponseBody
	public String showNodeCardMemory(String netid,String cardeq) {
		BufferedReader br = null;
		String line = null;
		List<String> result = new ArrayList<String>();
		try {
			br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_memory");
			while((line = br.readLine())!=null){
				if(line.contains("Slot")) {
					String[] item = line.split(":");
					String slot = item[1].trim();
					slot = slot.substring(0, slot.indexOf("/"));
					if(cardeq.equals(slot)) {
						result.add(line);
						line = br.readLine();
						result.add(line);
						line = br.readLine();
						result.add(line);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JsonMapper.getInstance().toJson(result);
	}
	
	@RequestMapping(value = "showNodeCardDisk")
	@ResponseBody
	public String showNodeCardDisk(String netid,String cardeq) {
		BufferedReader br = null;
		String line = null;
		List<String> result = new ArrayList<String>();
		try {
			br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/saegw_card");
			while((line = br.readLine())!=null){
				if(line.contains("Slot")) {
					String[] item = line.split(":");
					String slot = item[1].trim();
					slot = slot.substring(0, slot.indexOf("/"));
					if(cardeq.equals(slot)) {
						result.add(line);
						while((line = br.readLine())!=null) {
							if(!line.contains("--------")) {
								result.add(line);
							} else {
								break;
							}
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JsonMapper.getInstance().toJson(result);
	}
	
	/*@RequestMapping(value = "showNodeCardDisk")
	@ResponseBody
	public String showNodeCardDisk(String netid,String cardeq) {
		TNewnetelement tNewnetelement = tNewnetelementService.get(netid);
		String ip = tNewnetelement.getIpadr();
		String username = tNewnetelement.getUsername1();
		String password = tNewnetelement.getPassword1();
		System.out.println(ip);
		System.out.println(username);
		System.out.println(password);
		SSHUtil ssh = new SSHUtil(ip,username,password);
		System.out.println(ssh);
		List<String> result = null;
		try {
			result = equipmentStatusScheduledService.nodeCardDisk(ssh, cardeq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ssh.disconnect();
		}
		return JsonMapper.getInstance().toJson(result);
	}*/
	
	@RequestMapping(value = "nodeCardStatus")
	@ResponseBody
	public String nodeCardStatus(String netid,String cardeq,String type) {
		TNewnetelement tNewnetelement = tNewnetelementService.get(netid);
		String ip = tNewnetelement.getIpadr();
		String username = tNewnetelement.getUsername1();
		String password = tNewnetelement.getPassword1();
		SSHUtil ssh = new SSHUtil(ip,username,password);
		List<String> result = null;
		try {
			switch(type){
			case "port":result = equipmentStatusScheduledService.nodeCardStatus_port(ssh, cardeq);break;
			case "rpsw":result = equipmentStatusScheduledService.nodeCardStatus_rpsw(ssh, cardeq);break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ssh.disconnect();
		}
		return JsonMapper.getInstance().toJson(result);
	}
	
	@RequestMapping(value = "showLockTemp")
	public String showLockTemp(Model model, String tempId,String netId,String cardLocal) {
		
		TVisExcel visExcel = tVisExcelService.get(tempId);
		TVisExcelModule excelModule = new TVisExcelModule();
		excelModule.setExcelId(Integer.parseInt(tempId));
		List<TVisExcelModule> excelModuleList = tVisExcelModuleService.findList(excelModule);
		model.addAttribute("moduleList", excelModuleList);
		Map<String,List<TVisExcelModuleDetail>> detailMap = new HashMap<String, List<TVisExcelModuleDetail>>();
		//查询关联module detail 对象
		for(TVisExcelModule excel : excelModuleList) {
			TVisExcelModuleDetail detail = new TVisExcelModuleDetail();
			detail.setExcelId(excel.getExcelId());
			detail.setModuleId(Integer.parseInt(excel.getId()));
			List<TVisExcelModuleDetail> detailList = tVisExcelModuleDetailService.findList(detail);
			detailMap.put("d_"+excel.getExcelId()+"_"+excel.getId(), detailList);
		}
		model.addAttribute("cardLocal",cardLocal.replace(".", ","));
		model.addAttribute("netId",netId);
		model.addAttribute("excelId",tempId);
		model.addAttribute("excelName", visExcel.getName());
		model.addAttribute("detailMap", JsonMapper.getInstance().toJson(detailMap));
		
		return "modules/netconfig/tExcelTemplate";
	}
	
	@RequestMapping(value = "checkLockTemp")
	@ResponseBody
	public String checkLockTemp( String cardName,String tempType) {
		
		TLockTemplate tLockTemplate = new TLockTemplate();
		tLockTemplate.setType("SAEGW");
		tLockTemplate.setTemp_field1(cardName);
		List<TLockTemplate> lockTemplates = tlockTemplateService.findList(tLockTemplate);
		if(lockTemplates != null && lockTemplates.size() > 0){
			TLockTemplate lockTemplate = lockTemplates.get(0);
			Integer tempId = 0;
			if("lock".equals(tempType)){
				tempId = lockTemplate.getLockId();
			}
			if("unlock".equals(tempType)){
				tempId = lockTemplate.getUnLockId();
			}
			if("reboot".equals(tempType)){
				tempId = lockTemplate.getReBootId();
			}
			if(tempId != null && tempId > 0){
				return tempId+"";
			}else{
				return "empty";
			}
		}else{
			return "empty";
		}
	}
	
	public static void main(String[] args) {
		String line = "Slot number       : 12/LP";
		String[] item = line.split(":");
		String slot = item[1].trim();
		slot = slot.substring(0, slot.indexOf("/"));
		System.out.println(slot);
	}
	
}
