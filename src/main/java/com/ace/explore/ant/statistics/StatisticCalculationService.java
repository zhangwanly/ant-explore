package com.ace.explore.ant.statistics;

import java.io.*;

/**
 * Created by zhangwanli on 2018/7/7.
 */
public class StatisticCalculationService {
    private static final String JAVA_COMMENT_SINGLE_LINE = "//";
    private static final String JAVA_COMMENT_MULTI_LINE_START = "/*";
    private static final String JAVA_COMMENT_MULTI_LINE_END = "*/";

    public StatisticsBean getStatisticsBean(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            StatisticsBean bean = getStatisticsBean(inputStream);
            bean.setFile(file.getPath());
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private StatisticsBean getStatisticsBean(InputStream stream) {
        boolean inComment = false;
        int total = 0;
        int blank = 0;
        int comment = 0;
        int code = 0;
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
                LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(bufferedInputStream))
        ) {
            while (lineNumberReader.ready()) {
                String line = lineNumberReader.readLine();
                if (line != null) {
                    total++;
                    line = line.trim();
                    if (line.length() == 0) {
                        blank++;
                    } else if ((line.startsWith(JAVA_COMMENT_SINGLE_LINE))) {
                        comment++;
                    } else if (inComment) {
                        comment++;
                        if ((line.contains(JAVA_COMMENT_MULTI_LINE_END))) {
                            inComment = false;
                        }
                    } else if (((line.startsWith(JAVA_COMMENT_MULTI_LINE_START)) && (line.endsWith(JAVA_COMMENT_MULTI_LINE_END)))) {
                        comment++;
                    } else if ((line.startsWith(JAVA_COMMENT_MULTI_LINE_START))) {
                        comment++;
                        if ((!line.contains(JAVA_COMMENT_MULTI_LINE_END))) {
                            inComment = true;
                        }
                    } else {
                        code++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StatisticsBean bean = new StatisticsBean();
        bean.setTotal(total);
        bean.setBlank(blank);
        bean.setComment(comment);
        bean.setCode(code);
        return bean;
    }
}