package com.ace.explore.ant.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class AntStackTest {

    private final AntStack stack = new AntStack();
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
            stack.push(i);
        }
    }

    @Test
    public void pop() {
        push();
        for (int i = 0; i < size; i++) {
            int pop = stack.pop();
            Assert.assertEquals(data[size - i - 1], pop);
        }
    }

    @Test
    public void push_with_pop() {
        stack.push(25);
        stack.push(353);
        stack.push(54);
        Assert.assertEquals(54, stack.pop());
        stack.push(875);
        Assert.assertEquals(875, stack.pop());
        Assert.assertEquals(353, stack.pop());
        Assert.assertEquals(25, stack.pop());
    }

    @Test(expected = EmptyStackException.class)
    public void pop_with_exception() {
        stack.push(14);
        Assert.assertEquals(14, stack.pop());
        stack.pop();
    }

}