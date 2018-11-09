package com.thinkgem.jeesite.modules.userquery.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpSession;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.userquery.entity.DetailLog;
import com.thinkgem.jeesite.modules.userquery.entity.TCapConfig;
import com.thinkgem.jeesite.modules.userquery.entity.TCapPcrfFilterPath;
import com.thinkgem.jeesite.modules.userquery.entity.TCapPcrfPath;
import com.thinkgem.jeesite.modules.userquery.service.TCapPcrfFilterPathService;
import com.thinkgem.jeesite.modules.userquery.service.TCapPcrfPathService;


public class ExecutePcrfCapture implements Runnable{
	
	private String netid;
	//private TCapConfig capConfig;
	private CountDownLatch latch;
	private String msisdn;
	private Integer delay;
	private String startTime;
	private String capAllPathId;
	private HttpSession session;
	private TNewnetelementService netElementService;
	//private TCapPcrfPathService capPcrfPathService;
	private TCapPcrfFilterPathService capPcrfFilterPathService;
	
	public ExecutePcrfCapture(String netid,/*TCapConfig capConfig,*/CountDownLatch latch,String msisdn,Integer delay,String startTime,String capAllPathId,
			HttpSession session,TNewnetelementService netElementService,/*TCapPcrfPathService capPcrfPathService,*/TCapPcrfFilterPathService capPcrfFilterPathService){
		this.netid = netid;
		//this.capConfig = capConfig;
		this.latch = latch;
		this.msisdn = msisdn;
		this.delay = delay;
		this.startTime = startTime;
		this.capAllPathId = capAllPathId;
		this.session = session;
		this.netElementService = netElementService;
		//this.capPcrfPathService = capPcrfPathService;
		this.capPcrfFilterPathService = capPcrfFilterPathService;
	}

	@Override
	public void run() {
		TNewnetelement prcfInfo = netElementService.get(netid);
		String ip = prcfInfo.getAdminipadr();
		String username = prcfInfo.getAdminname();
		String password = prcfInfo.getAdminpassword();
		//String cardcode = prcfInfo.getCardcode();
		//String cpuLimit = capConfig.getCpuLimit();
		//String diskLimit = capConfig.getDiskLimit();
		//String checkPeriod = capConfig.getCheckPeriod();
		//设置文件夹的时间，格式yyyy_MM_dd
		String filedatetime = new SimpleDateFormat("yyyy_MM_dd").format(new Date()); 
		//.pcrf文件存放在服务器上的路径(不包括文件名)
		String pfPath = Global.getConfig("PcrfCapPath") + File.separator + filedatetime + File.separator + netid;
		File file = new File(pfPath);
		if(!file.exists()){
			file.mkdirs();
		}
		Process proc = null;
		//String returnCapPathAndCapName = "";//TODO test
		//String allpcrfFileName = "";
		String pcrfFileName = "";
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/opt/Ericsson/core/bin/trace_pcrf.pl -ne "+ip+" -user "+username+" -password "+password+
		         " -m "+ (delay * 60) + " -destpath " + pfPath + " -msisdn "+msisdn+
		         " -hostfile /opt/Ericsson/core/bin/" + Global.getConfig("hostFile");
		System.out.println("===== first cap step ===== "+cmd[2]);
		try {
			proc = Runtime.getRuntime().exec(cmd);//TODO test
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			Queue<String> queue = new LinkedList<String>();
			DetailLog dl = new DetailLog(); 
			List<String> list = new ArrayList<String>();
			session.setAttribute("capText_"+netid, queue);
			while((line = br.readLine())!=null){
				if(line.contains("AppTrace start_trace done")){
					queue.offer("正在抓包..."+"<br>");
				}
				list.add(StringUtils.toHtml(line)+"<br>");
				if(line.contains(".pcap")){
					pcrfFileName = line.trim();
				}
			}
			dl.setPcrfLogList(list);
			session.setAttribute(netid, dl);
	        proc.waitFor();
			proc.destroy();
			Thread.sleep(3000);
			//得到文件名字不包括路径
			String filter_arg[] = pcrfFileName.split("/");
			pcrfFileName = filter_arg[filter_arg.length-1];
			//录库的时间，格式yyyy-MM-dd HH:mm:ss,相当于结束时间
			String insertdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//将此次PCRF抓包过滤后产生的文件名和路径存入过滤的PCRF子表中(t_cap_pcrf_filter_path)
			
			TCapPcrfFilterPath capPcrfFilterPath = new TCapPcrfFilterPath();
			capPcrfFilterPath.setNetid(netid);
			capPcrfFilterPath.setCapName(pcrfFileName);
			capPcrfFilterPath.setCapPath(pfPath);
			capPcrfFilterPath.setStartTime(startTime);
			capPcrfFilterPath.setEndTime(insertdatetime);
			capPcrfFilterPath.setPid(capAllPathId);
			
			capPcrfFilterPathService.save(capPcrfFilterPath);
		    //下面开始执行过滤msisdn的操作步骤：
			Queue<String> mmequeue = new LinkedList<String>();
			Queue<String> epgqueue = new LinkedList<String>();
			session.setAttribute("capTextMmePool", mmequeue);
			session.setAttribute("capTextEpgPool", epgqueue);
			mmequeue.offer("等待PCRF过滤抓包文件..."+"<br>");
			epgqueue.offer("等待PCRF过滤抓包文件..."+"<br>");
			queue.offer("PCRF过滤抓包文件..."+"<br>");
		    //this.filter(pfPath, allpcrfFileName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			latch.countDown();
		}
	}
	
