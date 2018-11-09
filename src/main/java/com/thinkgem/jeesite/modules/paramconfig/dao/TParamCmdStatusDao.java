/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.paramconfig.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.TParamCmdStatus;

/**
 * 网元参数修改任务状态DAO接口
 * @author 王晶石
 * @version 2017-06-17
 */
@MyBatisDao
public interface TParamCmdStatusDao extends CrudDao<TParamCmdStatus> {
	
}