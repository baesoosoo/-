package egovframework.let.cop.mail.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.MailUtil;
import egovframework.let.cop.mail.vo.MailVO;

/**
* @packageName    : egovframework.let.cop.mail.controller
* @fileName        : MailController.java
* @author        : yhs
* @date            : 2024.08.25
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2024.08.25        yhs       최초 생성
*/

@Controller
public class MailController {

	@Autowired
	MailUtil mailUtil;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	@RequestMapping("/mailList.do")
	public String mailView(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam int currentPage) throws Exception {
		
		try {
			LoginVO LoginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			
			String userId = LoginVO.getEmail();
			String password = LoginVO.getPassword();
			
			//임시
			userId = "user@example.com";
			password = "user";
			
			List<MailVO> mailList = mailUtil.getMailList(userId, password);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(currentPage);
			paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));
			paginationInfo.setPageSize(propertyService.getInt("pageSize"));

			int totCnt = mailList.size();

			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("mailList", mailList);
			model.addAttribute("paginationInfo", paginationInfo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/cop/mail/mailList";
	}
	
	@RequestMapping("/mailDetail.do")
	public String mailDetail(HttpServletRequest request, HttpServletResponse response, MailVO mailVO, Model model) throws Exception {
		
		try {
			LoginVO LoginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			
			String userId = LoginVO.getEmail();
			String password = LoginVO.getPassword();
			
			//임시
			userId = "user@example.com";
			password = "user";
			
			MailVO mailDetail =  mailUtil.getMailDetail(userId, password, mailVO.getUid());
			
			model.addAttribute("mailDetail", mailDetail);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/cop/mail/mailDetail";
	}
	
	@RequestMapping("/sendMailView.do")
	public String sendMailView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/cop/mail/mailSend";
	}
	
	@RequestMapping("/sendMail.json")
	public ModelAndView sendMail(HttpServletRequest request, HttpServletResponse response, MailVO mailVO) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		
		String resultCode = "N";
		
		try {
			mailUtil.sendMail(mailVO.getTo(), mailVO.getTitle(), mailVO.getContent(), mailVO.getAttachmentFile());
			
			resultCode = "Y";
		} catch(Exception e) {
			resultCode = "N";
		}
		
		mav.addObject("resultCode", resultCode);
		
		return mav;
	}
	
	@RequestMapping("/fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") String filePath) throws Exception {
		
		OutputStream out = null;
    	FileInputStream fis = null;
		
		try {
			
			String browser = request.getHeader("User-Agent");
            
			File file = new File(filePath);
			
			String originalFileName = file.getName();
			
			if(!file.exists()) {
				throw new IOException("파일을 찾을 수 없습니다.");
			}
			
			//파일명 설정 
            if (browser.contains("MSIE") || browser.contains("Trident")
                    || browser.contains("Chrome")) {
            	originalFileName = URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");
                response.setHeader("Content-Disposition", "attachment; filename=" + originalFileName + ";");
            } else {
            	originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");
            }
			
            String extension = FilenameUtils.getExtension(originalFileName).toLowerCase();
            
            switch(extension) {
            	case "hwp" :
            		response.setContentType("application/vnd.hancom.hwp");
            		break;
            	
            	case "hwt" :
            		response.setContentType("application/vnd.hancom.hwt");
            		break;
            	
            	case "hml" :
            		response.setContentType("application/vnd.hancom.hml");
            		break;
            		
            	case "hwpx" :
            		response.setContentType("application/vnd.hancom.hwpx");
            		break;
            	
            	case "doc" :
            		response.setContentType("application/msword");
            		break;
            		
            	case "docx" :
            		response.setContentType("application/vnd.openxmlformats-");
            		break;
            		
            	case "xls" :
            		response.setContentType("application/vnd.ms-excel");
            		break;
            		
            	case "xlsx" :
            		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            		break;
            		
            	default : 
            		response.setContentType("application/octet-stream");
            		break;
            }
            
	        response.setHeader("Content-Transfer-Encoding", "binary;");
        	out = response.getOutputStream();
        	fis = new FileInputStream(file);
        	
        	int count = 0;
        	byte[] bytes = new byte[1024];
        	
        	while ((count = fis.read(bytes)) != -1) {
        		out.write(bytes, 0, count);
        	}
        	
        	out.flush();
		
		} catch(IOException | RuntimeException  e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				fis.close();
			}
			
			if(out != null) {
				out.close();
			}
		}
	}
	
}
