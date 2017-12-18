package Httpbin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
         * Class HttpBinGetTest is testing http://www.httpbin.org/redirect/:n where n is a number of redirects
         *
         * Created: 15/12/2017
         *
         * */

public class HttpBinRedirectTest {

    private static final String Url = "http://www.httpbin.org/redirect/";
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(HttpBinRedirectTest.class.getSimpleName());

    //TODO these stub tests to be a redirect counter

    @Test(groups = "group2")
    public void testResponseRedirect() {
        LOG.info("Test: testResponseRedirect");

        HttpBinResponse responseGet = HttpBinRequest
                .get(Url)
                .sendAndGetResponse();

    }

    @Test(groups = "group2")
    public void testRedirectInParameters() {
        LOG.info("Test: testResponseHeadersInParameters1");

        HttpBinResponse responseGet = HttpBinRequest
                .get(Url + 10)
                .sendAndGetResponse();


    }

    @Test(groups = "group1")
    public void testInvalidMethod() {
        LOG.info("Test: testGetInvalidMethod");

        HttpBinResponse responseGet = HttpBinRequest
                .post(Url)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(404);
        assertThat(responseMessage).as("Response message").isEqualTo("NOT FOUND");
    }
}
