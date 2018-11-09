package com.thinkgem.jeesite.modules.userquery.web;

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

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.CdrDecoder;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.oa.entity.Leave;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.userquery.entity.TImeitacTranslate;
import com.thinkgem.jeesite.modules.userquery.service.TImeitacTranslateService;

/**
 * 用户聚合查询 Controller
 * @author chenhongbo
 * @version 2017-07-25
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "${adminPath}/userquery/userQuery")
public class UserQueryController extends BaseController {
	
	//private String queryFilePath = Global.getConfig("queryFilePath");
	
	@Autowired
	private TNewnetelementService tnewnetelementService;
	@Autowired
	private TImeitacTranslateService imeitacTranslateService;

	@RequiresPermissions("userquery:userQuery:view")
	@RequestMapping(value = "queryForm")
	public String queryForm() {
		return "modules/userquery/userQueryForm";
	}
	
	private List<TNewnetelement> getTNewnetelementList(String netType) {
		List<Dict> dictList = DictUtils.getDictList("biz_net_type");
		String parameterEpc = "1";
		for(Dict d : dictList) {
			if(netType.equals(d.getLabel())) {
				parameterEpc = d.getValue();
				break;
			}
		}
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setType(parameterEpc);
		List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
		return list;
	}
	
	@RequestMapping(value = "query")
	public String query(@RequestParam(required = true) String searchType,@RequestParam(required = true) String searchValue, Model model,HttpSession session) {
		List<TNewnetelement> list = getTNewnetelementList("MME");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		/*Process proc = null;*/
		BufferedReader br = null;
		List<String> mmedata = new ArrayList<String>();
		String msisdn = "";
		//String showVal = CdrDecoder.getTransformValue("hide","show", "");
		try {
			List<Map<String,String>> connList = new ArrayList<Map<String,String>>();
			dataMap.put("msisdn", searchValue);
			dataMap.put("terminalBrand", "Apple"); //客户终端品牌
			dataMap.put("terminalModel", "iphone 7"); //客户终端品牌
			String ipStr = "";
			String user = "";
			String password = "";
			for(TNewnetelement e : list) {
				if("".equals(user)) {
					user = e.getUsername1();
				}
				if("".equals(password)) {
					password = e.getPassword1();
				}
				ipStr += " -sgsn " + e.getIpadr();
			}
			
			/*String[] cmd = new String[3];
			cmd[0] = "sh";
			cmd[1] = "-c";
			cmd[2] = "/usr/bin/perl -w /opt/Ericsson/core/bin/get_userinfo_detail.pl";
			cmd[2] += ipStr;
			cmd[2] += " -user "+user+" -password "+password+" -"+searchType+" "+searchValue;
			cmd[2] += " -hostfile /opt/Ericsson/core/bin/" + Global.getConfig("hostFile");*/
			
			/*proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			System.out.println(cmd[2]);
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));*/
            //br = readFile("D:/test.txt");//测试代码
			String line = null;
			int flag = 0;
			/*while((line = br.readLine())!=null){
				if(line.contains("==========================")) {
					while((line = br.readLine())!=null) {
						if(line.contains(":")){
							String[] item = line.trim().split(":");
							if(item.length>=2){
								if("state".equals(item[0].trim())) {
									String[] val = item[1].trim().split("\\s+");
									dataMap.put(item[0].trim(), val[0] + "</br>" + val[1]);
								} else {
									dataMap.put(item[0].trim(), item[1].trim());
								}
							}else{
								dataMap.put(item[0].trim(), "N/A");
							}
						}
						if(line.contains("msisdn") && line.contains(":")) {
							String[] item = line.trim().split(":");
							if(item.length>=2){
								msisdn = item[1];
							}
							
						}
						if(line.contains("imei") && line.contains(":")){
							String imeitacTranslate = null;
							String nodeValue = line.trim().split(":")[1].trim();
							if(nodeValue.length()>=8){
								String imeisvKey = nodeValue.substring(0, 8);
								TImeitacTranslate imeitacObj = imeitacTranslateService.get(imeisvKey);
								if(imeitacObj != null){
									imeitacTranslate = imeitacObj.getPhonename();
								}
							}
							if(imeitacTranslate != null){
								dataMap.put("imei", imeitacTranslate);
							}else{
								dataMap.put("imei", "未知");
							}
						}
						
						if(line.contains("volte")) {
							if(line.contains("yes")){
								dataMap.put("userProp", "VOLTE");
							}else{
								flag++;
							}
						}
						if(line.contains("csfb")){
							if(line.contains("yes")) {
								dataMap.put("userProp", "CSFB");
							}else{
								flag++;
							}
						}
						if(flag==2){
							dataMap.put("userProp", "2G/3G");
						}
						
						//Active PDN connection 相关信息
						if(line.contains("Active")) {
							String line1 = null;
							Map<String, String> m = new HashMap<String,String>();
							boolean end = false;
							while((line1 = br.readLine())!=null){
								if(line1.contains("apn")) {
									
									String[] apnName = line1.split(":");
									if(apnName.length >= 2) {
										m.put("apnName", apnName[1]);
									}
								}
								if(line1.contains("ue")) {
									String line2 = null;
									while((line2 = br.readLine())!=null){
										if(line2 != null && !"".equals(line2.trim())) {
											if(line2.contains("ipv4")) {
												String[] ipv4 = line2.trim().split(":");
												if(ipv4.length>=2 && !StringUtils.isBlank(ipv4[1])){
													m.put("iptype", "ipv4");
													m.put("ip4addr", ipv4[1].trim());
												}else{
													m.put("iptype", ipv4[0].trim());
													m.put("ip4addr", "N/A");
												}
											}
											if(line2.contains("ipv6")){
												String[] ipv6 = line2.trim().split(":");
												if(ipv6.length>=2 && !StringUtils.isBlank(ipv6[1])){
													m.put("iptype", "ipv6");
													m.put("ip6addr", line2.substring(line2.indexOf(":")+1));
												}else{
													m.put("iptype", "ipv6");
													m.put("ip6addr", "N/A");
												}
												//end = true;
												break;
											}
										}
									}
								}
								if(line1.contains("sgw")) {
									String line2 = null;
									while((line2 = br.readLine())!=null) {
										if(line2 != null && !"".equals(line2.trim())) {
											if(line2.contains("node")) {
												String[] node = line2.trim().split(":");
												if(node.length >= 2 && !StringUtils.isBlank(node[1])) {
													m.put("sgw", node[1].trim());
												} else {
													m.put("sgw", "N/A");
												}
												break;
											}
											
										}
									}
								}
								if(line1.contains("pgw")) {
									String line2 = null;
									while((line2 = br.readLine())!=null) {
										if(line2 != null && !"".equals(line2.trim())) {
											
											if(line2.contains("node")) {
												String[] node = line2.trim().split(":");
												if(node.length >= 2 && !StringUtils.isBlank(node[1])) {
													m.put("pgw", node[1].trim());
												} else {
													m.put("pgw", "N/A");
												}
												end = true;
												break;
											}
											
										}
									}
								}
								if(end) {
									connList.add(m);
									break;
								}
								
							}
						}
					}
					
				} else {
					if(!StringUtils.isBlank(line)) {
						if(line.contains("Last") && line.contains("Visited") && line.contains("Tracking") && line.contains("Area")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("Tracking") && line.contains("Area") && line.contains("List")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Type")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Name")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Id")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("PLMN") && line.contains("Id")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("Latest") && line.contains("eNodeB") && line.contains("List")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else {
							mmedata.add(line.replaceAll(" ", "&nbsp;"));
						}
					}
				}
				
			}*/
			dataMap.put("connList", connList);
		} catch (Exception e) {
    			e.printStackTrace();
    	}finally{
    		/*if(proc != null){
    			proc.destroy();
    		}
    		if(br != null){
    			try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}*/
    	}
		//String val = CdrDecoder.getTransformValue("hide","show", "");
		/*if("show".equals(showVal)) {
			model.addAttribute("show","true");
		} else {
			model.addAttribute("show","false");
		}*/
		model.addAttribute("show","true");
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("searchType",searchType);
		model.addAttribute("searchValue", searchValue);
		session.setAttribute("mmedata", mmedata);
