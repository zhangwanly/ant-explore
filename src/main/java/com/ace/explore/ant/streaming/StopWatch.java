package com.ace.explore.ant.streaming;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class StopWatch {
    private volatile boolean done = false;
    private volatile boolean running = false;
    private final CountDownLatch latch;

    public StopWatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public void countDown() {
        latch.countDown();
    }

    public void await() throws InterruptedException {
        latch.await();
    }

    public void start() {
        running = true;
    }

    public void stop() {
        done = true;
        running = false;
    }

    public boolean isDone() {
        return done;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStoped() {
        return done && !running;
    }

}
