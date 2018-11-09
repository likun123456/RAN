/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.TConfig;

/**
 * 抓包参数设置DAO接口
 * @author 王晶石
 * @version 2017-10-23
 */
@MyBatisDao
public interface TConfigDao extends CrudDao<TConfig> {
	
}