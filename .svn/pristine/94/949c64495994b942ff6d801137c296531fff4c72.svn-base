package com.thinkgem.jeesite.modules.cheat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.TCheatFreeBusinessDao;
import com.thinkgem.jeesite.modules.cheat.dao.TFreeRatingGroupDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatFreeBusiness;
import com.thinkgem.jeesite.modules.cheat.entity.CheatFreeBusinessChart;
import com.thinkgem.jeesite.modules.cheat.entity.CheatType;
import com.thinkgem.jeesite.modules.cheat.entity.CheatTypeChart;
import com.thinkgem.jeesite.modules.cheat.entity.TFreeRatingGroup;

/**
 * 免费业务类型欺诈流量评估
 * 
 * @author 杨海
 */
@Service
@Transactional(readOnly = true)
public class CheatFreeBusinessService extends CrudService<TCheatFreeBusinessDao, CheatFreeBusiness> {
	@Autowired
	private TCheatFreeBusinessDao tCheatFreeBusinessDao;
	@Autowired
	private TFreeRatingGroupDao tFreeRatingGroupDao;

	public CheatFreeBusiness get(String id) {
		return super.get(id);
	}

	public List<CheatFreeBusiness> querypercentTableList(String startTime, String endTime, String netId, String netName,
			String gran, int limit, int offset, String sortName, String sortOrder,String ratingGroup) {
		return tCheatFreeBusinessDao.getTableList(startTime, endTime, netId, netName, gran, limit, offset, sortName, sortOrder,ratingGroup);
	}

	public int getpercentTotal(String netId, String startTime, String endTime, String netName, String gran,String ratingGroup) {
		return tCheatFreeBusinessDao.getTableCount(startTime, endTime, netId, netName, gran,ratingGroup);
	}

	public List<CheatFreeBusiness> querypercentTableListDay(String startTime, String endTime, String netId, String netName,
			String gran, int limit, int offset, String sortName, String sortOrder,String ratingGroup) {
		return tCheatFreeBusinessDao.getTableListDay(startTime, endTime, netId, netName, gran, limit, offset, sortName,
				sortOrder,ratingGroup);
	}

	public int getpercentTotalDay(String netId, String startTime, String endTime, String netName, String gran,String ratingGroup) {
		return tCheatFreeBusinessDao.getTableCountDay(startTime, endTime, netId, netName, gran,ratingGroup);
	}

	public List<CheatFreeBusiness> querypercentTableListMonth(String startTime, String endTime, String netId, String netName,
			String gran, int limit, int offset, String sortName, String sortOrder,String ratingGroup) {
		return tCheatFreeBusinessDao.getTableListMonth(startTime, endTime, netId, netName, gran, limit, offset, sortName,
				sortOrder,ratingGroup);
	}

	public int getpercentTotalMonth(String netId, String startTime, String endTime, String netName, String gran,String ratingGroup) {
		return tCheatFreeBusinessDao.getTableCountMonth(startTime, endTime, netId, netName, gran,ratingGroup);
	}

	public Map<String, CheatFreeBusinessChart> queryCdrSubpercentMap(String netId, String startTime, String endTime,
			String gran,String ratingGroup) {
		List<String> typeList = new ArrayList<String>();
		List<String> typeTempList = new ArrayList<String>();
		List<CheatFreeBusiness> tempList;
		typeTempList = tCheatFreeBusinessDao.getTypeList(netId, startTime, endTime, gran);
		for (int i = 0; i < typeTempList.size(); i++) {
			String temp = typeTempList.get(i);
			typeList.add(temp.substring(temp.length() - 15, temp.length() - 5));
		}
		List<TFreeRatingGroup> freeRatingGroupList=new ArrayList<TFreeRatingGroup>();
		List<TFreeRatingGroup> freeRatingGroupListTemp = tFreeRatingGroupDao.findList(new TFreeRatingGroup());
		String []strRG=ratingGroup.split(",");
		for(int m=0;m<strRG.length;m++){
			for(TFreeRatingGroup tFreeRatingGroup:freeRatingGroupListTemp){
				if(tFreeRatingGroup.getRatingGroup().equals(strRG[m])){
					freeRatingGroupList.add(tFreeRatingGroup);
				}
			}
		}
		
		Map<String, CheatFreeBusinessChart> perLstMap = new HashMap<String, CheatFreeBusinessChart>();
		CheatFreeBusinessChart cheatFreeBusinessChart;

		List<CheatFreeBusiness> tempListAll = tCheatFreeBusinessDao.getTableList(startTime, endTime, netId, null, gran, 0, 0, null,
				null,ratingGroup);

		for (TFreeRatingGroup tFreeRatingGroup : freeRatingGroupList) {
			tempList = new ArrayList<CheatFreeBusiness>();
			for (String timeStr : typeTempList) {
				CheatFreeBusiness cheatFreeBusiness=null;
				for (int tem = 0; tem < tempListAll.size(); tem++) {
					CheatFreeBusiness cheatFreeBusinessTemp= tempListAll.get(tem);
					if (cheatFreeBusinessTemp.getRatingGroup().equals(tFreeRatingGroup.getRatingGroup()) && cheatFreeBusinessTemp.getRecordtime().equals(timeStr)) {
						cheatFreeBusiness=cheatFreeBusinessTemp;
					}
				}
				if (null == cheatFreeBusiness) {
					cheatFreeBusiness = new CheatFreeBusiness();
					cheatFreeBusiness.setTotal("0");
				}
				tempList.add(cheatFreeBusiness);
			}
			cheatFreeBusinessChart = new CheatFreeBusinessChart();
			cheatFreeBusinessChart.setListCheatFreeBusiness(tempList);
			cheatFreeBusinessChart.setListTime(typeList);
			perLstMap.put(tFreeRatingGroup.getName(), cheatFreeBusinessChart);
		}
		return perLstMap;
	}

