package com.thinkgem.jeesite.modules.userquery.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.thinkgem.jeesite.modules.userquery.entity.TCapPcrfFilterPath;
import com.thinkgem.jeesite.modules.userquery.service.TCapPcrfFilterPathService;

public class ExecutePcrfSecondFilterService implements Runnable{
	
	private String cappath;
	private String capname;
	private String msisdn;
	private String netid;
	private String pid;
	private CountDownLatch latch;
	private String startTime;
	private TCapPcrfFilterPathService capPcrfFilterPathService;
	
	public ExecutePcrfSecondFilterService(String cappath,String capname,String msisdn,String netid,String pid,
			CountDownLatch latch,String startTime,TCapPcrfFilterPathService capPcrfFilterPathService){
		this.cappath = cappath;
		this.capname = capname;
		this.msisdn = msisdn;
		this.netid = netid;
		this.pid = pid;
		this.latch = latch;
		this.startTime = startTime;
		this.capPcrfFilterPathService = capPcrfFilterPathService;
	}

	@Override
	public void run() {
		String[] filter_cmd = new String[3];
		filter_cmd[0] = "sh";
		filter_cmd[1] = "-c";
		filter_cmd[2] = "/opt/Ericsson/core/bin/filter_genchart.pl -filter -srcfile " + cappath + File.separator + capname +
		                " -destpath " + cappath + " -msisdn " + msisdn + " -luafile /opt/Ericsson/core/bin/wnaa_pcrf.lua";
//		filter_cmd[2] = "cat /opt/Ericsson/core/bin/filterStep_"+netid+".txt";
		Process filter_proc = null;
		String pcrfFileName = "";
		try {
			System.out.println("===== second cap step ====="+filter_cmd[2]);
			filter_proc = Runtime.getRuntime().exec(filter_cmd);
			BufferedReader filter_br = new BufferedReader(new InputStreamReader(filter_proc.getInputStream()));
			String filter_line = null;
			while((filter_line = filter_br.readLine())!=null){
				if(filter_line.contains(".pcap")){
					//System.out.println("========== 1" + filter_line );
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
		capPcrfFilterPath.setCapPath(cappath);
		capPcrfFilterPath.setCapName(pcrfFileName);
		capPcrfFilterPath.setStartTime(startTime);
		capPcrfFilterPath.setEndTime(insertdatetime);
		capPcrfFilterPath.setPid(pid);
		capPcrfFilterPathService.save(capPcrfFilterPath);
		latch.countDown();
	}

}
