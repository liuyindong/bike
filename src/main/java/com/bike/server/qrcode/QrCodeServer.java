package com.bike.server.qrcode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ld on 2015/1/23.
 */
public interface QrCodeServer
{
    void getQrCode(HttpServletRequest request, HttpServletResponse response,String outValue,int width,int height) throws IOException;
}
