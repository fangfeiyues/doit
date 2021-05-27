package com.fang.doit.thought.design.observer;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author created by fang on 2020/7/19/019 17:08
 */

public class UserController {

//    private UserService userService; // ����ע��

    private EventBus eventBus;

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;

    public UserController() {
        // ͬ������ģʽ
        //eventBus = new EventBus();

        // �첽������ģʽ
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));
    }

    public void setRegObservers(List<Object> observers) {
        for (Object observer : observers) {
            eventBus.register(observer);
        }
    }

    public Long register(String telephone, String password) {
        //ʡ�����������У�����
        //ʡ��userService.register()�쳣��try-catch����
//        long userId = userService.register(telephone, password);

        eventBus.post(new Object());

        return 0L;
    }
}

