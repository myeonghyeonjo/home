<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
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
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
</style>
</head>
<body>
		<h1>회원 상세페이지</h1>
	<table >
		<tr>
			<th>회원ID</th>
			<th>회원이름</th>
			<th>회원전화번호</th>
			<th>회원나이</th>
		</tr>
			 <tr>
				<td>${user.u_id}</td>
				<th>${user.u_name}</th>
				<th>${user.u_tel}</th>
				<th>${user.u_age}</th>
		     <tr>
	</table>
</body>
</html>

</style>
	<tr style="height:50px;">
		<td style="border:none;">
			<a href="${path}/lcomputerstudy1/user-edit.do?u_idx=${user.u_idx}"	style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >수정</a>
		</td>
		<td style="border:none;">
			<a href="${path}/lcomputerstudy1/user-deleteprocess.do?u_idx=${user.u_idx}"	style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >삭제</a>
		</td>
	</tr>


