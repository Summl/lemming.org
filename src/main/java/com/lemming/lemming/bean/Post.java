package com.lemming.lemming.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String brief;
    private String imageFilename;
    private String postFilename;
    private int readNum;
    private int likeNum;
    private int userId;
    private Date postTime;

    public void parseFromResultSet(ResultSet res) throws SQLException {
        setId(res.getInt("id"));
        setTitle(res.getString("title"));
        setBrief(res.getString("brief"));
        setImageFilename(res.getString("image_filename"));
        setPostFilename(res.getString("post_filename"));
        setReadNum(res.getInt("read_num"));
        setLikeNum(res.getInt("like_num"));
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

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getPostFilename() {
        return postFilename;
    }

    public void setPostFilename(String postFilename) {
        this.postFilename = postFilename;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
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
