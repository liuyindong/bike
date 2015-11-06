package com.bike.util;

import com.qiniu.api.net.CallRet;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ld on 2015/1/24.
 */
public class QiNiuUtil
{
    public static String CHARSET = "utf-8";

    public static String encodeBase64URLSafeString(String p) {
        return encodeBase64URLSafeString(toByte(p));
    }

    public static String encodeBase64URLSafeString(byte[] binaryData) {
        byte[] b = encodeBase64URLSafe(binaryData);
        return toString(b);
    }

    /** 保留尾部的“=” */
    public static byte[] encodeBase64URLSafe(byte[] binaryData) {
        byte[] b = Base64.encodeBase64URLSafe(binaryData);
        int mod = b.length % 4;
        if(mod == 0){
            return b;
        }else{
            int pad = 4 - mod;
            byte[] b2 = new byte[b.length + pad];
            System.arraycopy(b, 0, b2, 0, b.length);
            b2[b.length] = '=';
            if (pad > 1) {
                b2[b.length + 1] = '=';
            }
            return b2;
        }
    }

    public static byte[] toByte(String s){
        try {
            return s.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(byte[] bs){
        try {
            return new String(bs, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String signRequest(HttpPost request,
                                     String secretKey, String accessKey) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        URI uri = request.getURI();
        String path = uri.getRawPath();
        String query = uri.getRawQuery();

        byte[] sk = toByte(secretKey);
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        SecretKeySpec keySpec = new SecretKeySpec(sk, "HmacSHA1");
        mac.init(keySpec);

        mac.update(toByte(path));

        if (query != null && query.length() != 0) {
            mac.update((byte) ('?'));
            mac.update(toByte(query));
        }
        mac.update((byte) '\n');

        signEntity(request, mac);

        byte[] digest = mac.doFinal();
        byte[] digestBase64 = encodeBase64URLSafe(digest);

        StringBuffer b = new StringBuffer();
        b.append(accessKey);
        b.append(':');
        b.append(toString(digestBase64));

        return b.toString();
    }

    private static void signEntity(HttpPost request, Mac mac)
            throws IOException {
        HttpEntity entity = getEntity(request);
        if (entity != null) {
            if (needSignEntity(entity, request)) {
                ByteArrayOutputStream w = new ByteArrayOutputStream();
                entity.writeTo(w);
                mac.update(w.toByteArray());
            }
        }
    }

    private static HttpEntity getEntity(HttpPost request) {
        try {
            HttpPost post = (HttpPost) request;
            if (post != null) {
                return post.getEntity();
            }
        } catch (Exception e) {
        }

        return null;
    }

    private static boolean needSignEntity(HttpEntity entity, HttpRequestBase request) {
        String contentType = "application/x-www-form-urlencoded";
        Header ect = entity.getContentType();
        if(ect!= null && contentType.equals(ect.getValue())){
            return true;
        }
        Header[] cts = request.getHeaders("Content-Type");
        for(Header ct : cts){
            if(contentType.equals(ct.getValue())){
                return true;
            }
        }
        return false;
    }

    public static CallRet handleResult(HttpResponse response) {
        try {
            StatusLine status = response.getStatusLine();
            int statusCode = status.getStatusCode();
            String responseBody = EntityUtils.toString(
                    response.getEntity(), CHARSET);
            return new CallRet(statusCode, responseBody);
        } catch (Exception e) {
            return new CallRet(400, e);
        }
    }
}
