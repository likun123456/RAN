package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatTypeAssess;
@MyBatisDao
public interface CheatTypeAssessDao extends CrudDao<CheatTypeAssess>{

	List<CheatTypeAssess> queryList(@Param("netid")String netid,@Param("startTime")String startTime,
			@Param("endTime")String endTime, @Param("timeGranularity")String timeGranularity);

	List<CheatTypeAssess> queryCheatUsersList(@Param("recordtime")String recordtime,@Param("netid")String netid,
			@Param("timeGranularity")String timeGranularity,@Param("proxyip")String proxyip,@Param("timeD")String timeD,
			@Param("timeX")String timeX,@Param("_timeX")int _timeX);

}
