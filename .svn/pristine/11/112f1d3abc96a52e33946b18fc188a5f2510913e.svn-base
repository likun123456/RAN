package com.thinkgem.jeesite.modules.paramconfig.service;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csvreader.CsvReader;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.paramconfig.dao.ParamPackageManagerDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamPackage;

@Service
@Transactional(readOnly = true)
public class ParamPackageManagerService extends CrudService<ParamPackageManagerDao, ParamPackage>  {
	
	@Autowired
	private ParamPackageManagerDao paramPackageManagerDao;
	
	public Page<ParamPackage> findPage(Page<ParamPackage> page, ParamPackage scripts) {
		return super.findPage(page, scripts);
	
	}
	
	@Transactional(readOnly = false)
	public void readParamCSV(String string, String packageName)
			throws Exception {
		List<File> csvs = getData(string);
		for(File csv : csvs){
			String tableRemark = csv.getName().split("_")[0];
			String mia = csv.getName().split("_")[1];
			List<ParamData> listParam = readCSV(csv, mia);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", listParam); 
			map.put("tableRemark", tableRemark);
			paramPackageManagerDao.deleteDictionaryByVersion(tableRemark, mia);
			paramPackageManagerDao.batchIntertDictionary(map);
			
			ParamPackage paramPackage = new ParamPackage();
			paramPackage.setNettype(tableRemark);
			paramPackage.setPackagename(packageName);
			paramPackage.setVersion(mia);
			paramPackage.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			paramPackageManagerDao.insert(paramPackage);
			//paramPackageManagerDao.save(listParam, mia, tableRemark, packageName);
		}
		
	}
	
	/**
	 * 获取目录下符合.csv条件的文件
	 * 
	 * @param path
	 * @return
	 */
	private static List<File> getData(String path) {
		File f = new File(path);
		List<File> csvFile = new ArrayList<File>();
		if (f.isDirectory()) {
			File[] fs = f.listFiles();
			for (int i = 0; i < fs.length; i++) {
				if (fs[i].getName().endsWith(".csv")) {
					csvFile.add(fs[i]);
				}
			}
		}
		return csvFile;
	}
	
	public static List<ParamData> readCSV(File f, String mia)
			throws Exception {
		List<ParamData> listParam = new ArrayList<ParamData>();
		CsvReader r = new CsvReader(f.getAbsolutePath(), ',', Charset
				.forName("GBK"));
		// 读取表头
		r.readHeaders();
		// 逐条读取记录，直至读完
		while (r.readRecord()) {
			ParamData paramDataVO = new ParamData();
			if (null != r.get("参数标识") && !r.get("参数标识").trim().equals("")) {
				// 按列名读取这条记录的值
				paramDataVO.setVersion(mia);
				paramDataVO.setParamid(r.get("参数标识"));
				if(r.get("参数标识").contains("(") && r.get("参数标识").contains(")")){
					paramDataVO.setParamidextend(r.get("参数标识").substring(r.get("参数标识").indexOf("(")+1, r.get("参数标识").indexOf(")")));
				}
				paramDataVO.setCnname(r.get("参数中文名称"));
				paramDataVO.setName(r.get("参数名称"));
				paramDataVO.setType(r.get("参数类型"));
				paramDataVO.setDescription(r.get("参数说明"));
				paramDataVO.setEndescription(r.get("参数英文说明"));
				paramDataVO.setApplyrange(r.get("参数适用范围"));
				paramDataVO.setValuerange(r.get("参数取值范围"));
				paramDataVO.setMemo(r.get("备注"));
				paramDataVO.setDefaultvalue(r.get("参数默认取值"));
				paramDataVO.setSuggestvalue(r.get("参数建议取值"));
				paramDataVO.setParamvalue(r.get("现网取值"));
				paramDataVO.setViewcmd(r.get("提取方法"));
				paramDataVO.setModcmd(r.get("修改方法"));
				listParam.add(paramDataVO);
			}
		}
		r.close();
		return listParam;
	}
	
}
