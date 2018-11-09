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
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.CheatFlowPerent;
import com.thinkgem.jeesite.modules.cheat.entity.CheatType;
import com.thinkgem.jeesite.modules.cheat.entity.CheatTypeChart;
import com.thinkgem.jeesite.modules.cheat.service.CheatTypeService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;

/**
 * 计费欺诈分类流量评估
 * 
 * @author yanghai
 * @version 2017-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatType")
public class CheatTypeController extends BaseController {
	@Autowired
	private CheatTypeService cheatTypeService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;

	@ModelAttribute
	public CheatType get(@RequestParam(required = false) String id) {
		CheatType entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cheatTypeService.get(id);
		}
		if (entity == null) {
			entity = new CheatType();
		}
		return entity;
	}

	@RequiresPermissions("cheat:cheatType:view")
	@RequestMapping(value = { "query" })
	public String query(@Valid @ModelAttribute("tCheatType") CheatType tCheatType, HttpServletRequest request,
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
		return "modules/cheat/cheatTypeList";
	}

	@RequiresPermissions("cheat:cheatType:view")
	@RequestMapping(value = { "queryList" })
	@ResponseBody
	public String queryList(String startTime, String endTime, String netId, String netName, String gran, int limit,
			int offset, String sortName, String sortOrder) {
		ServerPagination<CheatType> page = new ServerPagination<CheatType>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			int total = 0;
			List<CheatType> list = new ArrayList<CheatType>();
			if (gran.equals("hh")) {
				list = cheatTypeService.querypercentTableList(startTime, endTime, netId, netName, gran, limit, offset,
						sortName, sortOrder);
				total = cheatTypeService.getpercentTotal(netId, startTime, endTime, netName, gran);
			} else if (gran.equals("dd")) {
				list = cheatTypeService.querypercentTableListDay(startTime, endTime, netId, netName, gran, limit,
						offset, sortName, sortOrder);
				total = cheatTypeService.getpercentTotalDay(netId, startTime, endTime, netName, gran);
			} else {
				list = cheatTypeService.querypercentTableListMonth(startTime, endTime, netId, netName, gran, limit,
						offset, sortName, sortOrder);
				total = cheatTypeService.getpercentTotalMonth(netId, startTime, endTime, netName, gran);
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
	public Map<String,CheatTypeChart> findChart(String netId, String startTime, String endTime, String gran,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String,CheatTypeChart> resultMap=new HashMap<String,CheatTypeChart>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			if (gran.equals("hh")) {
				resultMap= cheatTypeService.queryCdrSubpercentMap(netId, startTime, endTime, gran);
			} else if (gran.equals("dd")) {
				resultMap= cheatTypeService.queryCdrSubpercentMapDay(netId, startTime, endTime, gran);
			} else {
				resultMap= cheatTypeService.queryCdrSubpercentMapMonth(netId, startTime, endTime, gran);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return resultMap;
	}
	
	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(@Valid @ModelAttribute("tCheatType") CheatType tCheatType,
			HttpServletRequest request, HttpServletResponse response) {
		List<CheatType> list = new ArrayList<CheatType>();
		String netName=tNewnetelementService.get(tCheatType.getNetId()).getFname();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(tCheatType.getNetId());
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			if (tCheatType.getGran().equals("hh")) {
				list = cheatTypeService.querypercentTableList(tCheatType.getTemp_field2(), tCheatType.getTemp_field3(), tCheatType.getNetId(), netName, tCheatType.getGran(), 0, 0,
						null, null);
			} else if (tCheatType.getGran().equals("dd")) {
				list = cheatTypeService.querypercentTableListDay(tCheatType.getTemp_field2(), tCheatType.getTemp_field3(), tCheatType.getNetId(), netName, tCheatType.getGran(), 0,
						0, null, null);
			} else {
				list = cheatTypeService.querypercentTableListMonth(tCheatType.getTemp_field2(), tCheatType.getTemp_field3(), tCheatType.getNetId(), netName, tCheatType.getGran(), 0,
						0, null, null);
			}
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		List<String> headerList = Lists.newArrayList();
		headerList.add("时间");
		headerList.add("网元名称");
		headerList.add("免费流量(KB)");
		headerList.add("欺诈类型");

		ExportExcel ee = new ExportExcel("分类流量评估", headerList);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
			CheatType cheatTypeTemp =list.get(i);
				ee.addCell(row, 0,cheatTypeTemp.getRecordtime());
				ee.addCell(row, 1,cheatTypeTemp.getNetName());
				ee.addCell(row, 2,cheatTypeTemp.getFreetotal());
				ee.addCell(row, 3,cheatTypeTemp.getCheatCase());
		}
		
		try {
			ee.write(response, "分类流量评估.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ee.dispose();
		}
	}
}
