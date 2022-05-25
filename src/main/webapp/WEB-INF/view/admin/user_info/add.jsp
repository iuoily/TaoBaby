<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>添加用户</title>
<script type="text/javascript">
	$(function(){
		$('.bt_close').on('click', function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
			return false;
		});

		$(".bt_save").click(function () {
			if ($("[name='userName']").val().trim() === "") {
				layer.msg("添加失败：用户名不能为空", {icon: 2});
				// return false;
			} else if ($("[name='password']").val().trim() === ""){
				layer.msg("添加失败：用户密码不能为空", {icon: 2});
			} else {
				$.post("/admin/user/add",$(".hp-form").serialize(),function (e) {
					if (e === "ok") {
						$('.hp-context',parent.document).load("${ctx}/admin/user/list?pageNum=" + ${userPages.pageNum});
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
</head>
<body>
	<div class="hp-context-page">
		<form class="hp-form">
			<input type="hidden" name="type" value="0">
			<div class="hp-form-item">
				<label class="hp-form-label">用户名称</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="userName">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">用户密码</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="password">
				</div>	
			</div>
			<div class="hp-form-item">
				<button class="bt_save">保存</button>
				<button class="bt_close">关闭</button>
			</div>
		</form>
	</div>
</body>
</html>