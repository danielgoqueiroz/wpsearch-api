package com.danielqueiroz.wpsearch.wpsearch.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Post {

    private Integer id;
    private String date;
    private String title;
    private String link;
    private String type;
    private String content;
    private String excerpt;
    private String author;
    private List<String> metas;
    private List<String> categories;
    private List<String> tags;

    public Post() {
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getMetas() {
        return metas;
    }

    public void setMetas(List<String> metas) {
        this.metas = metas;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Post parserElasticToObject(JSONObject json) {
        Post post = new Post();
        post.setId(Integer.parseInt(json.getString("id").toString()));
        post.setTitle(json.getString("title").toString());
        post.setContent(json.getString("content").toString());
        post.setAuthor(json.getString("author").toString());
        post.setDate(json.getString("date").toString());
        return post;
    }

}
