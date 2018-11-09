/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.VisNodes;

/**
 * 拓扑节点管理DAO接口
 * @author zhuguangrui
 * @version 2018-03-08
 */
@MyBatisDao
public interface VisNodesDao extends CrudDao<VisNodes> {
	public int savePosition(VisNodes node);

	public String findnodeidByNidAndType(@Param("netid")String netid,@Param("netType")String netType);
}