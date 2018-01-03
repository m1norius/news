package com.minorius.news.mvp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Minorius on 21.12.2017.
 */

public class NewsResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", region='" + region + '\'' +
                ", date='" + date + '\'' +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}