package com.thinkgem.jeesite.common.webservice.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.TimeUtils;
import com.thinkgem.jeesite.common.webservice.MobileWebService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.TDimensionVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpi;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpiParam;
import com.thinkgem.jeesite.modules.performance.entity.TIndexMain;
import com.thinkgem.jeesite.modules.performance.service.TEbmlogStatisticsService;
import com.thinkgem.jeesite.modules.performance.service.TIndexKpiParamService;
import com.thinkgem.jeesite.modules.performance.service.TIndexKpiService;
import com.thinkgem.jeesite.modules.performance.service.TIndexMainService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.userquery.entity.TImeitacTranslate;
import com.thinkgem.jeesite.modules.userquery.service.TImeitacTranslateService;

@WebService(endpointInterface = "com.thinkgem.jeesite.common.webservice.MobileWebService")
public class MobileWebServiceImpl implements MobileWebService {
	
	@Autowired
	private TPoolService tPoolService;// 查询池组信息
	@Autowired
	private TIndexKpiService tIndexKpiService;// 查询统计指标信息
	@Autowired
	private TIndexKpiParamService tIndexKpiParamService;// 查询统计指标信息
	@Autowired
	private TIndexMainService tIndexMainService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	@Autowired
	private TEbmlogStatisticsService tEbmlogStatisticsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TNewnetelementService tnewnetelementService;
	@Autowired
	private TImeitacTranslateService imeitacTranslateService;
	
