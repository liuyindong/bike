package com.bike.server.ueditor;

import com.bike.entity.uedit.ImgList;
import com.bike.entity.uedit.UploadEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Administrator on 2015/1/31.
 */
public interface UeditorServer
{
    UploadEntity uploadFile(MultipartFile multipartFile) throws IOException, Exception;

    void addFile(UploadEntity uploadEntity);

    ImgList listImg(ImgList imgList);
}
