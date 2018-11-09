package com.thinkgem.jeesite.modules.performance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.performance.entity.TMultipleIndex;
import com.thinkgem.jeesite.modules.performance.entity.TStatisticsVO;

/**
 * 性能指标综合 查询DAO接口
 * @author 杨海
 * @version 2017-06-01
 */
@MyBatisDao
public interface TMultipleIndexDao extends CrudDao<TMultipleIndex> {

	List<TStatisticsVO> findChart(TMultipleIndex tmultipleIndex);

	List<TStatisticsVO> queryList(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("netType")String netType,@Param("netids")String[] netids, @Param("formulaTypes")String[] formulaTypes, @Param("offset")int offset,
			@Param("limit")int limit, @Param("sortName")String sortName, @Param("sortOrder")String sortOrder);

	int queryListCount(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("netType")String netType,@Param("netids")String[] netids, @Param("formulaTypes")String[] formulaTypes);

}