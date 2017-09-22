package com.lh.mail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Main {
    private String from;
    private String subject;
    private String content;
    private String contentType;
    private String to;
    private String cc;
    private String bcc;

    public InternetAddress getFrom() {

        try {
            return InternetAddress.parse(from)[0];
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public InternetAddress getTo() {
        try {
            return InternetAddress.parse(to)[0];
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public InternetAddress getCc() {
        try {
            return InternetAddress.parse(cc)[0];
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public InternetAddress getBcc() {
        try {
            return InternetAddress.parse(bcc)[0];
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
}
