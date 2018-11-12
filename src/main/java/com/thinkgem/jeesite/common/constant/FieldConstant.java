package com.thinkgem.jeesite.common.constant;
/**
 * Description:   常用字段常量
 * @author Chenquanwei 
 * @version 1.0
 * @since: 2018年11月6日: 
 * Update By Chenquanwei 2018年11月6日: 
 */
public class FieldConstant {
	
	
	//资产核查管理start
	
	//解析文件所用常量    start----------------------
	/**
	 * log日志路径常量
	 */
	public static final String LOGFILE_URL_KEY = "siteLogURL";
	
	/**
	 * 资产设备核查管理表字段
	 */
	public static final String [] EQUIP_FIELDS_ARRAY = {"manufacturerDesignation","manufacturerId","manufacturerRevision","negotiatedBitRate","MO","productName","productNumber","productRevision","productionDate","serialNumber"};
	
	/**
	 * 爱立信自己生产的设备字段，在equipFieldArray中的起始位置
	 */
	public static final int START_FIELD_POSITION = 4;
	
	/**
	 * 路径截取日期起始下标
	 */
	public static final int SUBSTR_START_URL_DATESTR = 12;
	
	/**
	 * 路径截取日期结束下标
	 */
	public static final int SUBSTR_END_URL_DATESTR = 22;
	
	/**
	 * 读取log文件后缀
	 */
	public static final String SUFFIX_LOG_FILES = ".log";
	
	/**
	 * 产品区分字段：爱立信有productName，第三方没有
	 */
	public static final String DISTINCTION_PRODUCT_FIELD = "productName";

	/**
	 * 表格区分字段，每个表格三条“======”
	 */
	public static final String FLAG_TABLE_LINE = "====================";
	
	//解析文件      end-----------------------------------
	
	//MO板件类型分类       start-------------------------
	/**
	 * 机框标志
	 */
	public static final String MO_FLAG_CABINET = "1";
	
	/**
	 * RRU/IRU标志
	 */
	public static final String MO_FLAG_RRU_IRU = "2";
	
	/**
	 * 基带板标志
	 */
	public static final String MO_FLAG_BASEBAND = "3";
	
	/**
	 * 支撑系统标志
	 */
	public static final String MO_FLAG_SUPPORT = "4";
	
	/**
	 * 光模块标志
	 */
	public static final String MO_FLAG_OPTICAL = "5";
	
	/**
	 * DOT信息标志
	 */
	public static final String MO_FLAG_DOT = "6";
	
	/**
	 * 机框判断
	 */
	public static final String MO_CATEGORY_CABINET = "Cabinet=1";
	
	/**
	 * RRU/IRU信息判断  IRU part1
	 */
	public static final String MO_CATEGORY_IRU = "FieldReplaceableUnit=IRU-";
	
	/**
	 * RRU/IRU信息判断  IRU part2 需要配合光模块不含字段 RiPort
	 */
	public static final String MO_CATEGORY_AUX_IRU = "AuxPlugInUnit=IRU-";
	
	/**
	 * RRU/IRU信息判断   RRU part1
	 */
	public static final String MO_CATEGORY_AUX_RRU = "AuxPlugInUnit=RRU-";
	
	/**
	 * RRU/IRU信息判断   RRU part2 需要配合光模块 不含字段 SfpModule
	 */
	public static final String MO_CATEGORY_RRU = "FieldReplaceableUnit=RRU-";
	
	/**
	 * 光模块判断 part1
	 */
	public static final String MO_CATEGORY_OPTICAL = ",SfpModule=";
	
	/**
	 * 光模块 判断part2
	 */
	public static final String MO_CATEGORY_OPTICAL_MOUDLE = ",RiPort=";
	
	/**
	 * 基带板信息判断 part1  equals校验
	 */
	public static final String MO_CATEGORY_BASEBAND = "FieldReplaceableUnit=1";
	
	/**
	 * 基带板信息判断 part2  equals校验
	 */
	public static final String MO_CATEGORY_BASE_BAND = "Subrack=1,Slot=1";
	
	/**
	 * 支撑系统判断
	 */
	public static final String MO_CATEGORY_SUPPORT = "=SUP";
	
	
	/**
	 * DOT信息判断
	 */
	public static final String MO_CATEGORY_DOT = "=RD-";
	//MO板件类型分类     end----------------------------------------
	
	
	
	
	
}
