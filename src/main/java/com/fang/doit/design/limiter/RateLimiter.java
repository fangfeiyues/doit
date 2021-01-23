package com.fang.doit.design.limiter;

/**
 * @author fangfeiyue
 * @Date 2021/1/21 9:22 下午
 */
public class RateLimiter {
//    public double acquire(int permits) {
//        // 计算获取令牌所需等待的时间
//        long microsToWait = reserve(permits);
//        // 进行线程sleep
//        stopwatch.sleepMicrosUninterruptibly(microsToWait);
//        return 1.0 * microsToWait / SECONDS.toMicros(1L);
//    }
//    final long reserve(int permits) {
//        checkPermits(permits);
//        // 由于涉及并发操作，所以使用synchronized进行并发操作
//        synchronized (mutex()) {
//            return reserveAndGetWaitLength(permits, stopwatch.readMicros());
//        }
//    }
//    final long reserveAndGetWaitLength(int permits, long nowMicros) {
//        // 计算从当前时间开始，能够获取到目标数量令牌时的时间
//        long momentAvailable = reserveEarliestAvailable(permits, nowMicros);
//        // 两个时间相减，获得需要等待的时间
//        return max(momentAvailable - nowMicros, 0);
//    }
//
//    final long reserveEarliestAvailable(int requiredPermits, long nowMicros) {
//        // 刷新令牌数，相当于每次acquire时在根据时间进行令牌的刷新
//        resync(nowMicros);
//        long returnValue = nextFreeTicketMicros;
//        // 计算出可以目前从桶中可以获取到的令牌数
//        double storedPermitsToSpend = min(requiredPermits, this.storedPermits);
//        // 计算出需要预先支付的令牌
//        double freshPermits = requiredPermits - storedPermitsToSpend;
//        // 因为会突然涌入大量请求，而现有令牌数又不够用，因此会预先支付一定的令牌数
//        // waitMicros即是产生预先支付令牌的数量时间，则将下次要添加令牌的时间应该计算时间加上watiMicros
//        long waitMicros = storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend)
//                + (long) (freshPermits * stableIntervalMicros);
//        try {
//            // 更新nextFreeTicketMicros,本次预先支付的令牌所需等待的时间让下一次请求来实际等待。
//            this.nextFreeTicketMicros = LongMath.checkedAdd(nextFreeTicketMicros, waitMicros);
//        } catch (ArithmeticException e) {
//            this.nextFreeTicketMicros = Long.MAX_VALUE;
//        }
//        // 更新令牌数，最低数量为0
//        this.storedPermits -= storedPermitsToSpend;
//        // 返回旧的nextFreeTicketMicros数值，无需为预支付的令牌多加等待时间。
//        return returnValue;
//    }
}
