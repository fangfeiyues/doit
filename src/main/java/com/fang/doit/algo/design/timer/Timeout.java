package com.fang.doit.algo.design.timer;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.design.timer
 * @Description:
 * @date Date : 2024-04-07 3:34 下午
 */
public interface Timeout {

    /**
     * Returns the {@link Timer} that created this handle.
     */
    Timer timer();

    /**
     * Returns the {@link TimerTask} which is associated with this handle.
     */
    TimerTask task();

    /**
     * Returns {@code true} if and only if the {@link TimerTask} associated
     * with this handle has been expired.
     */
    boolean isExpired();

    /**
     * Returns {@code true} if and only if the {@link TimerTask} associated
     * with this handle has been cancelled.
     */
    boolean isCancelled();

    /**
     * Attempts to cancel the {@link TimerTask} associated with this handle.
     * If the task has been executed or cancelled already, it will return with
     * no side effect.
     *
     * @return True if the cancellation completed successfully, otherwise false
     */
    boolean cancel();
}
