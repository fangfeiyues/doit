package com.fang.doit.algo.encode;

import java.util.Base64;

/**
 * @author by Feiyue on 2020/7/20 2:26 PM
 */
public class EncoderBase64 {

    /**
     * base64加密
     *
     * @param content 待加密内容
     * @return byte[]
     */
    public static byte[] base64Encrypt(final String content) {
        return Base64.getEncoder().encode(content.getBytes());
    }

    /**
     * base64解密
     *
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public static byte[] base64Decrypt(final byte[] encoderContent) {
        return Base64.getDecoder().decode(encoderContent);
    }


    public static void main(String[] args) {

        String content = "dasdhasjdhkasj";
        byte[] encoder = EncoderBase64.base64Encrypt(content);
        System.out.println(new String(encoder));

        byte[] decoder = EncoderBase64.base64Decrypt(encoder);
        System.out.println(new String(decoder));

    }

}
