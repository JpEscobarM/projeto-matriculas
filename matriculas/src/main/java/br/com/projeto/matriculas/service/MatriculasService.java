package br.com.projeto.matriculas.service;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.matriculas.model.Matricula;
import br.com.projeto.matriculas.repositories.MatriculasRepository;
import jakarta.annotation.PostConstruct;

@Service
public class MatriculasService {

	@Autowired
	private MatriculasRepository matriculaRepository;
	
	private List<String> cursos;
	private List<String> estados;
	
	@PostConstruct
	public void init()
	{
		cursos = getCursos();
		estados = getEstados();
	}
	
	public List<String> getCursos() {
		
		List<String> cursos = new LinkedList<>();
			
		
		for(Matricula m: matriculaRepository.listaMatriculas())
		{
			if(cursos.contains(m.getNomeDoCurso())) continue;
			
			cursos.add(m.getNomeDoCurso());
			
		}
		
		return cursos;
	}
	
	
	public List<String> getEstados() {
		
		List<String> estados = new LinkedList<>();
			
		
		for(Matricula m: matriculaRepository.listaMatriculas())
		{
			if(estados.contains(m.getEstado())) continue;
			
			estados.add(m.getEstado());
			
		}
		
		return estados;
	}
	
	public int verificaValor(Integer valor) {
		return valor != null ? valor : 0;
	}

	public List<Matricula> sortMatriculasCrescente(List<Matricula> top10)
	{
		
		top10.sort((m1, m2) -> m1.getMatriculas2022().compareTo(m2.getMatriculas2022()));
		
		return top10;
	}
	
	public List<Matricula> listaMatriculas() {

		return matriculaRepository.listaMatriculas();
	}

	public Page<Matricula> listaMatriculas(Pageable pageable) {
		return matriculaRepository.listaMatriculas(pageable);
	}

	public Map<Integer, Integer> totalMatriculadosBrasilPorAno() {

		Map<Integer, Integer> totalPorAno = new LinkedHashMap();

		for (Matricula m : matriculaRepository.listaMatriculas()) {
			totalPorAno.put(2014, totalPorAno.getOrDefault(2014, 0) + verificaValor(m.getMatriculas2014()));
			totalPorAno.put(2015, totalPorAno.getOrDefault(2015, 0) + verificaValor(m.getMatriculas2015()));
			totalPorAno.put(2016, totalPorAno.getOrDefault(2016, 0) + verificaValor(m.getMatriculas2016()));
			totalPorAno.put(2017, totalPorAno.getOrDefault(2017, 0) + verificaValor(m.getMatriculas2017()));
			totalPorAno.put(2018, totalPorAno.getOrDefault(2018, 0) + verificaValor(m.getMatriculas2018()));
			totalPorAno.put(2019, totalPorAno.getOrDefault(2019, 0) + verificaValor(m.getMatriculas2019()));
			totalPorAno.put(2020, totalPorAno.getOrDefault(2020, 0) + verificaValor(m.getMatriculas2020()));
			totalPorAno.put(2021, totalPorAno.getOrDefault(2021, 0) + verificaValor(m.getMatriculas2021()));
			totalPorAno.put(2022, totalPorAno.getOrDefault(2022, 0) + verificaValor(m.getMatriculas2022()));

		}
		return totalPorAno;
	}

	public Map<Integer, Integer> totalMatriculadosBrasilPorAno(String modalidade) {
		Map<Integer, Integer> totalPorAno = new LinkedHashMap<>();

		for (Matricula m : matriculaRepository.listaMatriculas()) {
			if (!modalidade.equalsIgnoreCase(m.getModalidade())) {
				continue;
			}
			totalPorAno.put(2014, totalPorAno.getOrDefault(2014, 0) + verificaValor(m.getMatriculas2014()));
			totalPorAno.put(2015, totalPorAno.getOrDefault(2015, 0) + verificaValor(m.getMatriculas2015()));
			totalPorAno.put(2016, totalPorAno.getOrDefault(2016, 0) + verificaValor(m.getMatriculas2016()));
			totalPorAno.put(2017, totalPorAno.getOrDefault(2017, 0) + verificaValor(m.getMatriculas2017()));
			totalPorAno.put(2018, totalPorAno.getOrDefault(2018, 0) + verificaValor(m.getMatriculas2018()));
			totalPorAno.put(2019, totalPorAno.getOrDefault(2019, 0) + verificaValor(m.getMatriculas2019()));
			totalPorAno.put(2020, totalPorAno.getOrDefault(2020, 0) + verificaValor(m.getMatriculas2020()));
			totalPorAno.put(2021, totalPorAno.getOrDefault(2021, 0) + verificaValor(m.getMatriculas2021()));
			totalPorAno.put(2022, totalPorAno.getOrDefault(2022, 0) + verificaValor(m.getMatriculas2022()));

		}

		return totalPorAno;
	}

	
	public List<String> getKeyPorValor(Map<String, Integer> map, Integer valorProcurado) {
	    List<String> chaves = new ArrayList<>();
	    
	    for (Map.Entry<String, Integer> entry : map.entrySet()) {
	        if (entry.getValue().equals(valorProcurado)) {
	            chaves.add(entry.getKey());
	        }
	    }
	    
	    return chaves;
	}
	
