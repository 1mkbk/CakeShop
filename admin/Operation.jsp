<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>浏览历史</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
	<!--header-->
	<jsp:include page="header.jsp">
		<jsp:param name="flag" value="5"></jsp:param>
	</jsp:include>
	<!--//header-->
	<!--cart-items-->
	<div class="cart-items">
		<div class="container">
			<h3>浏览历史</h3>
				<table class="table table-bordered table-hover">
				<tr>
					<th width="15%">id</th>
					<th width="20%">操作名</th>
					<th width="15%">管理名</th>
					<th width="20%">ip地址</th>
					<th width="30%">操作时间</th>
				</tr>
				<c:forEach items="${userList }" var="user">
				<tr>
				<td><p>${user.id }</p></td>
				<td><p>${user.operator_name }</p></td>
				<td><p>${user.operation_name }</p></td>
				<td><p>${user.ip }</p></td>
				<td><p>${user.operation_time }</p></td>
				</c:forEach>
		</div>
	</div>
	<!--//cart-items-->
</body>
</html>