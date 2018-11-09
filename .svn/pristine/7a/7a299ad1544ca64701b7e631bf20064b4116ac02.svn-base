/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.entity.TTacSuccessRate;

/**
 * 维度指标查询DAO接口
 * @author 王晶石
 * @version 2017-05-31
 */
@MyBatisDao
public interface TTacSuccessRateDao extends CrudDao<TTacSuccessRate> {

	List<TEbmEvent> getEbmEventlist(@Param("netType")String netType);

	List<TTacSuccessRate> queryList(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
			                        @Param("netid")String netid, @Param("eventType")String eventType,@Param("offset")int offset,
			                        @Param("limit")int limit,@Param("sortName")String sortName,@Param("sortOrder")String sortOrder);
	
	int queryListCount(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime,
			           @Param("netid")String netid, @Param("eventType")String eventType);
	
	List<TTacSuccessRate> queryLnList(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
            @Param("netid")String netid, @Param("eventType")String eventType,@Param("offset")int offset,
            @Param("limit")int limit,@Param("sortName")String sortName,@Param("sortOrder")String sortOrder);

	int queryLnListCount(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime,
					   @Param("netid")String netid, @Param("eventType")String eventType);
	
}