package com.fang.doit.algo.string;

import com.fang.aads.algo.link.Solution;

import java.util.LinkedList;

/**
 * @author created by fang on 2020/5/19/019 23:05
 */
public class StringSolution {

    /**
     * ����һ���ǿ��ַ��� s�����ɾ��һ���ַ����ж��Ƿ��ܳ�Ϊ�����ַ���
     * <p>
     * ������: ��������������һ���������������������֣�98789���������������98789 ����Ҳ��98789��ż��������3223Ҳ�ǻ���������ĸ abcba Ҳ�ǻ���
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int count = 0;
        int length = s.length();

        for (int i = 0; i < length; i++) {
            if (s.toCharArray()[i] != s.toCharArray()[length - 1 - i]) {

                if (s.toCharArray()[i + 1] == s.toCharArray()[length - 1 - i]) {

                }
            }
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/decode-string
     *
     * <p>
     * ����һ������������ַ������������������ַ���
     * �������Ϊ: k[encoded_string]����ʾ���з������ڲ��� encoded_string �����ظ� k �Ρ�ע�� k ��֤Ϊ��������
     * <p>
     *
     * @param s sd3[qa5[op]ry]
     * @return
     */
    public String decodeString(String s) {

        // 1.ջ
        // ���֣�[����ĸ��ջ��
        // ] ��ʼ��ջ
        
        LinkedList<String> stk = new LinkedList<>();

        int cur = 0;
        while (cur < s.length()) {
            char chars = s.charAt(cur);

            // ��ĸ��ջ
            if (Character.isDefined(chars)) {
//                stk.addLast(chars);

            }
        }


        return "";
    }


}
