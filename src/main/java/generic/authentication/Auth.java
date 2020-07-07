package generic.authentication;
import generic.config.ConfigPropertyReader;
import generic.rest.Api;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Reporter;
import sun.misc.BASE64Encoder;

import java.util.Map;
import java.util.Properties;

import static com.dummy.assets.resources.Specification.reqBuilder;

public class Auth {
    public static Logger logs = Logger.getLogger("Auth");
    public static Headers emptyHeaders = new Headers();
    public static Properties obj = ConfigPropertyReader.readConfig("config.properties");

    public static void basicAuth() {
        Reporter.log("Proceed to API Authentication using basicAuth: ");
        RestAssured.baseURI = obj.getProperty("BASE_URL");
       /* PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(obj.getProperty("username"));
        authScheme.setPassword(obj.getProperty("password"));
        RestAssured.authentication = authScheme;*/

    }

    public static Header basicAuthHeader() {
        Reporter.log("Proceed to basicAuth API Authentication using basicAuthHeader: ");
        RestAssured.baseURI = obj.getProperty("BASE_URL");
        String name = obj.getProperty("username");
        String password = obj.getProperty("password");
        String authString = name + ":" + password;
        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
        logs.info("Base64 encoded auth string: " + authStringEnc);
        Header auth = new Header("Authorization", "Basic " + authStringEnc);
        return auth;

    }

    public static String authHeader() {
        Reporter.log("Proceed to basicAuth API Authentication using basicAuthHeader: ");
        String aUrl = obj.getProperty("auth_URL");
        String name = obj.getProperty("auth_Username");
        String password = obj.getProperty("auth_password");
        JSONObject payload = new JSONObject();
        payload.put("userName",name);
        payload.put("password",password);
        reqBuilder.setBody(payload.toString());
        reqBuilder.setContentType("application/json");
        RequestSpecification requestSpec = reqBuilder.build();
        Response ResObj = Api.doPost(requestSpec,aUrl);
        JsonPath jsonPath = new JsonPath(ResObj.asString());
        String atoken = jsonPath.getString("token");
       // System.out.println("Token****"+atoken);
        return "Bearer "+atoken;
    //    return c_obj.getProperty("authKey");
    }

    public static void oAuth1Authenticate() {
        Reporter.log("Proceed to API Authentication : ");
        RestAssured.baseURI = obj.getProperty("BASE_URL");
        OAuthScheme authScheme = new OAuthScheme();
        authScheme.setConsumerKey(obj.getProperty("ConsumerKey"));
        authScheme.setConsumerSecret(obj.getProperty("ConsumerSecret"));
        authScheme.setSecretToken(obj.getProperty("TokenSecret"));
        authScheme.setAccessToken(obj.getProperty("Token"));
        RestAssured.authentication = authScheme;

    }

    public static void oAuth2Authenticate(){
//        Reporter.log("Proceed to Oauth 2.0 API Authentication : ");
//        RestAssured.baseURI = obj.getProperty("BASE_URL");
//        PreemptiveOAuth2HeaderScheme auth2Scheme=new PreemptiveOAuth2HeaderScheme();
//        AccessToken accessPojo=
//                RestAssured.given().auth().preemptive(). basic(obj.getProperty("ConsumerKey"),obj.getProperty("ConsumerSecret"))
//                        .params("grant_type", "client_credentials")
//                        .params("scope", "read").when()
//                        .post(Path.POST_OAUTH_TOKEN).as(AccessToken.class);
//        Reporter.log("Access AccessToken :"+accessPojo.getAccess_token());
//        auth2Scheme.setAccessToken(accessPojo.getAccess_token());
//        RestAssured.authentication=auth2Scheme;

    }

    public static Headers headerSet(Map<String,String> headerData) {
        Header header =null;
        Headers headers =null ;
        for(Map.Entry<String,String> eachHeaderData : headerData.entrySet())
        {
            header = new Header(eachHeaderData.getKey(),eachHeaderData.getValue());
            headers = new Headers(header);
        }
        return headers;
    }

}
