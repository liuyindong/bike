package com.bike.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/1/26.
 */
public class MysqlPags
{
    /**
     * 一页数据默认20条
     */
    private int pageSize = 20;
    /**
     * 当前页码
     */
    private int pageNo;

    //结束页
    private int pageEnd;
    /**
     * 一共有多少条数据
     */
    private int totalCount;

    /**
     * 一共有多少页
     */
    private int totalPage;
    /**
     * 数据集合
     */
    private List datas;

    public MysqlPags(int pageNo)
    {
        this.pageNo = pageNo * pageSize;
        this.pageEnd = pageNo * pageSize + pageSize;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPage = totalCount/pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }
}
