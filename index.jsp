<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
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
    <jsp:param name="flag" value="1"></jsp:param>
</jsp:include>
<br><br><br><br>
<div class="alert alert-success">
	<p style="font-size: 28px;" align="center">欢迎来到我们的网上蛋糕商城，这里是您甜蜜梦想的起点！</p><br>
	<p style="font-size: 20px;" align="center">在这个独特的甜蜜角落，我们为您精心挑选了各式各样的美味蛋糕，每一款都是烘焙艺术的杰作，为您的味蕾奉上一场无与伦比的盛宴。</p><br>
    <p style="font-size: 20px;" align="center">无论是庆生、纪念日，还是与亲朋好友的欢聚时刻，我们都有完美的蛋糕选择，让您的每个特殊时刻都成为难忘的美好回忆。</p><br>
    <p style="font-size: 20px;" align="center">我们致力于为您提供高品质、新鲜出炉的蛋糕，通过精湛的烘焙技艺和丰富的口味组合，为您打造独一无二的味觉享受。</p><br>
	<p style="font-size: 20px;" align="center">而且，为了让您的购物体验更加愉悦，我们提供便捷的在线订购服务，确保您能随时随地轻松选购心仪蛋糕，将甜蜜送到您手中。</p><br>
	<p style="font-size: 20px;" align="center">不仅如此，我们的团队时刻倾听您的需求，提供个性化定制服务，确保每一块蛋糕都能符合您的期望，为您的庆典增色不少。</p><br>
	<p style="font-size: 20px;" align="center">感谢您选择我们，让我们一同走进这个充满甜蜜诱惑的蛋糕世界。</p><br>
	<p style="font-size: 20px;" align="center">期待为您带来甜蜜的每一刻，让美好时光从这里开始。</p><br>
	<p style="font-size: 28px;" align="center">祝您在我们的蛋糕商城中购物愉快！</p></div>

</body>
</html>