/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.userquery.entity.TLteWholeNetParam;

/**
 * LTE全网参数DAO接口
 * @author chenhongbo
 * @version 2017-06-16
 */
@MyBatisDao
public interface TLteWholeNetParamDao extends CrudDao<TLteWholeNetParam> {
	void batchIntert(@Param("map")Map<String,TLteWholeNetParam> map);
	
	@MapKey("eci")
	Map<String,TLteWholeNetParam> getLteWholeNetParamMap();

	List<TLteWholeNetParam> getLteWholeNetParamList();
	
	List<TLteWholeNetParam> getList(@Param("date")String date,@Param("minLongitude")String minLongitude,@Param("maxLongitude")String maxLongitude,
			@Param("minLatitude")String minLatitude,@Param("maxLatitude")String maxLatitude,@Param("pool")String pool,@Param("eventType")String eventType,
			@Param("recentMoment")String recentMoment);

	List<Map<String,Object>> getCollectByPool(@Param("poolId")String poolId,@Param("fType")String fType);

	String queryRecentMoment(@Param("date")String date,@Param("pool")String pool,@Param("eventType")String eventType);
}