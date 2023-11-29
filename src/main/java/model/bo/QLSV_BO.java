package model.bo;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Student;
import model.dao.QLSV_DAO;

public class QLSV_BO {
	private QLSV_DAO qlsv_DAO;
	public QLSV_BO() throws ClassNotFoundException, SQLException {
		qlsv_DAO = new QLSV_DAO();
	}
	public ArrayList<Student> getStudentList() throws SQLException{
		return qlsv_DAO.getStudentList();
	}
	
}
