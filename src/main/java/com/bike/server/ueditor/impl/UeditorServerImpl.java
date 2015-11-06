package com.bike.server.ueditor.impl;

import com.bike.entity.BikeImg;
import com.bike.entity.BikeMessages;
import com.bike.entity.Page;
import com.bike.entity.uedit.ImgList;
import com.bike.entity.uedit.ImgListEntity;
import com.bike.entity.uedit.UploadEntity;
import com.bike.server.ServerBase;
import com.bike.server.qiniu.QiNiuServer;
import com.bike.server.ueditor.UeditorServer;
import com.bike.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/31.
 */
@Service
public class UeditorServerImpl extends ServerBase implements UeditorServer
{
    private final String collectionName = "bikeImg";

    @Autowired
    private QiNiuServer qiNiuServer;

    @Override
    public UploadEntity uploadFile(MultipartFile multipartFile) throws Exception {
        UploadEntity uploadEntity = new UploadEntity();

        String fileName = multipartFile.getOriginalFilename();
        String type = DateUtil.imgType(fileName);
        String path = DateUtil.addressRadom(null) + DateUtil.generateFileName(fileName);

        BikeImg bikeImg = qiNiuServer.uploadImageQiniu(multipartFile.getInputStream(), path);

        uploadEntity.setOriginal(path);
        uploadEntity.setName(fileName);
        uploadEntity.setSize(multipartFile.getSize());
        uploadEntity.setType(type);
        uploadEntity.setUrl(bikeImg.getImgUrl());
        uploadEntity.setState("SUCCESS");

        addFile(uploadEntity);


        return uploadEntity;
    }

    @Override
    public void addFile(UploadEntity uploadEntity)
    {
        mongoTemplate.insert(uploadEntity,collectionName);
    }

    @Override
    public ImgList listImg(ImgList imgList)
    {

        int start = imgList.getStart();
        start = imgList.getStart() / imgList.getSize();

        Page page = this.getPageUtil(start,new Query(),collectionName,UploadEntity.class);

        List<ImgListEntity> listEntities = new ArrayList<>();

        for (Object object : page.getDatas())
        {
            UploadEntity uploadEntity = (UploadEntity)object;

            ImgListEntity imgListEntity = new ImgListEntity();
            imgListEntity.setUrl(uploadEntity.getUrl());
            imgListEntity.setMtime((int) uploadEntity.getSize());

            listEntities.add(imgListEntity);
        }
        imgList.setList(listEntities);
        imgList.setState("SUCCESS");
        imgList.setTotal((int) page.getTotalCount());

        return imgList;
    }
}
