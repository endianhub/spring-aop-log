<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>LOG</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="static/jquery-1.7.2.min.js"></script>
</head>

<body>
	<form action="saveRequest" method="post">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td>Sex:</td>
				<td><input type="text" name="sex" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><input type="text" name="age" /></td>
			</tr>
		</table>
		
		<input type="submit" value="提交"/>
	</form>


	<table border="1" cellspacing="0">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Password</th>
			<th>Age</th>
			<th>Sex</th>
			<th>Menu</th>
		</tr>
		<c:forEach var="u" items="${user }">
			<tr>
				<td>${u.id }</td>
				<td>${u.name }</td>
				<td>${u.password }</td>
				<td>4</td>
				<td>5</td>
				<td><button onclick="updateUser(${u.id })">更新</button>
					<button onclick="deleteUser(${u.id })">删除</button></td>
			</tr>
		</c:forEach>
	</table>
	
	<script type="text/javascript">
		function deleteUser(id){
			var action = "delete";
			$.post(action, {paramId:id}, function(data){
				
			});
		}
	</script>
</body>
</html>
