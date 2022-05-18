package com.lemming.lemming.servlet;

import com.lemming.lemming.bean.User;
import com.lemming.lemming.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    String userid;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userid = (String) req.getSession().getAttribute("userid");

        switch (req.getParameter("type")){
            case "register":
                registerServlet(req,resp);
                break;
            case "login":

                break;
        }
    }
    protected void registerServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");
        String verifyCode = req.getParameter("verifyCode");

        if (!verifyCode.equalsIgnoreCase(req.getSession().getAttribute("EmailVerifyCode").toString())){
            // 验证码验证失败
            return;
        }
        boolean res = UserDao.register(userName,email,passwd);
        if (res){
            // 注册成功
        }else  {
            // 注册失败
        }


    }
    protected void loginServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");

        User user = UserDao.getUserByEmail(email);
        if (user==null){
            // 登录失败，没找到该用户
            return;
        }
        if (!user.getPassword().equals(passwd)){
            // 登录失败，密码错误
            return;
        }

        // 登录成功
        req.getSession().setAttribute("user",user.getId());
    }
}
