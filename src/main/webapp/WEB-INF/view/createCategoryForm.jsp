<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="CategoryMenu1.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Category</title>
</head>
<body>
<jsp:include page="AdminHome.jsp"></jsp:include>

	 <c:url value="/manage_category_create" var="url"></c:url>

	<form:form method="post" action="${url}" commandName="createCategoryObj" >
		<table>
			<tr>
				<td><form:label path="id">Category Id</form:label></td>
				<td><form:input path="id" disabled="false" /> <form:hidden
						path="id" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Category Name</form:label></td>
				<td><form:input path="name"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="description">Category Description</form:label></td>
				<td><form:input path="description"></form:input></td>
			</tr>


			<tr>
				<td colspan="2"><input type="submit" class="btn btn-primary" value="Create"></td>
			</tr>
		</table>
	</form:form> 
	<%-- <form action="manage_category_create" method ="post">
	<input type ="text" name="id" placeholder="Id">
	<input type ="text" name="name" placeholder="Name">
	<input type ="text" name="description" placeholder="Description">
	<input type="submit" value="Create">
	</form> --%>
	
	<jsp:include page="AboutUs.jsp"></jsp:include>

</body>
</html>