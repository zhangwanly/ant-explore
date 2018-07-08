package com.ace.explore.ant.streaming;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangwanli on 2018/7/8.
 */
@Setter
@Getter
@Accessors(chain = true)
public class AggBean {
    private List<String> groupIds;
    private Map<String, Float> quotaMap;
}
