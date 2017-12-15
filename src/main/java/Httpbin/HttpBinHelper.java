package Httpbin;

import org.json.JSONException;
import org.json.JSONObject;

/* Imported libraries: http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm
*
* Class HttpBinHelper is receiving and  processing JSON objects.
*
* */

import java.util.Map;

public class HttpBinHelper {
    protected static final String convertArgsToString(final Map<String, String> args) {
        StringBuffer sb = new StringBuffer("?");

        Object[] values = args.values().toArray();
        Object[] keys = args.keySet().toArray();


        for (int i = 0; i < values.length; i++) {
            sb.append(keys[i]);
            sb.append("=");
            sb.append(values[i]);
            if (i != values.length - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

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

    protected static final JSONObject getJSONObjectArgs(final JSONObject jsonObject) {
        JSONObject responseJsonArgs = new JSONObject();
        try {
            if (((JSONObject) jsonObject.get("args")).length() == 0) {
                responseJsonArgs = null;
            } else {
                responseJsonArgs = (JSONObject) jsonObject.get("args");
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return responseJsonArgs;
    }

    protected final JSONObject getJSONObjectJson(final JSONObject jsonObject) {
        JSONObject responseJsonJson = new JSONObject();

        try {
            if ((jsonObject.get("json").toString().equals("null")) ||
                    (((JSONObject) jsonObject.get("json")).length() == 0)) {
                responseJsonJson = null;
            } else {
                responseJsonJson = (JSONObject) jsonObject.get("json");
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return responseJsonJson;
    }

    protected final JSONObject getJSONObjectForm(final JSONObject jsonObject) {
        JSONObject responseJsonForm = new JSONObject();
        try {
            if (((JSONObject) jsonObject.get("form")).length() == 0) {
                responseJsonForm = null;
            } else {
                responseJsonForm = (JSONObject) jsonObject.get("form");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseJsonForm;
    }

    protected final JSONObject getJSONObjectFiles(final JSONObject jsonObject) {
        JSONObject responseJsonFiles = new JSONObject();
        try {
            if (((JSONObject) jsonObject.get("files")).length() == 0) {
                responseJsonFiles = null;
            } else {
                responseJsonFiles = (JSONObject) jsonObject.get("files");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseJsonFiles;
    }

}
