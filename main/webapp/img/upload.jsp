<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- form의 형태로 전송 
		용량이 큰 파일인 경우에 enctype="multipart/form-data" 부분이 반드시 필요하다.
	-->
	<form method="post" action="imgup.jsp" enctype="multipart/form-data">
		<input type="file" name="filename" size=40>
		<input type="submit" value="업로드">
	</form>

</body>
</html>