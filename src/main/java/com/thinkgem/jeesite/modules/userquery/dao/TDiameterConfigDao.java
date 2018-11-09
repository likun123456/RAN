/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.userquery.entity.TDiameterConfig;

/**
 * 信令追踪DIAMETER端口设置DAO接口
 * @author 王晶石
 * @version 2017-07-31
 */
@MyBatisDao
public interface TDiameterConfigDao extends CrudDao<TDiameterConfig> {
	
}