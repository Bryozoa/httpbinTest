package Httpbin;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
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
         * Class HttpBinGetTest is testing http://www.httpbin.org/status/:code
         *
         * Created: 15/12/2017
         *
         * */

public class HttpBinStatusCodeTest {

    private static final String Url = "http://www.httpbin.org/status/";

    private static final int OK_200 = 200;
    private static final int NOT_FOUND_404 = 404;
    private static final int SERVICE_UNAVAILABLE_503 = 503;
    private static final int INTERNAL_SERVER_ERROR_500 = 500;

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(HttpBinStatusCodeTest.class.getSimpleName());

// Test OK status

    @Test(groups = "group1")
    public void testOkMethod() {
        LOG.info("Test: testOK_200");

        HttpBinResponse responseGet = HttpBinRequest
                .post(Url + OK_200)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(OK_200);
        assertThat(responseMessage).as("Response message").isEqualTo("OK");
    }

// Base mainstream error codes are tested here

        @Test(groups = "group1")
    public void testErrorsMethod() throws IOException {
        LOG.info("Test: test_404");

        HttpBinResponse responseGet = HttpBinRequest
                .post(Url+NOT_FOUND_404)
                .sendAndGetResponse();

        int responseCode = responseGet.getResponseCode();
        String responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(NOT_FOUND_404);
        assertThat(responseMessage).as("Response message").isEqualTo("NOT FOUND");

            LOG.info("Test: test_503");

            responseGet = HttpBinRequest
                    .post(Url + SERVICE_UNAVAILABLE_503)
                    .sendAndGetResponse();

            responseCode = responseGet.getResponseCode();
            responseMessage = responseGet.getResponseMessage();

            assertThat(responseCode).as("Response code").isEqualTo(SERVICE_UNAVAILABLE_503);
            assertThat(responseMessage).as("Response message").isEqualTo("SERVICE UNAVAILABLE");

        LOG.info("Test: test_500");

        responseGet = HttpBinRequest
                    .post(Url+INTERNAL_SERVER_ERROR_500)
                    .sendAndGetResponse();

        responseCode = responseGet.getResponseCode();
        responseMessage = responseGet.getResponseMessage();

        assertThat(responseCode).as("Response code").isEqualTo(INTERNAL_SERVER_ERROR_500);
        assertThat(responseMessage).as("Response message").isEqualTo("INTERNAL SERVER ERROR");
    }
}
