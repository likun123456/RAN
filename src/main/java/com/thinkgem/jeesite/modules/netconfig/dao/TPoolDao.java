/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;

/**
 * 单表生成DAO接口
 * @author wangjingshi
 * @version 2017-05-24
 */
@MyBatisDao
public interface TPoolDao extends CrudDao<TPool> {

	int getByName(TPool pool);

	List<TPool> queryPoolListByType(String poolType);

	String findPoolNameById(String poolid);
	
	List<String> queryNetidsByPoolName(String poolName);
	
	List<String> queryNetNamesByPoolName(String poolName);

	String queryNetTypeByNetName(String netName);
	
	String queryNetidByNetName(String netName);
	
}