	public Map<String, Integer> listaRanking2022() {
	    Map<String, Integer> mapCursos = new LinkedHashMap<>();

	    for (String c : cursos) {
	        mapCursos.put(c, 0);
	    }

	    for (Matricula m : matriculaRepository.listaMatriculas()) {
	        String curso = m.getNomeDoCurso();
	        int soma = mapCursos.getOrDefault(curso, 0) + verificaValor(m.getMatriculas2022());
	        mapCursos.put(curso, soma);
	    }

	
	    Map<String, Integer> ordenado = mapCursos.entrySet()
	        .stream()
	        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	        .limit(10)
	        .collect(Collectors.toMap(
	            Map.Entry::getKey,
	            Map.Entry::getValue,
	            (e1, e2) -> e1,
	            LinkedHashMap::new
	        ));

	    return ordenado;
	}

	public Map<String, Integer> listaRanking2022(String modalidade) {
	    Map<String, Integer> mapCursos = new LinkedHashMap<>();

	    for (String c : cursos) {
	        mapCursos.put(c, 0);
	    }

	    for (Matricula m : matriculaRepository.listaMatriculas()) {
	    	
	    	if( m.getModalidade()!= null && m.getModalidade().equalsIgnoreCase(modalidade))
	    	{
	    		String curso = m.getNomeDoCurso();
		        int soma = mapCursos.getOrDefault(curso, 0) + verificaValor(m.getMatriculas2022());
		        mapCursos.put(curso, soma);
	    	}
	        
	    }

	
	    Map<String, Integer> ordenado = mapCursos.entrySet()
	        .stream()
	        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	        .limit(10)
	        .collect(Collectors.toMap(
	            Map.Entry::getKey,
	            Map.Entry::getValue,
	            (e1, e2) -> e1,
	            LinkedHashMap::new
	        ));

	    return ordenado;
	}
	
	//RANKING DE CURSOS EM 2022 ( 10 CURSOS COM MAIOR NUMERO DE MATRICULAS NO BRASIL)
	//POR ESTADO
	public Map<String, Integer> listaRanking2022PorEstado(String estado) {
	    Map<String, Integer> mapCursos = new LinkedHashMap<>();

	    for (String c : cursos) {
	        mapCursos.put(c, 0);
	    }

	    for (Matricula m : matriculaRepository.listaMatriculas()) {
	    	
	    	if( m.getEstado()!= null && m.getEstado().equalsIgnoreCase(estado))
	    	{
	    		String curso = m.getNomeDoCurso();
		        int soma = mapCursos.getOrDefault(curso, 0) + verificaValor(m.getMatriculas2022());
		        mapCursos.put(curso, soma);
	    	}
	        
	    }

	
	    Map<String, Integer> ordenado = mapCursos.entrySet()
	        .stream()
	        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	        .limit(10)
	        .collect(Collectors.toMap(
	            Map.Entry::getKey,
	            Map.Entry::getValue,
	            (e1, e2) -> e1,
	            LinkedHashMap::new
	        ));

	    return ordenado;
	}
	
