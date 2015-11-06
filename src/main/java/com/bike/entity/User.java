package com.bike.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zz on 2015/9/16.
 */
@Document
public class User extends EntityBase
{
    private String userName;

    private String passWord;

    private String nickName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
