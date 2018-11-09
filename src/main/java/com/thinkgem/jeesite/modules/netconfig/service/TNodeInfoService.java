/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TNodeInfoDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TNodeInfo;

/**
 * 单表生成Service
 * @author zhuguangrui
 * @version 2018-03-26
 */
@Service
@Transactional(readOnly = true)
public class TNodeInfoService extends CrudService<TNodeInfoDao, TNodeInfo> {
	
	public List<TNodeInfo> findList(TNodeInfo nodeInfo) {
		return super.findList(nodeInfo);
	}
	
}