package com.lh.mail;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @version :1.0
 * CREATE TIME :2017/9/22 11:41
 * @authro :LH
 */
public class MailAuthenticator extends Authenticator {
    private String userName;
    private String passWord;
    public  MailAuthenticator(){}

    public  MailAuthenticator(String userName,String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, passWord);
    }



}
