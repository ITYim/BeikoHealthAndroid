package com.alensic.beikohealth.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class PropIndexBean {

    private String push_time;
    private List<List<PropagandaEducationEntity>> list;

    public String getPush_time() {
        return push_time;
    }

    public void setPush_time(String push_time) {
        this.push_time = push_time;
    }

    public List<List<PropagandaEducationEntity>> getList() {
        return list;
    }

    public void setList(List<List<PropagandaEducationEntity>> list) {
        this.list = list;
    }
}
