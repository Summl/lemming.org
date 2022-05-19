package com.lemming.lemming.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String brief;
    private String image;
    private String post;
    private int read;
    private int like;
    private int userId;
    private Date postTime;

    public void parseFromResultSet(ResultSet res) throws SQLException {
        setId(res.getInt("id"));
        setTitle(res.getString("title"));
        setBrief(res.getString("brief"));
        setImage(res.getString("image"));
        setPost(res.getString("post"));
        setRead(res.getInt("read"));
        setLike(res.getInt("like"));
        setUserId(res.getInt("user_id"));
        setPostTime(res.getTime("post_time"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
}
