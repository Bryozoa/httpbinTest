package Httpbin;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;

        /* Imported libraries: http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm
         *                     https://www.slf4j.org/download.html
         *                     https://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/
         *                     http://www.java2s.com/Code/Jar/t/Downloadtestng60jar.htm
         *
         *
         * Class HttpBinGetTest is testing http://www.httpbin.org/headers
         *
         * Created: 15/12/2017
         *
         * */

public class HttpBinHeadersTest {

    private static final String getUrl = "http://www.httpbin.org/headers";
    private static Logger LOG = LoggerFactory.getLogger(HttpBinHeadersTest.class.getSimpleName());


    @Test(groups = "group1")
    public void testGetDefaultBodyResponse() {
        LOG.info("Test: testGetDefaultBodyResponse");
        HttpBinResponse responseGet = HttpBinRequest
                .get(getUrl)
                .addHeader("User-Agent","Mozilla/5.0")
                .sendAndGetResponse();

        String requestMethod = responseGet.getRequestMethod();
        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();
        JSONObject responseJson = null;
        try {
            responseJson = new JSONObject(responseGet.getResponseBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Map<String, List<String>> headerFields = responseGet.getHeaderFields();

        assertThat(requestMethod).as("Request method").isEqualTo("GET");
        assertThat(responseCode).as("Response code").isEqualTo(200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");

        assertThat(headerFields.get("Server").get(0)).as("Header - Server").isEqualTo("nginx");
        assertThat(headerFields.get("Access-Control-Allow-Origin").get(0)).as("Header - Access-Control-Allow-Origin").isEqualTo("*");
        assertThat(headerFields.get("Access-Control-Allow-Credentials").get(0)).as("Header - Access-Control-Allow-Credentials").isEqualTo("true");
        assertThat(headerFields.get("Connection").get(0)).as("Header - Connection").isEqualTo("keep-alive");
        assertThat(headerFields.get("Content-Type").get(0)).as("Header - Content-Type").isEqualTo("application/json");


        JSONObject responseJsonArgs = HttpBinHelper.getJSONObjectArgs(responseJson);
        assertThat(responseJsonArgs).as("Response JSON Args").isNull();

        JSONObject responseJsonHeaders = HttpBinHelper.getJSONObjectHeaders(responseJson);

        String accept = null;
        try {
            accept = (String) responseJsonHeaders.get("Accept");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String userAgent = null;
        try {
            userAgent = (String) responseJsonHeaders.get("User-Agent");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String host = null;
        try {
            host = (String) responseJsonHeaders.get("Host");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertThat(accept).as("Response body header Accept").isEqualTo("text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
        assertThat(userAgent).as("Response body header User-Agent").isEqualTo("Mozilla/5.0");
        assertThat(host).as("Response body header Host").isEqualTo("www.httpbin.org");

        String url = null;
        try {
            url = (String) responseJson.get("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertThat(url).as("Response body url").isEqualTo(getUrl);
    }

    @Test(groups = "group1")
    public void testGetArgs1() {
        LOG.info("Test: testGetArgs1");

        Map<String, String> args = new HashMap<String, String>();
        args.put("hello1", "world1");
        args.put("hello2", "world2");

        String argsUrl = HttpBinHelper.convertArgsToString(args);

        HttpBinResponse responseGet = HttpBinRequest
                .get(getUrl + argsUrl)
                .sendAndGetResponse();

        JSONObject responseJsonArgs = null;
        try {
            responseJsonArgs = HttpBinHelper.getJSONObjectArgs(new JSONObject(responseGet.getResponseBody()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject expectedJsonArgs = null;
        try {
            expectedJsonArgs = new JSONObject(new Gson().toJson(args));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertThat(responseJsonArgs.toString()).as("JSON body args").isEqualTo(expectedJsonArgs.toString());
    }

    @Test(groups = "group1")
    public void testGetArgs2() {
        LOG.info("Test: testGetArgs2");

        Map<String, String> args = new HashMap<String, String>();
        args.put("hello1", "world1");
        args.put("hello2", "world1");

        String argsUrl = HttpBinHelper.convertArgsToString(args);

        HttpBinResponse responseGet = HttpBinRequest
                .get(getUrl + argsUrl)
                .sendAndGetResponse();

        JSONObject responseJsonArgs = null;
        try {
            responseJsonArgs = HttpBinHelper.getJSONObjectArgs(new JSONObject(responseGet.getResponseBody()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject expectedJsonArgs = null;
        try {
            expectedJsonArgs = new JSONObject(new Gson().toJson(args));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertThat(responseJsonArgs.toString()).as("JSON body args").isEqualTo(expectedJsonArgs.toString());
    }

    @Test(groups = "group1")
    public void testGetArgs3() {
        LOG.info("Test: testGetArgs3");

        HttpBinResponse responseGet = HttpBinRequest
                .get(getUrl + "?hello1=world1&hello1=world2")
                .sendAndGetResponse();

        JSONObject responseJsonArgs = null;
        try {
            responseJsonArgs = HttpBinHelper.getJSONObjectArgs(new JSONObject(responseGet.getResponseBody()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String expectedJsonArgs = "{\"hello1\":[\"world1\",\"world2\"]}";

        assertThat(responseJsonArgs.toString()).as("JSON body args").isEqualTo(expectedJsonArgs);
    }

    @Test(groups = "group1")
    public void testGetArgs4() {
        LOG.info("Test: testGetArgs4");

        HttpBinResponse responseGet = HttpBinRequest
                .get(getUrl + "?hello1=world1&hello1=world1")
                .sendAndGetResponse();

        JSONObject responseJsonArgs = null;
        try {
            responseJsonArgs = HttpBinHelper.getJSONObjectArgs(new JSONObject(responseGet.getResponseBody()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String expectedJsonArgs = "{\"hello1\":[\"world1\",\"world1\"]}";

        assertThat(responseJsonArgs.toString()).as("JSON body args").isEqualTo(expectedJsonArgs);
    }

    @Test(groups = "group1")
    public void testGetInvalidUrl() {
        LOG.info("Test: testGetInvalidUrl");

        HttpBinResponse responseGet = HttpBinRequest
                .get(getUrl + "/")
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(404);
        assertThat(responseMessage).as("Response message").isEqualTo("NOT FOUND");
    }

    @Test(groups = "group1")
    public void testGetHeaders() {
        LOG.info("Test: testGetHeaders");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Hello1", "World1");
        headers.put("Hello2", "World2");

        HttpBinResponse responseGet = HttpBinRequest
                .get(getUrl)
                .addHeader("Hello1", headers.get("Hello1"))
                .addHeader("Hello2", headers.get("Hello2"))
                .sendAndGetResponse();

        JSONObject responseJson = null;
        try {
            responseJson = new JSONObject(responseGet.getResponseBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject responseJsonHeaders = HttpBinHelper.getJSONObjectHeaders(responseJson);

        assertThat(responseJsonHeaders.has("Hello1")).as("Header - Hello1 is present").isTrue();
        assertThat(responseJsonHeaders.has("Hello2")).as("Header - Hello2 is present").isTrue();

        try {
            assertThat(responseJsonHeaders.get("Hello1")).as("Header - Hello1").isEqualTo(headers.get("Hello1"));
            assertThat(responseJsonHeaders.get("Hello2")).as("Header - Hello2").isEqualTo(headers.get("Hello2"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "group1")
    public void testGetInvalidMethod() {
        LOG.info("Test: testGetInvalidMethod");

        HttpBinResponse responseGet = HttpBinRequest
                .post(getUrl)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(405);
        assertThat(responseMessage).as("Response message").isEqualTo("METHOD NOT ALLOWED");
    }
}
