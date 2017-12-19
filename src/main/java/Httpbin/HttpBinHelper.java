package Httpbin;

import org.json.JSONException;
import org.json.JSONObject;

        /* Imported libraries: http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm
         *
         * Class HttpBinHelper is receiving and  processing JSON objects.
         *
         * Created: 15/12/2017
         *
         *
         * */

public class HttpBinHelper {

    protected static final JSONObject getJSONObjectHeaders(final JSONObject jsonObject) {
        JSONObject responseJsonHeaders = new JSONObject();
        try {
            if (((JSONObject) jsonObject.get("headers")).length() == 0) {
                responseJsonHeaders = null;
            } else {
                responseJsonHeaders = (JSONObject) jsonObject.get("headers");
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return responseJsonHeaders;
    }

}
