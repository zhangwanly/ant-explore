package com.ace.explore.ant.collection;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class AntQueue {

    private Stack<Integer> stack0 = new Stack<>();
    private Stack<Integer> stack1 = new Stack<>();

    /**
     * 给队列增加一个入队的操作
     *
     * @param data
     */
    public void push(int data) {
        stack0.push(data);
    }

    /**
     * 给队列增加一个出队的操作
     *
     * @return
     * @throws NoSuchElementException
     */
    public int pop() throws NoSuchElementException {
        if (!stack1.isEmpty()) {
            return stack1.pop();
        }
        if (stack0.isEmpty()) {
            throw new NoSuchElementException();
        }
        while (stack0.size() > 0) {
            stack1.push(stack0.pop());
        }
        return stack1.pop();
    }

}
