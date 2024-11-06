<%--
  Class Name : EgovIncTopnav.jsp
  Description : 상단메뉴(include)
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- topmenu start -->
<form name="selectOne" action="#LINK">
<input name="menuNo" type="hidden" />
<input name="link" type="hidden" />
</form>

<ul class="topnav">
    <li><a href="#">VCP</a></li>
	<li><a href="#">상황도</a></li>
	<li><a href="${contextPath}/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA&menuNo=10">종합상황</a></li>
	<li><a href="${contextPath}/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_000000000011">기능실포털</a></li>
	<li><a href="${contextPath}/mailList.do?currentPage=1">전문처리</a></li>
	<li><a href="#">웹캅</a></li>
    <li><a href="${contextPath}/cop/smt/sim/EgovIndvdlSchdulManageMonthList.do?menuNo=52">체계관리</a></li>
</ul>

<!-- //topmenu end -->