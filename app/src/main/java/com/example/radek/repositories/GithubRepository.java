package com.example.radek.repositories;

import com.google.gson.annotations.SerializedName;

public class GithubRepository {

    private long id;
    private String name;

    @SerializedName("html_url")
    private String htmlUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
