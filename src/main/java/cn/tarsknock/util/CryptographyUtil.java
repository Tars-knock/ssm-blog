package cn.tarsknock.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptographyUtil {
    /**
     * md5加密
     * 未启用
     */
    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("1","java12"));
    }
}
