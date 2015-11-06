package com.bike.server.qrcode.impl;

import com.bike.server.qrcode.QrCodeServer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ld on 2015/1/23.
 */
@Service
public class QrCodeServerImpl implements QrCodeServer
{
    private static final String IMAGETYPE = "JPEG";

    @Override
    public void getQrCode(HttpServletRequest request, HttpServletResponse response, String outValue,int width,int height) throws IOException {
        ServletOutputStream stream = null;

        try {
            stream = response.getOutputStream();
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix m = writer.encode(outValue, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(m, IMAGETYPE, stream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }

    }
}
