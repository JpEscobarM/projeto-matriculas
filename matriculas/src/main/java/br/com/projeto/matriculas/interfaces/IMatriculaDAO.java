package br.com.projeto.matriculas.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.matriculas.model.Matricula;

@Repository
public interface IMatriculaDAO extends JpaRepository<Matricula, Integer>{
	

	
}
