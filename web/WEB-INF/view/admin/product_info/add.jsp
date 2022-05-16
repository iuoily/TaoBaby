<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>添加商品</title>
<script type="text/javascript">
	$(function(){
		$('.bt_close').on('click', function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
			return false;
		})
		
		$('.bt-upload').on('click', function(){
			var file_form = $('<form method="post" enctype="multipart/form-data"></form>');
			var file_bt = '<input style="display: none" type="file" name="file">';
			if ($(this).next().length<=0) {
				$(file_form).append(file_bt);
				$($(this)).after(file_form);
			}
			$(this).next().children("input").click();
			return false;
		})
		
		$('body').on('change', 'input[type=file]', function(e){
			var bt_name = $($(this).parent().prev()).attr("data-name");
			$('#temp').val(bt_name);
			$.ajax({
	            url: '${ctx}/common/upload',
	            type: 'POST',
	            cache: false,
	            data: new FormData($(this).parent()[0]),
	            processData: false,
	            contentType: false,
	            dataType : "json",
	            beforeSend: function(){
	                uploading = true;
	            },
	            success : function(data) {
	            	if (data.result) {
	            		console.log(data);
	            		var bn = $('#temp').val();
	            		if (bn=="productDesc") {
	            			var v = $('input[name="'+bn+'"]').val();
	            			$('input[name="'+bn+'"]').remove();
	            			$('#'+bn).append('<img alt="" style="padding-right: 10px;" src="'+"${ctx}/common/getImage?image=" + data.data.fileName+'" width="50px">');
	            			if (v!="" && v!=undefined) {
	            				v = v + ",";
	            			}else {
	            				v = "";
	            			}
	            			v = v + data.data.fileName;
	            			$('#product-from').prepend("<input type='hidden' name='"+bn+"' value='"+v+"'>")
	            		}else {
	            			$('input[name="'+bn+'"]').remove();
		            		$('#product-from').prepend("<input type='hidden' name='"+bn+"' value='"+data.data.fileName+"'>")
		            		$('#'+bn).attr("src", "${ctx}/common/getImage?image=" + data.data.fileName);
	            		}
	            	}
	            }
	        });
		})
		
		$('select[name="productType"]').on('change', function(){
			var productTypeId = $(this).val();
			$.post(basePath + '/admin/product/getBrandByProductType',{"productTypeId" : productTypeId}, function(e){
				var brands = e.data;
				$('select[name="productBrand"]').empty();
				for (var i=0; i<brands.length; i++) {
					if (i==0) {
						$('select[name="productBrand"]').append('<option selected="selected" value="'+brands[i].id+'">'+brands[i].brandName+'</option>');
					}else {
						$('select[name="productBrand"]').append('<option value="'+brands[i].id+'">'+brands[i].brandName+'</option>');
					}
				}
			})
		})
	})
</script>
</head>
<body>
	<div class="hp-context-page">
		<input type="hidden" id="temp">
		<form action="${ctx}/admin/product/add" class="hp-form" id="product-from">
			<div class="hp-form-item">
				<label class="hp-form-label">商品名称</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="productName">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">商品图片</label>
				<div class="hp-input-block">
					<button style="margin-left: 10px;" class="bt-upload" data-name="productImage">上传</button>
				</div>	
			</div>
			<div class="hp-form-item">
				<img alt="" src="" id="productImage" width="200px">
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">商品价格</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="price">
				</div>	
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">商品详情图</label>
				<div class="hp-input-block">
					<button style="margin-left: 10px;" class="bt-upload" data-name="productDesc">上传</button>
				</div>
			</div>
			<div class="hp-form-item" id="productDesc">
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">所属分类</label>
				<div class="hp-input-block">
					<select class="hp-input" name="productType">
						<c:forEach items="${productTypes}" var="productType">
							<option value="${productType.id }">${productType.productTypeName }</option>
						</c:forEach>
					</select>
				</div>	
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">商品品牌</label>
				<div class="hp-input-block">
					<select class="hp-input" name="productBrand">
					</select>
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