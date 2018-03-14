<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

	<!-- 
		自定义类型转换器进行数据类型的转换：
		克里斯-cris.@qq.com-23-1-1  前台输入这样的字符串，后台接收后直接转换为对应的 Employee对象并保存到数据库
	 -->
	<form:form action="${pageContext.request.contextPath }/testConversion" method="post">
		Employee:<input type="text" name="employee">
		<br>
		<input type="submit" value="提交">
	</form:form>

	<br>
	

	<!-- 
		1. 为什么要使用form标签？
		可以更加快捷的开发出表单元素，而且可以很方便的进行页面数据回显
		2. 需要注意的是：如果要在jsp页面通过springMVC 表单来显示对应对象的值，得在后台控制器指定绑定的
			模型属性，然后在 form标签通过modelAttribu属性来进行映射 若没有指定该属性，那么默认从request 域对象中读取command的表单bean，如果该属性也不存在，那么会抛出异常
	 -->

	<form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee">
		<!-- path属性对应html标签的name属性 -->
		名字：<form:input path="name"/>
		<form:errors path="name"></form:errors>
		<br>
		
		<!-- 要求：不让员工修改邮箱信息 -->
		<!-- 新增用户请求可以添加邮箱信息 -->
		<c:if test="${employee.id == null }">
		邮箱：<form:input path="email"/>
		<form:errors path="email"></form:errors>
		<br>
		</c:if>
		
		<!-- 修改用户请求不仅不能修改邮箱信息，还需要将post 请求转换为 put类型 -->
		<c:if test="${employee.id != null }">
		
			<form:hidden path="id" />
			<input type="hidden" name="_method" value="PUT">		
			
			<!-- 对于 _method 不能使用 from：hidden 标签，因为 modelAttribute 对应的bean中没有_method 这个属性，如果强行加上会报错 -->
			
		</c:if>
		
		
		年龄：<form:input path="age"/>
		<br>
		
		<%
			Map<String, Object> genders = new HashMap<>();
			genders.put("0", "女");
			genders.put("1", "男");
			request.setAttribute("genders", genders);
		%>
		<!-- 自动根据map的键值对在页面上显示，value是键，显示的是值 -->
		性别：<br>
		<form:radiobuttons path="gender" items="${requestScope.genders }" delimiter="<br> "/>
		<br>
		
		部门：<form:select path="department.id" items="${requestScope.depts }" itemLabel="name" itemValue="id"></form:select>
		<br>
		
		<!-- 
			1. 数据类型转换
			2. 数据类型格式化
			3. 数据校验
				- 如何进行数据校验？ 使用注解的方式
				- 通过 JSR 3.0 验证标准
				- 加入 hibernate validator 验证框架的jar包
				- springMVC 的配置文件上添加 <mvc:annotation-driven />
				- 在javaBean 的属性上添加对应的注解
				- 在目标方法 bean类型入参的前面添加 @Valid 注解
			4. 校验失败转向哪个页面？
				- 注意：需要校验的bean 对象和其绑定结果对象或者错误对象时成对出现，他们之间顺序固定，不能插入其他入参
			5. 错误消息显示在前台，并且进行错误消息的国际化
			
		 -->
		生日：<form:input path="birth"/>
		<form:errors path="birth"></form:errors>
		<br>
		薪水：<form:input path="salary"/>
		<br>
		
		<input type="submit" value="添加">	
	</form:form>


</body>
</html>
