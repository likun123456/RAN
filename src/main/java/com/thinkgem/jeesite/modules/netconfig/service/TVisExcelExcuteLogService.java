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
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelExcuteLog;
import com.thinkgem.jeesite.modules.netconfig.dao.TVisExcelExcuteLogDao;

/**
 * 执行模板日志Service
 * @author 王晶石
 * @version 2018-04-19
 */
@Service
@Transactional(readOnly = true)
public class TVisExcelExcuteLogService extends CrudService<TVisExcelExcuteLogDao, TVisExcelExcuteLog> {
	
	@Autowired
	private TVisExcelExcuteLogDao tVisExcelExcuteLogDao;

	public TVisExcelExcuteLog get(String id) {
		return super.get(id);
	}
	
	public List<TVisExcelExcuteLog> findList(TVisExcelExcuteLog tVisExcelExcuteLog) {
		return super.findList(tVisExcelExcuteLog);
	}
	
	public Page<TVisExcelExcuteLog> findPage(Page<TVisExcelExcuteLog> page, TVisExcelExcuteLog tVisExcelExcuteLog) {
		return super.findPage(page, tVisExcelExcuteLog);
	}
	
	@Transactional(readOnly = false)
	public void save(TVisExcelExcuteLog tVisExcelExcuteLog) {
		super.save(tVisExcelExcuteLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(TVisExcelExcuteLog tVisExcelExcuteLog) {
		super.delete(tVisExcelExcuteLog);
	}

	public List<TVisExcelExcuteLog> findListByIds(String ids) {
		return tVisExcelExcuteLogDao.findListByIds(ids);
	}
	
}