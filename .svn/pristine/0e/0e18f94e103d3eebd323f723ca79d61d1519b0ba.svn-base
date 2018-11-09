package com.thinkgem.jeesite.modules.cheat.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.TCheatUserDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatLog;
import com.thinkgem.jeesite.modules.cheat.entity.CheatUser;

/**
 * 欺诈用户评估表
 * 
 * @author chenhongbo
 */
@Service
@Transactional(readOnly = true)
public class CheatUserService extends CrudService<TCheatUserDao, CheatUser> {
	@Autowired
	private TCheatUserDao tCheatUserDao;

	public List<CheatUser> getTableList(String startTime, String endTime,String netId, int limit, int offset, String sortName,String sortOrder) {
		List<CheatUser> list = tCheatUserDao.getTableList(startTime, endTime, netId, limit, offset, sortName, sortOrder);
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		for(CheatUser cu : list) {
			cu.setFreetotal(df.format(Double.parseDouble(cu.getFreetotal()) / (1024 * 1024)));
			cu.setTotal(df.format(Double.parseDouble(cu.getTotal()) / (1024 * 1024)));
			cu.setPercent(String.valueOf(df.format(Double.parseDouble(cu.getPercent()) * 100)) + "%");
			cu.setStartTime(startTime);
			cu.setEndTime(endTime);
			cu.setNetId(netId);
		}
		return list;
	}
	
	public Integer getTableCount(String startTime, String endTime,String netId) {
		return tCheatUserDao.getTableCount(startTime, endTime, netId);
	}
	
	public List<CheatLog> getCheatlog(String netId, String servedIMSI, String startTime, String endTime, String cheatCase) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		//当月第一天
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		startTime = dateFormat.format(calendar.getTime()) + "000000";
		//当月最后一天
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		endTime =  dateFormat.format(calendar.getTime()) + "235959";
		
		return tCheatUserDao.getCheatlog(netId, servedIMSI, startTime, endTime, cheatCase);
	}
	
	public List<Map<String, Object>> getPieChart(String netId,String startTime,String endTime,String servedIMSI) {
		List<Map<String, Object>> list = tCheatUserDao.getPieChart(netId, startTime, endTime, servedIMSI);
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		for(Map<String, Object> map : list) {
			map.put("dataUp", df.format(((BigDecimal)map.get("dataUp")).longValue() / 1024.0));
			map.put("dataDown", df.format(((BigDecimal)map.get("dataDown")).longValue() / 1024.0));
			map.put("total", df.format(((BigDecimal)map.get("total")).longValue() / 1024.0));
		}
		return list;
	}
	
	public Map<String, Object> getFlowPieChart(String netId,String servedIMSI,String startTime,String endTime) {
		Map<String, Object> flowMap = tCheatUserDao.getFlowPieChart(netId, servedIMSI, startTime, endTime);
		
		long freetotal = ((BigDecimal)flowMap.get("freetotal")).longValue();
		long charge = ((BigDecimal)flowMap.get("total")).longValue() - freetotal;
		flowMap.put("freetotal", freetotal / 1024);
		flowMap.put("charge", charge / 1024);
		
		return flowMap;
	}


}