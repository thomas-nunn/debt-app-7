<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Debt</title>
</head>
<body>
	<div align="center">
		<h1>Edit Debt</h1>
		<form:form action="saveDebt" method="post" modelAttribute="debt">
		<table>
			<tr>
				<td>Debt Name:</td>
				<td><form:input path="debtName" value="${ debtname }"/>
			</tr>
			<tr>
				<td>Payment:</td>
				<td><form:input path="payment" value="${ payment }"/></td>
			</tr>
			<tr>
				<td>Rate:</td>
				<td><form:input path="rate" value="${ rate }"/></td>
			</tr>
			<tr>
				<td>Balance:</td>
				<td><form:input path="balance" value="${ balance }"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>