package com.heyjia.seckill.Util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    private static String salt = "1a2b3c4d";
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    public static String inputToServer(String src){
        String str = "" + salt.charAt(0) + salt.charAt(2) + src +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    public static String ServerToDB(String src,String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + src +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    public static String inputToDB(String src,String randomsalt) {
        String serverPass = inputToServer(src);
        String dbPass = ServerToDB(serverPass,randomsalt);
        return dbPass;
    }
    public static void main(String [] args) {
        //d3b1294a61a07da9b49b6e22b2cbd7f9
        //d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(inputToServer("123456"));
        //b7797cce01b4b131b433b6acf4add449
        System.out.println(ServerToDB(inputToServer("123456"),"1a2b3c4d"));
    }
}
