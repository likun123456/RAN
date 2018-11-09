package com.thinkgem.jeesite.common.utils.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EPCExcelUtil {
	private static int sheetSize = 65536; 
	/**  
	 *  数据库导出至Excel表格 
	 */
	public void export(OutputStream os, String[] headers,List<String[]> bodyData) {
		try {
			int sheetNum = 1;//当前sheet索引  
	        int recNum = 0; // 当前记录索引  
			// 创建Excel工作薄   
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			
			
			WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD); 
			WritableCellFormat cf_title = new WritableCellFormat();
			cf_title.setAlignment(Alignment.CENTRE);
			cf_title.setBackground(Colour.PALE_BLUE);
			cf_title.setFont(wf_title);
			
			//开始分sheeet
			while(true){
				int rowNum = 0;  
				WritableSheet sheet = wwb.createSheet("sheet"+sheetNum, 0);
				sheetNum++;
				Label label;
				int sheetRecNum = 0;  //单个sheet的行数
				for (int i = 0; i < headers.length; i++) {
					sheet.setColumnView(i, 20); 
					label = new Label(i, rowNum, headers[i], cf_title);
					//将定义好的单元格添加到工作表中   
					sheet.addCell(label);
					
				}
				rowNum++;
				// 下面是填充数据 
				for(int k = recNum; k < bodyData.size(); k++){
					//单个SHEET数据行超过SHEET应达到数据限额，则停止写入  
                    if(sheetRecNum >= sheetSize){
                    	break;  
                    } 
					String[] str = bodyData.get(k);
					for (int i = 0; i < str.length; i++) {
						label = new Label(i, rowNum, str[i]);
						sheet.addCell(label);
					}
					recNum++;
					sheetRecNum++;
					rowNum++;
				}
				//数据写完，退出  
                if(recNum >= bodyData.size()){
                	 break;  
                }
			}
			
			// 写入数据   
			wwb.write();
			// 关闭文件   
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static WritableCellFormat getHeader(){
		   WritableFont font = new  WritableFont(WritableFont.TIMES, 10 ,WritableFont.BOLD);//定义字体
			   try{
			       font.setColour(Colour.BLUE);//蓝色字体
			   }catch(WriteException e1) {
			       e1.printStackTrace();
			   }
			   WritableCellFormat format = new  WritableCellFormat(font);
			   try{
				    format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
				    format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
				    format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);//黑色边框
				    format.setBackground(Colour.YELLOW2);//黄色背景
			   }catch(WriteException e) {
			        e.printStackTrace();
			   }
		   return format;
	}
	
	/**
     * 
    * @Title: readXls 
    * @Description: 处理xls文件
    * @param @param path
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List<List<String>>    返回类型 
    * @throws
    * 
    * 从代码不难发现其处理逻辑：
    * 1.先用InputStream获取excel文件的io流
    * 2.然后穿件一个内存中的excel文件HSSFWorkbook类型对象，这个对象表示了整个excel文件。
    * 3.对这个excel文件的每页做循环处理
    * 4.对每页中每行做循环处理
    * 5.对每行中的每个单元格做处理，获取这个单元格的值
    * 6.把这行的结果添加到一个List数组中
    * 7.把每行的结果添加到最后的总结果中
    * 8.解析完以后就获取了一个List<List<String>>类型的对象了
    * 
     */
    public List<List<String>> readXls(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        // HSSFWorkbook 标识整个excel
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        int size = hssfWorkbook.getNumberOfSheets();
        // 循环每一页，并处理当前循环页
        for (int numSheet = 0; numSheet < size; numSheet++) {
            // HSSFSheet 标识某一页
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 处理当前页，循环读取每一行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                // HSSFRow表示行
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int minColIx = hssfRow.getFirstCellNum();
                int maxColIx = hssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                // 遍历改行，获取处理每个cell元素
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    // HSSFCell 表示单元格
                    HSSFCell cell = hssfRow.getCell(colIx);
                    if (cell == null) {
                        continue;
                    }
                    rowList.add(getStringVal_HSSF(cell));
                }
                result.add(rowList);
            }
        }
        return result;
    }

    /**
     * 
    * @Title: readXlsx 
    * @Description: 处理Xlsx文件
    * @param @param path
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List<List<String>>    返回类型 
    * @throws
     */
    public List<List<String>> readXlsx(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        // 循环每一页，并处理当前循环页
        for (XSSFSheet xssfSheet : xssfWorkbook) {
            if (xssfSheet == null) {
                continue;
            }
            // 处理当前页，循环读取每一行
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                int minColIx = xssfRow.getFirstCellNum();
                int maxColIx = xssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    XSSFCell cell = xssfRow.getCell(colIx);
                    if (cell == null) {
                        continue;
                    }
                    rowList.add(getStringVal_XSSF(cell));
                }
                result.add(rowList);
            }
        }
        return result;
    }

    /**
     * 改造poi默认的toString（）方法如下
    * @Title: getStringVal 
    * @Description: 1.对于不熟悉的类型，或者为空则返回""控制串
    *               2.如果是数字，则修改单元格类型为String，然后返回String，这样就保证数字不被格式化了
    * @param @param cell
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String getStringVal_HSSF(HSSFCell cell) {
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_BOOLEAN:
            return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
        case Cell.CELL_TYPE_FORMULA:
            return cell.getCellFormula();
        case Cell.CELL_TYPE_NUMERIC:
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue();
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
        default:
            return "";
        }
    }

    public static String getStringVal_XSSF(XSSFCell cell) {
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_BOOLEAN:
            return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
        case Cell.CELL_TYPE_FORMULA:
            return cell.getCellFormula();
        case Cell.CELL_TYPE_NUMERIC:
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue();
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
        default:
            return "";
        }
    }

}
