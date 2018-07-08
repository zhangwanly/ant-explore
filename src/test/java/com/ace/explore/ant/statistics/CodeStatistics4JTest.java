package com.ace.explore.ant.statistics;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 * Created by zhangwanli on 2018/7/7.
 */
public class CodeStatistics4JTest {

    private CodeStatistics4J codeStatistics4J = new CodeStatistics4J();

    @Test
    public void statisticsCode() {
        URL url = CodeStatistics4J.class.getResource(".");
        String tmp = url.getFile();
        String projectRoot = tmp.substring(0, tmp.indexOf("/target"));
        File file = new File(projectRoot + "/assets");
        List<StatisticsBean> list = codeStatistics4J.statisticsCode(file);
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
        /*  assets/Override.java copied from jdk src.zip。
            total:52, comment:43，blank:3，code:6
         */
        Optional<StatisticsBean> Override = list.stream().filter(bean -> bean.getFile().endsWith("Override.java")).findAny();
        Assert.assertTrue(Override.isPresent());
        StatisticsBean statistics = Override.get();
        Assert.assertEquals(52, statistics.getTotal());
        Assert.assertEquals(43, statistics.getComment());
        Assert.assertEquals(3, statistics.getBlank());
        Assert.assertEquals(6, statistics.getCode());

        /*  assets/Runnable.java copied from jdk src.zip。
            total:69, comment:24+27+11=62，blank:2，code:5
         */
        Optional<StatisticsBean> Runnable = list.stream().filter(bean -> bean.getFile().endsWith("Runnable.java")).findAny();
        Assert.assertTrue(Runnable.isPresent());
        statistics = Runnable.get();
        Assert.assertEquals(69, statistics.getTotal());
        Assert.assertEquals(62, statistics.getComment());
        Assert.assertEquals(2, statistics.getBlank());
        Assert.assertEquals(5, statistics.getCode());
    }
}