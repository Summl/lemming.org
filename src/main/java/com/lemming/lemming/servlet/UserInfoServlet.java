package com.lemming.lemming.servlet;

import com.lemming.lemming.bean.User;
import com.lemming.lemming.dao.UserDao;
import com.lemming.lemming.dao.UserInfoDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
            case "updatePassword":
                updatePassword(req, resp);
                break;
            case "revokeUserAccount":
                revokeUserAccount(req, resp);
                break;
            case "updateImg":
                updateImg(req, resp);
                break;
        }
    }

    protected void updateImg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        upload.setFileSizeMax(10*1024*1024);
        String fname = UUID.randomUUID().toString();
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> list = upload.parseRequest(req);
                for (FileItem it : list){
                    if (it.isFormField()) {
                        continue;
                    }

                    String SavePath = this.getServletContext().getRealPath("data/images");

                    System.out.println(SavePath +"/"+fname);

                    File d = new File(SavePath);
                    if (!d.exists()){
                        if (d.mkdir()){
                            System.out.println("创建: "+SavePath);
                        }
                    }
                    File f = new File(SavePath,fname);
                    it.write(f);
                    it.delete();
                    resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().println(fname);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Integer userId = (Integer) req.getSession().getAttribute("user");
        User user = UserDao.getUserById(userId);
        Integer id = user.getId();
        if (user == null){
            resp.setStatus(404);
            return;
        }
        if(fname == null){
            req.setAttribute("content", "头像有误!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        boolean b = UserInfoDao.changeImg(id,fname);
        if(b){
            req.setAttribute("title","头像保存成功！");
            req.setAttribute("content","头像保存成功！");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
        }else{
            req.setAttribute("content", "头像保存失败。");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        }
    }



    protected void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user");
        String oldpassword = req.getParameter("oldpassword");
        String newpassword = req.getParameter("newpassword");
        String renewpassword = req.getParameter("renewpassword");
        req.setAttribute("title","密码修改失败");
        User user = UserDao.getUserById(userId);
        Integer id = user.getId();
        if (user == null){
            resp.setStatus(404);
            return;
        }
        if(oldpassword.trim().equals("")){
            req.setAttribute("content", "您的旧密码不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        if(newpassword.trim().equals("")){
            req.setAttribute("content", "您的新密码不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        if(renewpassword.trim().equals("")){
            req.setAttribute("content", "您重新输入的密码不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        if (!user.getPassword().equals(oldpassword)) {
            req.setAttribute("content", "您的旧密码不正确，请重新检查后输入。若您已经忘记，请联系系统管理员重置密码。");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        /*if(!newpassword.equals(renewpassword)){
            req.setAttribute("content", "您两次输入的密码不一致，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }*/
        boolean b = UserInfoDao.changePassword(id,newpassword);
        if (b) {
            req.setAttribute("title", "密码修改成功");
            req.setAttribute("content", "密码已经被成功修改，请您重新登录。");
            req.setAttribute("relogin", "true");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        } else {
            req.setAttribute("content", "服务器错误，您的密码没有被修改。");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
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
        if(updatename.trim().equals("")){
            req.setAttribute("content", "您的用户名不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        if(updatephone.trim().equals("")){
            req.setAttribute("content", "您的电话不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        if(updateemail.trim().equals("")){
            req.setAttribute("content", "您的邮箱不能为空，请重新检查您的输入!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
            return;
        }
        boolean b = UserInfoDao.updateUserBaseInfo(updatename,updatesex,updatephone,updateemail,id);
        if(b){
            req.setAttribute("title","保存成功！");
            req.setAttribute("content","保存成功！");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
        }else{
            req.setAttribute("content", "信息修改失败，可重新进行修改。");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        }

    }
//    注销账号
    protected void revokeUserAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id=(Integer) req.getSession().getAttribute("user");
        User user = UserDao.getUserById(id);
        if (user == null){
            resp.setStatus(404);
            return;
        }
        boolean b=UserInfoDao.revokeUserAccountDao(id);
        if(b){
            req.setAttribute("title","账号注销成功！");
            req.setAttribute("content","您的账号已注销成功！");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
        }else{
            req.setAttribute("content", "账号注销失败，请重试。");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        }
    }
}
