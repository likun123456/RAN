package com.thinkgem.jeesite.modules.performance.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormula;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TFormulaService;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.KpiDetailVO;
import com.thinkgem.jeesite.modules.performance.entity.TMultipleIndex;
import com.thinkgem.jeesite.modules.performance.entity.TStatisticsVO;
import com.thinkgem.jeesite.modules.performance.service.TMultipleIndexService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 性能指标综合查询Controller
 * 
 * @author 杨海
 * @version 2017-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/multipleIndex")
@Scope("prototype")
public class TMultipleIndexController extends BaseController {

	@Autowired
	private TMultipleIndexService tMultipleIndexService;
	@Autowired
	private TNewnetelementService tnewnetelementService;
	@Autowired
	private TFormulaService tFormulaService;
	@Autowired
	private TPoolService tPoolService;

	@ModelAttribute
	public TMultipleIndex get(@RequestParam(required = false) String id) {
		TMultipleIndex entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tMultipleIndexService.get(id);
		}
		if (entity == null) {
			entity = new TMultipleIndex();
		}
		return entity;
	}

	@RequiresPermissions("performance:multipleIndex:view")
	@RequestMapping(value = { "chart" })
	public String chart(@Valid @ModelAttribute("tMultipleIndex") TMultipleIndex tMultipleIndex, String netName,
			HttpServletRequest request, HttpServletResponse response, Model model, String kpi, String poolName,
			String datetime) {
		if (poolName != null && poolName.contains("-")) {
			String netType = poolName.split("-")[0];
			model.addAttribute("netType", netType);
			// model.addAttribute("poolName", poolName);
			String netids = tPoolService.queryNetidsByPoolName(poolName);
			String netNames = tPoolService.queryNetNamesByPoolName(poolName);
			String formulaName = tFormulaService.queryFormulaNameByKpi(kpi);
			model.addAttribute("netid", netids);
			model.addAttribute("netNames", netNames);
			model.addAttribute("formulaType", kpi);
			model.addAttribute("formulaName", formulaName);
			String cycletime = tFormulaService.queryCycleTime();
			String startTime = DateUtils.getNhourDateTime(datetime, Integer.parseInt(cycletime));
			model.addAttribute("pastScope", "0");
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", datetime);
			return "modules/performance/multipleIndexChartGoTo";
		} else if (netName != null && !netName.equals("")) {
			String netType = tPoolService.queryNetTypeByNetName(netName);
			model.addAttribute("netType", netType);
			String netid = tPoolService.queryNetidByNetName(netName);
			model.addAttribute("netid", netid);
			model.addAttribute("netNames", netName);
			model.addAttribute("formulaType", kpi);
			String formulaName = tFormulaService.queryFormulaNameByKpi(kpi);
			model.addAttribute("formulaName", formulaName);
			String cycletime = tFormulaService.queryCycleTime();
			String startTime = DateUtils.getNhourDateTime(datetime, Integer.parseInt(cycletime));
			model.addAttribute("pastScope", "0");
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", datetime);
			return "modules/performance/multipleIndexChartGoTo";
		} else {
			return "modules/performance/multipleIndexChart";
		}
	}

	@RequestMapping(value = { "findChart" })
	@ResponseBody
	public List<List<List<TStatisticsVO>>> findChart(
			@Valid @ModelAttribute("tMultipleIndex") TMultipleIndex tMultipleIndex, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (null != tMultipleIndex && null != tMultipleIndex.getNetType() && !"".equals(tMultipleIndex.getNetType())) {
			String result = "";
			if (StringUtils.isNumeric(tMultipleIndex.getNetType())) {
				result = DictUtils.getDictLabel(tMultipleIndex.getNetType(), "biz_net_type", result);
			}else{
				result=tMultipleIndex.getNetType();
			}
			tMultipleIndex.setTemp_field1(result);
			String startTime = tMultipleIndex.getStartTime();
			String endTime = tMultipleIndex.getEndTime();
			startTime = "2018-04-23"+startTime.substring(10, startTime.length());
			endTime = "2018-04-24"+endTime.substring(10, endTime.length());
			tMultipleIndex.setStartTime(startTime);
			tMultipleIndex.setEndTime(endTime);
		}
		return tMultipleIndexService.findChart(tMultipleIndex);
	}

	@RequestMapping(value = { "findList" })
	@ResponseBody
	public String findList(String startTime, String endTime, String netType, String netid, String formulaType,
			int limit, int offset, String sortName, String sortOrder) {
		String result = "";
		if (null != netType && !"".equals(netType)) {
			if (StringUtils.isNumeric(netType)) {
				result = DictUtils.getDictLabel(netType, "biz_net_type", result);
			}else{
				result=netType;
			}
		}
		startTime = "2018-04-24"+startTime.substring(10, startTime.length());
		endTime = "2018-04-24"+endTime.substring(10, endTime.length());
		List<TStatisticsVO> list = tMultipleIndexService.queryList(startTime, endTime, result, netid.split(","),
				formulaType.split(","), offset, limit, sortName, sortOrder);
		int total = tMultipleIndexService.queryListCount(startTime, endTime, result, netid, formulaType);
		ServerPagination<TStatisticsVO> page = new ServerPagination<TStatisticsVO>();
		page.setTotal(total);
		page.setRows(list);
		return JsonMapper.getInstance().toJson(page);
	}

	@RequiresPermissions("performance:multipleIndex:view")
	@RequestMapping(value = { "list" })
	public String list(@Valid @ModelAttribute("tMultipleIndex") TMultipleIndex tMultipleIndex,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/performance/multipleIndexList";
	}

	@RequestMapping(value = "getDynamicColumns")
	@ResponseBody
	public String getDynamicColumns(String netType,String netId,String formulaId,String recordTime) {
		MongoClient mongo = new MongoClient(Global.getConfig("mongodb.host"), 27017);
		MongoDatabase database = mongo.getDatabase(Global.getConfig("mongodb.db"));
		
		String netTypeStr = "";
		if (null != netType &&!"".equals(netType)) {			
			netTypeStr = DictUtils.getDictLabel(netType, "biz_net_type", netTypeStr);
		}
		MongoCollection<Document> collection = database.getCollection(netTypeStr);
		
		FindIterable<Document> iterable = collection.find(new Document("netId", netId).append("formulaId", formulaId).append("recordTime", recordTime));  
		
		MongoCursor<Document> mongoCursor = iterable.iterator();   
		List<String> columnNames = new ArrayList<String>();
		
        while(mongoCursor.hasNext()){
            Document d=mongoCursor.next(); 
            KpiDetailVO KpiDetail = (KpiDetailVO) JsonMapper.fromJsonString(d.toJson(), KpiDetailVO.class);
    		columnNames.add("OBJTYPE");
    		columnNames.add("INST");
			List<Map<String, String>> currentTable = KpiDetail.getCurrentTable();
    		if(currentTable != null && currentTable.size() > 0){
    			for(Map<String, String> columns : currentTable){
    				for(String column : columns.keySet()){
    					if(!columnNames.contains(column.replace("@@", "."))){
    						columnNames.add(column.replace("@@", "."));
    					}
        			}
    			}
    		}
            mongo.close();
            break;
        }
		return JsonMapper.getInstance().toJson(columnNames);
	}
	
	@RequiresPermissions("performance:multipleIndex:view")
	@RequestMapping(value = {"showDetail"})
	public String showDetail(String netType,String netId,String formulaId,String recordTime,String netName,String formulaName,String formulaText,Model model){
		MongoClient mongo = new MongoClient(Global.getConfig("mongodb.host"), 27017);
		MongoDatabase database = mongo.getDatabase(Global.getConfig("mongodb.db"));
		
		String netTypeStr = "";
		if (null != netType &&!"".equals(netType)) {			
			netTypeStr = DictUtils.getDictLabel(netType, "biz_net_type", netTypeStr);
		}
		MongoCollection<Document> collection = database.getCollection(netTypeStr);
		
		FindIterable<Document> iterable = collection.find(new Document("netId", netId).append("formulaId", formulaId).append("recordTime", recordTime));  
		
		MongoCursor<Document> mongoCursor = iterable.iterator();    
		
        while(mongoCursor.hasNext()){
            Document d=mongoCursor.next(); 
            KpiDetailVO KpiDetail = (KpiDetailVO) JsonMapper.fromJsonString(d.toJson(), KpiDetailVO.class);
            KpiDetail.setNetType(netType);
            KpiDetail.setNetName(netName);
            KpiDetail.setFormulaName(formulaName);
            KpiDetail.setFormulaText(formulaText);
            model.addAttribute("KpiDetail", KpiDetail);
            mongo.close();
            break;
        }
		return "modules/performance/kpiDetail";
	}
	
	@RequiresPermissions("performance:multipleIndex:view")
	@RequestMapping(value = {"queryCurrentTable"})
	@ResponseBody
	public String queryCurrentTable(String netType,String netId,String formulaId,String recordTime){
		MongoClient mongo = new MongoClient(Global.getConfig("mongodb.host"), 27017);
		MongoDatabase database = mongo.getDatabase(Global.getConfig("mongodb.db"));
		
		String netTypeStr = "";
		if (null != netType &&!"".equals(netType)) {			
			netTypeStr = DictUtils.getDictLabel(netType, "biz_net_type", netTypeStr);
		}
		MongoCollection<Document> collection = database.getCollection(netTypeStr);
		
		FindIterable<Document> iterable = collection.find(new Document("netId", netId).append("formulaId", formulaId).append("recordTime", recordTime));  
		
		MongoCursor<Document> mongoCursor = iterable.iterator();  
		
		ServerPagination<Map<String, String>> serverPage = null;
		
        while(mongoCursor.hasNext()){
            Document d=mongoCursor.next(); 
            KpiDetailVO KpiDetail = (KpiDetailVO) JsonMapper.fromJsonString(d.toJson(), KpiDetailVO.class);
            
    		serverPage = new ServerPagination<Map<String, String>>();
    		
    		List<Map<String, String>> currentTable = KpiDetail.getCurrentTable();
    		List<Map<String,String>> rcurrentTable = new ArrayList<Map<String,String>>();
    		if(currentTable != null){
				
				for(Map<String, String> tableRow : currentTable){
					Map<String,String> rtableRow = new HashMap<String,String>();
					for(Map.Entry<String, String> entry : tableRow.entrySet()){
						rtableRow.put(entry.getKey().replace("@@", "."), entry.getValue());
	    			}
					rcurrentTable.add(rtableRow);
				}
				serverPage.setRows(rcurrentTable);
				serverPage.setTotal(rcurrentTable.size());
    		}
            mongo.close();
            break;
        }
		return JsonMapper.getInstance().toJson(serverPage);
	}
	
	@RequiresPermissions("performance:multipleIndex:view")
	@RequestMapping(value = {"queryLastTable"})
	@ResponseBody
	public String queryLastTable(String netType,String netId,String formulaId,String recordTime){
		MongoClient mongo = new MongoClient(Global.getConfig("mongodb.host"), 27017);
		MongoDatabase database = mongo.getDatabase(Global.getConfig("mongodb.db"));
		
		String netTypeStr = "";
		if (null != netType &&!"".equals(netType)) {			
			netTypeStr = DictUtils.getDictLabel(netType, "biz_net_type", netTypeStr);
		}
		MongoCollection<Document> collection = database.getCollection(netTypeStr);
		
		FindIterable<Document> iterable = collection.find(new Document("netId", netId).append("formulaId", formulaId).append("recordTime", recordTime));  
		
		MongoCursor<Document> mongoCursor = iterable.iterator();  
		
		ServerPagination<Map<String, String>> serverPage = null;
		
        while(mongoCursor.hasNext()){
            Document d=mongoCursor.next(); 
            KpiDetailVO KpiDetail = (KpiDetailVO) JsonMapper.fromJsonString(d.toJson(), KpiDetailVO.class);
            
    		serverPage = new ServerPagination<Map<String, String>>();
    		
    		List<Map<String, String>> lastTable = KpiDetail.getLastTable();
    		List<Map<String,String>> rlastTable = new ArrayList<Map<String,String>>();
    		if(lastTable != null){
				
				for(Map<String, String> tableRow : lastTable){
					Map<String,String> rtableRow = new HashMap<String,String>();
					for(Map.Entry<String, String> entry : tableRow.entrySet()){
						rtableRow.put(entry.getKey().replace("@@", "."), entry.getValue());
	    			}
					rlastTable.add(rtableRow);
				}
				serverPage.setRows(rlastTable);
				serverPage.setTotal(rlastTable.size());
    		}
    		
            mongo.close();
            break;
        }
		return JsonMapper.getInstance().toJson(serverPage);
	}

	/**
	 * 根据选择的类型，查询对应的网元
	 * 
	 * @param extId
	 * @param type
	 * @param grade
	 * @param isAll
	 * @param parameterEpc
	 * @param response
	 * @return
	 */
	@RequiresPermissions("performance:multipleIndex:view")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, @RequestParam(required = false) String parameterEpc,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if (null != parameterEpc && !parameterEpc.equals("")) {

			String result = "";
			result = DictUtils.getDictLabel(parameterEpc, "biz_net_type", result);
			List<TPool> poolList = tPoolService.queryPoolListByType(result);

			TNewnetelement tnewnetelement = new TNewnetelement();
			tnewnetelement.setType(parameterEpc);
			List<TNewnetelement> list = tnewnetelementService.findList(tnewnetelement);

			for (TPool pool : poolList) {
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "p" + pool.getId());
				mapParent.put("pId", "0");
				mapParent.put("pIds", "0");
				mapParent.put("name", pool.getFpoolname());
				mapList.add(mapParent);
			}

			for (int i = 0; i < list.size(); i++) {
				TNewnetelement e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				/*
				 * if (i == 0) { Map<String, Object> mapParent =
				 * Maps.newHashMap(); mapParent.put("id", "0");
				 * mapParent.put("pId", "0"); mapParent.put("pIds", "0");
				 * mapParent.put("name", "网元"); mapList.add(mapParent); }
				 */
				map.put("id", e.getId());
				map.put("pId", "p" + e.getFnid());
				map.put("pIds", "0," + "p" + e.getFnid());
				map.put("name", e.getFname());
				mapList.add(map);
			}
		}
		return mapList;
	}

	/**
	 * 根据选择的类型，查询对应的网元
	 * 
	 * @param extId
	 * @param type
	 * @param grade
	 * @param isAll
	 * @param parameterEpc
	 * @param response
	 * @return
	 */
	@RequiresPermissions("performance:multipleIndex:view")
	@ResponseBody
	@RequestMapping(value = "treeDataFormula")
	public List<Map<String, Object>> treeDataFormula(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, @RequestParam(required = false) String parameterEpc,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if (!parameterEpc.equals("SAEGW") && !parameterEpc.equals("")) {
			TFormula tFormula = new TFormula();
			tFormula.setNettype(parameterEpc);
			List<TFormula> list = tFormulaService.findList(tFormula);
			for (int i = 0; i < list.size(); i++) {
				TFormula e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				if (i == 0) {
					Map<String, Object> mapParent = Maps.newHashMap();
					mapParent.put("id", "0");
					mapParent.put("pId", "0");
					mapParent.put("pIds", "0");
					mapParent.put("name", "统计指标");
					mapList.add(mapParent);
				}
				map.put("id", e.getId());
				map.put("pId", "0");
				map.put("pIds", "0,1");
				map.put("name", e.getName());
				mapList.add(map);
			}
		} else {
			Map<String, Object> mapParent = Maps.newHashMap();
			mapParent.put("id", "0");
			mapParent.put("pId", "0");
			mapParent.put("pIds", "0");
			mapParent.put("name", "统计指标");
			mapList.add(mapParent);

			TFormula tFormulaPgw = new TFormula();
			tFormulaPgw.setNettype("EPG-PGW");
			List<TFormula> listPgw = tFormulaService.findList(tFormulaPgw);
			for (int i = 0; i < listPgw.size(); i++) {
				TFormula e = listPgw.get(i);
				Map<String, Object> mapPgw = Maps.newHashMap();
				if (i == 0) {
					Map<String, Object> mapParentPgw = Maps.newHashMap();
					mapParentPgw.put("id", "1");
					mapParentPgw.put("pId", "0");
					mapParentPgw.put("pIds", "0,1");
					mapParentPgw.put("name", "PGW");
					mapList.add(mapParentPgw);
				}
				mapPgw.put("id", e.getId());
				mapPgw.put("pId", "1");
				mapPgw.put("pIds", "0,1");
				mapPgw.put("name", e.getName());
				mapList.add(mapPgw);
			}

			TFormula tFormulaSgw = new TFormula();
			tFormulaSgw.setNettype("EPG-SGW");
			List<TFormula> listSgw = tFormulaService.findList(tFormulaSgw);
			for (int i = 0; i < listSgw.size(); i++) {
				TFormula e = listSgw.get(i);
				Map<String, Object> mapSgw = Maps.newHashMap();
				if (i == 0) {
					Map<String, Object> mapParentSgw = Maps.newHashMap();
					mapParentSgw.put("id", "2");
					mapParentSgw.put("pId", "0");
					mapParentSgw.put("pIds", "0,2");
					mapParentSgw.put("name", "SGW");
					mapList.add(mapParentSgw);
				}
				mapSgw.put("id", e.getId());
				mapSgw.put("pId", "2");
				mapSgw.put("pIds", "0,2");
				mapSgw.put("name", e.getName());
				mapList.add(mapSgw);
			}
		}
		return mapList;
	}

	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(@Valid @ModelAttribute("tMultipleIndex") TMultipleIndex tMultipleIndex,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		String tempArrId[] = tMultipleIndex.getNetid().split(",");
		String[] netId = new String[tempArrId.length / 2];
		System.arraycopy(tempArrId, 0, netId, 0, tempArrId.length / 2);

		String tempArr[] = tMultipleIndex.getFormulaType().split(",");
		String[] formulaType = new String[tempArr.length / 2];
		System.arraycopy(tempArr, 0, formulaType, 0, tempArr.length / 2);
		String result = "";
		if (null != tMultipleIndex && null != tMultipleIndex.getNetType() && !"".equals(tMultipleIndex.getNetType())) {
			result = DictUtils.getDictLabel(tMultipleIndex.getNetType(), "biz_net_type", result);
		}
		List<TStatisticsVO> list = tMultipleIndexService.queryList(tMultipleIndex.getStartTime(),
				tMultipleIndex.getEndTime(), result, netId, formulaType, 0, 0, null, null);
		List<String> headerList = Lists.newArrayList();
		headerList.add("网元名称");
		headerList.add("KPI公式");
		headerList.add("时间");
		headerList.add("当天值");
		headerList.add("前一天值");
		headerList.add("前一周值");
		headerList.add("前一周平均值");

		ExportExcel ee = new ExportExcel("性能指标综合查询", headerList);

		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
			TStatisticsVO tstatisticsVO = list.get(i);
			ee.addCell(row, 0, tstatisticsVO.getFname());
			ee.addCell(row, 1, tstatisticsVO.getName());
			ee.addCell(row, 2, tstatisticsVO.getTemp_field1());
			ee.addCell(row, 3, tstatisticsVO.getResult().toString());
			ee.addCell(row, 4, tstatisticsVO.getDresult().toString());
			ee.addCell(row, 5, tstatisticsVO.getWresult().toString());
			ee.addCell(row, 6, tstatisticsVO.getAwresult().toString());
		}

		try {
			ee.write(response, "性能指标综合查询.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ee.dispose();
		}
	}
}