package Httpbin;

import java.util.List;
import java.util.Map;


        /* Imported libraries: https://www.slf4j.org/download.html
         *
         * Class HttpBinResponse is reading JSON objects.
         *
         * Created: 15/12/2017
         *
         * */

public class HttpBinResponse {

    private int responseCode;
    private String responseMessage;
    private String responseBody;
    private String requestMethod;
    private Map<String, List<String>> headerFields;

    public HttpBinResponse(final int responseCode, final String responseMessage, final String responseBody,
                        final String requestMethod, final Map<String, List<String>> headerFields) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseBody = responseBody;
        this.requestMethod = requestMethod;
        this.headerFields = headerFields;
    }

    public final Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public final String getRequestMethod() {
        return requestMethod;
    }

    public final String getResponseMessage() {
        return responseMessage;
    }

    public final int getResponseCode() {
        return responseCode;
    }

    public final String getResponseBody() {
        return responseBody;
    }
}
