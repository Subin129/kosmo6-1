<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<div class="container">
	<h1>
		Hello world!  
	</h1>	
	<p>  The time on the server is ${serverTime}. </p>
	<fieldset>
		<legend>static resource(이미지,동영상,.css,.js파일등등)표시방법</legend>
		<h2>resource태그 사용-webapp/resources아래에 있는 정적 리소스 표시-mapping에 지정한 url로 접근</h2>
		<img src="<c:url value='/resources/images/sumnail.png'/>" alt="wepapp/resources"/>
		<h2>resource태그 사용-wepapp밑에 images폴더 생성한 경우-mapping에 지정한 url로 접근</h2>
		<img src="<c:url value='/temporary/sumnail.png'/>" alt="wepapp/images"/>
		<h2>resource태그 미 사용-&lt;default-servlet-handler/&gt;-폴더명으로 접근</h2>
		<img src="<c:url value='/images/sumnail.png'/>" alt="폴더명으로 접근"/>
	</fieldset>
	
</div>
