package cn.com.site.page.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: Md5Util
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2021-01-10 22:27
 */
public class Md5Util {

    private static Logger log = LoggerFactory.getLogger(Md5Util.class);

    public static String MD51(String input) {
        if(input == null || input.length() == 0) {
            return null;
        }
        try {
            MessageDigest.getInstance("MD5");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(input.getBytes());
            byte[] byteArray = md5.digest();

            BigInteger bigInt = new BigInteger(1, byteArray);
            // 参数16表示16进制
            String result = bigInt.toString(16);
            // 不足32位高位补零
            while(result.length() < 32) {
                result = "0" + result;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        }
        return null;
    }
}
