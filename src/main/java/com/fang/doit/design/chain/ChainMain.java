package com.fang.doit.design.chain;

/**
 * 责任链模式  next连接下一个
 * created by fang on 2018/7/29/029 23:23
 */
public class ChainMain {

    public static void main(String[] args) {

        //QingjiaRequest qingjiaRequest = new QingjiaRequest("张三", 2, "请假");
        QingjiaRequest qingjiaRequest = new QingjiaRequest("张三", 5, "请假");

        Leader a = new Zhuren("张主任");
        Leader b = new Manager("王经理");

        //可以用一个容器对象责任链对象进行一些封装
        a.setNextLeader(b);
        //开始请假
        a.handleRequest(qingjiaRequest);

    }
}
