package com.weixin.common;

import java.util.List;

/**
 * Created by zpc on 2017/3/19.
 */
public class FileUpload implements AbstractParent {

    private int maxUploadSize;
    private int minUploadSize;
    private String path;
    private List<String> suffix;

    public FileUpload() {
    }

    public FileUpload(int maxUploadSize, int minUploadSize, String path, List<String> suffix) {
        this.maxUploadSize = maxUploadSize;
        this.minUploadSize = minUploadSize;
        this.path = path;
        this.suffix = suffix;
    }

    public int getMaxUploadSize() {
        return maxUploadSize;
    }

    public int getMinUploadSize() {
        return minUploadSize;
    }

    public String getPath() {
        return path;
    }

    public List<String> getSuffix() {
        return suffix;
    }

    public void setMaxUploadSize(int maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public void setMinUploadSize(int minUploadSize) {
        this.minUploadSize = minUploadSize;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSuffix(List<String> suffix) {
        this.suffix = suffix;
    }

}
