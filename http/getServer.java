package http;

public class getServer {

    public static String get_server(String uid)
    {
        if (uid.startsWith("1") || uid.startsWith("2"))
        {
            return "cn_gf01";   // 天空岛
        }
        else if (uid.startsWith("5"))
        {
            return "cn_qd01";   // 世界树
        }
        else
        {
            return "";   // 空
        }
    }
}
