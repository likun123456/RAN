package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TFormulaScriptsCommandDao;
import com.thinkgem.jeesite.modules.netconfig.dao.TFormulaScriptsDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormulaScripts;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormulaScriptsCommands;

@Service
@Transactional(readOnly = true)
public class TFormulaScriptsService extends CrudService<TFormulaScriptsDao, TFormulaScripts>  {
	
	@Autowired
	private TFormulaScriptsDao scriptsDao;
	
	@Autowired
	private TFormulaScriptsCommandsService tFormulaScriptsCommandsService;
	
	
	public TFormulaScripts get(String id) {
		return super.get(id);
	}
	
	public Page<TFormulaScripts> findPage(Page<TFormulaScripts> page, TFormulaScripts scripts) {
		return super.findPage(page, scripts);
	
	}
	
	@Transactional(readOnly = false)
	public void save(TFormulaScripts tFormulaScripts) {
		super.save(tFormulaScripts);
		String id = tFormulaScripts.getId();
		List<TFormulaScriptsCommands> list = tFormulaScripts.getCommandsList();
		long order = 1;
		for(TFormulaScriptsCommands c : list) {
			if(null != c.getCommand() && !"".equals(c.getCommand())) {
				c.setOrderNum(order);
				c.setScriptsId(Integer.parseInt(id));
				tFormulaScriptsCommandsService.save(c);
				order ++;
			} else {
				tFormulaScriptsCommandsService.delete(c);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TFormulaScripts tFormulaScripts) {
		super.delete(tFormulaScripts);
	}
	
	public int getScriptsByName(String name) {
		TFormulaScripts t = new TFormulaScripts();
		t.setName(name);
		return scriptsDao.getByName(t);
	}
}
