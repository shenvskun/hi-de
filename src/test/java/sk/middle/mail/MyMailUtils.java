package sk.middle.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyMailUtils {
	public static void sendQQPlainTextEmail(String name, String pw, String to, String subject, String content) {
		//连接
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.qq.com");  //服务器
		props.setProperty("mail.smtp.auth", "true");    //需要授权验证
		Session session = Session.getInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication () {
		        return new PasswordAuthentication(name, pw);//用户名和 授权码
			}
		});
		
		//消息
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(name + "@qq.com"));  //发件人
			msg.addRecipients(RecipientType.TO, to); //收件人
			msg.setSubject(subject); //主题
			msg.setContent(content, "text/plain;charset=utf-8"); //邮件内容
			Transport.send(msg);  //发送
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
