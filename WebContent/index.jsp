<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="sripts/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function () {
		$("#testJson").click(function () {
			alert("fafa");
			var url = this.href;
			var args = {};
			$.post(url, args, function(data){
				for(var i=0;i<data.length;i++){
					var name = data[i].name;
					var id = data[i].id;
					alert("id="+id+",name="+name);
				}
			})
			return false;
		});
	})
	
</script>

<body>

	<a href="testResponseEntity">模拟文件下载</a>
	
	<!-- 模拟文件上传 -->
	<form action="${pageContext.request.contextPath }/testHttpMessageConverter" method="post" enctype="multipart/form-data">
		上传文件：<input type="file" name="file">
		文件描述：<input type="text" name="desc">
		<input type="submit" value="Submit">
	</form>


	<a href="list">显示所有员工信息</a>
	
	<a href="testJson" id="testJson">test Json</a>

</body>
</html>