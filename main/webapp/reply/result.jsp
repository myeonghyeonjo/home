<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				<a href="reply-reply.do?r_order=${item.r_order}">${item.r_content}
			</td>
			<td>${item.r_writer}</td>
			<td>${item.r_date}</td>
        </c:if>
	</tr>
</c:forEach>