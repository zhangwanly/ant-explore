package com.ace.explore.ant.streaming;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class ResourceGenTest {

    @Test
    public void genResource() throws IOException {
        int groupIdCount = 500;
        String[] groupIds = new String[groupIdCount];
        for (int i = 0; i < groupIdCount; i++) {
            groupIds[i] = UUID.randomUUID().toString();
        }
        int fileCount = 80;
        int fileLines = 1024;
        URL url = getClass().getResource(".");
        String tmp = url.getFile();
        String projectRoot = tmp.substring(0, tmp.indexOf("/target"));
        String dir = projectRoot + "/assets/rs";
        File file;
        String format = "%02d";
        String line;
        Random random = new Random();
        List<String> batchLines = new ArrayList<>(fileLines);
        cleanDir(dir);
        for (int i = 0; i < fileCount; i++) {
            file = new File(dir + "/rs-" + String.format(format, i) + ".txt");
            for (int j = 0; j < fileLines; j++) {
                line = UUID.randomUUID().toString() + "," + groupIds[random.nextInt(groupIdCount)] + "," + random.nextFloat() * random.nextInt(1000);
                batchLines.add(line);
            }
            FileUtils.writeLines(file, "utf-8", batchLines, true);
            batchLines.clear();
        }
    }

    private void cleanDir(String dir) {
        File targetDir = new File(dir);
        if (targetDir.exists() && targetDir.isDirectory()) {
            File[] files = targetDir.listFiles();
            if (files != null) {
                for (File f : files) {
                    f.delete();
                }
            }
        }
    }

}