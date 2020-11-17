package com.fang.doit.algo.encode;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author by Feiyue on 2020/7/17 3:44 PM
 */
public class AES {

    //�������
    private static final String AES_TYPE = "AES/ECB/PKCS5Padding";


    /**
     * ����
     *
     * @param cleartext
     * @return
     */
    private static String encrypt(String key, String cleartext) {
        //���ܷ�ʽ�� AES128 + Base64, ˽Կ��1111222233334444
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(StandardCharsets.UTF_8));
            return new BASE64Encoder().encode(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * ����
     *
     * @param encrypted
     * @return
     */
    public static String decrypt(String key, String encrypted) {
        try {
            byte[] byteMi = new BASE64Decoder().decodeBuffer(encrypted);
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            //�����ʱ��ͬMODE:Cipher.DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * ����
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String content = "87455f0bf6ba40cdasdasfsdgdfgd";
//        87455f0bf6ba40c180b0942bc075cbaa, fn95yvtt44, 2826ae5939
        String key = "1111222233334444";
        System.out.println("keyֵ��" + key);
        String encryptResult = encrypt(key, content);
        System.out.println("���ܺ�" + encryptResult);
        String decryptResult = decrypt(key, encryptResult);
        System.out.println("������ɺ�" + decryptResult);
    }


}



