package com.mhx.ithome;

import java.time.LocalDateTime;
import java.util.Objects;

public class ArticleOfITHome {

    private String title;
    private String content;
    private LocalDateTime dateTime;
    private String url;

    public ArticleOfITHome() {
        this.title = "";
        this.content = "";
        this.dateTime = LocalDateTime.now();
        this.url = "";
    }

    public ArticleOfITHome(String title, String content, LocalDateTime dateTime, String url) {
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
        this.dateTime =Objects.requireNonNull(dateTime);
        this.url = Objects.requireNonNull(url);
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
        this.content += content;
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
}
