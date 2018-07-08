package com.ace.explore.ant.statistics;

/**
 * Created by zhangwanli on 2018/7/7.
 */
public class StatisticsBean {
    private String file;
    private int total;
    private int blank;
    private int comment;
    private int code;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBlank() {
        return blank;
    }

    public void setBlank(int blank) {
        this.blank = blank;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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