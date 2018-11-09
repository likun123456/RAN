package com.thinkgem.jeesite.modules.netconfig.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.SSHUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.dao.THwStatusDao;
import com.thinkgem.jeesite.modules.netconfig.dao.TNewnetelementDao;
import com.thinkgem.jeesite.modules.netconfig.dao.TNodeInfoDao;
import com.thinkgem.jeesite.modules.netconfig.entity.CardInfoSaegw;
import com.thinkgem.jeesite.modules.netconfig.entity.THwStatus;
import com.thinkgem.jeesite.modules.netconfig.entity.THwStatusSaegw;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TNodeInfo;

@Service
@Lazy(false)
public class TEquipmentStatusScheduledService {
	@Autowired
	private TNewnetelementDao tNewnetelementDao;
	@Autowired
	private THwStatusDao tTHwStatusDao;
	@Autowired
	private TNodeInfoDao tNodeInfoDao;

	/**
	 * 节点状态信息命令MME
	 */
	public static final String nodeStats = "eci stats";
	/**
	 * 节点性能指标命令MME
	 */
	public static final String nodekpi = "pdc_kpi.pl -n 5";
	/**
	 * 健康检查命令MME
	 */
	public static final String healthCheck = "ls -ltr /Core/log/health_check/data_automatic ";
	/**
	 * 节点当前告警命令MME
	 */
	public static final String nodeAlarm = "gsh list_alarms | grep ";
	/**
	 * 节点当前配置命令MME
	 */
	public static final String nodeConfig = "gsh export_config_active";
	/**
	 * 板卡当前位置命令MME
	 */
	public static final String nodeLocation = "hw_status";
	/**
	 * 板卡当前位置命令SAEGW
	 */
	public static final String nodeLocation_SAEGW = "show card";
	/**
	 * 板卡信息(CPU,内存,用户数)SAEGW 第一条指令
	 */
	public static final String START_OAMCLI = "start oam-cli";
	/**
	 * 板卡信息(CPU,内存,用户数)SAEGW 第二条指令
	 */
	public static final String nodeCardInfo_SAEGW = "ManagedElement=1,Epg=1,Node=1,status";
	/**
	 * 板卡内存状态SAEGW
	 */
	public static final String nodeCardMemory = "show memory card";
	/**
	 * 板卡磁盘状态SAEGW
	 */
	public static final String nodeCardDisk = "show disk card";
	/**
	 * 板卡基本状态(port)SAEGW
	 */
	public static final String nodeCardStatus_port = "show port counters";
	/**
	 * 板卡基本状态(rpsw)SAEGW
	 */
	public static final String nodeCardStatus_rpsw = "show redundancy";
	
	
	public static final String START_SHELL = "start shell";
	/**
	 * 网元性能指标查询SAEGW
	 */
	public static final String nodeKpi_SAEGW = "epg_healthcheck_kpi";
	/**
	 * 网元告警信息查询SAEGW
	 */
	public static final String nodeAlarm_SAEGW_STEP1 = "show system alarm";
	/**
	 * 网元告警信息查询SAEGW
	 */
	public static final String nodeAlarm_SAEGW_STEP2 = "ManagedElement=1,Epg=1,Node=1,FaultManagement=1,activeNotifications";
	/**
	 * 网元健康检查结果SAEGW
	 */
	public static final String nodeHealthCheck_SAEGW = "health_check.pl -s 60";
	/**
	 * 板卡倒换启动记录MME
	 */
	public static final String nodeCardSwitch = "cat /Core/log/isp.log | egrep ";
	/**
	 * 板卡Crash记录SAEGW
	 */
	public static final String nodeCardCrash = "show crashfiles";
	

