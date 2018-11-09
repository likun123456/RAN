/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TOss;

/**
 * OSS节点基本信息操作DAO接口
 * @author 王晶石
 * @version 2017-05-27
 */
@MyBatisDao
public interface TOssDao extends CrudDao<TOss> {

	int getByName(TOss t);
	
}