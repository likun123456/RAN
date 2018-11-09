package com.thinkgem.jeesite.modules.netconfig.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpSession;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
/**
 * 模版命令下发及结果接受类
 * @author Administrator
 *
 */
public class ExecuteExcelTemplate implements Runnable {
	//Process proc = null;
	//BufferedReader br = null;
	//BufferedReader brError = null;
	/*String line = null;
	String lineError = null;*/
	StringBuilder sb = new StringBuilder();
	StringBuilder sbNetName = new StringBuilder();
	TNewnetelement net;
	File fileTxt;
	private HttpSession session;
	private CountDownLatch latch;
	private String excelId;
	private String moduleId;

	public ExecuteExcelTemplate(TNewnetelement net, File fileTxt,HttpSession session,CountDownLatch latch, String excelId, String moduleId) {
		this.net = net;
		this.fileTxt = fileTxt;
		this.session = session;
		this.latch = latch;
		this.excelId = excelId;
		this.moduleId = moduleId;
	}
	
	@Override
	public void run() {
		
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			Queue<String> queue = new LinkedList<String>();
			//将返回的结果按照网元ID的不同存储到session中
			session.setAttribute("capText_"+net.getId(), queue);
			//获取存储到session中的执行日志
			Map<TNewnetelement,String> debugLogMap=(Map<TNewnetelement,String>)session.getAttribute("debugLog");
			StringBuilder sb=new StringBuilder();
			
			String path = "D:/apache-tomcat-8.0.46-PC/commandData/excelTemplate/"+excelId+"/"+moduleId+".txt";
			fileInputStream = new FileInputStream(path);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String line = "";
			int num = 0;
			//300 到 600 毫秒产生随机数
			int min = 300;
	        int max = 600;
	        Random r = new Random();
			while((line = reader.readLine())!=null){
				queue.offer(StringUtils.toHtml(line)+"<br>");
				sb.append(StringUtils.toHtml(line)+"\r\n");
				num ++;
				if(num % 5 == 0) {
					Thread.sleep(r.nextInt(max-min)+min);
				}
			}
			
			//将执行结果，按照网元名称存储的session中，提供给
			Iterator iter=debugLogMap.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry<TNewnetelement,String> entry=(Map.Entry<TNewnetelement, String>)iter.next();
				if(entry.getKey().getFname().equals(net.getFname())){
					entry.setValue(sb.toString());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				inputStreamReader.close();
				fileInputStream.close();
				latch.countDown();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}


	/*@Override
	public void run() {
		try {
			String[] cmd = new String[3];
			cmd[0] = "sh";
			cmd[1] = "-c";
			cmd[2] = "/usr/bin/perl -w /opt/Ericsson/core/bin/eops.pl";
			cmd[2] += " -ip " + net.getIpadr();
			cmd[2] += " -user " + net.getUsername1() + " -password '" + net.getPassword1();
			cmd[2] += "' -conf_file " + Global.getConfig("TpXmlPathTxt") + fileTxt.getName();
           
			try {
				System.out.println("path:"+Global.getConfig("TpXmlPathTxt"));
				System.out.println("cmd:"+cmd[2]);
				proc = Runtime.getRuntime().exec(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			brError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			try {
				Queue<String> queue = new LinkedList<String>();
				Map<String,String> logMap=new HashMap<String,String>();
				//将返回的结果按照网元ID的不同存储到session中
				session.setAttribute("capText_"+net.getId(), queue);
				//获取存储到session中的执行日志
				Map<TNewnetelement,String> debugLogMap=(Map<TNewnetelement,String>)session.getAttribute("debugLog");
				StringBuilder sb=new StringBuilder();
				while ((line = br.readLine()) != null) {
					queue.offer(StringUtils.toHtml(line)+"<br>");
					sb.append(StringUtils.toHtml(line)+"\r\n");
				}
				while ((lineError = brError.readLine()) != null) {
					queue.offer(StringUtils.toHtml(lineError)+"<br>");
					sb.append(StringUtils.toHtml(lineError)+"\r\n");
				}
				proc.waitFor();
				//将执行结果，按照网元名称存储的session中，提供给
				Iterator iter=debugLogMap.entrySet().iterator();
				while(iter.hasNext()){
					Map.Entry<TNewnetelement,String> entry=(Map.Entry<TNewnetelement, String>)iter.next();
					if(entry.getKey().getFname().equals(net.getFname())){
						entry.setValue(sb.toString());
					}
				}
				System.out.println("proc end");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			proc.destroy();
			latch.countDown();
			
		}
	}*/
	
}
