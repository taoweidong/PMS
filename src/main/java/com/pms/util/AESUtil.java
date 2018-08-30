package com.pms.util;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author Taowd 功 能：AES加密 对称加密 编写时间：2017-4-27-下午8:57:52
 */
public class AESUtil
{
    /**
     * 对称加密秘钥：固定值
     */
    public static final String SKA = "mqqian0528";

    /**
     * Author:Taowd 功能：加密 开发日期：2017-4-27-下午8:59:39
     * 
     * @param content
     *            需要加密的内容
     * @param password
     *            加密密码
     * @return
     */
    public static byte[] encrypt(String content)
    {
        try
        {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(SKA.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Author:Taowd 功能：解密 开发日期：2017-4-27-下午9:00:11
     * 
     * @param content
     *            待解密内容
     * @param password
     *            解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content)
    {
        try
        {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(SKA.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Author:Taowd 功能：加密之后的密文转成字符串，便于数据库存储：将二进制转换成16进制存储 开发日期：2017-4-27-下午9:03:04
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[])
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++ )
        {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1)
            {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * Author:Taowd 功能：将数据串转为对应的二进制密文： 将16进制转换为二进制 开发日期：2017-4-27-下午9:03:54
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr)
    {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++ )
        {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte)(high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args)
    {
        // AES测试
        /*
         * String content = "asdhkjadhkjahdkjahdjsakhdkjahdjk"; String password = "12345678"; // 加密
         * System.out.println("加密前：" + content); byte[] encryptResult = encrypt(content, password);
         * System.out.println("密文：" + encryptResult.toString());
         * System.out.println(encryptResult.toString().getBytes()); // 解密 byte[] decryptResult =
         * decrypt(encryptResult, password); System.out.println("解密后：" + new
         * String(decryptResult));
         */

        // AES 加密测试2  21232f297a57a5a743894a0e4a801fc3  21232f297a57a5a743894a0e4a801fc3
        String content = "admin";
        // 加密
        System.out.println("加密前：" + content);
        byte[] encryptResult = encrypt(content);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        System.out.println("加密后：" + encryptResultStr);
        // 解密
        byte[] decryptResult = decrypt(parseHexStr2Byte(encryptResultStr));
        System.out.println("解密后：" + new String(decryptResult));
    }

    /** Prevent instantiation */
    private AESUtil()
    {}
}
