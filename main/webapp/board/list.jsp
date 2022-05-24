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
	
	
	ul {
		width:400px;
		height:50px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
	}


	
</style>
<body>
<h1>게시글 목록</h1>
	<form action="board-search-process.do" method="post">

				  <form>
            <select name="opt">
					<option value="1">제목1</option>
				

					<option value="2">작성자</option>

					<option value="3">제목+내용</option>

				</select>

				&emsp;

				<input type="text" name="keyWord">&emsp;

				<input type="submit"  value="검색">

			</form>








	
	<table >
		<tr>
			<td colspan="3">전체 게시글 수 : ${pagination.count}</td>
		<tr>
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


	
<!-- 아래부터 pagination -->
	<div>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.prevPage >= 1}">
					<li>
						<a href="board-list.do?page=${pagination.prevPage}">
							◀
						</a>
					</li>
				</c:when>
			</c:choose> 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				
					<c:choose>
						<c:when test="${ pagination.page eq i }">
							
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.page ne i }">
							<li>
								<a href="board-list.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.nextPage le pagination.lastPage }">
					<li style="">
						<a href="board-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>



</body>
</html>