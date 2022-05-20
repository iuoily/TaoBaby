<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>修改用户</title>
<script type="text/javascript">
	$(function(){
		$('.bt_close').on('click', function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
			return false;
		});

		$(".bt_save").on('click', function () {
			if ($("[name='username']").val().trim() === "") {
				layer.msg("修改失败：用户名不能为空", {icon: 2});
			} else {
				$.post("/admin/user/update", $(".hp-form").serialize(), function (e) {
					if (e === "ok") {
						$('.hp-context',parent.document).load("${ctx}/admin/user/list?pageNum=" + ${userPages.pageNum});
						parent.layer.msg("修改成功", {icon: 1});
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					} else {
						layer.msg("修改失败：" + e, {icon: 2});
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
			<input type="hidden" name="id" value="${user.id }">
			<input type="hidden" name="type" value="${user.type }">
			<div class="hp-form-item">
				<label class="hp-form-label">用户名称</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="username" value="${user.username }">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">用户密码</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="password" value="123456">
				</div>	
			</div>
			<div class="hp-form-item" style="color: red">
				*此处为默认密码，不代表账号【${user.username }】的当前密码，修改时会覆盖原密码，请谨慎操作！！！
			</div>
			<div class="hp-form-item">
				<button class="bt_save">保存</button>
				<button class="bt_close">关闭</button>
			</div>
		</form>
	</div>
</body>
</html>