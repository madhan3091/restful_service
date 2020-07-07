package com.dummy.assets.resources;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ReadTestData {


    static String filePath ="";
    static File file;
    static FileInputStream fis;
    static XSSFWorkbook wb;
    static XSSFSheet sheet;
    static int row =1;



    public static Object[][] getCellData(String filePath, String sheetName) throws InvalidFormatException, IOException {

        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int column = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][column];
        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < column; j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                String val = formatter.formatCellValue(cell);
                data[i - 1][j] = val;
            }
        }

        return data;
    }

    public static void setSheetData() throws IOException
    {
        file = new File(filePath);
        fis = new FileInputStream(file);
        wb = new XSSFWorkbook(fis);

    }


    public static void writeData(String sheetName,String value1,String value2, String value3) throws IOException {

        sheet = wb.getSheet(sheetName);
        XSSFRow row1 = sheet.createRow(row);
        System.out.println(value3);
        row1.createCell(0).setCellValue(value1);
        row1.createCell(1).setCellValue(value2);
        row1.createCell(2).setCellValue(value3);
        ++row;
    }

    public static void exportExcelData() throws IOException
    {
        FileOutputStream fos = new FileOutputStream(filePath);
        wb.write(fos);
        fos.close();
    }
}
