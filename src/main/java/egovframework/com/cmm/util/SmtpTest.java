package egovframework.com.cmm.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
* @packageName    : egovframework.com.cmm.util
* @fileName        : ImapTest.java
* @author        : yhs
* @date            : 2024.08.25
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2024.08.25        yhs       최초 생성
*/

public class SmtpTest {

	public static void main(String[] args) {
		
		// SMTP 서버 정보를 설정합니다.
        String host = "172.30.1.191";
        final String username = "user@example.com"; // 사용자의 Gmail 주소
        final String password = "user"; // Gmail 비밀번호

        String toEmail = "user@example.com"; // 수신자 이메일 주소
        String subject = "테스트 SMTP JAVA";
        String body = "JAVA SMTP 전송";

        // SMTP 서버 설정
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");

        // 인증 정보 설정
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 이메일 메시지 객체 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // 이메일 전송
            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}