<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task 1 :: Servlet with JSP</title>
</head>
<body>
	Hello, I am JSP-2!
	<br /> Parameters:
	<%
	request.getParameterMap().forEach((k, v) -> {
		try {
			response.getOutputStream().println(k + " : " + v[0] + "<br>");
		} catch (Exception e) {
		}
	});
%>
</body>
</html>