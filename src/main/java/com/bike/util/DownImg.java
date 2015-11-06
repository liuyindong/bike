package com.bike.util;

/**
 * Created by ld on 2015/1/21.
 */

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载图片
 *
 * @author LD
 *
 */
public class DownImg
{
    public static InputStream inputStreamByUrl(String fileUrl) throws IOException, EncoderException {
        URLCodec urlEncoder = new URLCodec("UTF-8");
        urlEncoder.encode(fileUrl).replace("+", "%20");
        return  null;
    }

    public static boolean saveUrlAs(String fileUrl, String savePath) throws Exception/* fileUrl网络资源地址 */
    {
        URL url = new URL(fileUrl);/* 将网络资源地址传给,即赋值给url */
		/* 此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流 */
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        File dirname = new File(savePath);

        if (!dirname.isDirectory())
        {
            dirname.getParentFile().mkdirs();
            dirname.createNewFile();
        }

        DataInputStream in = new DataInputStream(connection.getInputStream());
		/* 此处也可用BufferedInputStream与BufferedOutputStream */
        DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));

		/* 将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址 */
        byte[] buffer = new byte[4096];
        int count = 0;
        while ((count = in.read(buffer)) > 0)/* 将输入流以字节的形式读取并写入buffer中 */
        {
            out.write(buffer, 0, count);
        }
        out.close();/* 后面三行为关闭输入输出流以及网络资源的固定格式 */
        in.close();
        connection.disconnect();
        return true;/* 网络资源截取并存储本地成功返回true */
    }

    public static String returnType(String fileUrl) throws Exception
    {

        URL url = new URL(fileUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String pathname;

        try
        {
            pathname = new String((connection.getHeaderField("content-disposition")).getBytes("ISO-8859-1"), "utf8");

            if (pathname != null && pathname.indexOf("=") != -1)
            {
                String[] fileName = pathname.split("=");
                pathname = fileName[1].replaceAll("\"", "");

            }
        }
        catch (Exception e)
        {
            int random = fileUrl.lastIndexOf("/");
            pathname = fileUrl.substring(random);
        }
        return pathname;
    }

    public static void main(String[] args) throws Exception
    {
        String pathname = "D://2013/04/07/电影/[720P]兄弟出头天 Stand Up Guys (2013).720P 中英字幕 高清下载 4.37G/地址/[www.tiantangbbs.com][电影天堂论坛][720P]兄弟出头天 Stand Up Guys (2013).720P 中英字幕 高清下载.torrent";
        File dirname = new File(pathname);

        if (!dirname.isDirectory())
        {
            dirname.getParentFile().mkdirs();
            dirname.createNewFile();
        }
    }
}