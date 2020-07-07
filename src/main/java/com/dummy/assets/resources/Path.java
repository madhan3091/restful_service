package com.dummy.assets.resources;

import generic.config.ConfigPropertyReader;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Properties;

public class Path {
    
    public static Properties obj = ConfigPropertyReader.readConfig("config.properties");

    public static final String BASE_URL = obj.getProperty("BASE_URL");
    public static String jsonPath=System.getProperty("user.dir")+"/src/main/java/com/dummy/assets/Json/";
    public static String testData=System.getProperty("user.dir")+"/src/main/java/com/dummy/assets/data/";
    public static JSONParser jsonParser = new JSONParser();
    public static Object payload = new JSONObject();
   // public static String testName="";

    //Endpoints
    public static final String API_CREATE ="/create";
    public static final String  GET_ALL_EMPLOYEES ="/employees";
    public static final String  UPDATE_EMPLOYEES ="/update";
    public static final String  DELETE_EMPLOYEES ="/delete";
    public static final String  GET_EMPLOYEE ="/employee";






}
