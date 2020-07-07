package com.dummy.assets.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    public static RequestSpecification requestSpecification;
    public static RequestSpecification emptyrequestSpec = new RequestSpecBuilder().build();
    public static ResponseSpecification responseSpecCreate = new ResponseSpecBuilder().expectStatusCode(201).build();
    public static ResponseSpecification responseSpecList = new ResponseSpecBuilder().expectStatusCode(200).build();
    public static ResponseSpecification responseSpecDel = new ResponseSpecBuilder().expectStatusCode(204).build();
    public static RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
}
