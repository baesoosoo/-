package egovframework.com.cmm.util;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import egovframework.let.cop.mail.vo.MailVO;

/**
* @packageName    : egovframework.com.cmm.util
* @fileName        : IMAPUtil.java
* @author        : yhs
* @date            : 2024.08.25
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2024.08.25        yhs       최초 생성
*/

@Component
public class MailUtil {
	
	public List<MailVO> getMailList(String userId, String password) throws Exception {
		
		List<MailVO> resultList = new ArrayList<MailVO>();
		
		try {
			
			Properties props = System.getProperties();
			// smtp 서버 설정
			props.setProperty("mail.store.protocol", "imap");

			Session session = Session.getInstance(props, null);
			
			Store store = session.getStore("imap");
			store.connect("172.30.1.191", 143, userId, password);
			
			// 받은편지함을 INBOX 라고 한다.
			Folder folder = store.getFolder("INBOX");
			// 속성인지 혹은 수정 속성인지 설정
			folder.open(Folder.READ_ONLY);
			
            //UIDFolder uf = (UIDFolder) folder;
			
			// 받은 편지함에 있는 메일 모두 읽어오기
			Message[] messages = folder.getMessages();
			
			FetchProfile profile = new FetchProfile();
			profile.add(FetchProfile.Item.FLAGS);
			profile.add(UIDFolder.FetchProfileItem.UID);
			folder.fetch(messages, profile);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			for(Message message : messages) {
				
				MailVO mailVO = new MailVO();
				
				String from = "";
				
				for(Address add : message.getFrom()) {
					from += add.toString();
				}
				
				mailVO.setTitle(message.getSubject());
				mailVO.setFrom(from);
				mailVO.setSendDate(format.format(message.getReceivedDate()));
				//mailVO.setUid(uf.getUID(message));
				mailVO.setUid(message.getMessageNumber());
				
				resultList.add(mailVO);
			}
			
			// folder와 store은 조회 후 꼭 close 해주어야함
			// folder.close()에는 메시지 삭제 여부를 주어야함
			// 실제 메시지가 삭제되는 시점은 folder를 close하는 시점
			folder.close(true);
			store.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
		
	}
	
	public int sendMail(String to, String subject, String content, MultipartFile file) throws Exception {
		
		int resultCount = 0;
		
        try {
        	
        	// SMTP 서버 정보 설정
            final String username = "user@example.com";
            final String password = "user";
            String host = "172.30.1.191"; // 예: smtp.gmail.com
            int port = 25; // SMTP 서버 포트 (TLS)

            // SMTP 서버 설정
            Properties properties = new Properties();
//            properties.put("mail.smtp.auth", "true");
//            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);

            // 인증 정보 설정
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        	
            // 메시지 객체 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // 메일 본문 생성
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content);
          
            // 메일 콘텐츠 생성 (본문 + 첨부 파일)
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart); // 본문 추가
            
            if(file != null) {
            	File attachmentFile = new File(file.getOriginalFilename());
            	file.transferTo(attachmentFile);
            	
            	// 첨부 파일 파트 생성
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentFile);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(file.getOriginalFilename());
                multipart.addBodyPart(attachmentBodyPart); // 첨부 파일 추가
            }

            // 메일에 콘텐츠 설정
            message.setContent(multipart);

            // 이메일 전송
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resultCount;
	}
	
	public int getMailCount(String userId, String password) throws Exception {
		
		int messageCount = 0;
		
		try {
			
			Properties props = System.getProperties();
			// smtp 서버 설정
			props.setProperty("mail.store.protocol", "imap");

			Session session = Session.getInstance(props, null);
			
			Store store = session.getStore("imap");
			store.connect("172.30.1.191", 143, userId, password);
			
			// 받은편지함을 INBOX 라고 한다.
			Folder folder = store.getFolder("INBOX");
			// 속성인지 혹은 수정 속성인지 설정
			folder.open(Folder.READ_ONLY);
			
			//안 읽은 메세지 Count
			messageCount = folder.getUnreadMessageCount();
			
			// folder와 store은 조회 후 꼭 close 해주어야함
			// folder.close()에는 메시지 삭제 여부를 주어야함
			// 실제 메시지가 삭제되는 시점은 folder를 close하는 시점
			folder.close(true);
			store.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return messageCount;
		
	}
	
	public MailVO getMailDetail(String userId, String password, long uid) throws Exception {
		
		MailVO mailVO = new MailVO();
		
		try {
			
			Properties props = System.getProperties();
			// smtp 서버 설정
			props.setProperty("mail.store.protocol", "imap");

			Session session = Session.getInstance(props, null);
			
			Store store = session.getStore("imap");
			store.connect("172.30.1.191", 143, userId, password);
			
			// 받은편지함을 INBOX 라고 한다.
			Folder folder = store.getFolder("INBOX");
			// 속성인지 혹은 수정 속성인지 설정
			folder.open(Folder.READ_WRITE);
			
			Message message = folder.getMessage((int) uid);
			
			//읽음 처리
			message.setFlag(Flag.SEEN, true);
			
			String content = "";
			
			if (message.isMimeType("multipart/*")) {
                // MimeMultipart 처리
				MimeMultipart multipart = (MimeMultipart) message.getContent();

                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);

                    if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                        // 첨부 파일 처리
                        String fileName = bodyPart.getFileName();
                        InputStream is = bodyPart.getInputStream();
                        System.out.println("Attachment: " + fileName);
                        // 여기에 파일 저장 로직 추가 가능
                        
                        File fileDirectory = new File("C:\\fileFolder");
                        
                        if(!fileDirectory.exists()) {
                        	fileDirectory.mkdirs();	
                        }
                        
                        Path filePath = Path.of("C:\\fileFolder\\"+fileName);
                        
                        // InputStream을 파일로 복사
                        Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);
                        
                        String encodeFilePath = URLEncoder.encode(filePath.toString(), "UTF-8");
                        
                        mailVO.setFilePath(encodeFilePath);
                        mailVO.setAttatchmentFileName(fileName);
                    } else {
                    	content = (String) bodyPart.getContent();
                    }
                }
            } else if (message.isMimeType("text/plain")) {
                content = (String) message.getContent();
            }
			
			String from = "";
			
			for(Address add : message.getFrom()) {
				from += add.toString();
			}
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			mailVO.setTitle(message.getSubject());
			mailVO.setContent(content);
			mailVO.setFrom(from);
			mailVO.setSendDate(format.format(message.getReceivedDate()));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return mailVO;
	}
}
