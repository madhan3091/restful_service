package com.dummy.corefunctions;

import generic.rest.Api;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Usemethods {

    public static Response createEmployee(Object inputJson, String postUrl){
        Header h1= new Header("Accept", "*/*");
        Header h2 = new Header("Content-Type", "application/json");
        Headers headers = new Headers(h1, h2);
        return Api.postData(inputJson, postUrl, headers);
    }
    public static Response getEmployees(String getUrl, Headers emptyHeaders) {

        Header h1= new Header("Accept", "*/*");
        Header h2 = new Header("Content-Type", "application/json");
        Headers headers = new Headers(h1, h2);
        return Api.getData(getUrl,headers);
    }
    public static Response getsingleEmployee(String getUrl, Headers emptyHeaders) {

        Header h1= new Header("Accept", "*/*");
        Header h2 = new Header("Content-Type", "application/json");
        Headers headers = new Headers(h1, h2);
        return Api.getData(getUrl,headers);
    }
    public static Response updateEmployee(RequestSpecification specification,String putUrl, Headers emptyHeaders) {

        Header h1= new Header("Accept", "*/*");
        Header h2 = new Header("Content-Type", "application/json");
        Headers headers = new Headers(h1, h2);
        return Api.putData(specification,putUrl,headers);
    }

    public static Response deleteEmployee(RequestSpecification specification,String deleteUrl, Headers emptyHeaders) {

        Header h1= new Header("Accept", "*/*");
        Header h2 = new Header("Content-Type", "application/json");
        Headers headers = new Headers(h1, h2);
        return Api.deleteData(specification,deleteUrl,headers);
    }
}
