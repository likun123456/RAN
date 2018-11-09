package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Demo2;
@MyBatisDao
public interface Demo2Dao {

	public List<Demo2> findList();
}
