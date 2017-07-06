package com.weixin.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * Created by zpc on 2017/7/6.
 */
public class TwoDimensionCode {

    private static Logger logger = LoggerFactory.getLogger(TwoDimensionCode.class);


    /**
     * 二维码生成器
     * @param content
     * @param outputStream
     */
    public static void toImage(String content, OutputStream outputStream, Integer width, Integer height) {

        String text = content;
        if (null == width) { width = 100; }
        if (null == height) { height = 100; }

        String format = "JPG";
        Hashtable hints= new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
        } catch (WriterException e) {
            logger.warn("【生成二维码失败。。。。。。】");
            e.printStackTrace();
        } catch ( IOException e ) {
            logger.warn("【生成二维码失败。。。。。。】");
            e.printStackTrace();
        }
    }

}
