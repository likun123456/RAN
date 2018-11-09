package com.thinkgem.jeesite.modules.cheat.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.thinkgem.jeesite.modules.cheat.entity.CheatTypeAssess;
import com.thinkgem.jeesite.modules.cheat.entity.TCheatChinese;
import com.thinkgem.jeesite.modules.cheat.service.CheatTypeAssessService;
import com.thinkgem.jeesite.modules.cheat.service.TCheatChineseService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;

/**
 * 欺诈类型评估表Controller
 * @author wangjingshi
 * @version 2017-08-31
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatTypeAssess")
public class CheatTypeAssessController extends BaseController{
	
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	@Autowired
	private CheatTypeAssessService cheatTypeAssessService;
	@Autowired
	private TCheatChineseService tCheatChineseService;
	
	@RequiresPermissions("cheat:cheatTypeAssess:view")
	@RequestMapping(value = {"show", ""})
	public String show(@Valid @ModelAttribute("cheatTypeAssess")CheatTypeAssess cheatTypeAssess,Model model) {
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
		return "modules/cheat/cheatTypeAssess";
	}
	
	@RequestMapping(value = "getDynamicColumns")
	@ResponseBody
	public String getDynamicColumns(String netid) {
		List<TCheatChinese> cheatChineseList = null;
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			cheatChineseList = tCheatChineseService.findList(new TCheatChinese());
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(cheatChineseList);
	}
	
	@RequestMapping(value = "queryList")
	@ResponseBody
	public String queryList(String startTime,String endTime,String netid,String timeGranularity,int page,int rows){
		int first = rows * (page - 1);
		ServerPagination<LinkedHashMap<String, String>> serverPage = new ServerPagination<LinkedHashMap<String, String>>();
		List<TCheatChinese> cheatChineseList = null;
		List<CheatTypeAssess> list = null;
		List<LinkedHashMap<String, String>> combinationlist = new ArrayList<LinkedHashMap<String, String>>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			cheatChineseList = tCheatChineseService.findList(new TCheatChinese());
			list = cheatTypeAssessService.queryList(netid,startTime,endTime,timeGranularity);
			combinationlist = cheatTypeAssessService.combineList(list,cheatChineseList,timeGranularity);
			int count = combinationlist.size();
			serverPage.setTotal(combinationlist.size());
			int endIndex = first+rows;
			if(endIndex>count){
				serverPage.setRows(combinationlist.subList(first, count));
			}else{
				serverPage.setRows(combinationlist.subList(first, endIndex));
			}
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(serverPage);
	}
	
	@RequestMapping(value = "showDetailInfo")
	public String showDetailInfo(String recordtime,String netid,String proxyip,String timeGranularity,Model model){
		String timeX = "";
		String timeD = recordtime.substring(0, 10).replaceAll("-", "");
		//proxyip = (proxyip == null)||("".equals(proxyip))?null:"'"+proxyip+"'";
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netid);
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			if(recordtime.length()>10){
				timeX = recordtime.substring(11, 13).replaceAll("0", "");
			}else if(recordtime.length()==10){
				timeX = recordtime.substring(0, 10).replaceAll("-", "");
			}else{
				timeX = recordtime.substring(0, 7).replaceAll("-", "");
			}
			int _timeX = timeX.length();
			List<CheatTypeAssess> list = cheatTypeAssessService.queryCheatUsersList(recordtime,netid,timeGranularity,proxyip,timeD,timeX,_timeX);
			model.addAttribute("list", list);
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return "modules/cheat/cheatTypeAssessDetailInfo";
	}
	
	@RequestMapping(value = {"exportExcel"})
	public void exportExcel(@Valid @ModelAttribute("cheatTypeAssess")CheatTypeAssess cheatTypeAssess,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		    List<CheatTypeAssess> list = null;
		    List<TCheatChinese> cheatChineseList = null;
			List<LinkedHashMap<String, String>> combinationlist = new ArrayList<LinkedHashMap<String, String>>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(cheatTypeAssess.getNetid());
			DynamicDataSource.setCurrentLookupKey(ip+"-core_data_sub");
			cheatChineseList = tCheatChineseService.findList(new TCheatChinese());
			list = cheatTypeAssessService.queryList(cheatTypeAssess.getNetid(),cheatTypeAssess.getStartTime(),
					cheatTypeAssess.getEndTime(),cheatTypeAssess.getTimeGranularity());
			combinationlist = cheatTypeAssessService.combineList(list,cheatChineseList,cheatTypeAssess.getTimeGranularity());
		} catch (Exception e) {
	        logger.error(e.getMessage(),e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		   int len = cheatChineseList.size();
		   String headStr[] = new String[2+len];
		   headStr[0]="时间";
		   headStr[1]="代理服务器";
		   for(int i=0;i<len;i++){
			   headStr[2+i] = cheatChineseList.get(i).getCheatCaseChinese();
		   }
		   ExportExcel ee = new ExportExcel("欺诈类型评估表", headStr);
		   for (int i = 0; i < combinationlist.size(); i++) {
				Row row = ee.addRow();
				LinkedHashMap<String, String> map = combinationlist.get(i);
					ee.addCell(row, 0,map.get("recordtime"));
					ee.addCell(row, 1,map.get("proxyip"));
					for(int j=0;j<len;j++){
						ee.addCell(row, 2+j,map.get(cheatChineseList.get(j).getCheatCaseChinese()));
					}
			}
		   try {
				ee.write(response, "欺诈类型评估表.xlsx");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				ee.dispose();
			}
	}

}
