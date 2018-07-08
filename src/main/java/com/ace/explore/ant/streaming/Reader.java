package com.ace.explore.ant.streaming;

import java.io.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class Reader implements Runnable {

    private final BlockingQueue<LineBean> queue;
    private final File file;

    public Reader(BlockingQueue<LineBean> queue, File file) {
        this.queue = queue;
        this.file = file;
    }

    @Override
    public void run() {
        try (
                FileInputStream inputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(bufferedInputStream))
        ) {
            while (lineNumberReader.ready()) {
                String line = lineNumberReader.readLine();
                if (line != null && !line.isEmpty()) {
                    queue.add(parseLine(line));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private LineBean parseLine(String line) {
        String[] split = line.split(",");
        LineBean bean = new LineBean();
        bean.setId(split[0]);
        bean.setGroupId(split[1]);
        bean.setQuota(Float.parseFloat(split[2]));
        return bean;
    }

}
