<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<fieldset>
		<legend>스프링 프레임워크  테스트 하기</legend>
		<h2>[기본 DispatcherServlet사용]${requestScope.message}</h2>
		<ul style="list-style:decimal;">
			<li><a href="<c:url value='/Basic/Controller.do?message=Hello Spring Contoller Interface'/>">Controller인터페이스로 컨트롤러 구현 </a></li>
			<li><a href="<c:url value='/Basic/Annotation.do?message=Hello Spring Annotaion'/>">어노테이션으로 컨트롤러 구현 </a></li>
		</ul>
		<h2>[또 다른 디스패처 서블릿사용]</h2>
		<c:url value="/Basic/AnotherDispatcher.kosmo?message=Hello Spring Another Dispatcher" var="url"/>
		<a href="${url}">또 다른 디스패처</a>
		<h2>DispatcherServlet과 관련된 빈 설정파일 이름 및 위치 변경</h2>
		<!-- 서블릿 초기화 파라미터 사용]
            -빈 설정파일의 이름 및 위치 변경 가능
     
     		1]WEB-INF인 경우 : /로 시작  
		    <param-value>
		     /WEB-INF/임의의 폴더/.../임의의 이름.xml    
		    </param-value>
    
		    2]src 밑에 위치한 경우:classpath: 
		           패키지 구분은 /로
		      예]
		      classpath:a/b/c/config.xml
		   아래는 공통(파라미터명은 반드시 contextConfigLocation로)
		   <param-name>contextConfigLocation</param-name>
    	-->
    	<ul style="list-style:decimal;">
    		<li><a href="<c:url value='/Basic/WEB-INF.nate?message=Hello Spring WEB-INF'/>">WEB-INF에 폴더 생성후 빈 설정파일 위치시키기</a></li>
    		<li><a href="<c:url value='/Basic/SRC.daum?message=Hello Spring SRC'/>">src(클래스 패스)에 패키지 생성후 빈 설정파일 위치시키기</a></li>
    	
    	</ul>
	</fieldset>
</body>
</html>