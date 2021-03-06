package Httpbin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* Imported libraries: https://www.slf4j.org/download.html
*
* Class HttpBinRequest is receiving JSON objects.
*
* Created: 15/12/2017
*
* */

public class HttpBinRequest {

    private static final  Logger LOG = LoggerFactory.getLogger(HttpBinRequest.class.getSimpleName());

    private HttpURLConnection httpURLConnection;
    private String url;
    private HTTP_METHOD method;

    public HttpBinRequest(final String stringUrl, final HTTP_METHOD method) {
        try {
            this.url = stringUrl;
            URL url = new URL(stringUrl);
            this.httpURLConnection = (HttpURLConnection) url.openConnection();
            this.httpURLConnection.setRequestMethod(method.toString());
            this.method = method;

        } catch (Exception e) {
            System.err.println("HTTP httpURLConnection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static HttpBinRequest get(final String url) {
        return new HttpBinRequest(url, HTTP_METHOD.GET);
    }

    public static HttpBinRequest post(final String url) {
        return new HttpBinRequest(url, HTTP_METHOD.POST);
    }

    public final HttpBinRequest addHeader(final String key, final String value) {
        this.httpURLConnection.setRequestProperty(key, value);
        return this;
    }

    public final HttpBinResponse sendAndGetResponse() {
        HttpBinResponse httpResponse = null;
        BufferedReader in = null;
        try {
            int responseCode = this.httpURLConnection.getResponseCode();

            String responseMessage = this.httpURLConnection.getResponseMessage();
            String requestMethod = this.httpURLConnection.getRequestMethod();
            Map<String, List<String>> headerFields = this.httpURLConnection.getHeaderFields();

            String responseBody = null;
            if (!(responseCode == 404 || responseCode == 503 || responseCode == 500)) {
                in = new BufferedReader(
                        new InputStreamReader(this.httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                responseBody = response.toString();
            }
            httpResponse = new HttpBinResponse(responseCode, responseMessage, responseBody, requestMethod, headerFields);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (this.httpURLConnection != null) {
                this.httpURLConnection.disconnect();
            }
        }
        return httpResponse;
    }

    public static final int countRedirects(String url, int hopsNumber) {
        int Redirects = 0;
        try {

            HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
            connection.setInstanceFollowRedirects(false);
            connection.connect();
            int responseCode = connection.getResponseCode();
            System.out.println("Original Url" + "" + url + responseCode);


            while (responseCode != 200) {
                    String newUrl = "http://www.httpbin.org" + connection.getHeaderField("Location");
                    System.out.println("newUrl: " + newUrl);
                    HttpURLConnection conn = (HttpURLConnection) new URL(newUrl).openConnection();
                    connection.setInstanceFollowRedirects(false);
                    responseCode = conn.getResponseCode();
                    Redirects++;
                    System.out.println("Redirects: " + Redirects);
                    System.out.println("location is" + newUrl);
                    System.out.println("number of Hops before Reaching " + conn.getURL() + "is" + Redirects);

            }

        }
        catch (Exception e){
        e.printStackTrace();
        }
        return Redirects;
    }

    public enum HTTP_METHOD {
        GET, PUT, POST, DELETE, OPTIONS, HEAD, CONNECT, TRACE
    }

    public final String getUrl() {
        return this.url;
    }
}
