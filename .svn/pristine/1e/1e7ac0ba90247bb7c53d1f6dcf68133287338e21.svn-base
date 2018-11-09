/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.userquery.entity.TCapAllPath;

/**
 * 单用户信令追踪DAO接口
 * @author zhuguangrui
 * @version 2017-06-15
 */
@MyBatisDao
public interface TCapAllPathDao extends CrudDao<TCapAllPath> {

	List<TCapAllPath> queryPcrfList(@Param("id")String id);
	
}