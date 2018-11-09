/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckConfig;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckLog;

/**
 * 智能巡检DAO接口
 * @author zhuguangrui
 * @version 2017-08-04
 */
@MyBatisDao
public interface TAutoCheckLogDao extends CrudDao<TAutoCheckLog> {
	
	List<TAutoCheckLog> queryList(@Param("netType")String netType,@Param("netId")String netId,@Param("checkItem")String checkItem,@Param("checkResult")String checkResult,@Param("beginDate")String beginDate,@Param("endDate")String endDate,
			@Param("offset")int offset,@Param("limit")int limit,@Param("sortName")String sortName,@Param("sortOrder")String sortOrder);

	int queryListCount(@Param("netType")String netType,@Param("netId")String netId,@Param("checkItem")String checkItem,@Param("checkResult")String checkResult,@Param("beginDate")String beginDate,@Param("endDate")String endDate);

	String queryMaxCheckTime();

	List<TAutoCheckConfig> queryCheckItems(@Param("netType")String netType);
		
}