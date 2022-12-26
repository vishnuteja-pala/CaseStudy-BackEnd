package com.casestudy.shoppingcart.entities;

import javax.persistence.*;

@Entity
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int imageId;
    private String imageName;
    private String imageType;
    @Column(length = 50000000)
    private byte[] imageByte;

    public ImageModel(String imageName, String imageType, byte[] imageByte) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageByte = imageByte;
    }

    public ImageModel() {

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }
}