	@Scheduled(cron = "0 0 2 * * ?")
	public void schNet() throws Exception {
		List<TNewnetelement> netList = tNewnetelementDao.findList(new TNewnetelement());
		TNodeInfo nodeInfo;
		List<THwStatus> thsList;
		for (TNewnetelement newnetelementInfoVO : netList) {
			if (Integer.parseInt(newnetelementInfoVO.getType()) == 1) {
				SSHUtil ssh = new SSHUtil(newnetelementInfoVO.getIpadr(), newnetelementInfoVO.getUsername1(),
						newnetelementInfoVO.getPassword1());
				try {
					ssh.connect();
					nodeInfo = new TNodeInfo();
					nodeInfo.setNetId(Integer.parseInt(newnetelementInfoVO.getId()));
					nodeInfo.setDateTime(DateUtils.getDate());
					nodeInfo.setEciStat(nodeStats(ssh, nodeStats));
					nodeInfo.setPdcKpi(nodekpi(ssh, nodekpi));
					nodeInfo.setHealthcheck(healthCheck(ssh, healthCheck));
					nodeInfo.setListAlarms(nodeAlarm(ssh, nodeAlarm));
					nodeInfo.setListParamConf(nodeConfig(ssh, nodeConfig));
					tNodeInfoDao.insert(nodeInfo);
					thsList = nodeLocation(ssh, nodeLocation, Integer.parseInt(newnetelementInfoVO.getId()),
							newnetelementInfoVO.getHwtype());
					tTHwStatusDao.batchIntert(thsList);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					ssh.disconnect();
				}
			}
			if (Integer.parseInt(newnetelementInfoVO.getType()) == 2) {
				SSHUtil ssh = new SSHUtil(newnetelementInfoVO.getIpadr(), newnetelementInfoVO.getUsername1(),
						newnetelementInfoVO.getPassword1());
				try {
					ssh.connect();
					nodeInfo = new TNodeInfo();
					nodeInfo.setNetId(Integer.parseInt(newnetelementInfoVO.getId()));
					nodeInfo.setDateTime(DateUtils.getDate());
					nodeInfo.setEciStat(nodeStatusSaegw(ssh));
					nodeInfo.setPdcKpi(nodeKpiSaegw(ssh));
					nodeInfo.setHealthcheck(nodeHealthCheckSaegw(ssh));
					nodeInfo.setListAlarms(nodeAlarmSaegw(ssh));
					nodeInfo.setListParamConf(nodeConfigSaegw(ssh, newnetelementInfoVO.getUsername2(), newnetelementInfoVO.getPassword2()));
					tNodeInfoDao.insert(nodeInfo);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					ssh.disconnect();
				}
			}
		}
	}

	/**
	 * 节点状态信息获取
	 * 
	 * @param ssh
	 * @param command
	 * @return
	 * @throws Exception
	 */
	public String nodeStats(SSHUtil ssh, String command) throws Exception {
		return ssh.execCommand(command);
	}

	/**
	 * 节点性能指标获取
	 */
	public String nodekpi(SSHUtil ssh, String command) throws Exception {
		return ssh.execCommand(command);
	}

	/**
	 * 节点健康检查结果
	 * 
	 * @param ssh
	 * @param command
	 * @return
	 */
	public String healthCheck(SSHUtil ssh, String command) throws Exception {
		String result = ssh.execCommand("ls -ltr /Core/log/health_check/data_automatic");
		String[] temp = result.split("\n");
		String[] fileName = temp[temp.length - 1].split("\\s");
		return ssh.execCommand("cat /Core/log/health_check/data_automatic/" + fileName[fileName.length - 1] + "/"
				+ fileName[fileName.length - 1] + ".txt");
	}

	/**
	 * 节点告警信息
	 * 
	 * @param ssh
	 * @param command
	 * @return
	 */
	public String nodeAlarm(SSHUtil ssh, String command) {
		String nowCommand = command + DateUtils.getMonth() + "-" + DateUtils.getDay();
		return ssh.execCommand(nowCommand);
	}

	/**
	 * 节点当前配置
	 * 
	 * @param ssh
	 * @param command
	 * @return
	 */
	public String nodeConfig(SSHUtil ssh, String command) {
		String fileUrl = ssh.execCommand(command);
		String[] temp = fileUrl.split("\n");
		return ssh.execCommand("cat " + temp[1]);
	}
	
	/**
	 * 节点当前配置SAEGW
	 * 
	 * @param ssh
	 * @param command
	 * @return
	 * @throws Exception 
	 */
	public String nodeConfigSaegw(SSHUtil ssh,String rootUserName,String rootPassword) throws Exception {
		StringBuffer sb = new StringBuffer();
		ssh.getShellChannel();
		ssh.sendCommand(START_SHELL, "$");
		String returnedResult = ssh.sendCommand("show_epg_version","$");
		sb.append(returnedResult);
		sb.append("\n");
		ssh.sendCommand("su "+rootUserName,":");
		ssh.sendCommand(rootPassword,"#");
		String sshResult = ssh.sendCommand("cat /flash/ericsson.cfg", "#");
		sb.append(sshResult);
		ssh.sendCommand("exit", "$");
		ssh.sendCommand("exit", "#");
		return sb.toString();
	}

