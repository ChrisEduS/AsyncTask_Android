package com.example.project6;
public class Nasa {
    private String title;
    private String http;
    private String url;

    public Nasa(String title, String http, String url) {
        this.title = title;
        this.http = http;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        String builder = "Title: " + this.title + ", URL: " + this.url;
        return builder;
    }
}
