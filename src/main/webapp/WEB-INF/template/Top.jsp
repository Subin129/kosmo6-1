<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<style>
body {
	padding-top: 50px;
}
</style>
<script>
	function isLogin() {
		$
				.ajax({
					url : "<c:url value='/OneMemo/Auth/IsLogin.bbs'/>",
					dataType : 'json',
					success : function(data) {

						console.log(typeof data);
						if (data.IsLogin == 'YES')
							location
									.replace("<c:url value='/OneMemo/BBS/List.bbs'/>");
						else {
							alert('로그인 후 이용');
							location
									.replace("<c:url value='/OneMemo/Auth/Login.bbs'/>");
						}
					},
					error : function(e) {
						console.log('에러 : ', e);
					}

				});
	}
</script>

<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
	<!-- 상단메뉴 시작 -->
	<div class="container-fluid">
		<!--화면 크기가 작을때 보여지는 네비게이션바(모바일용)  -->
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#collapse-menu">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href='<c:url value="/DataRoom/Index.kosmo"/>'>
				<span class="glyphicon glyphicon-education"></span> KOSMO
			</a>
		</div>
		<!-- navbar-header -->

		<!-- 화면 크기가 클때 상단에 보여지는 메뉴(데스크탑용) -->
		<div class="collapse navbar-collapse" id="collapse-menu">
			<!-- 네비게이션바에 폼 추가 -->
			<form class="navbar-form navbar-right">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="검색" />
				</div>
				<button class="btn btn-info">확인</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href='<c:url value="/"/>'>HOME</a></li>

				<!--  
				<c:if test="${empty sessionScope.id }" var="isNotLogin">
					<li><a href="<c:url value="/OneMemo/Auth/Login.bbs"/>">로그인</a></li>
				</c:if>
				<c:if test="${not  isNotLogin}">
					<li><a href="<c:url value="/OneMemo/Auth/Logout.bbs"/>">로그아웃</a></li>
				</c:if>
-->
				<sec:authorize access="isAnonymous()">
					<li><a href="<c:url value="/OneMemo/Auth/Login.bbs"/>">로그인</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">

					<li><a href="<c:url value="/OneMemo/Auth/Logout.bbs"/>">로그아웃</a></li>
				</sec:authorize>

				<li><a href='javascript:isLogin();'>한줄댓글 게시판</a></li>
				<li><a href="#">Q&A</a>
			</ul>
		</div>
		<!-- /.navbar-collapse -->

	</div>
	<!-- /.container-fluid -->
</nav>
<!--  상단 메뉴 끝 -->
<!-- action은 스프링 시큐리티의 디폴트 로그아웃 URL지정 -->
<form id="logoutForm" method="post" action="<c:url value='/OneMemo/Auth/Logout.bbs'/>">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

<script>
function logout() {
	$('#logoutForm').submit();
}

</script>
