package com.weixin.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.weixin.common.MyRunTimeExcption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * Created by zpc on 2017/7/6.
 */
public class TwoDimensionCode {

    public static Byte TARGET_TO_OUTPUTSTREAM = 0;

    public static Byte TARGET_TO_PICTURE = 1;

    private static Logger logger = LoggerFactory.getLogger(TwoDimensionCode.class);

    public static Integer PIC_WIDTH = 100;

    public static Integer PIC_HEIGHT = 100;

    public enum PicTypeEnum {
        PNG("PNG"),
        JPG("JPG");

        String picType;
        PicTypeEnum(String picType) {
            this.picType = picType;
        }

        public String getPicTypeName() {
            return this.picType;
        }
    }


    /**
     *
     * /**
     * 二维码生成嚣
     * @param content [ 二维码的内容 ]
     * @param width [ 图片宽度 ]
     * @param height [ 图片高度 ]
     * @param outputStream [ 二维码输出流 ]
     * @param targetTo [ 二维码输出型式 ]
     */
    public static void toImage(
            String content,
            Integer width,
            Integer height,
            PicTypeEnum picTypeEnum,
            OutputStream outputStream,
            Byte targetTo) {

        if (targetTo != 0 && targetTo != 1) {
            throw new MyRunTimeExcption("请选择二维码类型");
        }
        if (null == width || width <= 0) {
            width = TwoDimensionCode.PIC_WIDTH;
        }
        if (null == height || height <= 0) {
            height = TwoDimensionCode.PIC_HEIGHT;
        }
        if (null == picTypeEnum) {
            picTypeEnum = PicTypeEnum.JPG;
        }

        Hashtable hints= new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix;

        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, picTypeEnum.getPicTypeName(), outputStream);
            if(targetTo == TwoDimensionCode.TARGET_TO_PICTURE) {
                outputStream.close();
            }
        } catch (WriterException e) {
            logger.warn("【生成二维码失败。。。。。。】");
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
            logger.warn("【生成二维码失败。。。。。。】");
        }
    }

}
