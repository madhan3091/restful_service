package generic.rest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class Api {


    public static Response postData(Object inputjson, String postUrl, Headers headers){
        return RestAssured.
                given().
                headers(headers).
                body(inputjson).
                when().
                post(postUrl);


    }
    public static Response doPost(RequestSpecification specification, String postUrl) {
        return RestAssured.
                given().
                spec(specification).
                when().
                post(postUrl);
    }
    public static Response doMultiPart(RequestSpecification specification, String postUrl, String fPath) {
        return RestAssured.
                given().multiPart(new File(fPath)).
                spec(specification).
                when().
                post(postUrl);
    }
    public static Response doPut(RequestSpecification specification, String updateUrl) {
        return RestAssured.
                given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("*/*", ContentType.TEXT))).
                spec(specification).
                when().
                put(updateUrl);
    }
    public static Response doPatch(RequestSpecification specification, String updateUrl) {
        return RestAssured.
                given().
                spec(specification).
                when().
                patch(updateUrl);
    }
    public static Response doPost2(RequestSpecification specification) {
        return RestAssured.
                given().
                spec(specification).
                when().
                post();
    }
    public static Response doGet(RequestSpecification specification, String url) {
        return RestAssured.
                given().
                spec(specification).
                when().
                get(url);
    }
    public static Response doDelete(RequestSpecification specification, String url) {
        return RestAssured.
                given().
                spec(specification).
                when().
                delete(url);
    }
    public static Response getData(String getUrl, Headers headers){
        return RestAssured.
                given().
                headers(headers).
                when().
                get(getUrl);

    }

    public static Response getData1(RequestSpecification requestSpecification, String getUrl,Headers headers) {
        return RestAssured.
                given().
                spec(requestSpecification).
                headers(headers).
                when().
                get(getUrl);
    }

    public static Response deleteData(RequestSpecification specification, String deleteUrl,Headers headers) {
        return RestAssured.
                given().
                spec(specification).
                headers(headers).
                when().
                delete(deleteUrl);
    }

    public static Response patchData(RequestSpecification specification, String patchUrl,Headers headers) {
        return RestAssured.
                given().
                spec(specification).
                headers(headers).
                when().
                patch(patchUrl);
    }

    public static Response putData(RequestSpecification specification, String putUrl,Headers headers) {
        return RestAssured.
                given().
                spec(specification).
                headers(headers).
                when().
                put(putUrl);
    }

    //HEAD method is identical to GET method except that the server must not return a message-body in the response.
    // It is also used for testing hypertext links for validity,accessibility and recent modification.

    public static Response headData(RequestSpecification specification, String getUrl,Headers headers) {
        return RestAssured.
                given().
                spec(specification).
                headers(headers).
                when().
                put(getUrl);
    }

    //OPTIONS method is used by the client to find out the HTTP methods and other options supported by a web server.

    public static Response optionsData(RequestSpecification specification, String getUrl,Headers headers) {
        return RestAssured.
                given().
                spec(specification).
                headers(headers).
                when().
                options(getUrl);
    }
}
