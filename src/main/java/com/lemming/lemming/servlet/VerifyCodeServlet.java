package com.lemming.lemming.servlet;

import com.lemming.lemming.generic.Mailer;
import com.lemming.lemming.generic.VerifyCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 获取验证码的Servlet，使用GET向此Servlet发送请求，将得到一个验证码图片，并在Session中存入验证码文本信息。
 * 例如：
 *  发送邮箱验证码：http://localhost:8080/lemming_war_exploded/verifyCode?type=email&to=maicss@126.com
 *  获取图片验证码：http://localhost:8080/lemming_war_exploded/verifyCode?type=image
 *  验证码将分别存储在Session中的ImageVerifyCode和EmailVerifyCode中。
 */
@WebServlet("/verifyCode")
public class VerifyCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("type")){
            case "image":
                ImageVerifyCodeServlet(req,resp);
                break;
            case "email":
                EmailVerifyCodeServlet(req,resp);
                break;
        }
    }
    protected void ImageVerifyCodeServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = VerifyCodeUtil.drawImage(output);
        req.getSession().setAttribute("ImageVerifyCode", code);
        ServletOutputStream out = resp.getOutputStream();
        output.writeTo(out);
    }
    protected void EmailVerifyCodeServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String to = req.getParameter("to");
        if (to==null){
            resp.setStatus(400);
            return;
        }
        String code = Mailer.sendVerifyCode(to);
        req.getSession().setAttribute("EmailVerifyCode",code);
        resp.setStatus(200);
    }
}
