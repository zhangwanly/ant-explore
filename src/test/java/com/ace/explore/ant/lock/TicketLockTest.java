package com.ace.explore.ant.lock;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangwanli on 2018/7/7.
 */
public class TicketLockTest {

    private AntLock lock = new TicketLock();

    private int num = 0;
    private int tNum = Runtime.getRuntime().availableProcessors();
    private int eachCount = 400;

    @Test
    public void increment_one_by_one_with_ticket_lock() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(tNum);
        for (int i = 0; i < tNum; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < eachCount; j++) {
                    lock.lock();
                    num = num + 1;
//                    System.out.println(Thread.currentThread().getName() + " execute:" + num);
                    lock.unlock();
                }
                latch.countDown();
            });
            thread.setName("worker-" + i);
            thread.start();
        }
        latch.await();
        Assert.assertEquals(num, tNum * eachCount);
    }

    @Test
    public void lock() {

    }

    @Test
    public void unlock() {

    }

}