<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/static/common/common.jspf" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
		<title>爱购登录页面</title>
		<link rel="stylesheet" href="${ctx }/static/css/reset.css" />
		<link rel="stylesheet" href="${ctx }/static/css/footer.css" />
		<link rel="stylesheet" href="${ctx }/static/css/login.css" />
	</head>
	<body>
		<!--登录/注册的头部-->
		<div class="lr-top w1230">
			<a href="index"><img src="${ctx }/static/img/logo.png" width="150px" height="60px"/></a>
			<div class="top-link">
				<a href="javascript:;;" class="top-link1"></a>
				<a href="javascript:;;" class="top-link2"></a>
				<a href="javascript:;;" class="top-link3"></a>
			</div>
		</div>
		<!--登录/注册的中部-->
		<div class="lr-main">
			<div class="w1230">
				<div class="reg-div">
					<h3 class="login-title">登录淘宝宝</h3>
					<p class="go-reg">还没有淘宝宝账号？<a href="${ctx}/front/register.page">在此注册</a></p>
					<form action="" method="post">
						<p><span class="icon-account"></span><input type="text" name="username" placeholder="请输入已注册的账号"/></p>
						<p><span class="icon-pwd"></span><input type="password" name="password" placeholder="请输入密码"/></p>
						<p class="clear-float"><input type="text" name="code" placeholder="验证码" class="code"/><img src="${ctx}/common/getImage?image=code.jpg" width="110px" height="42px" class="code-img"/></p>
						<p><input type="checkbox" name="autoLogin" class="chk-login"/><span>7天免登录</span></p>
						<input type="submit" value="登录" class="sum-btn" />
					</form>
				</div>
			</div>	
		</div>
		<!--登录/注册的底部-->
		<div class="lr-footer footer">
			<p class="w1230">
				<a href="javascript:;;">关于淘宝宝</a>
				<a href="javascript:;;">合作伙伴</a>
				<a href="javascript:;;">营销中心</a>
				<a href="javascript:;;">廉正举报</a>
				<a href="javascript:;;">联系客服</a>
				<a href="javascript:;;">开发平台</a>
				<a href="javascript:;;">诚征英才</a>
				<a href="javascript:;;">联系我们</a>
				<a href="javascript:;;">网站地图</a>
				<a href="javascript:;;">知识产权</a><span>|</span>
				<span>&copy;2018-2020 igo.com 版权所有</span>
			</p>
		</div>
		<input type="hidden" class="to_index" value="${ctx}/front/index">
		<script src="${ctx}/static/plugs/jquery.js"></script>
		<script>
			$(function (){
				//验证码点击事件，点击更换验证码图片
				$('.code-img').on('click', function (){
					$.post('${ctx}/common/getVerificationCode', function(e){
						if (e!==null) {
							// 自动填充验证码
							$('[name="code"]').val(e);
							$('.code-img').attr("src", "${ctx}/common/getImage?image=code.jpg&" + Math.random());
						}else {
							layer.msg("网络故障，请重试！msg:" + e, {icon:2});
						}
					})
				})
				
				//登录事件
				$('form').on('submit', function (){
					if ($("[name='username']").val().trim()==="") {
						layer.msg("账号不能为空！", {icon:2});
						return false;
					}
					if ($("[name='password']").val().trim()==="") {
						layer.msg("密码不能为空！", {icon:2});
						return false;
					}
					if ($("[name='code']").val().trim()==="") {
						layer.msg("请输入验证码！", {icon:2});
						return false;
					}
					var data = $(this).serialize();
					$.post('${ctx}/front/user/login', data,  function(e){
						if (e === "ok") {
							layer.msg("登录成功", {icon:1});
							setTimeout(function () {
								window.location.href = $('.to_index').val();
							},1500);
						}else {
							layer.msg("登录失败：" + e, {icon:2});
						}
					});
					return false;
				});

				$('.code-img').trigger('click')
			})
		</script>
	</body>
</html>
