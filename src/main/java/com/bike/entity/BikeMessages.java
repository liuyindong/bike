package com.bike.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ld on 2015/1/14.
 */
public class BikeMessages extends EntityBase  implements Serializable
{

    private String title;

    private String frontcoverImg;

    private String summary;

    private String authorName;

    private String content;



    //目前首页显示信息
    private String displayForm;

    private int showNum;

    private String authorUuid;

    private List<BikeTag> bikeTagList;

    private String typeName;

    private int showTypeId;

    public BikeMessages() {
    }

    public int getShowTypeId() {
        return showTypeId;
    }

    public void setShowTypeId(int showTypeId) {
        this.showTypeId = showTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public String getAuthorUuid() {
        return authorUuid;
    }

    public List<BikeTag> getBikeTagList() {
        return bikeTagList;
    }

    public void setBikeTagList(List<BikeTag> bikeTagList) {
        this.bikeTagList = bikeTagList;
    }

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }
    public void setAuthorUuid(String authorUuid) {
        this.authorUuid = authorUuid;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrontcoverImg() {
        return frontcoverImg;
    }

    public void setFrontcoverImg(String frontcoverImg) {
        this.frontcoverImg = frontcoverImg;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getDisplayForm() {
        return displayForm;
    }

    public void setDisplayForm(String displayForm) {
        this.displayForm = displayForm;
    }

}
