<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>滞销商品</title>
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
			<h3>滞销商品</h3>
				<table class="table table-bordered table-hover">
				<tr>
					<th width="30%">商品id</th>
					<th width="30%">商品名</th>
					<th width="30%">商品价格</th>
				</tr>
				<c:forEach items="${topProducts }" var="g">
				<tr>
				<td><p>${g.id }</p></td>
				<td><p>${g.name }</p></td>
				<td><p>${g.price }</p></td>
				</c:forEach>
		</div>
	</div>
	<!--//cart-items-->
</body>
</html>