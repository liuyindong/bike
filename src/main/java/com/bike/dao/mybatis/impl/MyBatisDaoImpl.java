package com.bike.dao.mybatis.impl;

import com.bike.dao.mybatis.MyBatisDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ld on 2015/1/15.
 */
@Component
public class MyBatisDaoImpl extends SqlSessionDaoSupport implements MyBatisDao
{

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /** 查询一条记录（或值） **/
    public <T> T selectOne(String key)
    {
        return getSqlSession().selectOne(key);
    }

    /** 查询一条记录（或值），带参数 **/
    public <T> T selectOne(String key, Object params)
    {
        return getSqlSession().selectOne(key, params);
    }

    /** 查询记录集 **/
    public <T> List<T> selectList(String key)
    {
        return getSqlSession().selectList(key);
    }

    /** 查询记录集，带参数 **/
    public <T> List<T> selectList(String key, Object params)
    {
        return getSqlSession().selectList(key, params);
    }

    /** 插入记录，带参数 **/
    public int insert(String key, Object params)
    {
        return getSqlSession().insert(key, params);
    }

    /** 删除记录 **/
    public int delete(String key)
    {
        return getSqlSession().delete(key);
    }

    /** 删除记录，带参数 **/
    public int delete(String key, Object params)
    {
        return getSqlSession().delete(key, params);
    }

    /** 更新记录 **/
    public int update(String key)
    {
        return getSqlSession().update(key);
    }

    /** 更新记录，带参数 **/
    public int update(String key, Object params)
    {
        return getSqlSession().update(key, params);
    }
}
