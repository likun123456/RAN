package com.thinkgem.jeesite.modules.netconfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TVisExcelModuleDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;

@Service
@Transactional(readOnly = true)
public class TVisExcelModuleService extends CrudService<TVisExcelModuleDao, TVisExcelModule> {
	@Autowired
	private TVisExcelModuleDao tVisExcelModuleDao;
	@Transactional(readOnly = false)
	public int saveTVisExcelModule(TVisExcelModule tVisExcelModule) {
		return tVisExcelModuleDao.insert(tVisExcelModule);
	}
	@Transactional(readOnly = false)
	public void updateSort(String id,int sort) {
		tVisExcelModuleDao.updateSort(id, sort);
	}
}
