package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatFlowPerent;

/**
 * 分类流量评估表
 * 
 * @author 杨海
 */
@MyBatisDao
public interface TCheatFlowPerentDao extends CrudDao<CheatFlowPerent> {

	List<CheatFlowPerent> getTableList(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran,
			@Param("limit") int limit, @Param("offset") int offset, @Param("sortName") String sortName,
			@Param("sortOrder") String sortOrder);

	int getTableCount(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran);

	List<CheatFlowPerent> getTableListDay(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran,
			@Param("limit") int limit, @Param("offset") int offset, @Param("sortName") String sortName,
			@Param("sortOrder") String sortOrder);

	int getTableCountDay(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran);

	List<CheatFlowPerent> getTableListMonth(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran,
			@Param("limit") int limit, @Param("offset") int offset, @Param("sortName") String sortName,
			@Param("sortOrder") String sortOrder);

	int getTableCountMonth(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran);

	List<String> getTypeList(@Param("netId")String netId, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("gran")String gran);

	CheatFlowPerent getValue(@Param("cheatCase")String cheatCase, @Param("timeStr")String timeStr, @Param("netId")String netId);

	List<String> getTypeListDay(@Param("netId")String netId, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("gran")String gran);
	
	List<String> getTypeListMonth(@Param("netId")String netId, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("gran")String gran);

	List<CheatFlowPerent> getTotalFlow(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("netId")String netId);
	
	List<CheatFlowPerent> getTotalFlowDay(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("netId")String netId);
	
	List<CheatFlowPerent> getTotalFlowMonth(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("netId")String netId);

}