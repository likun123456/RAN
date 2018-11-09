package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.TCheatTopUserDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatTopUser;
import com.thinkgem.jeesite.modules.performance.entity.TTacSuccessRate;

/**
 * top欺诈用户流量评估
 * 
 * @author 杨海
 */
@Service
@Transactional(readOnly = true)
public class CheatTopUserService extends CrudService<TCheatTopUserDao, CheatTopUser> {
	@Autowired
	TCheatTopUserDao tCheatTopUserDao;
	public List<CheatTopUser> queryList(String startTime, String endTime, String netId, String top, int offset,
			int limit, String sortName, String sortOrder,String netName) {
		List<CheatTopUser> list = tCheatTopUserDao.queryList(startTime,endTime,netId,top,offset,limit,sortName,sortOrder,netName);
		return list;
	}

	public int queryListCount(String startTime, String endTime, String netId, String top) {
		int total = tCheatTopUserDao.queryListCount(startTime, endTime,netId,top);
		return total;
	}
	
}