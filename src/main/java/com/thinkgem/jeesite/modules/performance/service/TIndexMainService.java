package com.thinkgem.jeesite.modules.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.dao.TIndexMainDao;
import com.thinkgem.jeesite.modules.performance.entity.TIndexMain;

/**
 * 首页显示Service
 * @author 杨海
 * @version 2017-07-12
 */
@Service
@Transactional(readOnly = true)
public class TIndexMainService extends CrudService<TIndexMainDao, TIndexMain> {
	@Autowired
	TIndexMainDao tIndexMainDao;
	public TIndexMain get(String id) {
		return super.get(id);
	}
	
	public String getDateTime(String tableName){
		return tIndexMainDao.getDateTime(tableName);
	}
	
	public List<TIndexMain> queryList(TIndexMain tIndexMain,String tableName){
		String []kpiList=tIndexMain.getKpiList().split(",");
		return tIndexMainDao.queryList(tIndexMain.getTpoolId(),tIndexMain.getDateTime().substring(0,19),kpiList,tIndexMain.getTimeInterval(),tableName);
	}
	
	public List<TIndexMain> queryNetList(TIndexMain tIndexMain,String tableName){

		String []kpiList=tIndexMain.getKpiList().split(",");
		return tIndexMainDao.queryNetList(tIndexMain.getNetId(),tIndexMain.getDateTime().substring(0,19),kpiList,tIndexMain.getTimeInterval(),tableName);
	}
	
	public Map<String,List<TIndexMain>> queryChartList(TIndexMain tIndexMain,String tableName){
		String []kpiList=tIndexMain.getKpiList().split(",");
		Map<String,List<TIndexMain>> kpiDataList=new HashMap<String,List<TIndexMain>>();
		for(int m=0;m<kpiList.length;m++){
			kpiDataList.put(kpiList[m],tIndexMainDao.queryChartList(tIndexMain.getTpoolId(),kpiList[m],tIndexMain.getTimeInterval(),tableName));
		}
		return kpiDataList;
	}
	
	public Map<String,List<TIndexMain>> queryNetChartList(TIndexMain tIndexMain,String tableName){
		String []kpiList=tIndexMain.getKpiList().split(",");
		Map<String,List<TIndexMain>> kpiDataList=new HashMap<String,List<TIndexMain>>();
		for(int m=0;m<kpiList.length;m++){
			kpiDataList.put(kpiList[m],tIndexMainDao.queryNetChartList(tIndexMain.getNetId(),kpiList[m],tIndexMain.getTimeInterval(),tableName));
		}
		return kpiDataList;
	}
}