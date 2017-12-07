package com.bluedon.monitor.common.util;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author : zhouming
 * @date : 2017年11月26日
 * @module : 基础模块
 * @comment : 邮件
 */

public class SendMailUtil {

	// 设置服务器
	private static String KEY_SMTP = "mail.smtp.host";
	private static String VALUE_SMTP = "smtp.qq.com";
	// 服务器验证
	private static String KEY_PROPS = "mail.smtp.auth";
	private static boolean VALUE_PROPS = true;
	// 发件人用户名、密码
	private String SEND_USER = "664219802@qq.com";
	private String SEND_UNAME = "664219802@qq.com";
	private String SEND_PWD = "szijobucmbpdbcfh";
	// 建立会话
	private MimeMessage message;
	private Session session;

	private static SendMailUtil sendMailUtil = new SendMailUtil();

	public static SendMailUtil getInstance(){
		return sendMailUtil;
	}

	/*
     * 初始化方法
     */
	private SendMailUtil() {
		Properties props = System.getProperties();
		props.setProperty(KEY_SMTP, VALUE_SMTP);
		props.put(KEY_PROPS, "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		session =  Session.getDefaultInstance(props, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
			}});
		session.setDebug(false);
		message = new MimeMessage(session);
	}

	/**
	 * 发送邮件
	 *
	 * @param headName
	 *            邮件头文件名
	 * @param sendHtml
	 *            邮件内容
	 * @param receiveUser
	 *            收件人地址
	 */
	public void doSendHtmlEmail(String headName, String sendHtml,
								String receiveUser) {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(SEND_USER);
			message.setFrom(from);
			// 收件人
			InternetAddress to = new InternetAddress(receiveUser);
			message.setRecipient(Message.RecipientType.TO, to);
			// 邮件标题
			message.setSubject(headName);
			String content = sendHtml.toString();
			// 邮件内容,也可以使纯文本"text/plain"
			message.setContent(content, "text/html;charset=GBK");
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("send success!");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SendMailUtil se = new SendMailUtil();
		se.doSendHtmlEmail("111", "邮件内容", "664219802@qq.com");

	}

	
}
