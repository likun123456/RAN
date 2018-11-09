/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TNewnetelementDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;

/**
 * 网元基本信息操作Service
 * @author 王晶石
 * @version 2017-05-25
 */
@Service
@Transactional(readOnly = true)
public class TNewnetelementService extends CrudService<TNewnetelementDao, TNewnetelement> {
	
	@Autowired
	private TNewnetelementDao tNewnetelementDao;

	public TNewnetelement get(String id) {
		return super.get(id);
	}
	
	public List<TNewnetelement> findList(TNewnetelement tNewnetelement) {
		return super.findList(tNewnetelement);
	}
	
	public Page<TNewnetelement> findPage(Page<TNewnetelement> page, TNewnetelement tNewnetelement) {
		return super.findPage(page, tNewnetelement);
	}
	
	@Transactional(readOnly = false)
	public void save(TNewnetelement tNewnetelement) {
		super.save(tNewnetelement);
	}
	
	@Transactional(readOnly = false)
	public void delete(TNewnetelement tNewnetelement) {
		super.delete(tNewnetelement);
	}

	public int getNetByName(String fname) {
		TNewnetelement t = new TNewnetelement();
		t.setFname(fname);
		return tNewnetelementDao.getByName(t);
	}

	
	public List<TNewnetelement> findListByServiceType(TNewnetelement tNewnetelement) {
		List<TNewnetelement> list = tNewnetelementDao.findListByServiceType(tNewnetelement);
		return list;
	}
	
	public List<TNewnetelement> findListByTypeAndCollect(TNewnetelement tNewnetelement) {
		List<TNewnetelement> list = tNewnetelementDao.findListByTypeAndCollect(tNewnetelement);
		return list;
	}
	
	public String getVersionByNetIdAndDate(String netid, String date) {
		return tNewnetelementDao.getVersionByNetIdAndDate(netid, date);
	}
	
	public List<String> getParamTypeByNet(String netType,String version) {
		return tNewnetelementDao.getParamTypeByNet(netType, version);
	}
	
	public List<TNewnetelement> getNewNetelementsByType(String netType) {
		return tNewnetelementDao.getNewNetelementsByType(netType);
	}

	public long findPoolidByNetName(String mmeNetName) {
		return tNewnetelementDao.findPoolidByNetName(mmeNetName);
	}

	public TNewnetelement findNetIpByNetName(String saegwNetName) {
		return tNewnetelementDao.findNetIpByNetName(saegwNetName);
	}
	
}