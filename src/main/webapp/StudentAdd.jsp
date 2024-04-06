<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

        <h1 style="text-align: center;">Nhập thông tin sinh viên cần thêm</h1>
        <form action="C_student.php?mode=2" method="post">
            <label for="id">ID</label>
            <input type="text" name="id">
            <label for="name">Name</label>
            <input type="text" name="name">
            <label for="age">Age</label>
            <input type="text" name="age">
            <label for="university">University</label>
            <input type="text" name="university">
            
            <input type="submit" value="Insert">
        </form>


</body>
</html>