package com.thinkgem.jeesite.modules.propertycheck.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.constant.FieldConstant;
import com.thinkgem.jeesite.modules.propertycheck.entity.RanPropertyEquipment;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReadAndAnalysisLog {
//	private RanPropertyEquipment ranPropertyEquipment;
    private static int  serialNumber;
    private static String siteName;
    private static int managerObject;
    private static int productName;
    private static int productNumber;
    private static int productResivion;
    private static int productionDate;
    private static int manufactureId;
    private static int manufactureRevision;
    private static int negotiaedBitRate;
    private static String status;
    private static String logDate;
    static ArrayList<File> filePathArr=new ArrayList<File>();
    public static ArrayList<File> readDir(File filePath) throws  Exception{
        File[] files=filePath.listFiles();
        for (File f:files) {
            if(f.isDirectory()){
                readDir(f);
            }
            if (f.isFile()){
                filePathArr.add(f);

            }
        }
            return filePathArr;

    }


    /**
     * 读取一个站站点的数据
     * @param fileName
     * @throws Exception
     */
    public static List<RanPropertyEquipment> readUseLine(String fileName) throws  Exception{
        List<RanPropertyEquipment> ranPropertyEquipmentsList=new ArrayList<>();
    	TreeMap<Integer,String> map=new TreeMap<Integer, String>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });//存取每行文本的集合，键为行数，值为每行内容
        TreeMap<Integer,String> map2=new TreeMap<Integer, String>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });//存取每行文本的集合，键为行数，值为每行内容
        Map<Integer,Integer> map1=new HashMap<Integer,Integer>();//存储爱立信的表的始末行数
        Map<Integer,Integer> map3=new HashMap<Integer, Integer>();//存取第三方的表的始末行数
        InputStream inputStream;
        LineNumberReader lineNumberReader;
        inputStream=new FileInputStream(new File(fileName));
        lineNumberReader=new LineNumberReader(new InputStreamReader(inputStream));
        String line;
        int startline=0;//起始行
        int lastline=0;//结束行

        while((line=lineNumberReader.readLine())!=null){
            map.put(lineNumberReader.getLineNumber(),line);//存储所有行到map集合
        }
        String[] strs=map.get(1).split("/");
        siteName=strs[strs.length-1].split("\\.")[0];
        logDate=(strs[strs.length-4]);

        for (Map.Entry<Integer,String> entry:map.entrySet()) {//遍历文本
            if(entry.getValue().contains("serialNumber")) {//
                startline = entry.getKey();//做一个开始行的标记
            }
            if (entry.getValue().contains("Total")){
                lastline = entry.getKey();
                if (startline!=0)
                map1.put(startline,lastline);
            }
        }
//        存储有用的数据到map2
        for (Map.Entry<Integer,Integer> en:map1.entrySet()) {
            for (Map.Entry<Integer,String> m:map.entrySet()) {
                if(m.getKey()>=en.getKey()&&m.getKey()<=en.getValue()){
                    map2.put(m.getKey(),m.getValue());
                }
            }
        }
//        将map1填入爱立信自己的产品，map3填入第三方产品
        for (Map.Entry<Integer, String> m :map2.entrySet()){
            if(m.getValue().contains("manufacturerDesignation")){
                map3.put(m.getKey(),map1.get(m.getKey()));
                map1.remove(m.getKey());
            }
        }

//          根据表头的规则截取数据列，爱立信自己的
        for (Map.Entry<Integer,Integer> e0:map1.entrySet()){//遍历始末行行标
            for (Map.Entry<Integer,String> e1:map2.entrySet()) {
                if(e1.getKey()>=e0.getKey()&&e1.getKey()<=e0.getValue()){//遍历始>末行的内容

                    if (e1.getValue().contains("serialNumber")&&!e1.getValue().contains("manufacturerDesignation")){
                        managerObject=e1.getValue().indexOf("productName");
                        productName=e1.getValue().indexOf("productNumber");
                        productNumber=e1.getValue().indexOf("productRevision");
                        productResivion=e1.getValue().indexOf("productionDate");
                        productionDate=e1.getValue().indexOf("serialNumber");
                        serialNumber=e1.getValue().length();
                    }
                    if(!e1.getValue().contains("Total")&&!e1.getValue().contains("==")&&!e1.getValue().contains("MO")){
//                        除去空数据
                    if(!e1.getValue().substring(productionDate,serialNumber).trim().equals("")&&e1.getValue().substring(productionDate,serialNumber).trim()!=null){
                    	RanPropertyEquipment ranPropertyEquipment=new RanPropertyEquipment();
                    	ranPropertyEquipment.preInsert();
                    	ranPropertyEquipment.setManagerobject(e1.getValue().substring(0,managerObject));
                    	ranPropertyEquipment.setProductname(e1.getValue().substring(managerObject,productName));
                    	ranPropertyEquipment.setProductnumber(e1.getValue().substring(productName,productNumber));
                    	ranPropertyEquipment.setProductionrevision(e1.getValue().substring(productNumber,productResivion));
                    	ranPropertyEquipment.setProductiondate(e1.getValue().substring(productResivion,productionDate));
                    	ranPropertyEquipment.setSerialnumber(e1.getValue().substring(productionDate,serialNumber));
                    	ranPropertyEquipment.setLogdate(new SimpleDateFormat("yyyy-MM-dd").parse(logDate));
                    	ranPropertyEquipment.setStatus("1");
                    	ranPropertyEquipment.setSitename(siteName);
                    	separateMO(ranPropertyEquipment);
                    	ranPropertyEquipmentsList.add(ranPropertyEquipment);
//                        row.createCell(0).setCellValue(e1.getValue().substring(0,managerObject));
//                        row.createCell(1).setCellValue(e1.getValue().substring(managerObject,productName));
//                        row.createCell(2).setCellValue("");
//                        row.createCell(3).setCellValue("");
//                        row.createCell(4).setCellValue("");
//                        row.createCell(5).setCellValue(e1.getValue().substring(productName,productNumber));
//                        row.createCell(6).setCellValue(e1.getValue().substring(productNumber,productResivion));
//                        row.createCell(7).setCellValue(e1.getValue().substring(productResivion,productionDate));
//                        row.createCell(8).setCellValue(e1.getValue().substring(productionDate,serialNumber));
                        }
                    }
                }
            }
        }