	/**
	 * 节点位置信息
	 * 
	 * @param ssh
	 * @param command
	 * @return
	 */
	public List<THwStatus> nodeLocation(SSHUtil ssh, String command, int netID, String hwtype) {
		THwStatus ths;
		List<THwStatus> thsList = new ArrayList<THwStatus>();
		String sshResult = ssh.execCommand(command);
		String[] temp = sshResult.split("\n");
		for (int i = 3; i < temp.length - 2; i++) {
			ths = new THwStatus();
			ths.setNetID(netID);
			ths.setDateTime(DateUtils.getDate());
			if (!hwtype.equals("MKX")) {
				ths.setEql(Integer.parseInt(temp[i].substring(0, 1).trim()));
				ths.setEqr(Integer.parseInt(temp[i].substring(2, 5).trim()));
				ths.setEqlAndEqr(ths.getEql() + "." + ths.getEqr());
				ths.setEqClass(temp[i].substring(6, 14).trim());
				ths.setType(temp[i].substring(15, 23).trim());
				ths.setAdminState(temp[i].substring(24, 34).trim());
				ths.setOperState(temp[i].substring(35, 44).trim());
				ths.setPowerState(temp[i].substring(45, 55).trim());
				ths.setRevision(temp[i].substring(56, 64).trim());
				ths.setBootRom(temp[i].substring(65, 72).trim());
				ths.setProdNo(temp[i].substring(73, 86).trim());
				ths.setProdName(temp[i].substring(87, 98).trim());
				ths.setManWeek(temp[i].substring(99, 107).trim());
				ths.setSerialNo(temp[i].substring(108, 121).trim());
				ths.setFsbRole(temp[i].substring(122, temp[i].length()).trim());
			} else {
				ths.setEql(Integer.parseInt(temp[i].substring(0, 1).trim()));
				ths.setEqr(Integer.parseInt(temp[i].substring(2, 5).trim()));
				ths.setEqlAndEqr(ths.getEql() + "." + ths.getEqr());
				ths.setEqClass(temp[i].substring(6, 14).trim());
				ths.setType(temp[i].substring(15, 19).trim());
				ths.setAdminState(temp[i].substring(20, 30).trim());
				ths.setOperState(temp[i].substring(31, 40).trim());
				ths.setPowerState(temp[i].substring(41, 51).trim());
				ths.setRevision(temp[i].substring(52, 60).trim());
				ths.setBootRom(temp[i].substring(61, 68).trim());
				ths.setProdNo(temp[i].substring(69, 82).trim());
				ths.setProdName(temp[i].substring(83, 92).trim());
				ths.setManWeek(temp[i].substring(93, 101).trim());
				ths.setSerialNo(temp[i].substring(102, 115).trim());
				ths.setFsbRole(temp[i].substring(116, temp[i].length()).trim());
			}
			thsList.add(ths);
		}
		return thsList;
	}
	
	/**
	 * 节点板卡位置信息
	 * 
	 * @param ssh
	 * @param command
	 * @return
	 * @throws Exception 
	 */
	public List<THwStatusSaegw> nodeLocationSaegw(SSHUtil ssh) throws Exception {
		List<THwStatusSaegw> hwList = new ArrayList<THwStatusSaegw>();
		ssh.getShellChannel();
		ssh.sendCommand("terminal length 0","#");
		String sshResult = ssh.sendCommand(nodeLocation_SAEGW,"#",2);
		if(StringUtils.isNotBlank(sshResult)){
			String[] temp = sshResult.split("\n");
			for(String item : temp){
				if(item.contains(":") && !"Slot".equals(item.split(":")[0].trim())){
					THwStatusSaegw hwSaegw = new THwStatusSaegw();
					hwSaegw.setSlot(item.substring(0, 6).trim());
					hwSaegw.setConfiguredType(item.substring(7, 29).trim());
					hwSaegw.setInstalledType(item.substring(29, 50).trim());
					hwSaegw.setOperationalState(item.substring(50, 68).trim());
					hwSaegw.setAdminState(item.substring(68, item.length()).trim());
					hwList.add(hwSaegw);
				}
			}
		}
		return hwList;
	}
	
	public Map<String,CardInfoSaegw> nodeCardInfoSaegw(SSHUtil ssh) throws Exception {
		String sshResult = nodeStatusSaegw(ssh);
		Map<String,CardInfoSaegw> cardMap = null;
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
		return cardMap;
	}
	/**
	 * 网元状态信息查询SAEGW
	 * @param ssh
	 * @return
	 * @throws Exception
	 */
	public String nodeStatusSaegw(SSHUtil ssh) throws Exception {
		ssh.getShellChannel();
		ssh.sendCommand(START_OAMCLI, ">");
		String sshResult = ssh.sendCommand(nodeCardInfo_SAEGW, ">");
		ssh.sendCommand("exit", "#");
		return sshResult;
	}
	
	/**
	 * 网元性能指标查询SAEGW
	 * @param ssh
	 * @return
	 * @throws Exception
	 */
	public String nodeKpiSaegw(SSHUtil ssh) throws Exception {
		ssh.getShellChannel();
		ssh.sendCommand(START_SHELL, "$");
		String sshResult = ssh.sendCommand(nodeKpi_SAEGW, "$");
		ssh.sendCommand("exit", "#");
		return sshResult;
	}
	
