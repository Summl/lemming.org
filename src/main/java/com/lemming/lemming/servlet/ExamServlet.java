package com.lemming.lemming.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lemming.lemming.bean.Exam;
import com.lemming.lemming.bean.UserGroup;
import com.lemming.lemming.dao.ExamDao;
import com.lemming.lemming.dao.GroupDao;
import com.lemming.lemming.dao.UserDao;
import com.lemming.lemming.dao.UserInfoDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        Integer userID = (Integer) req.getSession().getAttribute("user");
        if (userID == null) {
            req.setAttribute("title","提交失败");
            req.setAttribute("content","您的登录已过期，请重新登录");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
            return;
        }
        Map<String, String[]> map = req.getParameterMap();
        System.out.println(map.toString());
        int score = 0;
        int fullMarks = 0; //满分
        for (String key : map.keySet()){
            if (!key.startsWith("ti")) continue;
            String value =((String[]) map.get(key))[0];
            System.out.println(key+":"+value);
            int examID = Integer.parseInt(key.substring(2));
            int answer = ExamDao.getAnswerById(examID);
            int itScore = ExamDao.getScoreById(examID);
            fullMarks += itScore;
            if(Integer.toString(answer).equals(value)){
                score += itScore;
            }
        }
        System.out.println(score);
        if (score*100/fullMarks > 60){
            //成绩合格
            if (Objects.requireNonNull(GroupDao.getGroupInfoByUserId(userID)).getId()<1){
                UserInfoDao.setGroupById(userID,1);
                req.setAttribute("title","成绩合格");
                req.setAttribute("content","恭喜您晋升为”博主“！");
                req.getRequestDispatcher("message.jsp").forward(req,resp);
            }else {
                req.setAttribute("title","成绩合格");
                req.setAttribute("content","经常复习，有益身心健康");
                req.getRequestDispatcher("message.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("title","成绩不及格");
            req.setAttribute("content","继续努力，再接再厉！");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
        }
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
        JSONArray res = new JSONArray();
        assert examList != null;
        for (Exam exam : examList) {
            JSONObject json = new JSONObject();
            json.put("id", exam.getId());
            json.put("title", exam.getTitle());
            String str = exam.getOptions();
            JSONArray option = new JSONArray();
            option.addAll(Arrays.asList(str.split(";")));
            json.put("option", option);
            res.add(json);
        }
        resp.setContentType("text/json;charset=UTF-8");
        resp.getWriter().print(res);
    }
}


