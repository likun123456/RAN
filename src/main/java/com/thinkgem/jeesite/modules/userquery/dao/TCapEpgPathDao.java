/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.userquery.entity.TCapEpgPath;

/**
 * 单用户信令追踪SAEGW池组抓包DAO接口
 * @author zhuguangrui
 * @version 2017-06-16
 */
@MyBatisDao
public interface TCapEpgPathDao extends CrudDao<TCapEpgPath> {
	
}