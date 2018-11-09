package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatTopUser;

import groovy.transform.PackageScope;

/**
 * Top欺诈用户流量评估表
 * 
 * @author 杨海
 */
@MyBatisDao
public interface TCheatTopUserDao extends CrudDao<CheatTopUser> {

	List<CheatTopUser> queryList(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("top") String top, @Param("offset") int offset,
			@Param("limit") int limit, @Param("sortName") String sortName, @Param("sortOrder") String sortOrder,@Param("netName")String netName);

	int queryListCount(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("netId") String netId, @Param("top") String top);

}