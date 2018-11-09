/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.TimeUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormula;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TFormulaService;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;
import com.thinkgem.jeesite.modules.performance.entity.TDimensionVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpiEvent;
import com.thinkgem.jeesite.modules.performance.entity.TMultipleIndex;
import com.thinkgem.jeesite.modules.performance.entity.TStatisticsVO;
import com.thinkgem.jeesite.modules.performance.service.TEbmlogStatisticsService;
import com.thinkgem.jeesite.modules.performance.service.TIndexKpiEventService;
import com.thinkgem.jeesite.modules.performance.service.TTacSuccessRateService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.userquery.entity.TCdrTreeMould;
import com.thinkgem.jeesite.modules.userquery.entity.UserCdrDetail;

/**
 * 异常原因多维分析(ebmlog)Controller
 * @author 王晶石
 * @version 2017-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/tEbmlogStatistics")
@Scope("prototype")
public class TEbmlogStatisticsController extends BaseController {

	@Autowired
	private TEbmlogStatisticsService tEbmlogStatisticsService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	@Autowired
	private TTacSuccessRateService tTacSuccessRateService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TFormulaService tFormulaService;
	@Autowired
	private TIndexKpiEventService tIndexKpiEventService;
	
	@ModelAttribute
	public TEbmlogStatistics get(@RequestParam(required=false) String id) {
		TEbmlogStatistics entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tEbmlogStatisticsService.get(id);
		}
		if (entity == null){
			entity = new TEbmlogStatistics();
		}
		return entity;
	}
	
	@RequiresPermissions("performance:tEbmlogStatistics:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tEbmlogStatistics")TEbmlogStatistics tEbmlogStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		TNewnetelement tNewnetelementMME = new TNewnetelement();
		tNewnetelementMME.setType("1");//type设置为1代表MME类型网元
		tNewnetelementMME.setTemp_field1("4");//4代表采集类型为ebmlog的业务类型
		List<TPool> poolMMEList = tPoolService.queryPoolListByType("MME");
		List<TNewnetelement> netMMEListAll = tNewnetelementService.findListByServiceType(tNewnetelementMME);
		List<PoolNetVO>  poolNetList = new ArrayList<PoolNetVO>();
		for(TPool pool : poolMMEList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netMMEListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("MME");
		model.addAttribute("ebmEventlist", ebmEventlist);
		model.addAttribute("poolNetList", poolNetList);
		return "modules/performance/tEbmlogStatisticsList";
	}
	
	
	@RequiresPermissions("performance:tEbmlogStatistics:view")
	@RequestMapping(value = {"pcrflist"})
	public String pcrflist(@Valid @ModelAttribute("tEbmlogStatistics")TEbmlogStatistics tEbmlogStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		TNewnetelement tNewnetelementPcrf = new TNewnetelement();
		tNewnetelementPcrf.setType("3");//type设置为1代表MME类型网元
		tNewnetelementPcrf.setTemp_field1("4");//4代表采集类型为ebmlog的业务类型
		List<TPool> poolList = tPoolService.queryPoolListByType("PCRF");
		List<TNewnetelement> netListAll = tNewnetelementService.findListByServiceType(tNewnetelementPcrf);
		List<PoolNetVO>  poolNetList = new ArrayList<PoolNetVO>();
		for(TPool pool : poolList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("PCRF");
		model.addAttribute("ebmEventlist", ebmEventlist);
		model.addAttribute("poolNetList", poolNetList);
		return "modules/performance/tEbmlogStatisticsPcrfList";
	}
	
	@RequiresPermissions("performance:tEbmlogStatistics:view")
	@RequestMapping(value = {"saegwlist"})
	public String saegwlist(@Valid @ModelAttribute("tEbmlogStatistics")TEbmlogStatistics tEbmlogStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		TNewnetelement tNewnetelementPcrf = new TNewnetelement();
		tNewnetelementPcrf.setType("2");//type设置为1代表MME类型网元
		tNewnetelementPcrf.setTemp_field1("4");//4代表采集类型为ebmlog的业务类型
		List<TPool> poolList = tPoolService.queryPoolListByType("SAEGW");
		List<TNewnetelement> netListAll = tNewnetelementService.findListByServiceType(tNewnetelementPcrf);
		List<PoolNetVO>  poolNetList = new ArrayList<PoolNetVO>();
		for(TPool pool : poolList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("SAEGW");
		model.addAttribute("ebmEventlist", ebmEventlist);
		model.addAttribute("poolNetList", poolNetList);
		return "modules/performance/tEbmlogStatisticsSaegwList";
	}
	
	
	/**
	 * 读取详细数据
	 * @param TElementcollect
	 * @return
	 */
	@RequestMapping(value = "queryEbmEvent")
	@ResponseBody
	public String queryEbmEvent(String netid){
		List<TEbmEvent> ebmEventlist = null;
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			ebmEventlist = tTacSuccessRateService.getEbmEventlist("MME");
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(ebmEventlist);
	}
	
	@RequestMapping(value = "queryList")
	@ResponseBody
	public String queryList(String startTime,String endTime,String netid,String eventType,int limit, int offset){
		netid = "26";
		startTime = "2018-05-01 09:00:00";
		endTime = "2018-05-01 11:00:00";
		ServerPagination<TEbmlogStatistics> page = new ServerPagination<TEbmlogStatistics>();
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			int total = 0;
			List<TEbmlogStatistics> list = new ArrayList<TEbmlogStatistics>();
			List<TEbmlogStatistics> errorlist = null;
			List<TEbmlogStatistics> successList = null;
			if(eventType.equals("strsta")||eventType.equals("rarraa")||eventType.equals("aaraaa")||eventType.equals("asrasa")||
					eventType.equals("grif")||eventType.equals("ccrcca")||eventType.equals("gxrarraa")) {
				 netid = "79";
				 startTime = "2018-10-24 00:00:00";
				 endTime = "2018-10-24 24:00:00"; 
				 date = startTime.substring(0, 10);
				 list = tEbmlogStatisticsService.queryPcrfList(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,offset,limit);
				 total = tEbmlogStatisticsService.queryPcrfListCount(date.replaceAll("-", "_"), startTime, endTime, netid, eventType);
			}else if(eventType.equals("bc")||eventType.equals("pbc")||eventType.equals("bu")||eventType.equals("pbu")){
				 netid = "40";
				 startTime = "2018-10-23 00:00:00";
				 endTime = "2018-10-23 24:00:00"; 
				 date = startTime.substring(0, 10);
				 errorlist = tEbmlogStatisticsService.querySaegwList(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,offset,limit);
				 successList = tEbmlogStatisticsService.querySuccessList(date.replaceAll("-", "_"),startTime,endTime,netid,eventType);
				 list.addAll(0,successList);
				 list.addAll(errorlist);
				 total = tEbmlogStatisticsService.queryListCount(date.replaceAll("-", "_"), startTime, endTime, netid, eventType);
				 total = total + successList.size();
			}else {
				 errorlist = tEbmlogStatisticsService.queryList(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,offset,limit);
				 successList = tEbmlogStatisticsService.querySuccessList(date.replaceAll("-", "_"),startTime,endTime,netid,eventType);
				 list.addAll(0,successList);
				 list.addAll(errorlist);
				 total = tEbmlogStatisticsService.queryListCount(date.replaceAll("-", "_"), startTime, endTime, netid, eventType);
				 total = total + successList.size();
			}
			page.setTotal(total);
			page.setRows(list);
			page.setErrorrows(errorlist);
			page.setSuccessrows(successList);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(page);
	}

	@RequiresPermissions("performance:tEbmlogStatistics:view")
	@RequestMapping(value = "analysis")
	public String analysis(Model model,String cc,String scc,String netid,String startTime,String endTime,String eventType,
			               String ccdes,String sccdes,String action,String domain,HttpServletRequest request,String statisticType) {
		String returnurl = "modules/performance/tEbmlogStatisticsAnalysis";
		if(eventType.equals("att")){
			String str[] = {"APN","SGW","PGW","MSC","IMSISERIES","IMSI","IMEITAC","NAS_ESM_CAUSE_CODE","ECI","TAI","LAI","ATTACHTYPE","MMEI","MSISDN","TAC"};
			queryMap(str,model,netid,cc,scc,startTime,endTime,eventType,ccdes,sccdes,action,domain,request,statisticType);
		}else if(eventType.equals("tau")){
			String str[] = {"APN","SGW","PGW","MSC","IMEITAC","IMSISERIES","IMSI","TAI","ECI","LAI","MMEI","TAUTYPE","OLDTAI","OLDMME","OLDSGW",
			                "OLDSGSN","OLDMME_NEWMME","OLDTAI_NEWTAI","OLDSGSN_NEWMME","OLDRAI_NEWTAI","MSISDN","TAC"};
			queryMap(str,model,netid,cc,scc,startTime,endTime,eventType,ccdes,sccdes,action,domain,request,statisticType);
		}else if(eventType.equals("pdn")||eventType.equals("ims")){
			String str[] = {"APN","SGW","PGW","IMEITAC","IMSISERIES","IMSI","TAI","ECI","LAI","MMEI","PDN_CONNECT_REQUEST_TYPE",
			                "UE_REQUESTED_APN","BEARER_CAUSE","MSISDN","TAC"};
			queryMap(str,model,netid,cc,scc,startTime,endTime,eventType,ccdes,sccdes,action,domain,request,statisticType);
		}else if(eventType.equals("strsta")){
			String str[] = {"DURATION","RX_SESSION_ID","SBC"};
			queryPcrfMap(str,model,netid,cc,startTime,endTime,eventType);
			returnurl = "modules/performance/tEbmlogStatisticsPcrfAnalysis";
		}else if(eventType.equals("rarraa")){
			String str[] = {"DURATION","SPECIFIC_ACTION","RX_SESSION_ID","SBC","ABORT_CAUSE",};
			queryPcrfMap(str,model,netid,cc,startTime,endTime,eventType);
			returnurl = "modules/performance/tEbmlogStatisticsPcrfAnalysis";
		}else if(eventType.equals("aaraaa")){
			String str[] = {"DURATION","OPERATION_TYPE","MEDIA_TYPE","MSISDN","RX_SESSION_ID","GX_SESSION_ID","SAEGW","SBC"};
			queryPcrfMap(str,model,netid,cc,startTime,endTime,eventType);
			returnurl = "modules/performance/tEbmlogStatisticsPcrfAnalysis";
		}else if(eventType.equals("asrasa")){
			String str[] = {"DURATION","RX_SESSION_ID","SBC","ABORT_CAUSE"};
			queryPcrfMap(str,model,netid,cc,startTime,endTime,eventType);
			returnurl = "modules/performance/tEbmlogStatisticsPcrfAnalysis";
		}else if(eventType.equals("grif")){
			String str[] = {"DURATION","APN","IMSI","MSISDN","RULE_ID","GX_SESSION_ID","SAEGW"};
			queryPcrfMap(str,model,netid,cc,startTime,endTime,eventType);
			returnurl = "modules/performance/tEbmlogStatisticsPcrfAnalysis";
		}else if(eventType.equals("ccrcca")){
			String str[] = {"DURATION","APN","QCI","IMSI","MSISDN","OPERATION_TYPE","SUBSCRIBERGROUP","GX_SESSION_ID","SAEGW"};
			queryPcrfMap(str,model,netid,cc,startTime,endTime,eventType);
			returnurl = "modules/performance/tEbmlogStatisticsPcrfAnalysis";
		}else if(eventType.equals("gxrarraa")){
			String str[] = {"DURATION","APN","IMSI","MSISDN","AUTHORIZED_SERVICE_ID","SUBSCRIBERGROUP","GX_SESSION_ID","SAEGW"};
			queryPcrfMap(str,model,netid,cc,startTime,endTime,eventType);
			returnurl = "modules/performance/tEbmlogStatisticsPcrfAnalysis";
		}else if(eventType.equals("bc")){
			String str[] = {"DURATION","EVENT_TRIGGER","ORIGINATING_NODE","APN","PGW_ADDRESS","IMSI","MSISDN","TAC","ECI"};
			querySaegwMap(str,model,netid,cc,scc,startTime,endTime,eventType,ccdes,sccdes,action,domain,request,statisticType);
			returnurl = "modules/performance/tEbmlogStatisticsSaegwAnalysis";
		}else if(eventType.equals("pbc")){
			String str[] = {"DURATION","EVENT_TRIGGER","ORIGINATING_NODE","PEER_NODE_TYPE","PGW_U_PLANE_IPADDRESS","PEER_C_PLANE_IPADDRESS",
					"PEER_U_PLANE_IPADDRESS","GX_IPADDRESS","GY_IPADDRESS","IMSI","MSISDN","TAC","ECI"};
			querySaegwMap(str,model,netid,cc,scc,startTime,endTime,eventType,ccdes,sccdes,action,domain,request,statisticType);
			returnurl = "modules/performance/tEbmlogStatisticsSaegwAnalysis";
		}else if(eventType.equals("bu")){
			String str[] = {"DURATION","EVENT_TRIGGER","ORIGINATING_NODE","APN","PGW_ADDRESS","IMSI","MSISDN","TAC","ECI"};
			querySaegwMap(str,model,netid,cc,scc,startTime,endTime,eventType,ccdes,sccdes,action,domain,request,statisticType);
			returnurl = "modules/performance/tEbmlogStatisticsSaegwAnalysis";
		}else if(eventType.equals("pbu")){
			String str[] = {"DURATION","EVENT_TRIGGER","ORIGINATING_NODE","PEER_NODE_TYPE","PGW_U_PLANE_IPADDRESS","PEER_C_PLANE_IPADDRESS",
					"PEER_U_PLANE_IPADDRESS","GX_IPADDRESS","GY_IPADDRESS","IMSI","MSISDN","TAC","ECI"};
			querySaegwMap(str,model,netid,cc,scc,startTime,endTime,eventType,ccdes,sccdes,action,domain,request,statisticType);
			returnurl = "modules/performance/tEbmlogStatisticsSaegwAnalysis";
		}else{
			String str[] = {"APN","SGW","PGW","IMEITAC","IMSISERIES","IMSI","TAI","ECI","LAI","MMEI","HANDOVER_TYPE","OLDMME","OLDSGSN","OLDSGW",
			                "OLDRAI","OLDMME_NEWMME","OLDTAI_NEWTAI","OLDECI","OLDECI_NEWECI","HANDOVER_NODE_ROLE","HANDOVER_RAT_CHANGE_TYPE",
			                "HANDOVER_SGW_CHANGE_TYPE","SRVCC_TARGET_LAI","MSC_ON_SV","STN_SR","SRVCC_TYPE","RNCID","OLDTAI","MSISDN","TAC"};
			queryMap(str,model,netid,cc,scc,startTime,endTime,eventType,ccdes,sccdes,action,domain,request,statisticType);
		}
		return returnurl;
	}
	
	public void queryPcrfMap(String str[],Model model,String netid,String cc,String startTime,String endTime,String eventType) {
		int num = Integer.parseInt(Global.getConfig("EbmLogStatisticsLimit"));
		String date = startTime.substring(0, 10);
		endTime = date + " " + endTime;
		List<TDimensionVO> dimensionlist;
		Map<String,List<TDimensionVO>> map = new LinkedHashMap<String, List<TDimensionVO>>();
		String parm = cc;
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			for(int i=0;i<str.length;i++){
				 dimensionlist = tEbmlogStatisticsService.getDimensionPcrflistByKey(parm,netid,date.replaceAll("-", "_"),startTime,endTime,ip,str[i],num,eventType);
				 map.put(str[i], dimensionlist);
			}
			model.addAttribute("pieMap", map);
			model.addAttribute("cc" , cc);
			model.addAttribute("netid",netid);
			model.addAttribute("startTime",startTime);
			model.addAttribute("endTime",endTime);
			model.addAttribute("eventType",eventType);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
	}
	
	public void queryMap(String str[],Model model,String netid,String cc,String scc,String startTime,String endTime,String eventType,
			             String ccdes,String sccdes,String action,String domain,HttpServletRequest request,String statisticType){
		int num = Integer.parseInt(Global.getConfig("EbmLogStatisticsLimit"));
		String date = startTime.substring(0, 10);
		endTime = date + " " + endTime;
		List<TDimensionVO> dimensionlist;
		Map<String,List<TDimensionVO>> map = new LinkedHashMap<String, List<TDimensionVO>>();
		String parm = cc+scc;
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			for(int i=0;i<str.length;i++){
				 dimensionlist = tEbmlogStatisticsService.getDimensionlistByKey(parm,netid,date.replaceAll("-", "_"),startTime,endTime,ip,str[i],num,eventType,statisticType);
				 map.put(str[i], dimensionlist);
			}
			model.addAttribute("pieMap", map);
			HttpSession session = request.getSession();
			session.setAttribute("pieMap", map);
			model.addAttribute("cc" , cc);
			model.addAttribute("scc", scc);
			model.addAttribute("ccdes", ccdes);
			model.addAttribute("sccdes", sccdes);
			model.addAttribute("action", action);
			model.addAttribute("domain", domain);
			model.addAttribute("netid",netid);
			model.addAttribute("startTime",startTime);
			model.addAttribute("endTime",endTime);
			model.addAttribute("eventType",eventType);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
	}
	
	public void querySaegwMap(String str[],Model model,String netid,String cc,String scc,String startTime,String endTime,String eventType,
            String ccdes,String sccdes,String action,String domain,HttpServletRequest request,String statisticType){
			int num = Integer.parseInt(Global.getConfig("EbmLogStatisticsLimit"));
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			List<TDimensionVO> dimensionlist;
			Map<String,List<TDimensionVO>> map = new LinkedHashMap<String, List<TDimensionVO>>();
			String parm = cc+scc;
			try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			for(int i=0;i<str.length;i++){
				 dimensionlist = tEbmlogStatisticsService.getDimensionSaegwlistByKey(parm,netid,date.replaceAll("-", "_"),startTime,endTime,ip,str[i],num,eventType,statisticType);
				 map.put(str[i], dimensionlist);
			}
			model.addAttribute("pieMap", map);
			HttpSession session = request.getSession();
			session.setAttribute("pieMap", map);
			model.addAttribute("cc" , cc);
			model.addAttribute("scc", scc);
			model.addAttribute("ccdes", ccdes);
			model.addAttribute("sccdes", sccdes);
			model.addAttribute("action", action);
			model.addAttribute("domain", domain);
			model.addAttribute("netid",netid);
			model.addAttribute("startTime",startTime);
			model.addAttribute("endTime",endTime);
			model.addAttribute("eventType",eventType);
			} catch (Exception e) {
			logger.error(e.getMessage(),e);
			} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
			}
	}

	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(@Valid @ModelAttribute("tEbmlogStatistics") TEbmlogStatistics tEbmlogStatistics,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		    List<TEbmlogStatistics> list = null;
		    List<TEbmlogStatistics> successList = null;
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(tEbmlogStatistics.getNetid());
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			String date = tEbmlogStatistics.getStartTime().substring(0, 10);
			String endTime = date + " " + tEbmlogStatistics.getEndTime();
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			list = tEbmlogStatisticsService.queryList(date.replaceAll("-", "_"),tEbmlogStatistics.getStartTime(),
				endTime,tEbmlogStatistics.getNetid(),tEbmlogStatistics.getEventType(),0,0);
			successList = tEbmlogStatisticsService.querySuccessList(date.replaceAll("-", "_"),tEbmlogStatistics.getStartTime(),
					endTime,tEbmlogStatistics.getNetid(),tEbmlogStatistics.getEventType());
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		   String headStr[] = {"事件结果","CC(原因值)","原因值描述","SCC(子原因值)","子原因值描述","出现次数",};
		   ExportExcel ee = new ExportExcel("异常原因多维分析", headStr);
		   for (int i = 0; i < successList.size(); i++) {
				Row row = ee.addRow();
					TEbmlogStatistics t =successList.get(i);
					ee.addCell(row, 0,t.getEventResult());
					ee.addCell(row, 1,t.getCausecode());
					ee.addCell(row, 2,t.getCc());
					ee.addCell(row, 3,t.getSubcausecode());
					ee.addCell(row, 4,t.getScc());
					ee.addCell(row, 5,t.getFailures().toString());
			}
		   for (int i = 0; i < list.size(); i++) {
				Row row = ee.addRow();
					TEbmlogStatistics t =list.get(i);
					ee.addCell(row, 0,t.getEventResult());
					ee.addCell(row, 1,t.getCausecode());
					ee.addCell(row, 2,t.getCc());
					ee.addCell(row, 3,t.getSubcausecode());
					ee.addCell(row, 4,t.getScc());
					ee.addCell(row, 5,t.getFailures().toString());
			}
		   
		   try {
				ee.write(response, "异常原因多维分析.xlsx");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				ee.dispose();
			}
	}
	
	@RequestMapping(value = { "exportAnalysisExcel" })
	public void exportAnalysisExcel(HttpServletRequest request, HttpServletResponse response, Model model,String cc,String scc) {
		HttpSession session = request.getSession();
		Map<String,List<TDimensionVO>> map = (Map<String, List<TDimensionVO>>) session.getAttribute("pieMap");
		ExportExcel ee = new ExportExcel();
		HSSFWorkbook workbook = new HSSFWorkbook();
		response.setContentType("application/vnd.ms-excel");
		String fileName = "statistics_"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+".xls";
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		String[] headers = {"指标值","失败次数"};
		String[] _headers = {"指标值","失败次数","总次数","占比"};
		OutputStream out;
		try {
			out = response.getOutputStream();
		int k = 0;
		for (Map.Entry<String, List<TDimensionVO>> entry : map.entrySet()) {
			 List<Object[]> rowList = new ArrayList<Object[]>();
			 for (int i = 0; i < entry.getValue().size(); i++) {
				    if(entry.getKey().equals("ECI")||entry.getKey().equals("TAC")) {
				    	Object[] o = new Object[4];
						o[0] = entry.getValue().get(i).getObj();
						o[1] = entry.getValue().get(i).getFailures();
						o[2] = entry.getValue().get(i).getTotalCount();
						o[3] = entry.getValue().get(i).getSuccessRate();
						rowList.add(o);
				    }else {
				    	Object[] o = new Object[2];
						o[0] = entry.getValue().get(i).getObj();
						o[1] = entry.getValue().get(i).getFailures();
						rowList.add(o);
				    }
				}
			 if(entry.getKey().equals("ECI")||entry.getKey().equals("TAC")) {
				 ee.createMultiSheetExcel(workbook, k, entry.getKey(), _headers, rowList, out);
			 }else {
				 ee.createMultiSheetExcel(workbook, k, entry.getKey(), headers, rowList, out);
			 }
			 k++;
		}
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	
	@RequestMapping(value = "redirectTEbmlogStatistics")
	public String redirectTEbmlogStatistics(@Valid @ModelAttribute("tEbmlogStatistics")TEbmlogStatistics tEbmlogStatistics,String formulaName,String net,String startTime, Model model) {
		
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setType("1");//type设置为1代表MME类型网元
		tNewnetelement.setTemp_field1("4");//4代表采集类型为ebmlog的业务类型
		List<TPool> poolList = tPoolService.queryPoolListByType("MME");
		List<TNewnetelement> netListAll = tNewnetelementService.findListByServiceType(tNewnetelement);
		List<PoolNetVO>  poolNetList = new ArrayList<PoolNetVO>();
		for(TPool pool : poolList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("MME");
		model.addAttribute("ebmEventlist", ebmEventlist);
		model.addAttribute("poolNetList", poolNetList);
		
		
		
		
		TFormula tFormula = new TFormula();
		tFormula.setName(formulaName.split(":")[1]);
		TFormula f = tFormulaService.findList(tFormula).get(0);
		
		boolean flag = false;
		if(null != f) {
			TIndexKpiEvent tIndexKpiEvent = new TIndexKpiEvent();
			tIndexKpiEvent.setKpi(f.getId());
			List<TIndexKpiEvent> eventList = tIndexKpiEventService.findList(tIndexKpiEvent);
			if(eventList.size()>0) {
				String e = eventList.get(0).getEvent();
				model.addAttribute("event", e);
				for(TEbmEvent ev : ebmEventlist) {
					if(ev.getEventname().equals(e)) {
						model.addAttribute("eventfullname", ev.getEventfullname());
						flag = true;
						break;
					}
				}
			}
			
		}
		if(!flag) {
			model.addAttribute("event", "null");
			model.addAttribute("eventfullname", "null");
		}
		
		
		
		TNewnetelement el = new TNewnetelement();
		el.setFname(net);
		List<TNewnetelement>  elList = tNewnetelementService.findList(el);
		if(elList.size() > 0) {
			TNewnetelement tel = elList.get(0);
			model.addAttribute("netId", tel.getId());
			model.addAttribute("netName", tel.getFname());
		} else {
			model.addAttribute("netId", "");
			model.addAttribute("netName","");
		}
		
		String dateStr = "";
		try {
			dateStr = TimeUtils.formatDateToString(new Date(), "yyyy-MM-dd");
			dateStr += " " + startTime;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		model.addAttribute("startTime", dateStr);
		
		String endTime = TimeUtils.getBeforeMinute(dateStr, 15);
		
		model.addAttribute("endTime", endTime.split("\\s+")[1]);
		model.addAttribute("isRedirect", "true");
		
		return "modules/performance/tEbmlogStatisticsList";
	}

}