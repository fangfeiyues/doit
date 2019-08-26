package com.fang.doit.base.runorder;

/**
 * @author created by fang on 2019/8/24/024 10:49
 */
public class B extends A {

    int b1 = 0;
    int b2 = getB2();

    {
        int b3 = 5;
        System.out.println("top of B() b1=" + b1 + " b2=" + b2 + " b3=" + b3);

    }

    public B() {
//        this(33);
         super(44); //���super��䣬�ᵼ��ʵ����ʱֱ��ִ�и���������Ĺ��캯��
        System.out.print("B ���캯��\n");
    }

    public B(int num) {
        // ���super��䣬�ᵼ��ʵ����ʱֱ��ִ�и���������Ĺ��캯��
        // ǰ���Ǵ������Ĺ��캯��B�ᱻ���У�newʵ������this��
        // super(77);

        System.out.print("B ���������캯��:" + num + "\n");
    }

    {
        System.out.println("below B()..has start");
    }

    static {
        System.out.println("I`m a static {} from class B..");
    }

    int getB2() {
        System.out.println("getB2..");
        return 33;

    }

    @Override
    public void methodA() {
        System.out.println("methoaA int class B");
        super.methodA();

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("main app run..");
        B b = new B();
//		B b = new B(22);
        b.methodA();
    }


}
