package com.lemming.lemming.servlet;

import com.lemming.lemming.bean.User;
import com.lemming.lemming.dao.UserDao;
import com.lemming.lemming.dao.UserInfoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        switch (req.getParameter("type")) {
            case "userBaseInfo":
                userBaseInfo(req, resp);
                break;
        }
    }

    protected void userBaseInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String updatename = req.getParameter("updatename");
        String updatesex = req.getParameter("updatesex");
        String updatephone = req.getParameter("updatephone");
        String updateemail = req.getParameter("updateemail");
        String name = (String) req.getSession().getAttribute("user");
        User user = UserDao.getUserByName(name);
        if (user == null){
            resp.setStatus(404);
            return;
        }
        //boolean b = UserInfoDao.updateUserBaseInfo(name);


    }
}
