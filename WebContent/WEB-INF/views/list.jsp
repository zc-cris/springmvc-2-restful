<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="sripts/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".delete").click(function() {
			var href = $(this).attr("href");
			//将超连接的url地址属性作为 form 表单的action属性值
			$("form").attr("action", href).submit();
			return false;
		});
	})
</script>

</head>
<body>
	<!-- 删除用户的时候传递到后台，将post请求转换为delete类型的请求,也就是说，经过jquery的处理后，用户点击超连接实际上提交到后台的是一个 form表单 -->
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE">
	</form>

	<c:if test="${empty requestScope.emps }">
		用户列表里面没有用户了
	</c:if>
	<c:if test="${!empty requestScope.emps }">
		<table align="center" cellpadding="10" cellspacing="0" border="1">
			<tr>
				<th>员工id</th>
				<th>员工名</th>
				<th>邮箱</th>
				<th>性别</th>
				<th>年龄</th>
				<th>部门id</th>
				<th>修改</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${requestScope.emps }" var="emp">
				<tr align="center">
					<td>${emp.id }</td>
					<td>${emp.name }</td>
					<td>${emp.email }</td>
					<td>${emp.gender eq '0' ? '女' : '男' }</td>
					<td>${emp.age }</td>
					<td>${emp.department.id }</td>
					<td><a href="${pageContext.request.contextPath }/emp/${emp.id}">修改</a></td>
					<td><a class="delete" href="${pageContext.request.contextPath }/emp/${emp.id }">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<a href="emp">新增一个员工</a>
</body>
</html>