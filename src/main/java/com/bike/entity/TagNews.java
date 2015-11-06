package com.bike.entity;

/**
 * Created by Administrator on 2015/1/24.
 */
public class TagNews extends EntityBase
{
    private  String newUuid;
    private String tagUuid;

    public String getNewUuid() {
        return newUuid;
    }

    public void setNewUuid(String newUuid) {
        this.newUuid = newUuid;
    }

    public String getTagUuid() {
        return tagUuid;
    }

    public void setTagUuid(String tagUuid) {
        this.tagUuid = tagUuid;
    }
}
