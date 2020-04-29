<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<div class="container">
<h1>
	Hello wo1d12d12rld!  
</h1>

<p>  The time on the server 12d12d1d12d1is ${serverTime}.21312 </p>

<fieldset>
<legend>정적 메서드(static resource(이미지, 동영상, .css, .js파일 등))</legend>

<h2>resourses 태그 사용 : webapp/resourses아래에 있는 정적 메소드 표시</h2>
<img src="<c:url value='/resources/images/sumnail.png'/>" alt="webapp/resources"/>

<h2>resourses 태그 사용 : webapp 밑에 images폴더 생성한경우 </h2>
<img src="<c:url value='/temporary/sumnail.png'/>" alt="temporary/resources"/>

<h2>resourses 태그 미사용 :	 &lt;default-servlet-handler &gt;  -폴더명으로 접근 </h2>
<img src="<c:url value='temporary/images/sumnail.png'/>" alt="폴더명으로 접근"/>



</fieldset>


</div>
