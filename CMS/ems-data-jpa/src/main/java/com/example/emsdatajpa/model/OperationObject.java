package com.example.emsdatajpa.model;

import java.util.List;

public class OperationObject {

    private Integer pid;
    private Integer eid;
    private List<Integer> tid;

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

    public List<Integer> getTid() {
        return tid;
    }

    public void setTid(List<Integer> tid) {
        this.tid = tid;
    }
}
