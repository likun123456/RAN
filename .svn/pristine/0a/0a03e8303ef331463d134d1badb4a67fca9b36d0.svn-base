package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TFormulaDao;
import com.thinkgem.jeesite.modules.netconfig.dao.THwStatusDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormula;
import com.thinkgem.jeesite.modules.netconfig.entity.THwStatus;

/**
 * 公式配置Service
 * @author wangjingshi
 * @version 2018-03-26
 */
@Service
@Transactional(readOnly = true)
public class THwStatusService extends CrudService<THwStatusDao, THwStatus> {
	@Autowired
	private THwStatusDao tHwStatusDao;
	
	public THwStatus get(String id) {
		return super.get(id);
	}
	
	public List<THwStatus> findList(THwStatus tHwStatus) {
		return super.findList(tHwStatus);
	}
}
