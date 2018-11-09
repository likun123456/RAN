/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatLog;

/**
 * 计费欺诈日志DAO接口
 * @author zhuguangrui
 * @version 2017-09-06
 */
@MyBatisDao
public interface CheatLogDao extends CrudDao<CheatLog> {
	
}