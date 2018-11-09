/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TLockTemplate;

/**
 * 闭锁模版管理
 * @author 杨海
 * @version 2018-4-25
 */
@MyBatisDao
public interface TLockTemplateDao extends CrudDao<TLockTemplate> {
	
}