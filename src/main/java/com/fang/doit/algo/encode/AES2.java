package com.fang.doit.algo.encode;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author by Feiyue on 2020/7/17 4:14 PM
 */
public class AES2 {

    //ƫ����
    public static final String VIPARA = "1234567876543210";   //AES Ϊ16bytes. DES Ϊ8bytes

    //���뷽ʽ
    public static final String CODE_TYPE = "UTF-8";
    //public static final String CODE_TYPE = "GBK";

    //�������
    public static final String AES_TYPE = "AES/ECB/PKCS5Padding";
    //public static final String AES_TYPE = "AES/ECB/PKCS7Padding";
    //������ ��������,��Կ����Ϊ16�ֽڵı���λ,�������쳣,��Ҫ�ֽڲ�ȫ�ٽ��м���
    //public static final String AES_TYPE = "AES/ECB/NoPadding";
    //java ��֧��ZeroPadding
    //public static final String AES_TYPE = "AES/CBC/ZeroPadding";

    //˽Կ
    private static final String AES_KEY = "1111222233334444";   //AES�̶���ʽΪ128/192/256 bits.����16/24/32bytes��DES�̶���ʽΪ128bits����8bytes��

    //�ַ���ȫ
    private static final String[] consult = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G"};


    /**
     * ����
     *
     * @param cleartext
     * @return
     */
    public static String encrypt(String cleartext) {
        //���ܷ�ʽ�� AES128(CBC/PKCS5Padding) + Base64, ˽Կ��1111222233334444
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            //������������һ��Ϊ˽Կ�ֽ����飬 �ڶ���Ϊ���ܷ�ʽ AES����DES
            SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
            //ʵ���������࣬����Ϊ���ܷ�ʽ��Ҫдȫ
            Cipher cipher = Cipher.getInstance(AES_TYPE); //PKCS5Padding��PKCS7PaddingЧ�ʸߣ�PKCS7Padding��֧��IOS�ӽ���
            //��ʼ�����˷������Բ������ַ�ʽ���������㷨Ҫ������ӡ���1���޵�����������2������������ΪSecureRandom random = new SecureRandom();��random�����������(AES���ɲ������ַ���)��3�����ô˴����е�IVParameterSpec
            //����ʱʹ��:ENCRYPT_MODE;  ����ʱʹ��:DECRYPT_MODE;
            cipher.init(Cipher.ENCRYPT_MODE, key); //CBC���͵Ŀ����ڵ�������������ƫ����zeroIv,ECBû��ƫ����
            //���ܲ���,���ؼ��ܺ���ֽ����飬Ȼ����Ҫ���롣��Ҫ����뷽ʽ��Base64, HEX, UUE,7bit�ȵȡ��˴�����������Ҫʲô���뷽ʽ
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(CODE_TYPE));

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
    public static String decrypt(String encrypted) {
        try {
            byte[] byteMi = new BASE64Decoder().decodeBuffer(encrypted);
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(
                    AES_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            //�����ʱ��ͬMODE:Cipher.DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, key);  //CBC���͵Ŀ����ڵ�������������ƫ����zeroIv,ECBû��ƫ����
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, CODE_TYPE);
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
        String content = "��Ц������ÿ�";
        test(content);
    }

    public static void test(String content) throws UnsupportedEncodingException {
        System.out.println("�������ݣ�" + content);
        //�ֽ���
        int num = content.getBytes(CODE_TYPE).length;
        System.out.println("���������ֽ���: " + num);
        System.out.println("���������Ƿ�16����: " + (num % 16 == 0 ? true : false));

        //�ֽڲ�ȫ
        if (AES_TYPE.equals("AES/ECB/NoPadding")) {
            System.out.println();
            content = completionCodeFor16Bytes(content);
            System.out.println("�������ݲ�ȫ��: " + content);
        }

        System.out.println();

        // ����
        String encryptResult = encrypt(content);
        content = new String(encryptResult);
        System.out.println("���ܺ�" + content);

        System.out.println();

        // ����
        String decryptResult = decrypt(encryptResult);
        content = new String(decryptResult);
        //��ԭ
        if (AES_TYPE.equals("AES/ECB/NoPadding")) {
            System.out.println("�������ݻ�ԭǰ: " + content);
            content = resumeCodeOf16Bytes(content);
        }

        System.out.println("������ɺ�" + content);
    }


    //NoPadding
    //��ȫ�ַ�
    public static String completionCodeFor16Bytes(String str) throws UnsupportedEncodingException {
        int num = str.getBytes(CODE_TYPE).length;
        int index = num % 16;
        //���м������ݲ�ȫ����, ��������Ӧ��Ϊ 16�ֽڵı���, ������16*n�ֽ��ǽ��в�ȫ, ��һλʱ ��ȫ16+1λ
        //��ȫ�ַ� �� $ ��ʼ,$��һλ����$��ȫ�ַ�λ��,֮��ȫ����0���в�ȫ;
        if (index != 0) {
            StringBuffer sbBuffer = new StringBuffer(str);
            if (16 - index == 1) {
                sbBuffer.append("$" + consult[16 - 1] + addStr(16 - 1 - 1));
            } else {
                sbBuffer.append("$" + consult[16 - index - 1] + addStr(16 - index - 1 - 1));
            }
            str = sbBuffer.toString();
        }
        return str;
    }

    //׷���ַ�
    public static String addStr(int num) {
        StringBuffer sbBuffer = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            sbBuffer.append("0");
        }
        return sbBuffer.toString();
    }


    //��ԭ�ַ�(�����ַ��ж�)
    public static String resumeCodeOf16Bytes(String str) {
        int indexOf = str.lastIndexOf("$");
        if (indexOf == -1) {
            return str;
        }
        String trim = str.substring(indexOf + 1, indexOf + 2).trim();
        int num = 0;
        for (int i = 0; i < consult.length; i++) {
            if (trim.equals(consult[i])) {
                num = i;
            }
        }
        if (num == 0) {
            return str;
        }
        return str.substring(0, indexOf).trim();
    }

}

