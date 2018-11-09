/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.dao;

import java.util.List;
import java.util.Map.Entry;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.userquery.entity.TEbmSingleSearch;

/**
 * 单用户EBM查询DAO接口
 * @author 王晶石
 * @version 2017-06-17
 */
@MyBatisDao
public interface TEbmSingleSearchDao extends CrudDao<TEbmSingleSearch> {

	void truncateEbmSingleSearchTable();

	void insertEbmSingleSearchTable(@Param("list")List<TEbmSingleSearch> list);

	List<TEbmSingleSearch> queryEbmSingleSearchTable(@Param("offset")int offset,@Param("limit")int limit, 
			                   @Param("sortName")String sortName, @Param("sortOrder")String sortOrder,@Param("nType")String nType,
			                   @Param("eventType")String eventType,@Param("eventResult")String eventResult);
	int queryListCount(@Param("nType")String nType,@Param("eventType")String eventType,@Param("eventResult")String eventResult);

	String queryEbmLogById(@Param("id")int id);

	String queryCcdes(@Param("cc")String cc);

	TEbmSingleSearch querySccdesAndAction(@Param("scc")String scc);
	
}