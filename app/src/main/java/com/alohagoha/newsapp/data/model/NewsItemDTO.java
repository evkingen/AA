package com.alohagoha.newsapp.data.model;

import com.alohagoha.newsapp.data.model.NewsImageUrlDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NewsItemDTO implements Serializable {

    @SerializedName("section")
    private String category;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String newsUrl;

    @SerializedName("published_date")
    private Date publishDate;

    @SerializedName("abstract")
    private String previewText;

    @SerializedName("multimedia")
    private List<NewsImageUrlDTO> multimedias;

    public List<NewsImageUrlDTO> getMultimedias() {
        return multimedias;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getCategory() {
        return category;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getPreviewText() {
        return previewText;
    }

    public String getTitle() {
        return title;
    }
}
