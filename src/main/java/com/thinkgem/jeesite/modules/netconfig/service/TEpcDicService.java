package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TEpcDicDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TEpcDic;

/**
 * TEpcDic管理Service
 * @author yanghai
 * @version 2018-05-31
 */
@Service
@Transactional(readOnly = true)
public class TEpcDicService extends CrudService<TEpcDicDao, TEpcDic> {

	public TEpcDic get(String id) {
		return super.get(id);
	}
	
	public List<TEpcDic> findList(TEpcDic tEpcDic) {
		return super.findList(tEpcDic);
	}
	
	public Page<TEpcDic> findPage(Page<TEpcDic> page, TEpcDic tEpcDic) {
		return super.findPage(page, tEpcDic);
	}
	
	@Transactional(readOnly = false)
	public void save(TEpcDic tEpcDic) {
		super.save(tEpcDic);
	}
	
	@Transactional(readOnly = false)
	public void delete(TEpcDic tEpcDic) {
		super.delete(tEpcDic);
	}
	
}