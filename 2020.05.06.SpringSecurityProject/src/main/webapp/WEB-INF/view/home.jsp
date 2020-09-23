<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsp/jstl/core">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	Kullanici:
	<sec:authentication property="principal.username" />
	, Role:
	<sec:authentication property="principal.authorities" />

	<hr />
	Anasayfa,hoşgeldiniz
	<br />

	<a href="google.com">Link</a>
	<f:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</f:form>
	<hr />

	<sec:authorize access="hasRole('ADMIN')">
		<a href="admin/home">Admin Anasayfa</a>
	</sec:authorize>

	<sec:authorize access="hasAnyRole('PERSONEL','ADMIN')">
		<a href="personel/home">Personel Anasayfa</a>
	</sec:authorize>

</body>
</html>