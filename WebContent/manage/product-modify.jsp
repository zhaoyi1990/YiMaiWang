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
				<dd><em><a href="user-add.jsp">新增</a></em><a href="user.jsp">用户管理</a></dd>
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
		<h2>修改商品</h2>
		<div class="manage">
			<form action="product_update.do?product.ep_id=${product.ep_id }" method="post" enctype="multipart/form-data">
				<table class="form">
					<tr>
						<td class="field">商品名称：</td>
						<td><input type="text" class="text" name="product.ep_name" value="${product.ep_name}" /></td>
					</tr>
					<tr>
						<td class="field">所属分类：</td>
						<td>
							<select name="product.epc_id" onchange="clist(this)">
								
								<s:iterator value="plist">
									<s:if test="epc_id==product.epc_id">
										<option value="<s:property value="epc_id"/>" selected="selected">${epc_name}</option>
									</s:if><s:else>
										<option value="<s:property value="epc_id"/>">${epc_name}</option>		
									</s:else>
								</s:iterator>
							</select>
							<select name="product.epc_child_id" id="product_clist">
								<s:iterator value="clist">
									<s:if test="epc_id==product.epc_child_id">
										<option value="<s:property value="epc_id"/>" selected="selected">${epc_name}</option>
									</s:if><s:else>
										<option value="<s:property value="epc_id"/>">${epc_name}</option>		
									</s:else>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">商品图片：</td>
						<td><input id="imgFile" type="file" class="text" name="file" onchange="xianshi()"/></td>
					</tr>
					<tr id="imgtr">
						<td class="field">图片缩略图：</td>
						<td>
							<img id="fileImg" alt="图片显示有误" style="width: 100px;height: 100px;" src="../images/product/${product.ep_file_name}"/>
						</td>
					</tr>
					<tr>
						<td class="field">商品价格：</td>
						<td><input type="text" class="text tiny" name="product.ep_price" value="${product.ep_price}"/> 元</td>
					</tr>
					<tr>
						<td class="field">品牌：</td>
						<td><input type="text" class="text" name="product.ep_brand" value="${product.ep_brand}"/></td>
					</tr>                                                                  
					<tr>
						<td class="field">库存：</td>
						<td><input type="text" class="text tiny" name="product.ep_stock" value="${product.ep_stock }"/></td>
					</tr>
					<tr>
						<td class="field">条码号：</td>
						<td><input type="text" class="text" name="product.ep_bar_code" value="${product.ep_bar_code }"/></td>
					</tr>
					<tr>
						<td class="field">描述：</td>
						<td><textarea name="product.ep_description">${product.ep_description }</textarea> </td>
					</tr>
					<tr>
						<td></td>
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
