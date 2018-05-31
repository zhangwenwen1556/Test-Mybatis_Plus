package com.example.testmybatisplus.utils;

import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.bcrypt.BCrypt;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密 工具类
 *
 * @author dion.li
 * @version v1.0.0
 * @date 2018/03/10 10:41
 */
public class EncryptUtil {

    /**
     * 加密常量字符串
     */
    public static final String ENCRYPT_CONSTENT = "JIA_HUI";

    /**
     * sha加密
     */
    public static String sha(String inputText) {
        return encrypt(inputText, "sha-1");
    }

    /**
     * md5加密
     */
    public static String md5(String inputText) {
        return encrypt(inputText, "md5");
    }


    /**
     * md5或者sha-1加密
     *
     * @param inputText     要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     */
    private static String encrypt(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte s[] = m.digest();
            // m.digest(inputText.getBytes("UTF8"));
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    // 返回十六进制字符串
    public static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /***
     * MD5加密 生成32位md5码
     *
     * @param inStr 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encrypt(String inStr, Charset charset) throws NoSuchAlgorithmException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes(charset);
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    /**
     * @author ming
     * @date 2017-10-24 11:51
     */
    public static String md5AndSaltEncrypt(String inStr, String salt, Charset charset) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (messageDigest == null) {
            throw new NullPointerException();
        }
        messageDigest.update(inStr.getBytes(charset));
        messageDigest.update(salt.getBytes(charset));
        byte[] digestBytes = messageDigest.digest();
        return Hex.encodeHexString(digestBytes);
    }

    /**
     * @defaultparam Charset = utf-8
     * @author ming
     * @date 2017-10-24 13:56
     */
    public static String md5AndSaltEncryptByUTF8(String inStr, String slat) {
        return md5AndSaltEncrypt(inStr, slat, Charset.forName("UTF-8"));
    }


    /**
     * BCrypt算法加密
     *
     * @param pwd 密码
     */
    public static String bCryptEncrypt(String pwd) {
        return BCrypt.hashpw(pwd, BCrypt.gensalt(16));
    }


    /**
     * BCrypt算法校验
     */
    public static boolean bCryptEncryptCheck(String pwd, String encryptPwd) {
        return BCrypt.checkpw(pwd, encryptPwd);
    }


    /**
     * MD5 64位加密算法
     */
    public static final String md5Encrypt(String pwd) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(md5.digest(pwd.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

}