<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>用户列表</title>
<script type="text/javascript">
	$(function (){
		//分页
		var currentPage = ${userPages.pageNum};
		var pageSize = ${userPages.pageSize};
		var totalPage = ${userPages.pageTotal};
		$("#pagination3").pagination({
			currentPage: currentPage,
			totalPage: totalPage,
			isShow: true,
			count: pageSize,
			homePageText: "首页",
			endPageText: "尾页",
			prevPageText: "上一页",
			nextPageText: "下一页",
			callback: function(current) {
				$('.hp-context').load("${ctx}/admin/user/list?pageNum=" + current);
			}
		});
		
		//添加方法
		$('.bt_add').on('click', function(){
			layer.open({
				title: "添加用户",
		  		type: 2,
			  	area: ['700px', '450px'],
			  	fixed: false, //不固定
			  	maxmin: true,
			  	content: basePath + '/admin/user/addPage'
			});
		})

		//修改方法
		$('.bt_update').on('click', function(){
			var type = $(this).parent().parent().children("td:eq(4)").text();
			if (type == '1') {
				layer.msg("权限不足禁止操作",{icon: 2});
				return false;
			}

			var id = $(this).parent().parent().children("td:eq(1)").text();

			layer.open({
				title: "修改用户",
		  		type: 2,
			  	area: ['700px', '450px'],
			  	fixed: false, //不固定
			  	maxmin: true,
			  	content: basePath + '/admin/user/updatePage?id='+id
			});
		})

		//删除方法
		$('.bt_delete.bt_op').click( function(){
			var id = $(this).parent().parent().children("td:eq(1)").text();
			if(id==='0') {
				layer.msg("超级管理员账户禁止删除", {icon: 3});
				return false;
			}
			var type = $(this).parent().parent().children("td:eq(4)").text();
			if (type == '1') {
				layer.msg("权限不足禁止操作",{icon: 2});
				return false;
			}
			layer.open({
				content: '确定将这条数据删除吗？',
				btn: ['确认', '取消'],
				shadeClose: false,
				yes: function(){
					$.post("/admin/user/delete", "id=" + id, function (e) {
						if (e === "ok") {
							layer.msg("删除成功", {icon: 1});
							$('.hp-context').load("${ctx}/admin/user/list?pageNum=" + ${userPages.pageNum});
						} else {
							layer.msg("删除失败：" + e, {icon: 2});
						}
					});
				}
			});
			return false;
		});
	})
</script>
</head>
<body>
	<div class="hp-context-page">
		<div class="bt_top_bar">
			<button class="bt_add">添加</button>
		</div>
		<table>
			<tr>
				<th style="width: 40px;"><input type="checkbox"></th>
				<th width="10%">id</th>
				<th>用户名称</th>
				<th>用户密码</th>
				<th>用户类型</th>
				<th>操作</th>
			</tr>	
			<c:forEach items="${userPages.pageData }" var="user">
				<tr>
					<td><input type="checkbox"></td>
					<td>${user.id }</td>
					<td>${user.username }</td>
					<td>${user.password }</td>
					<td style="display: none">${user.type }</td>
					<td><c:if test="${user.type == 0}" >管理用户</c:if>
						<c:if test="${user.type == 1}" >前台用户</c:if></td>
					<td style="width: 170px;">
						<button class="bt_update bt_op">修改</button>
						<button class="bt_delete bt_op">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="pagination3" class="page_fl"></div>
	</div>
</body>
</html>