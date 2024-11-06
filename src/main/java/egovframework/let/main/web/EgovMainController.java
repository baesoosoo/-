package egovframework.let.main.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.MailUtil;
import egovframework.let.cop.alarm.service.AlarmService;
import egovframework.let.cop.alarm.vo.AlarmVO;
import egovframework.let.cop.bbs.service.BoardVO;
import egovframework.let.cop.bbs.service.EgovBBSManageService;

/**
 * 템플릿 메인 페이지 컨트롤러 클래스(Sample 소스)
 * @author 실행환경 개발팀 JJY
 * @since 2011.08.31
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.31  JJY            최초 생성
 *
 * </pre>
 */
@Controller@SessionAttributes(types = ComDefaultVO.class)
public class EgovMainController {
	
	@Autowired
	MailUtil mailUtil;
	
	@Autowired
	AlarmService alarmService;
	
	/**
	 * EgovBBSManageService
	 */
	@Resource(name = "EgovBBSManageService")
    private EgovBBSManageService bbsMngService;

	/**
	 * 메인 페이지에서 각 업무 화면으로 연계하는 기능을 제공한다.
	 *
	 * @param request
	 * @param commandMap
	 * @exception Exception Exception
	 */
	@RequestMapping(value = "/cmm/forwardPage.do")
	public String forwardPageWithMenuNo(HttpServletRequest request, @RequestParam Map<String, Object> commandMap)
	  throws Exception{
		return "";
	}

	/**
	 * 템플릿 메인 페이지 조회
	 * @return 메인페이지 정보 Map [key : 항목명]
	 *
	 * @param request
	 * @param model
	 * @exception Exception Exception
	 */
	@RequestMapping(value = "/cmm/main/mainPage.do")
	public String getMgtMainPage(HttpServletRequest request, ModelMap model)
	  throws Exception{

		// 공지사항 메인 컨텐츠 조회 시작 ---------------------------------
		BoardVO boardVO = new BoardVO();
		boardVO.setPageUnit(5);
		boardVO.setPageSize(10);
		boardVO.setBbsId("BBSMSTR_AAAAAAAAAAAA");

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//공지사항
		Map<String, Object> map = bbsMngService.selectBoardArticles(boardVO, "BBSA02");
		model.addAttribute("notiList", map.get("resultList"));

		//상황개요
		boardVO.setBbsId("BBSMSTR_000000000001");
		map = bbsMngService.selectBoardArticles(boardVO, "BBSA03");
		model.addAttribute("eventList", map.get("resultList"));
		
		//지휘관 강조사항
		boardVO.setBbsId("BBSMSTR_000000000021");
		map = bbsMngService.selectBoardArticles(boardVO, "BBSA03");
		model.addAttribute("orderList", map.get("resultList"));
		
		//태세현황
		/* AlarmVO alarmInfo = alarmService.selectAlarm(); */
		/* model.addAttribute("alarmInfo", alarmInfo); */
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		
		String userEmail = loginVO == null ? "user@example.com" : loginVO.getEmail();
		String password = loginVO == null ? "user" : loginVO.getPassword();
		
		//임시
		userEmail = "user@example.com";
		password = "user";
		
		int mailCount = mailUtil.getMailCount(userEmail, password);
		
		model.addAttribute("mailCount", mailCount);

		// 공지사항 메인컨텐츠 조회 끝 -----------------------------------

		return "main/EgovMainView";
	}
}