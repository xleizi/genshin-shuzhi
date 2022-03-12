package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class getDaily {


    public static String Daily(String uid, String cookie)
    {
        String server = getServer.get_server(uid);
        String role_id = uid;
        String body = "";
        String q = "role_id=" + role_id + "&server=" + server;
        String url = "https://api-takumi-record.mihoyo.com/game_record/app/genshin/api/dailyNote?" + q;
        return doDailyinfoGet(url, cookie, body, q);
    }

    public static String doDailyinfoGet(String httpurl, String cookie, String body, String q) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = "错误";// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：3000毫秒
            connection.setConnectTimeout(3000);
            // 设置读取远程返回的数据时间：3000毫秒
            connection.setReadTimeout(3000);
            connection.setRequestProperty("Accept", "application / json, text / plain, */*");
            connection.setRequestProperty("DS", SafeUtil1.Get_DS(body, q));
            connection.setRequestProperty("Origin", "https://webstatic.mihoyo.com");
            connection.setRequestProperty("x-rpc-app_version", "2.11.1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPad; CPU OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) miHoYoBBS/2.14.1");
            connection.setRequestProperty("x-rpc-client_type", "5");
            connection.setRequestProperty("Cookie", cookie);
            connection.setRequestProperty("Referer", "https://webstatic.mihoyo.com/app/community-game-records/index.html?v=6");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "zh-CN,en-US;q=0.8");
            connection.setRequestProperty("X-Requested-With", "com.mihoyo.hyperion");

// 发送请求
            connection.connect();

            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }
        return result;
    }
}
