package com.ace.explore.ant.streaming;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class Main implements Observer {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private BlockingQueue<LineBean> calcuQueue = new ArrayBlockingQueue<>(Short.MAX_VALUE);

    public void statisticsAggQuota(File resourceDir) {
        File[] files = resourceDir.listFiles();
        if (files != null) {
            QuotaCalculation quotaCalculation = new QuotaCalculation(calcuQueue);
            quotaCalculation.addObserver(this);
            Thread calcuThread = new Thread(quotaCalculation);
            calcuThread.setName("calcuThread");
            calcuThread.start();
            dispatchReaders(files);
            quotaCalculation.getResult();
        }
    }

    private void dispatchReaders(File[] files) {
        for (File file : files) {
            executorService.submit(new Reader(calcuQueue, file));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        AggBean aggBean = (AggBean) arg;
        List<String> groupIds = aggBean.getGroupIds();
        Map<String, Float> quotaMap = aggBean.getQuotaMap();
        for (String groupId : groupIds) {
            Float quota = quotaMap.get(groupId);
            System.out.println("groupId=" + groupId + ",minQuota=" + quota);
        }
        System.out.println("====================================================================");
        System.out.println("====================================================================");
    }

    public static void main(String[] args) {
        URL url = Main.class.getResource(".");
        String tmp = url.getFile();
        String projectRoot = tmp.substring(0, tmp.indexOf("/target"));
        File file = new File(projectRoot + "/assets/rs");
        new Main().statisticsAggQuota(file);
    }

}
