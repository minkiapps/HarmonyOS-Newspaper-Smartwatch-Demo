package com.minkiapps.newspaper.test.model;

import java.io.Serializable;

public class Article implements Serializable {

    private String preTitle;
    private String preSubTitle;

    private String title;
    private String summary;

    private String smallImageUrl;
    private String largeImageUrl;

    public String getPreTitle() {
        return preTitle;
    }

    public void setPreTitle(final String preTitle) {
        this.preTitle = preTitle;
    }

    public String getPreSubTitle() {
        return preSubTitle;
    }

    public void setPreSubTitle(final String preSubTitle) {
        this.preSubTitle = preSubTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(final String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(final String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }
}
