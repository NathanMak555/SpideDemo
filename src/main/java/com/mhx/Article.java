package com.mhx;

import java.time.LocalDateTime;

public class Article {

    private String title;
    private String content;
    private LocalDateTime dateTime;
    private String url;

    public Article(String title, String content, LocalDateTime dateTime, String url) {
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.url = url;
    }

    public Article() {
        this.title = "";
        this.content = "";
        this.dateTime = LocalDateTime.now();
        this.url = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void appendContent(String content) {
        this.content += content;
    }
}
