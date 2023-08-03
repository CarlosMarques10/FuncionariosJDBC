package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.excpetions.DbException;
import model.dao.GenericDao;
import model.entities.Department;
import model.entities.Employee;

public class EmployeeDao implements GenericDao<Employee>{

	private Connection conn;
	
	public EmployeeDao(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void inserir(Employee obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO employees (name,tel,birthDate,salary,id_department) VALUES (?,?,?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getTel());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void atualizar(Employee obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE employees SET name = ?, tel = ?, birthDate = ?, salary = ?, id_department = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getTel());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deletarPeloId(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM employees WHERE id = ?");
			
			st.setInt(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Employee procurarPeloId(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = instanciaDepartment(rs);
				Employee emp = instanciaEmployee(rs, dep);
				return emp;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(null);
		}
	}

	@Override
	public List<Employee> procurarTudo() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM employees");
			
			rs = st.executeQuery();
			
			List<Employee> list = new ArrayList<Employee>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("id"));
				if(dep == null) {
					dep = instanciaDepartment(rs);
					map.put(rs.getInt("id"), dep);
				}
				Employee emp = instanciaEmployee(rs, dep);
				list.add(emp);
			}
			return list;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Employee> procurarPeloDepartamento(Department department) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM employees WHERE id_department = ?");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Employee> list = new ArrayList<Employee>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("id_department"));
				if (dep == null) {
					dep = instanciaDepartment(rs);
					map.put(rs.getInt("id_department"), dep);
				}
				Employee emp = instanciaEmployee(rs, dep);
				list.add(emp);
			}
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Department instanciaDepartment(ResultSet rs) throws SQLException {
		
		Department dep = new Department();
		dep.setId(rs.getInt("id"));
		dep.setName(rs.getString("name"));
		return dep;	
	}
	
	private Employee instanciaEmployee(ResultSet rs, Department dep) throws SQLException {
		
		Employee emp = new Employee();
		emp.setId(rs.getInt("id"));
		emp.setName(rs.getString("name"));
		emp.setTel(rs.getString("tel"));
		emp.setBirthDate(rs.getDate("birthDate"));
		emp.setSalary(rs.getDouble("salary"));
		emp.setDepartment(dep);
		return emp;
		
	}

}
