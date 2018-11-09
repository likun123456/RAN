package com.thinkgem.jeesite.modules.performance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.performance.entity.TIndexMain;

/**
 * 首页
 * @author 杨海
 * @version 2017-07-12
 */
@MyBatisDao
public interface TIndexMainDao extends CrudDao<TIndexMain> {
	String getDateTime(@Param("tableName")String tableName);
	List<TIndexMain> queryList(@Param("tpoolId")String tpoolId, @Param("dateTime")String dateTime, @Param("kpiList")String[] kpiList,@Param("timeInterval")String timeInterval,@Param("tableName")String tableName);
	List<TIndexMain> queryNetList(@Param("netId")String netId, @Param("dateTime")String dateTime, @Param("kpiList")String[] kpiList,@Param("timeInterval")String timeInterval,@Param("tableName")String tableName);
	List<TIndexMain> queryChartList(@Param("tpoolId")String tpoolId, @Param("kpi")String kpi,@Param("timeInterval")String timeInterval,@Param("tableName")String tableName);
	List<TIndexMain> queryNetChartList(@Param("netId")String netId, @Param("kpi")String kpi,@Param("timeInterval")String timeInterval,@Param("tableName")String tableName);
}