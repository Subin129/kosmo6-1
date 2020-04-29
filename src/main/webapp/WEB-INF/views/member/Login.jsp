<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!-- 실제 내용 시작 -->
<div class="container">
	<!-- 점보트론(Jumbotron) -->
	<div class="jumbotron">
		<h1>
			한줄메모 게시판<small>로그인 페이지</small>
		</h1>
	</div>
	<%--
	<c:if test="${! empty NotMember or !empty param.NotLogin}">
		<div class="row">
			<div class="alert alert-warning fade in  col-sm-5">
				<button type="button" class="close" data-dismiss="alert">
					<span>&times;</span>
				</button>
				${NotMember}
			</div>
		</div>
	</c:if>

	<div class="row">
		<c:if test="${not empty sessionScope.id}" var="isLogin">
			<div class="alert alert-success col-md-5">${sessionScope.id}님
				즐감하세요</div>
		</c:if>
		<c:if test="${! isLogin}">
			<div class="col-md-12">
				<form class="form-horizontal" method="post"
					action="<c:url value='/OneMemo/Auth/LoginProcess.bbs'/>">
					<div class="form-group">
						<label for="id" class="col-sm-2  control-label">아이디</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="id" id="id"
								placeholder="아이디를 입력하세요">
						</div>
					</div>
					<div class="form-group">
						<label for="pwd" class="col-sm-2  control-label">비밀번호</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="pwd" name="pwd"
								placeholder="비밀번호를 입력하세요">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-danger">로그인</button>
						</div>
					</div>
				</form>
			</div>
		</c:if>
	</div>

 --%>

	<sec:authorize access="isAuthenticated()">
		<div class="alert alert-success col-md-5">
			<sec:authentication property="principal.username" />
			님 즐감하세요
		</div>
	</sec:authorize>
	<!-- 인증안된사람들 로그인폼 보여주기 -->

	<sec:authorize access="isAnonymous()">
		<c:if test="${not empty NotLogin}">
			<div class="row">
				<div class="alert alert-warning fade in  col-sm-5">
					<button type="button" class="close" data-dismiss="alert">
						<span>&times;</span>
					</button>
					아이디와 비번 틀림
				</div>
			</div>
		</c:if>
		<div class="col-md-12">
			<form class="form-horizontal" method="post"
				action="<c:url value='/OneMemo/Auth/LoginProcess.bbs'/>">
				<div class="form-group">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<label for="id" class="col-sm-2  control-label">아이디</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="id" id="id"
							placeholder="아이디를 입력하세요">
					</div>
				</div>
				<div class="form-group">
					<label for="pwd" class="col-sm-2  control-label">비밀번호</label>
					<div class="col-sm-3">
						<input type="password" class="form-control" id="pwd" name="pwd"
							placeholder="비밀번호를 입력하세요">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-danger">로그인</button>
					</div>
				</div>
			</form>
		</div>
	</sec:authorize>

</div>
<!-- container -->
<!-- 실제 내용 끝 -->
