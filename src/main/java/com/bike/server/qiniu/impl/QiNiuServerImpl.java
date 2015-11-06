package com.bike.server.qiniu.impl;

import com.bike.entity.BikeImg;
import com.bike.server.qiniu.QiNiuServer;
import com.bike.util.QiNiuUtil;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.net.Http;
import com.qiniu.api.resumableio.ResumeableIoApi;
import com.qiniu.api.rs.PutPolicy;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ld on 2015/1/24.
 */
@Service
public class QiNiuServerImpl implements QiNiuServer
{
    @Autowired
    private com.bike.util.Config config;

    private String getUptoken() throws AuthException, JSONException {
        Config.ACCESS_KEY = config.getACCESSKEY();
        Config.SECRET_KEY = config.getSECRETKEY();

        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        String bucketName = config.getBucket();
        PutPolicy putPolicy = new PutPolicy(bucketName);

        return putPolicy.token(mac);
    }


    @Override
    public BikeImg uploadImageQiniu(InputStream is, String fileName) throws AuthException, JSONException
    {
        Map<String, String> map = new HashMap<String, String>();

        PutRet ret = ResumeableIoApi.put(is, getUptoken(), fileName, null);
        String result = config.getQnUrl() + ret.getKey();

        BikeImg bikImg = new BikeImg();
        bikImg.setImgUrl(result);
        bikImg.setImgName(fileName);

        return bikImg;
    }

    @Override
    public BikeImg imgFetch(String formUrl, String bucket, String fileName) throws NoSuchAlgorithmException, InvalidKeyException, IOException {

        String to = bucket + ":" + fileName;
        String encodeFrom = QiNiuUtil.encodeBase64URLSafeString(formUrl);
        String encodeTo = QiNiuUtil.encodeBase64URLSafeString(to);
        String url = "http://iovip.qbox.me/fetch/" + encodeFrom + "/to/" + encodeTo;

        HttpClient client = Http.getClient();
        HttpPost post = new HttpPost(url);

        String accessToken = QiNiuUtil.signRequest(post, config.getSECRETKEY(), config.getACCESSKEY());

        post.setHeader("User-Agent", "curl/7.30.0");
        post.setHeader("Authorization", "QBox " + accessToken);
        HttpResponse res = client.execute(post);
        CallRet ret =  QiNiuUtil.handleResult(res);

        String result = config.getQnUrl() + fileName;

        BikeImg bikImg = new BikeImg();
        bikImg.setImgUrl(result);
        bikImg.setImgName(fileName);

        return  bikImg;

    }
}
