/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormula;
import com.thinkgem.jeesite.modules.netconfig.dao.TFormulaDao;

/**
 * 公式配置Service
 * @author liuliang
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class TFormulaService extends CrudService<TFormulaDao, TFormula> {
	@Autowired
	private TFormulaDao tFormulaDao;
	public TFormula get(String id) {
		return super.get(id);
	}
	
	public List<TFormula> findList(TFormula tFormula) {
		return super.findList(tFormula);
	}
	
	public Page<TFormula> findPage(Page<TFormula> page, TFormula tFormula) {
		return super.findPage(page, tFormula);
	}
	
	@Transactional(readOnly = false)
	public void save(TFormula tFormula) {
		super.save(tFormula);
	}
	
	@Transactional(readOnly = false)
	public void delete(TFormula tFormula) {
		super.delete(tFormula);
	}
	
	public String queryFormulaNameByKpi(String kpi) {
		return tFormulaDao.queryFormulaNameByKpi(kpi);
	}
	
	public String queryCycleTime() {
		return tFormulaDao.queryCycleTime();
	}	
	
	public String rebulidFormula(String formula) {

		String[] mark = { "/", "-", "+", "*", "^", "(", ")" };

		String stemp = formula.replace("*100%", "");
		for (String m : mark) {

			stemp = stemp.replace(m, "@" + m + "@");

		}
		String result = stemp.replace(" ", "").replace("@@", "@");
		if (result.charAt(0) == '@') {
			result = result.substring(1);
		}
		if (result.charAt(result.length() - 1) == '@') {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	
}