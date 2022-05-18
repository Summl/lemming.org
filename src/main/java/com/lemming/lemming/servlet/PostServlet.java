package com.lemming.lemming.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("type")){
            case "image":
                uploadImage(req,resp);
                break;
            case "post":
                uploadPost(req,resp);
                break;
        }

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

                    String fname = it.getName();
                    String SavePath = this.getServletContext().getRealPath("data/images");


                    File d = new File(SavePath);
                    if (!d.exists()){
                        if (d.mkdir()){
                            System.out.println("创建文件夹成功");
                        }
                    }
                    File f = new File(SavePath,fname);
                    it.write(f);
                    it.delete();
//                    resp.setContentType("text/html;charset=UTF-8");
//                    resp.getWriter().println("上传 " + fname + " 成功!");

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    protected void uploadPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = (String) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        String postDir = req.getSession().getServletContext().getRealPath("data/posts");

        String uuid = UUID.fromString(userid+content).toString();

        File file = new File(postDir,uuid+".md");
        if (file.exists()){
            // 该文件已存在，保存失败
            return;
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
