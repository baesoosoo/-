<%--
  Class Name : EgovNoticeInqire.jsp
  Description : 게시물 조회 화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.23   이삼섭          최초 생성
     2009.06.26   한성곤          2단계 기능 추가 (댓글관리, 만족도조사)
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 공통서비스 개발팀 이삼섭
    since    : 2009.03.23 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />"></script>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<title>메일 전송</title>

<script type="text/javascript">
    function fn_egov_select_mailList(pageNo) {
        document.frm.currentPage.value = pageNo; 
        document.frm.action = "<c:url value='/mailList.do'/>";
        document.frm.submit();  
    }
    
    function send_mail() {
        document.frm.action = "<c:url value='/sendMail.do'/>";
        document.frm.submit();  
    }
    
</script>

<style type="text/css">
    h1 {font-size:12px;}
    caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>

</head>
<body onload="onloading();">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>    
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <!-- header 시작 -->
    <div id="header_mainsize"><jsp:include page="/WEB-INF/jsp/main/inc/EgovIncHeader.jsp"/></div>
    <div id="topnavi"><jsp:include page="/WEB-INF/jsp/main/inc/EgovIncTopnav.jsp"/></div>        
    <!-- //header 끝 --> 
    <!-- container 시작 -->
    <div id="container">
        <!-- 좌측메뉴 시작 -->
        <div id="leftmenu"><jsp:include page="/WEB-INF/jsp/main/inc/EgovIncLeftmenu.jsp"/></div>
        <!-- //좌측메뉴 끝 -->
            <!-- 현재위치 네비게이션 시작 -->
            <div id="content">
                <div id="cur_loc">
                    <div id="cur_loc_align">
                        <ul>
                            <li>HOME</li>
                            <li>&gt;</li>
                            <li><strong>전문처리</strong></li>
                        </ul>
                    </div>
                </div>
                <!-- 검색 필드 박스 시작 -->
                <div id="search_field">
                    <div id="search_field_loc"><h2><strong>메일전송</strong></h2></div>
                </div>
				<form name="frm" method="post">
					<input type="hidden" name="currentPage" value="">
                    <div class="modify_user" >
                        <table>
                          <tr> 
					        <th width="15%" height="23" nowrap >제목</th>
					        <td width="85%" colspan="5" nowrap="nowrap">
					        	<input type="text" name="title">
					        </td>
					      </tr>
					      <tr> 
					        <th height="23" >내용</th>
					        <td colspan="5">
					         <div id="bbs_cn">
					           <textarea id="nttCn" name="content"  cols="75" rows="20"  style="width:99%" title="글내용"></textarea>
					         </div>
					        </td>
					      </tr>
                        </table>
                    </div>

                    <!-- 버튼 시작(상세지정 style로 div에 지정) -->
                    <div class="buttons" style="padding-top:10px;padding-bottom:10px;">
                      <!-- 목록/저장버튼  -->
                      <table border="0" cellspacing="0" cellpadding="0" align="center">
                        <tr>
			              <td width="10"></td>
                          <td>
                          	<a href="#LINK" onclick="javascript:send_mail(); return false;">전송</a> 
							<a href="#LINK" onclick="javascript:fn_egov_select_noticeList('1'); return false;">목록</a> 
                          </td>
			            </tr>
                      </table>
                    </div>
                    <!-- 버튼 끝 -->                           
                </form>

            </div>  
            <!-- //content 끝 -->    
    </div>  
    <!-- //container 끝 -->
    <!-- footer 시작 -->
    <div id="footer"><jsp:include page="/WEB-INF/jsp/main/inc/EgovIncFooter.jsp"/></div>
    <!-- //footer 끝 -->
</div>
<!-- //전체 레이어 끝 -->
</body>
</html>

