package utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendEmail {
    /**
     * 发送邮件的配置类 主要用户找回密码功能的验证码发送到QQ邮箱
     *
     * @param strTo 收信人的电子邮箱
     * @param str   要发送的数据
     */
    public SendEmail(String strTo, String str) {
        // 收件人电子邮箱
        String to = strTo;

        // 发件人电子邮箱
        String from = "404608626@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com"; // QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("404608626@qq.com", "mhgwtkkggugmbhac"); // 发件人邮件用户名、密码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("三月慕课");

            // 设置消息体
            message.setText("尊敬的客户您好,感谢您使用三月慕课.您的验证码为" + str);

            // 发送消息
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
