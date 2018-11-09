package com.thinkgem.jeesite.modules.performance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.dao.TMultipleIndexDao;
import com.thinkgem.jeesite.modules.performance.entity.TMultipleIndex;
import com.thinkgem.jeesite.modules.performance.entity.TStatisticsVO;

/**
 * 性能指标综合查询Service
 * @author 杨海
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class TMultipleIndexService extends CrudService<TMultipleIndexDao, TMultipleIndex> {
	
	@Autowired
	private TMultipleIndexDao tMultipleIndexDao;

	public TMultipleIndex get(String id) {
		return super.get(id);
	}	
	
	public List<List<List<TStatisticsVO>>> findChart(TMultipleIndex tmultipleIndex){
		List<List<List<TStatisticsVO>>> formulalist = new ArrayList<List<List<TStatisticsVO>>>();
		if(tmultipleIndex.getNetid()!=null){
		String[] netids = tmultipleIndex.getNetid().split(",");
		String[] formulaids = tmultipleIndex.getFormulaType().split(",");
		for (int j = 0; j < formulaids.length; j++) {
			List<List<TStatisticsVO>> chartlist = new ArrayList<List<TStatisticsVO>>();
			for (int i = 0; i < netids.length; i++) {
				tmultipleIndex.setNetid(netids[i]);
				tmultipleIndex.setFormulaType(formulaids[j]);
				List<TStatisticsVO> list =tMultipleIndexDao.findChart(tmultipleIndex);
				if (list.size() != 0) {
					chartlist.add(list);
				}
			}
			if (chartlist.size() != 0) {
				formulalist.add(chartlist);
			}
		}
	}
		return formulalist;
	}

	public List<TStatisticsVO> queryList(String startTime, String endTime, String netType,String[] netid, String[] formulaType, int offset,
			int limit, String sortName, String sortOrder) {
		List<TStatisticsVO> list = tMultipleIndexDao.queryList(startTime,endTime,netType,netid,formulaType,offset,limit,sortName,sortOrder);
		return list;
	}

	public int queryListCount(String startTime, String endTime, String netType,String netid, String formulaType) {
		int total = tMultipleIndexDao.queryListCount(startTime, endTime,netType,netid.split(","),formulaType.split(","));
		return total;
	}
}