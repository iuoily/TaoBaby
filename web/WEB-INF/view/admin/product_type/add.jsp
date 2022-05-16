<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>添加商品分类</title>

</head>
<body>
	<div class="hp-context-page">
		<form class="hp-form">
			<div class="hp-form-item">
				<label class="hp-form-label">分类名称</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="productTypeName", id="productTypeName">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">分类描述</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="productTypeDesc">
				</div>	
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">图标</label>
				<div class="hp-input-block">
					<label class="hp-input" style="height: 36px; cursor:pointer;" id="icon_input" p>
						<i id="iconfont" class="iconfont" style="font-size: 25px !important; position: absolute; top: 5px; left: 30px;"></i>
					</label>
				</div>	
			</div>
			<div class="hp-form-item" style="display: none" id="icon_div">
				<c:forEach items="${iconfonts }" var="iconfont" >
					<span style="height: 25px;display: inline-block; cursor:pointer" class="icon_span">
						<input type="radio" id="productTypeIcon" name="productTypeIcon" value="${iconfont}" style="cursor:pointer">
						<i class="iconfont ${iconfont}" style="font-size: 25px !important"></i>
					</span>
				</c:forEach>
			</div>
			<div class="hp-form-item">
				<button class="bt_save">保存</button>
				<button class="bt_close">关闭</button>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		$(function(){
			$('.bt_close').on('click', function(){
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
				return false;
			});

			$('.icon_span').on('click', function (){
				$(this).children("input").prop("checked", true);
				var iconfont = $('input[name="productTypeIcon"]:checked').val();
				$('#iconfont').attr("class", "iconfont " + iconfont);
				$('.icon_span').css("background-color", "");
				$(this).css("background-color", "#4CAF50");
				$('#icon_div').hide();
			});

			$('#icon_input').on('click', function (){
				$('#icon_div').show();
			});

			$(".bt_save").click(function () {
				if ($("#productTypeName").val().trim() === "") {
					layer.msg("添加失败：商品分类名称不能为空", {icon: 2});
					// return false;
				} else if ($('input[name="productTypeIcon"]:checked').val() === undefined){
					layer.msg("添加失败：商品分类图标不能为空", {icon: 2});
				} else {
					$.post("/admin/productType/add",$(".hp-form").serialize(),function (e) {
						if (e === "ok") {
							$('.hp-context',parent.document).load("${ctx}/admin/productType/list?pageNum=" + ${productTypePages.pageNum});
							parent.layer.msg("添加成功", {icon: 1});
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.close(index); //再执行关闭
						} else {
							layer.msg("添加失败：" + e, {icon: 2});
						}
					});
				}
				return false;
			});

		})
	</script>
</body>
</html>