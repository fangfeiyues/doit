package com.fang.doit.design.chain;

/**
 * created by fang on 2018/7/29/029 23:22
 */
public class Manager extends Leader {

    public Manager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(QingjiaRequest qingjiaRequest) {
        if (qingjiaRequest.getDays() < 10 && qingjiaRequest.getDays() > 3) {
            System.out.println("经理审批通过");
        } else {
            //经理处理后的逻辑
        }


    }

}
