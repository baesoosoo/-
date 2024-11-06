<%--
  Class Name : EgovMainView.jsp 
  Description : 메인화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<title>표준프레임워크 경량환경 홈페이지 템플릿</title>
<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	
<!-- 전체 레이어 시작 -->

<div id="wrap">
	<!-- header 시작 -->
    <div id="header_mainsize">
    	<jsp:include page="/WEB-INF/jsp/main/inc/EgovIncHeader.jsp"/>
    </div>
    <div id="topnavi">
    	<jsp:include page="/WEB-INF/jsp/main/inc/EgovIncTopnav.jsp"/>
    </div>
    <!-- //header 끝 -->
	<!-- container 시작 -->
	<div id="main_container">
		<div class="left_div">
			<div class="left_child_div">
				<p class="font_size_25 font_weight_bold left_child_title">사용자정보</p>
				<div class="user_info_div">
					<p class="font_size_15 font_green"><span>대위 ${LoginVO.name}</span></p>
					<!-- <p class="font_size_15"><span>[결재] </span><span>0건</span></p>
					<p class="font_size_15"><span>[접수대기] </span><span>0건</span></p> -->
					<p class="font_size_15"><span>[안읽은편지] </span><span>${mailCount}건</span></p>
					<!-- <p class="font_size_15"><span>[인증서만료] </span><span>737일</span></p> -->
				</div>
			</div>
			<div class="left_child_div">
				<span class="font_size_25 font_weight_bold left_child_title">태세발령현황</span>
				<div class="event_box">
					<div>
						<img alt="" src="">
						<img alt="" src="">
						<img alt="" src="">
					</div>
					<div>
						<img alt="" src="">
						<img alt="" src="">
						<img alt="" src="">
					</div>
				</div>
			</div>
			<div class="left_child_div">
				<span class="font_size_25 font_weight_bold left_child_title">최근접속메뉴</span>
				<ul class="recent_menu_list">
					<li><a>최근접속메뉴1</a></li>
					<li><a>최근접속메뉴2</a></li>
					<li><a>최근접속메뉴3</a></li>
					<li><a>최근접속메뉴4</a></li>
					<li><a>최근접속메뉴5</a></li>
				</ul>
			</div>
		</div>
		<div class="rightmain_wrap">			
			<div id="right_div1">
				<p class="font_size_15 font_weight_bold left_child_title">상황개요</p>
				<table>
					<colgroup>
						<col width="20%"/>
					    <col width="80%"/>
					</colgroup>
					<thead>
						<tr>
							<th>발생일시</th>
							<th>내용</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${eventList}" var="result">
							<tr>
								<td>${result.frstRegisterPnttm}</td>
								<td><a href="/sht_webapp/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_000000000001&menuNo=11">${result.nttSj}</a></td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(eventList) == 0}">
							<tr>
								<td colspan="2">등록된 상황개요가 없습니다.</td>
							</tr>
                       	</c:if>
					</tbody>
				</table>
			</div>
			<div id="right_div2">
				<div id="right_child_div1">
					<p class="font_size_15 font_weight_bold">공지사항</p>
					<table>
						<colgroup>
							<col width="80%"/>
						    <col width="20%"/>
						</colgroup>
						<thead>
							<tr>
								<th>제목</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${notiList}" var="result">
								<tr>
									<td><a href="/sht_webapp/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA&menuNo=10">${result.nttSj}</a></td>
									<td>${result.frstRegisterPnttm}</td>
								</tr>
							</c:forEach>
							<c:if test="${fn:length(notiList) == 0}">
								<tr>
									<td colspan="2">등록된 공지사항이 없습니다.</td>
								</tr>
                        	</c:if>
						</tbody>
					</table>
				</div>
				<div id="right_child_div2">
					<p class="font_size_15 font_weight_bold">지휘관 강조사항</p>
					<table>
						<colgroup>
							<col width="80%"/>
						    <col width="20%"/>
						</colgroup>
						<thead>
							<tr>
								<th>제목</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderList}" var="result">
								<tr>
									<td><a href="/sht_webapp/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_000000000021&menuNo=12">${result.nttSj}</a></td>
									<td>${result.frstRegisterPnttm}</td>
								</tr>
							</c:forEach>
							<c:if test="${fn:length(orderList) == 0}">
								<tr>
									<td colspan="2">등록된 지휘관 강조사항이 없습니다.</td>
								</tr>
                        	</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- //code layer -->		
	</div>
	<!-- footer 시작 -->
	<div id="footer">
		<jsp:include page="/WEB-INF/jsp/main/inc/EgovIncFooter.jsp"/>
	</div>
	<!-- //footer 끝 -->
<!-- //전체 레이어 끝 -->
</div>
</body>
</html>

