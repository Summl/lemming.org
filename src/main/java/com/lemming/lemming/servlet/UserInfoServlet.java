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
        Integer id=(Integer) req.getSession().getAttribute("user");
        User user = UserDao.getUserById(id);
        if (user == null){
            resp.setStatus(404);
            return;
        }
        if(updatename == null){
            req.setAttribute("context", "您的用户名不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        if(updatephone == null){
            req.setAttribute("context", "您的电话不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        if(updateemail == null){
            req.setAttribute("context", "您的邮箱不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        boolean b = UserInfoDao.updateUserBaseInfo(updatename,updatesex,updatephone,updateemail,id);
        if(b){
            req.setAttribute("title","保存成功！");
            req.setAttribute("content","保存成功！");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
        }else{
            req.setAttribute("context", "信息修改失败，可重新进行修改。");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        }

    }
}
