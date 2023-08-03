package model.dao;

import java.util.List;
import model.entities.Department;


public interface GenericDao <T>{
	
	void inserir(T obj);
	void atualizar(T obj);
	void deletarPeloId(Integer id);
	T procurarPeloId(Integer id);
	List<T> procurarTudo();
	List<T> procurarPeloDepartamento(Department department);
}
