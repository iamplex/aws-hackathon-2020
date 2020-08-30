package com.cats.lostandfound.entity;

public class Post {
    private long post_id;
    private long user_id;
    private String location;
    private String title;
    private String description;
    private int cat_class;
    private int type;
    private int status;
    private long timestamp;
    private String cover_path;

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCat_class() {
        return cat_class;
    }

    public void setCat_class(int cat_class) {
        this.cat_class = cat_class;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCover_path() {
        return cover_path;
    }

    public void setCover_path(String cover_path) {
        this.cover_path = cover_path;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", user_id=" + user_id +
                ", location=" + location +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cat_class=" + cat_class +
                ", type=" + type +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", cover_path='" + cover_path + '\'' +
                '}';
    }
}
