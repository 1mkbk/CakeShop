<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>用户注销信息记录日志</title>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container-fluid">
	<jsp:include page="header.jsp"></jsp:include>
	<table class="table table-bordered table-hover">
	<tr>
		<th width="30%">用户名</th>
		<th width="30%">注销时间</th>
		<th width="30%">ip地址</th>
	</tr>
		<c:forEach items="${userList }" var="user">
			<tr>
				<td><p>${user.username }</p></td>
				<td><p>${user.logoutTime }</p></td>
				<td><p>${user.ipAddress }</p></td>
		</c:forEach>
</table>
</div>
</body>
</html>