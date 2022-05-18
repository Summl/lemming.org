package com.lemming.lemming.servlet;

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
        req.getParameter("");
    }
}
