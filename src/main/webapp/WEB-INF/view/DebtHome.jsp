<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Debt Repayment Home</title>
    </head>
    <body>
    	<div align="center">
	        <h1>${userName}'s Debt List</h1>
	        <h3>
				<form:form action="DebtForm" method="post" modelAttribute="user">
					<form:hidden path="userName" />
					<form:hidden path="userPassword" />
					<input type="submit" value="New Debt">
				</form:form>		
	       	</h3>
	        <table border="1">
	        	<th>No</th>
	        	<th>Debt Name</th>
	        	<th>Monthly Payment</th>
	        	<th>Interest Rate</th>
	        	<th>Balance</th>
	        	<th>Action</th>
	        	
				<c:forEach var="debt" items="${listDebts}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${debt.debtName}</td>
					<td>${debt.payment}</td>
					<td>${debt.rate}</td>
					<td>${debt.balance}</td>
					<td>
						<a href="editDebt?debtName=${debt.debtName}&payment=${debt.payment}&rate=${debt.rate}&balance=${debt.balance}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteDebt?debtName=${debt.debtName}">Delete</a>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>
