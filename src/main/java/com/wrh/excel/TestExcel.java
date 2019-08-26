package com.wrh.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:34 2019/7/12 0012
 * @Modified By:
 */
public class TestExcel {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "e:\\file\\123.xlsx";
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = null;
        try {
            workbook = getWorkbook(file,"123.xlsx");
        } catch (Exception e) {
            System.out.println("异常： {}"+e);

        }
        int physicalNumberOfRows = workbook.getSheetAt(0).getPhysicalNumberOfRows();
        System.out.println("excel行数： " + physicalNumberOfRows);

        int lastRowNum = workbook.getSheetAt(0).getLastRowNum();
        System.out.println("lastRowNum : " + lastRowNum);

        int physicalNumberOfCells;
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < physicalNumberOfRows; i++) {
            physicalNumberOfCells = sheet.getRow(i).getPhysicalNumberOfCells();
            int num1 = sheet.getRow(i).getRowNum();
            int num2 = sheet.getRow(i).getLastCellNum();
            int num3 = sheet.getRow(i).getFirstCellNum();

            //为空的值不计算在内
            System.out.println("第" + i +"行的元素个数 PhysicalNumberOfCells 为：" + physicalNumberOfCells);
            //当前所在行数， 第一行为0
            System.out.println("第" + i +"行的元素个数 RowNum 为：" + num1);
            System.out.println("第" + i +"行的元素个数 LastCellNum 为：" + num2);
            System.out.println("第" + i +"行的元素个数 FirstCellNum 为：" + num3);
            /*int lastNum = workbook.getSheetAt(0).getRow(i).getLastCellNum();
            System.out.println("lastCELLNum :" + lastNum);
            for (int j = 0; j < lastNum; j++) {
                String value = (String) getCellFormatValue(workbook.getSheetAt(0).getRow(i).getCell(j));
                System.out.println("第" + i +"行" + ", 第" + j + "列的值为：" + value);
            }*/


        }
    }

    private static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            System.out.println("文件:{}非excel文件格式" + fileName);
            return null;
        }
        return workbook;
    }

    private static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:// 如果当前Cell的Type为NUMERIC
                case FORMULA: {
                    // 判断当前的cell是否为Date
                    System.out.println("===>formula");
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {
                        // 如果是纯数字
                        // 取得当前Cell的数值
                        System.out.println("===>formula - num");
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:
                    System.out.println("===>string");
                    // 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    System.out.println("===>default");
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
}
