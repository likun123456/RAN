package com.thinkgem.jeesite.modules.performance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.dao.TEbmlogConfigDao;
import com.thinkgem.jeesite.modules.performance.entity.EbmXmlVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;

/**
 * EBM XML 配制管理Service
 * @author yanghai
 * @version 2017-06-22
 */
@Service
@Transactional(readOnly = true)
public class TEbmlogConfigService extends CrudService<TEbmlogConfigDao, TEbmlogStatistics> {
	
	@Autowired
	private TEbmlogConfigDao tEbmlogConfigDao;

	public TEbmlogStatistics get(String id) {
		return super.get(id);
	}
	@Transactional(readOnly = false)
	public void insertEbmXml(List<EbmXmlVO> ebmxmllist, String netid) {
		tEbmlogConfigDao.deleteEbmlogXml(netid);
		tEbmlogConfigDao.insertEbmXml(ebmxmllist,Integer.parseInt(netid));
	}
	@Transactional(readOnly = false)
	public boolean insertImeitacTranslate(Map<String, String> map) {
		tEbmlogConfigDao.deleteImeitac();
		tEbmlogConfigDao.insertImeitacTranslate(map);
		return true;
	}
}