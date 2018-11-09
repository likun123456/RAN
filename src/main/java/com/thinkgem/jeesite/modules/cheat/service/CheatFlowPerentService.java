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
import com.thinkgem.jeesite.modules.cheat.dao.TCheatFlowPerentDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatFlowPerent;
import com.thinkgem.jeesite.modules.cheat.entity.CheatFlowPerentChart;
import com.thinkgem.jeesite.modules.cheat.entity.TCheatChinese;

/**
 * 欺诈包下载
 * 
 * @author 杨海
 */
@Service
@Transactional(readOnly = true)
public class CheatFlowPerentService extends CrudService<TCheatFlowPerentDao, CheatFlowPerent> {
	@Autowired
	private TCheatFlowPerentDao tCheatFlowPerentDao;
	@Autowired
	private TCheatChineseDao tCheatChineseDao;
	java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");

	public CheatFlowPerent get(String id) {
		return super.get(id);
	}

	public List<CheatFlowPerent> querypercentTableList(String startTime, String endTime, String netId, String netName,
			String gran, int limit, int offset, String sortName, String sortOrder) {

		// 总流量
		List<CheatFlowPerent> listCheatFlowTotal = tCheatFlowPerentDao.getTotalFlow(startTime, endTime, netId);
		Map<String, String> totalMap = new HashMap<String, String>();
		for (CheatFlowPerent cheatFlowPerent : listCheatFlowTotal) {
			totalMap.put(cheatFlowPerent.getRecordtime(), cheatFlowPerent.getTotal());
		}
		List<CheatFlowPerent> listCheatFlow = tCheatFlowPerentDao.getTableList(startTime, endTime, netId, netName, gran,
				limit, offset, sortName, sortOrder);
		for (CheatFlowPerent cheatFlowPerent : listCheatFlow) {
			cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
			try {
				cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal()) * 100
						/ Double.parseDouble(cheatFlowPerent.getTotal())) + "%");// 欺诈流量占总流量百分比
			} catch (Exception e) {
				cheatFlowPerent.setTemp_field3(totalMap.get(cheatFlowPerent.getRecordtime()));
				cheatFlowPerent.setTemp_field2("0");
			}
		}
		return listCheatFlow;
	}

	public int getpercentTotal(String netId, String startTime, String endTime, String netName, String gran) {
		return tCheatFlowPerentDao.getTableCount(startTime, endTime, netId, netName, gran);
	}

	public List<CheatFlowPerent> querypercentTableListDay(String startTime, String endTime, String netId,
			String netName, String gran, int limit, int offset, String sortName, String sortOrder) {
		// 总流量
		List<CheatFlowPerent> listCheatFlowTotal = tCheatFlowPerentDao.getTotalFlowDay(startTime, endTime, netId);
		Map<String, String> totalMap = new HashMap<String, String>();
		for (CheatFlowPerent cheatFlowPerent : listCheatFlowTotal) {
			totalMap.put(cheatFlowPerent.getRecordtime(), cheatFlowPerent.getTotal());
		}
		List<CheatFlowPerent> listCheatFlow = tCheatFlowPerentDao.getTableListDay(startTime, endTime, netId, netName,
				gran, limit, offset, sortName, sortOrder);
		for (CheatFlowPerent cheatFlowPerent : listCheatFlow) {
			cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
			try {
				cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal()) * 100
						/ Double.parseDouble(cheatFlowPerent.getTotal())) + "%");// 欺诈流量占总流量百分比
			} catch (Exception e) {
				cheatFlowPerent.setTemp_field3(totalMap.get(cheatFlowPerent.getRecordtime()));
				cheatFlowPerent.setTemp_field2("0");
			}
		}
		return listCheatFlow;
	}

	public int getpercentTotalDay(String netId, String startTime, String endTime, String netName, String gran) {
		return tCheatFlowPerentDao.getTableCountDay(startTime, endTime, netId, netName, gran);
	}

	public List<CheatFlowPerent> querypercentTableListMonth(String startTime, String endTime, String netId,
			String netName, String gran, int limit, int offset, String sortName, String sortOrder) {
		// 总流量
		List<CheatFlowPerent> listCheatFlowTotal = tCheatFlowPerentDao.getTotalFlowMonth(startTime, endTime, netId);
		Map<String, String> totalMap = new HashMap<String, String>();
		for (CheatFlowPerent cheatFlowPerent : listCheatFlowTotal) {
			totalMap.put(cheatFlowPerent.getRecordtime(), cheatFlowPerent.getTotal());
		}
		List<CheatFlowPerent> listCheatFlow = tCheatFlowPerentDao.getTableListMonth(startTime, endTime, netId, netName,
				gran, limit, offset, sortName, sortOrder);
		for (CheatFlowPerent cheatFlowPerent : listCheatFlow) {
			cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
			try {
				cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal()) * 100
						/ Double.parseDouble(cheatFlowPerent.getTotal())) + "%");// 欺诈流量占总流量百分比
			} catch (Exception e) {
				cheatFlowPerent.setTemp_field3(totalMap.get(cheatFlowPerent.getRecordtime()));
				cheatFlowPerent.setTemp_field2("0");
			}
		}
		return listCheatFlow;
	}

	public int getpercentTotalMonth(String netId, String startTime, String endTime, String netName, String gran) {
		return tCheatFlowPerentDao.getTableCountMonth(startTime, endTime, netId, netName, gran);
	}

	public Map<String, CheatFlowPerentChart> queryCdrSubpercentMap(String netId, String startTime, String endTime,
			String gran) {
		// 总流量
		List<CheatFlowPerent> listCheatFlowTotal = tCheatFlowPerentDao.getTotalFlow(startTime, endTime, netId);
		Map<String, String> totalMap = new HashMap<String, String>();
		for (CheatFlowPerent cheatFlowPerent : listCheatFlowTotal) {
			totalMap.put(cheatFlowPerent.getRecordtime(), cheatFlowPerent.getTotal());
		}

		Map<String, CheatFlowPerentChart> perLstMap = new HashMap<String, CheatFlowPerentChart>();

		List<String> typeTempList = new ArrayList<String>();
		List<TCheatChinese> cheatTypeList = new ArrayList<TCheatChinese>();
		List<CheatFlowPerent> tempList;
		typeTempList = tCheatFlowPerentDao.getTypeList(netId, startTime, endTime, gran);
		cheatTypeList = tCheatChineseDao.findList(new TCheatChinese());
		CheatFlowPerentChart cheatFlowPerentChart;

		List<CheatFlowPerent> tempListAll = tCheatFlowPerentDao.getTableList(startTime, endTime, netId, null, gran, 0,
				0, null, null);
		for (TCheatChinese tCheatChinese : cheatTypeList) {
			tempList = new ArrayList<CheatFlowPerent>();
			for (String timeStr : typeTempList) {
				CheatFlowPerent cheatFlowPerent = null;
				for (int tem = 0; tem < tempListAll.size(); tem++) {
					CheatFlowPerent cheatTypeTemp = tempListAll.get(tem);
					if (cheatTypeTemp.getCheatCase().equals(tCheatChinese.getCheatCaseChinese())
							&& cheatTypeTemp.getRecordtime().equals(timeStr)) {
						cheatFlowPerent = cheatTypeTemp;
						cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
						try {
							cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal())
									* 100 / Double.parseDouble(cheatFlowPerent.getTotal())));// 欺诈流量占总流量百分比
						} catch (Exception e) {
							cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
							cheatFlowPerent.setPercent("0");
						}
					}
				}
				if (null == cheatFlowPerent || cheatFlowPerent.getFreetotal().equals("0")) {
					cheatFlowPerent = new CheatFlowPerent();
					cheatFlowPerent.setFreetotal("0");
					cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
					try {
						cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal()) * 100
								/ Double.parseDouble(cheatFlowPerent.getTotal())));// 欺诈流量占总流量百分比
					} catch (Exception e) {
						cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
						cheatFlowPerent.setPercent("0");
					}
				}
				tempList.add(cheatFlowPerent);
			}
			cheatFlowPerentChart = new CheatFlowPerentChart();
			cheatFlowPerentChart.setListCheatFlowPerent(tempList);
			cheatFlowPerentChart.setListTime(typeTempList);
			perLstMap.put(tCheatChinese.getCheatCaseChinese(), cheatFlowPerentChart);
		}
		return perLstMap;
	}

	public Map<String, CheatFlowPerentChart> queryCdrSubpercentMapDay(String netId, String startTime, String endTime,
			String gran) {
		// 总流量
				List<CheatFlowPerent> listCheatFlowTotal = tCheatFlowPerentDao.getTotalFlowDay(startTime, endTime, netId);
				Map<String, String> totalMap = new HashMap<String, String>();
				for (CheatFlowPerent cheatFlowPerent : listCheatFlowTotal) {
					totalMap.put(cheatFlowPerent.getRecordtime(), cheatFlowPerent.getTotal());
				}

				Map<String, CheatFlowPerentChart> perLstMap = new HashMap<String, CheatFlowPerentChart>();

				List<String> typeList = new ArrayList<String>();
				List<String> typeTempList = new ArrayList<String>();
				List<TCheatChinese> cheatTypeList = new ArrayList<TCheatChinese>();
				List<CheatFlowPerent> tempList;
				typeTempList = tCheatFlowPerentDao.getTypeListDay(netId, startTime, endTime, gran);
				for (int i = 0; i < typeTempList.size(); i++) {
					String temp = typeTempList.get(i);
					typeList.add(temp.substring(temp.length() - 4, temp.length() - 0));
				}
				cheatTypeList = tCheatChineseDao.findList(new TCheatChinese());
				CheatFlowPerentChart cheatFlowPerentChart;

				List<CheatFlowPerent> tempListAll = tCheatFlowPerentDao.getTableListDay(startTime, endTime, netId, null, gran, 0,
						0, null, null);
				for (TCheatChinese tCheatChinese : cheatTypeList) {
					tempList = new ArrayList<CheatFlowPerent>();
					for (String timeStr : typeTempList) {
						CheatFlowPerent cheatFlowPerent = null;
						for (int tem = 0; tem < tempListAll.size(); tem++) {
							CheatFlowPerent cheatTypeTemp = tempListAll.get(tem);
							if (cheatTypeTemp.getCheatCase().equals(tCheatChinese.getCheatCaseChinese())
									&& cheatTypeTemp.getRecordtime().equals(timeStr)) {
								cheatFlowPerent = cheatTypeTemp;
								cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
								try {
									cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal())
											* 100 / Double.parseDouble(cheatFlowPerent.getTotal())));// 欺诈流量占总流量百分比
								} catch (Exception e) {
									cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
									cheatFlowPerent.setPercent("0");
								}
							}
						}
						if (null == cheatFlowPerent || cheatFlowPerent.getFreetotal().equals("0")) {
							cheatFlowPerent = new CheatFlowPerent();
							cheatFlowPerent.setFreetotal("0");
							cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
							try {
								cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal()) * 100
										/ Double.parseDouble(cheatFlowPerent.getTotal())));// 欺诈流量占总流量百分比
							} catch (Exception e) {
								cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
								cheatFlowPerent.setPercent("0");
							}
						}
						tempList.add(cheatFlowPerent);
					}
					cheatFlowPerentChart = new CheatFlowPerentChart();
					cheatFlowPerentChart.setListCheatFlowPerent(tempList);
					cheatFlowPerentChart.setListTime(typeList);
					perLstMap.put(tCheatChinese.getCheatCaseChinese(), cheatFlowPerentChart);
				}
				return perLstMap;

	}

	public Map<String, CheatFlowPerentChart> queryCdrSubpercentMapMonth(String netId, String startTime, String endTime,
			String gran) {

		// 总流量
				List<CheatFlowPerent> listCheatFlowTotal = tCheatFlowPerentDao.getTotalFlowMonth(startTime, endTime, netId);
				Map<String, String> totalMap = new HashMap<String, String>();
				for (CheatFlowPerent cheatFlowPerent : listCheatFlowTotal) {
					totalMap.put(cheatFlowPerent.getRecordtime(), cheatFlowPerent.getTotal());
				}

				Map<String, CheatFlowPerentChart> perLstMap = new HashMap<String, CheatFlowPerentChart>();

				List<String> typeTempList = new ArrayList<String>();
				List<TCheatChinese> cheatTypeList = new ArrayList<TCheatChinese>();
				List<CheatFlowPerent> tempList;
				typeTempList = tCheatFlowPerentDao.getTypeListMonth(netId, startTime, endTime, gran);

				cheatTypeList = tCheatChineseDao.findList(new TCheatChinese());
				CheatFlowPerentChart cheatFlowPerentChart;

				List<CheatFlowPerent> tempListAll = tCheatFlowPerentDao.getTableListMonth(startTime, endTime, netId, null, gran, 0,
						0, null, null);
				for (TCheatChinese tCheatChinese : cheatTypeList) {
					tempList = new ArrayList<CheatFlowPerent>();
					for (String timeStr : typeTempList) {
						CheatFlowPerent cheatFlowPerent = null;
						for (int tem = 0; tem < tempListAll.size(); tem++) {
							CheatFlowPerent cheatTypeTemp = tempListAll.get(tem);
							if (cheatTypeTemp.getCheatCase().equals(tCheatChinese.getCheatCaseChinese())
									&& cheatTypeTemp.getRecordtime().equals(timeStr)) {
								cheatFlowPerent = cheatTypeTemp;
								cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
								try {
									cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal())
											* 100 / Double.parseDouble(cheatFlowPerent.getTotal())));// 欺诈流量占总流量百分比
								} catch (Exception e) {
									cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
									cheatFlowPerent.setPercent("0");
								}
							}
						}
						if (null == cheatFlowPerent || cheatFlowPerent.getFreetotal().equals("0")) {
							cheatFlowPerent = new CheatFlowPerent();
							cheatFlowPerent.setFreetotal("0");
							cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
							try {
								cheatFlowPerent.setPercent(df.format(Double.parseDouble(cheatFlowPerent.getFreetotal()) * 100
										/ Double.parseDouble(cheatFlowPerent.getTotal())));// 欺诈流量占总流量百分比
							} catch (Exception e) {
								cheatFlowPerent.setTotal(totalMap.get(cheatFlowPerent.getRecordtime()));
								cheatFlowPerent.setPercent("0");
							}
						}
						tempList.add(cheatFlowPerent);
					}
					cheatFlowPerentChart = new CheatFlowPerentChart();
					cheatFlowPerentChart.setListCheatFlowPerent(tempList);
					cheatFlowPerentChart.setListTime(typeTempList);
					perLstMap.put(tCheatChinese.getCheatCaseChinese(), cheatFlowPerentChart);
				}
				return perLstMap;
	}
}