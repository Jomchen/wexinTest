package com.weixin.entity;

/**
 * Created by zpc on 2017/6/23.
 */
public enum ColorEnum {

    BLACK("黑色", 0),
    WHITE("白色", 255),
    RED("红色", 2),
    YELLOW("黄色", 3),
    BLUE("蓝色", 4),
    GREEN("绿色", 5),
    PINK("粉色", 6);

    private String colorName;
    private int colorNumber;

    ColorEnum(String colorName, int colorNumber) {
        this.colorName = colorName;
        this.colorNumber = colorNumber;
    }

    public String getColorName() {
        return this.colorName;
    }

    public int getColorNumber() {
        return this.colorNumber;
    }

}
