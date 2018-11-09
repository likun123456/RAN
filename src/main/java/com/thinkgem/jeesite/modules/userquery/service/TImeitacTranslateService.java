/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.userquery.entity.TImeitacTranslate;
import com.thinkgem.jeesite.modules.userquery.dao.TImeitacTranslateDao;

/**
 * 手机型号翻译Service
 * @author zhuguangrui
 * @version 2017-07-07
 */
@Service
@Transactional(readOnly = true)
public class TImeitacTranslateService extends CrudService<TImeitacTranslateDao, TImeitacTranslate> {

	public TImeitacTranslate get(String id) {
		return super.get(id);
	}
	
	public List<TImeitacTranslate> findList(TImeitacTranslate tImeitacTranslate) {
		return super.findList(tImeitacTranslate);
	}
	
	public Page<TImeitacTranslate> findPage(Page<TImeitacTranslate> page, TImeitacTranslate tImeitacTranslate) {
		return super.findPage(page, tImeitacTranslate);
	}
	
	@Transactional(readOnly = false)
	public void save(TImeitacTranslate tImeitacTranslate) {
		super.save(tImeitacTranslate);
	}
	
	@Transactional(readOnly = false)
	public void delete(TImeitacTranslate tImeitacTranslate) {
		super.delete(tImeitacTranslate);
	}
	
}