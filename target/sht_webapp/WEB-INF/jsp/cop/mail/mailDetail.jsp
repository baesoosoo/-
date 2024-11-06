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
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
    function fn_egov_select_mailList(pageNo) {
        document.frm.currentPage.value = pageNo; 
        document.frm.action = "<c:url value='/mailList.do'/>";
        document.frm.submit();  
    }
    
</script>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<title>메일 조회</title>

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
                            <li><strong>전문보고</strong></li>
                        </ul>
                    </div>
                </div>
                <!-- 검색 필드 박스 시작 -->
                <div id="search_field">
                    <div id="search_field_loc"><h2><strong>글조회</strong></h2></div>
                </div>
				<form name="frm" method="post" action="">
					<input type="hidden" name="currentPage" value="">
					<input type="submit" id="invisible" class="invisible"/>

                    <div class="modify_user" >
                        <table>
                          <tr> 
					        <th width="15%" height="23" nowrap >제목</th>
					        <td width="85%" colspan="5" nowrap="nowrap"><c:out value="${mailVO.title}" />
					        </td>
					      </tr>
					      <tr> 
					        <th width="15%" height="23" nowrap >보낸이</th>
					        <td width="15%" nowrap="nowrap">
					            <c:out value="${mailVO.from}" />
					        </td>
					        <th width="15%" height="23" nowrap >작성시간</th>
					        <td width="15%" nowrap="nowrap"><c:out value="${mailVO.sendDate}" />
					        </td>
					      </tr>    
					      <tr> 
					        <th height="23" >글내용</th>
					        <td colspan="5">
					         <div id="bbs_cn">
					         	${mailVO.content}
					           <%-- <textarea id="nttCn" name="nttCn"  cols="75" rows="20"  style="width:99%" readonly="readonly" title="글내용"><c:out value="${result.content}" escapeXml="true" /></textarea> --%>
					         </div>
					        </td>
					      </tr>
					      <c:if test="${not empty result.atchFileId}">
					          <c:if test="${result.bbsAttrbCode == 'BBSA02'}">
					          <tr> 
					            <th height="23" >첨부이미지</th>
					            <td colspan="5">
					                    <c:import url="/cmm/fms/selectImageFileInfs.do" charEncoding="utf-8">
					                        <c:param name="atchFileId" value="${egovc:encryptSession(result.atchFileId, pageContext.session.id)}" />
					                    </c:import>
					            </td>
					          </tr>
					          </c:if>
					          <tr> 
					            <th height="23">첨부파일 목록</th>
					            <td colspan="5">
					                <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					                    <c:param name="param_atchFileId" value="${egovc:encrypt(result.atchFileId)}" />
					                </c:import>
					            </td>
					          </tr>
					      </c:if>
					      <c:if test="${anonymous == 'true'}">
					      <tr> 
					        <th height="23"><label for="password"><spring:message code="cop.password" /></label></th>
					        <td colspan="5">
					            <input name="password" title="암호" type="password" size="20" value="" maxlength="20" >
					        </td>
					      </tr>
					      </c:if>   
                        </table>
                    </div>

                    <!-- 버튼 시작(상세지정 style로 div에 지정) -->
                    <div class="buttons" style="padding-top:10px;padding-bottom:10px;">
                      <!-- 목록/저장버튼  -->
                      <table border="0" cellspacing="0" cellpadding="0" align="center">
                        <tr>
                         <%-- <% if(null != session.getAttribute("LoginVO")){ %>
			             <c:if test="${result.frstRegisterId == sessionUniqId}">     
			                  <td>
			                     <a href="#LINK" onclick="javascript:fn_egov_moveUpdt_notice(); return false;">수정</a> 
			                  </td>
			                  
			                  <td width="10"></td>
			                  <td>
			                     <a href="#LINK" onclick="javascript:fn_egov_delete_notice(); return false;">삭제</a> 
			                  </td>
			             </c:if>    
			             <c:if test="${result.replyPosblAt == 'Y'}">     
			                  <td width="10"></td>
			                  <td>
			                     <a href="#LINK" onclick="javascript:fn_egov_addReply(); return false;">답글작성</a> 
			                  </td>
			              </c:if>
			              <% } %> --%>
			              <td width="10"></td>
                          <td>
                              <a href="#LINK" onclick="javascript:fn_egov_select_mailList('1'); return false;">목록</a> 
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