	//RANKING DE CURSOS EM 2022 ( 10 CURSOS COM MAIOR NUMERO DE MATRICULAS NO BRASIL)
	//POR ESTADO E MODALIDADE
		public Map<String, Integer> listaRanking2022PorEstado(String estado,String modalidade) {
		    Map<String, Integer> mapCursos = new LinkedHashMap<>();

		    for (String c : cursos) {
		        mapCursos.put(c, 0);
		    }

		    for (Matricula m : matriculaRepository.listaMatriculas()) {
		    	
		    	if( m.getEstado()!= null && m.getEstado().equalsIgnoreCase(estado) && m.getModalidade().equalsIgnoreCase(modalidade))
		    	{
		    		String curso = m.getNomeDoCurso();
			        int soma = mapCursos.getOrDefault(curso, 0) + verificaValor(m.getMatriculas2022());
			        mapCursos.put(curso, soma);
		    	}
		        
		    }

		
		    Map<String, Integer> ordenado = mapCursos.entrySet()
		        .stream()
		        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
		        .limit(10)
		        .collect(Collectors.toMap(
		            Map.Entry::getKey,
		            Map.Entry::getValue,
		            (e1, e2) -> e1,
		            LinkedHashMap::new
		        ));

		    return ordenado;
		}
	
	
	public Map<Integer, Integer> totalPorEstado(String estado) {
		
		List<Integer> anos = Arrays.asList(2014,2015,2016,2017,2018,2019,2020,2021,2022);
		
		Map<Integer, Integer> totalPorAno = new LinkedHashMap();
		
		for(Matricula m: matriculaRepository.listaMatriculas())
		{
			if(m.getEstado().equalsIgnoreCase(estado))
			{
				for(Integer ano : anos)
				{
					try {
						Method metodo = Matricula.class.getMethod("getMatriculas"+ano);
						Integer valor = (Integer) metodo.invoke(m);
						totalPorAno.put(ano, totalPorAno.getOrDefault(ano, 0) + verificaValor(valor));
						
					} catch (NoSuchMethodException e) {
						
						e.printStackTrace();
					} catch (SecurityException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
				
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
					
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
		
		return totalPorAno;
	}

	//Total de alunos matriculados (no Brasil) por ano, com a possibilidade de escolher a modalidade (EaD ou Presencial)
	public Map<Integer, Integer> totalPorEstadoEModalidade(String estado) {
		
		List<Integer> anos = Arrays.asList(2014,2015,2016,2017,2018,2019,2020,2021,2022);
		
		Map<Integer, Integer> totalPorAno = new LinkedHashMap();
		
		for(Matricula m: matriculaRepository.listaMatriculas())
		{
			if(m.getEstado().equalsIgnoreCase(estado))
			{
				for(Integer ano : anos)
				{
					try {
						Method metodo = Matricula.class.getMethod("getMatriculas"+ano);
						Integer valor = (Integer) metodo.invoke(m);
						totalPorAno.put(ano, totalPorAno.getOrDefault(ano, 0) + verificaValor(valor));
						
					} catch (NoSuchMethodException e) {
						
						e.printStackTrace();
					} catch (SecurityException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
				
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
					
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
		
		return totalPorAno;
	}
	
	public List<Integer> gerarAnos()
	{
		List<Integer> anos = new LinkedList<Integer>();
		
	
		for(int i = 2014; i < 2022;i++) {
			anos.add(i);
		}
		return anos;
	}
	
	public Map<Integer,Integer> totalPorEstadoModalidade(String estado, String modalidade) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		
	List<Integer> anos = gerarAnos();
		
	Map<Integer,Integer> totalPorAnoEstadoModalidade = new HashMap<Integer,Integer>();
	
	for(Integer i : anos)
	{
		totalPorAnoEstadoModalidade.put(i, 0);
	}
	
	for(Matricula m : matriculaRepository.listaMatriculas())
	{
		if(m.getModalidade().equalsIgnoreCase(modalidade) && m.getEstado().equalsIgnoreCase(estado))
		{
			for(int i = 2014; i < 2023 ; i ++)
			{
				
				try {
					Method metodo;
					metodo = Matricula.class.getMethod("getMatriculas"+i);
					Integer valor = (Integer) metodo.invoke(m);
					totalPorAnoEstadoModalidade.put(i, 
				   totalPorAnoEstadoModalidade.getOrDefault(i, 0)+ verificaValor(valor));
				} catch (NoSuchMethodException | SecurityException e) {
					
					e.printStackTrace();
				}
				
				
			
			}
		}
		
	}
		
		return totalPorAnoEstadoModalidade;
	}
	
	
}
