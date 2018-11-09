package com.thinkgem.jeesite.modules.performance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.dao.TIndexSettingDao;
import com.thinkgem.jeesite.modules.performance.entity.TIndexSettingVO;

/**
 * 首页参数设置Service
 * @author 杨海
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class TIndexSettingService extends CrudService<TIndexSettingDao, TIndexSettingVO> {

	public TIndexSettingVO get(String id) {
		return super.get(id);
	}
}