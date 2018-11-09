package com.thinkgem.jeesite.modules.cheat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.DetectPackage;

/**
 * 计费欺诈更新包管理DAO
 * @author zhuguangrui
 * @version 2017-08-22
 */
@MyBatisDao
public interface DetectPackageDao extends CrudDao<DetectPackage> {
}
