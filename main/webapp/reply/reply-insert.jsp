<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가</title>
</head>
<body>
<h2> 댓글의댓글등fdfdfdf록 </h2>
<form action="reply-reply-process.do" name="reply" method="post">
	<input type="hidden" name="r_group" value="${reply.r_group}">
	<input type="hidden" name="r_order" value="${reply.r_order}">
	<input type="hidden" name="r_depth" value="${reply.r_depth}">
	<p> 내용 : <input type="text" name="content"></p>
	<p> 작성자 : <input type="text" name="writer"></p>
	<p> <input type="submit" value="등록하기"></p>
</form>

</body>
</html>

