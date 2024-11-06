package egovframework.let.cop.mail.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* @packageName    : egovframework.let.cop.mail.vo
* @fileName        : MailVO.java
* @author        : yhs
* @date            : 2024.08.25
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2024.08.25        yhs       최초 생성
*/

public class MailVO {
	
	private String title;
	private String content;
	private String sendDate;
	private String from;
	private String to;
	private long uid;
	
	private String attatchmentFileName;
	private String filePath;
	
	@JsonIgnore
	private MultipartFile attachmentFile;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getAttatchmentFileName() {
		return attatchmentFileName;
	}

	public void setAttatchmentFileName(String attatchmentFileName) {
		this.attatchmentFileName = attatchmentFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public MultipartFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(MultipartFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}
	
}
