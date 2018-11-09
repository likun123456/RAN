package com.thinkgem.jeesite.modules.cheat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.TCheatChineseDao;
import com.thinkgem.jeesite.modules.cheat.dao.TCheatTypeDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatType;
import com.thinkgem.jeesite.modules.cheat.entity.CheatTypeChart;
import com.thinkgem.jeesite.modules.cheat.entity.TCheatChinese;

/**
 * 欺诈包下载
 * 
 * @author 杨海
 */
@Service
@Transactional(readOnly = true)
public class CheatTypeService extends CrudService<TCheatTypeDao, CheatType> {
	@Autowired
	private TCheatTypeDao tCheatTypeDao;
	@Autowired
	private TCheatChineseDao tCheatChineseDao;

	public CheatType get(String id) {
		return super.get(id);
	}

	public List<CheatType> querypercentTableList(String startTime, String endTime, String netId, String netName,
			String gran, int limit, int offset, String sortName, String sortOrder) {
		return tCheatTypeDao.getTableList(startTime, endTime, netId, netName, gran, limit, offset, sortName, sortOrder);
	}

	public int getpercentTotal(String netId, String startTime, String endTime, String netName, String gran) {
		return tCheatTypeDao.getTableCount(startTime, endTime, netId, netName, gran);
	}

	public List<CheatType> querypercentTableListDay(String startTime, String endTime, String netId, String netName,
			String gran, int limit, int offset, String sortName, String sortOrder) {
		return tCheatTypeDao.getTableListDay(startTime, endTime, netId, netName, gran, limit, offset, sortName,
				sortOrder);
	}

	public int getpercentTotalDay(String netId, String startTime, String endTime, String netName, String gran) {
		return tCheatTypeDao.getTableCountDay(startTime, endTime, netId, netName, gran);
	}

	public List<CheatType> querypercentTableListMonth(String startTime, String endTime, String netId, String netName,
			String gran, int limit, int offset, String sortName, String sortOrder) {
		return tCheatTypeDao.getTableListMonth(startTime, endTime, netId, netName, gran, limit, offset, sortName,
				sortOrder);
	}

	public int getpercentTotalMonth(String netId, String startTime, String endTime, String netName, String gran) {
		return tCheatTypeDao.getTableCountMonth(startTime, endTime, netId, netName, gran);
	}

	public Map<String, CheatTypeChart> queryCdrSubpercentMap(String netId, String startTime, String endTime,
			String gran) {
		List<String> typeList = new ArrayList<String>();
		List<String> typeTempList = new ArrayList<String>();
		List<TCheatChinese> cheatTypeList = new ArrayList<TCheatChinese>();
		List<CheatType> tempList;
		typeTempList = tCheatTypeDao.getTypeList(netId, startTime, endTime, gran);
		for (int i = 0; i < typeTempList.size(); i++) {
			String temp = typeTempList.get(i);
			typeList.add(temp.substring(temp.length() - 15, temp.length() - 5));
		}
		cheatTypeList = tCheatChineseDao.findList(new TCheatChinese());
		Map<String, CheatTypeChart> perLstMap = new HashMap<String, CheatTypeChart>();
		CheatTypeChart cheatTypeChart;

		List<CheatType> tempListAll = tCheatTypeDao.getTableList(startTime, endTime, netId, null, gran, 0, 0, null,
				null);

		for (TCheatChinese tCheatChinese : cheatTypeList) {
			tempList = new ArrayList<CheatType>();
			for (String timeStr : typeTempList) {
				CheatType cheatType=null;
				for (int tem = 0; tem < tempListAll.size(); tem++) {
					CheatType cheatTypeTemp= tempListAll.get(tem);
					if (cheatTypeTemp.getCheatCase().equals(tCheatChinese.getCheatCaseChinese()) && cheatTypeTemp.getRecordtime().equals(timeStr)) {
						cheatType=cheatTypeTemp;
					}
				}
				if (null == cheatType || cheatType.getFreetotal().equals("0")) {
					cheatType = new CheatType();
					cheatType.setFreetotal("0");
				}
				tempList.add(cheatType);
			}
			cheatTypeChart = new CheatTypeChart();
			cheatTypeChart.setListCheatType(tempList);
			cheatTypeChart.setListTime(typeList);
			perLstMap.put(tCheatChinese.getCheatCaseChinese(), cheatTypeChart);
		}
		return perLstMap;
	}

