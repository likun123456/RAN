package com.thinkgem.jeesite.modules.cheat.web;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.CheatFreeBusiness;
import com.thinkgem.jeesite.modules.cheat.entity.CheatFreeBusinessChart;
import com.thinkgem.jeesite.modules.cheat.entity.TFreeRatingGroup;
import com.thinkgem.jeesite.modules.cheat.service.CheatFreeBusinessService;
import com.thinkgem.jeesite.modules.cheat.service.TFreeRatingGroupService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;

/**
 * 计费欺诈流量占比评估表
 * 
 * @author yanghai
 * @version 2017-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatFreeBusiness")
public class CheatFreeBusinessController extends BaseController {
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TFreeRatingGroupService freeRatingGroupService;
	@Autowired
	private CheatFreeBusinessService cheatFreeBusinessService;
	@Autowired
	private TCollectordeployService tCollectordeployService;

	@ModelAttribute
	public CheatFreeBusiness get(@RequestParam(required = false) String id) {
		CheatFreeBusiness entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cheatFreeBusinessService.get(id);
		}
		if (entity == null) {
			entity = new CheatFreeBusiness();
		}
		return entity;
	}
	
	@RequiresPermissions("cheat:cheatFreeBusiness:view")
	@RequestMapping(value = { "query" })
	public String query(@Valid @ModelAttribute("tCheatFreeBusiness") CheatFreeBusiness tCheatFreeBusiness, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		TNewnetelement tnet = new TNewnetelement();
		tnet.setType("6");
		List<TNewnetelement> netListAll = tNewnetelementService.findListByTypeAndCollect(tnet);
		List<TPool> poolList = tPoolService.queryPoolListByType(null);
		List<PoolNetVO> poolNetList = new ArrayList<PoolNetVO>();
		for (TPool pool : poolList) {
			PoolNetVO poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for (TNewnetelement n : netListAll) {
				if (pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			if (netList.size() != 0) {
				poolNetVO.setNetList(netList);
				poolNetVO.setPoolName(pool.getFpoolname());
				poolNetList.add(poolNetVO);
			}
		}

		model.addAttribute("poolNetList", poolNetList);

		return "modules/cheat/cheatFreeBusinessList";
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
	@RequiresPermissions("cheat:cheatFreeBusiness:view")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, @RequestParam(required = false) String parameterEpc,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		// 业务类型
		List<TFreeRatingGroup> list = freeRatingGroupService.findList(new TFreeRatingGroup());
		for (int i = 0; i < list.size(); i++) {
			TFreeRatingGroup e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			if (i == 0) {
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "0");
				mapParent.put("pId", "0");
				mapParent.put("pIds", "0");
				mapParent.put("name", "免费业务类型");
				mapList.add(mapParent);
			}
			map.put("id", e.getRatingGroup());
			map.put("pId", "0");
			map.put("pIds", "0,1");
			map.put("name", e.getName());
			mapList.add(map);
		}
		return mapList;
	}
	
	@RequiresPermissions("cheat:cheatFreeBusiness:view")
	@RequestMapping(value = { "queryList" })
	@ResponseBody
	public String queryList(String startTime, String endTime, String netId, String netName, String gran, int limit,
			int offset, String sortName, String sortOrder,String ratingGroup) {
		ServerPagination<CheatFreeBusiness> page = new ServerPagination<CheatFreeBusiness>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			int total = 0;
			List<CheatFreeBusiness> list = new ArrayList<CheatFreeBusiness>();
			if (gran.equals("hh")) {
				list = cheatFreeBusinessService.querypercentTableList(startTime, endTime, netId, netName, gran, limit, offset,
						sortName, sortOrder,ratingGroup);
				total = cheatFreeBusinessService.getpercentTotal(netId, startTime, endTime, netName, gran,ratingGroup);
			} else if (gran.equals("dd")) {
				list = cheatFreeBusinessService.querypercentTableListDay(startTime, endTime, netId, netName, gran, limit,
						offset, sortName, sortOrder,ratingGroup);
				total = cheatFreeBusinessService.getpercentTotalDay(netId, startTime, endTime, netName, gran,ratingGroup);
			} else {
				list = cheatFreeBusinessService.querypercentTableListMonth(startTime, endTime, netId, netName, gran, limit,
						offset, sortName, sortOrder,ratingGroup);
				total = cheatFreeBusinessService.getpercentTotalMonth(netId, startTime, endTime, netName, gran,ratingGroup);
			}
			DynamicDataSource.setCurrentLookupKey("dataSource");
			page.setTotal(total);
			page.setRows(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(page);
	}

	@RequestMapping(value = { "findChart" })
	@ResponseBody
	public Map<String,CheatFreeBusinessChart> findChart(String netId, String startTime, String endTime, String gran,String ratingGroup,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String,CheatFreeBusinessChart> resultMap=new HashMap<String,CheatFreeBusinessChart>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			if (gran.equals("hh")) {
				resultMap= cheatFreeBusinessService.queryCdrSubpercentMap(netId, startTime, endTime, gran,ratingGroup);
			} else if (gran.equals("dd")) {
				resultMap= cheatFreeBusinessService.queryCdrSubpercentMapDay(netId, startTime, endTime, gran,ratingGroup);
			} else {
				resultMap= cheatFreeBusinessService.queryCdrSubpercentMapMonth(netId, startTime, endTime, gran,ratingGroup);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return resultMap;
	}
	
	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(@Valid @ModelAttribute("tcheatFreeBusiness") CheatFreeBusiness cheatFreeBusiness,
			HttpServletRequest request, HttpServletResponse response) {
		List<CheatFreeBusiness> list = new ArrayList<CheatFreeBusiness>();
		String netName=tNewnetelementService.get(cheatFreeBusiness.getNetId()).getFname();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(cheatFreeBusiness.getNetId());
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			String []tempStr=cheatFreeBusiness.getRatingGroup().split(",");
			StringBuilder sb=new StringBuilder();
			for(int m=tempStr.length/2;m>0;m--){
				sb.append(tempStr[m-1]);
				if(m!=1){
					sb.append(",");
				}
			}
			if (cheatFreeBusiness.getGran().equals("hh")) {
				list = cheatFreeBusinessService.querypercentTableList(cheatFreeBusiness.getTemp_field2(), cheatFreeBusiness.getTemp_field3(), cheatFreeBusiness.getNetId(), netName, cheatFreeBusiness.getGran(), 0, 0,
						null, null,sb.toString());
			} else if (cheatFreeBusiness.getGran().equals("dd")) {
				list = cheatFreeBusinessService.querypercentTableListDay(cheatFreeBusiness.getTemp_field2(), cheatFreeBusiness.getTemp_field3(), cheatFreeBusiness.getNetId(), netName, cheatFreeBusiness.getGran(), 0,
						0, null, null,sb.toString());
			} else {
				list = cheatFreeBusinessService.querypercentTableListMonth(cheatFreeBusiness.getTemp_field2(), cheatFreeBusiness.getTemp_field3(), cheatFreeBusiness.getNetId(), netName, cheatFreeBusiness.getGran(), 0,
						0, null, null,sb.toString());
			}
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		List<String> headerList = Lists.newArrayList();
		headerList.add("时间");
		headerList.add("业务名称");
		headerList.add("上行流量(KB)");
		headerList.add("下行流量(KB)");
		headerList.add("总流量(KB)");

		ExportExcel ee = new ExportExcel("免费业务欺诈流量", headerList);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
			CheatFreeBusiness cheatFreeBusinessTemp =list.get(i);
				ee.addCell(row, 0,cheatFreeBusinessTemp.getRecordtime());
				ee.addCell(row, 1,cheatFreeBusinessTemp.getRatingGroupName());
				ee.addCell(row, 2,cheatFreeBusinessTemp.getDataUp());
				ee.addCell(row, 3,cheatFreeBusinessTemp.getDataDown());
				ee.addCell(row, 4,cheatFreeBusinessTemp.getTotal());
		}
		
		try {
			ee.write(response, "免费业务欺诈流量.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ee.dispose();
		}
	}
}
