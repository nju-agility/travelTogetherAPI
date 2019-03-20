package com.aglie.nju.traveltogetherapi.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 *
 * @description: 邮件工具
 * @date: 2019-03-14
 * @author
 */

public class javaMail {
    public static void sendEmail(String account, String name, String newPassword)throws Exception {    // 配置信息
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");


        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        ts.connect("smtp.qq.com","295869813", "wjepxagfqmggbgcd");
        // 创建邮件
        Message message = createSimpleMail(session, account, name, newPassword);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */
    public static MimeMessage createSimpleMail(Session session, String account, String name, String newPassword)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("295869813@qq.com"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(account));
        // 邮件的标题
        message.setSubject("TravelTogether找回个人密码");
        // 邮件的文本内容
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>您好，"+name+"，您正在初始化【TravelTogether】的密码，如不是本人操作，请忽略此邮件\n" +
                "<div >您的新密码为:</div><br>\n" +
                "<div style=\"color: red; font-size:16px;\">"+newPassword+"</div>\n" +
                "<div>为了安全起见请您登录后尽快修改初始化密码。</div><br>\n" +
                "<body></html>";
        message.setContent(content, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}

