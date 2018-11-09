package com.thinkgem.jeesite.modules.cheat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.CheatTypeAssessDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatTypeAssess;
import com.thinkgem.jeesite.modules.cheat.entity.TCheatChinese;
@Service
@Transactional(readOnly = true)
public class CheatTypeAssessService extends CrudService<CheatTypeAssessDao, CheatTypeAssess>{
	
	@Autowired
	CheatTypeAssessDao cheatTypeAssessDao;
	
	public List<CheatTypeAssess> queryList(String netid, String startTime, String endTime, String timeGranularity) {
		List<CheatTypeAssess> list = cheatTypeAssessDao.queryList(netid,startTime,endTime,timeGranularity);
		return list;
	}

	public List<LinkedHashMap<String, String>> combineList(List<CheatTypeAssess> list,
			List<TCheatChinese> cheatChineseList,String timeGranularity) {
		List<LinkedHashMap<String, String>> combinationlist = new ArrayList<LinkedHashMap<String, String>>();
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		Map<String,CheatTypeAssess> map = new HashMap<String,CheatTypeAssess>();
		CheatTypeAssess cheatTypeAssess;
		for(int i=0;i<list.size();i++){
			Map<String, String> innerMap = new HashMap<String, String>();
			CheatTypeAssess c = list.get(i);
			String cheatCase = c.getCheatCaseChinese();
			long cheatFlow = c.getCheatFlow();
			int counts = c.getCounts();
			innerMap.put(cheatCase, df.format((double)cheatFlow/(1024*1024))+"M/"+counts);
			String recordtime = c.getRecordtime();
			String _timeH = c.getTimeH();
			String _timeD = c.getTimeD();
			String _timeM = c.getTimeM();
			String proxyServerIp = c.getProxyIp();
			String timeX = "";
			switch(timeGranularity) {
				case "H": timeX = _timeH;break;
				case "D": timeX = _timeD;break;
				case "M": timeX = _timeM;break;
			}
			if (map.containsKey(timeX + "-" + proxyServerIp)) {
				map.get(timeX + "-" + proxyServerIp).getInnerCheatMap().putAll(innerMap);
			} else {
				cheatTypeAssess = new CheatTypeAssess();
				cheatTypeAssess.setRecordtime(recordtime);
				cheatTypeAssess.setTimeH(_timeH);
				cheatTypeAssess.setTimeD(_timeD);
				cheatTypeAssess.setTimeM(_timeM);
				cheatTypeAssess.setInnerCheatMap(innerMap);
				map.put(timeX + "-" + proxyServerIp,cheatTypeAssess);
			}
		}
		LinkedHashMap<String,String> dataMap;
		for (Map.Entry<String, CheatTypeAssess> entry : map.entrySet()) {
			dataMap = new LinkedHashMap<String,String>();
			switch(timeGranularity) {
				case "H": dataMap.put("recordtime", entry.getValue().getRecordtime());break;
				case "D": dataMap.put("recordtime", entry.getValue().getRecordtime().substring(0, 10));break;
				case "M": dataMap.put("recordtime", entry.getValue().getRecordtime().substring(0, 7));break;
			}
			dataMap.put("proxyip", entry.getKey().split("-")[1]);
			for(int i=0;i<cheatChineseList.size();i++){
				TCheatChinese t = cheatChineseList.get(i);
				if(entry.getValue().getInnerCheatMap().containsKey(t.getCheatCaseChinese())){
					dataMap.put(t.getCheatCaseChinese(), entry.getValue().getInnerCheatMap().get(t.getCheatCaseChinese()));
				}else{
					dataMap.put(t.getCheatCaseChinese(), "0M/0");
				}
				
			}
		   combinationlist.add(dataMap);
		}
		return combinationlist;
	}

	public List<CheatTypeAssess> queryCheatUsersList(String recordtime, String netid, String timeGranularity,
			String proxyip, String timeD, String timeX, int _timeX) {
		List<CheatTypeAssess> list = cheatTypeAssessDao.queryCheatUsersList(recordtime,netid,timeGranularity,proxyip,timeD,timeX,_timeX);
		return list;
	}

}
