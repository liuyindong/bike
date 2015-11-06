package com.bike.server.jsoup.impl;

import com.bike.entity.BikeMessages;
import com.bike.entity.CrwNews;
import com.bike.server.jsoup.JsoupServer;
import com.bike.util.Config;
import com.bike.util.DateUtil;
import com.bike.util.DownImg;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ld on 2015/1/21.
 */
@Service
public class JsoupServerImpl implements JsoupServer {


    @Override
    public Document getDocument(String url) throws Exception {
        Document doc = null;
        while (true) {
            Connection conn = Jsoup.connect(url);
            conn.header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.10; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            conn.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            conn.header("Accept-Encoding","gzip, deflate");
            conn.header("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            conn.header("Cache-Control","max-age=0");
            conn.header("Connection","keep-alive");
            try {
                doc = conn.timeout(5000).get();
            } catch (IOException e) {
                System.out.println("抓取超时等待10秒继续");
                Thread.sleep(10000);
            }
            if (doc != null) {
                break;
            }

        }
        return doc;

    }
}
