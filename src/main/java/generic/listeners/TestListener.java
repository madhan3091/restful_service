package generic.listeners;

import generic.utils.ExtentReport;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import static Path.testName;

public class TestListener implements ITestListener {

    Logger logs = Logger.getLogger("TestListener");
    public static String testName="";
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName()+""+testName;
    }
    public static void testName(String tName){
        testName=tName;
    }

    //Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {
        logs.info("I am in onStart method " + iTestContext.getName());
        ExtentReport.startReport();

    }

    //After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        logs.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
        ExtentReport.endReport();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logs.info("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
        //Start operation for extentreports.
        //ExtentReport.startTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logs.info("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
        //Extentreports log operation for passed tests.
        ExtentReport.log("Test Passed","pass");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
//        try{
            logs.info("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
            //Extentreports log operation for failed tests.
            // ExtentReport.log(""+iTestResult.getThrowable());
             // System.out.println("***********"+ResObj.asString());
           // String description = ResObj.asString();
           // sendNotification.defectLog(getTestMethodName(iTestResult), description, iTestResult);
            // sendNotification.send();
           // System.out.println(iTestResult.getThrowable().toString());
            ExtentReport.log(""+iTestResult.getThrowable(),"fail");
            ExtentReport.log("Test Failed","fail");
//        }catch(ParseException e){
//            e.printStackTrace();
//        }catch(IOException e){
//            e.printStackTrace();
//        }


    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logs.info("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
        //Extentreports log operation for skipped tests.
        ExtentReport.log("Test Skipped","skip");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logs.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}