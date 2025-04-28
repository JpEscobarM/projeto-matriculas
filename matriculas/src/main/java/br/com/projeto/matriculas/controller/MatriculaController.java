package br.com.projeto.matriculas.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.matriculas.model.Matricula;
import br.com.projeto.matriculas.service.MatriculasService;

@RestController
public class MatriculaController {
	
	@Autowired
	private MatriculasService ms;
	
	@GetMapping("/matriculas")
	public Page<Matricula> listarMatriculas(Pageable pageable) {
	    return ms.listaMatriculas(pageable);
	}
	
	//Total de alunos matriculados (no Brasil) por ANO e
	//Total de alunos matriculados por ANO com possibilidade de escolher EAD ou Presencial ( passando como parametro no
	//endpoint)
	@GetMapping("/matriculas/total-por-ano")
	public Map<Integer,Integer> totalMatriculadosPorAno(@RequestParam(required = false) String modalidade)
	{
		if(modalidade != null)
		{
			return ms.totalMatriculadosBrasilPorAno(modalidade);
		}
		else
		{
			return ms.totalMatriculadosBrasilPorAno();
		}
	
	}
	//Ranking de cursos em 2022 (10 cursos com maior número de matrículas no Brasil) 
	//com possibilidade de escolher EAD ou Presencial ( passando como parametro no
	//endpoint)
	@GetMapping("matriculas/ranking2022")
	public Map<String,Integer> rankingTop10de2022(@RequestParam(required = false) String modalidade)
	{
	
		if(modalidade != null)
		{
			return ms.listaRanking2022(modalidade);
		}
		else
		{
			return ms.listaRanking2022();
		}
	
	}
	
	//Total de alunos matriculados (no Brasil) por ANO e ESTADO
	@GetMapping("matriculas/total-por-estado")
	public Map<Integer,Integer> totalMatriculadosPorEstado(@RequestParam String estado,@RequestParam(required = false) String modalidade
			) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		
		if(modalidade != null && estado != null)
		{

			//Total de alunos matriculados (no Brasil) por ano,
			//com a possibilidade de escolher a modalidade (EaD ou Presencial)
			
			return ms.totalPorEstadoModalidade(estado,modalidade);
		}
		else
		{
			return ms.totalPorEstado(estado);
		}
	
	}
	
	
	//Ranking de cursos em 2022 (10 cursos com maior número de matrículas no Brasil)
	//COM FILTRO PARA ESTADO
	@GetMapping("matriculas/ranking2022PorEstado")
	public Map<String,Integer> rankingTop10de2022PorEstado(@RequestParam String estado,@RequestParam(required = false) String modalidade)
	{
	
		if(modalidade != null)
		{
			return  ms.listaRanking2022PorEstado(estado,modalidade);
		}
		else
		{
			return ms.listaRanking2022PorEstado(estado);
		}
	
	}
	

	
}
