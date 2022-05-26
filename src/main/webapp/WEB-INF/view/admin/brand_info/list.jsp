<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>商品品牌列表</title>
<script type="text/javascript">
	$(function (){
		//分页
		var currentPage = ${BrandPages.pageNum};
		var pageSize = ${BrandPages.pageSize};
		var totalPage = ${BrandPages.pageTotal};
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
				$('.hp-context').load("${ctx}/admin/brand/list?pageNum=" + current);
			}
		});
		
		//添加方法
		$('.bt_add').on('click', function(){
			layer.open({
				title: "添加品牌",
		  		type: 2,
			  	area: ['700px', '450px'],
			  	fixed: false, //不固定
			  	maxmin: true,
			  	content: basePath + '/admin/brand/addPage'
			});
		})
		
		//修改方法
		$('.bt_update').on('click', function(){
			var id = $(this).parent().parent().children("td:eq(1)").text();
			layer.open({
				title: "修改品牌",
		  		type: 2,
			  	area: ['700px', '450px'],
			  	fixed: false, //不固定
			  	maxmin: true,
			  	content: basePath + '/admin/brand/updatePage?id='+id
			});
		})

		//删除方法
		$('.bt_delete.bt_op').click( function(){
			var id = $(this).parent().parent().children("td:eq(1)").text();
			layer.open({
				content: '确定将这条数据删除吗？',
				btn: ['确认', '取消'],
				shadeClose: false,
				yes: function(){
					$.post("/admin/brand/delete", "id=" + id, function (e) {
						if (e === "ok") {
							layer.msg("删除成功", {icon: 1});
							$('.hp-context').load("${ctx}/admin/brand/list?pageNum=" + ${BrandPages.pageNum});
						} else {
							layer.msg("删除失败：" + e, {icon: 2});
						}
					});
				}
			});
			return false;
		});

		//全选
		$("#selectAll").on('click', function () {
			if (this.checked) {
				$("input:checkbox").prop("checked",true);
			} else {
				$("input:checkbox").prop("checked",false);
			}
		});

		//多选删除方法
		$('div>.bt_delete').click( function(){
			var cks = $("[type='checkbox']:gt(0):checked");
			var len = cks.length;
			if (len===0) {
				layer.msg('至少选中一条数据', {icon: 2});
			}else {
				layer.open({
					content: '确定将这' + len + '条数据删除吗？',
					btn: ['确认', '取消'],
					shadeClose: false,
					yes: function(){
						var ids = [];
						for (var i = 0; i < cks.length; i++) {
							var ck = cks[i];
							ids.push($(ck).parent().next().text());
						}
						$.post('/admin/brand/deleteSelect', {
							ids: ids.join()
						}, function (e) {
							if (e === "ok") {
								$('.hp-context').load("${ctx}/admin/brand/list?pageNum=" + ${BrandPages.pageNum});
							}
							layer.closeAll();
						})
					}
				});
			}
			return false;
		});

	});

</script>
</head>
<body>
	<div class="hp-context-page">
		<div class="bt_top_bar">
			<button class="bt_add">添加</button>
			<button class="bt_delete">删除</button>
		</div>
		<table>
			<tr>
				<th style="width: 40px;"><input id="selectAll" type="checkbox"></th>
				<th width="10%">id</th>
				<th>商品品牌名称</th>
				<th>所属分类</th>
				<th>品牌Logo</th>
				<th>操作</th>
			</tr>	
			<c:forEach items="${BrandPages.pageData }" var="brand">
				<tr>
					<td><input type="checkbox"></td>
					<td>${brand.id }</td>
					<td>${brand.brandName }</td>
					<td>${brand.brandType }</td>
					<td><img alt="" src="${ctx}/common/getImage?image=${brand.brandImg }" width="50px;"></td>
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