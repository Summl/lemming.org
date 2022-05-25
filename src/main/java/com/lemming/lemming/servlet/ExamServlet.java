package com.lemming.lemming.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lemming.lemming.bean.Exam;
import com.lemming.lemming.dao.ExamDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/exam")
public class ExamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        switch (req.getParameter("type")){
            case "submit":
                judgeServlet(req,resp);
        }
    }

    protected void judgeServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        switch (req.getParameter("type")){
            case "get":
                examServlet(req,resp);
        }
    }

    protected void examServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Exam> examList = ExamDao.getExamData();
        JSONObject json = new JSONObject();
        JSONArray option = new JSONArray();
        assert examList != null;
        for (Exam exam : examList) {
            if (exam != null) {
                json.put("id", exam.getId());
                json.put("title", exam.getTitle());
                String str = exam.getOptions();
                option.addAll(Arrays.asList(str.split(";")));
                json.put("option", option);
            }
            resp.setContentType("text/json;charset=UTF-8");
            resp.getWriter().print(json);
        }
    }
}


