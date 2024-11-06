package egovframework.com.cmm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

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

public class ImapTest {

	public static void main(String[] args) {
		
		try {
			
			Properties props = System.getProperties();
			// smtp 서버 설정
			props.setProperty("mail.store.protocol", "imap");

			Session session = Session.getInstance(props, null);
			
			Store store = session.getStore("imap");
			store.connect("172.30.1.191", 143, "user@example.com", "user");
			//store.connect("imap.naver.com", 993, "yook9688", "gustjr6588@@");
			
			
			// 받은편지함을 INBOX 라고 한다.
			Folder folder = store.getFolder("INBOX");
			// 속성인지 혹은 수정 속성인지 설정
			folder.open(Folder.READ_WRITE);
			
			// 받은 편지함에 있는 메일 모두 읽어오기
			Message[] messages = folder.getMessages();
			
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			
			for(Message message : messages) {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				
				resultMap.put("title", message.getSubject());
				resultMap.put("content", message.getContent().toString());
				resultMap.put("from", message.getFrom().toString());
				
				System.out.println(message.getSubject());
				System.out.println(message.getContent());
				System.out.println(message.getFrom().toString());
				
				resultList.add(resultMap);
			}
			
			// folder와 store은 조회 후 꼭 close 해주어야함
			// folder.close()에는 메시지 삭제 여부를 주어야함
			// 실제 메시지가 삭제되는 시점은 folder를 close하는 시점
			folder.close(true);
			store.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
}
