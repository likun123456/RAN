package com.thinkgem.jeesite.modules.netconfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TFormulaScriptsCommandDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormulaScriptsCommands;

@Service
@Transactional(readOnly = true)
public class TFormulaScriptsCommandsService extends CrudService<TFormulaScriptsCommandDao,TFormulaScriptsCommands> {
	
	public TFormulaScriptsCommands get(String id) {
		return super.get(id);
	}
	
	public Page<TFormulaScriptsCommands> findPage(Page<TFormulaScriptsCommands> page, TFormulaScriptsCommands scripts) {
		return super.findPage(page, scripts);
	
	}
	
	@Transactional(readOnly = false)
	public void save(TFormulaScriptsCommands tFormulaScripts) {
		super.save(tFormulaScripts);
	}
	
	@Transactional(readOnly = false)
	public void delete(TFormulaScriptsCommands tFormulaScripts) {
		super.delete(tFormulaScripts);
	}
	
}
