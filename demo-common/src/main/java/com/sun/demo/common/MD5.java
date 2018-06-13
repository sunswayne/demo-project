package com.sun.demo.common;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5 {

    /**
     * 把字符串进行MD5加密
     *
     * @param string 字符串
     * @return MD5加密后的字符串
     */
    public static String encode(String string) {
        String encode = defaultEncode(string);
        StringBuilder sb = new StringBuilder();
        sb.append("125639751@qq.com");
        for (int i = 0, length = encode.length() / 2; i < length; i++) {
            sb.append(encode.charAt(i * 2 + 1));
            sb.append(encode.charAt(i * 2));
        }
        return defaultEncode(sb.toString());
    }

    /**
     * 把字符串进行MD5加密
     *
     * @param string 字符串
     * @return MD5加密后的字符串
     */
    public static String defaultEncode(String string) {
        StringBuilder sb = new StringBuilder(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashValue = md.digest(string.getBytes());
            for (int i = 0; i < hashValue.length; i++) {
                sb.append(Integer.toHexString((hashValue[i] & 0xf0) >> 4));
                sb.append(Integer.toHexString(hashValue[i] & 0x0f));
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return sb.toString();
    }

    public static byte[] encrypt(String data) throws IOException {
        return encrypt(data.getBytes("utf-8"));
    }

    public static byte[] encrypt(byte[] data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data);
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    public static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    public String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public void writeStringToFile(String filePath, String md5) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(md5);// 往文件里写入字符串
//            ps.append("http://www.jb51.net");// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