	public Map<String, CheatTypeChart> queryCdrSubpercentMapDay(String netId, String startTime, String endTime,
			String gran) {
		List<String> typeList = new ArrayList<String>();
		List<String> typeTempList = new ArrayList<String>();
		List<TCheatChinese> cheatTypeList = new ArrayList<TCheatChinese>();
		List<CheatType> tempList;
		typeTempList = tCheatTypeDao.getTypeListDay(netId, startTime, endTime, gran);
		for (int i = 0; i < typeTempList.size(); i++) {
			String temp = typeTempList.get(i);
			typeList.add(temp.substring(temp.length() - 4, temp.length() - 0));
		}
		cheatTypeList = tCheatChineseDao.findList(new TCheatChinese());
		Map<String, CheatTypeChart> perLstMap = new HashMap<String, CheatTypeChart>();
		CheatTypeChart cheatTypeChart;

		List<CheatType> tempListAll = tCheatTypeDao.getTableListDay(startTime, endTime, netId, null, gran, 0, 0, null,
				null);

		for (TCheatChinese tCheatChinese : cheatTypeList) {
			tempList = new ArrayList<CheatType>();
			for (String timeStr : typeTempList) {
				CheatType cheatType=null;
				for (int tem = 0; tem < tempListAll.size(); tem++) {
					CheatType cheatTypeTemp= tempListAll.get(tem);
					if (cheatTypeTemp.getCheatCase().equals(tCheatChinese.getCheatCaseChinese()) && cheatTypeTemp.getRecordtime().equals(timeStr)) {
						cheatType=cheatTypeTemp;
					}
				}
				if (null == cheatType || cheatType.getFreetotal().equals("0")) {
					cheatType = new CheatType();
					cheatType.setFreetotal("0");
				}
				tempList.add(cheatType);
			}
			cheatTypeChart = new CheatTypeChart();
			cheatTypeChart.setListCheatType(tempList);
			cheatTypeChart.setListTime(typeList);
			perLstMap.put(tCheatChinese.getCheatCaseChinese(), cheatTypeChart);
		}
		return perLstMap;
	}

	public Map<String, CheatTypeChart> queryCdrSubpercentMapMonth(String netId, String startTime, String endTime,
			String gran) {
		List<String> typeTempList = new ArrayList<String>();
		List<TCheatChinese> cheatTypeList = new ArrayList<TCheatChinese>();
		List<CheatType> tempList;
		typeTempList = tCheatTypeDao.getTypeListMonth(netId, startTime, endTime, gran);
		cheatTypeList = tCheatChineseDao.findList(new TCheatChinese());
		Map<String, CheatTypeChart> perLstMap = new HashMap<String, CheatTypeChart>();
		CheatTypeChart cheatTypeChart;

		List<CheatType> tempListAll = tCheatTypeDao.getTableListMonth(startTime, endTime, netId, null, gran, 0, 0, null,
				null);

		for (TCheatChinese tCheatChinese : cheatTypeList) {
			tempList = new ArrayList<CheatType>();
			for (String timeStr : typeTempList) {
				CheatType cheatType=null;
				for (int tem = 0; tem < tempListAll.size(); tem++) {
					CheatType cheatTypeTemp= tempListAll.get(tem);
					if (cheatTypeTemp.getCheatCase().equals(tCheatChinese.getCheatCaseChinese()) && cheatTypeTemp.getRecordtime().equals(timeStr)) {
						cheatType=cheatTypeTemp;
					}
				}
				if (null == cheatType || cheatType.getFreetotal().equals("0")) {
					cheatType = new CheatType();
					cheatType.setFreetotal("0");
				}
				tempList.add(cheatType);
			}
			cheatTypeChart = new CheatTypeChart();
			cheatTypeChart.setListCheatType(tempList);
			cheatTypeChart.setListTime(typeTempList);
			perLstMap.put(tCheatChinese.getCheatCaseChinese(), cheatTypeChart);
		}
		return perLstMap;
	}
}