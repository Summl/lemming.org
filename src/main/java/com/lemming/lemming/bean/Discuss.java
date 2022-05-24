package com.lemming.lemming.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Discuss {
    private int id;
    private int postId;
    private int userId;
    private String discuss;
    private int like;
    private Date regTime;

    public void parseFromResultSet(ResultSet res) throws SQLException {
        setId(res.getInt("id"));
        setPostId(res.getInt("post_id"));
        setDiscuss(res.getString("discuss"));
        setUserId(res.getInt("user_id"));
        setLike(res.getInt("like_num"));
        setRegTime(res.getDate("reg_time"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}
