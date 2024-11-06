<%--
  Class Name : EgovIncHeader.jsp
  Description : 화면상단 Header(include)
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div id="logoarea">
    <h1><a href="<c:url value='/cmm/main/mainPage.do' />"><img src="<c:url value='/images/header/logo.png' />" alt="로고"/></a></h1>
</div>
<div class="header_login">
    <c:choose>
    	<c:when test="${empty LoginVO.name}">
    		<div id="header_loginname">
		        <a href="#" ></a>
		    </div>
		    <div class="header_loginconnection"></div>
		    <ul class="login_bg_area">
		        <li class="righttop_bgleft">&nbsp;</li>
		        <li class="righttop_bgmiddle"><a href="<c:url value='/uat/uia/egovLoginUsr.do'/>">로그인</a></li>
		        <li class="righttop_bgright">&nbsp;</li>
		    </ul>	
    	</c:when>
    	<c:otherwise>
    		<div id="header_loginname">
		        <a href="#LINK" onclick="alert('개인정보 확인 등의 링크 제공'); return false;"><c:out value="${LoginVO.name}"/> 님</a>
		    </div>
		    <!-- 수정해야하 -->
		    <!-- <div class="header_loginconnection"> 관리자로 로그인하셨습니다.</div> -->
		    <ul class="login_bg_area">
		        <li class="righttop_bgleft">&nbsp;</li>
		        <li class="righttop_bgmiddle"><a href="<c:url value='/uat/uia/actionLogout.do'/>">로그아웃</a></li>
		        <li class="righttop_bgright">&nbsp;</li>
		    </ul>
    	</c:otherwise>
    </c:choose>
    
    
</div>