package com.bike.entity;

import java.util.Date;

/**
 * Created by Administrator on 2015/1/21.
 */
public class EntityBase
{
    private String id;

    private String uuid;

    private Date createTime;

    private String typeUuid;

    private Integer statusId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeUuid() {
        return typeUuid;
    }

    public void setTypeUuid(String typeUuid) {
        this.typeUuid = typeUuid;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
