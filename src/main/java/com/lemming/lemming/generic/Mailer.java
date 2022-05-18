package com.lemming.lemming.generic;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer
{
    /**
     * 向指定邮箱发送邮件
     * @param to 接收邮件的邮箱
     * @param title 邮件标题内容
     * @param content 邮件正文内容
     */
    public static void send(String to, String title, String content)
    {

        // 发件人电子邮箱
        String from = "lemming_robot@126.com";

        // 指定发送邮件的主机为 smtp.126.com
        String host = "smtp.126.com";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                // 授权码：  ZMZWFHUEZUZHVOBO
                // 邮箱密码： lmg.123456
                return new PasswordAuthentication(from, "ZMZWFHUEZUZHVOBO"); //发件人邮件用户名、授权码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject(title);

            // 设置消息体
            message.setText(content);

            // 发送消息
            Transport.send(message);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    /**
     * 生成一个随机的验证码并发送给指定邮箱
     * @param to 接受验证码的邮箱
     * @return 返回生成的验证码
     */
    public static String sendVerifyCode(String to){

        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder code = new StringBuilder();
        for (int i=0;i<6;i++){
            Random random = new Random();
            code.append(str.charAt(random.nextInt(str.length())));
        }
        send(to,"【旅鼠论坛】您的验证码","您的验证码是: "+code+" ，请勿将此其告诉他人，若您不知道为何收到此条信息，请忽略。");
        return code.toString();
    }
}