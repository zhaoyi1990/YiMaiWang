<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<li><a href="news.html">新闻</a></li>
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
				<dd><em><a href="news-add.html">新增</a></em><a href="news.html">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>修改用户</h2>
		<div class="manage">
			<form action="user_update.do" method="post">
				<table class="form">
					<tr>
						<td class="field">用户名：</td>
						<td><input type="text" class="text" name="user.eu_user_name" value="<s:property value='user.eu_user_name'/>"/></td>
					</tr>
					<tr>
						<td class="field">姓名：</td>
						<td><input type="text" class="text" name="user.eu_name" value="<s:property value='user.eu_name'/>" /></td>
					</tr>
					<tr>
						<td class="field">密码：</td>
						<td><input  type="text" class="text" name="user.eu_password" value="<s:property value='user.eu_password'/>" /></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<td>
							<s:if test="user.eu_sex==1">
								<input type="radio" name="user.eu_sex" value="1" checked="checked" />男 
								<input type="radio" name="user.eu_sex" value="2" />女
							</s:if><s:else>
								<input type="radio" name="user.eu_sex" value="1" />男 
								<input type="radio" name="user.eu_sex" value="2"  checked="checked"  />女
							</s:else>
						</td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td>
							<select name="year" id="year" onchange="setDay()">
								<s:iterator begin="1970" end="2016" step="1" var="i">
									<s:if test="year==#i">
										<option value="${i}" selected="selected">${i}</option>
									</s:if><s:else>
										<option value="${i}">${i}</option>
									</s:else>
								</s:iterator>
							</select>年
							<select name="month" id="month"  onchange="setDay()">
								<s:iterator begin="1" end="12" step="1" var="i">
									<s:if test="month==#i">
										<option value="${i}" selected="selected">${i}</option>
									</s:if><s:else>
										<option value="${i}">${i}</option>
									</s:else>
								</s:iterator>
							</select>月
							<select name="day" id="day">
								<s:iterator begin="1" end="max" step="1" var="i">
									<s:if test="day==#i">
										<option value="${i}" selected="selected">${i}</option>
									</s:if><s:else>
										<option value="${i}">${i}</option>
									</s:else>
								</s:iterator>
							</select>日
						</td>
					</tr>
					<tr>
						<td class="field">身份证号：</td>
						<td><input type="text" class="text" name="user.eu_identity_code" value="<s:property value='user.eu_identity_code'/>" /></td>
					</tr>
					<tr>
						<td class="field">电子邮箱：</td>
						<td><input type="text" class="text" name="user.eu_email" value="<s:property value='user.eu_email'/>" /></td>
					</tr>
					<tr>
						<td class="field">手机号码：</td>
						<td><input type="text" class="text" name="user.eu_mobile" value="<s:property value='user.eu_mobile'/>" /></td>
					</tr>
					<tr>
						<td class="field">送货地址：</td>
						<td><input type="text" class="text" name="user.eu_address" value="<s:property value='user.eu_address'/>" /></td>
					</tr>
					<tr>
						<td><input type="hidden" name="user.eu_user_id" value="<s:property value='user.eu_user_id'/>"></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="添加" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
