package com.fang.doit.algo.encode;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author by Feiyue on 2020/7/17 11:05 AM
 */
public class DES {

    /**
     * DES加密介绍
     * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少因为DES使用56位密钥，以现代计算能力24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
     * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
     */

    public DES() {
    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    private static final String ALGORITHM_DES = "des";

    private static SecureRandom secureRandom;

    static {
        secureRandom = new SecureRandom();
    }

    /**
     * DES加密
     *
     * @param key     秘钥key
     * @param content 待加密内容
     * @return byte[]
     */
    public static byte[] DESEncrypt(final String key, final String content) {
        return processCipher(content.getBytes(), getSecretKey(key), Cipher.ENCRYPT_MODE, ALGORITHM_DES);
    }

    /**
     * DES解密
     *
     * @param key            秘钥key
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public static byte[] DESDecrypt(final String key, final byte[] encoderContent) {
        return processCipher(encoderContent, getSecretKey(key), Cipher.DECRYPT_MODE, ALGORITHM_DES);
    }

    /**
     * 加密/解密处理
     *
     * @param processData 待处理的数据
     * @param key         提供的密钥
     * @param opsMode     工作模式
     * @param algorithm   使用的算法
     * @return byte[]
     */
    private static byte[] processCipher(final byte[] processData, final Key key,
                                        final int opsMode, final String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            //初始化
            cipher.init(opsMode, key, secureRandom);
            return cipher.doFinal(processData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据key生成秘钥
     *
     * @param key 给定key,要求key至少长度为8个字符
     * @return SecretKey
     */
    public static SecretKey getSecretKey(final String key) {
        try {
//            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("gbk"));
            SecretKeyFactory instance = SecretKeyFactory.getInstance(ALGORITHM_DES);
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
            return instance.generateSecret(desKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //测试
    public static void main(String args[]) {
        //待加密内容
        String str = "eqtyweqywesahhdkas23455";
        //密码，长度要是8的倍数
        String password = "12345678";

//        byte[] result = DES.encrypt(str.getBytes(), password);
//        System.out.println("encode:" + new String(result));
//        //直接将如上内容解密
//        try {
//            byte[] decryResult = DES.decrypt(result, password);
//            System.out.println("decode：" + new String(decryResult));
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }

        byte[] bytes = DESEncrypt(password, str);
        str = new String(bytes);
        System.out.println("encode:" + str);
        byte[] decrypt = DESDecrypt(password, parseHexStr2Byte(str));
        System.out.println("decode：" + new String(decrypt));
    }

    public static byte[] parseHexStr2Byte(String hexStr) {

        if (hexStr.length() < 1)

            return null;

        byte[] result = new byte[hexStr.length()/2];

        for (int i = 0;i< hexStr.length()/2; i++) {

            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);

            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);

            result[i] = (byte) (high * 16 + low);

        }

        return result;

    }
}
