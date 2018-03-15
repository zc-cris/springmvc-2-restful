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


	<a href="testSimpleMappingExceptionResolver?i=1">test SimpleMappingExceptionResolver</a>	

	<br><br>
	<a href="testDefaultHandlerExceptionResolver">test DefaultHandlerExceptionResolver</a>
	
	<br><br>
	<a href="testNotFindUserNameAndPasswordException?i=9">test NotFindUserNameAndPasswordException</a>
	
	<br><br>
	<a href="testExceptionHandler?i=10">test ExceptionHandler</a>

	<!-- 实现文件上传，文件的下载使用 HttpMessageConverter 即可 -->
	<form action="${pageContext.request.contextPath }/testFileUpload" method="post" enctype="multipart/form-data">
		上传文件：<input type="file" name="file">
		文件描述：<input type="text" name="desc">
		<input type="submit" value="Submit">
	</form>

	<!-- 
		关于springMVC 如何实现国际化的？
		1. 在页面上可以根据浏览器语言设置的情况对固定的文本内容，时间，数值进行本地化处理
		2. 可以在bean 中获取国际化资源文件 Locale对应的消息
		3. 可以通过超链接切换 Locale ，而不在依赖浏览器的语言设置情况
		
		解决：
		1. 使用 JSTL 的fmt标签
		2. 在bean 中注入 ResourceBundleMessageSource 的实例，使用其对应的 getMessage 方法即可
		3. 配置 LocaleResolver 和 LocaleChangeInterceptor
	 -->
	<a href="i18n">i18n jsp</a>
	
	<br><br>
	<a href="testResponseEntity">实现文件下载</a>
	<br><br>
	
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