package com.thinkgem.jeesite.modules.performance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.dao.TIndexKpiDao;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpi;

/**
 * 首页参数设置Service
 * @author 杨海
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class TIndexKpiService extends CrudService<TIndexKpiDao, TIndexKpi> {

	public TIndexKpi get(String id) {
		return super.get(id);
	}
}