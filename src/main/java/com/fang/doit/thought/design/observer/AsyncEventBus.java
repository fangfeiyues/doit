package com.fang.doit.thought.design.observer;

import java.util.concurrent.Executor;

/**
 * @author created by fang on 2020/7/19/019 17:06
 */

public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {

        super(executor);
    }
}
