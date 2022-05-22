package com.lemming.lemming.servlet;

import com.alibaba.fastjson.JSONObject;
import com.lemming.lemming.bean.Post;
import com.lemming.lemming.dao.PostDao;
import com.lemming.lemming.generic.PageNumber;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
@WebServlet("/post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        switch (req.getParameter("type")){
            case "image": // 上传图片
                uploadImage(req,resp);
                break;
            case "post": // 上传帖文
                uploadPost(req,resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        switch (req.getParameter("type")){
            case "page": // 浏览帖文
                pageServlet(req,resp);
            case "list": // 获取列表Json
                listServlet(req,resp);
        }
    }


    protected void listServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        int pageNumber = Integer.parseInt(page);
        List<Post> list = PostDao.queryPost(pageNumber,10, PostDao.POST_ORDER.ORDER_BY_TIME);
        JSONObject json = new JSONObject();
        json.put("list",list);
        json.put("page",PageNumber.getValidPage(pageNumber,PostDao.getCount()));
        resp.setContentType("text/json;charset=UTF-8");
        resp.getWriter().print(json.toJSONString());
    }

    protected void uploadImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        upload.setFileSizeMax(10*1024*1024);

        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> list = upload.parseRequest(req);
                for (FileItem it : list){

                    String fname = UUID.randomUUID().toString();

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
    }
    protected void uploadPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userid = (Integer) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        String brief = req.getParameter("brief");
        String content = req.getParameter("content");
        System.out.println(title);
        System.out.println(content);

        if (userid==null){
            req.setAttribute("tip","您的登录已过期，请重新登录");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        String postDir = req.getSession().getServletContext().getRealPath("data/posts");

        String uuid = UUID.randomUUID().toString();

        File d = new File(postDir);
        if (!d.exists()){
            if (d.mkdir()){
                System.out.println("创建: "+postDir);
            }
        }

        File file = new File(postDir,uuid+".md");
        if (file.exists()){

            req.setAttribute("title","发布失败");
            req.setAttribute("content","发布失败，服务器错误");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
            // 该文件已存在，保存失败
            return;
        }

        if(!PostDao.addPost(title,uuid+".md",brief,userid)){
            req.setAttribute("title","发布失败");
            req.setAttribute("content","发布失败，数据库错误");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
            return;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bw=new BufferedWriter(writer);

            bw.write(content);
            bw.flush();

            req.setAttribute("title","发布成功");
            req.setAttribute("content","发布成功");
            req.getRequestDispatcher("message.jsp").forward(req,resp);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    protected void pageServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postid = req.getParameter("post");
        Post post = PostDao.getPostById(Integer.parseInt(postid));
        if (post == null){
            resp.setStatus(404);
            return;
        }
        PostDao.addReadNum(post.getId());
        req.setAttribute("post",post);
        req.getRequestDispatcher("postpage.jsp").forward(req,resp);
    }


}
