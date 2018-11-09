package com.thinkgem.jeesite.modules.propertycheck.util;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.*;

public class ReadAndAnalysisLog {
	private static int  serialNumber;
    private static int siteName;
    private static int managerObject;
    private static int productName;
    private static int productNumber;
    private static int productResivion;
    private static int productionDate;
    private static int manufactureId;
    private static int manufactureRevision;
    private static int negotiaedBitRate;
    private static int status;
    private static int logDate;
    private static ArrayList<File> filePathArr=new ArrayList<File>();
    /**
     * 递归读取文件夹下所有的文件，存入到List集合的是所有的log文件
     * @author BruceLee
     * @param filePath
     * @return
     * @throws Exception
     */
    public static ArrayList<File> readDir(File filePath) throws  Exception{
        File[] files=filePath.listFiles();
        for (File f:files) {
            if(f.isDirectory()){
                readDir(f);
            }
            if (f.isFile()){
//                System.out.println(f);
                filePathArr.add(f);
            }
        }
            return filePathArr;

    }
    /**
     * 读取一个站站点的数据
     * @author BruceLee
     * @param fileName
     * @throws Exception
     */
    public static void readUseLine(String fileName) throws  Exception{
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
//        Map<Integer,String> map2=new HashMap<Integer, String>();//存取所有的表
        Map<Integer,Integer> map3=new HashMap<Integer, Integer>();//存取第三方的表的始末行数
        InputStream inputStream;
        LineNumberReader lineNumberReader;
        inputStream=new FileInputStream(new File(fileName));
        lineNumberReader=new LineNumberReader(new InputStreamReader(inputStream));
        String line;
        int startline=0;//起始行
        int lastline=0;//结束行

//        Workbook workbook=new HSSFWorkbook();//创建excel文件
//        Sheet sheet=workbook.createSheet("资产信息核查表");//一张excel表
//        sheet.setColumnWidth(0,256*60);
//        sheet.setColumnWidth(1,256*25);
//        sheet.setColumnWidth(2,256*17);
//        sheet.setColumnWidth(3,256*20);
//        sheet.setColumnWidth(4,256*18);
//        sheet.setColumnWidth(5,256*20);
//        sheet.setColumnWidth(6,256*15);
//        sheet.setColumnWidth(7,256*15);
//        sheet.setColumnWidth(8,256*15);
//        Row row=sheet.createRow(0);//参数表示创建第几行，索引从0开始
//        Cell cell;

        while((line=lineNumberReader.readLine())!=null){
            map.put(lineNumberReader.getLineNumber(),line);//存储所有行到map集合
        }
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

//       row.createCell(0).setCellValue("MO");
//       row.createCell(1).setCellValue("manufacturerDesignation");
//       row.createCell(2).setCellValue("manufacturerId");
//       row.createCell(3).setCellValue("manufacturerRevision");
//       row.createCell(4).setCellValue("negotiatedBitRate");
//       row.createCell(5).setCellValue("productNumber");
//       row.createCell(6).setCellValue("productRevision");
//       row.createCell(7).setCellValue("productionDate");
//       row.createCell(8).setCellValue("serialNumber");


//          根据表头的规则截取数据列，爱立信自己的
        int rownum=1;
        int cellnum=0;
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
//                    row=sheet.createRow(rownum);
                    if(!e1.getValue().contains("Total")&&!e1.getValue().contains("==")&&!e1.getValue().contains("MO")){
//                        System.out.print(e1.getValue().substring(0,managerObject));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(managerObject,productName));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(productName,productNumber));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(productNumber,productResivion));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(productResivion,productionDate));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(productionDate,serialNumber));
//                        System.out.println("");

//                        除去空数据
                    if(!e1.getValue().substring(productionDate,serialNumber).trim().equals("")&&e1.getValue().substring(productionDate,serialNumber).trim()!=null){

//                        row.createCell(0).setCellValue(e1.getValue().substring(0,managerObject));
//                        row.createCell(1).setCellValue(e1.getValue().substring(managerObject,productName));
//                        row.createCell(2).setCellValue("");
//                        row.createCell(3).setCellValue("");
//                        row.createCell(4).setCellValue("");
//                        row.createCell(5).setCellValue(e1.getValue().substring(productName,productNumber));
//                        row.createCell(6).setCellValue(e1.getValue().substring(productNumber,productResivion));
//                        row.createCell(7).setCellValue(e1.getValue().substring(productResivion,productionDate));
//                        row.createCell(8).setCellValue(e1.getValue().substring(productionDate,serialNumber));
                        rownum++;
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
//                        System.out.print(e1.getValue().substring(0,managerObject));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(managerObject,productName));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(productName,manufactureId));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(manufactureId,manufactureRevision));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(manufactureRevision,negotiaedBitRate));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(negotiaedBitRate,productNumber));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(productNumber,productResivion));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(productResivion,productionDate));
//                        System.out.print("\t");
//                        System.out.print(e1.getValue().substring(productionDate,serialNumber));
//                        System.out.println("");row.createCell(0).setCellValue(e1.getValue().substring(0,managerObject));
                        if(!e1.getValue().substring(productionDate,serialNumber).trim().equals("")&&e1.getValue().substring(productionDate,serialNumber).trim()!=null){

//                            row.createCell(0).setCellValue(e1.getValue().substring(0,managerObject));
//                            row.createCell(1).setCellValue(e1.getValue().substring(managerObject,productName));
//                            row.createCell(2).setCellValue(e1.getValue().substring(productName,manufactureId));
//                            row.createCell(3).setCellValue(e1.getValue().substring(manufactureId,manufactureRevision));
//                            row.createCell(4).setCellValue(e1.getValue().substring(manufactureRevision,negotiaedBitRate));
//                            row.createCell(5).setCellValue(e1.getValue().substring(negotiaedBitRate,productNumber));
//                            row.createCell(6).setCellValue(e1.getValue().substring(productNumber,productResivion));
//                            row.createCell(7).setCellValue(e1.getValue().substring(productResivion,productionDate));
//                            row.createCell(8).setCellValue(e1.getValue().substring(productionDate,serialNumber));
                            rownum++;
                        }
                    }
                }
            }
        }
//        FileOutputStream fileOutputStream=new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\"+UUID.randomUUID().toString().replaceAll("-","")+".xls");
//        workbook.write(fileOutputStream);
//        fileOutputStream.close();
    }

}
