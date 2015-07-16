<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Debt</h1>
		<form:form action="saveDebt" method="post" modelAttribute="debt">
		<table>
			<tr>
				<td>Debt Name:</td>
				<td><form:input path="debtName" />
				<input value="${userName}"/>
			</tr>
			<tr>
				<td>Payment:</td>
				<td><form:input path="payment" /></td>
			</tr>
			<tr>
				<td>Rate:</td>
				<td><form:input path="rate" /></td>
			</tr>
			<tr>
				<td>Balance:</td>
				<td><form:input path="balance" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>