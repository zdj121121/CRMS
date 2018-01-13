<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>hello</title>
</head>
<body>
<h2>Hello ${name}!</h2>
<form action="/userController/login" method="post">
	<input name="name" >
	<button type="submit">提交</button>
</form>
</body>
</html>
