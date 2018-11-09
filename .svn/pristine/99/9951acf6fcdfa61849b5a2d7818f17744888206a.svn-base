/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;

/**
 * 网元基本信息操作DAO接口
 * @author 王晶石
 * @version 2017-05-25
 */
@MyBatisDao
public interface TNewnetelementDao extends CrudDao<TNewnetelement> {

	int getByName(TNewnetelement t);

	List<TNewnetelement> findListByServiceType(TNewnetelement tNewnetelement);
	
	List<TNewnetelement> findListByTypeAndCollect(TNewnetelement tNewnetelement);
	
	String getVersionByNetIdAndDate(@Param("netid")String netid, @Param("date")String date);
	
	List<String> getParamTypeByNet(@Param("netType")String netType, @Param("version")String version);
	
	List<TNewnetelement> getNewNetelementsByType(@Param("netType")String netType);

	long findPoolidByNetName(@Param("mmeNetName")String mmeNetName);

	TNewnetelement findNetIpByNetName(@Param("saegwNetName")String saegwNetName);
	

}