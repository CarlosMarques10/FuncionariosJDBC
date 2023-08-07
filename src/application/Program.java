package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import db.excpetions.DbException;
import model.dao.DaoFactory;
import model.dao.implement.DepartmentDao;
import model.dao.implement.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		

		char respostaMenu;
		int opcao;
		
		do {
			System.out.println("---------------BEM-VINDO---------------");
			System.out.println("Escolha uma opção: ");
			System.out.println("1 = Adicionar um novo Empregado");
			System.out.println("2 = Adicionar um Departamento");
			System.out.println("3 = Mostrar lista de Empregados");
			System.out.println("4 = Mostrar lista de Departamentos");
			System.out.println("5 = Buscar Empregado pelo ID");
			System.out.println("6 = Buscar Empregado pelo departamento");
			System.out.println("7 = Deletar um Empregado");
			opcao = scan.nextInt();
			
			switch(opcao) {
			
			case 1 -> adicionarEmpregado(scan);
			case 2 -> adicionarDepartamento(scan);
			case 3 -> mostrarEmpregados();
			case 4 -> mostrarDepartamentos();
			case 5 -> buscarEmpregadoPeloId(scan);
			case 6 -> buscarEmpregadoPeloDepartamento(scan);
			case 7 -> deletarEmpregado(scan);
				
			}
			System.out.println();
			System.out.println("Mostrar o menu novamente? (y/n)");
			respostaMenu = scan.next().charAt(0);
			
		} while(respostaMenu == 'y');
		
		scan.close();
		
	}
	
	private static void adicionarEmpregado(Scanner scan) {
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		
		try {
			System.out.println("Informe o nome: ");
			String name = scan.nextLine();
			System.out.println("Informe o telefone: ");
			String tel = scan.nextLine();
			System.out.println("Salario: ");
			double salary = scan.nextDouble();
			scan.nextLine();
			System.out.println("ID do departamento: ");
			int departamento = scan.nextInt();
			
			Employee emp = new Employee(name, tel, salary, new Department(departamento));
			employeeDao.inserir(emp);
			System.out.println("Empregado cadastrado com sucesso!");
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}

	}
	
	
	private static void adicionarDepartamento(Scanner scan) {
		Department department = new Department();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		try {
			System.out.println("Informe o nome do Departamento: ");
			String nomeDepartamento = scan.nextLine();
			
			if(departmentDao.procurarPeloDepartamento(nomeDepartamento) == null) {
				department.setName(nomeDepartamento);
				departmentDao.inserir(department);
				System.out.println("Departamento cadastrado!");
			}else {
				System.out.println("Departamento ja existe");
			}
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}	
	}
	
	
	private static void mostrarEmpregados() {
		
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		
		try {
			List<Employee> list = new ArrayList<>();
			list = employeeDao.procurarTudo();
			
			for (Employee employee : list) {
				System.out.println(employee);
			}
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		
	}
	

	private static void mostrarDepartamentos() {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		try {
			List<Department> list = new ArrayList<>();
			list = departmentDao.procurarTudo();
			
			for (Department department : list) {
				System.out.println(department);
			}
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	
	private static void buscarEmpregadoPeloId(Scanner scan) {
		EmployeeDao employee = DaoFactory.createEmployeeDao();
		
		try {
			System.out.println("Digite o Id do empregado: ");
			int idFuncionario = scan.nextInt();
			
			if(employee.procurarPeloId(idFuncionario) != null) {
				System.out.println(employee.procurarPeloId(idFuncionario));
			}else {
				System.out.println("Empregado não existe");
			}
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} 
		
	}
	
	
	private static void buscarEmpregadoPeloDepartamento(Scanner scan) {
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		
		try {
			System.out.println("Informe o ID do Departamento: ");
			int nameDepartment = scan.nextInt();
			
			Department dep = new Department(nameDepartment);
			
			List<Employee> list = new ArrayList<>();
			list = employeeDao.procurarPeloDepartamento(dep);
			
			if(list.size() > 0) {
				for (Employee employee : list) {
					System.out.println(employee);
				}
			}else {
				System.out.println("Não existem funcionarios no departamento informado!");
			}
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	
	private static void deletarEmpregado(Scanner scan) {
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		
		try {
			System.out.println("Informe o ID do empregado que deseja excluir: ");
			int id = scan.nextInt();
			
			employeeDao.deletarPeloId(id);
			System.out.println("Empregado deletado com sucesso!");
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
			
	}
	

}
