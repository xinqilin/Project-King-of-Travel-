package com.pointgoodsord.controller;

import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailServiceWithPic2 {

	public static void sendMail(String to, String subject, MimeMultipart multipart) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "da101g3yugioh@gmail.com";
			final String myGmail_password = "BBB45678";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}
	public static String generateContentId(String prefix) {
	     return String.format("%s-%s", prefix, UUID.randomUUID());
	}

	public static void main(String args[]) {

		try {

			String to = "mosesxyl@gmail.com";
			String subject = "祝大家專題順利";

			MimeMultipart multipart = new MimeMultipart("related");
			//String cid = ContentIdGenerator.getContentId();

			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<H1>Hello怎麼可能</H1><img src='cid:image'>";
			messageBodyPart.setContent(htmlText, "text/html");

			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource("D:\\hellobear.png");
			messageBodyPart.setFileName("image.png");
			messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");
			
			
			multipart.addBodyPart(messageBodyPart);
			
			sendMail(to, subject, multipart);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
