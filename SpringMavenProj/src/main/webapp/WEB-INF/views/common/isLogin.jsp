<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!--  isLogin.jsp -->
<c:if test="${empty sessionScope.id }">
	<script>
		alert('로그인후 이용하세요');
		location.replace("<c:url value='/OneMemo/Auth/Login.bbs'/>");
	</script>
</c:if>