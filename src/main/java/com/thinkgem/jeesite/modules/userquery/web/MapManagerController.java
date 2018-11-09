/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;
import com.thinkgem.jeesite.modules.performance.service.TEbmlogStatisticsService;
import com.thinkgem.jeesite.modules.performance.service.TTacSuccessRateService;
import com.thinkgem.jeesite.modules.userquery.entity.TLteWholeNetParam;
import com.thinkgem.jeesite.modules.userquery.service.TLteWholeNetParamService;

/**
 * 地图Controller
 * @author 陈宏波
 * @version 2017-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/userquery/MapManager")
@Scope("prototype")
public class MapManagerController extends BaseController {
	
	@Autowired
	private TLteWholeNetParamService tLteWholeNetParamService;
	
	@Autowired
	private TPoolService tPoolService;
	
	@Autowired
	private TTacSuccessRateService tTacSuccessRateService;
	
	@Autowired
	private TNewnetelementService tnewnetelementService;
	
	@Autowired
	private TEbmlogStatisticsService tEbmlogStatisticsService;
	
	@Autowired
	private TCollectordeployService tCollectordeployService;
	
	@RequestMapping(value = {"list", ""})
	public String list(Model model,String poolName) {
		List<TPool> poolList = tPoolService.queryPoolListByType("MME");
		model.addAttribute("poolList",poolList);
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("MME");
		model.addAttribute("ebmEventlist", ebmEventlist);
		model.addAttribute("poolName",poolName);
		return "modules/userquery/map";
	}

	@RequestMapping(value = "query")
	@ResponseBody
	public String query(@RequestParam(required = true) String minLongitude,@RequestParam(required = true) String maxLongitude,@RequestParam(required = true) String minLatitude,@RequestParam(required = true) String maxLatitude,@RequestParam(required = true) String pool,
			@RequestParam(required = true) String eventType) {
		Map<String,Object> k_map = new HashMap<String,Object>();
		/*//String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
		TCollectordeploy c = new TCollectordeploy();
		c.setCoreebmlog(1);
		List<TCollectordeploy> collectList = tCollectordeployService.findList(c);
		
		for(TCollectordeploy collect : collectList) {
			if(!"".equals(pool) && !"".equals(eventType)) {
				try {
					DynamicDataSource.setCurrentLookupKey(collect.getIp() + "-ebmlog");
					String nowDate = DateUtils.getDate().replace("-", "_");
					//nowDate = "2017_08_25";
					String recentMoment = tLteWholeNetParamService.queryRecentMoment(nowDate,pool,eventType);
					List<TLteWholeNetParam> subList = tLteWholeNetParamService.getList(nowDate,minLongitude, maxLongitude, minLatitude, maxLatitude, pool, eventType,recentMoment);
					list.addAll(subList);
					DynamicDataSource.setCurrentLookupKey("dataSource");
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				} finally {
					DynamicDataSource.setCurrentLookupKey("dataSource");
				}
			}
		}*/
		
		List<TLteWholeNetParam> list = new ArrayList<TLteWholeNetParam>();
		try {
			DynamicDataSource.setCurrentLookupKey("39.106.18.174-ebmlog");
			//String nowDate = DateUtils.getDate().replace("-", "_");
			String nowDate = "2017_08_25";
			String recentMoment = tLteWholeNetParamService.queryRecentMoment(nowDate,pool,eventType);
			list = tLteWholeNetParamService.getList(nowDate,minLongitude, maxLongitude, minLatitude, maxLatitude, pool, eventType,recentMoment);
			k_map.put("list", list);
			k_map.put("recentMoment", recentMoment);
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
				
		return JsonMapper.getInstance().toJson(k_map);
	}
	
	@RequestMapping(value = "queryStatisticalPage")
	public String queryStatisticalPage(@RequestParam(required = true) String poolId,@RequestParam(required = true) String eventType,
			@RequestParam(required = true) String startTime,@RequestParam(required = true) String endTime,@RequestParam(required = true) String eci,
			Model model,String factory,String stationNo,String stationName,String enodebId,String failureCount,String totalCount,String successRate,
			String poolName,String eventFullType) {
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setTemp_field2(poolId);;
		List<TNewnetelement> netList = tnewnetelementService.findList(tNewnetelement);
		Map<String,List<TEbmlogStatistics>> map = new HashMap<String,List<TEbmlogStatistics>>();
		for(int i=0;i<netList.size();i++) {
			List<TEbmlogStatistics> list  = queryEbmLogByEci(netList.get(i).getId(),eventType,startTime,endTime,eci);
			if(list.size()>0) {
				map.put(netList.get(i).getFname(), list);
			}
		}
		model.addAttribute("poolName", poolName);
		model.addAttribute("eventType", eventFullType);
		model.addAttribute("eci", eci);
		model.addAttribute("factory", factory);
		model.addAttribute("pieMap",map);
		model.addAttribute("stationNo", stationNo);
		model.addAttribute("stationName", stationName);
		model.addAttribute("enodebId", enodebId);
		model.addAttribute("failureCount", failureCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("successRate", successRate);
		return "modules/userquery/tEbmlogByMapEci";
	}
	
	public List<TEbmlogStatistics> queryEbmLogByEci(String netid,String eventType,String startTime,String endTime,String eci){
		List<TEbmlogStatistics> list = null;
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			//String nowDate = DateUtils.getDate().replace("-", "_");
			String nowDate = "2017_08_25";
			endTime =  nowDate + " 24:00:00";
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			list = tEbmlogStatisticsService.queryListByEci(nowDate,startTime,endTime,netid,eventType,eci,0,0);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return list;
	}
	
	/**
	 * 点击红点显示图标
	 * @param poolId
	 * @return
	 */
	
	@RequestMapping(value = "queryStatistical")
	@ResponseBody
	public String queryStatistical(@RequestParam(required = true) String poolId,@RequestParam(required = true) String eventType,@RequestParam(required = true) String startTime,@RequestParam(required = true) String endTime,@RequestParam(required = true) String eci) {

		//eci = "49751184";
		//查询词组下的网元所在的采集器
		List<Map<String,Object>> list = tLteWholeNetParamService.getCollectByPool(poolId, "4");
		
		//将查询结果进行封装， 属于同一采集器的网元进行归类，减少数据源切换频率
		Map<String, List<Map<String,Object>>> dataMap = new HashMap<String,List<Map<String,Object>>>();
		for(Map<String,Object> map : list) {
			String ip = map.get("ip").toString();
			List<Map<String, Object>> l = dataMap.get(ip);
			if(l == null || l.size() <= 0) {
				l = new ArrayList<Map<String, Object>>();
				l.add(map);
				dataMap.put(ip, l);
			} else {
				l.add(map);
			}
		}
		
		
		//切换数据源进行查询， 取出每个网元的统计信息，封装到一个list中
		List<TEbmlogStatistics> totalList = new ArrayList<TEbmlogStatistics>();
		String date = startTime.substring(0, 10);
		endTime = date + " " + endTime;
		for(String key : dataMap.keySet()) {
			List<Map<String, Object>> netlist = dataMap.get(key);
			try {
				DynamicDataSource.setCurrentLookupKey(key + "-core_data_ebmlog");
				for(Map<String, Object> m : netlist) {
					String netid = m.get("f_id").toString();
					List<TEbmlogStatistics> l = tEbmlogStatisticsService.queryListByEci(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,eci,0,0);
					totalList.addAll(l);
				}
				DynamicDataSource.setCurrentLookupKey("dataSource");
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			} finally {
				DynamicDataSource.setCurrentLookupKey("dataSource");
			}
		}
		
		//统计分析， 如果 集合中两条或以上的TEbmlogStatistics 的 cc && scc 属性相同 则累加 failures 并且合并
		List<TEbmlogStatistics> totalListResult = new ArrayList<TEbmlogStatistics>();
		for(TEbmlogStatistics statistics : totalList) {
			if(totalListResult.size() == 0) {
				totalListResult.add(statistics);
			} else {
				boolean exist = false;
				for(TEbmlogStatistics s : totalListResult) {
					if(s.getCc().equals(statistics.getCc()) && s.getScc().equals(statistics.getScc())) {
						s.setFailures(s.getFailures().add(statistics.getFailures()));
						exist = true;
						break;
					}
				}
				if(!exist) {
					totalListResult.add(statistics);
				}
			}
		}
		
		return JsonMapper.getInstance().toJson(totalListResult);
	}
	
	

}