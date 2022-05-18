package com.lemming.lemming.servlet;

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
 */
@WebServlet("/verifyCode")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = VerifyCodeUtil.drawImage(output);
        req.getSession().setAttribute("verifyCode", code);
        ServletOutputStream out = resp.getOutputStream();
        output.writeTo(out);
    }
}
