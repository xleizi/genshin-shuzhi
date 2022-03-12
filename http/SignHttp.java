package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignHttp {
    String act_id = "e202009291139501";
    String cookie;
    String uid;
    String url_sign = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/sign";
    String url_award = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/home?act_id=" + act_id;
    String url_info = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/info?act_id=" + act_id + "&region=cn_gf01&uid=";


    public static String doPost(String httpUrl, String cookie, String param) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = "错误";
        try {
            // 通过远程url连接对象打开连接
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("POST");
            // 设置连接主机服务器的超时时间：3000毫秒
            connection.setConnectTimeout(3000);
            // 设置读取远程返回的数据时间：3000毫秒
            connection.setReadTimeout(3000);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Accept", "application / json, text / plain, */*");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0.1; MuMu Build/V417IR; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36 miHoYoBBS/2.4.0");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("x-rpc-device_id", "fa498beb-eddf-345d-84e1-a3145b225309");
            connection.setRequestProperty("x-rpc-client_type", "5");
            connection.setRequestProperty("x-rpc-app_version", "2.2.1");
            connection.setRequestProperty("Cookie", cookie);

            connection.setRequestProperty("DS", SafeUtil2.Get_DS());
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
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
            if (null != os) {
                try {
                    os.close();
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
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }

    public static String doWithCookiesGet(String httpurl, String cookies) {
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
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            //connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50");
            connection.setRequestProperty("Cookie", cookies);
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


    public static String doSignGet(String httpurl, String cookie) {
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
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Accept", "application / json, text / plain, */*");
            //connection.setRequestProperty("DS", "1635836822,166301,41b941820e7d47adb687294a8b19db12");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0.1; MuMu Build/V417IR; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36 miHoYoBBS/2.4.0");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("x-rpc-device_id", "fa498beb-eddf-345d-84e1-a3145b225309");
            connection.setRequestProperty("x-rpc-client_type", "5");
            connection.setRequestProperty("x-rpc-app_version", "2.2.1");
            connection.setRequestProperty("Cookie", cookie);

            connection.setRequestProperty("DS", SafeUtil2.Get_DS());

            //connection.setRequestProperty("Origin", "https://webstatic.mihoyo.com");
            //connection.setRequestProperty("x-rpc-client_type", "5");
            //connection.setRequestProperty("Referer", "https://webstatic.mihoyo.com/app/community-game-records/index.html?v=6");
            //connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            //connection.setRequestProperty("Accept-Language", "zh-CN,en-US;q=0.8");
            //connection.setRequestProperty("X-Requested-With", "com.mihoyo.hyperion");

            // 发送请求
            connection.connect();
            result = "1";
            // 通过connection连接，获取输入流
            //if (connection.getResponseCode() == 200) {
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
            //}
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = "2";
        } catch (IOException e) {
            e.printStackTrace();
            result = "3";
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
