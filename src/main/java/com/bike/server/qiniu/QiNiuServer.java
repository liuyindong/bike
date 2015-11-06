package com.bike.server.qiniu;

import com.bike.entity.BikeImg;
import com.qiniu.api.auth.AuthException;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ld on 2015/1/24.
 */
public interface QiNiuServer
{
    //七牛图片上传
    BikeImg uploadImageQiniu(InputStream is,String fileName) throws AuthException, JSONException;

    //网络图片下载
    BikeImg imgFetch(String url, String bucket, String fileName) throws NoSuchAlgorithmException, InvalidKeyException, IOException;
}
