<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!-- c:out ; c:foreach; c:if -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<!-- formatting things like dates -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Insert title here</title>
	<link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
	<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>

<body>
<a href="/">Home</a>
	<div class="container">
		<h1>Welcome ${loggedinuser.firstName} ${loggedinuser.lastName}!</h1><br><br>

		<h3>Ideas</h3>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Ideas</th>
					<th scope="col">Created By</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allideas}" var="idea">
					<tr>
						<td><a href="/ideas/show/${idea.id}">${idea.name}</a></td>
						<td>${idea.creator.firstName} ${idea.creator.lastName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a class="btn btn-info" href="ideas/new">Create New Idea</a>
	</div>
</body>

</html>