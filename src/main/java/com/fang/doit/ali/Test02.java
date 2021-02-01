package com.fang.doit.ali;

import java.util.concurrent.*;

/**
 * @author fangfeiyue
 * @Date 2021/1/31 8:17 下午
 */
public class Test02 {

    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    /**
     * 代码实现生产者和消费者模式。要求保证单机的环境下生产和消费效率
     */
    public void producer(String content) {
        try {
            while (running) {
                queue.offer(content, 2, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
//            log.info("producer InterruptedException:{}", e);
        }
    }


    public void stop() {
        running = false;
    }

    public void consume() {
        while (running) {
            try {
                String content = queue.take();
                System.out.println("消费内容:" + content);
            } catch (InterruptedException e) {
//                log.info("consume InterruptedException:{}", e);
            }
        }
    }
}
