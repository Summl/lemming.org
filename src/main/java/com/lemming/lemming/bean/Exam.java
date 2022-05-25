package com.lemming.lemming.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Exam {
    private int id;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private int score;

    public void parseFromResultSet(ResultSet res) throws SQLException {
        setId(res.getInt("id"));
        setTitle(res.getString("title"));
        setOptionA(res.getString("option_a"));
        setOptionB(res.getString("option_b"));
        setOptionC(res.getString("option_c"));
        setOptionD(res.getString("option_d"));
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

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
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
