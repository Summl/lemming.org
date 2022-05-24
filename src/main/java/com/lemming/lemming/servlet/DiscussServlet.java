package com.lemming.lemming.servlet;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.lemming.lemming.bean.Discuss;
import com.lemming.lemming.bean.User;
import com.lemming.lemming.bean.UserGroup;
import com.lemming.lemming.dao.DiscussDao;
import com.lemming.lemming.dao.GroupDao;
import com.lemming.lemming.dao.PostDao;
import com.lemming.lemming.dao.UserDao;
import com.lemming.lemming.generic.PageNumber;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/discuss")
public class DiscussServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        switch (req.getParameter("type")){
            case "add":
                addDiscussServlet(req,resp);
                break;
            case "list":
                queryDiscussServlet(req,resp);
                break;

        }
    }
    protected void queryDiscussServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("post");
        String page = req.getParameter("page"); //评论内容


        if (page==null){
            page="1";
        }

        int pageNum = Integer.parseInt(page);

        List<Discuss> list = DiscussDao.queryDiscuss(Integer.parseInt(postId),pageNum);

        JSONObject json = new JSONObject();
        json.put("list",list);
        json.put("page", PageNumber.getValidPage(pageNum, DiscussDao.getCount()));

        resp.setContentType("text/json;charset=UTF-8");
        resp.getWriter().print(json.toJSONString());
    }

//    @Override
//    protected void (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
    protected void addDiscussServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Integer userId = (Integer) req.getSession().getAttribute("user");
        if (userId == null){
            resp.setStatus(500);
            // 没有登录
            return;
        }
        User user = UserDao.getUserById(userId);
        if (user == null){
            resp.setStatus(500);
            // 没有找到该用户
            return;
        }

        UserGroup group = GroupDao.getGroupInfoByUserId(user.getId());
        assert group != null;
        if (!(group.isAllowComment())){
            resp.setStatus(500);
            return;
        }
        String content = req.getParameter("content"); //评论内容
        String postId = req.getParameter("post"); //要评论的文章

        boolean res = DiscussDao.addDiscuss(Integer.parseInt(postId),user.getId(),content);

        if (res) {
            resp.setStatus(200);
        }else {
            resp.setStatus(500);
        }

    }
}