	//全网指标，表盘（按词组分）
	@Override
	public String getMainIndex() {
		// 查询池组集合
		List<TPool> tPoolList = tPoolService.findList(new TPool());
		List<TIndexKpi> tIndexKpiList = tIndexKpiService.findList(new TIndexKpi());
		String datetime = TimeUtils.getYesterMinute(tIndexMainService.getDateTime("mme"),15);
		//Map<String, List<TIndexMain>> mainData = new LinkedHashMap<String, List<TIndexMain>>();
		List<TIndexMain> listMain;
		TIndexMain tIndexMain;
		TIndexKpiParam tikp;
		
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", 200);
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		for (int m = 0; m < tPoolList.size(); m++) {// 查询池组对应的KPI值
			Map<String,Object> rm = new LinkedHashMap<String,Object>();
			tIndexMain = new TIndexMain();
			tIndexMain.setTpoolId(tPoolList.get(m).getId());
			tIndexMain.setTpoolName(tPoolList.get(m).getFpoolname());
			tIndexMain.setTpoolType(tPoolList.get(m).getFtype());
			tIndexMain.setDateTime(datetime);
			tIndexMain.setTimeInterval(tIndexKpiList.get(0).getStarttime());
			if (tPoolList.get(m).getFtype().equals("MME")) {
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMmekpione() + "," + tIndexKpiList.get(0).getMmekpitwo()
						+ "," + tIndexKpiList.get(0).getMmekpithree() + "," + tIndexKpiList.get(0).getMmekpifour() + ","
						+ tIndexKpiList.get(0).getMmekpifive() + "," + tIndexKpiList.get(0).getMmekpisix());
			} else if (tPoolList.get(m).getFtype().equals("SAEGW")) {
				tIndexMain.setKpiList(tIndexKpiList.get(0).getSaegwkpione() + ","
						+ tIndexKpiList.get(0).getSaegwkpitwo() + "," + tIndexKpiList.get(0).getSaegwkpithree() + ","
						+ tIndexKpiList.get(0).getSaegwkpifour() + "," + tIndexKpiList.get(0).getSaegwkpifive() + ","
						+ tIndexKpiList.get(0).getSaegwkpisix());
			} else {
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPcrfkpione() + "," + tIndexKpiList.get(0).getPcrfkpitwo()
						+ "," + tIndexKpiList.get(0).getPcrfkpithree() + "," + tIndexKpiList.get(0).getPcrfkpifour()
						+ "," + tIndexKpiList.get(0).getPcrfkpifive() + "," + tIndexKpiList.get(0).getPcrfkpisix());
			}
			listMain = tIndexMainService.queryList(tIndexMain,tPoolList.get(m).getFtype());
			//Map<String, List<Map<String, String>>> data = new LinkedHashMap<String, List<Map<String, String>>>();
			List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
			for (int n = 0; n < listMain.size(); n++) {// 计算每个池组中KPI值所处的区间（优，良，中，差，预警）
				tikp = new TIndexKpiParam();
				tikp.setKpi(listMain.get(n).getKpi());
				tikp = tIndexKpiParamService.get(tikp);
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getExcellentDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double
								.parseDouble(tikp.getExcellentUp())) {// 优
					//listMain.get(n).setIconName("icon-blue-5");
					map.put("key_value", "5");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getGoodDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getGoodUp())) {// 良
					//listMain.get(n).setIconName("icon-green-4");
					map.put("key_value", "4");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getWellDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getWellUp())) {// 中
					//listMain.get(n).setIconName("icon-yellow-3");
					map.put("key_value", "3");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getBadDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getBadUp())) {// 差
					//listMain.get(n).setIconName("icon-orange-2");
					map.put("key_value", "2");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getAlarmDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getAlarmUp())) {
					//listMain.get(n).setIconName("icon-red-1");
					map.put("key_value", "1");
				}
				if(listMain.get(n).getCountertype().startsWith("0")) {
					map.put("key_percent", listMain.get(n).getResult().substring(0,5));
				} else if(listMain.get(n).getResult().endsWith(".00")) {
					map.put("key_percent", listMain.get(n).getResult().replaceAll(".00",""));
				} else {
					map.put("key_percent", listMain.get(n).getResult().split("\\.")[0]);
				}
				map.put("key_name",  listMain.get(n).getKpiName());
				dataList.add(map);
			}
			rm.put("poolid", tIndexMain.getTpoolId());
			rm.put("poolname", tPoolList.get(m).getFpoolname());
			rm.put("time", datetime);
			rm.put("data", dataList);
			l.add(rm);
		}
		returnMap.put("message", l);
		return JsonMapper.getInstance().toJson(returnMap);
	}

	//全网指标，表盘（按网元分）
	@Override
	public String getNetelementIndex(String poolName, String datetime) {
		
		List<TIndexKpi> tIndexKpiList = tIndexKpiService.findList(new TIndexKpi());
		TPool tPool = new TPool();
		tPool.setFpoolname(poolName);
		List<TPool> poolList = tPoolService.findList(tPool);
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setFnid(Long.parseLong(poolList.get(0).getId()));
		List<TNewnetelement> tNewnetelementList = tNewnetelementService.findList(tNewnetelement);
		
		
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", 200);
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();

		//Map<String, List<TIndexMain>> mainData = new LinkedHashMap<String, List<TIndexMain>>();
		List<TIndexMain> listMain;
		TIndexMain tIndexMain;
		TIndexKpiParam tikp;
		for (int m = 0; m < tNewnetelementList.size(); m++) {// 查询池组对应的KPI值
			Map<String,Object> rm = new LinkedHashMap<String,Object>();
			tIndexMain = new TIndexMain();
			tIndexMain.setNetId(tNewnetelementList.get(m).getId());
			tIndexMain.setNetName(tNewnetelementList.get(m).getFname());
			tIndexMain.setTpoolType(tNewnetelementList.get(m).getType());
			tIndexMain.setDateTime(datetime);
			tIndexMain.setTimeInterval(tIndexKpiList.get(0).getStarttime());
			String tableName = "MME";
			if (tNewnetelementList.get(m).getType().equals("1")) {
				tableName="MME";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMmekpione() + "," + tIndexKpiList.get(0).getMmekpitwo()
						+ "," + tIndexKpiList.get(0).getMmekpithree() + "," + tIndexKpiList.get(0).getMmekpifour() + ","
						+ tIndexKpiList.get(0).getMmekpifive() + "," + tIndexKpiList.get(0).getMmekpisix());
			} else if (tNewnetelementList.get(m).getType().equals("2")) {
				tableName="SAEGW";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getSaegwkpione() + ","
						+ tIndexKpiList.get(0).getSaegwkpitwo() + "," + tIndexKpiList.get(0).getSaegwkpithree() + ","
						+ tIndexKpiList.get(0).getSaegwkpifour() + "," + tIndexKpiList.get(0).getSaegwkpifive() + ","
						+ tIndexKpiList.get(0).getSaegwkpisix());
			} else {
				tableName="PCRF";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPcrfkpione() + "," + tIndexKpiList.get(0).getPcrfkpitwo()
						+ "," + tIndexKpiList.get(0).getPcrfkpithree() + "," + tIndexKpiList.get(0).getPcrfkpifour()
						+ "," + tIndexKpiList.get(0).getPcrfkpifive() + "," + tIndexKpiList.get(0).getPcrfkpisix());
			}
			listMain = tIndexMainService.queryNetList(tIndexMain,tableName);
			List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
			for (int n = 0; n < listMain.size(); n++) {// 计算每个池组中KPI值所处的区间（优，良，中，差，预警）
				tikp = new TIndexKpiParam();
				tikp.setKpi(listMain.get(n).getKpi());
				tikp = tIndexKpiParamService.get(tikp);
				Map<String,String> map = new LinkedHashMap<String,String>();
				if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getExcellentDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double
								.parseDouble(tikp.getExcellentUp())) {// 优
					//listMain.get(n).setIconName("icon-blue-5");
					map.put("key_value", "5");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getGoodDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getGoodUp())) {// 良
					//listMain.get(n).setIconName("icon-green-4");
					map.put("key_value", "4");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getWellDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getWellUp())) {// 中
					//listMain.get(n).setIconName("icon-yellow-3");
					map.put("key_value", "3");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getBadDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getBadUp())) {// 差
					//listMain.get(n).setIconName("icon-orange-2");
					map.put("key_value", "2");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getAlarmDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getAlarmUp())) {
					//listMain.get(n).setIconName("icon-red-1");
					map.put("key_value", "1");
				}
				if(listMain.get(n).getCountertype().startsWith("0")) {
					map.put("key_percent", listMain.get(n).getResult().substring(0,5));
				} else if(listMain.get(n).getResult().endsWith(".00")) {
					map.put("key_percent", listMain.get(n).getResult().replaceAll(".00",""));
				} else {
					map.put("key_percent", listMain.get(n).getResult().split("\\.")[0]);
				}
				map.put("key_name",  listMain.get(n).getKpiName());
				dataList.add(map);
			}
			//mainData.put(tNewnetelementList.get(m).getFname(), listMain);
			rm.put("poolid", tIndexMain.getTpoolId());
			rm.put("poolname", tNewnetelementList.get(m).getFname());
			rm.put("time", datetime);
			rm.put("data", dataList);
			l.add(rm);
		}
		
		returnMap.put("message", l);
		/*model.addAttribute("mainData", mainData);
		model.addAttribute("datetime", datetime);
		model.addAttribute("poolName", poolName);*/
		
		return JsonMapper.getInstance().toJson(returnMap);
	}
	
	//全网指标，图标模式（按词组分）
	@Override
	public String mainChart() {
		// 查询池组集合
		List<TPool> tPoolList = tPoolService.findList(new TPool());
		List<TIndexKpi> tIndexKpiList = tIndexKpiService.findList(new TIndexKpi());
		String datetime = TimeUtils.getYesterMinute(tIndexMainService.getDateTime("mme"),15);
		Map<String, Map<String, List<TIndexMain>>> mainData = new LinkedHashMap<String, Map<String, List<TIndexMain>>>();
		Map<String, List<TIndexMain>> listMain;
		
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", 200);
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		TIndexMain tIndexMain = null;
		for (int m = 0; m < tPoolList.size(); m++) {// 查询池组对应的KPI值
			Map<String,Object> rm = new LinkedHashMap<String,Object>();
			tIndexMain = new TIndexMain();
			tIndexMain.setTpoolId(tPoolList.get(m).getId());
			tIndexMain.setTpoolName(tPoolList.get(m).getFpoolname());
			tIndexMain.setTpoolType(tPoolList.get(m).getFtype());
			tIndexMain.setDateTime(datetime);
			tIndexMain.setTimeInterval(tIndexKpiList.get(0).getStarttime());
			if (tPoolList.get(m).getFtype().equals("MME")) {
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMmekpione() + "," + tIndexKpiList.get(0).getMmekpitwo()
						+ "," + tIndexKpiList.get(0).getMmekpithree() + "," + tIndexKpiList.get(0).getMmekpifour() + ","
						+ tIndexKpiList.get(0).getMmekpifive() + "," + tIndexKpiList.get(0).getMmekpisix());
			} else if (tPoolList.get(m).getFtype().equals("SAEGW")) {
				tIndexMain.setKpiList(tIndexKpiList.get(0).getSaegwkpione() + ","
						+ tIndexKpiList.get(0).getSaegwkpitwo() + "," + tIndexKpiList.get(0).getSaegwkpithree() + ","
						+ tIndexKpiList.get(0).getSaegwkpifour() + "," + tIndexKpiList.get(0).getSaegwkpifive() + ","
						+ tIndexKpiList.get(0).getSaegwkpisix());
			} else {
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPcrfkpione() + "," + tIndexKpiList.get(0).getPcrfkpitwo()
						+ "," + tIndexKpiList.get(0).getPcrfkpithree() + "," + tIndexKpiList.get(0).getPcrfkpifour()
						+ "," + tIndexKpiList.get(0).getPcrfkpifive() + "," + tIndexKpiList.get(0).getPcrfkpisix());
			}
			rm.put("poolid", tIndexMain.getTpoolId());
			rm.put("poolname", tIndexMain.getTpoolName());
			rm.put("starttime", TimeUtils.getYesterHour(datetime,Integer.parseInt(tIndexMain.getTimeInterval())));
			rm.put("endtime", datetime.substring(0,19));
			
			listMain = tIndexMainService.queryChartList(tIndexMain,tPoolList.get(m).getFtype());
			List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
			for(String key : listMain.keySet()) {
				Map<String, Object> m1 = new LinkedHashMap<String,Object>();
				List<Map<String,String>> list = new ArrayList<Map<String,String>>();
				List<TIndexMain> mainList = listMain.get(key);
				if(mainList.size() > 0) {
					m1.put("key_name", mainList.get(0).getKpiName());
				
					for(TIndexMain tm : mainList) {
						Map<String,String> map = new LinkedHashMap<String,String>();
						map.put("time", tm.getDateTime());
						map.put("key_percent", tm.getResult().substring(0,5));
						list.add(map);
					}
					m1.put("subdata", list);
					ls.add(m1);
				}
			}
			rm.put("data", ls);
			l.add(rm);
			mainData.put(tPoolList.get(m).getFpoolname(), listMain);
		}
		returnMap.put("message", l);
		if (null != tIndexMain) {
			//model.addAttribute("timeInterval",TimeUtils.getYesterHour(datetime,Integer.parseInt(tIndexMain.getTimeInterval()))+"-"+datetime.substring(0,19));
		}
		//model.addAttribute("mainChartData", mainData);
		//model.addAttribute("datetime", datetime);
		
		return JsonMapper.getInstance().toJson(returnMap);
	}
	
	//全网指标，图标模式（按网元）
	@Override
	public String netelementChart(String poolName,String datetime) {
		// 查询池组集合
		TPool tPool = new TPool();
		tPool.setFpoolname(poolName);
		List<TPool> poolList = tPoolService.findList(tPool);
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setFnid(Long.parseLong(poolList.get(0).getId()));
		List<TNewnetelement> tNewnetelementList = tNewnetelementService.findList(tNewnetelement);
		List<TIndexKpi> tIndexKpiList = tIndexKpiService.findList(new TIndexKpi());
		Map<String, Map<String, List<TIndexMain>>> mainData = new LinkedHashMap<String, Map<String, List<TIndexMain>>>();
		
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", 200);
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		
		Map<String, List<TIndexMain>> listMain;
		TIndexMain tIndexMain=null;
		for (int m = 0; m < tNewnetelementList.size(); m++) {// 查询池组对应的KPI值
			Map<String,Object> rm = new LinkedHashMap<String,Object>();
			tIndexMain = new TIndexMain();
			tIndexMain.setNetId(tNewnetelementList.get(m).getId());
			tIndexMain.setNetName(tNewnetelementList.get(m).getFname());
			tIndexMain.setTpoolType(tNewnetelementList.get(m).getType());
			tIndexMain.setDateTime(datetime);
			tIndexMain.setTimeInterval(tIndexKpiList.get(0).getStarttime());
			String tableName = "MME";
			if (tNewnetelementList.get(m).getType().equals("1")) {
				tableName="MME";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMmekpione() + "," + tIndexKpiList.get(0).getMmekpitwo()
						+ "," + tIndexKpiList.get(0).getMmekpithree() + "," + tIndexKpiList.get(0).getMmekpifour() + ","
						+ tIndexKpiList.get(0).getMmekpifive() + "," + tIndexKpiList.get(0).getMmekpisix());
			} else if (tNewnetelementList.get(m).getType().equals("2")) {
				tableName="SAEGW";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getSaegwkpione() + ","
						+ tIndexKpiList.get(0).getSaegwkpitwo() + "," + tIndexKpiList.get(0).getSaegwkpithree() + ","
						+ tIndexKpiList.get(0).getSaegwkpifour() + "," + tIndexKpiList.get(0).getSaegwkpifive() + ","
						+ tIndexKpiList.get(0).getSaegwkpisix());
			} else {
				tableName="PCRF";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPcrfkpione() + "," + tIndexKpiList.get(0).getPcrfkpitwo()
						+ "," + tIndexKpiList.get(0).getPcrfkpithree() + "," + tIndexKpiList.get(0).getPcrfkpifour()
						+ "," + tIndexKpiList.get(0).getPcrfkpifive() + "," + tIndexKpiList.get(0).getPcrfkpisix());
			}
			rm.put("poolid", tIndexMain.getNetId());
			rm.put("poolname", tIndexMain.getNetName());
			rm.put("starttime", TimeUtils.getYesterHour(datetime,Integer.parseInt(tIndexMain.getTimeInterval())));
			rm.put("endtime", datetime.substring(0,19));
			
			listMain = tIndexMainService.queryNetChartList(tIndexMain,tableName);
			List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
			for(String key : listMain.keySet()) {
				Map<String, Object> m1 = new LinkedHashMap<String,Object>();
				List<Map<String,String>> list = new ArrayList<Map<String,String>>();
				List<TIndexMain> mainList = listMain.get(key);
				if(mainList.size() > 0) {
					m1.put("key_name", mainList.get(0).getKpiName());
				
					for(TIndexMain tm : mainList) {
						Map<String,String> map = new LinkedHashMap<String,String>();
						map.put("time", tm.getDateTime());
						map.put("key_percent", tm.getResult().substring(0,5));
						list.add(map);
					}
					m1.put("subdata", list);
					ls.add(m1);
				}
			}
			rm.put("data", ls);
			l.add(rm);
			
			mainData.put(tNewnetelementList.get(m).getFname(), listMain);
		}
		returnMap.put("message", l);
		if (null != tIndexMain) {
			//model.addAttribute("timeInterval",TimeUtils.getYesterHour(datetime,Integer.parseInt(tIndexMain.getTimeInterval()))+"-"+datetime.substring(0,19));
		}
		/*model.addAttribute("mainChartData", mainData);
		model.addAttribute("datetime", datetime);
		model.addAttribute("poolName", poolName);*/
		return JsonMapper.getInstance().toJson(returnMap);
	}
	
	
	//ebm异常原因值多维分析，chart：饼状图， list：列表（分页）
	@Override
	public String queryEbmlogStatistics(String startTime, String endTime, String netid, String eventType, int limit,
			int offset) {
		
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		ServerPagination<TEbmlogStatistics> page = new ServerPagination<TEbmlogStatistics>();
		try {
			String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
			List<TEbmlogStatistics> list = tEbmlogStatisticsService.queryList(date.replaceAll("-", "_"),startTime,endTime,netid,eventType,offset,limit);
			int total = tEbmlogStatisticsService.queryListCount(date.replaceAll("-", "_"), startTime, endTime, netid, eventType);
			page.setTotal(total);
			page.setRows(list);
			
			Map<String,Object> mapData = new LinkedHashMap<String,Object>();
			mapData.put("status", 200);
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			for(TEbmlogStatistics statistics : list) {
				String CC = statistics.getCausecode().split("\\(")[0];
				String SCC = statistics.getSubcausecode().split("\\(")[0] + 
 			    "|" + statistics.getCausecode().split("\\(")[1] + "|" + statistics.getSubcausecode().split("\\(")[1] +
 			    "|" + statistics.getCc() + "|" + statistics.getScc() + "|" + statistics.getAction() + "|" + statistics.getDomain();
				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("key_id", statistics.getNetid());
				map.put("name", CC + " " + SCC);
				map.put("key_percent", ""+statistics.getFailures().intValue());
				mapList.add(map);
			}
			mapData.put("message", mapList);
			
			returnMap.put("chart", mapData);
			returnMap.put("list", page);
		} catch (Exception e) {
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(returnMap);
	}

	//点击饼状图，或多维分析进入改方法
	@Override
	public String analysis(String cc, String scc, String netid, String startTime, String endTime,
			String eventType) {
		String returnStr;
		if(eventType.equals("att")){
			String str[] = {"APN","SGW","PGW","MSC","IMSISERIES","IMSI","IMEITAC","NAS_ESM_CAUSE_CODE","ECI","TAI","LAI","ATTACHTYPE","MMEI","MSISDN","TAC"};
			returnStr = queryMap(str,netid,cc,scc,startTime,endTime,eventType);
		}else if(eventType.equals("tau")){
			String str[] = {"APN","SGW","PGW","MSC","IMEITAC","IMSISERIES","IMSI","TAI","ECI","LAI","MMEI","TAUTYPE","OLDTAI","OLDMME","OLDSGW",
			                "OLDSGSN","OLDMME_NEWMME","OLDTAI_NEWTAI","OLDSGSN_NEWMME","OLDRAI_NEWTAI","MSISDN","TAC"};
			returnStr = queryMap(str,netid,cc,scc,startTime,endTime,eventType);
		}else if(eventType.equals("pdn")||eventType.equals("ims")){
			String str[] = {"APN","SGW","PGW","IMEITAC","IMSISERIES","IMSI","TAI","ECI","LAI","MMEI","PDN_CONNECT_REQUEST_TYPE",
			                "UE_REQUESTED_APN","BEARER_CAUSE","MSISDN","TAC"};
			returnStr = queryMap(str,netid,cc,scc,startTime,endTime,eventType);
		}else{
			String str[] = {"APN","SGW","PGW","IMEITAC","IMSISERIES","IMSI","TAI","ECI","LAI","MMEI","HANDOVER_TYPE","OLDMME","OLDSGSN","OLDSGW",
			                "OLDRAI","OLDMME_NEWMME","OLDTAI_NEWTAI","OLDECI","OLDECI_NEWECI","HANDOVER_NODE_ROLE","HANDOVER_RAT_CHANGE_TYPE",
			                "HANDOVER_SGW_CHANGE_TYPE","SRVCC_TARGET_LAI","MSC_ON_SV","STN_SR","SRVCC_TYPE","RNCID","OLDTAI","MSISDN","TAC"};
			returnStr = queryMap(str,netid,cc,scc,startTime,endTime,eventType);
		}
		return returnStr;
	}
	
	public String queryMap(String str[],String netid,String cc,String scc,String startTime,String endTime,String eventType){
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
			 dimensionlist = tEbmlogStatisticsService.getDimensionlistByKey(parm,netid,date.replaceAll("-", "_"),startTime,endTime,ip,str[i],num,eventType,"value");
			 String key = str[i];
			 if("IMEITAC".equals(key)) {
				 key = "终端型号";
			 } else if("IMSISERIES".equals(key)) {
				 key = "IMSI号段";
			 } else if("ECI".equals(key)) {
				 key = "基站";
			 }
			 map.put(key, dimensionlist);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		return JsonMapper.getInstance().toJson(map);
	}
	
	//用户登录
	@Override
	public String login(String username, String password) {
		User user = systemService.getUserByLoginName(username);
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		if (user != null) {
			if (SystemService.validatePassword(password, user.getPassword())){
				returnMap.put("status", "200");
				map.put("userid", user.getId());
				map.put("username", user.getName());
				map.put("mail", user.getEmail());
				map.put("root", user.getRoleNames());
			} else {
				returnMap.put("status", "201");
				map.put("msg", "用户名或密码错误");
			}
		} else {
			returnMap.put("status", "201");
			map.put("msg", "用户名或密码错误");
		}
		returnMap.put("message", map);
		return JsonMapper.getInstance().toJson(returnMap);
	}
	
	private List<TNewnetelement> getTNewnetelementList(String netType) {
		List<Dict> dictList = DictUtils.getDictList("biz_net_type");
		String parameterEpc = "1";
		for(Dict d : dictList) {
			if(netType.equals(d.getLabel())) {
				parameterEpc = d.getValue();
				break;
			}
		}
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setType(parameterEpc);
		List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
		return list;
	}

	//用户聚合查询
	@Override
	public String userQuery(String searchType, String searchValue) {
		List<TNewnetelement> list = getTNewnetelementList("MME");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Process proc = null;
		BufferedReader br = null;
		List<String> mmedata = new ArrayList<String>();
		String msisdn = "";
		//String showVal = CdrDecoder.getTransformValue("hide","show", "");
		String showVal = "show";
		try {
			List<Map<String,String>> connList = new ArrayList<Map<String,String>>();
			dataMap.put("msisdn", searchValue);
			dataMap.put("terminalBrand", "Apple"); //客户终端品牌
			dataMap.put("terminalModel", "iphone 7"); //客户终端品牌
			String ipStr = "";
			String user = "";
			String password = "";
			for(TNewnetelement e : list) {
				if("".equals(user)) {
					user = e.getUsername1();
				}
				if("".equals(password)) {
					password = e.getPassword1();
				}
				ipStr += " -sgsn " + e.getIpadr();
			}
			
			String[] cmd = new String[3];
			cmd[0] = "sh";
			cmd[1] = "-c";
			cmd[2] = "/usr/bin/perl -w /opt/Ericsson/core/bin/get_userinfo_detail.pl";
			cmd[2] += ipStr;
			cmd[2] += " -user "+user+" -password "+password+" -"+searchType+" "+searchValue;
			cmd[2] += " -hostfile /opt/Ericsson/core/bin/" + Global.getConfig("hostFile");
			
			proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			System.out.println(cmd[2]);
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            //br = readFile("D:/test.txt");//测试代码
			String line = null;
			int flag = 0;
			while((line = br.readLine())!=null){
				if(line.contains("==========================")) {
					while((line = br.readLine())!=null) {
						if(line.contains(":")){
							String[] item = line.trim().split(":");
							if(item.length>=2){
								if("state".equals(item[0].trim())) {
									String[] val = item[1].trim().split("\\s+");
									dataMap.put(item[0].trim(), val[0] + "</br>" + val[1]);
								} else {
									dataMap.put(item[0].trim(), item[1].trim());
								}
							}else{
								dataMap.put(item[0].trim(), "N/A");
							}
						}
						if(line.contains("msisdn") && line.contains(":")) {
							String[] item = line.trim().split(":");
							if(item.length>=2){
								msisdn = item[1];
							}
							
						}
						if(line.contains("imei") && line.contains(":")){
							String imeitacTranslate = null;
							String nodeValue = line.trim().split(":")[1].trim();
							if(nodeValue.length()>=8){
								String imeisvKey = nodeValue.substring(0, 8);
								TImeitacTranslate imeitacObj = imeitacTranslateService.get(imeisvKey);
								if(imeitacObj != null){
									imeitacTranslate = imeitacObj.getPhonename();
								}
							}
							if(imeitacTranslate != null){
								dataMap.put("imei", imeitacTranslate);
							}else{
								dataMap.put("imei", "未知");
							}
						}
						
						if(line.contains("volte")) {
							if(line.contains("yes")){
								dataMap.put("userProp", "VOLTE");
							}else{
								flag++;
							}
						}
						if(line.contains("csfb")){
							if(line.contains("yes")) {
								dataMap.put("userProp", "CSFB");
							}else{
								flag++;
							}
						}
						if(flag==2){
							dataMap.put("userProp", "2G/3G");
						}
						
						//Active PDN connection 相关信息
						if(line.contains("Active")) {
							String line1 = null;
							Map<String, String> m = new HashMap<String,String>();
							boolean end = false;
							while((line1 = br.readLine())!=null){
								if(line1.contains("apn")) {
									
									String[] apnName = line1.split(":");
									if(apnName.length >= 2) {
										m.put("apnName", apnName[1]);
									}
								}
								if(line1.contains("ue")) {
									String line2 = null;
									while((line2 = br.readLine())!=null){
										if(line2 != null && !"".equals(line2.trim())) {
											if(line2.contains("ipv4")) {
												String[] ipv4 = line2.trim().split(":");
												if(ipv4.length>=2 && !StringUtils.isBlank(ipv4[1])){
													m.put("iptype", "ipv4");
													m.put("ip4addr", ipv4[1].trim());
												}else{
													m.put("iptype", ipv4[0].trim());
													m.put("ip4addr", "N/A");
												}
											}
											if(line2.contains("ipv6")){
												String[] ipv6 = line2.trim().split(":");
												if(ipv6.length>=2 && !StringUtils.isBlank(ipv6[1])){
													m.put("iptype", "ipv6");
													m.put("ip6addr", line2.substring(line2.indexOf(":")+1));
												}else{
													m.put("iptype", "ipv6");
													m.put("ip6addr", "N/A");
												}
												//end = true;
												break;
											}
										}
									}
								}
								if(line1.contains("sgw")) {
									String line2 = null;
									while((line2 = br.readLine())!=null) {
										if(line2 != null && !"".equals(line2.trim())) {
											if(line2.contains("node")) {
												String[] node = line2.trim().split(":");
												if(node.length >= 2 && !StringUtils.isBlank(node[1])) {
													m.put("sgw", node[1].trim());
												} else {
													m.put("sgw", "N/A");
												}
												break;
											}
											
										}
									}
								}
								if(line1.contains("pgw")) {
									String line2 = null;
									while((line2 = br.readLine())!=null) {
										if(line2 != null && !"".equals(line2.trim())) {
											
											if(line2.contains("node")) {
												String[] node = line2.trim().split(":");
												if(node.length >= 2 && !StringUtils.isBlank(node[1])) {
													m.put("pgw", node[1].trim());
												} else {
													m.put("pgw", "N/A");
												}
												end = true;
												break;
											}
											
										}
									}
								}
								if(end) {
									connList.add(m);
									break;
								}
								
							}
						}
					}
					
				} /*else {
					if(!StringUtils.isBlank(line)) {
						if(line.contains("Last") && line.contains("Visited") && line.contains("Tracking") && line.contains("Area")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("Tracking") && line.contains("Area") && line.contains("List")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Type")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Name")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Id")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("PLMN") && line.contains("Id")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("Latest") && line.contains("eNodeB") && line.contains("List")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else {
							mmedata.add(line.replaceAll(" ", "&nbsp;"));
						}
					}
				}*/
				
			}
			dataMap.put("connList", connList);
		} catch (Exception e) {
    			e.printStackTrace();
    	}finally{
    		if(proc != null){
    			proc.destroy();
    		}
    		if(br != null){
    			try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
		
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", "200");
		Map<String,Object> levelMap1 = new LinkedHashMap<String,Object>();
		levelMap1.put("missdn", dataMap.get("msisdn"));
		levelMap1.put("IMSI", dataMap.get("imsi"));
		levelMap1.put("termtype", dataMap.get("imei"));
		levelMap1.put("user_status", dataMap.get("state"));
		levelMap1.put("user_prop", dataMap.get("userProp") + "用户");
		levelMap1.put("user_nam", dataMap.get("nam"));
		levelMap1.put("user_mme", dataMap.get("mme"));
		levelMap1.put("user_ta", dataMap.get("ta"));
		levelMap1.put("user_lastta", dataMap.get("last_ta"));
		levelMap1.put("enodeb", dataMap.get("enb"));
		String pcrf = execpcrf(msisdn);
		levelMap1.put("user_pcrf", pcrf);
		List<Map<String,String>> connList = (List<Map<String,String>>)dataMap.get("connList");
		List<Map<String,Object>> apnList = new ArrayList<Map<String,Object>>();
		for(Map<String,String> m : connList) {
			Map<String,Object> apn = new LinkedHashMap<String,Object>();
			apn.put("msg", m.get("apnName") + " UE IP : ipv4:" + m.get("ip4addr") + " / ipv6:" + m.get("ip6addr"));
			List<Map<String,String>> subfiledList = new ArrayList<Map<String,String>>();
			Map<String,String> map = new LinkedHashMap<String,String>();
			map.put("sgw", m.get("sgw"));
			map.put("pgw", m.get("pgw"));
			subfiledList.add(map);
			apn.put("subfiled", subfiledList);
			apnList.add(apn);
		}
		levelMap1.put("apn", apnList);
		returnMap.put("message", levelMap1);
		
		/*if("show".equals(showVal)) {
			model.addAttribute("show","true");
		} else {
			model.addAttribute("show","false");
		}
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("searchType",searchType);
		model.addAttribute("searchValue", searchValue);
		session.setAttribute("mmedata", mmedata);
		execpcrf(msisdn,model,session);*/
		return JsonMapper.getInstance().toJson(returnMap);
	}
	
	public String execpcrf(String searchValue) {
		String returnVal = "";
		List<TNewnetelement> list = getTNewnetelementList("PCRF");
		String ipStr = "";
		String user = "";
		String password = "";
		for(TNewnetelement e : list) {
			if("".equals(user)) {
				user = e.getUsername1();
			}
			if("".equals(password)) {
				password = e.getPassword1();
			}
			ipStr += " -ip " + e.getIpadr();
		}
		Process proc = null;
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/opt/Ericsson/core/bin/get_pcrf.pl";
		cmd[2] += ipStr;
		cmd[2] += " -user "+user+" -password "+password+" --msisdn "+searchValue;
		cmd[2] += " -hostfile /opt/Ericsson/core/bin/oamhosts";
		System.out.println(cmd[2]);
		BufferedReader br = null;
		String line = null;
		List<String> l = new ArrayList<String>();
		try {
			proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			//br = readFile("D:/test2.txt");//测试代码
			while((line = br.readLine())!=null){
				if(line.contains("==========================")) {
					while((line = br.readLine())!=null) {
						if(line.contains("pcrf")) {
							String[] prcf = line.split(":");
							if(prcf.length >= 2 && !StringUtils.isBlank(prcf[1])) {
								String[] val = prcf[1].trim().split(",");
								String pcrfval = "";
								if(val.length >= 2) {
									for(String v : val) {
										pcrfval += v + "<br>";
									}
								} else {
									pcrfval = prcf[1].trim();
								}
								returnVal = pcrfval;
								//model.addAttribute("pcrfname", pcrfval);
							}
						}
					}
				} else {
					if(!StringUtils.isBlank(line)) {
						//l.add(line);
						l.add(line.replaceAll(" ", "&nbsp;"));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(proc != null){
    			proc.destroy();
    		}
    		if(br != null){
    			try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
		}
		
		return returnVal;
	}

	@Override
	public String showmme(String searchType, String searchValue) {
		List<TNewnetelement> list = getTNewnetelementList("MME");
		Process proc = null;
		BufferedReader br = null;
		List<String> mmedata = new ArrayList<String>();
		String msisdn = "";
		//String showVal = CdrDecoder.getTransformValue("hide","show", "");
		String showVal = "show";
		try {
			List<Map<String,String>> connList = new ArrayList<Map<String,String>>();
			String ipStr = "";
			String user = "";
			String password = "";
			for(TNewnetelement e : list) {
				if("".equals(user)) {
					user = e.getUsername1();
				}
				if("".equals(password)) {
					password = e.getPassword1();
				}
				ipStr += " -sgsn " + e.getIpadr();
			}
			
			String[] cmd = new String[3];
			cmd[0] = "sh";
			cmd[1] = "-c";
			cmd[2] = "/usr/bin/perl -w /opt/Ericsson/core/bin/get_userinfo_detail.pl";
			cmd[2] += ipStr;
			cmd[2] += " -user "+user+" -password "+password+" -"+searchType+" "+searchValue;
			cmd[2] += " -hostfile /opt/Ericsson/core/bin/" + Global.getConfig("hostFile");
			
			proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			System.out.println(cmd[2]);
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            //br = readFile("D:/test.txt");//测试代码
			String line = null;
			while((line = br.readLine())!=null){
				if(line.contains("==========================")) {
				} else {
					if(!StringUtils.isBlank(line)) {
						if(line.contains("Last") && line.contains("Visited") && line.contains("Tracking") && line.contains("Area")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("Tracking") && line.contains("Area") && line.contains("List")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Type")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Name")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("Id")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("eNodeB") && line.contains("PLMN") && line.contains("Id")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else if(line.contains("Latest") && line.contains("eNodeB") && line.contains("List")) {
							if("show".equals(showVal)) {
								mmedata.add(line.replaceAll(" ", "&nbsp;"));
							}
						} else {
							mmedata.add(line.replaceAll(" ", "&nbsp;"));
						}
					}
				}
			}
		} catch (Exception e) {
    			e.printStackTrace();
    	}finally{
    		if(proc != null){
    			proc.destroy();
    		}
    		if(br != null){
    			try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", "200");
		returnMap.put("message", mmedata);
		return JsonMapper.getInstance().toJson(returnMap);
	}

	@Override
	public String showpcrf(String searchValue) {
		List<TNewnetelement> list = getTNewnetelementList("PCRF");
		String ipStr = "";
		String user = "";
		String password = "";
		for(TNewnetelement e : list) {
			if("".equals(user)) {
				user = e.getUsername1();
			}
			if("".equals(password)) {
				password = e.getPassword1();
			}
			ipStr += " -ip " + e.getIpadr();
		}
		Process proc = null;
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		cmd[2] = "/opt/Ericsson/core/bin/get_pcrf.pl";
		cmd[2] += ipStr;
		cmd[2] += " -user "+user+" -password "+password+" --msisdn "+searchValue;
		cmd[2] += " -hostfile /opt/Ericsson/core/bin/oamhosts";
		System.out.println(cmd[2]);
		BufferedReader br = null;
		String line = null;
		List<String> l = new ArrayList<String>();
		try {
			proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			//br = readFile("D:/test2.txt");//测试代码
			while((line = br.readLine())!=null){
				if(line.contains("==========================")) {
				} else {
					if(!StringUtils.isBlank(line)) {
						l.add(line.replaceAll(" ", "&nbsp;"));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(proc != null){
    			proc.destroy();
    		}
    		if(br != null){
    			try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
		}
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", "200");
		returnMap.put("message", l);
		return JsonMapper.getInstance().toJson(returnMap);
	}

	@Override
	public String showpgw(String pgw, String imsi) {
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setFname(pgw);
		List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
		Process proc = null;
		BufferedReader br = null;
		String line = null;
		List<String> l = new ArrayList<String>();
		if(null != list) {
			TNewnetelement t = list.get(0);
			String[] cmd = new String[3];
			cmd[0] = "sh";
			cmd[1] = "-c";
			cmd[2] = "/opt/Ericsson/core/bin/sendcmd_epg.pl -ip " + t.getIpadr() + " -user " + t.getUsername1() + " -password '" + t.getPassword1() + "' -cmd 'ManagedElement=1,Epg=1,Pgw=1,userInfo imsi "+imsi+"'";
			try {
				proc = Runtime.getRuntime().exec(cmd);
				proc.waitFor();
				br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				while((line = br.readLine())!=null){
					l.add(line.replaceAll(" ", "&nbsp;"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", "200");
		returnMap.put("message", l);
		return JsonMapper.getInstance().toJson(returnMap);
	}

	@Override
	public String showsgw(String sgw, String imsi) {
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setFname(sgw);
		List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);
		Process proc = null;
		BufferedReader br = null;
		String line = null;
		List<String> l = new ArrayList<String>();
		if(null != list) {
			TNewnetelement t = list.get(0);
			String[] cmd = new String[3];
			cmd[0] = "sh";
			cmd[1] = "-c";
			cmd[2] = "/opt/Ericsson/core/bin/sendcmd_epg.pl -ip " + t.getIpadr() + " -user " + t.getUsername1() + " -password '" + t.getPassword1() + "' -cmd 'ManagedElement=1,Epg=1,Sgw=1,userInfo imsi "+imsi+"'";
			System.out.println(cmd[2]);
			try {
				proc = Runtime.getRuntime().exec(cmd);
				proc.waitFor();
				br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				while((line = br.readLine())!=null){
					l.add(line.replaceAll(" ", "&nbsp;"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", "200");
		returnMap.put("message", l);
		return JsonMapper.getInstance().toJson(returnMap);
	}

	@Override
	public String aaa() {
		// TODO Auto-generated method stubaa
		return "aaa";
	}

}
