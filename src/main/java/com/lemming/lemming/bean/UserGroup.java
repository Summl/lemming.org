package com.lemming.lemming.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGroup {
    private int id;
    private String name;
    private boolean allowPost;
    private boolean allowComment;
    private boolean allowAdmin;
    private boolean allowAudit; //允许审核
    public void parseFromResultSet(ResultSet res) throws SQLException {
        setId(res.getInt("id"));
        setName(res.getString("name"));
        setAllowPost(res.getBoolean("allow_post"));
        setAllowAdmin(res.getBoolean("allow_admin"));
        setAllowAudit(res.getBoolean("allow_audit"));
        setAllowComment(res.getBoolean("allow_comment"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowPost() {
        return allowPost;
    }

    public void setAllowPost(boolean allowPost) {
        this.allowPost = allowPost;
    }

    public boolean isAllowComment() {
        return allowComment;
    }

    public void setAllowComment(boolean allowComment) {
        this.allowComment = allowComment;
    }

    public boolean isAllowAdmin() {
        return allowAdmin;
    }

    public void setAllowAdmin(boolean allowAdmin) {
        this.allowAdmin = allowAdmin;
    }

    public boolean isAllowAudit() {
        return allowAudit;
    }

    public void setAllowAudit(boolean allowAudit) {
        this.allowAudit = allowAudit;
    }
}
