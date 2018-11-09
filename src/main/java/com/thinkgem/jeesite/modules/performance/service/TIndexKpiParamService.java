package com.thinkgem.jeesite.modules.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.dao.TIndexKpiParamDao;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpiParam;

/**
 * 首页参数设置Service
 * 
 * @author 杨海
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class TIndexKpiParamService extends CrudService<TIndexKpiParamDao, TIndexKpiParam> {

	public TIndexKpiParam get(String id) {
		return super.get(id);
	}

	public Map<String, TIndexKpiParam> getKpiParamMap() {
		Map<String, TIndexKpiParam> kpiParamMap = new HashMap<String, TIndexKpiParam>();
		List<TIndexKpiParam> kpiParamList = super.findList(new TIndexKpiParam());
		for (int i = 0; i < kpiParamList.size(); i++) {
			kpiParamMap.put(kpiParamList.get(i).getKpi(), kpiParamList.get(i));
		}
		return kpiParamMap;
	}
}