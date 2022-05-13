<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>商品分类列表</title>

</head>
<body>
	<div class="hp-context-page">
		<div class="bt_top_bar">
			<button class="bt_add">添加</button>
			<button class="bt_delete">删除</button>
		</div>
		<table>
			<tr>
				<th style="width: 40px;"><input type="checkbox"></th>
				<th width="10%">id</th>
				<th>商品分类名称</th>
				<th>商品分类描述</th>
				<th>分类图标</th>
				<th>操作</th>
			</tr>	
			<c:forEach items="${productTypePages.pageData}" var="productType">
				<tr>
					<td><input type="checkbox"></td>
					<td>${productType.id }</td>
					<td>${productType.productTypeName }</td>
					<td>${productType.productTypeDesc }</td>
					<td><i class="iconfont ${productType.productTypeIcon }" style="font-size: 25px !important"></i></td>
					<td style="width: 170px;">
						<button class="bt_update bt_op">修改</button>
						<button class="bt_delete bt_op">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="pagination3" class="page_fl"></div>
	</div>

	<script type="text/javascript">
		$(function (){
			//分页
			var currentPage = ${productTypePages.pageNum};
			var pageSize = ${productTypePages.pageSize};
			var totalPage = ${productTypePages.pageTotal};
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
					$('.hp-context').load("${ctx}/admin/productType/list?pageNum=" + current);
				}
			});

			//添加方法
			$('.bt_add').on('click', function(){
				layer.open({
					title: "添加分类",
					type: 2,
					area: ['700px', '450px'],
					fixed: false, //不固定
					maxmin: true,
					content: basePath + '/admin/productType/addPage'
				});
			});

			//修改方法
			$('.bt_update').on('click', function(){
				var productTypeName = $(this).parent().parent().children("td:eq(2)").text();
				layer.open({
					title: "修改分类",
					type: 2,
					area: ['700px', '450px'],
					fixed: false, //不固定
					maxmin: true,
					content: basePath + '/admin/productType/updatePage?productTypeName='+productTypeName
				});
			});

			//删除方法
			$('.bt_delete').click( function(){
				var productTypeName = $(this).parent().parent().children("td:eq(2)").text();
				$.post("/admin/productType/delete", "productTypeName=" + productTypeName, function (e) {
					if (e === "ok") {
						layer.msg("删除成功", {icon: 1});
						$('.hp-context').load("${ctx}/admin/productType/list?pageNum=" + ${productTypePages.pageNum});
					} else {
						layer.msg("删除失败：" + e, {icon: 2});
					}
				})

				return false;
			});

		});
	</script>
</body>
</html>