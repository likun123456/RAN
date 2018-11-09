/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.web;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.constant.DictConstant;
import com.thinkgem.jeesite.common.entity.FTPInfo;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.SFtpCarrierUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.userquery.entity.AutoExecuteEntity;
import com.thinkgem.jeesite.modules.userquery.entity.CapText;
import com.thinkgem.jeesite.modules.userquery.entity.DetailLog;
import com.thinkgem.jeesite.modules.userquery.entity.TCapAllPath;
import com.thinkgem.jeesite.modules.userquery.entity.TCapConfig;
import com.thinkgem.jeesite.modules.userquery.entity.TCapEpgPath;
import com.thinkgem.jeesite.modules.userquery.entity.TCapMmePath;
import com.thinkgem.jeesite.modules.userquery.entity.TCapPcrfFilterPath;
import com.thinkgem.jeesite.modules.userquery.entity.TCapPcrfPath;
import com.thinkgem.jeesite.modules.userquery.entity.TCapUserfacePath;
import com.thinkgem.jeesite.modules.userquery.entity.UserSignalTraceEntity;
import com.thinkgem.jeesite.modules.userquery.service.TCapAllPathService;
import com.thinkgem.jeesite.modules.userquery.service.TCapConfigService;
import com.thinkgem.jeesite.modules.userquery.service.TCapEpgPathService;
import com.thinkgem.jeesite.modules.userquery.service.TCapMmePathService;
import com.thinkgem.jeesite.modules.userquery.service.TCapPcrfFilterPathService;
import com.thinkgem.jeesite.modules.userquery.service.TCapPcrfPathService;
import com.thinkgem.jeesite.modules.userquery.service.TCapUserfacePathService;
import com.thinkgem.jeesite.modules.userquery.thread.ExectuePcrfCaptureAndFilter;
import com.thinkgem.jeesite.modules.userquery.thread.ExecuteMmePoolCapture;
import com.thinkgem.jeesite.modules.userquery.thread.ExecutePcrfSecondFilterService;
import com.thinkgem.jeesite.modules.userquery.thread.ExecuteSaegwPoolCapture;
import com.thinkgem.jeesite.modules.userquery.thread.ExecuteUserFaceCapture;





