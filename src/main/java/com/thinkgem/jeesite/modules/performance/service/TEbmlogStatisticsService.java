/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.entity.TDimensionVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;
import com.thinkgem.jeesite.modules.performance.dao.TEbmlogStatisticsDao;

/**
 * 异常原因多维分析(ebmlog)Service
 * @author 王晶石
 * @version 2017-06-07
 */
@Service
@Transactional(readOnly = true)
public class TEbmlogStatisticsService extends CrudService<TEbmlogStatisticsDao, TEbmlogStatistics> {
	
	@Autowired
	private TEbmlogStatisticsDao tEbmlogStatisticsDao;

	public TEbmlogStatistics get(String id) {
		return super.get(id);
	}
	
	public List<TEbmlogStatistics> findList(TEbmlogStatistics tEbmlogStatistics) {
		return super.findList(tEbmlogStatistics);
	}
	
	public Page<TEbmlogStatistics> findPage(Page<TEbmlogStatistics> page, TEbmlogStatistics tEbmlogStatistics) {
		return super.findPage(page, tEbmlogStatistics);
	}
	
	@Transactional(readOnly = false)
	public void save(TEbmlogStatistics tEbmlogStatistics) {
		super.save(tEbmlogStatistics);
	}
	
	@Transactional(readOnly = false)
	public void delete(TEbmlogStatistics tEbmlogStatistics) {
		super.delete(tEbmlogStatistics);
	}

	public List<TEbmlogStatistics> queryList(String date, String startTime, String endTime, String netid,
			String eventType, int offset, int limit) {
		List<TEbmlogStatistics> list = tEbmlogStatisticsDao.queryList(date,startTime,endTime,netid,eventType,offset,limit);
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getReject()!=null&&list.get(i).getReject().intValue()>0) {
				list.get(i).setEventResult("REJECT");
			}else if(list.get(i).getAbort()!=null&&list.get(i).getAbort().intValue()>0){
				list.get(i).setEventResult("ABORT");
			}else if(list.get(i).getIgnore()!=null&&list.get(i).getIgnore().intValue()>0){
				list.get(i).setEventResult("IGNORE");
			}else{
				list.get(i).setEventResult("SUCCESS");
			}
		}
		return list;
	}
	
	public List<TEbmlogStatistics> querySaegwList(String date, String startTime, String endTime, String netid,
			String eventType, int offset, int limit) {
		List<TEbmlogStatistics> list = tEbmlogStatisticsDao.querySaegwList(date,startTime,endTime,netid,eventType,offset,limit);
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getReject()!=null&&list.get(i).getReject().intValue()>0) {
				list.get(i).setEventResult("REJECT");
			}else if(list.get(i).getAbort()!=null&&list.get(i).getAbort().intValue()>0){
				list.get(i).setEventResult("ABORT");
			}else if(list.get(i).getIgnore()!=null&&list.get(i).getIgnore().intValue()>0){
				list.get(i).setEventResult("IGNORE");
			}else{
				list.get(i).setEventResult("SUCCESS");
			}
		}
		return list;
	}

	public int queryListCount(String date, String startTime, String endTime, String netid, String eventType) {
		int count = tEbmlogStatisticsDao.queryListCount(date,startTime,endTime,netid,eventType);
		return count;
	}
	
	public List<TEbmlogStatistics> queryPcrfList(String date, String startTime, String endTime, String netid,
			String eventType, int offset, int limit) {
		List<TEbmlogStatistics> list = tEbmlogStatisticsDao.queryPcrfList(date,startTime,endTime,netid,eventType,offset,limit);
		return list;
	}

	public int queryPcrfListCount(String date, String startTime, String endTime, String netid, String eventType) {
		int count = tEbmlogStatisticsDao.queryPcrfListCount(date,startTime,endTime,netid,eventType);
		return count;
	}

	public List<TDimensionVO> getDimensionlistByKey(String parm, String netid, String date, String startTime,
			String endTime, String ip, String str, int num,String eventType,String statisticType) {
		List<TDimensionVO> list = tEbmlogStatisticsDao.getDimensionlistByKey(parm,netid,date,startTime,endTime,ip,str,num,eventType,statisticType);
		return list;
	}
	
	public List<TDimensionVO> getDimensionSaegwlistByKey(String parm, String netid, String date, String startTime,
			String endTime, String ip, String str, int num,String eventType,String statisticType) {
		List<TDimensionVO> list = tEbmlogStatisticsDao.getDimensionSaegwlistByKey(parm,netid,date,startTime,endTime,ip,str,num,eventType,statisticType);
		return list;
	}
	
	public List<TDimensionVO> getDimensionPcrflistByKey(String parm, String netid, String date, String startTime,
			String endTime, String ip, String str, int num,String eventType) {
		List<TDimensionVO> list = tEbmlogStatisticsDao.getDimensionPcrflistByKey(parm,netid,date,startTime,endTime,ip,str,num,eventType);
		return list;
	}
	

	public List<TEbmlogStatistics> queryListByTac(String date, String startTime, String endTime, String netid,
			String eventType, String tac, int offset, int limit) {
		List<TEbmlogStatistics> list = tEbmlogStatisticsDao.queryListByTac(date,startTime,endTime,netid,eventType,tac,offset,limit);
		return list;
	}

	public int queryListByTacCount(String date, String startTime, String endTime, String netid, String tac,
			String eventType) {
		int count = tEbmlogStatisticsDao.queryListByTacCount(date,startTime,endTime,netid,eventType,tac);
		return count;
	}
	
	public List<TEbmlogStatistics> queryListByEci(String date, String startTime, String endTime, String netid,
			String eventType, String eci, int offset, int limit) {
		List<TEbmlogStatistics> list = tEbmlogStatisticsDao.queryListByEci(date,startTime,endTime,netid,eventType,eci,offset,limit);
		return list;
	}

	public int queryListByEciCount(String date, String startTime, String endTime, String netid, String eci,
			String eventType) {
		int count = tEbmlogStatisticsDao.queryListByEciCount(date,startTime,endTime,netid,eventType,eci);
		return count;
	}
	
	public List<TEbmlogStatistics> querySuccessList(String date, String startTime, String endTime, String netid,String eventType){
		List<TEbmlogStatistics> list = tEbmlogStatisticsDao.querySuccessList(date,startTime,endTime,netid,eventType);
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getReject()!=null&&list.get(i).getReject().intValue()>0) {
				list.get(i).setEventResult("REJECT");
			}else if(list.get(i).getAbort()!=null&&list.get(i).getAbort().intValue()>0){
				list.get(i).setEventResult("ABORT");
			}else if(list.get(i).getIgnore()!=null&&list.get(i).getIgnore().intValue()>0){
				list.get(i).setEventResult("IGNORE");
			}else{
				list.get(i).setEventResult("SUCCESS");
			}
		}
		return list;
	}
	
}