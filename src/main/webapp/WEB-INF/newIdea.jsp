<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- c:out ; c:foreach; c:if -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<!-- formatting things like dates -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<a href="/ideas">Back To Ideas</a>

	<div class="container">
		<h1>Create A New Idea</h1>
		<form:form action="/ideas/create" method="post" modelAttribute="idea" class="form-group">
			<p>
				<form:label path="name">Content</form:label>
				<form:errors path="name" class="text-danger"/>
				<form:input path="name" class="form-control col-3" placeholder="An Idea"/>
			</p>
			<input class="btn btn-secondary" type="submit" value="Create"/>
		</form:form> 

	</div>
</body>
</html>