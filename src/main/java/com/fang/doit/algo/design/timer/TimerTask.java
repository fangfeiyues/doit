package com.fang.doit.algo.design.timer;

import java.util.concurrent.TimeUnit;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.design.timer
 * @Description:
 * @date Date : 2024-04-07 3:35 下午
 */
public interface TimerTask {

    /**
     * Executed after the delay specified with
     * {@link Timer#newTimeout(TimerTask, long, TimeUnit)}.
     *
     * @param timeout a handle which is associated with this task
     */
    void run(Timeout timeout) throws Exception;

}
