<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>添加商品品牌</title>
<script type="text/javascript">
	$(function(){
		$('.bt_close').on('click', function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
			return false;
		});
		
		$('.bt-upload').on('click', function(){
			var file_form = $('<form method="post" enctype="multipart/form-data"></form>');
			var file_bt = '<input style="display: none" type="file" name="file">';
			if ($(this).next().length<=0) {
				$(file_form).append(file_bt);
				$($(this)).after(file_form);
			}
			$(this).next().children("input").click();
			return false;
		});
		
		$('body').on('change', 'input[type=file]', function(){
			$.ajax({
	            url: '${ctx}/common/upload',
	            type: 'POST',
	            processData: false,
	            contentType: false,
	            data: new FormData($(this).parent()[0]),
	            success : function(e) {
                    $("#img").val(e);
                    $("#brandImg").attr("src", "/common/getImage?image="+e);
	            }
	        });
		});

        $(".bt_save").on('click', function () {
            if ($("[name='brandName']").val().trim() === "") {
                layer.msg("添加失败：品牌名称不能为空", {icon: 2});
            } else {
                $.post("/admin/brand/add",$("#brand-from").serialize(),function (e) {
                    if (e === "ok") {
                        $('.hp-context',parent.document).load("${ctx}/admin/brand/list?pageNum=" + ${BrandPages.pageNum});
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
		<form class="hp-form" id="brand-from">
            <input type="hidden" name="brandImg" id="img">
			<div class="hp-form-item">
				<label class="hp-form-label">品牌名称</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="brandName">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">所属分类</label>
				<div class="hp-input-block">
					<select class="hp-input" name="brandType">
						<c:forEach items="${productTypes}" var="productType">
							<option value="${productType.id }">${productType.productTypeName }</option>
						</c:forEach>
					</select>
				</div>	
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">品牌图片</label>
				<div class="hp-input-block">
					<button style="margin-left: 10px;" class="bt-upload">上传</button>
				</div>
			</div>
			<div class="hp-form-item">
				<img alt="" src="" id="brandImg" width="200px">
			</div>
			<div class="hp-form-item">
				<button class="bt_save">保存</button>
				<button class="bt_close">关闭</button>
			</div>
		</form>
	</div>
</body>
</html>