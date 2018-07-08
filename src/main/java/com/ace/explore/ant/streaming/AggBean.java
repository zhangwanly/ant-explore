package com.ace.explore.ant.streaming;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class AggBean {
    private List<String> groupIds;
    private Map<String, Float> quotaMap;

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public Map<String, Float> getQuotaMap() {
        return quotaMap;
    }

    public void setQuotaMap(Map<String, Float> quotaMap) {
        this.quotaMap = quotaMap;
    }
}