	private void filter(String pfPath,String allpcrfFileName){
		//设置文件夹的时间，格式yyyy_MM_dd
		String[] filter_cmd = new String[3];
		filter_cmd[0] = "sh";
		filter_cmd[1] = "-c";
		filter_cmd[2] = "/opt/Ericsson/core/bin/filter_genchart.pl -filter -srcfile " + pfPath + File.separator + allpcrfFileName +
		                " -destpath " + pfPath + " -msisdn " + msisdn + " -luafile /opt/Ericsson/core/bin/wnaa_pcrf.lua";
//		filter_cmd[2] = "type E:\\opt\\Ericsson\\core\\bin\\filterStep_"+netid+".txt";
//		filter_cmd[2] = "ping 192.168.124." + netid;//TODO test
//		filter_cmd[2] = "ping 127.0.0.1";//TODO test
		Process filter_proc = null;
		String pcrfFileName = "";//TODO test
		try {
			System.out.println("===== second cap step ====="+filter_cmd[2]);
			filter_proc = Runtime.getRuntime().exec(filter_cmd);//TODO test
			BufferedReader filter_br = new BufferedReader(new InputStreamReader(filter_proc.getInputStream()));
			String filter_line = null;
			while((filter_line = filter_br.readLine())!=null){
				if(filter_line.contains(".pcap")){
					pcrfFileName = filter_line.trim();
				}
			}
			filter_proc.waitFor();
			filter_proc.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		//得到文件名字不包括路径
		String filter_arg[] = pcrfFileName.split("/");
		pcrfFileName = filter_arg[filter_arg.length-1];
		//录库的时间，格式yyyy-MM-dd HH:mm:ss,相当于结束时间
		String insertdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//将此次PCRF抓包过滤后产生的文件名和路径存入过滤的PCRF子表中(t_cap_pcrf_filter_path)
		
		TCapPcrfFilterPath capPcrfFilterPath = new TCapPcrfFilterPath();
		capPcrfFilterPath.setNetid(netid);
		capPcrfFilterPath.setCapName(pcrfFileName);
		capPcrfFilterPath.setCapPath(pfPath);
		capPcrfFilterPath.setStartTime(startTime);
		capPcrfFilterPath.setEndTime(insertdatetime);
		capPcrfFilterPath.setPid(capAllPathId);
		
		capPcrfFilterPathService.save(capPcrfFilterPath);
	}
	

}
