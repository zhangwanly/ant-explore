package com.ace.explore.ant.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangwanli on 2018/7/7.
 */
public class TicketLock implements AntLock {

    private final AtomicInteger versionSeq = new AtomicInteger(0);
    private final AtomicInteger ticketSeq = new AtomicInteger(0);
    private static final ThreadLocal<Integer> LOCAL = ThreadLocal.withInitial(() -> 0);

    public void lock() {
        LOCAL.set(ticketSeq.getAndIncrement());
        //@formatter:off
        while (versionSeq.get() != LOCAL.get()) {}
        //@formatter:on
    }

    public void unlock() {
        versionSeq.compareAndSet(LOCAL.get(), LOCAL.get() + 1);
    }

}