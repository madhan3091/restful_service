package generic.utils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils{
    public static Logger logs = Logger.getLogger("TestListener");
    @DataProvider
    public static Object[][] readRepositoryData(String filePath) throws IOException {
        XSSFWorkbook workbook;
        XSSFSheet worksheet;
        DataFormatter formatter = new DataFormatter();
        // Import excel sheet.
        String cwd = new File("").getAbsolutePath();
        File src = new File(cwd+ filePath);
        logs.info("Path of File:"+src);
        // Load the file.
        FileInputStream fileInputStream = new FileInputStream(src); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook(fileInputStream); //get my workbook
        worksheet = workbook.getSheet("Sheet1");// get my sheet from workbook
        XSSFRow Row = worksheet.getRow(0);     //get my Row which start from 0

        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum = Row.getLastCellNum(); // get last ColNum

        Object[][] Data = new Object[RowNum - 1][ColNum]; // pass my  count data in array

        for(int i=0; i<RowNum-1; i++) //Loop work for Rows
        {
            XSSFRow row= worksheet.getRow(i+1);

            for (int j=0; j<ColNum; j++) //Loop work for colNum
            {
                if(row==null)
                    Data[i][j]= "";
                else
                {
                    XSSFCell cell= row.getCell(j);
                    if(cell==null)
                        Data[i][j]= ""; //if it get Null value it pass no data
                    else
                    {
                        String value=formatter.formatCellValue(cell);
                        Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                    }
                }
            }

        }

        return Data;
    }

}
