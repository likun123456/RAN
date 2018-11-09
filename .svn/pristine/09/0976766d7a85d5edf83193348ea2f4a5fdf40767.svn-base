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
import com.thinkgem.jeesite.modules.performance.entity.TTacSuccessRate;
import com.thinkgem.jeesite.modules.performance.service.TEbmlogStatisticsService;
import com.thinkgem.jeesite.modules.performance.service.TTacSuccessRateService;

/**
 * 维度指标查询Controller
 * @author 王晶石
 * @version 2017-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/tTacSuccessRate")
@Scope("prototype")
public class TTacSuccessRateController extends BaseController {

	@Autowired
	private TTacSuccessRateService tTacSuccessRateService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	@Autowired
	private TEbmlogStatisticsService tEbmlogStatisticsService;
	@Autowired
	private TPoolService tPoolService;
	
	@ModelAttribute
	public TTacSuccessRate get(@RequestParam(required=false) String id) {
		TTacSuccessRate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tTacSuccessRateService.get(id);
		}
		if (entity == null){
			entity = new TTacSuccessRate();
		}
		return entity;
	}
	
	@RequiresPermissions("performance:tTacSuccessRate:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tTacSuccessRate")TTacSuccessRate tTacSuccessRate, HttpServletRequest request, HttpServletResponse response, Model model) {
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
		//model.addAttribute("netList", netList);
		return "modules/performance/tTacSuccessRateList";
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
			//String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			//DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			ebmEventlist = tTacSuccessRateService.getEbmEventlist("MME");
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			//DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(ebmEventlist);
	}
	
	@RequestMapping(value = "queryList")
	@ResponseBody
	public String queryList(String startTime,String endTime,String netid,String eventType,int limit, int offset,String sortName,String sortOrder){
		ServerPagination<TTacSuccessRate> page = new ServerPagination<TTacSuccessRate>();
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
			List<TTacSuccessRate> list = tTacSuccessRateService.queryLnList(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,offset,limit,sortName,sortOrder);
			int total = tTacSuccessRateService.queryLnListCount(date.replaceAll("-", "_"), startTime, endTime, netid, eventType);
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
	public void exportExcel(@Valid @ModelAttribute("tEciSuccessRate") TTacSuccessRate tTacSuccessRate,
			HttpServletRequest request, HttpServletResponse response) {
		List<TTacSuccessRate> list=null;
		String date ="";
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(tTacSuccessRate.getNetid());
			date = tTacSuccessRate.getTemp_field2().substring(0, 10);
			String endTime = date + " " + tTacSuccessRate.getTemp_field3();
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			list = tTacSuccessRateService.queryLnList(date.replaceAll("-", "_"),tTacSuccessRate.getTemp_field2(),endTime,tTacSuccessRate.getNetid(),tTacSuccessRate.getEventType(),0,0,null,null);
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		List<String> headerList = Lists.newArrayList();
		headerList.add("网元名称");
		headerList.add("时间段");
		headerList.add("TAC");
		headerList.add("失败数");
		headerList.add("总数");
		headerList.add("成功率");

		ExportExcel ee = new ExportExcel("TAC维度指标查询", headerList);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
				TTacSuccessRate tTacSuccessRatetemp =list.get(i);
				ee.addCell(row, 0,tTacSuccessRatetemp.getFname());
				ee.addCell(row, 1,tTacSuccessRate.getTemp_field2()+" 至 "+tTacSuccessRate.getTemp_field3());
				ee.addCell(row, 2,tTacSuccessRatetemp.getTac());
				ee.addCell(row, 3,tTacSuccessRatetemp.getFailureCount());
				ee.addCell(row, 4,tTacSuccessRatetemp.getTotalCount());
				ee.addCell(row, 5,tTacSuccessRatetemp.getSuccessRate());
		}
		
		try {
			ee.write(response, "TAC维度指标查询.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ee.dispose();
		}
	}
	
	
	@RequestMapping(value = "queryEbmLogByTac")
	@ResponseBody
	public String queryEbmLogByTac(String startTime,String endTime,String netid,String eventType,String tac,int limit, int offset){
		ServerPagination<TEbmlogStatistics> page = new ServerPagination<TEbmlogStatistics>();
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			List<TEbmlogStatistics> list = tEbmlogStatisticsService.queryListByTac(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,tac,offset,limit);
			int total = tEbmlogStatisticsService.queryListByTacCount(date.replaceAll("-", "_"), startTime, endTime, netid,tac, eventType);
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
	public String showEbmPieListWindow(String tac, Model model){
		model.addAttribute("tac", tac);
		return "modules/performance/tEbmlogByTac";
	}
	
	 

}