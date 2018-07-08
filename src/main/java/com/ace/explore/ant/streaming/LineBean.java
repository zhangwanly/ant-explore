package com.ace.explore.ant.streaming;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * Created by zhangwanli on 2018/7/8.
 */
@Setter
@Getter
@Accessors(chain = true)
public class LineBean {
    private String id;
    private String groupId;
    private float quota;

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
