package com.ace.explore.ant.statistics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwanli on 2018/7/7.
 */
public class CodeStatistics4J {

    private static final String JAVA_FILE_SUFFIX = ".java";

    private StatisticCalculationService statisticCalculationService = new StatisticCalculationService();

    public List<StatisticsBean> statisticsCode(File file) {
        if (!file.exists()) {
            throw new FileNotExistsException(file.getName() + "not found");
        }
        List<StatisticsBean> list = new ArrayList<>();
        statisticsCode(file, list);
        return list;
    }

    private void statisticsCode(File file, List<StatisticsBean> list) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                statisticsCode(f, list);
            }
        } else if (file.getName().endsWith(JAVA_FILE_SUFFIX)) {
            StatisticsBean bean = statisticCalculationService.getStatisticsBean(file);
            list.add(bean);
        }
    }

}