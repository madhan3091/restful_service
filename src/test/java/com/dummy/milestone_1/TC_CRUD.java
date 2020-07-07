package com.dummy.milestone_1;

import com.dummy.assets.model.customerModel.employeeRequest;
import com.dummy.assets.resources.Path;
import com.dummy.assets.resources.ReadTestData;
import com.dummy.corefunctions.Usemethods;
import generic.authentication.Auth;
import generic.utils.ExtentReport;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.dummy.assets.resources.Path.*;
import static com.dummy.assets.resources.Specification.emptyrequestSpec;
import static com.dummy.assets.resources.Specification.reqBuilder;

public class TC_CRUD {

    public String UserId;

    @BeforeClass
    public void beforeClass()
    {
        //Properties obj = ConfigPropertyReader.readConfig("config.properties");
        Logger logs = Logger.getLogger("dummy Test Automation Suite");

    }
    @BeforeMethod
    public void beforeMethod()
    {
        Auth.basicAuth();
    }

    @DataProvider(name = "Employee")
    public Object[][] readCustomer() throws InvalidFormatException, IOException {
        return ReadTestData.getCellData(testData + "Employees.xlsx", "employee");
    }

    @Test(enabled = true, dataProvider="Employee")
    public void addCreate(String testCase,String name,String salary,String age){
        ExtentReport.startTest("addCreate-"+testCase);
        ExtentReport.log("*****Test File :  TC_Employee_TestCases *****");
        ExtentReport.log("*****TestCase :  "+testCase+"*****");
        employeeRequest payload = new employeeRequest();
        payload.setName(name);
        payload.setSalary(salary);
        payload.setAge(age);
        System.out.println(payload);
        ExtentReport.log("Payload Object: " + payload);
        ExtentReport.log("Input API: " + BASE_URL + API_CREATE);
        reqBuilder.setBody(payload.toString());
        Response ResObj = Usemethods.createEmployee(payload, Path.API_CREATE);
        System.out.println(ResObj.asString());
        ExtentReport.log("Response : " + ResObj.asString());
        String fullname =ResObj.jsonPath().getString("data.name");
        Assert.assertEquals(ResObj.statusCode(),200);
        Assert.assertNotNull(fullname);
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);

    }
    @Test(enabled = true)
    public void getEmployee(){
        String testCase="getAllEmployee";
        ExtentReport.startTest("getEmp -"+testCase);
        ExtentReport.log("*****Test File :  TC_Employee_TestCases *****");
        ExtentReport.log("*****TestCase :  "+testCase+"*****");
        ExtentReport.log("Input API: " + BASE_URL + GET_ALL_EMPLOYEES);
        Response ResObj = Usemethods.getEmployees(GET_ALL_EMPLOYEES,Auth.emptyHeaders);
        System.out.println(ResObj.asString());
        String ID =ResObj.jsonPath().getString("data.id[4]");
        UserId=ID;
        System.out.println("---------------"+ ID);
        ExtentReport.log("Response : " + ResObj.asString());
        Assert.assertEquals(ResObj.statusCode(),200);
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);

    }

    @Test(enabled = true)
    public void getsingleEmployee(){
        String testCase="getsingleEmployee";
        ExtentReport.startTest("getEmp -"+testCase);
        ExtentReport.log("*****Test File :  TC_Employee_TestCases *****");
        ExtentReport.log("*****TestCase :  "+testCase+"*****");
        ExtentReport.log("Input API: " + BASE_URL + GET_EMPLOYEE +"/"+UserId);
        Response ResObj = Usemethods.getsingleEmployee(GET_EMPLOYEE+"/"+UserId,Auth.emptyHeaders);
        System.out.println(ResObj.asString());
        ExtentReport.log("Response : " + ResObj.asString());
        Assert.assertEquals(ResObj.statusCode(),200);
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);

    }

    @Test(enabled = true)
    public void updateEmployee(){
        String testCase="updateEmployee";
        ExtentReport.startTest("updateEmp -"+testCase);
        ExtentReport.log("*****Test File :  TC_Employee_TestCases *****");
        ExtentReport.log("*****TestCase :  "+testCase+"*****");
        ExtentReport.log("Input API: " + BASE_URL + UPDATE_EMPLOYEES+"/"+UserId);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("name","Airi Satou");
        paramsMap.put("salary","50000");
        paramsMap.put("age","23");
        reqBuilder.setBody(paramsMap);
        ExtentReport.log("Input Request: " + paramsMap.toString());
        RequestSpecification requestSpecification = reqBuilder.build();
        Response ResObj = Usemethods.updateEmployee(requestSpecification,UPDATE_EMPLOYEES+"/"+UserId,Auth.emptyHeaders);
        System.out.println(ResObj.asString());
        ExtentReport.log("Response : " + ResObj.asString());
        Assert.assertEquals(ResObj.statusCode(),200);
        String data =ResObj.jsonPath().getString("data");
        Assert.assertNotNull(data,"Not expected a null object");
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);

    }
    @Test(enabled = true)
    public void deleteEmployee(){
        String testCase="deleteEmployee";
        ExtentReport.startTest("deleteEmp -"+testCase);
        ExtentReport.log("*****Test File :  TC_Employee_TestCases *****");
        ExtentReport.log("*****TestCase :  "+testCase+"*****");
        ExtentReport.log("Input API: " + BASE_URL + DELETE_EMPLOYEES+"/"+UserId);
        Response ResObj = Usemethods.deleteEmployee(emptyrequestSpec,DELETE_EMPLOYEES+"/"+UserId,Auth.emptyHeaders);
        System.out.println(ResObj.asString());
        ExtentReport.log("Response : " + ResObj.asString());
        Assert.assertEquals(ResObj.statusCode(),200);
        String data =ResObj.jsonPath().getString("status");
        Assert.assertEquals(data,"success");

        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);

    }
    @AfterClass
    public void tear(){

    }


}
