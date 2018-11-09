package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatType;

/**
 * 分类流量评估表
 * 
 * @author 杨海
 */
@MyBatisDao
public interface TCheatTypeDao extends CrudDao<CheatType> {

	List<CheatType> getTableList(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran,
			@Param("limit") int limit, @Param("offset") int offset, @Param("sortName") String sortName,
			@Param("sortOrder") String sortOrder);

	int getTableCount(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran);

	List<CheatType> getTableListDay(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran,
			@Param("limit") int limit, @Param("offset") int offset, @Param("sortName") String sortName,
			@Param("sortOrder") String sortOrder);

	int getTableCountDay(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran);

	List<CheatType> getTableListMonth(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran,
			@Param("limit") int limit, @Param("offset") int offset, @Param("sortName") String sortName,
			@Param("sortOrder") String sortOrder);

	int getTableCountMonth(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("netName") String netName, @Param("gran") String gran);

	List<String> getTypeList(@Param("netId")String netId, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("gran")String gran);

	CheatType getValue(@Param("cheatCase")String cheatCase, @Param("timeStr")String timeStr, @Param("netId")String netId);

	List<String> getTypeListDay(@Param("netId")String netId, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("gran")String gran);
	
	List<String> getTypeListMonth(@Param("netId")String netId, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("gran")String gran);

}