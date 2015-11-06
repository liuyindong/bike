package com.bike.entity.uedit;

import java.io.StringReader;
import java.util.List;

/**
 * Created by Administrator on 2015/1/31.
 */
public class ImgList
{
    private String state;

    private Integer start;
    private Integer size;

    private int total;

    private List<ImgListEntity> list;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ImgListEntity> getList() {
        return list;
    }

    public void setList(List<ImgListEntity> list) {
        this.list = list;
    }
}

