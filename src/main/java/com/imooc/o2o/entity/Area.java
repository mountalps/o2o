package com.imooc.o2o.entity;

import java.util.Date;

public class Area {

    private Integer areaId;
    private String areaName;

    // Priority, determines the rank of the area
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;

    public Integer getAreaID() {
        return areaId;
    }

    public void setAreaID(Integer areaID) {
        this.areaId = areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}