	/**
	 * 网元健康检查SAEGW
	 * @param ssh
	 * @return
	 * @throws Exception
	 */
	public String nodeHealthCheckSaegw(SSHUtil ssh) throws Exception {
		ssh.getShellChannel();
		ssh.sendCommand(START_SHELL, "$");
		String sshResult = ssh.sendCommand(nodeHealthCheck_SAEGW, "$");
		ssh.sendCommand("exit", "#");
		return sshResult;
	}
	/**
	 * 网元告警信息查询SAEGW
	 * @param ssh
	 * @return
	 * @throws Exception
	 */
	public String nodeAlarmSaegw(SSHUtil ssh) throws Exception {
		ssh.getShellChannel();
		StringBuffer sb = new StringBuffer();
		sb.append(ssh.sendCommand(nodeAlarm_SAEGW_STEP1, "#",2));
		sb.append("\n");
		ssh.sendCommand(START_OAMCLI, ">");
		String sshResult = ssh.sendCommand(nodeAlarm_SAEGW_STEP2, ">");
		sb.append(sshResult);
		ssh.sendCommand("exit", "#");
		return sb.toString();
	}
	/**
	 * 板卡倒换启动记录MME
	 * @param ssh
	 * @return
	 * @throws Exception
	 */
	public String nodeCardSwitch(SSHUtil ssh,String cardeq) throws Exception {
		String today = DateUtils.getDate();
		String yesterday = DateUtils.beforNumDay(new Date(),-1);
		String sshResult = ssh.execCommand(nodeCardSwitch+"'"+today+"|"+yesterday+"' | grep "+cardeq+",");
		return Encodes.escapeHtml(sshResult);
	}
	/**
	 * 板卡Crash记录SAEGW
	 * @param ssh
	 * @return
	 * @throws Exception
	 */
	public List<String> nodeCardCrash(SSHUtil ssh) throws Exception {
		ssh.getShellChannel();
		String sshResult = ssh.sendCommand(nodeCardCrash, "#",2);
		String sshResults[] = sshResult.split("\n");
		String temp;
		List<String> list = new ArrayList<String>();
		for(int i=0;i<sshResults.length;i++) {
			if(sshResults[i].contains(".core.gz")) {
				temp = sshResults[i].substring(0, sshResults[i].indexOf(".core.gz"));
				sshResults[i] = temp.substring(temp.indexOf("-")+1);
				list.add(sshResults[i]);
			}
		}
		return list;
	}
	/**
	 * 板卡内存状态SAEGW
	 * @param ssh
	 * @return
	 * @throws Exception
	 */
	public List<String> nodeCardMemory(SSHUtil ssh,String cardeq) throws Exception{
		ssh.connect();
		ssh.getShellChannel();
		String sshResult = ssh.sendCommand(nodeCardMemory+" "+cardeq, "#",2);
		String sshResults[] = sshResult.split("\n");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<sshResults.length;i++) {
		    if(!sshResults[i].contains("[local]")&&!sshResults[i].contains("show memory card")&&!sshResults[i].contains("---------")) {
		    	list.add(sshResults[i]);
		    }
		}
		return list;
	}
	/**
	 * 板卡磁盘状态SAEGW
	 * @param ssh
	 * @return
	 * @throws Exception
	 */
	public List<String> nodeCardDisk(SSHUtil ssh,String cardeq) throws Exception{
		ssh.connect();
		ssh.getShellChannel();
		String sshResult = ssh.sendCommand(nodeCardDisk+" "+cardeq, "#",2);
		String sshResults[] = sshResult.split("\n");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<sshResults.length;i++) {
		    if(!sshResults[i].contains("[local]")&&!sshResults[i].contains("show disk card")&&!sshResults[i].contains("---------")) {
		    	list.add(sshResults[i]);
		    }
		}
		return list;
	}
	
	public List<String> nodeCardStatus_port(SSHUtil ssh,String cardeq)throws Exception{
		ssh.connect();
		ssh.getShellChannel();
		ssh.sendCommand("terminal length 0","#");
		String sshResult = ssh.sendCommand(nodeCardStatus_port, "#",2);
		String sshResults[] = sshResult.split("\n");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<sshResults.length;i++) {
			if(sshResults[i].contains(cardeq+"/")) {
				list.add(sshResults[i]);
				list.add(sshResults[i+1]);
				list.add(sshResults[i+2]);
				list.add(sshResults[i+3]);
				list.add(sshResults[i+4]);
				list.add(sshResults[i+5]);
			}
		}
		return list;
	}
	
	public List<String> nodeCardStatus_rpsw(SSHUtil ssh,String cardeq)throws Exception{
		ssh.connect();
		ssh.getShellChannel();
		ssh.sendCommand("terminal length 0","#");
		String sshResult = ssh.sendCommand(nodeCardStatus_rpsw, "#",2);
		String sshResults[] = sshResult.split("\n");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<sshResults.length;i++) {
				list.add(sshResults[i]);
		}
		return list;
	}
}
