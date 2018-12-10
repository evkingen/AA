package com.alohagoha.newsapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsImageUrlDTO implements Serializable {

    @SerializedName("url")
    private String imageUrl;

    @SerializedName("format")
    private String format;

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("type")
    private String type;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("caption")
    private String caption;

    @SerializedName("copyright")
    private String copyright;

    public String getCopyright() {
        return copyright;
    }

    public String getCaption() {
        return caption;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getFormat() {
        return format;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
