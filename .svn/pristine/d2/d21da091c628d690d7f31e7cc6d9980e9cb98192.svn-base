package com.thinkgem.jeesite.modules.userquery.thread;

import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpSession;

import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.userquery.entity.TCapConfig;
import com.thinkgem.jeesite.modules.userquery.service.TCapPcrfFilterPathService;
import com.thinkgem.jeesite.modules.userquery.service.TCapPcrfPathService;


public class ExectuePcrfCaptureAndFilter implements Runnable{
	
	private String[] netids;
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
	
	public ExectuePcrfCaptureAndFilter(String[] netids,/*TCapConfig capConfig,*/CountDownLatch latch,String msisdn,Integer delay,String startTime,String capAllPathId,
			HttpSession session,TNewnetelementService netElementService,/*TCapPcrfPathService capPcrfPathService,*/TCapPcrfFilterPathService capPcrfFilterPathService){
		this.netids = netids;
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
		CountDownLatch son_latch = new CountDownLatch(netids.length);
		for(String netid : netids){
			ExecutePcrfCapture ep = new ExecutePcrfCapture(netid,/*capConfig,*/son_latch,msisdn,delay,startTime,capAllPathId,session,
					netElementService,/*capPcrfPathService,*/capPcrfFilterPathService);
			Thread t = new Thread(ep);
			t.start();
		}
		try {
			son_latch.await();
			System.out.println("==== 3. Pcrf cap and filter are finished！ Thread name : "+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}


}
