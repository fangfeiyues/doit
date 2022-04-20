package com.fang.doit.algo.lc.stage1;

import java.util.Objects;
import java.util.Stack;

/**
 * 栈
 *
 * @author fangfeiyue
 * @Date 2020/11/28 4:40 下午
 */
public class Stack01 {
    /**
     * Stack基本函数
     * 1.pop(), Removes the object at the top of this stack and returns that object as the value of this function.
     * 2.peek(), Looks at the object at the top of this stack without removing it from the stack.
     * 3.push(), Pushes an item onto the top of this stack, the same effect as: addElement(item)
     */

    /**
     * 1209 删除字符串所有邻的重复项
     *
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates(String s, int k) {
        if ("".equals(s) || s == null) {
            return null;
        }
        Stack<CharBlock> stack = new Stack<>();
        // 这里有个怎么存储重复的数字问题。1.Map<String,Integer>；2.两个Stack一个存字符一个存数量；3.加个对象存储字符与次数
        for (char c : s.toCharArray()) {
            String chars = String.valueOf(c);
            if (stack.empty()) {
                stack.push(new CharBlock(chars, 1));
                continue;
            }
            CharBlock top = stack.pop();
            if (!Objects.equals(top.getS(), chars)) {
                stack.push(top);
                stack.push(new CharBlock(chars, 1));
                continue;
            }
            if (Objects.equals(top.getS(), chars) && top.getC() < k - 1) {
                top.incr();
                stack.push(top);
                continue;
            }
            if (Objects.equals(top.getS(), chars) && top.getC() >= k - 1) {
                continue;
            }
        }
        return getResult(stack);
    }

    private String getResult(Stack<CharBlock> stack) {
        Stack<CharBlock> result = new Stack();
        while (!stack.empty()) {
            result.push(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while (!result.empty()) {
            CharBlock pop = result.pop();
            for (int i = 1; i <= pop.getC(); i++) {
                sb.append(pop.getS());
            }
        }
        return sb.toString();
    }

    class CharBlock {
        private String s;
        private Integer c;

        public CharBlock(String s, Integer c) {
            this.s = s;
            this.c = c;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public Integer getC() {
            return c;
        }

        public void setC(Integer c) {
            this.c = c;
        }

        public void incr() {
            this.c++;
        }
    }

    public static void main(String[] args) {
        Stack01 stack01 = new Stack01();
        System.out.println(stack01.removeDuplicates("deeedbbcccbdaa", 3));
    }

}
