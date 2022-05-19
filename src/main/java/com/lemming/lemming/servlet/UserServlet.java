package com.lemming.lemming.servlet;

import com.lemming.lemming.bean.User;
import com.lemming.lemming.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getParameter("type")){
            case "register":
                registerServlet(req,resp);
                break;
            case "login":
                loginServlet(req,resp);
                break;
        }
    }
    protected void registerServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String passwd = req.getParameter("password");
        String verifyCode = req.getParameter("verifyCode");

        if (!verifyCode.equalsIgnoreCase(req.getSession().getAttribute("EmailVerifyCode").toString())){
            // 验证码验证失败
            req.setAttribute("title","注册失败");
            req.setAttribute("context","验证码校验失败，请重新注册。");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
            return;
        }
        boolean res = UserDao.register(userName,email,passwd);
        if (res){
            User user = UserDao.getUserByEmail(email);
            assert user != null;
            // 注册成功
            req.setAttribute("title","注册成功");
            req.setAttribute("context","恭喜您注册成功，您是第"+user.getId()+"位用户！");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
        }else  {
            // 注册失败
            req.setAttribute("title","注册失败");
            req.setAttribute("context","很抱歉注册失败，请联系管理员。"); //TODO：这里应当显示更详细的失败原因。
            req.getRequestDispatcher("message.jsp").forward(req,resp);
        }


    }
    protected void loginServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usr = req.getParameter("userName");
        String passwd = req.getParameter("password");
        String verifyCode = req.getParameter("verifyCode");

        if (!verifyCode.equalsIgnoreCase(req.getSession().getAttribute("ImageVerifyCode").toString())){
            // 验证码验证失败
            req.setAttribute("title","注册失败");
            req.setAttribute("context","验证码校验失败，请重新登录。");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
            return;
        }
        // 这里首先根据邮箱列查找，若找不到则根据用户名列查找。
        User user = UserDao.getUserByEmail(usr);
        if (user==null){
            user = UserDao.getUserByName(usr);
            if (user==null){
                // 登录失败，没找到该用户
                req.setAttribute("title","登录失败");
                req.setAttribute("context","登录失败，没有找到对应用户。");
                req.getRequestDispatcher("message.jsp").forward(req,resp);
                return;
            }
        }
        if (!user.getPassword().equals(passwd)){
            // 登录失败，密码错误
            req.setAttribute("title","登录失败");
            req.setAttribute("context","登录失败，密码错误。");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
            return;
        }

        // 登录成功
        req.getSession().setAttribute("user",user.getId());
    }
}
