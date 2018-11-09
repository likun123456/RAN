package com.thinkgem.jeesite.modules.netconfig.web;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcraft.jsch.JSchException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.entity.InputStreamChannelExec;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SSHUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.entity.CardInfo;
import com.thinkgem.jeesite.modules.netconfig.entity.TEpcDic;
import com.thinkgem.jeesite.modules.netconfig.entity.THwStatus;
import com.thinkgem.jeesite.modules.netconfig.entity.TLockTemplate;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TNodeInfo;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetail;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisNetparamMme;
import com.thinkgem.jeesite.modules.netconfig.entity.VisNodes;
import com.thinkgem.jeesite.modules.netconfig.service.TEpcDicService;
import com.thinkgem.jeesite.modules.netconfig.service.TEquipmentStatusScheduledService;
import com.thinkgem.jeesite.modules.netconfig.service.TLockTemplateService;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TNodeInfoService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleDetailService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelModuleService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisNetparamMmeService;
import com.thinkgem.jeesite.modules.netconfig.service.VisNodesService;

/**
 * MME节点管理Controller
 * @author wangjingshi
 * @version 2018-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tMme")
public class TMmeController {
	
	public static final String NODE_CACHE = "nodeCache";
	
	public static final String USER_COUNT_2G ="eci dist | awk '/Distribution of SGSN-MME-G connections over GPBs:/,/Distribution of SGSN-MME-W connections over GPBs:/' | grep '%' | awk '{print $1, $2+ $3}' | sed 's/.2.1 / /g'";
	
	public static final String USER_COUNT_3G ="eci dist | awk '/Distribution of SGSN-MME-W connections over GPBs:/,/Distribution of MME connections over GPBs:/' | grep '%' | awk '{print $1, $2+ $3}' | sed 's/.2.1 / /g'";
	
	public static final String USER_COUNT_4G ="eci dist | awk '/Distribution of MME connections over GPBs:/,/Distribution of MBMS connections over GPBs:/' | grep '%' | awk '{print $1, $2}' | sed 's/.2.1 / /g'";
	
	public static final String CPU_STATUS = "gsh get_eq_cpu_load | sed 's/\\_[0-9]//g'";
	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@Autowired
	private TNodeInfoService nodeInfoService;
	
	@Autowired
	private TEquipmentStatusScheduledService equipmentStatusScheduledService;
	
	@Autowired
	private VisNodesService visNodesService;
	
	@Autowired
	private TVisNetparamMmeService visNetparamMmeService;
	
	@Autowired
	private TVisExcelService tVisExcelService;
	
	@Autowired
	private TVisExcelModuleService tVisExcelModuleService;
	
	@Autowired
	private TVisExcelModuleDetailService tVisExcelModuleDetailService;
	
	@Autowired
	private TLockTemplateService tlockTemplateService;
	
	@Autowired
	private TEpcDicService tEpcDicService;
	
	@RequiresPermissions("netconfig:tMme:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, String nodeid,String netid,String netType) {
		if(netid!=null&&!netid.equals("")) {
			nodeid = visNodesService.findnodeidByNidAndType(netid,netType);
		}
		VisNodes visNodes = visNodesService.get(nodeid);
		TNewnetelement tNewnetelement = null;
		if(null != visNodes) {
			tNewnetelement = tNewnetelementService.get(String.valueOf(visNodes.getOid()));
		} else {
			tNewnetelement = tNewnetelementService.get(netid);
		}
		if ("1".equals(tNewnetelement.getType())) {
			//getType=1代表网元类型为MME
			List<THwStatus> list = getHwStatus(tNewnetelement);
			Map<String, THwStatus> m1 = new LinkedHashMap<String, THwStatus>();
			Map<String, THwStatus> m2 = new LinkedHashMap<String, THwStatus>();
			Map<String, THwStatus> m3 = new LinkedHashMap<String, THwStatus>();
			if("MKVIII".equals(tNewnetelement.getHwtype())) {
				m1 = initCard("1","MKVIII");
				m2 = initCard("2","MKVIII");
				m3 = initCard("3","MKVIII");
				model.addAttribute("hwtype", "MKVIII");
				
			} else if("MKX".equals(tNewnetelement.getHwtype())) {
				m1 = initCard("1","MKX");
				m2 = initCard("2","MKX");
				m3 = initCard("3","MKX");
				model.addAttribute("hwtype", "MKX");
				
			}
			Map<String,Object> userCount = initUserCount(tNewnetelement);
			Map<String,Object> cpuInfo = initCpuInfo(tNewnetelement);
			model.addAttribute("cardMaxCpuMap", JsonMapper.getInstance().toJson(cpuInfo.get("cardMaxCpuMap")));
			model.addAttribute("cardUserCount", JsonMapper.getInstance().toJson(userCount.get("cardUserCount")));
			model.addAttribute("totalUserCount", userCount.get("totalUserCount"));
			for (THwStatus status : list) {
				/*if(cpuInfo != null && cpuInfo.containsKey("cardCpuMap")){
					initCardInfo(userCount,(Map<String, Map<String, String>>) cpuInfo.get("cardCpuMap"),status);
				}*/
				String prodName = status.getProdName().replaceAll("-", "_").replaceAll("/", "_");
				if (prodName.indexOf(" ") > 0) {
					prodName = prodName.substring(0, prodName.indexOf(" "));
				}
				
				if(!"GEP5".equals(prodName) && "MKX".equals(tNewnetelement.getHwtype()) && prodName.contains("GEP5")) {
					prodName = prodName.substring(0, prodName.indexOf("_"));
				}
				status.setProdName(prodName);
				if (status.getEql() == 1) {
					m1.put("m" + status.getEql() + "_" + status.getEqr(), status);
				} else if (status.getEql() == 2) {
					m2.put("m" + status.getEql() + "_" + status.getEqr(), status);
				} else if (status.getEql() == 3) {
					m3.put("m" + status.getEql() + "_" + status.getEqr(), status);
				}
			}
			Map<String, Map<String, THwStatus>> cardMap = new HashMap<String, Map<String, THwStatus>>();
			cardMap.put("m1", m1);
			cardMap.put("m2", m2);
			cardMap.put("m3", m3);
			model.addAttribute("card", cardMap);
			
			TVisNetparamMme visNetparamMme = visNetparamMmeService.get(tNewnetelement.getId());
			if(null != visNetparamMme) {
				visNetparamMme.setNetName(tNewnetelement.getFname());
				visNetparamMme.setMmec(String.valueOf(Integer.parseInt(visNetparamMme.getMmec(), 16)));
			}
			
			model.addAttribute("netParam", visNetparamMme);
			model.addAttribute("netId",tNewnetelement.getId());
			model.addAttribute("nodeid",nodeid);
			Map<String, String> memoryMap = getMemoryInfo(tNewnetelement);
			model.addAttribute("memoryMap", JsonMapper.getInstance().toJson(memoryMap));
		}
		return "modules/netconfig/tMmeConfigList";
	}
	
	
	private Map<String, Object> initCpuInfo(TNewnetelement tNewnetelement) {
		Map<String,Object> cpuInfoMap = new HashMap<String,Object>();
		Map<String,Double> cardMaxCpuMap = new HashMap<String,Double>();
		if("MKVIII".equals(tNewnetelement.getHwtype())) {	
			cardMaxCpuMap.put("1.1", 26.0);
			cardMaxCpuMap.put("1.3", 27.0);
			cardMaxCpuMap.put("1.5", 29.0);
			cardMaxCpuMap.put("1.7", 28.0);
			cardMaxCpuMap.put("2.1", 26.0);
			cardMaxCpuMap.put("2.3", 25.0);
			cardMaxCpuMap.put("2.5", 30.0);
			cardMaxCpuMap.put("2.7", 26.0);
			cardMaxCpuMap.put("2.9", 29.0);
			cardMaxCpuMap.put("2.11", 27.0);
			cardMaxCpuMap.put("3.1", 30.0);
			cardMaxCpuMap.put("3.3", 26.0);
			cardMaxCpuMap.put("3.5", 28.0);
			cardMaxCpuMap.put("3.7", 29.0);
			cardMaxCpuMap.put("3.9", 25.0);
			cardMaxCpuMap.put("3.11", 26.0);
			cardMaxCpuMap.put("3.13", 26.0);
			cardMaxCpuMap.put("3.15", 28.0);
			cardMaxCpuMap.put("3.17", 26.0);
			cardMaxCpuMap.put("3.19", 28.0);
			cardMaxCpuMap.put("3.21", 30.0);
			cardMaxCpuMap.put("3.23", 31.0);
		} else if("MKX".equals(tNewnetelement.getHwtype())) {
			cardMaxCpuMap.put("1.1", 26.0);
			cardMaxCpuMap.put("1.3", 27.0);
			cardMaxCpuMap.put("1.5", 29.0);
			cardMaxCpuMap.put("1.7", 28.0);
			cardMaxCpuMap.put("2.1", 26.0);
			cardMaxCpuMap.put("2.3", 25.0);
			cardMaxCpuMap.put("2.5", 30.0);
			cardMaxCpuMap.put("2.7", 26.0);
		}
		
		cpuInfoMap.put("cardMaxCpuMap", cardMaxCpuMap);
		return cpuInfoMap;
	}




	private void initCardInfo(Map<String, Object> userCount,Map<String,Map<String,String>> cpuInfo, THwStatus status) {
		CardInfo cardInfo = new CardInfo();
		if(userCount != null){
			Map<String,Integer> userCount2GMap = (Map<String, Integer>) userCount.get("userCount2G");
			Map<String,Integer> userCount3GMap = (Map<String, Integer>) userCount.get("userCount3G");
			Map<String,Integer> userCount4GMap = (Map<String, Integer>) userCount.get("userCount4G");
			
			if(userCount2GMap != null && userCount2GMap.containsKey(status.getEqlAndEqr())){
				cardInfo.setUserCount2G(userCount2GMap.get(status.getEqlAndEqr()));
			}
			if(userCount3GMap != null && userCount3GMap.containsKey(status.getEqlAndEqr())){
				cardInfo.setUserCount3G(userCount3GMap.get(status.getEqlAndEqr()));
			}
			if(userCount4GMap != null && userCount4GMap.containsKey(status.getEqlAndEqr())){
				cardInfo.setUserCount4G(userCount4GMap.get(status.getEqlAndEqr()));
			}
		}
		if(cpuInfo != null){
			if(cpuInfo.containsKey("AP") && cpuInfo.get("AP").containsKey(status.getEqlAndEqr())){
				cardInfo.setCpuAP(cpuInfo.get("AP").get(status.getEqlAndEqr()));
			}
			if(cpuInfo.containsKey("DP") && cpuInfo.get("DP").containsKey(status.getEqlAndEqr())){
				cardInfo.setCpuDP(cpuInfo.get("DP").get(status.getEqlAndEqr()));
			}
			if(cpuInfo.containsKey("NCB") && cpuInfo.get("NCB").containsKey(status.getEqlAndEqr())){
				cardInfo.setCpuNCB(cpuInfo.get("NCB").get(status.getEqlAndEqr()));
			}
			if(cpuInfo.containsKey("RP") && cpuInfo.get("RP").containsKey(status.getEqlAndEqr())){
				cardInfo.setCpuRP(cpuInfo.get("RP").get(status.getEqlAndEqr()));
			}
			if(cpuInfo.containsKey("SS7_FE_NB") && cpuInfo.get("SS7_FE_NB").containsKey(status.getEqlAndEqr())){
				cardInfo.setCpuSS7FENB(cpuInfo.get("SS7_FE_NB").get(status.getEqlAndEqr()));
			}
			if(cpuInfo.containsKey("SS7_SCTP_DP") && cpuInfo.get("SS7_SCTP_DP").containsKey(status.getEqlAndEqr())){
				cardInfo.setCpuSS7SCTPDP(cpuInfo.get("SS7_SCTP_DP").get(status.getEqlAndEqr()));
			}
		}
		status.setCardInfo(cardInfo);
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
	
	private List<THwStatus> getHwStatus(TNewnetelement tNewnetelement) {
		List<THwStatus> hwStatusList = new ArrayList<THwStatus>();
		BufferedReader br = null;
		try {
			String command = "";
			if("MKVIII".equals(tNewnetelement.getHwtype())) {
				command = "D:/apache-tomcat-8.0.46-PC/commandData/mme_hw_status";
			} else if("MKX".equals(tNewnetelement.getHwtype())) {
				command = "D:/apache-tomcat-8.0.46-PC/commandData/mmemkx_hw_status";
			}
			
			br = readFile(command);
			String line = null;
			while((line = br.readLine())!=null){
				THwStatus hw = new THwStatus();
				hw.setNetID(31); 
				hw.setDateTime(DateUtils.getDate());
				String[] item = line.split("\\s+");
				hw.setEql(Integer.parseInt(item[0]));
				hw.setEqr(Integer.parseInt(item[1]));
				hw.setEqlAndEqr(hw.getEql() + "." + hw.getEqr());
				hw.setEqClass(item[2]);
				hw.setType(item[3]);
				hw.setAdminState(item[4]);
				hw.setOperState(item[5]);
				hw.setPowerState(item[6]);
				hw.setRevision(item[7]);
				hw.setBootRom(item[8]);
				hw.setProdNo(item[9] + " " + item[10] + " " + item[11]);
				hw.setProdName(item[12]);
				hw.setManWeek(item[13]);
				hw.setSerialNo(item[14]);
				hw.setFsbRole(item[15]);
				hwStatusList.add(hw);
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
	
	
	private Map<String,Object> initUserCount(TNewnetelement tNewnetelement) {
		Map<String,Object> userCount = new HashMap<String,Object>();
		Map<String,Integer> userCountMap = new HashMap<String,Integer>();
		
		
		
		if("MKVIII".equals(tNewnetelement.getHwtype())) {	
			userCountMap.put("1.1", 26145);
			userCountMap.put("1.3", 26219);
			userCountMap.put("1.5", 26127);
			userCountMap.put("1.7", 26276);
			userCountMap.put("1.9", 0);
			userCountMap.put("1.15", 0);
			
			userCountMap.put("2.1", 26316);
			userCountMap.put("2.3", 26478);
			userCountMap.put("2.5", 25097);
			userCountMap.put("2.7", 25480);
			userCountMap.put("2.9", 24995);
			userCountMap.put("2.11", 25496);
			
			userCountMap.put("3.1", 25295);
			userCountMap.put("3.3", 25022);
			userCountMap.put("3.5", 25309);
			userCountMap.put("3.7", 25240);
			userCountMap.put("3.9", 25290);
			userCountMap.put("3.11", 25118);
			userCountMap.put("3.13", 25357);
			userCountMap.put("3.15", 24974);
			userCountMap.put("3.17", 25144);
			userCountMap.put("3.19", 25469);
			userCountMap.put("3.21", 25019);
			userCountMap.put("3.23", 24768);
		} else if("MKX".equals(tNewnetelement.getHwtype())) {
			userCountMap.put("1.1", 26145);
			userCountMap.put("1.3", 26219);
			userCountMap.put("1.5", 26127);
			userCountMap.put("1.7", 26276);
			userCountMap.put("1.9", 0);
			userCountMap.put("1.15", 0);
			userCountMap.put("2.1", 26316);
			userCountMap.put("2.3", 26478);
			userCountMap.put("2.5", 25097);
			userCountMap.put("2.7", 25480);
			
		
		}
	
		userCount.put("cardUserCount", userCountMap);
		userCount.put("totalUserCount", 658990);
		return userCount;
	}
	

	private Map<String,Integer> getUserCount(SSHUtil ssh, String command,Map<String,Integer> userCountMap) {
		String result = ssh.execCommand(command);
		Map<String,Integer> currentMap = null;
		if(StringUtils.isNotBlank(result)){
			String[] userCountLine = result.split("\n");
			currentMap = new HashMap<String,Integer>();
			for(String item : userCountLine) {
				String[] mapItem = item.split(" ");
				if(mapItem.length==2 && userCountMap.containsKey(mapItem[0])){
					userCountMap.put(mapItem[0], userCountMap.get(mapItem[0])+Integer.parseInt(mapItem[1]));
				}else if(mapItem.length==2 && !userCountMap.containsKey(mapItem[0])){
					userCountMap.put(mapItem[0], Integer.parseInt(mapItem[1]));
				}
				if(mapItem.length==2){
					currentMap.put(mapItem[0], Integer.parseInt(mapItem[1]));
				}
			}
		}
		return currentMap;
	}
	
	

	
	
	private Map<String, THwStatus> initCard(String eql,String hwtype) {
		
		Map<String, THwStatus> m = new LinkedHashMap<String, THwStatus>();
		if("MKVIII".equals(hwtype)) {
			m.put("m"+eql+"_25", null);
			m.put("m"+eql+"_26", null);
			for(int i=1; i<24; i+=2) {
				m.put("m"+eql+"_"+i, null);
			}
			m.put("m"+eql+"_27", null);
			m.put("m"+eql+"_28", null);
		} else if("MKX".equals(hwtype)) {
			m.put("m"+eql+"_25", null);
			for(int i=1; i<24; i+=2) {
				m.put("m"+eql+"_"+i, null);
			}
			m.put("m"+eql+"_27", null);
		}
		
		return m;
	}
	
	
	public Map<String,String> getMemoryInfo(TNewnetelement tNewnetelement) {
		Map<String,String> map = new HashMap<String,String>();
		
		
		if("MKVIII".equals(tNewnetelement.getHwtype())) {	
			map.put("m1_25", "33");
			map.put("m1_1", "9");
			map.put("m1_3", "10");
			map.put("m1_5", "9");
			map.put("m1_7", "9");
			map.put("m1_9", "4");
			map.put("m1_11", "1");
			map.put("m1_13", "2");
			map.put("m1_15", "8");
			
			map.put("m1_27", "33");
			map.put("m2_3", "9");
			map.put("m2_5", "9");
			map.put("m2_7", "9");
			map.put("m2_9", "9");
			
			map.put("m3_25", "27");
			map.put("m3_1", "9");
			map.put("m3_3", "9");
			map.put("m3_5", "9");
			map.put("m3_7", "10");
			map.put("m3_9", "9");
			map.put("m3_11", "9");
			map.put("m3_13", "9");
			map.put("m3_15", "9");
			map.put("m3_17", "9");
			map.put("m3_19", "9");
			map.put("m3_21", "9");
			map.put("m3_23", "9");
			map.put("m3_27", "27");
		} else if("MKX".equals(tNewnetelement.getHwtype())) {
			map.put("m1_25", "33");
			map.put("m1_1", "9");
			map.put("m1_3", "10");
			map.put("m1_5", "9");
			map.put("m1_7", "9");
			map.put("m1_9", "4");
			map.put("m1_11", "1");
			map.put("m1_13", "2");
			map.put("m1_15", "8");
			map.put("m1_27", "33");
			map.put("m2_3", "9");
			map.put("m2_5", "9");
			map.put("m2_7", "9");
			
		}
		
		return map;
	}
	
	
	
	@RequiresPermissions("netconfig:tMme:view")
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
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/mme_eci_stats");
					while((line = br.readLine())!=null){
						logList.add(line);
					}
					break;
				case("kpi"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/mme_pdc_kpi");
					while((line = br.readLine())!=null){
						logList.add(line);
					}
					break;
				case("health"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/mme_health");
					while((line = br.readLine())!=null){
						logList.add(line);
					}
					break;
				case("alarm"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/mme_alarm");
					while((line = br.readLine())!=null){
						logList.add(line);
					}
					break;
				case("config"):
					br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/mme_config");
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
	
	@RequiresPermissions("netconfig:tMme:view")
	@RequestMapping(value = "refresh")
	public String refresh(HttpServletRequest request, HttpServletResponse response, Model model, String nodeid,String netId) {
		CacheUtils.remove(NODE_CACHE, "hwStatus_"+netId);
		CacheUtils.remove(NODE_CACHE, "userCount_"+netId);
		CacheUtils.remove(NODE_CACHE, "memoryInfo_"+netId);
		CacheUtils.remove(NODE_CACHE, "config_"+netId);
		return this.list(request, response, model, nodeid,null,null);
	}
	
	@RequestMapping(value = "showExcelTemp")
	public String showExcelTemp(Model model, String tempId,String netId) {
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
		tLockTemplate.setType("MME");
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
		model.addAttribute("netId",netId);
		
		model.addAttribute("cardLocal",cardLocal.replace(".", ","));
		model.addAttribute("excelId",tempId);
		model.addAttribute("excelName", visExcel.getName());
		model.addAttribute("detailMap", JsonMapper.getInstance().toJson(detailMap));
		
		return "modules/netconfig/tExcelTemplate";
	}
	
	
	@RequestMapping(value = "showNodeCardSwitchInfo")
	@ResponseBody
	public String showNodeCardSwitchInfo(String netid,String cardeq) {
		TNewnetelement tNewnetelement = tNewnetelementService.get(netid);
		String ip = tNewnetelement.getIpadr();
		String username = tNewnetelement.getUsername1();
		String password = tNewnetelement.getPassword1();
		SSHUtil ssh = new SSHUtil(ip,username,password);
		String result = null;
		try {
			result = equipmentStatusScheduledService.nodeCardSwitch(ssh, cardeq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ssh.disconnect();
		}
		return JsonMapper.getInstance().toJson(result);
	}
	
	@RequestMapping(value = "checkEmergencyCode")
	@ResponseBody
	public String checkEmergencyCode(String authcode) {
		String result = "fail";
		TEpcDic epcDic = new TEpcDic();
		epcDic.setType("AUTHCODE");
		epcDic.setValue(authcode);
		List<TEpcDic> epcDicList = tEpcDicService.findList(epcDic);
		if(null != epcDicList) {
			if(epcDicList.size() > 0) {
				return "success";
			} else {
				result = "fail";
			}
		}
		return result;
	}
	
	@RequestMapping(value = "getEmergencytem")
	@ResponseBody
	public String getEmergencytem(String type,String temptype) {
		TVisExcel t = new TVisExcel();
		if(null != type && !"".equals(type)) {
			t.setType(type);
		}
		t.setTemplatetype(temptype);
		List<TVisExcel> excelList = tVisExcelService.findList(t);
		return JsonMapper.getInstance().toJson(excelList);
	}
	
	
	public static void main(String[] args) {
		String str = "﻿1 1   CB       GEP3E1T1 Unblocked  Up        Poweron    R5A      R10A02  ROJ 208 830/3 GEP3-E1/T1  20130627 D168331744    -                ";
		System.out.println("---------" + str.substring(1, 2) + "------------");
		System.out.println("---------" + str.substring(3, 6).trim() + "------------");
		System.out.println("---------" + str.substring(7, 15) + "------------");
	}
	
}
