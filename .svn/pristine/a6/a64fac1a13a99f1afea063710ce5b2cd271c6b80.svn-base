package com.thinkgem.jeesite.modules.netconfig.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class ExcelTemplate  {
	
	private static final long serialVersionUID = -500284215400513597L;
	// excelId - moduleId - detailId
	private String id;
	private String parentId;
	private String name;
	private String type;
	private String templatetype;
	public String getTemplatetype() {
		return templatetype;
	}


	public void setTemplatetype(String templatetype) {
		this.templatetype = templatetype;
	}

	private int sort;
	
	private String excelId;
	private String excelName;
	
	private String moduleId;
	private String moduleName;
	private String moduleType;
	private String netType;
	
	
	private int detailId;
	private int executeNo;
	private int paramType;
	private String commandName;
	private String formRemark;
	private String debugRemark;
	private String confCmd;
	private String beforePrompt;
	private String afterPrompt;
	private String checkRegexp;
	private String timeout;
	private String errorHandleMode;
	private String varArrayRegexp;
	private String varArray;
	
	private String level;
	
	public static List<ExcelTemplate>  sortList(List<TVisExcel> visExcelList,List<TVisExcelModule> tVisExcelModuleList,List<TVisExcelModuleDetail> TVisExcelModuleDetailList){
		
		List<ExcelTemplate> liuliangList = Lists.newArrayList();
		for(TVisExcel tvisExcel : visExcelList) {
			//添加Excel
			ExcelTemplate l = new ExcelTemplate();
			l.setId(tvisExcel.getId());
			l.setParentId("0");
			l.setType(tvisExcel.getType());
			l.setTemplatetype(tvisExcel.getTemplatetype());
			l.setName(tvisExcel.getName());
			l.setSort(tvisExcel.getSort());
			l.setLevel("1");
			//l.setExcelId(tvisExcel.getId());
			//l.setExcelName(tvisExcel.getName());
			liuliangList.add(l);
			for(TVisExcelModule tVisExcelModule : tVisExcelModuleList) {
				
				if(tvisExcel.getId().equals(String.valueOf(tVisExcelModule.getExcelId()))) {
					
					//添加模块
					ExcelTemplate l_m = new ExcelTemplate();
					l_m.setId(l.getId()+"_"+tVisExcelModule.getId());
					l_m.setParentId(l.getId());
					//l_m.setModuleName(tVisExcelModule.getModuleName());
					l_m.setName(tVisExcelModule.getModuleName());
					l_m.setSort(tVisExcelModule.getSort());
					l_m.setModuleType(tVisExcelModule.getModuleType());
					l_m.setNetType(tVisExcelModule.getNetType());
					l_m.setLevel("2");
					liuliangList.add(l_m);
					for(TVisExcelModuleDetail tVisExcelModuleDetail: TVisExcelModuleDetailList) {
						
						if(tVisExcelModule.getExcelId()==tVisExcelModuleDetail.getExcelId()&&tVisExcelModule.getId().equals(String.valueOf(tVisExcelModuleDetail.getModuleId()))) {
							
							//添加明细记录
							ExcelTemplate l_d = new ExcelTemplate();
							l_d.setId(l_m.getId()+"_" + tVisExcelModuleDetail.getId());
							l_d.setParentId(l_m.getId());
							l_d.setName(tVisExcelModuleDetail.getCommandName());
							l_d.setSort(tVisExcelModuleDetail.getExecuteNo());
							l_d.setConfCmd(tVisExcelModuleDetail.getConfCmd());
							l_d.setLevel("3");
							liuliangList.add(l_d);
							
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return liuliangList;
	}
	
	
	public int getSort() {
		return sort;
	}


	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getExcelId() {
		return excelId;
	}

	public void setExcelId(String excelId) {
		this.excelId = excelId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public int getExecuteNo() {
		return executeNo;
	}
	public void setExecuteNo(int executeNo) {
		this.executeNo = executeNo;
	}
	public int getParamType() {
		return paramType;
	}
	public void setParamType(int paramType) {
		this.paramType = paramType;
	}
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	public String getFormRemark() {
		return formRemark;
	}
	public void setFormRemark(String formRemark) {
		this.formRemark = formRemark;
	}
	public String getDebugRemark() {
		return debugRemark;
	}
	public void setDebugRemark(String debugRemark) {
		this.debugRemark = debugRemark;
	}
	public String getConfCmd() {
		return confCmd;
	}
	public void setConfCmd(String confCmd) {
		this.confCmd = confCmd;
	}
	public String getBeforePrompt() {
		return beforePrompt;
	}
	public void setBeforePrompt(String beforePrompt) {
		this.beforePrompt = beforePrompt;
	}
	public String getAfterPrompt() {
		return afterPrompt;
	}
	public void setAfterPrompt(String afterPrompt) {
		this.afterPrompt = afterPrompt;
	}
	public String getCheckRegexp() {
		return checkRegexp;
	}
	public void setCheckRegexp(String checkRegexp) {
		this.checkRegexp = checkRegexp;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getErrorHandleMode() {
		return errorHandleMode;
	}
	public void setErrorHandleMode(String errorHandleMode) {
		this.errorHandleMode = errorHandleMode;
	}
	public String getVarArrayRegexp() {
		return varArrayRegexp;
	}
	public void setVarArrayRegexp(String varArrayRegexp) {
		this.varArrayRegexp = varArrayRegexp;
	}
	public String getVarArray() {
		return varArray;
	}
	public void setVarArray(String varArray) {
		this.varArray = varArray;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