/**
 * 单用户信令追踪Controller
 * @author zhuguangrui
 * @version 2017-05-25
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "${adminPath}/userquery/unOnline")
public class UserUnOnlineQueryController extends BaseController {
	
	public static String status="finish";
	
	@Autowired
	private TPoolService poolService;
	@Autowired
	private TNewnetelementService netElementService;
	@Autowired
	private TCapAllPathService capAllPathService;
	@Autowired
	private TCapMmePathService capMmePathService;
	@Autowired
	private TCapEpgPathService capEpgPathService;
	@Autowired
	private TCapPcrfPathService capPcrfPathService;
	@Autowired
	private TCapPcrfFilterPathService capPcrfFilterPathService;
	@Autowired
	private TCapConfigService capConfigService;
	@Autowired
	private TCapUserfacePathService tCapUserfacePathService;
	private File uploadFile;//上传文件
	
	
	
	@RequiresPermissions("userquery:unOnline:view")
	@RequestMapping(value = {"show", ""})
	public String show() {
		return "modules/userquery/userUnOnlineTrace";
	}
	
	@RequestMapping(value ="uploadFile")
	public String uploadFile(@RequestParam(value = "upload", required = false) MultipartFile file,HttpServletRequest request,Model model){
		if(!file.isEmpty()) {
			String requestPath = "http://10.14.199.50:8080/epc/file/html/1502333350.html";
			String filedatetime = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
    		String pfPath = Global.getConfig("PcrfCapPath") + File.separator + filedatetime + File.separator;
            try {  
        		File f = new File(pfPath);
        		if(!f.exists()){
        			f.mkdir();
        		}
                // 文件保存路径  
                String filePath = pfPath + file.getOriginalFilename();  
                // 转存文件  
                file.transferTo(new File(filePath));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }
            String hostFile = Global.getConfig("hostFile");
            String[] cmd = new String[3];
    		cmd[0] = "sh";
    		cmd[1] = "-c";
    		cmd[2] = "/opt/Ericsson/core/bin/filter_genchart.pl -hostfile /opt/Ericsson/core/bin/"+hostFile+
    		         " -destpath " + pfPath + " -src " + file.getOriginalFilename();
    		Process proc = null;
    		String htmlFilePathAndName = "";
    		try {
    			System.out.println("===== unOnline cmd: ====="+cmd[2]);
    			proc = Runtime.getRuntime().exec(cmd);
    			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
    			String line = null;
    			while((line = br.readLine())!=null){
    				if(line.contains(".html")){
    					System.out.println("========== html:" + line );
    					htmlFilePathAndName = line.trim();
    				}
    			}
    			proc.waitFor();
    			proc.destroy();
    		} catch (Exception e) {
    			e.printStackTrace();
    			System.out.println(e.getMessage());
    		}
    		//如果执行lua没抓取到生成的pcap包，则返回empty
    		if(!htmlFilePathAndName.trim().equals("")){
    			File htmlFile = new File(htmlFilePathAndName);
    			String _arg[] = htmlFilePathAndName.split("/");
    			String htmlFileName = _arg[_arg.length-1];
    			//.html文件存在服务器tomcat下的路径(不包括文件名)
    			String hfPath = request.getSession().getServletContext().getRealPath("/")+"file/html/";
    			//.html文件存在服务器tomcat下的全路径
    			String tomcatHtmlPath = hfPath + htmlFileName;
    			//.html文件用于发送http请求的tomcat下的全路径
    			requestPath = this.getBasePath(request)+request.getContextPath() +"/file/html/"+htmlFileName;
    			//录库的时间，格式yyyy-MM-dd HH:mm:ss
    			String insertdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    			Process proc1 = null;
    			if(htmlFile.exists()&&!htmlFileName.equals("")){
    				File pf = new File(hfPath);
    				if(!pf.exists()){
    					pf.mkdirs();
    				}
    				try {
    					proc1 =Runtime.getRuntime().exec("cp " + htmlFile.getPath() + " " + tomcatHtmlPath);
    					System.out.println("=== execute === " +"cp " + htmlFile.getPath() + " " + tomcatHtmlPath);
    					proc1.waitFor();
    				} catch (Exception e) {
    					e.printStackTrace();
    				} finally {
    					proc1.destroy();
    				}
    			}
    			//将数据更新到当前全量表中，即(t_cap_all_path)中
    			TCapAllPath capAllPath = new TCapAllPath();
    			capAllPath.setHtmlPath(requestPath);
    			capAllPath.setEndTime(insertdatetime);
    			capAllPathService.save(capAllPath);
    		}
    		model.addAttribute("requestPath", requestPath);
	    }
		return "modules/userquery/userUnOnlineTrace";
	}
	
	public boolean upload(String netId,List<FTPInfo> ftpInfos) throws Exception{
		if(ftpInfos == null || ftpInfos.size() == 0){
			return false;
		}
		boolean flag = false;
		for(FTPInfo ftpInfo : ftpInfos){
			SFtpCarrierUtil ftpCarrier = new SFtpCarrierUtil(ftpInfo);
			ftpCarrier.connect();
			String remoteFileDir = Global.getConfig("EbmLoguploadPath")+netId;
			String remoteFilePath = remoteFileDir+"_xml/";
			File file = new File(remoteFilePath);
        	if(!file.exists()){
        		file.mkdirs();
        	}
        	if(getUploadFile() != null){
        		flag = ftpCarrier.uploadFile(remoteFilePath,uploadFile.getName(),getUploadFile().getAbsolutePath());
        	}
			ftpCarrier.disconnect();
			if(!flag){
				return false;
			}
		}
		return flag;
	}
	
	@ResponseBody
	@RequiresPermissions("userquery:signal:trace")
	@RequestMapping(value = "trace")
	public String trace(UserSignalTraceEntity userSignalTraceEntity, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		
    	//1.设置当有用户执行抓包任务后，其他登陆用户禁止抓包
    	status = "running";
    	//2.在所有任务执行前先删除/home/capture/stopCapture文件
    	File file = new File("/home/capture/stopCapture");
    	if(file.exists()){
    		file.delete();
    	}
    	//3.执行抓包任务
		return processCap(request,userSignalTraceEntity);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "pcrfFilter")
	public String executeSinglePcrfCatchPackage(String pcrfnames,String msisdn,String starttime,String p_id,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){
    	//开始过滤的时间，格式yyyy-MM-dd HH:mm:ss,
		String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    	//将PCRF网元二次过滤执行后的这次记录，录入全量表中
		TCapAllPath capAllPath = new TCapAllPath();
    	capAllPath.setNetNames(pcrfnames.substring(0, pcrfnames.length()-1));
    	capAllPath.setMsisdn(msisdn);
    	capAllPath.setStartTime(startTime);
    	
		String pid = capAllPathService.saveAndGetId(capAllPath);
		
    	List<TCapPcrfPath> capPcrfPathVOlist = capPcrfPathService.queryPcrfInfoById(p_id);
    	int count = capPcrfPathVOlist.size();
    	CountDownLatch latch = new CountDownLatch(count);
    	//多线程执行二次过滤pcrf网元
    	for(int i=0;i<count;i++){
    		String netid = capPcrfPathVOlist.get(i).getNetid();
    		String cappath = capPcrfPathVOlist.get(i).getCapPath();
    		String capname = capPcrfPathVOlist.get(i).getCapName();
    		ExecutePcrfSecondFilterService pcrfSecondFilterService = new ExecutePcrfSecondFilterService(cappath,capname,msisdn,netid,pid,latch,startTime,capPcrfFilterPathService);
        	Thread t = new Thread(pcrfSecondFilterService);
        	t.start();
    	}
    	String result = "";
    	try {
			latch.await();
			//确保所有PCRF网元过滤 完成之后再进行合成流程图
			result =  executeHtml(request,pid,msisdn);
			System.out.println("==== second filter pcrf finished ====");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
    	return result;
    }
	
    private String processCap(HttpServletRequest request,UserSignalTraceEntity userSignalTraceEntity) {
    	HttpSession session = request.getSession();
    	String result = null;
    	String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    	//2.录入全量表历史展示表t_pcrf_cap,返回这条历史展示表记录的id,包括mme池组和pcrf网元
    	String names = "";
    	String mmePoolNames = null;
    	String saegwPoolNames = null;
    	String pcrfNetNames = null;
    	String mmePoolIds = null;
    	String saegwPoolIds = null;
    	String pcrfNetIds = null;
    	String epgIp = null;
    	String epgUsername = null;
    	String epgPassword = null;
    	String userIpv4 = null;
    	boolean hasPcrf = false;
    	int captureScope = userSignalTraceEntity.getCaptureScope();
    	//自动判断节点 1:（MME+SAEGW） 2:（MME+SAEGW+用户面）3:（MME+SAEGW+用户面+PCRF）4:手动判断节点
    	if(captureScope==1){
    		AutoExecuteEntity ae = doMmePlusSaegw(userSignalTraceEntity,captureScope);
    		mmePoolIds = ae.getMmePoolid();
    		saegwPoolIds = ae.getSaegwPoolid();
    		mmePoolNames = ae.getMmePoolName();
    		saegwPoolNames = ae.getSaegwPoolName();
    	}else if(captureScope==2){
    		AutoExecuteEntity ae = doMmePlusSaegw(userSignalTraceEntity,captureScope);
    		mmePoolIds = ae.getMmePoolid();
    		saegwPoolIds = ae.getSaegwPoolid();
    		mmePoolNames = ae.getMmePoolName();
    		saegwPoolNames = ae.getSaegwPoolName();
    		userIpv4 = ae.getUserIpv4();
    		TNewnetelement tNewnetelement= netElementService.findNetIpByNetName(ae.getSaegwNetName());
    		if(tNewnetelement!=null){
    			epgIp = tNewnetelement.getIpadr();
        		epgUsername = tNewnetelement.getUsername1();
        		epgPassword = tNewnetelement.getPassword1();
    		}
    	}else if(captureScope==3){
    		AutoExecuteEntity ae = doMmePlusSaegw(userSignalTraceEntity,captureScope);
    		mmePoolIds = ae.getMmePoolid();
    		saegwPoolIds = ae.getSaegwPoolid();
    		mmePoolNames = ae.getMmePoolName();
    		saegwPoolNames = (ae.getSaegwPoolName()==null?"":ae.getSaegwPoolName().trim());
    		userIpv4 = ae.getUserIpv4();
    		TNewnetelement tNewnetelement= netElementService.findNetIpByNetName(ae.getSaegwNetName());
    		if(tNewnetelement!=null){
    			epgIp = tNewnetelement.getIpadr();
        		epgUsername = tNewnetelement.getUsername1();
        		epgPassword = tNewnetelement.getPassword1();
    		}
    		AutoExecuteEntity aee = AutoAudgePCRF(userSignalTraceEntity);
    		pcrfNetNames = aee.getPcrfNetNames();
    		pcrfNetIds = aee.getPcrfNetIds();
    		hasPcrf = true;
    	}else{
    		if(userSignalTraceEntity.getMmePools() != null){
        		mmePoolNames = userSignalTraceEntity.getMmePools().getFpoolname();
        		mmePoolIds = userSignalTraceEntity.getMmePools().getId();
        	}
        	if(userSignalTraceEntity.getSaegwPools() != null){
        		saegwPoolNames = userSignalTraceEntity.getSaegwPools().getFpoolname();
        		saegwPoolIds = userSignalTraceEntity.getSaegwPools().getId();
        	}
        	if(userSignalTraceEntity.getPcrfNetElements() != null){
        		pcrfNetNames = userSignalTraceEntity.getPcrfNetElements().getFname();
        		pcrfNetIds = userSignalTraceEntity.getPcrfNetElements().getId();
        		hasPcrf = true;
        	}
    	}
    	if(StringUtils.isNotBlank(pcrfNetNames)){
    		names = (StringUtils.isNotBlank(mmePoolNames))?(pcrfNetNames+"/"+mmePoolNames):(pcrfNetNames);
    	}else{
    		names = mmePoolNames;
    	}
    	//加入EPG池组后，相应的录入全量表的names字段的逻辑也有相应的改变
    	String newnames = "";
    	if(StringUtils.isNotBlank(names)){
    		newnames = (StringUtils.isNotBlank(saegwPoolNames))?(names+"/"+saegwPoolNames):(names);
    	}else{
    		newnames = saegwPoolNames;
    	}
    	Map<String,String> numberMap = getImsiAndMsisdn(userSignalTraceEntity.getNumberType(),userSignalTraceEntity.getNumber());
    	if(!numberMap.containsKey("error")){
    		TCapAllPath capAllPath = new TCapAllPath();
        	capAllPath.setNetNames(newnames);
        	capAllPath.setMsisdn(numberMap.get("msisdn"));
        	capAllPath.setStartTime(startTime);
        	
    		String capAllPathId = capAllPathService.saveAndGetId(capAllPath);
    		//3.设置多线程CountDown标志
        	int count = 0;
        	if(StringUtils.isNotBlank(pcrfNetIds)){
        		count++;
        	}
        	if(StringUtils.isNotBlank(mmePoolIds)){
        		count++;
        	}
        	if(StringUtils.isNotBlank(saegwPoolIds)){
        		count++;
        	}
        	if(captureScope==2||captureScope==3){
        		count++;
        	}
        	CountDownLatch latch = new CountDownLatch(count);
        	if(StringUtils.isNotBlank(mmePoolIds)){
        		executeMMEPOOL(mmePoolIds,mmePoolNames,latch,numberMap.get("imsi"),userSignalTraceEntity.getCaptureDuration(),startTime,capAllPathId,request.getSession(),hasPcrf,captureScope);
        	}
        	if(StringUtils.isNotBlank(saegwPoolIds)){
        		executeSAEGWPOOL(saegwPoolIds,saegwPoolNames,latch,numberMap.get("imsi"),userSignalTraceEntity.getCaptureDuration(),startTime,capAllPathId,request.getSession(),hasPcrf,captureScope);
        	}
        	if(StringUtils.isNotBlank(pcrfNetIds)){
        		String[] pcrfNetIdArray = pcrfNetIds.split(",");
        		String[] pcrfNetNameArray = pcrfNetNames.split(",");
        		session.setAttribute("pcrfNetIdArray", pcrfNetIdArray);
        		session.setAttribute("pcrfNetNameArray", pcrfNetNameArray);
        		executePCRF(pcrfNetIdArray,latch,numberMap.get("msisdn"),userSignalTraceEntity.getCaptureDuration(),startTime,capAllPathId,request.getSession());
        	}
        	if((StringUtils.isNotBlank(epgIp))&&(captureScope==2||captureScope==3)){
        		executeUserFace(epgIp,epgUsername,epgPassword,userIpv4,userSignalTraceEntity.getCaptureDuration(),numberMap.get("imsi"),hasPcrf,latch,capAllPathId,tCapUserfacePathService);
        	}
        	try {
    			latch.await();
    			if(StringUtils.isNotBlank(mmePoolIds)){
    				Queue<String> mmequeue = new LinkedList<String>();
    				session.setAttribute("capTextMmePool", mmequeue);
    				mmequeue.offer("正在合并抓包、去重、生成流程图..."+"<br>");
    	    	}
    	    	if(StringUtils.isNotBlank(saegwPoolIds)){
    	    		Queue<String> epgqueue = new LinkedList<String>();
    	    		session.setAttribute("capTextEpgPool", epgqueue);
    	    		epgqueue.offer("正在合并抓包、去重、生成流程图..."+"<br>");
    	    	}
    	    	if(StringUtils.isNotBlank(pcrfNetIds)){
    	    		String[] pcrfNetIdArray = pcrfNetIds.split(",");
    	    		for(int i=0;i<pcrfNetIdArray.length;i++){
    	    			Queue<String> queue = new LinkedList<String>();
    					session.setAttribute("capText_"+pcrfNetIdArray[i], queue);
    					queue.offer("正在合并抓包、去重、生成流程图..."+"<br>");
    				}
    	    	}
    	    	long start = System.currentTimeMillis();
    			result = executeHtml(request,capAllPathId,numberMap.get("msisdn"));
    			long end = System.currentTimeMillis();
    			
    			System.out.println("filter_genchart.pl use time" + (end-start)/1000 + "s");
    			
    			if(StringUtils.isNotBlank(mmePoolIds)){
    				Queue<String> mmequeue = new LinkedList<String>();
    				session.setAttribute("capTextMmePool", mmequeue);
    				mmequeue.offer("信令追踪结束！"+"<br>");
    	    	}
    	    	if(StringUtils.isNotBlank(saegwPoolIds)){
    	    		Queue<String> epgqueue = new LinkedList<String>();
    	    		session.setAttribute("capTextEpgPool", epgqueue);
    	    		epgqueue.offer("信令追踪结束！"+"<br>");
    	    	}
    	    	if(StringUtils.isNotBlank(pcrfNetIds)){
    	    		String[] pcrfNetIdArray = pcrfNetIds.split(",");
    	    		for(int i=0;i<pcrfNetIdArray.length;i++){
    	    			Queue<String> queue = new LinkedList<String>();
    					session.setAttribute("capText_"+pcrfNetIdArray[i], queue);
    					queue.offer("信令追踪结束！"+"<br>");
    				}
    	    	}
    	    	Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		} finally{
    			status = "finish";
    		}
    	}else{
    		result = "error";
    		status="finish";
    		if(StringUtils.isNotBlank(mmePoolIds)){
				Queue<String> mmequeue = new LinkedList<String>();
				session.setAttribute("capTextMmePool", mmequeue);
				mmequeue.offer("error");
	    	}
	    	if(StringUtils.isNotBlank(saegwPoolIds)){
	    		Queue<String> epgqueue = new LinkedList<String>();
	    		session.setAttribute("capTextEpgPool", epgqueue);
	    		epgqueue.offer("error");
	    	}
	    	if(StringUtils.isNotBlank(pcrfNetIds)){
	    		String[] pcrfNetIdArray = pcrfNetIds.split(",");
	    		for(int i=0;i<pcrfNetIdArray.length;i++){
	    			Queue<String> queue = new LinkedList<String>();
					session.setAttribute("capText_"+pcrfNetIdArray[i], queue);
					queue.offer("error");
				}
	    	}
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	return result;
	}
    

	

	private AutoExecuteEntity AutoAudgePCRF(UserSignalTraceEntity userSignalTraceEntity) {
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setType("3");
		List<TNewnetelement> list = netElementService.findList(tnewnetelement);
		String ipStr = "";
		String username = "";
		String password = "";
		String pcrfNetNames = "";
		String pcrfNetIds = "";
		Process proc = null;
		for(TNewnetelement e : list) {
			if("".equals(username)) {
				username = e.getUsername1();
			}
			if("".equals(password)) {
				password = e.getPassword1();
			}
			ipStr += " -ip " + e.getIpadr();
		}
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/usr/bin/perl -w /opt/Ericsson/core/bin/get_pcrf.pl"+ipStr+
		         " -user "+username+" -password '"+password+"' -"+userSignalTraceEntity.getNumberType()+" "+userSignalTraceEntity.getNumber()+
		         " -hostfile /opt/Ericsson/core/bin/oamhosts";
		System.out.println(cmd[2]);
		try {
			proc = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while((line = br.readLine())!=null){
				if(line.contains("pcrf:")){
					pcrfNetNames = line.split(":")[1].trim();
					System.out.println("+++++++++++++++ "+pcrfNetNames);
				}
			}
	        proc.waitFor();
			proc.destroy();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		if(!pcrfNetNames.equals("")){
			System.out.println("#####################pcrfNetNames:"+pcrfNetNames);
			String[] pcrfNetNameArr = pcrfNetNames.split(",");
			String[] pcrfNetIdArr = new String[pcrfNetNameArr.length];
			for(int i=0;i<pcrfNetNameArr.length;i++){
				pcrfNetIdArr[i] = netElementService.findNetIpByNetName(pcrfNetNameArr[i].trim()).getId();
				if(i!=pcrfNetNameArr.length-1){
					pcrfNetIds = pcrfNetIds + pcrfNetIdArr[i] + ",";
				}else{
					pcrfNetIds = pcrfNetIds + pcrfNetIdArr[i];
				}
				
			}
		}
		AutoExecuteEntity autoExecuteEntity = new AutoExecuteEntity();
		System.out.println("+++++++++++++++ "+pcrfNetNames);
		System.out.println("+++++++++++++++ "+pcrfNetIds);
		autoExecuteEntity.setPcrfNetIds(pcrfNetIds);
		autoExecuteEntity.setPcrfNetNames(pcrfNetNames);
		return autoExecuteEntity;
	}

	private AutoExecuteEntity doMmePlusSaegw(UserSignalTraceEntity userSignalTraceEntity,int captureScope) {
    	TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setType("1");
		List<TNewnetelement> mmelist = netElementService.findList(tnewnetelement);
		String ipStr = "";
		String username = "";
		String password = "";
		String mmeNetName = "";
		String saegwNetName = "";
		String userIpv4 = "";
		Process proc = null;
		for(TNewnetelement e : mmelist) {
			if("".equals(username)) {
				username = e.getUsername1();
			}
			if("".equals(password)) {
				password = e.getPassword1();
			}
			ipStr += " -sgsn " + e.getIpadr();
		}
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/usr/bin/perl -w /opt/Ericsson/core/bin/get_userinfo_detail.pl"+ipStr+
		         " -user "+username+" -password "+password+" -"+userSignalTraceEntity.getNumberType()+" "+userSignalTraceEntity.getNumber()+
		         " -hostfile /opt/Ericsson/core/bin/" + Global.getConfig("hostFile");
		System.out.println(cmd[2]);
		try {
			proc = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while((line = br.readLine())!=null){
				if(line.contains("mme:")){
					mmeNetName = line.split(":")[1];
				}
				if(captureScope==2||captureScope==3){
					if(line.contains("Active PDN connection:")){
						out:while((line = br.readLine())!=null){
							if(line.contains("apn:")){
								if(!line.contains("ims")){
									while((line = br.readLine())!=null){
										if(line.contains("pgw:")){
											line = br.readLine();
											saegwNetName = line.split(":")[1].trim();
											break out;
										}
									}
								}
							}
							if(line.contains("user ipv4:")){
								userIpv4 = line.split(":")[1].trim();
								break;
							}
						}
					}
					if(line.contains("user ipv4:")){
						userIpv4 = line.split(":")[1].trim();
						break;
					}
				}
			}
	        proc.waitFor();
			proc.destroy();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		AutoExecuteEntity ae = new AutoExecuteEntity();
		if(!mmeNetName.equals("")){
			//根据mme网元查找到对应归属池组的id
			String mmePoolid = String.valueOf(netElementService.findPoolidByNetName(mmeNetName));
			//根据池组id查找对应的池组的名字
			String mmePoolName = poolService.findPoolNameById(mmePoolid);
			String mmeLastStr = mmePoolName.substring(mmePoolName.length()-1);
			List<TPool> saegwPoolList = poolService.queryPoolListByType(DictConstant.POOL_TYPE_SAEGW);
			String saegwPoolName = "";
			String saegwLastStr = "";
			String saegwPoolid = "";
			for(TPool t:saegwPoolList){
				saegwLastStr = t.getFpoolname().substring(t.getFpoolname().length()-1);//截取saegw池组名字最后一位
				if(saegwLastStr.equals(mmeLastStr)){
					saegwPoolid = t.getId();
					saegwPoolName = t.getFpoolname();
				}
			}
			ae.setMmePoolid(mmePoolid);
			ae.setMmePoolName(mmePoolName);
			ae.setSaegwPoolid(saegwPoolid);
			ae.setSaegwPoolName(saegwPoolName);
			ae.setSaegwNetName(saegwNetName);
			ae.setUserIpv4(userIpv4);
		}
		return ae;
	}
	
	//单线程执行用户面抓包
	private void executeUserFace(String epgIp, String epgUsername, String epgPassword, String userIpv4,Integer delay,String imsi,boolean hasPcrf,CountDownLatch latch,String capAllPathId,TCapUserfacePathService tCapUserfacePathService) {
		ExecuteUserFaceCapture up = new ExecuteUserFaceCapture(epgIp,epgUsername,epgPassword,userIpv4,delay,imsi,latch,hasPcrf,capAllPathId,tCapUserfacePathService);
		Thread t = new Thread(up);
		t.start();
	}


	//单线程执行MME池组抓包
  	private void executeMMEPOOL(String mmePoolIds,String mmePoolNames,CountDownLatch latch,String imsi,Integer delay,String startTime,String capAllPathId,HttpSession session,boolean hasPcrf,int captureScope) {
  		ExecuteMmePoolCapture mp = new ExecuteMmePoolCapture(mmePoolIds,mmePoolNames,latch,imsi,delay,startTime,capAllPathId,session,netElementService,capMmePathService,hasPcrf,captureScope);
  		Thread t = new Thread(mp);
  		t.start();
  	}
    
    //单线程执行SAEGW池组抓包	
    private void executeSAEGWPOOL(String saegwPoolIds,String saegwPoolNames,CountDownLatch latch,String imsi,Integer delay,String startTime,String capAllPathId,HttpSession session,boolean hasPcrf,int captureScope) {
    	ExecuteSaegwPoolCapture sp = new ExecuteSaegwPoolCapture(saegwPoolIds,saegwPoolNames,latch,imsi,delay,startTime,capAllPathId,session,netElementService,capEpgPathService,hasPcrf,captureScope );
		Thread t = new Thread(sp);
		t.start();
	}
	
	//多线程执行PCRF网元抓包
	private void executePCRF(String[] netids,CountDownLatch latch,String msisdn,Integer delay,String startTime,String capAllPathId,HttpSession session) {
		//TCapConfig capConfig = capConfigService.get("0");
		ExectuePcrfCaptureAndFilter pf = new ExectuePcrfCaptureAndFilter(netids,/*capConfig,*/latch,
				msisdn,delay,startTime,capAllPathId,session,netElementService/*,capPcrfPathService*/,capPcrfFilterPathService);
		Thread t = new Thread(pf);
		t.start();
	}
    
    public String executeHtml(HttpServletRequest request,String capAllPathId,String msisdn) {
		//设置文件夹的时间，格式yyyy_MM_dd
		String filedatetime = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		String pfPath = Global.getConfig("PcrfCapPath") + File.separator + filedatetime;
		String hostFile = Global.getConfig("hostFile");
		TCapPcrfFilterPath pcrfFilterPath = new TCapPcrfFilterPath();
		pcrfFilterPath.setPid(capAllPathId);
		List<TCapPcrfFilterPath> paths = capPcrfFilterPathService.findList(pcrfFilterPath);
		StringBuffer capPathSb = new StringBuffer();
		for(int i=0;i<paths.size();i++){
        	if(i!=0){
        		capPathSb.append(","+paths.get(i).getCapPath()+File.separator+paths.get(i).getCapName());
        	}else{
        		capPathSb.append(paths.get(i).getCapPath()+File.separator+paths.get(i).getCapName());
        	}
        }
		String capPaths = capPathSb.toString();
		//System.out.println("==== capPaths ===="+capPaths);
		TCapMmePath capMmePath = new TCapMmePath();
		capMmePath.setPid(capAllPathId);
		List<TCapMmePath> mmePoolPaths = capMmePathService.findList(capMmePath);
		String poolcapPaths = "";
        if(mmePoolPaths.size()>0){
        	poolcapPaths = mmePoolPaths.get(0).getPcapPath() + File.separator + mmePoolPaths.get(0).getPcapName();
        }
		
		//System.out.println("==== poolcapPaths ===="+poolcapPaths);
		String src = "";
    	if(StringUtils.isNotBlank(capPaths)){
    		src = (StringUtils.isNotBlank(poolcapPaths))?(capPaths+","+poolcapPaths):(capPaths);
    	}else{
    		src = poolcapPaths;
    	}
    	
    	TCapEpgPath capEpgPath = new TCapEpgPath();
    	capEpgPath.setPid(capAllPathId);
    	//将EPG池组的所抓包路径加上
    	List<TCapEpgPath> epgPoolPaths = capEpgPathService.findList(capEpgPath);
    	String saegwpoolcapPaths = "";
		if(epgPoolPaths.size()>0){
			saegwpoolcapPaths = epgPoolPaths.get(0).getPcapPath() + File.separator + epgPoolPaths.get(0).getPcapName();
        }
		//System.out.println("==== saegwpoolcapPaths ===="+saegwpoolcapPaths);
		String newsrc = "";
		if(StringUtils.isNotBlank(src)){
			newsrc = (StringUtils.isNotBlank(saegwpoolcapPaths))?(src+","+saegwpoolcapPaths):(src);
    	}else{
    		newsrc = saegwpoolcapPaths;
    	}
		
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/opt/Ericsson/core/bin/filter_genchart.pl -hostfile /opt/Ericsson/core/bin/"+hostFile+
		         " -destpath " + pfPath + " -src " + newsrc + " -msisdn " + msisdn;
//		cmd[2] = "type E:\\opt\\Ericsson\\core\\bin\\html.txt";
//		cmd[2] = "ping 127.0.0.1";
		Process proc = null;
//		String htmlFilePathAndName = "E://opt//Ericsson//core//PcrfCap//2017_02_06//1499194477.html";//TODO test
//		String pcrfFilePathAndName = "E://opt//Ericsson//core//PcrfCap//2017_02_06//1499194477.pcap";//TODO test
		String htmlFilePathAndName = "";
		String pcrfFilePathAndName = "";
		try {
			System.out.println("===== last cap step ====="+cmd[2]);
			proc = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while((line = br.readLine())!=null){
				if(line.contains(".pcap")){
					System.out.println("========== 1" + line );
					pcrfFilePathAndName = line.trim();
				}
				if(line.contains(".html")){
					System.out.println("========== 2" + line );
					htmlFilePathAndName = line.trim();
				}
			}
			proc.waitFor();
			proc.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		//如果执行lua没抓取到生成的pcap包，则返回empty
		if(!pcrfFilePathAndName.trim().equals("")){
			File htmlFile = new File(htmlFilePathAndName);
			String arg[] = pcrfFilePathAndName.split("/");
			String pcrfFileName = arg[arg.length-1];
			String _arg[] = htmlFilePathAndName.split("/");
			String htmlFileName = _arg[_arg.length-1];
			//.html文件存在服务器tomcat下的路径(不包括文件名)
			String hfPath = request.getSession().getServletContext().getRealPath("/")+"file/html/";
			//.html文件存在服务器tomcat下的全路径
			String tomcatHtmlPath = hfPath + htmlFileName;
			//.html文件用于发送http请求的tomcat下的全路径
			String requestPath = this.getBasePath(request)+request.getContextPath() +"/file/html/"+htmlFileName;
			//录库的时间，格式yyyy-MM-dd HH:mm:ss
			String insertdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			Process proc1 = null;
			if(htmlFile.exists()&&!htmlFileName.equals("")){
				File pf = new File(hfPath);
				if(!pf.exists()){
					pf.mkdirs();
				}
				try {
					proc1 =Runtime.getRuntime().exec("cp " + htmlFile.getPath() + " " + tomcatHtmlPath);
					System.out.println("=== execute === " +"cp " + htmlFile.getPath() + " " + tomcatHtmlPath);
					proc1.waitFor();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					proc1.destroy();
				}
			}
			
//			//2.数据录入到单用户表
//			int singleCapId = singleUserSignalingTrackingService.insertPath(pcrfFileName,requestPath,insertdatetime,p_id,msisdn);
			//将数据更新到当前全量表中，即(t_cap_all_path)中
			TCapAllPath capAllPath = capAllPathService.get(capAllPathId);
			capAllPath.setCapName(pcrfFileName);
			capAllPath.setHtmlPath(requestPath);
			capAllPath.setEndTime(insertdatetime);
			capAllPath.setCapPath(pcrfFilePathAndName);
			capAllPathService.save(capAllPath);
			System.out.println("===== return singleCapId =====" + capAllPathId);
			System.out.println("=========== finish html step ============");
			return capAllPathId;
		}else{
			return "empty";
		}
	}
    
    private String getBasePath(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort();
		return basePath;
	}
    
    /**
     * @Description: 通过imsi获取msisdn,或者通过msisdn获取imsi
     * @param numberType
     * @param number 
     */
    private Map<String,String> getImsiAndMsisdn(String numberType, String number) {
		Map<String,String> numberMap = new HashMap<String,String>();
    	StringBuffer poolstr = new StringBuffer();
		String username = "";
		String password = "";
		String str = "";
		List<TPool> poolList = poolService.queryPoolListByType(DictConstant.POOL_TYPE_MME);
		for (TPool pool : poolList) {
			TNewnetelement netElement = new TNewnetelement();
			netElement.setFnid(Long.valueOf(pool.getId()));
			List<TNewnetelement> netElements = netElementService.findList(netElement);
			for(TNewnetelement element : netElements){
				String ip = element.getIpadr();
				username = element.getUsername2();
				password = element.getPassword2();
				poolstr.append(" -sgsn "+ip);
			}
		}
		poolstr.append(" -user "+username+" -password "+password);
		str = " -" + numberType +" " + number;
    	String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/opt/Ericsson/core/bin/get_imsi_or_msisdn.pl "+poolstr.toString()+str;
		System.out.println(cmd[2]);
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while((line = br.readLine())!=null){
				System.out.println("--------------------: "+line);
				if(line.contains("Can't get the imsi or msisdn from the node,please switch on the phone!")){
					numberMap.put("error", "0");
				}else{
					if(line.contains("imsi")){
						numberMap.put("imsi",line.trim().split(":")[1]);
					}
					if(line.contains("msisdn")){
						numberMap.put("msisdn",line.trim().split(":")[1]);
					}
				}
			}
			proc.waitFor();
			proc.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return numberMap;
	}
	
    @ResponseBody
	@RequiresPermissions("user")
	@RequestMapping(value = "queryProcessText")
    public String queryProcessText(@RequestParam(required=false) String netIds,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){
		String[] netid_arr = netIds.split(",");
		List<CapText> list = new ArrayList<CapText>();
		HttpSession session = request.getSession();
		for(int n = 0;n<netid_arr.length;n++){
			Queue<String> queue = (Queue<String>) session.getAttribute("capText_"+netid_arr[n]);
			
//			System.out.println(netid_arr[n] +"========queryProcessText================pcrf=" + queue);
			CapText capText = new CapText();
			List<String> text = new ArrayList<String>();
			capText.setNetId(netid_arr[n]);
			if(queue!= null && queue.size() > 0){
				for(int i =0; i<queue.size();i++){
					String aa = queue.poll();
					text.add(aa);
				}
				StringBuffer sb = new StringBuffer("");
				for(String s:text){
					sb.append(s);
				}
				capText.setText(sb.toString().replaceAll("null", ""));
			}else{
				capText.setText("");
			}
			list.add(capText);
		}
		Queue<String> queue = (Queue<String>) session.getAttribute("capTextMmePool");
		
//		System.out.println("queryProcessText=============mme====" + queue);
		CapText capText = new CapText();
		List<String> text = new ArrayList<String>();
		capText.setNetId("mmePool");
		if(queue!= null && queue.size() > 0){
			for(int i =0; i<queue.size();i++){
				String aa = queue.poll();
				text.add(aa);
			}
			StringBuffer sb = new StringBuffer("");
			for(String s:text){
				sb.append(s);
			}
			capText.setText(sb.toString().replaceAll("null", ""));
		}else{
			capText.setText("");
		}
		list.add(capText);
		
		Queue<String> epg_queue = (Queue<String>) session.getAttribute("capTextEpgPool");
//		System.out.println("queryProcessText==============epg===" + queue);
		CapText epg_capText = new CapText();
		List<String> epg_text = new ArrayList<String>();
		epg_capText.setNetId("epgPool");
		if(epg_queue!= null && epg_queue.size() > 0){
			for(int i =0; i<epg_queue.size();i++){
				String aa = epg_queue.poll();
				epg_text.add(aa);
			}
			StringBuffer sb = new StringBuffer("");
			for(String s:epg_text){
				sb.append(s);
			}
			epg_capText.setText(sb.toString().replaceAll("null", ""));
		}else{
			epg_capText.setText("");
		}
		list.add(epg_capText);
		
		return JsonMapper.getInstance().toJson(list);
	}
    
    
	@ResponseBody
	@RequiresPermissions("user")
	@RequestMapping(value = "checkCaptureStatus")
	public String checkCaptureStatus(){
		return UserUnOnlineQueryController.status;
	}
	
	@RequiresPermissions("userquery:signal:trace")
	@RequestMapping(value = "showCaptureResult")
	public String showCaptureResult(@RequestParam String id,HttpServletRequest request, HttpServletResponse response, Model model){
		TCapAllPath capAllPath = capAllPathService.get(id);
		System.out.println("============:"+id);
		model.addAttribute("capAllPath", capAllPath);
		TCapUserfacePath tCapUserfacePath = tCapUserfacePathService.queryByPid(id);
		if(tCapUserfacePath==null){
			TCapUserfacePath _tCapUserfacePath = new TCapUserfacePath();
			_tCapUserfacePath.setPcappath("0");
			model.addAttribute("tCapUserfacePath", _tCapUserfacePath);
		}else{
			model.addAttribute("tCapUserfacePath", tCapUserfacePath);
		}
		return "modules/userquery/userSingleCaptureResult";
	}

	
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TPool> list = poolService.queryPoolListByType(type);
		for (int i=0; i<list.size(); i++){
			TPool e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			if (i == 0) {
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "0");
				mapParent.put("pId", "0");
				mapParent.put("name", type + "池组");
				mapList.add(mapParent);
			}
			map.put("id", e.getId());
			map.put("pId", "0");
			map.put("name", e.getFpoolname());
			mapList.add(map);
		}
		return mapList;
	}
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "pcrfElements")
	public List<Map<String, Object>> pcrfElements(@RequestParam(required=false) String extId, @RequestParam String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setType(type);
		List<TNewnetelement> list = netElementService.findList(tnewnetelement);
		for (int i = 0; i < list.size(); i++) {
			TNewnetelement e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			if (i == 0) {
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "0");
				mapParent.put("pId", "0");
				mapParent.put("name", "PCRF网元");
				mapList.add(mapParent);
			}
			map.put("id", e.getId());
			map.put("pId", "0");
			map.put("name", e.getFname());
			mapList.add(map);
		}
		return mapList;
	}
	
	@RequestMapping(value = { "downloadCap" })
	public void downloadCap(String path,HttpServletRequest request, HttpServletResponse response){
		File f = new File(path);
		FileUtils.downFile(f, request, response);
	}
	
	@RequestMapping(value = { "detailLog" })
	public String detailLog(HttpServletRequest request,String logType, Model model){
		HttpSession session = request.getSession();
		if(logType.equals("MME")){
			DetailLog dl = (DetailLog) session.getAttribute("mmeDetailLog");
			model.addAttribute("dl", dl);
			return "modules/userquery/mmeDetailLog";
		}else if(logType.equals("EPG")){
			DetailLog dl = (DetailLog) session.getAttribute("epgDetailLog");
			model.addAttribute("dl", dl);
			return "modules/userquery/epgDetailLog";
		}else{
			String[] pcrfNetIdArray = (String[]) session.getAttribute("pcrfNetIdArray");
			String[] pcrfNetNameArray = (String[]) session.getAttribute("pcrfNetNameArray");
			Map<String,DetailLog> map = new HashMap<String,DetailLog>();
			for(int i=0;i<pcrfNetIdArray.length;i++){
				DetailLog dl = (DetailLog) session.getAttribute(pcrfNetIdArray[i]);
				map.put(pcrfNetNameArray[i], dl);
			}
			model.addAttribute("map", map);
			return "modules/userquery/pcrfDetailLog";
		}
	}
	
	@RequestMapping(value = "redirectSignal")
	public String redirectSignal(String searchType1, String searchValue1,String captureDuration,String captureScope, Model model) {
		model.addAttribute("searchType", searchType1);
		model.addAttribute("searchValue", searchValue1);
		model.addAttribute("captureDuration", captureDuration); //抓包范围
		model.addAttribute("captureScope", captureScope); //抓包时长
		model.addAttribute("isRedirect", "true");
		return "modules/userquery/userSignalTrace";
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	
}