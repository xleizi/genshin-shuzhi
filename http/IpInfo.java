package http;
//import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;


public class IpInfo {
    public static String getIp(String args) throws JSONException {
        JSONObject jsonObj = new JSONObject(args);
        String ip = (String) jsonObj.get("ip");
        return ip;
    }
}
