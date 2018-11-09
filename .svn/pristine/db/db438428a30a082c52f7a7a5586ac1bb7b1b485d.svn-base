/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.dao.TTacSuccessRateDao;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.entity.TTacSuccessRate;

/**
 * 维度指标查询Service
 * @author 王晶石
 * @version 2017-05-31
 */
@Service
@Transactional(readOnly = true)
public class TTacSuccessRateService extends CrudService<TTacSuccessRateDao, TTacSuccessRate> {
	
	@Autowired
	private TTacSuccessRateDao tTacSuccessRateDao;

	public TTacSuccessRate get(String id) {
		return super.get(id);
	}
	
	public List<TTacSuccessRate> findList(TTacSuccessRate tTacSuccessRate) {
		return super.findList(tTacSuccessRate);
	}
	
	public Page<TTacSuccessRate> findPage(Page<TTacSuccessRate> page, TTacSuccessRate tTacSuccessRate) {
		return super.findPage(page, tTacSuccessRate);
	}
	
	@Transactional(readOnly = false)
	public void save(TTacSuccessRate tTacSuccessRate) {
		super.save(tTacSuccessRate);
	}
	
	@Transactional(readOnly = false)
	public void delete(TTacSuccessRate tTacSuccessRate) {
		super.delete(tTacSuccessRate);
	}

	public List<TEbmEvent> getEbmEventlist(String netType) {
		List<TEbmEvent> list = tTacSuccessRateDao.getEbmEventlist(netType);
		return list;
	}

	public List<TTacSuccessRate> queryList(String date,String startTime, String endTime, String netid, String eventType,int offset,int limit,String sortName,String sortOrder) {
		List<TTacSuccessRate> list = tTacSuccessRateDao.queryList(date,startTime,endTime,netid,eventType,offset,limit,sortName,sortOrder);
		return list;
	}
	
	public int queryListCount(String date,String startTime, String endTime, String netid, String eventType){
		int total = tTacSuccessRateDao.queryListCount(date, startTime, endTime,netid,eventType);
		return total;
	}
	
	public List<TTacSuccessRate> queryLnList(String date,String startTime, String endTime, String netid, String eventType,int offset,int limit,String sortName,String sortOrder) {
		List<TTacSuccessRate> list = tTacSuccessRateDao.queryLnList(date,startTime,endTime,netid,eventType,offset,limit,sortName,sortOrder);
		for(int i=0;i<list.size();i++) {
			list.get(i).setFname(Global.getConfig(list.get(i).getNetid()));
		}
		return list;
	}
	
	public int queryLnListCount(String date,String startTime, String endTime, String netid, String eventType){
		int total = tTacSuccessRateDao.queryLnListCount(date, startTime, endTime,netid,eventType);
		return total;
	}

	
	
}