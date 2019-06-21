<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task 1 :: MVC</title>
</head>
<body>
	<h1>Hello, I am MVC</h1>
	<div>
		<%
			java.util.List<String> all = (java.util.List) request.getAttribute("all");
			if (all != null) {
				for (String line : all) {
					try {
						out.println(line + "<br>");
					} catch (Exception e) {

					}
				}
			}
		%>
	</div>
	<div>
		<h2>Create</h2>
		<form action="/task1/crud" method="post">
			<label for="data">Data:</label>&nbsp;<input type="text" name="data"
				id="data"> <br> <br> <input type="submit">
		</form>
	</div>
	<div>
		<h2>Read</h2>
		<form action="/task1/crud" method="get">
			<label for="id">Id:</label>&nbsp;<input type="text" name="id" id="id">
			<br> <br> <input type="submit">
		</form>
	</div>
	<div>
		<h2>Update</h2>
		<form action="/task1/crud" method="post">
			<input type="hidden" name="_method" value="PUT" /> <label for="id2">Id:</label>&nbsp;<input
				type="text" name="id" id="id2"> <br> <label
				for="newData">New value:</label>&nbsp;<input type="text"
				name="newData" id="newData"> <br> <br> <input
				type="submit">
		</form>
	</div>
	<div>
		<h2>Delete</h2>
		<form action="/task1/crud" method="post">
			<input type="hidden" name="_method" value="delete" /> <label
				for="id3">Id:</label>&nbsp;<input type="text" name="id" id="id3">
			<br> <input type="submit">
		</form>
	</div>
</body>
</html>