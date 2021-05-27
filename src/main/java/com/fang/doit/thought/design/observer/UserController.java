package com.fang.doit.thought.design.observer;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author created by fang on 2020/7/19/019 17:08
 */

public class UserController {

//    private UserService userService; // 依赖注入

    private EventBus eventBus;

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;

    public UserController() {
        // 同步阻塞模式
        //eventBus = new EventBus();

        // 异步非阻塞模式
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));
    }

    public void setRegObservers(List<Object> observers) {
        for (Object observer : observers) {
            eventBus.register(observer);
        }
    }

    public Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
//        long userId = userService.register(telephone, password);

        eventBus.post(new Object());

        return 0L;
    }
}

