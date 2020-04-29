<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 실제 내용 시작 -->
<div class="container">
	<!-- 점보트론(Jumbotron) -->
	<div class="jumbotron">
		<h1>
			한줄 메모 게시판<small>등록페이지</small>
		</h1>
	</div>
	<div class="row">
		<div class="col-md-12">
			<form class="form-horizontal" method="post"
				action="<c:url value='/OneMemo/BBS/Write.bbs'/>">

				<div class="form-group">
					<label class="col-sm-2 control-label">제목</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="title"
							placeholder="제목을 입력하세요?">
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">내용</label>
					<!-- 중첩 컬럼 사용 -->
					<div class="col-sm-10">
						<div class="row">
							<div class="col-sm-8">
								<textarea class="form-control" name="content" rows="5"
									placeholder="내용 입력하세요"></textarea>
							</div>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">등록</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</div>
<!-- container -->

<!-- 경고창 모달 시작 -->
<div class="modal fade" id="small-modal" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-body">
				<button class="close" data-dismiss="modal">
					<span>&times;
				</button>
				</button>
				<h4 class="modal-title">
					<span class="glyphicon glyphicon-bullhorn">경고 메시지</span>
				</h4>
				<h5 id="warningMessage"></h5>
			</div>

		</div>
	</div>
</div>

<!-- 경고창 모달 끝 -->
<!-- 실제 내용 끝 -->

<script>
	    
    	$(function(){
    		
    		var focusObject;
    		
    		
    		//모달창이 닫혔을때 발생하는 이벤트 처리 - 해당 객체에 포커스 주기
    		$('#small-modal').on('hidden.bs.modal', function (e) {
    			focusObject.focus();    			
    		});
    		
    		$('form').on('submit',function(){
    			
    			
    			if($(this).get(0).title.value==""){
    				$('#warningMessage').html('제목을 입력하세요');
    				$('#small-modal').modal('show');
    				focusObject=$(this).get(0).title; 				
    				return false;
    			}
    			
    			if($(this).get(0).content.value==""){
    				$('#warningMessage').html('내용을 입력하세요');
    				$('#small-modal').modal('show');
    				focusObject=$(this).get(0).content; 				
    				return false;
    			}
    				
    			
    		});   		
    		
    	});
    
    </script>
