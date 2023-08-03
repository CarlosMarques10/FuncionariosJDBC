package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Employee;

public interface EmployeeDao {
	
	void inserir(Employee obj);
	void atualizar(Employee obj);
	void deletarPeloId(Integer id);
	Employee procurarPeloId(Integer id);
	List<Employee> procurarTudo();
	List<Employee> procurarPeloDepartamento(Department department);
}
