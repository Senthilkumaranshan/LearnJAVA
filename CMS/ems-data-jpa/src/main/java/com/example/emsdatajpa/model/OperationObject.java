package com.example.emsdatajpa.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class OperationObject {

    private Integer pid;
    private Integer eid;
    private Integer[] tid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer[] getTid() {
        return tid;
    }

    public void setTid(Integer[] tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "OperationObject{" +
                "pid=" + pid +
                ", eid=" + eid +
                ", tid=" + Arrays.toString(tid) +
                '}';
    }
}
