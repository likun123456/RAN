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
import com.thinkgem.jeesite.modules.netconfig.entity.TCg;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.dao.TCgDao;

/**
 * 单表生成Service
 * @author wangjingshi
 * @version 2017-05-25
 */
@Service
@Transactional(readOnly = true)
public class TCgService extends CrudService<TCgDao, TCg> {
	
	@Autowired
	private TCgDao tcgDao;

	public TCg get(String id) {
		return super.get(id);
	}
	
	public List<TCg> findList(TCg tCg) {
		return super.findList(tCg);
	}
	
	public Page<TCg> findPage(Page<TCg> page, TCg tCg) {
		return super.findPage(page, tCg);
	}
	
	@Transactional(readOnly = false)
	public void save(TCg tCg) {
		super.save(tCg);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCg tCg) {
		super.delete(tCg);
	}

	public int getCgByName(String fname) {
		TCg t = new TCg();
		t.setFname(fname);
		return tcgDao.getByName(t);
	}
	
}