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
     * DES���ܽ���
     * DES��һ�ֶԳƼ����㷨����ν�ԳƼ����㷨�������ܺͽ���ʹ����ͬ��Կ���㷨��DES�����㷨����IBM���о�������������������ʽ���ã�֮��ʼ�㷺���������ǽ�Щ��ʹ��Խ��Խ����ΪDESʹ��56λ��Կ�����ִ���������24Сʱ�ڼ��ɱ��ƽ⡣��Ȼ��ˣ���ĳЩ��Ӧ���У����ǻ��ǿ���ʹ��DES�����㷨�����ļ򵥽���DES��JAVAʵ��
     * ע�⣺DES���ܺͽ��ܹ����У���Կ���ȶ�������8�ı���
     */

    public DES() {
    }

    /**
     * ����
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //����һ���ܳ׹�����Ȼ��������DESKeySpecת����
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher����ʵ����ɼ��ܲ���
            Cipher cipher = Cipher.getInstance("DES");
            //���ܳ׳�ʼ��Cipher����
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //���ڣ���ȡ���ݲ�����
            //��ʽִ�м��ܲ���
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES�㷨Ҫ����һ�������ε������Դ
        SecureRandom random = new SecureRandom();
        // ����һ��DESKeySpec����
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // ����һ���ܳ׹���
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // ��DESKeySpec����ת����SecretKey����
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher����ʵ����ɽ��ܲ���
        Cipher cipher = Cipher.getInstance("DES");
        // ���ܳ׳�ʼ��Cipher����
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // ������ʼ���ܲ���
        return cipher.doFinal(src);
    }

    private static final String ALGORITHM_DES = "des";

    private static SecureRandom secureRandom;

    static {
        secureRandom = new SecureRandom();
    }

    /**
     * DES����
     *
     * @param key     ��Կkey
     * @param content ����������
     * @return byte[]
     */
    public static byte[] DESEncrypt(final String key, final String content) {
        return processCipher(content.getBytes(), getSecretKey(key), Cipher.ENCRYPT_MODE, ALGORITHM_DES);
    }

    /**
     * DES����
     *
     * @param key            ��Կkey
     * @param encoderContent �Ѽ�������
     * @return byte[]
     */
    public static byte[] DESDecrypt(final String key, final byte[] encoderContent) {
        return processCipher(encoderContent, getSecretKey(key), Cipher.DECRYPT_MODE, ALGORITHM_DES);
    }

    /**
     * ����/���ܴ���
     *
     * @param processData �����������
     * @param key         �ṩ����Կ
     * @param opsMode     ����ģʽ
     * @param algorithm   ʹ�õ��㷨
     * @return byte[]
     */
    private static byte[] processCipher(final byte[] processData, final Key key,
                                        final int opsMode, final String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            //��ʼ��
            cipher.init(opsMode, key, secureRandom);
            return cipher.doFinal(processData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����key������Կ
     *
     * @param key ����key,Ҫ��key���ٳ���Ϊ8���ַ�
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

    //����
    public static void main(String args[]) {
        //����������
        String str = "eqtyweqywesahhdkas23455";
        //���룬����Ҫ��8�ı���
        String password = "12345678";

//        byte[] result = DES.encrypt(str.getBytes(), password);
//        System.out.println("encode:" + new String(result));
//        //ֱ�ӽ��������ݽ���
//        try {
//            byte[] decryResult = DES.decrypt(result, password);
//            System.out.println("decode��" + new String(decryResult));
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }

        byte[] bytes = DESEncrypt(password, str);
        str = new String(bytes);
        System.out.println("encode:" + str);
        byte[] decrypt = DESDecrypt(password, parseHexStr2Byte(str));
        System.out.println("decode��" + new String(decrypt));
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
