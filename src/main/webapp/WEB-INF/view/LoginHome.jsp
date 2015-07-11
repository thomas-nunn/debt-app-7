<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log In</title>
</head>
<body>
	<div align="center">
		<h1>Login</h1>
		<form:form action="login" method="post" modelAttribute="user">
		<table>
			<tr>
				<td>User Name:</td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="userPassword" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Login"></td>
			</tr>
		</table>
		</form:form>
	</div>
	<div align="center">
		<h1><a href="register">Register</a></h1>
	</div>
</body>
</html>