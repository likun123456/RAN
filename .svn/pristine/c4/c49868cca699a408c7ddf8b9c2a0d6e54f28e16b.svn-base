/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.collectset.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;
import com.thinkgem.jeesite.modules.collectset.entity.TElementcollect;

/**
 * 采集配置DAO接口
 * @author yanghai
 * @version 2017-05-25
 */
@MyBatisDao
public interface TElementcollectDao extends CrudDao<TElementcollect> {
	public void deleteHistory(TCollectordeploy tCollectordeploy);
	public void insertCollect(TCollectordeploy tCollectordeploy);
	public List<TElementcollect> findCollectorIp(TElementcollect telementcollect);
	public String getCollectorIdByIp(String ip);
}