package com.bike.entity;

/**
 * Created by ld on 2015/1/23.
 */
public class BikeTag extends EntityBase
{
    private String name;

    private String newUUID;

    //权重
    private Integer weight;

    public String getNewUUID() {
        return newUUID;
    }

    public void setNewUUID(String newUUID) {
        this.newUUID = newUUID;
    }



    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
