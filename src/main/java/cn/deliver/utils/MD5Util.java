package cn.deliver.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ming
 * @date 2019/8/2 - 21:12
 */
public class MD5Util {
    public class MD5Dao {
        public  String md5(String plainText) {
            byte[] secretBytes = null;
            try {
                // 先通过MessageDigest把目标内容转换为字节数组
                secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("找不到md5算法");
            }
            // 把字节数组变为字符串
            String md5code = new BigInteger(1, secretBytes).toString(16);
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
            return md5code;
        }
    }
}