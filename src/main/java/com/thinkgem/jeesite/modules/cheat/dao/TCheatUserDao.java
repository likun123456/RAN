package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatLog;
import com.thinkgem.jeesite.modules.cheat.entity.CheatUser;

/**
 * 欺诈用户评估表
 * 
 * @author chenhongbo
 */
@MyBatisDao
public interface TCheatUserDao extends CrudDao<CheatUser> {

	List<CheatUser> getTableList(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("limit") int limit, @Param("offset") int offset, @Param("sortName") String sortName,
			@Param("sortOrder") String sortOrder);
	
	Integer getTableCount(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId);
	
	List<CheatLog> getCheatlog(@Param("netId") String netId, @Param("servedIMSI") String servedIMSI, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("cheatCase") String cheatCase);

	List<Map<String, Object>> getPieChart(@Param("netId") String netId,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("servedIMSI") String servedIMSI);
	
	Map<String, Object> getFlowPieChart(@Param("netId") String netId,@Param("servedIMSI") String servedIMSI,@Param("startTime") String startTime,@Param("endTime") String endTime);
}