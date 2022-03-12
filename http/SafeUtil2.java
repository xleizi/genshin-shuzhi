package http;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Random;

public class SafeUtil2 {

    public static String Get_DS()
    {
        String n = "cx2y9z9a29tfqvr1qsq6c7yz99b5jsqt";
        String i = GetTimeStamp();
        String r = getRandomCharacterAndNumber(6);
        String c = GenerateMD5(MessageFormat.format("salt={0}&t={1}&r={2}", n, i, r));
        return MessageFormat.format("{0},{1},{2}", i, r, c);
    }

    public static String GetRandomString()
    {
        Random rand = new Random();         //定义一个随机数生成
        int index = rand.nextInt(100000) + 100000;     //生成一个随机数
        return String.valueOf(index);
    }

    public static String getRandomCharacterAndNumber(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
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
