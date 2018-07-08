package com.ace.explore.ant.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class AntQueueTest {

    private final AntQueue queue = new AntQueue();
    private int size = 9;
    private int[] data = new int[size];

    {
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    @Test
    public void push() {
        for (int i : data) {
            queue.push(i);
        }
    }

    @Test
    public void pop() {
        push();
        for (int i = 0; i < size; i++) {
            Assert.assertEquals(data[i], queue.pop());
        }
    }

    @Test
    public void push_with_pop() {
        queue.push(54);
        queue.push(74);
        queue.push(23);
        Assert.assertEquals(54, queue.pop());
        queue.push(89);
        Assert.assertEquals(74, queue.pop());
        Assert.assertEquals(23, queue.pop());
        Assert.assertEquals(89, queue.pop());
    }

    @Test(expected = NoSuchElementException.class)
    public void pop_with_exception() {
        queue.push(235);
        queue.push(78);
        Assert.assertEquals(235, queue.pop());
        Assert.assertEquals(78, queue.pop());
        queue.pop();
    }

}