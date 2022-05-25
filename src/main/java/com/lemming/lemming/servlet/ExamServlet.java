package com.lemming.lemming.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lemming.lemming.bean.Exam;
import com.lemming.lemming.dao.ExamDao;


import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exam")
public class ExamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        switch (req.getParameter("type")){
            case "submit":
                examServlet(req,resp);
        }
    }
    protected void examServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Exam> examList = ExamDao.getExamData();
        JSONObject json = new JSONObject();

        for (int i = 0; i < examList.size(); i++) {
            Exam exam = examList.get(i);
            if (exam != null){
                json.put("id",exam.getId());
                json.put("title",exam.getTitle());
                json.put("option_a",exam.getOptionA());
                json.put("option_b",exam.getOptionB());
                json.put("option_c",exam.getOptionC());
                json.put("option_d",exam.getOptionD());
            }
            resp.setContentType("text/json;charset=UTF-8");
            resp.getWriter().print(json);
        }
    }
}


