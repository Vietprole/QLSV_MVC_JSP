<%@page import="model.bean.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xem thong tin sinh vien</title>
</head>
<body>
	<table>
	<%
		ArrayList<Student> studentArray = (ArrayList<Student>)request.getAttribute("studentArray");
		for (int i = 0; i < studentArray.size(); i++){
	%>
			<tr>
				<td><%= studentArray.get(i).getId() %></td>
				<td><%= studentArray.get(i).getName() %></td>
				<td><%= studentArray.get(i).getAge() %></td>
				<td><%= studentArray.get(i).getUniversity() %></td>
			</tr>	
	<%}%>
	</table>
</body>
</html>