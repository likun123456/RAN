package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatAnalysis;
import com.thinkgem.jeesite.modules.cheat.entity.CheatRatingGroup;

@MyBatisDao
public interface CheatAnalysisDao extends CrudDao<CheatAnalysis>{

	List<CheatAnalysis> queryList(@Param("date")String date, @Param("startTime")String startTime, @Param("endTime")String endTime,
			@Param("netid")String netid, @Param("freePrecentThreshold")int freePrecentThreshold,
			@Param("freeThreshold")int freeThreshold, @Param("offset")int offset, @Param("limit")int limit);

	int queryListCount(@Param("date")String date, @Param("startTime")String startTime, @Param("endTime")String endTime, 
			@Param("netid")String netid, @Param("freePrecentThreshold")int freePrecentThreshold,
			@Param("freeThreshold")int freeThreshold);

	List<CheatRatingGroup> queryCheatRatingGroupList(@Param("date")String date, @Param("startTime")String startTime, @Param("endTime")String endTime, 
			@Param("netid")String netid,@Param("imsi")String imsi);

}
