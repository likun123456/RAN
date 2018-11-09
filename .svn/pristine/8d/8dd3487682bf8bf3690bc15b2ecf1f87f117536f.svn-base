/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatHistory;


/**
 * 计费欺诈抓包历史记录DAO接口
 * @author zhuguangrui
 * @version 2017-09-04
 */
@MyBatisDao
public interface CheatHistoryDao extends CrudDao<CheatHistory> {
	
	public List<CheatHistory> queryListByImsi(@Param("netid")String netid,@Param("imsi") String imsi,
			@Param("beginDate")String beginDate,@Param("endDate") String endDate,@Param("date") String date);
	
	public List<CheatHistory> queryFlowList(@Param("netid")String netid,@Param("imsi")String imsi, 
			@Param("datetime")String datetime,@Param("date")String date,@Param("cheatCase") String cheatCase);

	public String queryCapName(@Param("datetime")String datetime,@Param("imsi") String imsi,@Param("ip") String ip);
	
}