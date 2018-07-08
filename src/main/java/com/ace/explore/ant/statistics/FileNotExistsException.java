package com.ace.explore.ant.statistics;

/**
 * Created by zhangwanli on 2018/7/7.
 */
public class FileNotExistsException extends RuntimeException {
    private static final long serialVersionUID = 6862141022091075668L;

    public FileNotExistsException(String message) {
        super(message);
    }

    public FileNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}