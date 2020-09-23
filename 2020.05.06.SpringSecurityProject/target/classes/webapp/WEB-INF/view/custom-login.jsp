<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Please sign in</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous">
<link
	href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css"
	rel="stylesheet" crossorigin="anonymous" />
<style>
 .error{
   color:red;
 }	

</style>
</head>
<body>
	<div class="container">
		<f:form class="form-signin" method="post" action="${pageContext.request.contextPath}/authenticateTheUser">
			<h2 class="form-signin-heading">Lütfen Bağlanın</h2>
			<p>
				<label for="username" class="sr-only">Kulanici</label> <input
					type="text" id="username" name="username" class="form-control"
					placeholder="Username" required autofocus>
			</p>
			<p>
				<label for="password" class="sr-only">Sifre</label> <input
					type="password" id="password" name="password" class="form-control"
					placeholder="Password" required>
			</p>
			
			<c:if test="${param.error != null}"><!-- parametrenin erroru null degilse bu degere check et -->
			<b class="error">Hatalı kullanıcı adi/sifre girdiniz!</b>
			</c:if>
			<c:if test="${param.logout != null}"><!-- parametrenin erroru null degilse bu degere check et -->
			<b class="error">Sistemden çıkış yaptınız!</b>
			</c:if>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</f:form>
	</div>
</body>
</html>