package com.thinkgem.jeesite.modules.cheat.web;

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
import com.thinkgem.jeesite.modules.cheat.entity.CheatTopUser;
import com.thinkgem.jeesite.modules.cheat.service.CheatTopUserService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;

/**
 * 计费欺诈top欺诈用户评估表
 * 
 * @author yanghai
 * @version 2017-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatTopUser")
public class CheatTopUserController extends BaseController {
	@Autowired
	private CheatTopUserService cheatTopUserService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;

	@ModelAttribute
	public CheatTopUser get(@RequestParam(required = false) String id) {
		CheatTopUser entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cheatTopUserService.get(id);
		}
		if (entity == null) {
			entity = new CheatTopUser();
		}
		return entity;
	}

	@RequiresPermissions("cheat:cheatTopUser:view")
	@RequestMapping(value = { "query" })
	public String query(@Valid @ModelAttribute("cheatTopUser") CheatTopUser cheatTopUser, HttpServletRequest request,
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
		return "modules/cheat/cheatTopUserList";
	}

	@RequestMapping(value = "queryList")
	@ResponseBody
	public String queryList(String startTime, String endTime, String netId, String top, int limit, int offset,
			String sortName, String sortOrder, String netName) {
		ServerPagination<CheatTopUser> page = new ServerPagination<CheatTopUser>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			List<CheatTopUser> list = cheatTopUserService.queryList(startTime, endTime, netId, top, offset, limit,
					sortName, sortOrder, netName);
			int total = cheatTopUserService.queryListCount(startTime, endTime, netId, top);
			DynamicDataSource.setCurrentLookupKey("dataSource");
			if (Integer.parseInt(top) < total) {
				page.setTotal(Integer.parseInt(top));
			}else{
				page.setTotal(total);
			}
			page.setRows(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(page);
	}
	
	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(@Valid @ModelAttribute("cheatTopUser") CheatTopUser cheatTopUser,
			HttpServletRequest request, HttpServletResponse response) {
		List<CheatTopUser> list = new ArrayList<CheatTopUser>();
		String netName=tNewnetelementService.get(cheatTopUser.getNetId()).getFname();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(cheatTopUser.getNetId());
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
				list = cheatTopUserService.queryList(cheatTopUser.getTemp_field2(), cheatTopUser.getTemp_field3(), cheatTopUser.getNetId(), cheatTopUser.getTop(), 0, Integer.parseInt(cheatTopUser.getTop()),
						null, null,netName);
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		List<String> headerList = Lists.newArrayList();
		headerList.add("网元名称");
		headerList.add("用户IMSI");
		headerList.add("免费流量(KB)");
		headerList.add("总流量(KB)");
		headerList.add("免费占比(%)");

		ExportExcel ee = new ExportExcel("欺诈用户", headerList);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
			CheatTopUser cheatTopUserTemp =list.get(i);
			ee.addCell(row, 0, cheatTopUserTemp.getNetName());
			ee.addCell(row, 1, cheatTopUserTemp.getServedIMSI());
			ee.addCell(row, 2, cheatTopUserTemp.getFreetotal());
			ee.addCell(row, 3, cheatTopUserTemp.getTotal());
			ee.addCell(row, 4, cheatTopUserTemp.getPercent());
		}
		
		try {
			ee.write(response, "欺诈用户.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ee.dispose();
		}
	}
}
