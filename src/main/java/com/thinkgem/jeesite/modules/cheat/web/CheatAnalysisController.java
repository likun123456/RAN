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
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.CheatAnalysis;
import com.thinkgem.jeesite.modules.cheat.entity.CheatRatingGroup;
import com.thinkgem.jeesite.modules.cheat.service.CheatAnalysisService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;
/**
 * 计费欺诈防控分析Controller
 * @author wangjingshi
 * @version 2017-08-28
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatAnalysis")
public class CheatAnalysisController extends BaseController{
	
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	@Autowired
	private CheatAnalysisService cheatAnalysisService;
	
	@RequiresPermissions("cheat:cheatAnalysis:view")
	@RequestMapping(value = {"show", ""})
	public String show(@Valid @ModelAttribute("cheatAnalysis")CheatAnalysis cheatAnalysis,Model model) {
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setType("2");//type设置为1代表MME类型网元
		tNewnetelement.setTemp_field1("6");//6代表采集类型为防欺诈的业务类型
		List<TPool> poolList = tPoolService.queryPoolListByType("SAEGW");
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
		model.addAttribute("poolNetList", poolNetList);
		return "modules/cheat/cheatAnalysis";
	}
	
	
	@RequestMapping(value = "queryList")
	@ResponseBody
	public String queryList(String startTime,String endTime,String netid,int freePrecentThreshold,int freeThreshold,int limit, int offset){
		ServerPagination<CheatAnalysis> page = new ServerPagination<CheatAnalysis>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			List<CheatAnalysis> list = cheatAnalysisService.queryList(date.replaceAll("-", "_"),startTime,endTime,netid,
					freePrecentThreshold,freeThreshold,offset,limit);
			int total = cheatAnalysisService.queryListCount(date.replaceAll("-", "_"),startTime,endTime,netid,freePrecentThreshold,freeThreshold);
			page.setTotal(total);
			page.setRows(list);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(page);
	}
	
	@RequestMapping(value = "showDetailInfo")
	public String showDetailInfo(String startTime,String endTime,String netid,String imsi,Model model){
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			List<CheatRatingGroup> list = cheatAnalysisService.queryCheatRatingGroupList(date.replaceAll("-", "_"),startTime,endTime,netid,imsi);
			model.addAttribute("list", list);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return "modules/cheat/cheatAnalysisDetailInfo";
	}
	
	@RequestMapping(value = "showPieChart")
	public String showPieChart(String startTime,String endTime,String netid,String imsi,Model model,String freeTotal,String charge){
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			String date = startTime.substring(0, 10);
			endTime = date + " " + endTime;
			List<CheatRatingGroup> list = cheatAnalysisService.queryCheatRatingGroupList(date.replaceAll("-", "_"),startTime,endTime,netid,imsi);
			model.addAttribute("list", list);
			model.addAttribute("imsi", imsi);
			model.addAttribute("freeTotal", freeTotal);
			model.addAttribute("charge", charge);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return "modules/cheat/cheatAnalysisPie";
	}
	
	@RequestMapping(value = {"exportExcel"})
	public void exportExcel(@Valid @ModelAttribute("cheatAnalysis")CheatAnalysis cheatAnalysis,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		    List<CheatAnalysis> list = null;
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(cheatAnalysis.getNetid());
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			String date = cheatAnalysis.getStartTime().substring(0, 10);
			String endTime = date + " " + cheatAnalysis.getEndTime();
			list = cheatAnalysisService.queryList(date.replaceAll("-", "_"),cheatAnalysis.getStartTime(),endTime,
					cheatAnalysis.getNetid(),cheatAnalysis.getFreePrecentThreshold(),cheatAnalysis.getFreeThreshold(),0,0);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		   String headStr[] = {"用户IMSI","RAT类型","免费流量（KB）","总流量（KB）","免费流量占比"};
		   ExportExcel ee = new ExportExcel("计费欺诈防控分析报表", headStr);
		   java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		   for (int i = 0; i < list.size(); i++) {
				Row row = ee.addRow();
				   CheatAnalysis c = list.get(i);
					ee.addCell(row, 0,c.getImsi());
					ee.addCell(row, 1,c.getRatType());
					ee.addCell(row, 2,df.format((double)c.getFreeTotal()/1024));
					ee.addCell(row, 3,df.format((double)c.getTotal()/1024));
					ee.addCell(row, 4,df.format(c.getPercent()*100)+"%");
			}
		   try {
				ee.write(response, "计费欺诈防控分析.xlsx");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				ee.dispose();
			}
	}

}
