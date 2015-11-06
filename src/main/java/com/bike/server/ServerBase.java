package com.bike.server;

import com.bike.dao.mybatis.MyBatisDao;
import com.bike.entity.MysqlPags;
import com.bike.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by ld on 2015/1/14.
 */
public class ServerBase
{

    @Autowired
    public MyBatisDao myBatisDao;

    @Autowired
    public MongoOperations mongoTemplate;

 /*   @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;
*/
    @Autowired
    public GridFsOperations gridFsOperations;


    public Map<String,Object> map ;
    public MysqlPags mysqlPags ;

    protected <T> Page getPageUtil(int pageNo, Query query,String collectionName,Class<T> entityClass) {

        long totalCount = this.mongoTemplate.count(query,collectionName);

        Page page = new Page(pageNo, totalCount);

        query.skip(page.getFirstResult());// skip相当于从那条记录开始

        query.limit(page.getPageSize());// 从skip开始,取多少条记录



        List datas =  mongoTemplate.find(query, entityClass, collectionName);
        page.setDatas(datas);

        return page;
    }

    public void getNewPag(int pageNo)
    {
        mysqlPags = new MysqlPags(pageNo);

        map = new HashMap<>();

        map.put("pageNo",mysqlPags.getPageNo());
        map.put("pageEnd",mysqlPags.getPageEnd());

    }

    public void getNewMap()
    {
        map = new HashMap<>();
    }






}
