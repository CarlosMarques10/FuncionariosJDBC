package model.dao;

import db.DB;
import model.dao.implement.EmployeeDao;

public class DaoFactory {
	
	public static EmployeeDao createEmployeeDao() {
		return new EmployeeDao(DB.getConnection());
	}

}