//          根据表头的规则截取数据列，第三方的
        for (Map.Entry<Integer,Integer> e0:map3.entrySet()){
            for (Map.Entry<Integer,String> e1:map2.entrySet()) {
                if(e1.getKey()>=e0.getKey()&&e1.getKey()<=e0.getValue()){
                    if (e1.getValue().contains("serialNumber")&&e1.getValue().contains("manufacturerDesignation")) {

                        managerObject = e1.getValue().indexOf("manufacturerDesignation");
                        productName = e1.getValue().indexOf("manufacturerId");
                        manufactureId = e1.getValue().indexOf("manufacturerRevision");
                        manufactureRevision = e1.getValue().indexOf("negotiatedBitRate");
                        negotiaedBitRate = e1.getValue().indexOf("productNumber");
                        productNumber = e1.getValue().indexOf("productRevision");
                        productResivion = e1.getValue().indexOf("productionDate");
                        productionDate = e1.getValue().indexOf("serialNumber");
                        serialNumber = e1.getValue().length();
                    }
//                    row=sheet.createRow(rownum);
                    if(!e1.getValue().contains("Total")&&!e1.getValue().contains("==")&&!e1.getValue().contains("MO")){
                        if(!e1.getValue().substring(productionDate,serialNumber).trim().equals("")&&e1.getValue().substring(productionDate,serialNumber).trim()!=null){

                        	RanPropertyEquipment ranPropertyEquipment=new RanPropertyEquipment();
                        	ranPropertyEquipment.preInsert();
                        	ranPropertyEquipment.setManagerobject(e1.getValue().substring(0,managerObject));
                        	ranPropertyEquipment.setProductname(e1.getValue().substring(managerObject,productName));
                        	ranPropertyEquipment.setManufacturerid(e1.getValue().substring(productName,manufactureId));
                        	ranPropertyEquipment.setManufacturerrevision(e1.getValue().substring(manufactureId,manufactureRevision));
                        	ranPropertyEquipment.setNegotiatedbitrate(e1.getValue().substring(manufactureRevision,negotiaedBitRate));
                        	ranPropertyEquipment.setProductnumber(e1.getValue().substring(negotiaedBitRate,productNumber));
                        	System.out.println(ranPropertyEquipment.getProductnumber());
                        	ranPropertyEquipment.setProductionrevision(e1.getValue().substring(productNumber,productResivion));
                        	ranPropertyEquipment.setProductiondate(e1.getValue().substring(productResivion,productionDate));
                        	ranPropertyEquipment.setSerialnumber(e1.getValue().substring(productionDate,serialNumber));
                        	ranPropertyEquipment.setLogdate(new SimpleDateFormat("yyyy-MM-dd").parse(logDate));
                        	ranPropertyEquipment.setSitename(siteName);
                        	ranPropertyEquipment.setStatus("1");
                        	separateMO(ranPropertyEquipment);
                        	ranPropertyEquipmentsList.add(ranPropertyEquipment);
//                            row.createCell(0).setCellValue(e1.getValue().substring(0,managerObject));
//                            row.createCell(1).setCellValue(e1.getValue().substring(managerObject,productName));
//                            row.createCell(2).setCellValue(e1.getValue().substring(productName,manufactureId));
//                            row.createCell(3).setCellValue(e1.getValue().substring(manufactureId,manufactureRevision));
//                            row.createCell(4).setCellValue(e1.getValue().substring(manufactureRevision,negotiaedBitRate));
//                            row.createCell(5).setCellValue(e1.getValue().substring(negotiaedBitRate,productNumber));
//                            row.createCell(6).setCellValue(e1.getValue().substring(productNumber,productResivion));
//                            row.createCell(7).setCellValue(e1.getValue().substring(productResivion,productionDate));
//                            row.createCell(8).setCellValue(e1.getValue().substring(productionDate,serialNumber));
                        }
                    }
                }
            }
        }
        return  ranPropertyEquipmentsList;
    }
    public static void separateMO(RanPropertyEquipment ranPropertyEquipment) {
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


/*    public static void main(String[] args) throws Exception{
//        String fileName=System.getProperty("user.dir")+"\\src\\main\\resources\\FBJ085980.log";
//        readUseLine(fileName);
        Properties properties=new Properties();
        properties.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\log.properties"));
        ArrayList<File> filePathArr=readDir(new File(properties.getProperty("path")));
        for (File file:filePathArr) {
            if(file.getName().endsWith("log")){
                readUseLine(file.toString());

            }

        }
    }*/
}
