<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- 아이디 얻어서 var에 지정한 변수 id에 저장 : 세군데서 사용해서 -->
<sec:authentication property="principal.username" var="username"/>	
	<sec:authentication property="principal.authorities" var="authorities"/>	
	
<style>
/* 수정/삭제/목록 버튼 사이 간격 주기 */
.btn-success {
	margin-right: 5px;
}
</style>
<script>
	$(function() {

		//코멘트 입력 / 수정처리
		$('#submit').click(function() {
			if($(this).val() == '등록')
				var urlEdit = "<c:url value='/MemoComment/BBS/Write.bbs'/>";
				else
					var urlEdit ="<c:url value='/MemoComment/BBS/Edit.bbs'/>";
			
			
			$.ajax({
				url : urlEdit,
				data : $('#frm').serialize(),
				dataType : 'text',
				type : 'post',
				success : function(data) {
					console.log(data);

					showComments();
					$('#title').val("");
					$('#title').focus();
					if($('#submit').val()=='수정')
						$('#submit').val('등록');
				}

			});
		});
		
		
		
		
		//글번호 가져오기(ajax)
		var showComments = function() {
			$.ajax({
				url : "<c:url value='/MemoComment/BBS/List.bbs'/>",
				data : {
					"no" : "${record.no}"
				},
				dataType : 'json',
				type : 'post',
				success : showComments_,
				error : function(e) {
					console.log('에러 : ', e);
				}
			});

		};
		
		

		var showComments_ = function(data) {

			var comments = "<h2>한줄 댓글 목록</h2>";
			comments += "<table class='table table-borderd'>";
			comments += "<tr style='text-align:right'> <th width='15%'>작성자</th> <th width='50%'>코멘트</th>  <th>작성일</th> <th>삭제</th> </tr>";
			if (data.length == 0) {
				comments += "<tr><td colspan='4'>등록된 코멘트가 없어요</td></tr>";
			}
			$.each(data, function(index, elements) {
				comments += "<tr><td>" + elements['NAME'] + '</td>';
			//	if ('${sessionScope.id}' == elements['ID']) 시큐리티 미사용
				if ('${username}' == elements['ID'])
					comments += "<td><span class ='commentEdit' title='"+elements['CNO']+"' style='curser'>" + elements['LINECOMMENT'] + '</span></td>';
				else
					comments += "<td>" + elements['LINECOMMENT'] + '</td>';
				comments += "<td>" + elements['CPOSTDATE'] + '</td><td>';
				
				//if ('${sessionScope.id}' == elements['ID'])
				if ('${username}' == elements['ID'])
					comments += "<span class='commentDelete' title='"+ elements['CNO']+"'>삭제</span>";
				else
					comments += "<span style='color:gray;font-size:.8em'>"+'삭제불가'+"</span>";
				comments += "</td></tr>";
		
			});

			comments += "</table>";

			$('#comments').html(comments);
			
			$('.commentEdit').click(function () {
				console.log('클릭한 댓글의 CNO : ', $(this).attr('title'));
				$('#title').val($(this).html());
				$('#submit').val('수정');
				$('input[name=cno]').val($(this).attr('title'));
			});
			$(".commentDelete").click(function () {
				$.ajax({url :"<c:url value='/MemoComment/BBS/Delete.bbs'/>",
				data : {cno : $(this).attr('title')},
				type:'post',
				success : function () {showComments();}
				});
			});
			
			
			
		};

		showComments();
		
		
		
	
		
	});
</script>


<!-- 실제 내용 시작 -->
<div class="container">
	<!-- 점보트론(Jumbotron) -->
	<div class="jumbotron">
		<h1>
			한줄메모 게시판<small>상세보기 페이지</small>
		</h1>
	</div>
	<!-- 시큐리티 사용시 사용자 권한 출력 div -->
	
	<div class ="col-md-offset-2 col-md-8">
	<ul>
	<c:forEach items="${authorities}" var="autority">
		<li>${autority}</li>
	</c:forEach>
	</ul>
	</div>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table table-bordered table-striped">
				<tr>
					<th class="col-md-2 text-center">번호</th>
					<td>${record.no}</td>
				</tr>
				<tr>
					<th class="text-center">제목</th>
					<td>${record.title}</td>
				</tr>
				<tr>
					<th class="text-center">작성자</th>
					<td>${record.name}</td>
				</tr>

				<tr>
					<th class="text-center">등록일</th>
					<td>${record.postDate}</td>
				</tr>
				<tr>
					<th class="text-center" colspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="2">${record.content}</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- row -->
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<!-- .center-block 사용시 해당 블락의 크기를 지정하자 -->
			<ul id="pillMenu" class="nav nav-pills center-block"
				style="width: 280px; margin-bottom: 10px">
				<c:if test="${username == record.id }">
					<li><a
						href="<c:url value='/OneMemo/BBS/Edit.bbs?no=${record.no}'/>"
						class="btn btn-success">수정</a></li>
					<li><a href="javascript:isDelete();" class="btn btn-success">삭제</a></li>
				</c:if>

				<li><a
					href="<c:url value='/OneMemo/BBS/List.bbs?nowPage=${param.nowPage}'/>"
					class="btn btn-success">목록</a></li>

			</ul>
		</div>
	</div>
	<!-- row -->
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="text-center">
				<!-- 한줄 코멘트 입력 폼-->
				<h2>한줄 댓글 입력 폼</h2>
				<form class="form-inline" id="frm" method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="no"
						value="${record.no}" />

					<!-- 수정 및 삭제용 파라미터 -->
					<input type="hidden" name="cno" /> <input placeholder="댓글을 입력하세요"
						id="title" class="form-control" type="text" size="50"
						name="linecomment" /> <input class="btn btn-success" id="submit"
						type="button" value="등록" />

				</form>
				<div class="row" id="comments">
					<!-- 한줄 코멘트 목록-->
					<!-- ajax로 아래에 코멘트 목록 뿌리기 -->

				</div>
			</div>
		</div>
	</div>


</div>
<!-- container -->


<!-- 실제 내용 끝 -->

<script>
	function isDelete() {
		if (confirm("정말로 삭제 하시겠습니까?"))
			location
					.replace("<c:url value='/OneMemo/BBS/Delete.bbs?no=${record.no}'/>");

	}
</script>
