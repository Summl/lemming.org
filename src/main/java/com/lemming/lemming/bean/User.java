package com.lemming.lemming.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User {
    private int id;
    private String nickName;
    private String phone;
    private String email;
    private int group;
    private String sex;
    private String password;
    private Date registrationDate;

    public void parseFromResultSet(ResultSet res) throws SQLException {
        setId(res.getInt("id"));
        setNickName(res.getString("nick_name"));
        setEmail(res.getString("email"));
        setGroup(res.getInt("group_id"));
        setSex(res.getString("sex"));
        setPhone(res.getString("phone"));
        setPassword(res.getString("password"));
        setRegistrationDate(res.getDate("registration_time"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
