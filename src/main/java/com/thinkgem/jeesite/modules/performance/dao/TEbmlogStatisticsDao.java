/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.performance.entity.TDimensionVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;

/**
 * 异常原因多维分析(ebmlog)DAO接口
 * @author 王晶石
 * @version 2017-06-07
 */
@MyBatisDao
public interface TEbmlogStatisticsDao extends CrudDao<TEbmlogStatistics> {

	List<TEbmlogStatistics> queryList(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
                                      @Param("netid")String netid, @Param("eventType")String eventType,@Param("offset")int offset,
                                      @Param("limit")int limit);
	
	List<TEbmlogStatistics> querySaegwList(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
            @Param("netid")String netid, @Param("eventType")String eventType,@Param("offset")int offset,
            @Param("limit")int limit);
	
	List<TEbmlogStatistics> querySuccessList(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
            								 @Param("netid")String netid, @Param("eventType")String eventType);

	int queryListCount(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime,
	                   @Param("netid")String netid, @Param("eventType")String eventType);
	
	List<TEbmlogStatistics> queryPcrfList(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
							              @Param("netid")String netid, @Param("eventType")String eventType,@Param("offset")int offset,
							              @Param("limit")int limit);

    int queryPcrfListCount(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime,
    					   @Param("netid")String netid, @Param("eventType")String eventType);

	List<TDimensionVO> getDimensionPcrflistByKey(@Param("parm")String parm, @Param("netid")String netid, @Param("date")String date, 
												 @Param("startTime")String startTime, @Param("endTime")String endTime,
												 @Param("ip")String ip, @Param("str")String str, @Param("num")int num,
												 @Param("eventType")String eventType);
	
	List<TDimensionVO> getDimensionlistByKey(@Param("parm")String parm, @Param("netid")String netid, @Param("date")String date, 
											 @Param("startTime")String startTime, @Param("endTime")String endTime,
											 @Param("ip")String ip, @Param("str")String str, @Param("num")int num,
											 @Param("eventType")String eventType,@Param("statisticType")String statisticType);
    
	List<TDimensionVO> getDimensionSaegwlistByKey(@Param("parm")String parm, @Param("netid")String netid, @Param("date")String date, 
											 @Param("startTime")String startTime, @Param("endTime")String endTime,
											 @Param("ip")String ip, @Param("str")String str, @Param("num")int num,
											 @Param("eventType")String eventType,@Param("statisticType")String statisticType);

	List<TEbmlogStatistics> queryListByTac(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
            @Param("netid")String netid, @Param("eventType")String eventType,@Param("tac")String tac,@Param("offset")int offset,
            @Param("limit")int limit);

	int queryListByTacCount(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime,
            @Param("netid")String netid, @Param("eventType")String eventType,@Param("tac")String tac);
	
	List<TEbmlogStatistics> queryListByEci(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime, 
            @Param("netid")String netid, @Param("eventType")String eventType,@Param("eci")String eci,@Param("offset")int offset,
            @Param("limit")int limit);

	int queryListByEciCount(@Param("date")String date,@Param("startTime")String startTime,@Param("endTime")String endTime,
            @Param("netid")String netid, @Param("eventType")String eventType,@Param("eci")String eci);
	
}