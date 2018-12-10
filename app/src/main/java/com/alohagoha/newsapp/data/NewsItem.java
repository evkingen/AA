package com.alohagoha.newsapp.data;

import java.io.Serializable;
import java.util.Date;

public class NewsItem implements Serializable {

    private final String title;
    private final String newsUrl;
    private final Category category;
    private final Date publishDate;
    private final String previewText;
    private final String fullText;

    public NewsItem(String title, String newsUrl, Category category, Date publishDate, String previewText, String fullText) {
        this.title = title;
        this.newsUrl = newsUrl;
        this.category = category;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.fullText = fullText;
    }

    public String getTitle() {
        return title;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public Category getCategory() {
        return category;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getPreviewText() {
        return previewText;
    }

    public String getFullText() {
        return fullText;
    }
}
