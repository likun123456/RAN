package com.thinkgem.jeesite.modules.netconfig.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TEpcDic;

/**
 * EPCDIC节点管理DAO接口
 * @author yanghai
 * @version 2018-05-31
 */
@MyBatisDao
public interface TEpcDicDao extends CrudDao<TEpcDic> {
	
}