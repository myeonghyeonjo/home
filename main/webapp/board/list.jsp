<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글목록</title>
</head>
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
<body>
<h1>게시글 목록</h1>
	<table >
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>
			
		</tr>
		<c:forEach items="${list}" var="item">
			<tr>
			 		<td> 
			 			<c:if test="${item.b_depth > 0}">
                        	<c:forEach begin="1" end="${item.b_depth}">
                            	&nbsp;&nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
                        	</c:forEach>
                        		RE : 
                    	</c:if>
					<a href="board-detail.do?b_idx=${item.b_idx}">${item.b_title}</a>
					</td>
			 		<td>${item.b_writer}</td>
					<td>${item.b_count}</td>
					<td>${item.b_date}</td> 
				
		     <tr>
		</c:forEach>
	</table>
</body>
</html>