<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chu</title>
</head>
<body>

	<h1 style="text-align: center">Quản lý Thông tin Sinh Viên - MVC - JSP</h1>
	<ol>
		<p>
		<b>Chức năng:</b>
		</p>
		<li><a href="controller/studentController?action=view">Xem thông tin sinh viên</a></li>
		<li><a href="controller/studentController?action=add">Chèn thông tin sinh viên</a></li>
		<li><a href="controller/studentController?action=update">Cập nhật thông tin sinh viên</a></li>
		<li><a href="controller/studentController?action=search">Tìm kiếm thông tin sinh viên</a></li>
		<li><a href="controller/studentController?action=delete">Xóa thông tin sinh viên(một/nhiều)</a></li>
	</ol>

</body>
</html>