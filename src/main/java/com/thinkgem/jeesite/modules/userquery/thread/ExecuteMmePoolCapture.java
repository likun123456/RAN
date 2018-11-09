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
import com.thinkgem.jeesite.modules.userquery.entity.TCapMmePath;
import com.thinkgem.jeesite.modules.userquery.service.TCapMmePathService;



public class ExecuteMmePoolCapture implements Runnable{
	
	private String poolids;
	private String poolnames;
	private CountDownLatch latch;
	private String imsi;
	private Integer delay;
	private String startTime;
	private String capAllPathId;
	private HttpSession session;
	private TNewnetelementService netElementService;
	private TCapMmePathService capMmePathService;
	private boolean hasPcrf;
	private int captureScope;
	
	public ExecuteMmePoolCapture(String poolids,String poolnames,CountDownLatch latch,String imsi,Integer delay,
			String startTime,String capAllPathId,HttpSession session,TNewnetelementService netElementService,
			TCapMmePathService capMmePathService,boolean hasPcrf,int captureScope){
		this.poolids = poolids;
		this.poolnames = poolnames;
		this.latch = latch;
		this.imsi = imsi;
		this.delay = delay;
		this.startTime = startTime;
		this.capAllPathId = capAllPathId;
		this.session = session;
		this.netElementService = netElementService;
		this.capMmePathService = capMmePathService;
		this.hasPcrf = hasPcrf;
		this.captureScope = captureScope;
	}

	@Override
	public void run() {
		StringBuffer poolstr = new StringBuffer();
		String username = "";
		String password = "";
		String returnCapPathAndCapName = "";
		TNewnetelement newnetelement = new TNewnetelement();
		if(captureScope==0) {
			//原来用的池组id是temp_field2,改成field3之后,这里用的是网元的id
			newnetelement.setTemp_field3(poolids);
		}else {
			newnetelement.setTemp_field2(poolids);
		}
		List<TNewnetelement> netElements = netElementService.findList(newnetelement);
		for(TNewnetelement element : netElements){
			String ip = element.getIpadr();
			username = element.getUsername2();
			password = element.getPassword2();
			poolstr.append(" -sgsn "+ip);
		}
		poolstr.append(" -user "+username+" -password "+password);
		//设置文件夹的时间，格式yyyy_MM_dd
		String filedatetime = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		//文件存放在服务器上的路径(不包括文件名)
		String pfPath = Global.getConfig("PcrfCapPath") + File.separator + filedatetime + File.separator + poolids.replaceAll(",", "_");
		File file = new File(pfPath);
		if(!file.exists()){
			file.mkdirs();
		}
		Process proc = null;
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/opt/Ericsson/core/bin/trace_mme.pl "+poolstr.toString()+" -m "+ (delay * 60) +" -imsi "+imsi+" -destpath " + pfPath;
//		cmd[2] = "type E:\\opt\\Ericsson\\core\\bin\\MmeStep.txt";
//		cmd[2] = "ping 127.0.0.1";
		if(hasPcrf){
			cmd[2] = cmd[2] + " -waitpcrf";
		}
		System.out.println("===== first cap step ===== "+cmd[2]);
		try {
			proc = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			Queue<String> queue = new LinkedList<String>();
			DetailLog dl = new DetailLog(); 
			List<String> list = new ArrayList<String>();
			session.setAttribute("capTextMmePool", queue);
			while((line = br.readLine())!=null){
				if(line.contains("Capturing")){
					queue.offer("正在抓包..."+"<br>");
				}
				
				list.add(StringUtils.toHtml(line)+"<br>");
				if(line.contains(".pcap")){
					returnCapPathAndCapName = line.trim();
				}
			}
			dl.setMmeLogList(list);
			session.setAttribute("mmeDetailLog", dl);
//			returnCapPathAndCapName = "MME.pcap"; //TODO delete
	        proc.waitFor();
			proc.destroy();
			Thread.sleep(3000);
			//得到文件名字不包括路径
			String arg[] = returnCapPathAndCapName.split("/");
			String pcrfFileName = arg[arg.length-1];
			//录库的时间，格式yyyy-MM-dd HH:mm:ss,相当于结束时间
			String insertdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//数据录入到MME池组抓包单用户表
			if(pcrfFileName!=""){
				TCapMmePath capMmePath = new TCapMmePath();
				capMmePath.setPid(capAllPathId);
				capMmePath.setMsisdn(imsi);
				capMmePath.setPoolids(poolids);
				capMmePath.setPoolNames(poolnames);
				capMmePath.setPcapPath(pfPath);
				capMmePath.setPcapName(pcrfFileName);
				capMmePath.setStartTime(startTime);
				capMmePath.setEndTime(insertdatetime);
				
				capMmePathService.save(capMmePath);
			}
		System.out.println("==== 3.MME Pool sub task is finished！Thread name : "+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			latch.countDown();
		}
	}
}
