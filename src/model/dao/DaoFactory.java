package model.dao;

import db.DB;
import model.dao.implement.DepartmentDao;
import model.dao.implement.EmployeeDao;

public class DaoFactory {
	
	public static EmployeeDao createEmployeeDao() {
		return new EmployeeDao(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDao(DB.getConnection());
	}

}
