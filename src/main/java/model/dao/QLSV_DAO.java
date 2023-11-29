package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Student;

public class QLSV_DAO {
	private Connection connection;

    public QLSV_DAO() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/DULIEU";
        this.connection = DriverManager.getConnection(url);
    }

    public ArrayList<Student> getStudentList() throws SQLException {
        String sql = "SELECT * FROM sinhvien";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            Integer age = resultSet.getInt("age");
            String university = resultSet.getString("university");

            Student student = new Student(id, name, age, university);
            students.add(student);
        }

        statement.close();
        resultSet.close();

        return students;
    }

    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, age, university) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getAge());
        preparedStatement.setString(3, student.getUniversity());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, age = ?, university = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getAge());
        preparedStatement.setString(3, student.getUniversity());
        preparedStatement.setString(4, student.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteStudent(String id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, id);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ArrayList<Student> searchStudents(String searchTerm) throws SQLException {
        String sql = "SELECT * FROM students WHERE name LIKE ? OR age = ? OR university LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        searchTerm = "%" + searchTerm + "%";
        preparedStatement.setString(1, searchTerm);
        preparedStatement.setInt(2, Integer.parseInt(searchTerm));
        preparedStatement.setString(3, searchTerm);

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            Integer age = resultSet.getInt("age");
            String university = resultSet.getString("university");

            Student student = new Student(id, name, age, university);
            students.add(student);
        }

        preparedStatement.close();
        resultSet.close();

        return students;
    }
}
