package com.ace.explore.ant.collection;

import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Queue;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class AntStack {

    private Queue<Integer> queue = new ArrayDeque<>();
    private Queue<Integer> transferStation = new ArrayDeque<>();

    /**
     * 入栈操作
     *
     * @param data
     */
    public void push(int data) {
        queue.add(data);
    }

    /**
     * 出栈操作
     *
     * @return
     * @throws EmptyStackException
     */
    public int pop() throws EmptyStackException {
        if (queue.isEmpty()) {
            throw new EmptyStackException();
        }
        while (queue.size() > 1) {
            transferStation.add(queue.remove());
        }
        int data = queue.remove();
        exchange();
        return data;
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void exchange() {
        Queue<Integer> tmp = queue;
        queue = transferStation;
        transferStation = tmp;
    }

}
