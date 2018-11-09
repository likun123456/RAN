package com.thinkgem.jeesite.modules.userquery.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpSession;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.userquery.entity.DetailLog;
import com.thinkgem.jeesite.modules.userquery.entity.TCapEpgPath;
import com.thinkgem.jeesite.modules.userquery.service.TCapEpgPathService;



public class ExecuteSaegwPoolCapture implements Runnable {
	
	private String saegwPoolIds;
	private String saegwPoolNames;
	private CountDownLatch latch;
	private String imsi;
	private Integer delay;
	private String startTime;
	private String capAllPathId;
	private HttpSession session;
	private TNewnetelementService netElementService;
	private TCapEpgPathService capEpgPathService;
	private boolean hasPcrf;
	private int captureScope;
	
	
	public ExecuteSaegwPoolCapture(String saegwPoolIds,String saegwPoolNames,CountDownLatch latch,String imsi,Integer delay,
			String startTime,String capAllPathId,HttpSession session,TNewnetelementService netElementService,
			TCapEpgPathService capEpgPathService,boolean hasPcrf,int captureScope){
		this.saegwPoolIds = saegwPoolIds;
		this.saegwPoolNames = saegwPoolNames;
		this.latch = latch;
		this.imsi = imsi;
		this.delay = delay;
		this.startTime = startTime;
		this.capAllPathId = capAllPathId;
		this.session = session;
		this.netElementService = netElementService;
		this.capEpgPathService = capEpgPathService;
		this.hasPcrf = hasPcrf;
		this.captureScope = captureScope;
	}

	@Override
	public void run() {
		StringBuffer poolstr = new StringBuffer();
		String username = "";
		String password = "";
		String returnCapPathAndCapName = "";//TODO test
		TNewnetelement newnetelement = new TNewnetelement();
		if(captureScope==0) {
			//原来用的池组id是temp_field2,改成field3之后,这里用的是网元的id
			newnetelement.setTemp_field3(saegwPoolIds);
		}else {
			newnetelement.setTemp_field2(saegwPoolIds);
		}
		List<TNewnetelement> netElements = netElementService.findList(newnetelement);
		for(TNewnetelement element : netElements){
			String ip = element.getIpadr();
			username = element.getUsername1();
			password = element.getPassword1();
			poolstr.append(" -epg "+ip);
		}
		poolstr.append(" -user "+username+" -password "+"'"+password.replace("&", "\\&")+"'");
		//设置文件夹的时间，格式yyyy_MM_dd
		String filedatetime = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		//文件存放在服务器上的路径(不包括文件名)
		String pfPath = Global.getConfig("PcrfCapPath") + File.separator + filedatetime + File.separator + saegwPoolIds.replaceAll(",", "_") ;
		File file = new File(pfPath);
		if(!file.exists()){
			file.mkdirs();
		}
		Process proc = null;
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/opt/Ericsson/core/bin/trace_epg.pl "+poolstr.toString()+" -m "+ (delay * 60) +" -imsi "+imsi+" -destpath " + pfPath;
//		cmd[2] = "type E:\\opt\\Ericsson\\core\\bin\\EpgStep.txt"; //TODO test
//		cmd[2] = "ping 127.0.0.1"; //TODO test
		if(hasPcrf){
			cmd[2] = cmd[2] + " -waitpcrf";
		}
		System.out.println("===== first cap step ===== "+cmd[2]);
		try {
			proc = Runtime.getRuntime().exec(cmd);//TODO test
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			Queue<String> queue = new LinkedList<String>();
			session.setAttribute("capTextEpgPool", queue);
			DetailLog dl = new DetailLog(); 
			List<String> list = new ArrayList<String>();
			while((line = br.readLine())!=null){
				if(line.contains("Capturing")){
					queue.offer("正在抓包..."+"<br>");
				}
				list.add(StringUtils.toHtml(line)+"<br>");
				if(line.contains(".pcap")){
					returnCapPathAndCapName = line.trim();
				}
			}
			dl.setEpgLogList(list);
			session.setAttribute("epgDetailLog", dl);
	        proc.waitFor();
			proc.destroy();
			Thread.sleep(3000);
			//得到文件名字不包括路径
			String arg[] = returnCapPathAndCapName.split("/");
			String saegwFileName = arg[arg.length-1];
			//录库的时间，格式yyyy-MM-dd HH:mm:ss,相当于结束时间
			String insertdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//数据录入到SAEGW池组抓包单用户表
			if(saegwFileName!=""){
				TCapEpgPath epgPath = new TCapEpgPath();
				epgPath.setPid(capAllPathId);
				epgPath.setMsisdn(imsi);
				epgPath.setPoolids(saegwPoolIds);
				epgPath.setPoolNames(saegwPoolNames);
				epgPath.setPcapPath(pfPath);
				epgPath.setPcapName(saegwFileName);
				epgPath.setStartTime(startTime);
				epgPath.setEndTime(insertdatetime);
				capEpgPathService.save(epgPath);
			}
		System.out.println("==== 3.EPG Pool sub task is finished！Thread name : "+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			latch.countDown();
		}

	}

}
