/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckConfig;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoTemplateCheckConfig;

/**
 * 自动巡检配置DAO接口
 * @author zhuguangrui
 * @version 2017-08-05
 */
@MyBatisDao
public interface TAutoTemplateCheckConfigDao extends CrudDao<TAutoTemplateCheckConfig> {
	
	public int deleteAll();
	
}