package com.bike.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by zz on 2015/9/22.
 */
@Service
public class ServiceUtil
{
    private Logger logger = LoggerFactory.getLogger(ServiceUtil.class);

    /**
     *
     * @param file
     * @param path
     * @return  -1:已经存在    1:上传成功  0：上传失败
     */
    public int uploadFile(MultipartFile file,String path)
    {


        File filePath = new File(path);
        File parent = filePath.getParentFile();
        try
        {
            if(parent!=null&&!parent.exists())
            {
                parent.mkdirs();

                file.transferTo(filePath);

                return 1;
            }else
            {
                return -1;
            }


        } catch (IOException e)
        {
            return 0;
        }
    }
}
