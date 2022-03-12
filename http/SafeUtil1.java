package http;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Random;

public class SafeUtil1 {

    public static String Get_DS(String body, String q)
    {
        String n = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs";
        String i = GetTimeStamp();
        String r = GetRandomString();
        String c = GenerateMD5(MessageFormat.format("salt={0}&t={1}&r={2}&b={3}&q={4}", n, i, r, body, q));
        return MessageFormat.format("{0},{1},{2}", i, r, c);
    }

    public static String GetRandomString()
    {
        Random rand = new Random();         //定义一个随机数生成
        int index = rand.nextInt(100000) + 100000;     //生成一个随机数
        return String.valueOf(index);
    }
    final long l = System.currentTimeMillis();

    public static String GetTimeStamp()
    {
        final long ts = System.currentTimeMillis()/1000;
        return String.valueOf(ts);
    }

    public static String GenerateMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }


}
