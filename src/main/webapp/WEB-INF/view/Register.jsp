<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register a New Account</title>
</head>
<body>
	<div align="center">
		<h1>Create a new account</h1>
		<form:form action="createUser" method="post" modelAttribute="user">
		<table>
			<tr>
				<td>User Name:</td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="userEmail" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="userPassword" /></td>
			</tr>
			<tr>
				<td>Verify Password:</td>
				<td><form:input path="passwordVerify" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Register"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>