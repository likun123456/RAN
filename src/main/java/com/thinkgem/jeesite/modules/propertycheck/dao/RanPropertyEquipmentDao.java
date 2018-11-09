/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.propertycheck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.propertycheck.entity.RanPropertyEquipment;

/**
 * 资产信息核查DAO接口
 * @author 李昆
 * @version 2018-11-09
 */
@MyBatisDao
public interface RanPropertyEquipmentDao extends CrudDao<RanPropertyEquipment> {
	boolean insertBatch(List<RanPropertyEquipment> list);
	
}