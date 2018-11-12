/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.propertycheck.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.constant.FieldConstant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.propertycheck.entity.RanPropertyEquipment;
import com.thinkgem.jeesite.modules.propertycheck.util.ReadAndAnalysisLog;
import com.thinkgem.jeesite.modules.propertycheck.dao.RanPropertyEquipmentDao;

/**
 * 资产信息核查Service
 * @author 李昆
 * @version 2018-11-09
 */
@Service
@Transactional(readOnly = true)
public class RanPropertyEquipmentService extends CrudService<RanPropertyEquipmentDao, RanPropertyEquipment> {
	@Autowired
	private RanPropertyEquipmentDao ranPropertyEquipmentDao;
//	@Autowired
//	private RanPropertyEquipment ranPropertyEquipment;
	private static Date LogDate;
	private static String SiteName;
	public RanPropertyEquipment get(String id) {
		return super.get(id);
	}
	
	public List<RanPropertyEquipment> findList(RanPropertyEquipment ranPropertyEquipment) {
		return super.findList(ranPropertyEquipment);
	}
	
	public Page<RanPropertyEquipment> findPage(Page<RanPropertyEquipment> page, RanPropertyEquipment ranPropertyEquipment) {
		return super.findPage(page, ranPropertyEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(RanPropertyEquipment ranPropertyEquipment) {
		super.save(ranPropertyEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(RanPropertyEquipment ranPropertyEquipment) {
		super.delete(ranPropertyEquipment);
	}

	@Transactional(readOnly = false)
//	@Scheduled(cron="0/30 * * * * ?")
	public void batchInsert(){
		
		// 获取第一级路径
		String logFileURL = Global.getConfig(FieldConstant.LOGFILE_URL_KEY);
		try {
			ArrayList<File> arr=ReadAndAnalysisLog.readDir(new File(logFileURL));
			for (File file : arr) {
				if(file.getName().endsWith("log")){
	                List list=ReadAndAnalysisLog.readUseLine(file.toString());
	                boolean result=ranPropertyEquipmentDao.insertBatch(list);
	                System.out.println(result);

	            }
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/*String filesURL = getFilesURL(logFileURL);
		
		//获取日志文件路径
		filesURL = getLogFilesURL(filesURL);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String substringDate = filesURL.substring(FieldConstant.SUBSTR_START_URL_DATESTR, FieldConstant.SUBSTR_END_URL_DATESTR);
		try {
			//日志创建时间
			
			LogDate=sdf.parse(substringDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 解析文件夹下所有的log文件并排除.txt文件
		String[] logsList = new File(filesURL).list();
		for (String logFileName : logsList) {
			//开始读取日志内容，到那时忽略.txt 格式
			if(logFileName.endsWith(FieldConstant.SUFFIX_LOG_FILES)){
				//文件名就是站点名
//				ranPropertyEquipment.setSitename(logFileName.substring(0, logFileName.length()-4));
				SiteName=logFileName.substring(0, logFileName.length()-4);
				File needReadLog = new File(filesURL + File.separator + logFileName);
				try(FileReader readLog = new FileReader(needReadLog);
					BufferedReader br = new BufferedReader(readLog)
				){
					//处理日志文件数据并入库
					handleLogData(br);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}*/
	}
	/**
	 * Description: 处理日志文件数据并入库
	 * @param fieldIndexMap
	 * @param br
	 * @throws IOException void
	 * @author Chenquanwei
	 * @since 2018年11月8日: 下午2:04:08
	 * Update By Chenquanwei 2018年11月8日: 下午2:04:08
	 */
	private void handleLogData(BufferedReader br) throws IOException {
		//存放实体集合
		List<RanPropertyEquipment> equipmentList = new ArrayList<>();
		RanPropertyEquipment ranPropertyEquipment=new RanPropertyEquipment();
		HashMap<String, Integer> fieldIndexMap = new HashMap<>();
		boolean startFlag = false;
		String line;
		int i = 0;
		outContinue:
		while ((line = br.readLine()) != null) {
			if(line.contains(FieldConstant.FLAG_TABLE_LINE)){
				startFlag = true;
				++ i;
				if(i > 3){
					i = 1;
				}
				continue;
			}
			if(startFlag){
				
				if(i == 1){
					getFieldToIndex(line,fieldIndexMap,FieldConstant.EQUIP_FIELDS_ARRAY);
				} else if( i == 2){
					Integer startIndex;
					Integer endIndex = null;
					for (int j = 0; j < FieldConstant.EQUIP_FIELDS_ARRAY.length - 1; j++) {
						//获取每个字段每行中对应的数据
						startIndex = fieldIndexMap.get(FieldConstant.EQUIP_FIELDS_ARRAY[j]);
						if(j == 5 && startIndex == -1){
							continue;
						}
						if(startIndex == -1){
							j += 3;
							continue;
						}
						
						endIndex = fieldIndexMap.get(FieldConstant.EQUIP_FIELDS_ARRAY[j + 1]);
						if(j == 3){
							endIndex = fieldIndexMap.get(FieldConstant.EQUIP_FIELDS_ARRAY[j + 2]);
							//第三方没有productName字段
							if(endIndex == -1){
								endIndex = fieldIndexMap.get(FieldConstant.EQUIP_FIELDS_ARRAY[j + 3]);
							}
						//处理管理对象字段下标
						} else if (j == 4 && fieldIndexMap.get(FieldConstant.EQUIP_FIELDS_ARRAY[5]) == -1){
							endIndex = fieldIndexMap.get(FieldConstant.EQUIP_FIELDS_ARRAY[0]);
						}
//						System.out.println(FieldConstant.EQUIP_FIELDS_ARRAY[j] + "对应数据" +line.substring(startIndex, endIndex).trim());
						String fieldDataStr = line.substring(startIndex, endIndex).trim();
						//剔除为空的数据
						if(j == 6 && (fieldDataStr.equals("") || fieldDataStr.length() == 0)){
							continue outContinue;
						}
						//组合实体数据
						associationEntity(ranPropertyEquipment, j, fieldDataStr);
					}
					//截取表的最后一个字段以及需要判断的字段,放入属性中并加到List中
					ranPropertyEquipment.setId(UUID.randomUUID().toString().replace("-", ""));
					//TODO 设备状态  0：无效设备，1:新设备，2：复用设备
					/**
					 * 使用序列号查询设备使用已经录入过，默认为新设备;对于复用设备，需要log日志传来一个区分标志
					 */
					ranPropertyEquipment.setStatus("1");
					
					//区分板件的类型并放入实体对应的标志字段中
					separateMO(ranPropertyEquipment);
					
					ranPropertyEquipment.setSerialnumber(line.substring(endIndex).trim());
					try {
						ranPropertyEquipment.setLogdate(LogDate);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ranPropertyEquipment.setSitename(SiteName);
					equipmentList.add(ranPropertyEquipment);
					
					
					
				} else if( i == 3){
					fieldIndexMap.clear();
					startFlag = false;
					continue;
				}
			}
		}
		for (RanPropertyEquipment ranPropertyEquipment2 : equipmentList) {
			System.out.println(ranPropertyEquipment2);
		}
		ranPropertyEquipmentDao.insertBatch(equipmentList);
	}

	/**
	 * Description: 区分不同板件的类型，放入字段对应的标志
	 * @param ranPropertyEquipment void
	 * @author Chenquanwei
	 * @since 2018年11月9日: 下午2:27:19
	 * Update By Chenquanwei 2018年11月9日: 下午2:27:19
	 */
	private void separateMO(RanPropertyEquipment ranPropertyEquipment) {
		String moStr = ranPropertyEquipment.getManagerobject();
		if(moStr.equals(FieldConstant.MO_CATEGORY_CABINET)){
			ranPropertyEquipment.setMocategory(FieldConstant.MO_FLAG_CABINET);
		} else if (moStr.equals(FieldConstant.MO_CATEGORY_BASE_BAND) || moStr.equals(FieldConstant.MO_CATEGORY_BASEBAND)){
			ranPropertyEquipment.setMocategory(FieldConstant.MO_FLAG_BASEBAND);
		} else if (moStr.contains(FieldConstant.MO_CATEGORY_SUPPORT)){
			ranPropertyEquipment.setMocategory(FieldConstant.MO_FLAG_SUPPORT);
		} else if (moStr.contains(FieldConstant.MO_CATEGORY_OPTICAL) || moStr.contains(FieldConstant.MO_CATEGORY_OPTICAL_MOUDLE)){
			ranPropertyEquipment.setMocategory(FieldConstant.MO_FLAG_OPTICAL);
		} else if (moStr.contains(FieldConstant.MO_CATEGORY_DOT)){
			ranPropertyEquipment.setMocategory(FieldConstant.MO_FLAG_DOT);
		} else {
			ranPropertyEquipment.setMocategory(FieldConstant.MO_FLAG_RRU_IRU);
		}
	}

	/**
	 * Description: 区分IRU和RRU信息（备用方法）
	 * @param moStr
	 * @return boolean
	 * @author Chenquanwei
	 * @since 2018年11月9日: 下午2:08:09
	 * Update By Chenquanwei 2018年11月9日: 下午2:08:09
	 */
	@Deprecated
	private boolean rruFlag(String moStr) {
		boolean iruFlag = false;
		boolean rruFlag = false;
		if(moStr.contains(FieldConstant.MO_CATEGORY_IRU) || (moStr.contains(FieldConstant.MO_CATEGORY_AUX_IRU) && ! moStr.contains(FieldConstant.MO_CATEGORY_OPTICAL_MOUDLE))){
			iruFlag = true;
		}
		if(moStr.contains(FieldConstant.MO_CATEGORY_AUX_RRU) || (moStr.contains(FieldConstant.MO_CATEGORY_RRU) && ! moStr.contains(FieldConstant.MO_CATEGORY_OPTICAL))){
			rruFlag = true;
		}
		return iruFlag || rruFlag;
	}

	/**
	 * Description: 将实体填充大部分数据
	 * @param ranPropertyEquipment
	 * @param j
	 * @param fieldDataStr void
	 * @author Chenquanwei
	 * @since 2018年11月8日: 下午10:42:05
	 * Update By Chenquanwei 2018年11月8日: 下午10:42:05
	 */
	private void associationEntity(RanPropertyEquipment ranPropertyEquipment, int j, String fieldDataStr) {
		switch (j) {
		case 0:
			ranPropertyEquipment.setProductiondate(fieldDataStr);break;
		case 1:
			ranPropertyEquipment.setManufacturerid(fieldDataStr);break;
		case 2:
			ranPropertyEquipment.setManufacturerid(fieldDataStr);break;
		case 3:
			ranPropertyEquipment.setNegotiatedbitrate(fieldDataStr);break;
		case 4:
			ranPropertyEquipment.setManagerobject(fieldDataStr);break;
		case 5:
			ranPropertyEquipment.setProductiondate(fieldDataStr);break;
		case 6:
			ranPropertyEquipment.setProductnumber(fieldDataStr);break;
		case 7:
			ranPropertyEquipment.setProductionrevision(fieldDataStr);break;
		case 8:
			ranPropertyEquipment.setProductiondate(fieldDataStr);break;
		case 9:
			ranPropertyEquipment.setSerialnumber(fieldDataStr);break;
		default:
			break;
		}
	}
	
	/**
	 * Description: 获取log日志中字段对应的下标
	 * @param line
	 * @param fieldIndexMap
	 * @param equipFields void
	 * @author Chenquanwei
	 * @since 2018年11月8日: 下午1:32:25
	 * Update By Chenquanwei 2018年11月8日: 下午1:32:25
	 */
	private static void getFieldToIndex(String line, HashMap<String, Integer> fieldIndexMap, String... equipFields) {
		for (String equipField : equipFields) {
			//如果没有第三方厂商，下标就为 -1
			fieldIndexMap.put(equipField, line.indexOf(equipField));
		}
	}

	/**
	 * Description: 解析后获得日志文件所在文件夹路径
	 * @param filesURL
	 * @return String
	 * @author Chenquanwei
	 * @since 2018年11月7日: 下午2:02:41
	 * Update By Chenquanwei 2018年11月7日: 下午2:02:41
	 */
	private String getLogFilesURL(String filesURL) {
		// 获取该文件夹下的日期文件夹
		String[] dateCatalogList = new File(filesURL).list();
		
		// TODO。。。。查询数据库中当前log的日期
		Date lastLogDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/**
		 * 注意：此算法读写日志的最小粒度为 天
		 * 1、关于首次读取文件，如果是数据库中没有数据，那么全部读取
		 * 2、接下来读取通过数据库中的日期来对比读取
		 */
		for (String folderDateStr : dateCatalogList) {
			Date readTime;
			try {
				readTime = sdf.parse(folderDateStr);
				if (lastLogDate.before(readTime)) {
					/**
					 * 此处需要明确是多少层文件夹，以及每层文件夹内的数量
					 */
					filesURL = filesURL + File.separator + folderDateStr;
					String[] nextList = new File(filesURL).list();
					filesURL = filesURL + File.separator + nextList[0];
					String[] filesList = new File(filesURL).list();
					filesURL = filesURL + File.separator + filesList[0];
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return filesURL;
	}

	/**
	 * Description: 转化为兼容不同平台路径
	 * @return String
	 * @author Chenquanwei
	 * @since 2018年11月7日: 
	 * Update By Chenquanwei 2018年11月7日: 
	 */
	private String getFilesURL(String url) {
		String[] tempArray = url.split("/");
		// 循环添加File.separator
		url = tempArray[0];
		for (int i = 1; i < tempArray.length; i++) {
			url = url + File.separator + tempArray[i];
		}
		return url;
	}
	
	
}