package com.bike.dao.mybatis;

import java.util.List;

/**
 * Created by ld on 2015/1/15.
 */
public interface MyBatisDao
{
    <T> T selectOne(String key);

    <T> T selectOne(String key, Object params);

    <T> List<T> selectList(String key);

    <T> List<T> selectList(String key, Object params);

    int insert(String key, Object params);

    int delete(String key);

    int delete(String key, Object params);

    int update(String key);

    int update(String key, Object params);
}
