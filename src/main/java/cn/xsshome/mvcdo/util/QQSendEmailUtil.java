package cn.xsshome.mvcdo.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
public class QQSendEmailUtil {
	private static final String FROM_EMAIL="发送邮件的邮箱地址";
	//	可以参考 https://my.oschina.net/xshuai/blog/819153 
	private static final String EMAIL_AUTHCODE="发送邮件的邮箱地址的授权码";

	/**
	 * 目前发送到163 qq个人邮箱可以，企业邮箱有问题
	 * @param subject 邮件主题
	 * @param text 邮件内容纯文本
	 * @param mails 邮件地址多个用逗号隔开
	 * @throws Exception 
	 */
	public static void send_email(String subject,String text,String mails) throws Exception{
        //用于读取配置文件
        Properties props=new Properties();
        //开启Debug调试
        props.setProperty("mail.debug", "true");
        //发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        //发送邮件服务器的主机名
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        //端口号
        props.setProperty("mail.smtp.port", "465");
        //发送邮件协议
        props.setProperty("mail.transport.protocol", "smtp");
        //开启ssl加密（并不是所有的邮箱服务器都需要，但是qq邮箱服务器是必须的）
        MailSSLSocketFactory msf= new MailSSLSocketFactory();
        msf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory",msf);
        //获取Session会话实例（javamail Session与HttpSession的区别是Javamail的Session只是配置信息的集合）
        Session session=Session.getInstance(props,new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                        //用户名密码验证（取得的授权吗）
                        return new PasswordAuthentication (FROM_EMAIL,EMAIL_AUTHCODE);
                }
        });

        //抽象类MimeMessage为实现类 消息载体封装了邮件的所有消息
        Message message=new MimeMessage(session);
        //设置邮件主题
        message.setSubject(subject);
        //封装需要发送电子邮件的信息
        message.setText(text);
        //设置发件人地址
        message.setFrom(new InternetAddress(FROM_EMAIL));
        //此类的功能是发送邮件 又会话获得实例
        Transport transport=session.getTransport();
        //开启连接
        transport.connect();
        //设置收件人地址邮件信息
        String mailAddress[] = mails.split(",");
        for (int i = 0; i < mailAddress.length; i++) {
            transport.sendMessage(message,new Address[]{new InternetAddress(mailAddress[i])});
            //邮件发送后关闭信息
            transport.close();
		}
	}
}