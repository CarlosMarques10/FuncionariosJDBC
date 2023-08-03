package model.dao;

import db.DB;
import model.dao.implement.EmployeeDaoJDBC;

public class DaoFactory {
	
	public static EmployeeDaoJDBC createEmployeeDao() {
		return new EmployeeDaoJDBC(DB.getConnection());
	}

}
