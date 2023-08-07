package model.dao;

import java.util.List;

public interface GenericDao <T>{
	
	void inserir(T obj);
	void atualizar(T obj);
	void deletarPeloId(Integer id);
	T procurarPeloId(Integer id);
	List<T> procurarTudo();
}
