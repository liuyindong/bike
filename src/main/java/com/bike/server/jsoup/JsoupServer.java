package com.bike.server.jsoup;

import org.jsoup.nodes.Document;

/**
 * Created by ld on 2015/1/21.
 */
public interface JsoupServer
{
    Document getDocument(String url) throws Exception;

}
