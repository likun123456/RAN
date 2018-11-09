/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.entity.TEciSuccessRate;

/**
 * eci维度指标查询DAO接口
 * @author 陈宏波
 * @version 2017-06-09
 */
@MyBatisDao
public interface TEciSuccessRateDao extends CrudDao<TEciSuccessRate> {

	List<TEbmEvent> getEbmEventlist();

	List<TEciSuccessRate> queryList(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
			                        @Param("netid")String netid, @Param("eventType")String eventType,@Param("offset")int offset,
			                        @Param("limit")int limit,@Param("sortName")String sortName,@Param("sortOrder")String sortOrder);
	
	List<TEciSuccessRate> queryLnList(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
						            @Param("netid")String netid, @Param("eventType")String eventType,@Param("offset")int offset,
						            @Param("limit")int limit,@Param("sortName")String sortName,@Param("sortOrder")String sortOrder);
	
	int queryListCount(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime,
			           @Param("netid")String netid, @Param("eventType")String eventType);
	
}