/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.userquery.dao.TLteWholeNetParamDao;
import com.thinkgem.jeesite.modules.userquery.entity.TLteWholeNetParam;

/**
 * LTE全网参数Service
 * @author 陈宏波
 * @version 2017-07-28
 */
@Service
@Transactional(readOnly = true)
public class TLteWholeNetParamService extends CrudService<TLteWholeNetParamDao, TLteWholeNetParam> {

	@Autowired
	private TLteWholeNetParamDao tLteWholeNetParamDao;
	
	public TLteWholeNetParam get(String id) {
		return super.get(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(TLteWholeNetParam tLteWholeNetParam) {
		super.delete(tLteWholeNetParam);
	}
	
	@Transactional(readOnly = false)
	public void batchIntert(Map<String,TLteWholeNetParam> map) {
		
		tLteWholeNetParamDao.batchIntert(map);
	}
	
	public Map<String,TLteWholeNetParam> getLteWholeNetParamMap(){
		Map<String,TLteWholeNetParam> map = tLteWholeNetParamDao.getLteWholeNetParamMap();
		return map;
	};
	
	public List<TLteWholeNetParam> getLteWholeNetParamList(){
		List<TLteWholeNetParam> list = tLteWholeNetParamDao.getLteWholeNetParamList();
		return list;
	}
	
	public List<TLteWholeNetParam> getList(String date,String minLongitude,String maxLongitude,String minLatitude,String maxLatitude, String pool,String eventType,
			String recentMoment) {
		return tLteWholeNetParamDao.getList(date,minLongitude, maxLongitude, minLatitude, maxLatitude,pool,eventType,recentMoment);
	}
	
	public List<Map<String,Object>> getCollectByPool(String poolId,String fType) {
		return tLteWholeNetParamDao.getCollectByPool(poolId,fType);
	}

	public String queryRecentMoment(String nowDate, String pool, String eventType) {
		return tLteWholeNetParamDao.queryRecentMoment(nowDate,pool,eventType);
	}
	
}