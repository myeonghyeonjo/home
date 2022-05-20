<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>게시판 상세</title>
<style>
	table {
		border-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
</style>
</head>
<body>
	<h1>게시판 상세페이지</h1>
	<table >
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
			<th>조회수</th>
			<th>작성일시</th>
		</tr>
		 <tr>
			<td>${board.b_title}</td>
			<td>${board.b_writer}</td>
			<td>${board.b_content}</td>
			<td>${board.b_count}</td>
			<td>${board.b_date}</td>
	     <tr>
	</table>
	
	<table>
		<tr style="height:50px;">
			<td>
				<a href="${path}/lcomputerstudy1/board-edit.do?b_idx=${board.b_idx}"	style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >수정</a>
			</td>
			<td>
				<a href="${path}/lcomputerstudy1/board-deleteprocess.do?b_idx=${board.b_idx}"	style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >삭제</a>
			</td>
			<td>
				<a href="${path}/lcomputerstudy1/board-reply.do?b_idx=${board.b_idx}"	style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >답글달기</a>
			</td>
			
			
		</tr>
	</table>
	
	<!-- 
	<form  action="reply-insert-process.do" name="reply" method="post">
		<input type="hidden" name="r_order" value="1">
		<input type="hidden" name="r_depth" value="0">
		<input type="hidden" name="r_group" value="${board.b_idx}">
		<p> 글쓴이 : <input type="text" name="writer"></p>
		<p> 내용 : <input type="text" name="content"p> 
		<input type="submit" value="댓글등록"></p>
	</form>
	 -->
	 
	<p> 글쓴이 : <input type="text" name="writer"></p>
	<p> 내용 : <textarea rows="5" cols="120" name="content" id="commentContent"></textarea><p> 
	<p><input type="button" value="댓글등록" class="btnReply"></p>
	

	<h1>댓글 목록</h1>
	<table id="tblReply">
		<tr>
			<th>내용</th>
			<th>글쓴이</th>
			<th>작성일시</th>
			
		</tr>
		<c:forEach items="${list}" var="item">
			 <tr>
			 
			 			<c:if test="${item.r_group == board.b_idx}">
			 				<td>
			 					<c:if test="${item.r_depth > 0}">
                        		<c:forEach begin="1" end="${item.r_depth}">
                            		&nbsp;&nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
                        		</c:forEach>
                        			RE : 
                    		</c:if>	
			 				<td>${item.r_content }</td>
			 				</td>
			 				
			 				<td>${item.r_writer}</td>
							<td>${item.r_date}</td>
							<td>
								<input type="button" value="수정" class="btnUpdate">
								<input type="button" value="삭제" class="btnDelete" r_idx="${item.r_idx}">
								<input type="button" value="댓글" class="btnReReply">
							</td>
                    	</c:if>
			  </tr>
			  <tr style="display: none;">
			  	<td colspan="3">
			  		<textarea rows="2" cols="120" name="">${item.r_content}</textarea>
			  		<textarea rows="2" cols="120" name="">${item.r_writer}</textarea>
			  		<input type="button" value="등록" class="btnUpdateOK" r_idx="${item.r_idx}">
			  		<input type="button" value="취소" class="btnCancel">
			  	</td>
			  </tr>
			   <tr style="display: none;">
			  	<td colspan="3">
			  		<textarea rows="2" cols="120" name=""></textarea>
			  		<textarea rows="2" cols="120" name=""></textarea>
			  		<input type="button" value="등록2" class="btnReReplyOK" r_idx="${item.r_idx}" r_replygroup="${item.r_replygroup}" r_order="${item.r_order}" r_depth="${item.r_depth}">
			  		<input type="button" value="취소2" class="btnCancel">
			  	</td>
			  </tr>
			
			
			  
			  
		</c:forEach>
		
		
		
		
		
		
		
		
		
		
		
	</table>
	
	
	
	
	
	
	
	
	
<script>

$(document).on('click', '.btnUpdate', function () {
	$(this).parent().parent().next().css("display", "");
	$(this).parent().parent().css("display", "none");
});

$(document).on('click', '.btnCancel', function () {
	$(this).parent().parent().next().css("display", "");
	$(this).parent().parent().css("display", "none");
});

$(document).on('click', '.btnReReply', function () {
	$(this).parent().parent().next().next().css("display", "");
	$(this).parent().parent().css("display", "none");
});







$(document).on('click', '.btnReply', function () {
	let bidx = '${board.b_idx}';
	let writer = $('input[name="writer"]').val();
	let content = $('#commentContent').val();
	
	
	
	
	$.ajax({
		method: "POST",
		url: "aj-insertComment.do",
		data: { b_idx: bidx, writer: writer, content: content }
	})
	.done(function( html ) {
		//console.log(html);
		$('#tblReply').html(html);
	});
	
});


$(document).on('click', '.btnUpdateOK', function () {
	let r_idx = $(this).attr('r_idx');
	let b_idx = '${board.b_idx}';
	let content = $(this).prev().prev().val();
	let writer = $(this).prev().val();
	
	
	
	$.ajax({
		method: "POST",
		url: "aj-updateComment.do",
		data: { r_idx: r_idx, b_idx: b_idx, content: content , writer: writer }
	})
	.done(function( html ) {
		//console.log(html);
		$('#tblReply').html(html);
	});
});




$(document).on('click', '.btnDelete', function () {
	let r_idx = $(this).attr('r_idx');
	let b_idx = '${board.b_idx}';
	$.ajax({
		method: "POST",
		url: "aj-deleteComment.do",
		data: { r_idx: r_idx , b_idx: b_idx }
	})
	.done(function( html ) {
		//console.log(html);
		$('#tblReply').html(html);
	});
});




$(document).on('click', '.btnReReplyOK', function () {
	let r_idx = $(this).attr('r_idx');
	let b_idx = '${board.b_idx}';
	let content = $(this).prev().prev().val();
	let writer = $(this).prev().val();
	let r_replygroup = $(this).attr('r_replygroup');
	let r_order = $(this).attr('r_order');
	let r_depth = $(this).attr('r_depth');
	$.ajax({
		method: "POST",
		url: "aj-reReplyComment.do",
		data: { r_idx: r_idx, b_idx: b_idx, content: content , writer: writer, r_replygroup:r_replygroup, r_order:r_order, r_depth:r_depth}
	})
	.done(function( html ) {
		//console.log(html);
		$('#tblReply').html(html);
	});
});


$(document).on('click', '.btnReplyview', function () {
	let bidx = '${board.b_idx}';
	let writer = $('input[name="writer"]').val();
	let content = $('#commentContent').val();
	
	
	
	
	$.ajax({
		method: "POST",
		url: "aj-re.do",
		data: { b_idx: bidx, writer: writer, content: content }
	})
	.done(function( html ) {
		//console.log(html);
		$('#tblReply').html(html);
	});
	
});






</script>






</body>
</html>