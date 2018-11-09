/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.userquery.entity.TUserSignalHostConfig;

/**
 * 信令追踪hostDAO接口
 * @author chenhongbo
 * @version 2017-07-13
 */
@MyBatisDao
public interface TUserSignalHostConfigDao extends CrudDao<TUserSignalHostConfig> {
	void batchIntert(List<TUserSignalHostConfig> list);
}