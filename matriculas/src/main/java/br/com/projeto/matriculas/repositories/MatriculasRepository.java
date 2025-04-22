package br.com.projeto.matriculas.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.projeto.matriculas.interfaces.IMatriculaDAO;
import br.com.projeto.matriculas.model.Matricula;

@Repository
public class MatriculasRepository {

	//FAZ INJECAO DE DEPENDENCIAS NO "dao" SEM PRECISAR USAR IMPLEMENT EM OUTRA CLASSE
	//PARA ACESSAR OS OS METODOS
	@Autowired 
	private IMatriculaDAO dao;
	
	
	public List<Matricula> listaMatriculas()
	{
		return (List<Matricula>) dao.findAll(); //USA FINDALL E FAZ O CASTING PRA MATRICULA
	}
	
	public Page<Matricula> listaMatriculas(Pageable pageable) {
	    return dao.findAll(pageable);
	}
	
}
