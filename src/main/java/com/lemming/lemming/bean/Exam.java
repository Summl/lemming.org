package com.lemming.lemming.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Exam {
    private int id;
    private String title;
    private String options;
    private String answer;
    private int score;

    public void parseFromResultSet(ResultSet res) throws SQLException {
        setId(res.getInt("id"));
        setTitle(res.getString("title"));
        setOptions(res.getString("options"));
        setAnswer(res.getString("answer"));
        setScore(res.getInt("score"));
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
