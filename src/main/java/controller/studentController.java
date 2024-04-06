package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Student;
import model.bo.QLSV_BO;
import model.dao.QLSV_DAO;

@WebServlet("/")
public class studentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getServletPath();
        try {
            switch (action) {
        	case "/view":
				handleViewStudent(request, response);
            case "/add":
                handleAddStudent(request, response);
                break;
            case "/update":
                handleUpdateStudent(request, response);
                break;
            case "/delete":
                handleDeleteStudent(request, response);
                break;
            default:
                sendError(request, response, "Invalid action: " + action);
            }
		} catch (ClassNotFoundException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private void handleViewStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, ClassNotFoundException, IOException{
		QLSV_BO qlsv_BO = new QLSV_BO();
		ArrayList<Student> studentArray = null;
		studentArray = qlsv_BO.getStudentList();
		request.setAttribute("studentArray", studentArray);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentList.jsp");
        dispatcher.forward(request, response);
	}
	
    private void handleAddStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
    	String id = request.getParameter("id");
    	if (id != null) {
        	String name = request.getParameter("name");
            Integer age = Integer.parseInt(request.getParameter("age"));
            String university = request.getParameter("university");
            Student student = new Student(id, name, age, university);
            QLSV_DAO qlsv_DAO = new QLSV_DAO();

            try {
                qlsv_DAO.insertStudent(student);
                request.setAttribute("message", "Student added successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error adding student: " + e.getMessage());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentList.jsp");
            dispatcher.forward(request, response);
    	} else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentAdd.jsp");
            dispatcher.forward(request, response);
    	}

    }

    private void handleUpdateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));
        String university = request.getParameter("university");

        Student student = new Student(id, name, age, university);
        QLSV_DAO QLSV_DAO = new QLSV_DAO();

        try {
            QLSV_DAO.updateStudent(student);
            request.setAttribute("message", "Student updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating student: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
        dispatcher.forward(request, response);
    }

    private void handleDeleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String id =request.getParameter("id");

        QLSV_DAO qlsv_DAO = new QLSV_DAO();

        try {
            qlsv_DAO.deleteStudent(id);
            request.setAttribute("message", "Student deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error deleting student: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
        dispatcher.forward(request, response);
    }

    private void sendError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("error", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
        dispatcher.forward(request, response);
    }
}