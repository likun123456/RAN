/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.userquery.entity.TCapPcrfPath;

/**
 * 单用户信令追踪PCRF抓包DAO接口
 * @author zhuguangrui
 * @version 2017-06-16
 */
@MyBatisDao
public interface TCapPcrfPathDao extends CrudDao<TCapPcrfPath> {

	List<TCapPcrfPath> queryPcrfInfoById(@Param("p_id")String p_id);
	
}