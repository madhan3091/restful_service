package com.dummy.assets.resources;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class RequestHeaders {
    public static Headers emptyHeaders = new Headers();

    public static Header accept= new Header("Accept", "*/*");
    public static Header content_type = new Header("Content-Type", "application/json");
    public static Header acceptsppjson = new Header("accept","application/json");


}
