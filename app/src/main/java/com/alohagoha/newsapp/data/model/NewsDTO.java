package com.alohagoha.newsapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsDTO {

    @SerializedName("section")
    private String section;

    @SerializedName("results")
    private List<NewsItemDTO> results;

    public String getSection() {
        return section;
    }

    public List<NewsItemDTO> getResults() {
        return results;
    }
}
