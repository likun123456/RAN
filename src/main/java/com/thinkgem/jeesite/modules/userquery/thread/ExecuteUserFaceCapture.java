package com.thinkgem.jeesite.modules.userquery.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.userquery.entity.TCapUserfacePath;
import com.thinkgem.jeesite.modules.userquery.service.TCapUserfacePathService;

public class ExecuteUserFaceCapture implements Runnable{
	
	private String epgIp;
	private String epgUsername; 
	private String epgPassword;
	private String userIpv4;
	private Integer delay;
	private String imsi;
	private CountDownLatch latch;
	private boolean hasPcrf;
	private String capAllPathId;
	private TCapUserfacePathService tCapUserfacePathService;
	
	public ExecuteUserFaceCapture(String epgIp,String epgUsername,String epgPassword,String userIpv4,Integer delay,String imsi,
			CountDownLatch latch,boolean hasPcrf,String capAllPathId,TCapUserfacePathService tCapUserfacePathService){
		this.epgIp = epgIp;
		this.epgUsername = epgUsername;
		this.epgPassword = epgPassword;
		this.userIpv4 = userIpv4;
		this.delay = delay;
		this.imsi = imsi;
		this.latch = latch;
		this.hasPcrf = hasPcrf;
		this.capAllPathId = capAllPathId;
		this.tCapUserfacePathService = tCapUserfacePathService;
	}

	@Override
	public void run() {
		String filedatetime = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		//文件存放在服务器上的路径(不包括文件名)
		String pfPath = Global.getConfig("PcrfCapPath") + File.separator + filedatetime + File.separator + "userface";
		String returnCapPathAndCapName = "";
		File file = new File(pfPath);
		if(!file.exists()){
			file.mkdirs();
		}
		Process proc = null;
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/usr/bin/perl -w /opt/Ericsson/core/bin/trace_itc_epg.pl -epg "+epgIp+" -user "+epgUsername+
				 " -password "+"'"+epgPassword.replace("&", "\\&")+"'"+" -m "+ (delay * 60) +" -imsi "+imsi+
				 " -destpath " + pfPath + " -subscriberip "+userIpv4;
		if(hasPcrf){
			cmd[2] = cmd[2] + " -waitpcrf";
		}
		System.out.println("===== UserFace cap  ===== "+cmd[2]);
		try {
			proc = Runtime.getRuntime().exec(cmd);//TODO test
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while((line = br.readLine())!=null){
				if(line.contains(".pcap")){
					returnCapPathAndCapName = line.trim();
				}
			}
	        proc.waitFor();
			proc.destroy();
			//录库的时间，格式yyyy-MM-dd HH:mm:ss,相当于结束时间
			String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			if(returnCapPathAndCapName!=""){
				TCapUserfacePath userFacePath = new TCapUserfacePath();
				userFacePath.setPid(capAllPathId);
				userFacePath.setImsi(imsi);
				userFacePath.setPcappath(returnCapPathAndCapName);
				userFacePath.setDatetime(datetime);
				tCapUserfacePathService.save(userFacePath);
			}
			//数据录入到userface抓包单用户表
		System.out.println("==== UserFace cap is finished！Thread name : "+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			latch.countDown();
		}
	}

}