	public Map<String, CheatFreeBusinessChart> queryCdrSubpercentMapDay(String netId, String startTime, String endTime,
			String gran,String ratingGroup) {
		
		List<String> typeList = new ArrayList<String>();
		List<String> typeTempList = new ArrayList<String>();
		List<CheatFreeBusiness> tempList;
		typeTempList = tCheatFreeBusinessDao.getTypeListDay(netId, startTime, endTime, gran);
		for (int i = 0; i < typeTempList.size(); i++) {
			String temp = typeTempList.get(i);
			typeList.add(temp.substring(temp.length() - 4, temp.length() - 0));
		}
		
		List<TFreeRatingGroup> freeRatingGroupList=new ArrayList<TFreeRatingGroup>();
		List<TFreeRatingGroup> freeRatingGroupListTemp = tFreeRatingGroupDao.findList(new TFreeRatingGroup());
		String []strRG=ratingGroup.split(",");
		for(int m=0;m<strRG.length;m++){
			for(TFreeRatingGroup tFreeRatingGroup:freeRatingGroupListTemp){
				if(tFreeRatingGroup.getRatingGroup().equals(strRG[m])){
					freeRatingGroupList.add(tFreeRatingGroup);
				}
			}
		}
		
		Map<String, CheatFreeBusinessChart> perLstMap = new HashMap<String, CheatFreeBusinessChart>();
		CheatFreeBusinessChart cheatFreeBusinessChart;

		List<CheatFreeBusiness> tempListAll = tCheatFreeBusinessDao.getTableListDay(startTime, endTime, netId, null, gran, 0, 0, null,
				null,ratingGroup);

		for (TFreeRatingGroup tFreeRatingGroup : freeRatingGroupList) {
			tempList = new ArrayList<CheatFreeBusiness>();
			for (String timeStr : typeTempList) {
				CheatFreeBusiness cheatFreeBusiness=null;
				for (int tem = 0; tem < tempListAll.size(); tem++) {
					CheatFreeBusiness cheatFreeBusinessTemp= tempListAll.get(tem);
					if (cheatFreeBusinessTemp.getRatingGroup().equals(tFreeRatingGroup.getRatingGroup()) && cheatFreeBusinessTemp.getRecordtime().equals(timeStr)) {
						cheatFreeBusiness=cheatFreeBusinessTemp;
					}
				}
				if (null == cheatFreeBusiness) {
					cheatFreeBusiness = new CheatFreeBusiness();
					cheatFreeBusiness.setTotal("0");
				}
				tempList.add(cheatFreeBusiness);
			}
			cheatFreeBusinessChart = new CheatFreeBusinessChart();
			cheatFreeBusinessChart.setListCheatFreeBusiness(tempList);
			cheatFreeBusinessChart.setListTime(typeList);
			perLstMap.put(tFreeRatingGroup.getName(), cheatFreeBusinessChart);
		}
		return perLstMap;
	}

	public Map<String, CheatFreeBusinessChart> queryCdrSubpercentMapMonth(String netId, String startTime, String endTime,
			String gran,String ratingGroup) {
		
		List<String> typeTempList = new ArrayList<String>();
		List<CheatFreeBusiness> tempList;
		typeTempList = tCheatFreeBusinessDao.getTypeListMonth(netId, startTime, endTime, gran);

		List<TFreeRatingGroup> freeRatingGroupList=new ArrayList<TFreeRatingGroup>();
		List<TFreeRatingGroup> freeRatingGroupListTemp = tFreeRatingGroupDao.findList(new TFreeRatingGroup());
		String []strRG=ratingGroup.split(",");
		for(int m=0;m<strRG.length;m++){
			for(TFreeRatingGroup tFreeRatingGroup:freeRatingGroupListTemp){
				if(tFreeRatingGroup.getRatingGroup().equals(strRG[m])){
					freeRatingGroupList.add(tFreeRatingGroup);
				}
			}
		}
		
		Map<String, CheatFreeBusinessChart> perLstMap = new HashMap<String, CheatFreeBusinessChart>();
		CheatFreeBusinessChart cheatFreeBusinessChart;

		List<CheatFreeBusiness> tempListAll = tCheatFreeBusinessDao.getTableListMonth(startTime, endTime, netId, null, gran, 0, 0, null,
				null,ratingGroup);

		for (TFreeRatingGroup tFreeRatingGroup : freeRatingGroupList) {
			tempList = new ArrayList<CheatFreeBusiness>();
			for (String timeStr : typeTempList) {
				CheatFreeBusiness cheatFreeBusiness=null;
				for (int tem = 0; tem < tempListAll.size(); tem++) {
					CheatFreeBusiness cheatFreeBusinessTemp= tempListAll.get(tem);
					if (cheatFreeBusinessTemp.getRatingGroup().equals(tFreeRatingGroup.getRatingGroup()) && cheatFreeBusinessTemp.getRecordtime().equals(timeStr)) {
						cheatFreeBusiness=cheatFreeBusinessTemp;
					}
				}
				if (null == cheatFreeBusiness) {
					cheatFreeBusiness = new CheatFreeBusiness();
					cheatFreeBusiness.setTotal("0");
				}
				tempList.add(cheatFreeBusiness);
			}
			cheatFreeBusinessChart = new CheatFreeBusinessChart();
			cheatFreeBusinessChart.setListCheatFreeBusiness(tempList);
			cheatFreeBusinessChart.setListTime(typeTempList);
			perLstMap.put(tFreeRatingGroup.getName(), cheatFreeBusinessChart);
		}
		return perLstMap;
	}
}