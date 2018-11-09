package com.thinkgem.jeesite.common.entity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

public class StreamGobbler extends Thread {

	private InputStream is;
	private String type;
	private Logger log;
	private HttpSession session;
	
	
	public StreamGobbler(InputStream is, String type,Logger log) {
		this.is = is;
		this.type = type;
		this.log = log;
	}
	
	
	public StreamGobbler(InputStream is, String type,Logger log,HttpSession session) {
		this.is = is;
		this.type = type;
		this.log = log;
		this.session = session;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			int lineCount=0;
			while((line = br.readLine())!=null){
				lineCount++;
				if (lineCount%200==0){
					if(type.equals("error")){
						log.info("================error ===================" + line);
						if(session!=null){
							session.setAttribute("processText", "执行"+line);
						}
					}else{
						log.info("================debug ==================" + line);
						if(session!=null){
							session.setAttribute("processText", "执行"+line);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
