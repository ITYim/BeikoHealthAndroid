package com.alensic.beikohealth.bean;

import java.io.Serializable;

/**
 * 本地图片对象
 * @author zym
 * @since 2017-08-11 15:01
 */
public class PhotoInfo implements Serializable{

    private String photoPath;

    private String name;

    private long size;

    private String description;

    public PhotoInfo() {
    }

    public PhotoInfo(String photoPath) {
        this.photoPath = photoPath;
    }

    public PhotoInfo(String photoPath, String name, long size, String description) {
        this.photoPath = photoPath;
        this.name = name;
        this.size = size;
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PhotoInfo{" +
                "photoPath='" + photoPath + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", description='" + description + '\'' +
                '}';
    }
}
