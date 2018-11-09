package com.thinkgem.jeesite.modules.performance.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.utils.SortList;
import com.thinkgem.jeesite.common.utils.TimeUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpi;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpiParam;
import com.thinkgem.jeesite.modules.performance.entity.TIndexMain;
import com.thinkgem.jeesite.modules.performance.service.TIndexKpiParamService;
import com.thinkgem.jeesite.modules.performance.service.TIndexKpiService;
import com.thinkgem.jeesite.modules.performance.service.TIndexMainService;

/**
 * 首页Controller
 * 
 * @author 杨海
 * @version 2017-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/index")
@Scope("prototype")
public class TIndexMainControlle extends BaseController {
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

	@RequiresPermissions("performance:index:view")
	@RequestMapping(value = "mainIndex")
	public String mainIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 查询池组集合
		List<TPool> tPoolList = tPoolService.findList(new TPool());
		//为前台页面按照顺序显示做错里-start
		SortList<TPool> sortList = new SortList<TPool>(); 
		Map<String, String> orderMap=new HashMap<String,String>();
		orderMap.put("MME", "1");
		orderMap.put("SAEGW", "2");
		orderMap.put("PCRF", "3");
//		orderMap.put("MGW", "4");
//		orderMap.put("HLRFE", "5");
//		orderMap.put("HSS", "6");
//		orderMap.put("CUDB", "7");
//		orderMap.put("PG", "8");
		for(int tempOrder=0;tempOrder<tPoolList.size();tempOrder++){
			tPoolList.get(tempOrder).setOrder(orderMap.get(tPoolList.get(tempOrder).getFtype()));
		}
		sortList.Sort(tPoolList, "getOrder", "asc");
		//为前台页面按照顺序显示做错里-end		
		List<TIndexKpi> tIndexKpiList = tIndexKpiService.findList(new TIndexKpi());
		String datetime = "2018-04-25 13:45:00";//TimeUtils.getYesterMinute(tIndexMainService.getDateTime("mme"), 15);
		Map<String, TIndexKpiParam> kpiParamMap=tIndexKpiParamService.getKpiParamMap();
		Map<String, List<TIndexMain>> mainData = new LinkedHashMap<String, List<TIndexMain>>();
		List<TIndexMain> listMain;
		TIndexMain tIndexMain;
		TIndexKpiParam tikp;
		for (int m = 0; m < tPoolList.size(); m++) {// 查询池组对应的KPI值
			tIndexMain = new TIndexMain();
			tIndexMain.setTpoolId(tPoolList.get(m).getId());
			tIndexMain.setTpoolName(tPoolList.get(m).getFpoolname());
			tIndexMain.setTpoolType(tPoolList.get(m).getFtype());
			tIndexMain.setDateTime(datetime);
			tIndexMain.setTimeInterval(tIndexKpiList.get(0).getStarttime());
			switch (tPoolList.get(m).getFtype()) {
			case "MME":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMmekpione() + "," + tIndexKpiList.get(0).getMmekpitwo()
						+ "," + tIndexKpiList.get(0).getMmekpithree() + "," + tIndexKpiList.get(0).getMmekpifour() + ","
						+ tIndexKpiList.get(0).getMmekpifive() + "," + tIndexKpiList.get(0).getMmekpisix());
				break;
			case "SAEGW":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getSaegwkpione() + ","
						+ tIndexKpiList.get(0).getSaegwkpitwo() + "," + tIndexKpiList.get(0).getSaegwkpithree() + ","
						+ tIndexKpiList.get(0).getSaegwkpifour() + "," + tIndexKpiList.get(0).getSaegwkpifive() + ","
						+ tIndexKpiList.get(0).getSaegwkpisix());
				break;
			case "PCRF":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPcrfkpione() + "," + tIndexKpiList.get(0).getPcrfkpitwo()
						+ "," + tIndexKpiList.get(0).getPcrfkpithree() + "," + tIndexKpiList.get(0).getPcrfkpifour()
						+ "," + tIndexKpiList.get(0).getPcrfkpifive() + "," + tIndexKpiList.get(0).getPcrfkpisix());
				break;
			case "MSC":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMsckpione() + "," + tIndexKpiList.get(0).getMsckpitwo()
						+ "," + tIndexKpiList.get(0).getMsckpithree() + "," + tIndexKpiList.get(0).getMsckpifour()
						+ "," + tIndexKpiList.get(0).getMsckpifive() + "," + tIndexKpiList.get(0).getMsckpisix());
				break;
			case "MGW":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMgwkpione() + "," + tIndexKpiList.get(0).getMgwkpitwo()
						+ "," + tIndexKpiList.get(0).getMgwkpithree() + "," + tIndexKpiList.get(0).getMgwkpifour()
						+ "," + tIndexKpiList.get(0).getMgwkpifive() + "," + tIndexKpiList.get(0).getMgwkpisix());
				break;
			case "HLRFE":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getHlrfekpione() + "," + tIndexKpiList.get(0).getHlrfekpitwo()
						+ "," + tIndexKpiList.get(0).getHlrfekpithree() + "," + tIndexKpiList.get(0).getHlrfekpifour()
						+ "," + tIndexKpiList.get(0).getHlrfekpifive() + "," + tIndexKpiList.get(0).getHlrfekpisix());
				break;
			case "HSS":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getHsskpione() + "," + tIndexKpiList.get(0).getHsskpitwo()
						+ "," + tIndexKpiList.get(0).getHsskpithree() + "," + tIndexKpiList.get(0).getHsskpifour()
						+ "," + tIndexKpiList.get(0).getHsskpifive() + "," + tIndexKpiList.get(0).getHsskpisix());
				break;
			case "CUDB":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getCudbkpione() + "," + tIndexKpiList.get(0).getCudbkpitwo()
						+ "," + tIndexKpiList.get(0).getCudbkpithree() + "," + tIndexKpiList.get(0).getCudbkpifour()
						+ "," + tIndexKpiList.get(0).getCudbkpifive() + "," + tIndexKpiList.get(0).getCudbkpisix());
				break;
			case "PG":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPgkpione() + "," + tIndexKpiList.get(0).getPgkpitwo()
						+ "," + tIndexKpiList.get(0).getPgkpithree() + "," + tIndexKpiList.get(0).getPgkpifour()
						+ "," + tIndexKpiList.get(0).getPgkpifive() + "," + tIndexKpiList.get(0).getPgkpisix());
				break;
			}

			listMain = tIndexMainService.queryList(tIndexMain,tPoolList.get(m).getFtype());
			for (int n = 0; n < listMain.size(); n++) {// 计算每个池组中KPI值所处的区间（优，良，中，差，预警）
				tikp = new TIndexKpiParam();
				tikp.setKpi(listMain.get(n).getKpi());
				tikp = kpiParamMap.get(listMain.get(n).getKpi());
				if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getExcellentDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double
								.parseDouble(tikp.getExcellentUp())) {// 优
					listMain.get(n).setIconName("icon-blue-5");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getGoodDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getGoodUp())) {// 良
					listMain.get(n).setIconName("icon-green-4");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getWellDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getWellUp())) {// 中
					listMain.get(n).setIconName("icon-yellow-3");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getBadDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getBadUp())) {// 差
					listMain.get(n).setIconName("icon-orange-2");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getAlarmDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getAlarmUp())) {
					listMain.get(n).setIconName("icon-red-1");
				}
			}
			mainData.put(tPoolList.get(m).getFpoolname(), listMain);
		}
		model.addAttribute("mainData", mainData);
		model.addAttribute("datetime", datetime.substring(0, 19));
		return "modules/performance/mainIndex";
	}

	@RequiresPermissions("performance:index:view")
	@RequestMapping(value = "mainChart")
	public String mainChart(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 查询池组集合
		List<TPool> tPoolList = tPoolService.findList(new TPool());
		List<TIndexKpi> tIndexKpiList = tIndexKpiService.findList(new TIndexKpi());
		String datetime = "2018-04-25 13:45:00";//TimeUtils.getYesterMinute(tIndexMainService.getDateTime("mme"), 15);
		Map<String, Map<String, List<TIndexMain>>> mainData = new LinkedHashMap<String, Map<String, List<TIndexMain>>>();
		Map<String, List<TIndexMain>> listMain;
		TIndexMain tIndexMain = null;
		for (int m = 0; m < tPoolList.size(); m++) {// 查询池组对应的KPI值
			tIndexMain = new TIndexMain();
			tIndexMain.setTpoolId(tPoolList.get(m).getId());
			tIndexMain.setTpoolName(tPoolList.get(m).getFpoolname());
			tIndexMain.setTpoolType(tPoolList.get(m).getFtype());
			tIndexMain.setDateTime(datetime);
			tIndexMain.setTimeInterval(tIndexKpiList.get(0).getStarttime());
			switch (tPoolList.get(m).getFtype()) {
			case "MME":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMmekpione() + "," + tIndexKpiList.get(0).getMmekpitwo()
						+ "," + tIndexKpiList.get(0).getMmekpithree() + "," + tIndexKpiList.get(0).getMmekpifour() + ","
						+ tIndexKpiList.get(0).getMmekpifive() + "," + tIndexKpiList.get(0).getMmekpisix());
				break;
			case "SAEGW":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getSaegwkpione() + ","
						+ tIndexKpiList.get(0).getSaegwkpitwo() + "," + tIndexKpiList.get(0).getSaegwkpithree() + ","
						+ tIndexKpiList.get(0).getSaegwkpifour() + "," + tIndexKpiList.get(0).getSaegwkpifive() + ","
						+ tIndexKpiList.get(0).getSaegwkpisix());
				break;
			case "PCRF":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPcrfkpione() + "," + tIndexKpiList.get(0).getPcrfkpitwo()
						+ "," + tIndexKpiList.get(0).getPcrfkpithree() + "," + tIndexKpiList.get(0).getPcrfkpifour()
						+ "," + tIndexKpiList.get(0).getPcrfkpifive() + "," + tIndexKpiList.get(0).getPcrfkpisix());
				break;
			case "MSC":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMsckpione() + "," + tIndexKpiList.get(0).getMsckpitwo()
						+ "," + tIndexKpiList.get(0).getMsckpithree() + "," + tIndexKpiList.get(0).getMsckpifour()
						+ "," + tIndexKpiList.get(0).getMsckpifive() + "," + tIndexKpiList.get(0).getMsckpisix());
				break;
			case "MGW":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMgwkpione() + "," + tIndexKpiList.get(0).getMgwkpitwo()
						+ "," + tIndexKpiList.get(0).getMgwkpithree() + "," + tIndexKpiList.get(0).getMgwkpifour()
						+ "," + tIndexKpiList.get(0).getMgwkpifive() + "," + tIndexKpiList.get(0).getMgwkpisix());
				break;
			case "HLRFE":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getHlrfekpione() + "," + tIndexKpiList.get(0).getHlrfekpitwo()
						+ "," + tIndexKpiList.get(0).getHlrfekpithree() + "," + tIndexKpiList.get(0).getHlrfekpifour()
						+ "," + tIndexKpiList.get(0).getHlrfekpifive() + "," + tIndexKpiList.get(0).getHlrfekpisix());
				break;
			case "HSS":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getHsskpione() + "," + tIndexKpiList.get(0).getHsskpitwo()
						+ "," + tIndexKpiList.get(0).getHsskpithree() + "," + tIndexKpiList.get(0).getHsskpifour()
						+ "," + tIndexKpiList.get(0).getHsskpifive() + "," + tIndexKpiList.get(0).getHsskpisix());
				break;
			case "CUDB":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getCudbkpione() + "," + tIndexKpiList.get(0).getCudbkpitwo()
						+ "," + tIndexKpiList.get(0).getCudbkpithree() + "," + tIndexKpiList.get(0).getCudbkpifour()
						+ "," + tIndexKpiList.get(0).getCudbkpifive() + "," + tIndexKpiList.get(0).getCudbkpisix());
				break;
			case "PG":
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPgkpione() + "," + tIndexKpiList.get(0).getPgkpitwo()
						+ "," + tIndexKpiList.get(0).getPgkpithree() + "," + tIndexKpiList.get(0).getPgkpifour()
						+ "," + tIndexKpiList.get(0).getPgkpifive() + "," + tIndexKpiList.get(0).getPgkpisix());
				break;
			}
			
			listMain = tIndexMainService.queryChartList(tIndexMain,tPoolList.get(m).getFtype());
			mainData.put(tPoolList.get(m).getFpoolname(), listMain);
		}
		if (null != tIndexMain) {
			model.addAttribute("timeInterval",
					TimeUtils.getYesterHour(datetime, Integer.parseInt(tIndexMain.getTimeInterval())) + "-"
							+ datetime.substring(0, 19));
		}
		model.addAttribute("mainChartData", mainData);
		model.addAttribute("datetime", datetime);
		return "modules/performance/mainChart";
	}

	@RequestMapping(value = "netelementIndex")
	public String netelementIndex(@RequestParam("poolName") String poolName, @RequestParam("datetime") String datetime,
			HttpServletRequest request, Model model) {
		List<TIndexKpi> tIndexKpiList = tIndexKpiService.findList(new TIndexKpi());
		TPool tPool = new TPool();
		tPool.setFpoolname(poolName);
		List<TPool> poolList = tPoolService.findList(tPool);
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setFnid(Long.parseLong(poolList.get(0).getId()));
		List<TNewnetelement> tNewnetelementList = tNewnetelementService.findList(tNewnetelement);

		Map<String, List<TIndexMain>> mainData = new LinkedHashMap<String, List<TIndexMain>>();
		List<TIndexMain> listMain;
		TIndexMain tIndexMain;
		TIndexKpiParam tikp;
		for (int m = 0; m < tNewnetelementList.size(); m++) {// 查询池组对应的KPI值
			tIndexMain = new TIndexMain();
			tIndexMain.setNetId(tNewnetelementList.get(m).getId());
			tIndexMain.setNetName(tNewnetelementList.get(m).getFname());
			tIndexMain.setTpoolType(tNewnetelementList.get(m).getType());
			tIndexMain.setDateTime(datetime);
			tIndexMain.setTimeInterval(tIndexKpiList.get(0).getStarttime());
			String tableName = "MME";
			switch (tNewnetelementList.get(m).getType()) {
			case "1":
				tableName="MME";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMmekpione() + "," + tIndexKpiList.get(0).getMmekpitwo()
						+ "," + tIndexKpiList.get(0).getMmekpithree() + "," + tIndexKpiList.get(0).getMmekpifour() + ","
						+ tIndexKpiList.get(0).getMmekpifive() + "," + tIndexKpiList.get(0).getMmekpisix());
				break;
			case "2":
				tableName="SAEGW";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getSaegwkpione() + ","
						+ tIndexKpiList.get(0).getSaegwkpitwo() + "," + tIndexKpiList.get(0).getSaegwkpithree() + ","
						+ tIndexKpiList.get(0).getSaegwkpifour() + "," + tIndexKpiList.get(0).getSaegwkpifive() + ","
						+ tIndexKpiList.get(0).getSaegwkpisix());
				break;
			case "3":
				tableName="PCRF";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPcrfkpione() + "," + tIndexKpiList.get(0).getPcrfkpitwo()
						+ "," + tIndexKpiList.get(0).getPcrfkpithree() + "," + tIndexKpiList.get(0).getPcrfkpifour()
						+ "," + tIndexKpiList.get(0).getPcrfkpifive() + "," + tIndexKpiList.get(0).getPcrfkpisix());
				break;
			case "4":
				tableName="MSC";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMsckpione() + "," + tIndexKpiList.get(0).getMsckpitwo()
						+ "," + tIndexKpiList.get(0).getMsckpithree() + "," + tIndexKpiList.get(0).getMsckpifour()
						+ "," + tIndexKpiList.get(0).getMsckpifive() + "," + tIndexKpiList.get(0).getMsckpisix());
				break;
			case "5":
				tableName="MGW";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMgwkpione() + "," + tIndexKpiList.get(0).getMgwkpitwo()
						+ "," + tIndexKpiList.get(0).getMgwkpithree() + "," + tIndexKpiList.get(0).getMgwkpifour()
						+ "," + tIndexKpiList.get(0).getMgwkpifive() + "," + tIndexKpiList.get(0).getMgwkpisix());
				break;
			case "6":
				tableName="HLRFE";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getHlrfekpione() + "," + tIndexKpiList.get(0).getHlrfekpitwo()
						+ "," + tIndexKpiList.get(0).getHlrfekpithree() + "," + tIndexKpiList.get(0).getHlrfekpifour()
						+ "," + tIndexKpiList.get(0).getHlrfekpifive() + "," + tIndexKpiList.get(0).getHlrfekpisix());
				break;
			case "7":
				tableName="HSS";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getHsskpione() + "," + tIndexKpiList.get(0).getHsskpitwo()
						+ "," + tIndexKpiList.get(0).getHsskpithree() + "," + tIndexKpiList.get(0).getHsskpifour()
						+ "," + tIndexKpiList.get(0).getHsskpifive() + "," + tIndexKpiList.get(0).getHsskpisix());
				break;
			case "8":
				tableName="CUDB";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getCudbkpione() + "," + tIndexKpiList.get(0).getCudbkpitwo()
						+ "," + tIndexKpiList.get(0).getCudbkpithree() + "," + tIndexKpiList.get(0).getCudbkpifour()
						+ "," + tIndexKpiList.get(0).getCudbkpifive() + "," + tIndexKpiList.get(0).getCudbkpisix());
				break;
			case "9":
				tableName="PG";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPgkpione() + "," + tIndexKpiList.get(0).getPgkpitwo()
						+ "," + tIndexKpiList.get(0).getPgkpithree() + "," + tIndexKpiList.get(0).getPgkpifour()
						+ "," + tIndexKpiList.get(0).getPgkpifive() + "," + tIndexKpiList.get(0).getPgkpisix());
				break;
			}
			listMain = tIndexMainService.queryNetList(tIndexMain,tableName);
			for (int n = 0; n < listMain.size(); n++) {// 计算每个池组中KPI值所处的区间（优，良，中，差，预警）
				tikp = new TIndexKpiParam();
				tikp.setKpi(listMain.get(n).getKpi());
				tikp = tIndexKpiParamService.get(tikp);
				if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getExcellentDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double
								.parseDouble(tikp.getExcellentUp())) {// 优
					listMain.get(n).setIconName("icon-blue-5");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getGoodDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getGoodUp())) {// 良
					listMain.get(n).setIconName("icon-green-4");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getWellDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getWellUp())) {// 中
					listMain.get(n).setIconName("icon-yellow-3");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getBadDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getBadUp())) {// 差
					listMain.get(n).setIconName("icon-orange-2");
				} else if (Double.parseDouble(listMain.get(n).getResult()) >= Double.parseDouble(tikp.getAlarmDown())
						&& Double.parseDouble(listMain.get(n).getResult()) < Double.parseDouble(tikp.getAlarmUp())) {
					listMain.get(n).setIconName("icon-red-1");
				}
			}
			mainData.put(tNewnetelementList.get(m).getFname(), listMain);
		}
		model.addAttribute("mainData", mainData);
		model.addAttribute("datetime", datetime);
		model.addAttribute("poolName", poolName);
		return "modules/performance/mainNetelementIndex";
	}

	@RequestMapping(value = "netelementChart")
	public String netelementChart(@RequestParam("poolName") String poolName, @RequestParam("datetime") String datetime,
			HttpServletRequest request, Model model) {
		// 查询池组集合
		TPool tPool = new TPool();
		tPool.setFpoolname(poolName);
		List<TPool> poolList = tPoolService.findList(tPool);
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setFnid(Long.parseLong(poolList.get(0).getId()));
		List<TNewnetelement> tNewnetelementList = tNewnetelementService.findList(tNewnetelement);
		List<TIndexKpi> tIndexKpiList = tIndexKpiService.findList(new TIndexKpi());
		Map<String, Map<String, List<TIndexMain>>> mainData = new LinkedHashMap<String, Map<String, List<TIndexMain>>>();
		Map<String, List<TIndexMain>> listMain;
		TIndexMain tIndexMain = null;
		for (int m = 0; m < tNewnetelementList.size(); m++) {// 查询池组对应的KPI值
			tIndexMain = new TIndexMain();
			tIndexMain.setNetId(tNewnetelementList.get(m).getId());
			tIndexMain.setNetName(tNewnetelementList.get(m).getFname());
			tIndexMain.setTpoolType(tNewnetelementList.get(m).getType());
			tIndexMain.setDateTime(datetime);
			tIndexMain.setTimeInterval(tIndexKpiList.get(0).getStarttime());
			String tableName = "MME";
			switch (tNewnetelementList.get(m).getType()) {
			case "1":
				tableName="MME";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMmekpione() + "," + tIndexKpiList.get(0).getMmekpitwo()
						+ "," + tIndexKpiList.get(0).getMmekpithree() + "," + tIndexKpiList.get(0).getMmekpifour() + ","
						+ tIndexKpiList.get(0).getMmekpifive() + "," + tIndexKpiList.get(0).getMmekpisix());
				break;
			case "2":
				tableName="SAEGW";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getSaegwkpione() + ","
						+ tIndexKpiList.get(0).getSaegwkpitwo() + "," + tIndexKpiList.get(0).getSaegwkpithree() + ","
						+ tIndexKpiList.get(0).getSaegwkpifour() + "," + tIndexKpiList.get(0).getSaegwkpifive() + ","
						+ tIndexKpiList.get(0).getSaegwkpisix());
				break;
			case "3":
				tableName="PCRF";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPcrfkpione() + "," + tIndexKpiList.get(0).getPcrfkpitwo()
						+ "," + tIndexKpiList.get(0).getPcrfkpithree() + "," + tIndexKpiList.get(0).getPcrfkpifour()
						+ "," + tIndexKpiList.get(0).getPcrfkpifive() + "," + tIndexKpiList.get(0).getPcrfkpisix());
				break;
			case "4":
				tableName="MSC";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMsckpione() + "," + tIndexKpiList.get(0).getMsckpitwo()
						+ "," + tIndexKpiList.get(0).getMsckpithree() + "," + tIndexKpiList.get(0).getMsckpifour()
						+ "," + tIndexKpiList.get(0).getMsckpifive() + "," + tIndexKpiList.get(0).getMsckpisix());
				break;
			case "5":
				tableName="MGW";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getMgwkpione() + "," + tIndexKpiList.get(0).getMgwkpitwo()
						+ "," + tIndexKpiList.get(0).getMgwkpithree() + "," + tIndexKpiList.get(0).getMgwkpifour()
						+ "," + tIndexKpiList.get(0).getMgwkpifive() + "," + tIndexKpiList.get(0).getMgwkpisix());
				break;
			case "6":
				tableName="HLRFE";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getHlrfekpione() + "," + tIndexKpiList.get(0).getHlrfekpitwo()
						+ "," + tIndexKpiList.get(0).getHlrfekpithree() + "," + tIndexKpiList.get(0).getHlrfekpifour()
						+ "," + tIndexKpiList.get(0).getHlrfekpifive() + "," + tIndexKpiList.get(0).getHlrfekpisix());
				break;
			case "7":
				tableName="HSS";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getHsskpione() + "," + tIndexKpiList.get(0).getHsskpitwo()
						+ "," + tIndexKpiList.get(0).getHsskpithree() + "," + tIndexKpiList.get(0).getHsskpifour()
						+ "," + tIndexKpiList.get(0).getHsskpifive() + "," + tIndexKpiList.get(0).getHsskpisix());
				break;
			case "8":
				tableName="CUDB";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getCudbkpione() + "," + tIndexKpiList.get(0).getCudbkpitwo()
						+ "," + tIndexKpiList.get(0).getCudbkpithree() + "," + tIndexKpiList.get(0).getCudbkpifour()
						+ "," + tIndexKpiList.get(0).getCudbkpifive() + "," + tIndexKpiList.get(0).getCudbkpisix());
				break;
			case "9":
				tableName="PG";
				tIndexMain.setKpiList(tIndexKpiList.get(0).getPgkpione() + "," + tIndexKpiList.get(0).getPgkpitwo()
						+ "," + tIndexKpiList.get(0).getPgkpithree() + "," + tIndexKpiList.get(0).getPgkpifour()
						+ "," + tIndexKpiList.get(0).getPgkpifive() + "," + tIndexKpiList.get(0).getPgkpisix());
				break;
			}
			listMain = tIndexMainService.queryNetChartList(tIndexMain,tableName);
			mainData.put(tNewnetelementList.get(m).getFname(), listMain);
		}
		if (null != tIndexMain) {
			model.addAttribute("timeInterval",
					TimeUtils.getYesterHour(datetime, Integer.parseInt(tIndexMain.getTimeInterval())) + "-"
							+ datetime.substring(0, 19));
		}
		model.addAttribute("mainChartData", mainData);
		model.addAttribute("datetime", datetime);
		model.addAttribute("poolName", poolName);
		return "modules/performance/mainNetelementChart";
	}
}