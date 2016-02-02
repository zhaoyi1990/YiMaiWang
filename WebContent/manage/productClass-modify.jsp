<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.gif" /></div>
	<div class="help"><a href="../index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<li><a href="user.jsp">用户</a></li>
			<li class="current"><a href="product.jsp">商品</a></li>
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
		<h2>修改分类</h2>
		<div class="manage">
			<form action="productClass_update.do" method="post">
				<table class="form">
					<tr>
						<td class="field">父分类：</td>
						<td>
							<select name="epc.epc_parent_id">
								<option value="0" selected="selected">根栏目</option>
								<!-- 如果为顶层类则不允许修改父类防止出现逻辑死循环 -->
								<s:if test="epc.epc_parent_id!=0">
									<s:iterator value="list">
										
										<s:if test="epc_id==epc.epc_parent_id">
											<option value="<s:property value='epc_id'/>" selected="selected">
												<s:property value="epc_name"/>
											</option>
										</s:if><s:else>
											<option value="<s:property value='epc_id'/>">
												<s:property value="epc_name"/>
											</option>
										</s:else>
									</s:iterator>
								</s:if>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">分类名称：</td>
						<td><input type="text" class="text" name="epc.epc_name" value="<s:property value='epc.epc_name'/>" /></td>
					</tr>
					<tr>
						<td><input type="hidden" name="epc.epc_id" value="<s:property value='epc.epc_id'/>"></td>
						<td>
							<label class="ui-blue"><input type="submit" name="submit" value="更新" /></label>
							<label class="ui-blue"><s:a id="a_delete" href="productClass_delete.do?epc.epc_id=%{epc.epc_id}">删除</s:a></label>
							
						</td>
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
