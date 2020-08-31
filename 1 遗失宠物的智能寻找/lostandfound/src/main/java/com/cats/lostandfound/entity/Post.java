package com.cats.lostandfound.entity;

/**
 * post_id:自增id
 * user_id:关联user表的user_id
 * location:字符串，6位地区码
 * title:标题，限制128个字符
 * description:文字内容，描述宠物具体信息，有字数限制500个字符
 * cat_class:数字，0到12，
 * type:lost-0,found-1
 * status:仍在寻找猫或寻找主人-0,已经找到-1
 * timestamp:毫秒数
 * cover_path:封面图路径
 */
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
