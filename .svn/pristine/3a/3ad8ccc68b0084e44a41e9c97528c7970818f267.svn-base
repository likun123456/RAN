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
import com.thinkgem.jeesite.modules.cheat.entity.CheatFlowPerentChart;
import com.thinkgem.jeesite.modules.cheat.entity.CheatType;
import com.thinkgem.jeesite.modules.cheat.service.CheatFlowPerentService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;
import com.thinkgem.jeesite.modules.performance.entity.TTacSuccessRate;

/**
 * 计费欺诈流量占比评估表
 * @author yanghai
 * @version 2017-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatFlowPerent")
public class CheatFlowPerentController extends BaseController {
	@Autowired
	private CheatFlowPerentService cheatFlowPerentService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;

	@ModelAttribute
	public CheatFlowPerent get(@RequestParam(required = false) String id) {
		CheatFlowPerent entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cheatFlowPerentService.get(id);
		}
		if (entity == null) {
			entity = new CheatFlowPerent();
		}
		return entity;
	}

	@RequiresPermissions("cheat:cheatFlowPerent:view")
	@RequestMapping(value = { "query" })
	public String query(@Valid @ModelAttribute("tCheatFlowPerent") CheatFlowPerent cheatFlowPerent, HttpServletRequest request,
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
		return "modules/cheat/cheatFlowPerent";
	}

	@RequiresPermissions("cheat:cheatFlowPerent:view")
	@RequestMapping(value = { "queryList" })
	@ResponseBody
	public String queryList(String startTime, String endTime, String netId, String netName, String gran, int limit,
			int offset, String sortName, String sortOrder) {
		ServerPagination<CheatFlowPerent> page = new ServerPagination<CheatFlowPerent>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			int total = 0;
			List<CheatFlowPerent> list = new ArrayList<CheatFlowPerent>();
			if (gran.equals("hh")) {
				list = cheatFlowPerentService.querypercentTableList(startTime, endTime, netId, netName, gran, limit, offset,
						sortName, sortOrder);
				total = cheatFlowPerentService.getpercentTotal(netId, startTime, endTime, netName, gran);
			} else if (gran.equals("dd")) {
				list = cheatFlowPerentService.querypercentTableListDay(startTime, endTime, netId, netName, gran, limit,
						offset, sortName, sortOrder);
				total = cheatFlowPerentService.getpercentTotalDay(netId, startTime, endTime, netName, gran);
			} else {
				list = cheatFlowPerentService.querypercentTableListMonth(startTime, endTime, netId, netName, gran, limit,
						offset, sortName, sortOrder);
				total = cheatFlowPerentService.getpercentTotalMonth(netId, startTime, endTime, netName, gran);
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
	public Map<String,CheatFlowPerentChart> findChart(String netId, String startTime, String endTime, String gran,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String,CheatFlowPerentChart> resultMap=new HashMap<String,CheatFlowPerentChart>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			if (gran.equals("hh")) {
				resultMap= cheatFlowPerentService.queryCdrSubpercentMap(netId, startTime, endTime, gran);
			} else if (gran.equals("dd")) {
				resultMap= cheatFlowPerentService.queryCdrSubpercentMapDay(netId, startTime, endTime, gran);
			} else {
				resultMap= cheatFlowPerentService.queryCdrSubpercentMapMonth(netId, startTime, endTime, gran);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return resultMap;
	}
	
	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(@Valid @ModelAttribute("tCheatFlowPerent") CheatFlowPerent cheatFlowPerent,
			HttpServletRequest request, HttpServletResponse response) {
		List<CheatFlowPerent> list = new ArrayList<CheatFlowPerent>();
		String netName=tNewnetelementService.get(cheatFlowPerent.getNetId()).getFname();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(cheatFlowPerent.getNetId());
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			if (cheatFlowPerent.getGran().equals("hh")) {
				list = cheatFlowPerentService.querypercentTableList(cheatFlowPerent.getTemp_field2(), cheatFlowPerent.getTemp_field3(), cheatFlowPerent.getNetId(), netName, cheatFlowPerent.getGran(), 0, 0,
						null, null);
			} else if (cheatFlowPerent.getGran().equals("dd")) {
				list = cheatFlowPerentService.querypercentTableListDay(cheatFlowPerent.getTemp_field2(), cheatFlowPerent.getTemp_field3(), cheatFlowPerent.getNetId(), netName, cheatFlowPerent.getGran(), 0,
						0, null, null);
			} else {
				list = cheatFlowPerentService.querypercentTableListMonth(cheatFlowPerent.getTemp_field2(), cheatFlowPerent.getTemp_field3(), cheatFlowPerent.getNetId(), netName, cheatFlowPerent.getGran(), 0,
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
		headerList.add("总流量(KB)");
		headerList.add("占比(%)");
		headerList.add("欺诈类型");

		ExportExcel ee = new ExportExcel("欺诈流量占比", headerList);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
				CheatFlowPerent cheatFlowPerentTemp =list.get(i);
				ee.addCell(row, 0,cheatFlowPerentTemp.getRecordtime());
				ee.addCell(row, 1,cheatFlowPerentTemp.getNetName());
				ee.addCell(row, 2,cheatFlowPerentTemp.getFreetotal());
				ee.addCell(row, 3,cheatFlowPerentTemp.getTotal());
				ee.addCell(row, 4,cheatFlowPerentTemp.getPercent());
				ee.addCell(row, 5,cheatFlowPerentTemp.getCheatCase());
		}
		
		try {
			ee.write(response, "欺诈流量占比.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ee.dispose();
		}
	}
	
}
