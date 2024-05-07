package com.fang.doit.design.chain;

/**
 * created by fang on 2018/7/29/029 23:22
 */
public class Zhuren extends Leader {

    public Zhuren(String name) {
        super(name);
    }

    @Override
    public void handleRequest(QingjiaRequest qingjiaRequest) {
        if (qingjiaRequest.getDays() < 3) {
            System.out.println("员工请假小于3天");
            System.out.println("主任审批通过");
        } else {
            if (this.nextLeader != null) {
                this.nextLeader.handleRequest(qingjiaRequest);
            }
        }


    }

}