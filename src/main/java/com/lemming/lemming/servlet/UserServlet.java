package com.lemming.lemming.servlet;

import com.alibaba.fastjson.JSONObject;
import com.lemming.lemming.bean.User;
import com.lemming.lemming.dao.PostDao;
import com.lemming.lemming.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
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
        String userName = req.getParameter("register_userName");
        String email = req.getParameter("register_email");
        String passwd = req.getParameter("register_password");
        String verifyCode = req.getParameter("register_verifyCode");


        String realCode = (String) req.getSession().getAttribute("EmailVerifyCode");
        if (realCode == null){
            // 验证码验证失败
            req.setAttribute("tip","您需要先获取邮箱验证码！");
            req.getRequestDispatcher("login.jsp#register").forward(req,resp);
            return;
        }
        if (!verifyCode.equalsIgnoreCase(req.getSession().getAttribute("EmailVerifyCode").toString())){
            // 验证码验证失败
            req.setAttribute("tip","验证码校验失败，请重新注册！");
            req.getRequestDispatcher("login.jsp#register").forward(req,resp);
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
            req.setAttribute("tip","很抱歉注册失败，请联系管理员！");
            req.getRequestDispatcher("login.jsp#register").forward(req,resp);
        }


    }
    protected void loginServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usr = req.getParameter("login_userName");
        String passwd = req.getParameter("login_password");
        String verifyCode = req.getParameter("login_verifyCode");

        if (!verifyCode.equalsIgnoreCase(req.getSession().getAttribute("ImageVerifyCode").toString())){
            // 验证码验证失败
            req.setAttribute("tip","验证码校验失败，请重新登录。");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }
        // 这里首先根据邮箱列查找，若找不到则根据用户名列查找。
        User user = UserDao.getUserByEmail(usr);
        if (user==null){
            user = UserDao.getUserByName(usr);
            if (user==null){
                // 登录失败，没找到该用户
                req.setAttribute("tip","登录失败，没有找到对应用户。");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
                return;
            }
        }
        if (!user.getPassword().equals(passwd)){
            // 登录失败，密码错误
            req.setAttribute("tip","登录失败，密码错误。");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        // 登录成功
        req.getSession().setAttribute("user",user.getId());
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        switch (req.getParameter("type")){
            case "info":
                getUserInfo(req,resp);
                break;
            case "exit":
                exitUserLogin(req,resp);
                break;
        }
    }
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_str = req.getParameter("id");
        Integer userId = (Integer) req.getSession().getAttribute("user");

        // 若查找的用户是当前用户，则提供更为详细的信息
        if (userId==null){ // 防止未登录的状态下userID为null导致报错
            userId=-1;
        }
        if (id_str == null) {
            id_str = userId.toString();
        } else if (Objects.equals(id_str, "")){
            id_str = userId.toString();
        }

        User user = UserDao.getUserById(Integer.parseInt(id_str));
        if (user == null){
            resp.setStatus(404);
            return;
        }

        JSONObject json = new JSONObject();
        json.put("id",user.getId());
        json.put("name",user.getUserName());
        json.put("post_count", PostDao.getCountByUserId(user.getId()));

        if (user.getId() == userId){
            json.put("sex",user.getSex());
            json.put("email",user.getEmail());
            json.put("group_id",user.getGroup());
            json.put("phone",user.getPhone());
        }

        resp.getWriter().print(json);

    }
    protected void exitUserLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user",null);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
