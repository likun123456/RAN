/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatReport;

/**
 * 话单欺诈业务报表DAO接口
 * @author zhuguangrui
 * @version 2017-08-30
 */
@MyBatisDao
public interface CheatReportDao extends CrudDao<CheatReport> {
	
	List<Map<String,Object>> queryCheatReport(@Param("netId")String netId,@Param("reportName") String reportName,@Param("times") String times,@Param("suffix") String suffix);
	
}