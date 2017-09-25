package com.lh.mail;

import sun.security.krb5.Config;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @version :1.0
 * CREATE TIME :2017/9/22 11:45
 * @authro :LH
 */
public class MailUtils  {

    public static void sendMail() throws  Exception{
        Properties properties = ConfigUtil.build("src/main/resources/mail.properties");
        Session session = Session.getInstance(properties,new MailAuthenticator(ConfigUtil.getString("userName"),ConfigUtil.getString("passWord")));

        Message message = new MimeMessage(session);

        message.setFrom(InternetAddress.parse(ConfigUtil.getString("userName"))[0]);
        message.setSubject("国庆放假通知");
        message.setContent("8天假","text/html;charset=utf-8");
        message.setRecipient(Message.RecipientType.TO,InternetAddress.parse("1787450136@qq.com")[0]);
        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message,InternetAddress.parse("1787450136@qq.com"));
    }

    public static void sendMail1(Main main) throws  Exception{
        Properties properties = ConfigUtil.build("src/main/resources/mail.properties");
        Session session = Session.getInstance(properties,new MailAuthenticator(ConfigUtil.getString("userName"),ConfigUtil.getString("passWord")));
        Message message = new MimeMessage(session);
        main.setFrom(ConfigUtil.getString("userName"));//邮件发送者
        message.setSubject(main.getSubject());//邮件主题
        message.setFrom(main.getFrom());//邮件接收者
        if(main.getContent()!=null){
            message.setContent(main.getContent(),main.getContentType());//邮件正文，第一个参数为内容，第二个为参数
        }else{
            Multipart multipart = new MimeMultipart();
            BodyPart contentBody = new MimeBodyPart();
            contentBody.setContent(main.getBodyContent(),main.getContentType());
            multipart.addBodyPart(contentBody);
            for(String filePath : main.getAttachments()){
                BodyPart fileBody = new MimeBodyPart();
                fileBody.setDataHandler(new DataHandler(new FileDataSource(filePath)));
                multipart.addBodyPart(fileBody);
            }
            message.setContent(multipart);
        }

        message.setRecipient(Message.RecipientType.TO,main.getTo());//发送一个普通邮件To
        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message,message.getAllRecipients());
    }
    public static void main(String[] args)throws Exception {
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    MailUtils.sendMail();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    Main mail = new Main();
                    mail.setSubject("放假啦");
//                    mail.setContent("8天<a href='http://baidu.com'>进入网站</a>" +
//                            "<img src='http://b.hiphotos.baidu.com/zhidao/pic/item/a5c27d1ed21b0ef47a3cc0a7dbc451da80cb3e76.jpg' />");
//                    mail.setContentType("text/html;charset=utf-8");

                    mail.setBodyContent("内容");
                    mail.setContentType("text/html;charset=utf-8");
                    List<String> attachments = new ArrayList<String>();
                    attachments.add("src/main/resources/mail.properties");
                    attachments.add("src/main/resources/baidu.html");
                    mail.setAttachments(attachments);
                    mail.setTo("1787450136@qq.com,1839096686@qq.com");
                    mail.setCc("1787450136@qq.com,1839096686@qq.com");
                    mail.setBcc("1787450136@qq.com,1839096686@qq.com");
                    MailUtils.sendMail1(mail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
