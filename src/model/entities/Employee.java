package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String tel;
	private Double salary;
	private Department department;
	
	public Employee() {}

	public Employee(String name, String tel, Double salary, Department department) {
		this.name = name;
		this.tel = tel;
		this.salary = salary;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Nome: ").append(getName());
	    sb.append(", Tel: ").append(getTel());
	    sb.append(", Salario: R$ ").append(getSalary());
	    if (department != null) {
	        sb.append(", ID do departamento: ").append(department.getId());
	    } else {
	        sb.append(", Departamento não atribuído");
	    }
	    return sb.toString();
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}
	

}
