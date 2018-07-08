package com.ace.explore.ant.streaming;

import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class QuotaCalculation extends Observable implements Runnable {

    private final BlockingQueue<LineBean> queue;

    private Map<String, Float> map = new HashMap<>(1024);
    private List<String> groupdIds = new ArrayList<>(1024);
    private int notifyPeriod = 100;
    private int modCount = 0;


    public QuotaCalculation(BlockingQueue<LineBean> queue) {
        this.queue = queue;
    }

    private void calculate(LineBean bean) {
        String groupId = bean.getGroupId();
        float quota = bean.getQuota();
        Float min = map.get(groupId);
        if (min == null) {
            groupdIds.add(groupId);
            map.put(groupId, quota);
            modCount++;
        } else if (quota < min) {
            map.put(groupId, quota);
            modCount++;
        }
        if (modCount >= notifyPeriod) {
            Collections.sort(groupdIds);
            AggBean aggBean = new AggBean().setGroupIds(Collections.unmodifiableList(groupdIds))
                    .setQuotaMap(Collections.unmodifiableMap(map));
            setChanged();
            notifyObservers(aggBean);
            modCount = 0;
        }
    }

    @Override
    public void run() {
        LineBean bean;
        while (true) {
            try {
                bean = queue.take();
                calculate(bean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public AggBean getResult() {
        //TODO wait for done
        Collections.sort(groupdIds);
        return new AggBean().setGroupIds(Collections.unmodifiableList(groupdIds))
                .setQuotaMap(Collections.unmodifiableMap(map));
    }

}
