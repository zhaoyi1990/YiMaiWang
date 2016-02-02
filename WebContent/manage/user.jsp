<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
<script type="text/javascript" src="../scripts/jquery.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.gif" /></div>
	<div class="help"><a href="../index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<li class="current"><a href="user.jsp">用户</a></li>
			<li><a href="product.jsp">商品</a></li>
			<li><a href="order.html">订单</a></li>
			<li><a href="guestbook.html">留言</a></li>
			<li><a href="news.jsp">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员${sessionScope.user.eu_name}您好，今天是<s:date name="#request.today" format="yyyy-MM-dd"/>，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="user.jsp">用户管理</a></dd>
				<dt>商品信息</dt>
				<dd><em><a href="productClass_add.jsp">新增</a></em><a href="productClass.jsp">分类管理</a></dd>
				<dd><em><a href="product_add.jsp">新增</a></em><a href="product.jsp">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.html">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.html">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.jsp">新增</a></em><a href="news.jsp">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<thead>
					<tr>
						<th>ID</th>
						<th>姓名</th>
						<th>性别</th>
						<th>Email</th>
						<th>手机</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tb1">
					<s:iterator value="list">
						<tr>
							<td class="first w4 c">${eu_user_id-1}</td>
							<td class="w1 c">${eu_name}</td>
							<td class="w2 c">${eu_sex==1?'男':'女'}</td>
							<td>${eu_email}</td>
							<td class="w4 c">${eu_mobile}</td>
							<td class="w1 c"><s:a href="user_modify.do?user.eu_user_id=%{eu_user_id}" >修改</s:a> 
							<s:a href="user_delete.do?user.eu_user_id=%{eu_user_id}&pages.currentPage=%{pages.currentPage}" >删除</s:a></td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="6" style="text-align: center;">
							<s:a href="user.jsp?pages.currentPage=1">首页</s:a>
							<s:a href="user.jsp?pages.currentPage=%{pages.pageUp}">上一页</s:a>
							<s:a href="user.jsp?pages.currentPage=%{pages.pageDown}">下一页</s:a>
							<s:a href="user.jsp?pages.currentPage=%{pages.pageCount}">最后页</s:a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
