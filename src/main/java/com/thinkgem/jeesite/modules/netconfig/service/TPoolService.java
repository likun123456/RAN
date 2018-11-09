/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TPoolDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;

/**
 * 单表生成Service
 * @author wangjingshi
 * @version 2017-05-24
 */
@Service
@Transactional(readOnly = true)
public class TPoolService extends CrudService<TPoolDao, TPool> {
	
	@Autowired
	private TPoolDao tpoolDao;

	public TPool get(String id) {
		return super.get(id);
	}
	
	public String queryNetidsByPoolName(String poolName) {
		List<String> list = tpoolDao.queryNetidsByPoolName(poolName);
		String netids = "";
		for(int i=0;i<list.size();i++) {
			if(i==0) {
				netids = list.get(i);
			}else {
				netids += ","+list.get(i);
			}
		}
		return netids;
	}
	
	public String queryNetNamesByPoolName(String poolName) {
		List<String> list = tpoolDao.queryNetNamesByPoolName(poolName);
		String netNames = "";
		for(int i=0;i<list.size();i++) {
			if(i==0) {
				netNames = list.get(i);
			}else {
				netNames += ","+list.get(i);
			}
		}
		return netNames;
	}
	
	public String queryNetTypeByNetName(String netName) {
		return tpoolDao.queryNetTypeByNetName(netName);
	}
	
	public String queryNetidByNetName(String netName) {
		return tpoolDao.queryNetidByNetName(netName);
	};
	
	public List<TPool> findList(TPool tPool) {
		return super.findList(tPool);
	}
	
	public Page<TPool> findPage(Page<TPool> page, TPool tPool) {
		return super.findPage(page, tPool);
	}
	
	@Transactional(readOnly = false)
	public void save(TPool tPool) {
		super.save(tPool);
	}
	
	@Transactional(readOnly = false)
	public void delete(TPool tPool) {
		super.delete(tPool);
	}

	public int getPoolByName(String fpoolname) {
		TPool t = new TPool();
		t.setFpoolname(fpoolname);
		return tpoolDao.getByName(t);
	}

	public List<TPool> queryPoolListByType(String poolType) {
		List<TPool> list = tpoolDao.queryPoolListByType(poolType);
		return list;
	}

	public String findPoolNameById(String mmePoolid) {
		String name = tpoolDao.findPoolNameById(mmePoolid);
		return name;
	}
	
}