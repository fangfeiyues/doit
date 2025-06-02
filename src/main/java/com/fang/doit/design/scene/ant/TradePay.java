package com.fang.doit.design.scene.ant;

public class TradePay {

    /**
     * 场景：支付成功: 更新订单状态 -> 扣减库存 -> 增加商家余额，要求99.99%成功率
     *
     * 1、采用TCC模式（Try-Confirm-Cancel）：Try阶段预冻结库存/余额，Confirm阶段异步执行更新，失败则触发Cancel回滚
     * 2、结合RocketMQ事务消息：支付成功消息入队，消费者幂等处理下游服务，消息堆积时动态扩容消费者 -> 消息
     * 3、降级策略：峰值期将库存扣减改为缓存预扣减（Redis+Lua），峰后同步DB
     */


    public boolean afterPay() {



        return false;
    }

    /**
     * 事务协调者 transaction coordination
     */
    static class TC{

        public boolean tc_try(){

            // 1、save record

            // 2、try_a
            State state = try_a();
            if(state == State.FAIL){

                // cancel

            }

            return false;
        }


        public State try_a(){

            // 1、save record

            // 2、try_a


            return State.UNKNOWN;
        }

        // confirm

        // cancel

    }

     enum State{

        SUCCESS,FAIL, UNKNOWN
    }

    static class Order {

        public boolean update() {

            return false;
        }

    }


    static class Stock {

        public boolean update(int nums) {

            return false;
        }

    }


    static class Shop {
        public boolean addAmount(int amount) {

            return false;
        }

    }
}
