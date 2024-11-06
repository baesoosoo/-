<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div style="text-align: center;">
			    <h2>회원가입</h2>
			  <form action="${contextPath}/uat/uia/signupOk.do" method="POST"> 
			        <table style="margin: 0 auto;">
			            <tr>
			                <th><label for="mber_id">아이디:</label></th>
			                <td><input type="text" id="mber_id" name="mber_id" required></td>
			            </tr>
			            <tr>
			                <th><label for="password">비밀번호:</label></th>
			                <td><input type="password" id="password" name="password" required></td>
			            </tr>
			            <tr>
			                <th><label for="mber_nm">이름:</label></th>
			                <td><input type="text" id="mber_nm" name="mber_nm" required></td>
			            </tr>
			         
			            <tr>
			                <td colspan="2" style="text-align: center;">
			                    <input type="submit" value="가입하기">
			                </td>
			            </tr>
			        </table>
			    </form>
		  </div>


</body>
</html>