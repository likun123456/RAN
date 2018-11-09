/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;
import com.thinkgem.jeesite.modules.performance.entity.TEciSuccessRate;
import com.thinkgem.jeesite.modules.performance.service.TEbmlogStatisticsService;
import com.thinkgem.jeesite.modules.performance.service.TEciSuccessRateService;
import com.thinkgem.jeesite.modules.performance.service.TTacSuccessRateService;

/**
 * eci维度指标查询Controller
 * @author 陈宏波
 * @version 2017-06-09
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/tEciSuccessRate")
@Scope("prototype")
public class TEciSuccessRateController extends BaseController {

	@Autowired
	private TEciSuccessRateService tEciSuccessRateService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	@Autowired
	private TEbmlogStatisticsService tEbmlogStatisticsService;
	@Autowired
	private TTacSuccessRateService tTacSuccessRateService;
	@Autowired
	private TPoolService tPoolService;
	
	@ModelAttribute
	public TEciSuccessRate get(@RequestParam(required=false) String id) {
		TEciSuccessRate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tEciSuccessRateService.get(id);
		}
		if (entity == null){
			entity = new TEciSuccessRate();
		}
		return entity;
	}
	
	@RequiresPermissions("performance:tEciSuccessRate:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tEciSuccessRate")TEciSuccessRate tEciSuccessRate, HttpServletRequest request, HttpServletResponse response, Model model) {
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
		//TODO tEciSuccessRateList为青岛版本，tEciSuccessRateLnList为辽宁版本
		return "modules/performance/tEciSuccessRateList";
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
			ebmEventlist = tEciSuccessRateService.getEbmEventlist();
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(ebmEventlist);
	}
	
	@RequestMapping(value = "queryList")
	@ResponseBody
	public String queryList(String startTime,String endTime,String netid,String eventType,int limit, int offset,String sortName,String sortOrder){
		ServerPagination<TEciSuccessRate> page = new ServerPagination<TEciSuccessRate>();
		/*if(sortName!=null){
			if(sortName.equals("failureCount")){
				sortName = "failure_count";
			}
			if(sortName.equals("totalCount")){
				sortName = "total_count";
			}
			if(sortName.equals("successRate")){
				sortName = "success_rate";
			}
		}*/
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			netid = "26";
			startTime = "2018-05-01 09:00:00";
			endTime = "2018-05-01 11:00:00";
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			//TODO queryLnList为辽宁移动版本(缺少xls文件导入到station信息表)，queryList方法为青岛移动版本。
			List<TEciSuccessRate> list = tEciSuccessRateService.queryList(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,offset,limit,sortName,sortOrder);
			int total = tEciSuccessRateService.queryListCount(date.replaceAll("-", "_"), startTime, endTime, netid, eventType);
			DynamicDataSource.setCurrentLookupKey("dataSource");
			page.setTotal(total);
			page.setRows(list);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(page);
	}
	

	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(@Valid @ModelAttribute("tEciSuccessRate") TEciSuccessRate tEciSuccessRate,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TEciSuccessRate> list=null;
		String date="";
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(tEciSuccessRate.getNetid());
			date = tEciSuccessRate.getTemp_field2().substring(0, 10);
			String endTime = date + " " + tEciSuccessRate.getTemp_field3();
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			list = tEciSuccessRateService.queryLnList(date.replaceAll("-", "_"),tEciSuccessRate.getTemp_field2(),endTime,tEciSuccessRate.getNetid(),tEciSuccessRate.getEventType(),0,0,null,null);
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		List<String> headerList = Lists.newArrayList();
		headerList.add("网元名称");
		headerList.add("时间段");
		headerList.add("厂家");
		headerList.add("站名");
		headerList.add("站号");
		headerList.add("enodebId");
		headerList.add("ECI");
		headerList.add("失败数");
		headerList.add("总数");
		headerList.add("成功率");

		ExportExcel ee = new ExportExcel("eNodeB综合查询", headerList);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
				TEciSuccessRate tEciSuccessRatetemp =list.get(i);
				ee.addCell(row, 0,tEciSuccessRatetemp.getFname());
				ee.addCell(row, 1,tEciSuccessRate.getTemp_field2()+" 至 "+tEciSuccessRate.getTemp_field3());
				ee.addCell(row, 2,tEciSuccessRatetemp.getStationName());
				ee.addCell(row, 3,tEciSuccessRatetemp.getFactory());
				ee.addCell(row, 4,tEciSuccessRatetemp.getStationNo());
				ee.addCell(row, 5,tEciSuccessRatetemp.getEnodebId());
				ee.addCell(row, 6,tEciSuccessRatetemp.getEci());
				ee.addCell(row, 7,tEciSuccessRatetemp.getFailureCount());
				ee.addCell(row, 8,tEciSuccessRatetemp.getTotalCount());
				ee.addCell(row, 9,tEciSuccessRatetemp.getSuccessRate());
		}
		
		try {
			ee.write(response, "eNodeB综合查询.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ee.dispose();
		}
	}
	
	@RequestMapping(value = { "exportLnExcel" })
	public void exportLnExcel(@Valid @ModelAttribute("tEciSuccessRate") TEciSuccessRate tEciSuccessRate,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TEciSuccessRate> list=null;
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(tEciSuccessRate.getNetid());
			String date = tEciSuccessRate.getTemp_field2().substring(0, 10);
			String endTime = date + " " + tEciSuccessRate.getTemp_field3();
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			list = tEciSuccessRateService.queryLnList(date.replaceAll("-", "_"),tEciSuccessRate.getTemp_field2(),endTime,tEciSuccessRate.getNetid(),tEciSuccessRate.getEventType(),0,0,null,null);
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		List<String> headerList = Lists.newArrayList();
		headerList.add("网元");
		headerList.add("时间");
		headerList.add("ECI");
		headerList.add("失败数");
		headerList.add("总数");
		headerList.add("成功率");

		ExportExcel ee = new ExportExcel("eNodeB综合查询.xlsx", headerList);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
				TEciSuccessRate tEciSuccessRatetemp =list.get(i);
				ee.addCell(row, 0,tEciSuccessRatetemp.getFname());
				ee.addCell(row, 1,tEciSuccessRatetemp.getDatetime());
				ee.addCell(row, 2,tEciSuccessRatetemp.getEci());
				ee.addCell(row, 3,tEciSuccessRatetemp.getFailureCount());
				ee.addCell(row, 4,tEciSuccessRatetemp.getTotalCount());
				ee.addCell(row, 5,tEciSuccessRatetemp.getSuccessRate());
		}
		
		try {
			ee.write(response, "eNodeB缁煎悎鏌ヨ.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ee.dispose();
		}
	}
	
	@RequestMapping(value = "queryEbmLogByEci")
	@ResponseBody
	public String queryEbmLogByEci(String startTime,String endTime,String netid,String eventType,String eci,int limit, int offset){
		ServerPagination<TEbmlogStatistics> page = new ServerPagination<TEbmlogStatistics>();
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			List<TEbmlogStatistics> list = tEbmlogStatisticsService.queryListByEci(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,eci,offset,limit);
			int total = tEbmlogStatisticsService.queryListByEciCount(date.replaceAll("-", "_"), startTime, endTime, netid,eci, eventType);
			page.setTotal(total);
			page.setRows(list);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(page);
	}
	
	@RequestMapping(value = "showEbmPieListWindow")
	public String showEbmPieListWindow(String eci, Model model){
		model.addAttribute("eci", eci);
		return "modules/performance/tEbmlogByEci";
	}
	
}