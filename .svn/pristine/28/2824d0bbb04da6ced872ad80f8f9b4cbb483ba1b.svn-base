package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.CheatAnalysisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatAnalysis;
import com.thinkgem.jeesite.modules.cheat.entity.CheatRatingGroup;
/**
 * 计费欺诈防控分析Service
 * @author wangjingshi
 * @version 2017-08-29
 */
@Service
@Transactional(readOnly = true)
public class CheatAnalysisService extends CrudService<CheatAnalysisDao, CheatAnalysis>  {
	
	@Autowired
	private CheatAnalysisDao cheatAnalysisDao;

	public List<CheatAnalysis> queryList(String date, String startTime, String endTime, String netid,
			int freePrecentThreshold, int freeThreshold, int offset, int limit) {
		List<CheatAnalysis> list = cheatAnalysisDao.queryList(date,startTime,endTime,netid,freePrecentThreshold,freeThreshold,
				offset,limit);
		return list;
	}

	public int queryListCount(String date, String startTime, String endTime, String netid,
			int freePrecentThreshold, int freeThreshold) {
		int count = cheatAnalysisDao.queryListCount(date,startTime,endTime,netid,freePrecentThreshold,freeThreshold);
		return count;
	}

	public List<CheatRatingGroup> queryCheatRatingGroupList(String date, String startTime, String endTime,
			String netid, String imsi) {
		List<CheatRatingGroup> list = cheatAnalysisDao.queryCheatRatingGroupList(date,startTime,endTime,netid,imsi);
		return list;
	}

}
