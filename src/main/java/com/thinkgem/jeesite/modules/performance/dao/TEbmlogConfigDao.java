package com.thinkgem.jeesite.modules.performance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.performance.entity.EbmXmlVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;

/**
 * EBM XML配制管理
 * @author yanghai
 * @version 2017-06-22
 */
@MyBatisDao
public interface TEbmlogConfigDao extends CrudDao<TEbmlogStatistics> {

	void insertEbmXml(@Param("ebmxmllist")List<EbmXmlVO> ebmxmllist, @Param("netid")int netid);

	void insertImeitacTranslate(@Param("imeiTacmap")Map<String, String> map);
	
	void deleteEbmlogXml(@Param("netid")String netid);
	
	void deleteImeitac();
}