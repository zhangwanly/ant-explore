package com.ace.explore.ant.streaming;

import java.util.Objects;

/**
 * Created by zhangwanli on 2018/7/8.
 */
public class LineBean {
    private String id;
    private String groupId;
    private float quota;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public float getQuota() {
        return quota;
    }

    public void setQuota(float quota) {
        this.quota = quota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineBean lineBean = (LineBean) o;
        return Objects.equals(id, lineBean.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", quota=" + quota +
                '}';
    }

}
