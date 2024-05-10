<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">蛋糕店后台</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li ><a href="order_list.jsp">订单管理</a></li>
<%--                <li ><a href="/admin/user_list.jsp">客户管理</a></li>--%>
                <li ><a href="goods_list.jsp">商品管理</a></li>
                <li ><a href="NoRecord">滞销商品</a></li>
                <li ><a href="user_login">用户登录日志</a></li>
                <li ><a href="user_logout">用户注销日志</a></li>
                <li ><a href="browsing">用户浏览记录</a></li>
                <li ><a href="operation">管理员操作记录</a>
            </ul>
        </div>
    </div>
</nav>
