package com.bike.server.tags.impl;

import com.bike.entity.BikeMessages;
import com.bike.entity.BikeTag;
import com.bike.entity.TagNews;
import com.bike.server.ServerBase;
import com.bike.server.tags.TagServer;
import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.dic.LearnTool;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2015/1/23.
 */
@Service
public class TagServerImpl extends ServerBase implements TagServer
{

    private  final  String xlName = "BikeTagMapper.";

    @Override
    public int addTags(BikeTag bikeTag)
    {
       return myBatisDao.insert(xlName + "addTag",bikeTag);
    }

    @Async
    @Override
    public void addListTags(List<BikeTag> tagList)
    {
        for (BikeTag bikeTag : tagList)
        {
            if(this.updateWeight(bikeTag) <= 0)
            {
                this.addTags(bikeTag);
            }else
            {
                bikeTag.setUuid(this.tagByName(bikeTag.getName()).getUuid());
            }
            TagNews tagNews = new TagNews();
            tagNews.setUuid(UUID.randomUUID().toString());
            tagNews.setNewUuid(bikeTag.getNewUUID());
            tagNews.setTagUuid(bikeTag.getUuid());
            this.addTagNews(tagNews);
        }

    }

    @Override
    public int updateWeight(BikeTag bikeTag)
    {
        return myBatisDao.update(xlName + "update_weight",bikeTag);
    }

    @Override
    public BikeTag tagByName(String name) {
        return myBatisDao.selectOne(xlName + "tag_by_name", name);
    }

    @Override
    public void addTagNews(TagNews tagNews) {
        myBatisDao.insert(xlName + "addTagNews",tagNews);
    }

    @Override
    public List<BikeTag> listBikeTag() {
        return null;
    }

    @Override
    public List<BikeTag> newsGetTags(BikeMessages bikeMessages)
    {

        KeyWordComputer kwc = new KeyWordComputer(10);
        String title = bikeMessages.getTitle();
        String content = bikeMessages.getContent();
        Collection<Keyword> result = kwc.computeArticleTfidf(title, content);

        List<BikeTag> bikeTagList = new ArrayList<>();

        for (Keyword keyword : result)
        {

            if(StringUtils.isEmpty(keyword.getName()))continue;

            BikeTag bikeTag = new BikeTag();

            bikeTag.setUuid(UUID.randomUUID().toString());
            bikeTag.setName(keyword.getName());
            bikeTag.setNewUUID(bikeMessages.getUuid());

            bikeTagList.add(bikeTag);
        }

        return bikeTagList;
    }

    @Override
    public List<BikeTag> learnTags(BikeMessages bikeMessages)
    {
        LearnTool learnTool = new LearnTool() ;

        NlpAnalysis.parse(bikeMessages.getTitle(), learnTool) ;
        NlpAnalysis.parse(bikeMessages.getContent(), learnTool) ;
        //取得学习到的topn新词,返回前10个。这里如果设置为0则返回全部
        List<Map.Entry<String, Double>> list = learnTool.getTopTree(5);

        List<BikeTag> bikeTagList = new ArrayList<>();

        for (Map.Entry<String, Double> stringDoubleEntry : list)
        {

            if(StringUtils.isEmpty(stringDoubleEntry.getKey()))continue;

            BikeTag bikeTag = new BikeTag();

            bikeTag.setUuid(UUID.randomUUID().toString());
            bikeTag.setName(stringDoubleEntry.getKey());
            bikeTag.setNewUUID(bikeMessages.getUuid());

            bikeTagList.add(bikeTag);
        }
        return bikeTagList;
    }

    @Override
    public List<BikeTag> tagByNewUUID(String uuid)
    {
        return myBatisDao.selectList(xlName + "tagBynewUUID",uuid);
    }

    @Override
    public List<BikeTag> topNumTags(int topNum)
    {
        return myBatisDao.selectList(xlName + "bikeTagByTopNum",topNum);
    }

}
