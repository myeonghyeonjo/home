<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>게시글 작성</h1>
        <hr>

        <form action="boardinsert2" method="post">
        <!--  csrf  -->
           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="text" name="title" placeholder="제목 입력">
            <input type="text" name="content" placeholder="내용 입력">
            <input type="text" name="writer" placeholder="작성자 입력">
           
            <button type="submit">글작성하기</button>
        </form>
</body>
</html>