//		execpcrf(msisdn,model,session);
		return "modules/userquery/userQueryResult";
	}
	@RequestMapping(value = "showmme")
	public String showmme(Model model,HttpSession session) {
		/*BufferedReader br = readFile(queryFilePath);
		String line = null;
		List<String> list = new ArrayList<String>();
		try {
			while((line = br.readLine())!=null){
				if(!StringUtils.isBlank(line)) {
					list.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		//List<String> list = (List<String>)session.getAttribute("mmedata");
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;
		try {
			br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/userQuery/mme_msg");
			while((line = br.readLine())!=null){
				list.add(line);
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
		
		model.addAttribute("data", list);
		return "modules/userquery/userQueryMsg";
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
	
	public void execpcrf(String searchValue, Model model, HttpSession session) {
		List<TNewnetelement> list = getTNewnetelementList("PCRF");
		String ipStr = "";
		String user = "";
		String password = "";
		for(TNewnetelement e : list) {
			if("".equals(user)) {
				user = e.getUsername1();
			}
			if("".equals(password)) {
				password = e.getPassword1();
			}
			ipStr += " -ip " + e.getIpadr();
		}
		Process proc = null;
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/opt/Ericsson/core/bin/get_pcrf.pl";
		cmd[2] += ipStr;
		cmd[2] += " -user "+user+" -password "+password+" --msisdn "+searchValue;
		cmd[2] += " -hostfile /opt/Ericsson/core/bin/oamhosts";
		System.out.println(cmd[2]);
		BufferedReader br = null;
		String line = null;
		List<String> l = new ArrayList<String>();
		try {
			proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			//br = readFile("D:/test2.txt");//测试代码
			while((line = br.readLine())!=null){
				if(line.contains("==========================")) {
					while((line = br.readLine())!=null) {
						if(line.contains("pcrf")) {
							String[] prcf = line.split(":");
							if(prcf.length >= 2 && !StringUtils.isBlank(prcf[1])) {
								String[] val = prcf[1].trim().split(",");
								String pcrfval = "";
								if(val.length >= 2) {
									for(String v : val) {
										pcrfval += v + "<br>";
									}
								} else {
									pcrfval = prcf[1].trim();
								}
								model.addAttribute("pcrfname", pcrfval);
							}
						}
					}
				} else {
					if(!StringUtils.isBlank(line)) {
						//l.add(line);
						l.add(line.replaceAll(" ", "&nbsp;"));
					}
				}
			}
			
			Map<String, List<String>> dataMap = new LinkedHashMap<String, List<String>>();
			int start = 0;
			int end = 0;
			for(int i=0; i<l.size(); i++) {
				String str = l.get(i);
				if(str.contains("Cmds") && str.contains("need")) {
					start = end;
					end = i;
					List<String> slist = new ArrayList<String>();
					slist.addAll(l.subList(start, end));
					//List<String> slist = l.subList(start, end);
					if(slist.size() > 0) {
						String tabName = getTabName(slist);
						dataMap.put(tabName, slist);
					}
				}
			}
			List<String> elist = new ArrayList<String>();
			elist.addAll(l.subList(end, l.size()));
			if(elist.size()>0) {
				String tabName = getTabName(elist);
				dataMap.put(tabName, elist);
			}
		
			session.setAttribute("pcrfdataMap", dataMap);  //显示叶签
			session.setAttribute("pcrfdataList", l);  //只有一个pcrf
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(proc != null){
    			proc.destroy();
    		}
    		if(br != null){
    			try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
		}
	}
	
	public String getTabName(List<String> slist) {
		String tabName = "N/A";
		for(String ss : slist) {
			if(ss.contains("shareTree") && ss.contains("nodeName")) {
				String[] item = ss.split(":");
				if(item.length >= 2) {
					String it = item[1];
					String[] item2 = it.split("=");
					if(item2.length >= 2) {
						tabName = item2[1];
						break;
					}
				}
			}
		}
		return tabName;
	}
	
	@RequestMapping(value = "showpcrf")
	public String showpcrf(Model model, HttpSession session) {
		List<String> dataList = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;
		try {
			br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/userQuery/pcrf_msg");
			while((line = br.readLine())!=null){
				dataList.add(line);
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
		model.addAttribute("data", dataList);
		return "modules/userquery/userQueryMsg";
		/*Map<String, List<String>> dataMap = (Map<String, List<String>>)session.getAttribute("pcrfdataMap");
		if(dataMap.size() >= 2) {
			model.addAttribute("data", dataMap);
			model.addAttribute("count", dataMap.size());
			return "modules/userquery/userQueryPcrfMsg";
		} else {
			List<String> dataList = (List<String>)session.getAttribute("pcrfdataList");
			model.addAttribute("data", dataList);
			return "modules/userquery/userQueryMsg";
		}*/
	}
	
	@RequestMapping(value = "showsgw")
	public String showsgw(@RequestParam(required = true) String sgw,@RequestParam(required = true) String imsi, Model model) {
		/*TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setFname(sgw);
		List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
		Process proc = null;
		BufferedReader br = null;
		String line = null;
		List<String> l = new ArrayList<String>();
		if(null != list) {
			TNewnetelement t = list.get(0);
			String[] cmd = new String[3];
			cmd[0] = "sh";
			cmd[1] = "-c";
			//cmd[2] = "/opt/Ericsson/core/bin/sendcmd_epg.pl -ip " + t.getIpadr() + "-user " + t.getUsername1() + " -password " + t.getPassword1() + " -cmd 'ManagedElement=1,Epg=1,Pgw=1,userInfo imsi " + imsi;
			cmd[2] = "/opt/Ericsson/core/bin/sendcmd_epg.pl -ip " + t.getIpadr() + " -user " + t.getUsername1() + " -password '" + t.getPassword1() + "' -cmd 'ManagedElement=1,Epg=1,Sgw=1,userInfo imsi "+imsi+"'";
			System.out.println(cmd[2]);
			try {
				proc = Runtime.getRuntime().exec(cmd);
				proc.waitFor();
				br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				while((line = br.readLine())!=null){
					//if(!StringUtils.isBlank(line)) {
						l.add(line.replaceAll(" ", "&nbsp;"));
						//l.add(line);
					//}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		List<String> dataList = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;
		try {
			br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/userQuery/sgw_msg1");
			while((line = br.readLine())!=null){
				dataList.add(line);
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
		
		model.addAttribute("data", dataList);
		return "modules/userquery/userQueryMsg";
	}
	
	@RequestMapping(value = "showpgw")
	public String showpgw(@RequestParam(required = true) String pgw,@RequestParam(required = true) String imsi, Model model) {
		/*TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setFname(pgw);
		List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
		Process proc = null;
		BufferedReader br = null;
		String line = null;
		List<String> l = new ArrayList<String>();
		if(null != list) {
			TNewnetelement t = list.get(0);
			String[] cmd = new String[3];
			cmd[0] = "sh";
			cmd[1] = "-c";
			//cmd[2] = "/opt/Ericsson/core/bin/sendcmd_epg.pl -ip " + t.getIpadr() + "-user " + t.getUsername1() + " -password " + t.getPassword1() + " -cmd 'ManagedElement=1,Epg=1,Sgw=1,userInfo imsi " + imsi;
			cmd[2] = "/opt/Ericsson/core/bin/sendcmd_epg.pl -ip " + t.getIpadr() + " -user " + t.getUsername1() + " -password '" + t.getPassword1() + "' -cmd 'ManagedElement=1,Epg=1,Pgw=1,userInfo imsi "+imsi+"'";
			try {
				proc = Runtime.getRuntime().exec(cmd);
				proc.waitFor();
				br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				while((line = br.readLine())!=null){
					//if(!StringUtils.isBlank(line)) {
						l.add(line.replaceAll(" ", "&nbsp;"));
						//l.add(line);
					//}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		List<String> dataList = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;
		try {
			br = readFile("D:/apache-tomcat-8.0.46-PC/commandData/userQuery/pgw_msg1");
			while((line = br.readLine())!=null){
				dataList.add(line);
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
		model.addAttribute("data", dataList);
		return "modules/userquery/userQueryMsg";
	}
	
	@RequestMapping(value = "redirectCdrQuery")
	public String redirectCdrQuery(Leave leave, RedirectAttributes redirectAttributes) {
		
		return "redirect:" + adminPath + "/oa/leave/form";
	}
	
	public static void main(String[] args) {
		String str = "Latest eNodeB List                   : 460-00-Macro-352748,460-00-Macro-354715,460-00-Macro-194351,460-00-Macro-193519,460-00-Macro-194008,460-00-Macro-788280,460-00-Macro-352931,460-00-Macro-193697,460-00-Macro-834652,460-00-Macro-353190";
		int startWith = 0;
		for(int i=0; i<str.length(); i++) {
			if(i%100 == 0) {
				str.substring(startWith, i);
				startWith = i;
			}
		}
	}
	
}
