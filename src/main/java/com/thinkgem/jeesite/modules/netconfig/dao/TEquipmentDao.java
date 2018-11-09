/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TEquipment;

/**
 * 拓扑设备管理DAO接口
 * @author yh
 * @version 2018-03-08
 */
@MyBatisDao
public interface TEquipmentDao extends CrudDao<TEquipment> {
	
}