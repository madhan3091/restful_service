package generic.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import generic.config.ConfigPropertyReader;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Properties;

public class ExtentReport {

    public static ExtentReports extent;
    public static ExtentTest test;
    public static Properties obj = ConfigPropertyReader.readConfig("config.properties");
    public static String testName="";

    @BeforeSuite
    public void deleteReport(){
        File extentreport = new File(obj.getProperty("Extent_Report"));
        boolean exists = extentreport.exists();
        if(exists){
            extentreport.delete();
        }
    }

    public static void testName(String tName){
        testName=tName;
    }
    public static ExtentReports startReport() {
        extent = new ExtentReports(obj.getProperty("Extent_Report"),false);
        return extent;
    }

    public static ExtentTest startTest(String testCaseName) {
        test = extent.startTest((testCaseName));
        //test.log(LogStatus.INFO,extent.getClass().toString());
        test.log(LogStatus.INFO,testCaseName);
        return test;
    }
    public static ExtentTest start_Test(String testCaseName) {
        test = extent.startTest((testCaseName));
        test.log(LogStatus.INFO,testCaseName);
        return test;
    }

    public static String log(String description, String status) {

        if (status.equalsIgnoreCase("pass")) {
            test.log(LogStatus.PASS, description);
        } else if (status.equalsIgnoreCase("fail")) {
            test.log(LogStatus.FAIL, description);
        } else if (status.equalsIgnoreCase("skip")){
            test.log(LogStatus.SKIP,description);
        } else{
            test.log(LogStatus.INFO, description);
        }
        return test.toString();
    }
    public static String log(String description) {
        test.log(LogStatus.INFO, description);
        return test.toString();
    }

    public static void endReport()
    {
        extent.endTest(test);
        extent.flush();
    }
    public static void assignDetails(String author,String category){
        test.assignAuthor(author);
        test.assignCategory(category);
    }
}
