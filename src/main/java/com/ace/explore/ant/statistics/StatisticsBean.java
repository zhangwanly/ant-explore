package com.ace.explore.ant.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by zhangwanli on 2018/7/7.
 */
@Setter
@Getter
@Accessors(chain = true)
public class StatisticsBean {
    private String file;
    private int total;
    private int blank;
    private int comment;
    private int code;

    @Override
    public String toString() {
        return "{" +
                "file='" + file + '\'' +
                ", total=" + total +
                ", blank=" + blank +
                ", comment=" + comment +
                ", code=" + code +
                '}';
    }
}