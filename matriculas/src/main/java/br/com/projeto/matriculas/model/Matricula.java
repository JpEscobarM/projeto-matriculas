package br.com.projeto.matriculas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="matriculasbrasil")
public class Matricula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name ="estado", length= 200, nullable = true)
	private String estado;
	
	@Column(name ="cidade", length= 200, nullable = true)
	private String cidade;
	
	@Column(name ="ies", length= 200, nullable = true)
	private String ies;
	
	@Column(name ="sigla", length= 50, nullable = true)
	private String sigla;
	
	@Column(name ="organizacao", length= 200, nullable = true)
	private String organizacao;
	
	@Column(name ="categoria_administrativa", length= 200, nullable = true)
	private String categoriaAdministrativa;
	
	@Column(name ="nome_do_curso", length= 200, nullable = true)
	private String nomeDoCurso;
	
	@Column(name ="nome_detalhado_do_curso", length= 200, nullable = true)
	private String nomeDetalhadoDoCurso;
	
	@Column(name ="modalidade", length= 200, nullable = true)
	private String modalidade;

	@Column(name ="grau", length= 50, nullable = true)
	private String grau;
	
	@Column(name ="matriculas_2014", nullable = true)
	private Integer matriculas2014;
	
	@Column(name ="matriculas_2015", nullable = true)
	private Integer matriculas2015;
	
	@Column(name ="matriculas_2016", nullable = true)
	private Integer matriculas2016;
	
	@Column(name ="matriculas_2017", nullable = true)
	private Integer matriculas2017;
	
	@Column(name ="matriculas_2018", nullable = true)
	private Integer matriculas2018;
	
	@Column(name ="matriculas_2019", nullable = true)
	private Integer matriculas2019;
	
	@Column(name ="matriculas_2020", nullable = true)
	private Integer matriculas2020;
	
	@Column(name ="matriculas_2021", nullable = true)
	private Integer matriculas2021;
	
	@Column(name ="matriculas_2022", nullable = true)
	private Integer matriculas2022;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getIes() {
		return ies;
	}
	public void setIes(String ies) {
		this.ies = ies;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getOrganizacao() {
		return organizacao;
	}
	public void setOrganizacao(String organizacao) {
		this.organizacao = organizacao;
	}
	public String getCategoriaAdministrativa() {
		return categoriaAdministrativa;
	}
	public void setCategoriaAdministrativa(String categoriaAdministrativa) {
		this.categoriaAdministrativa = categoriaAdministrativa;
	}
	public String getNomeDoCurso() {
		return nomeDoCurso;
	}
	public void setNomeDoCurso(String nomeDoCurso) {
		this.nomeDoCurso = nomeDoCurso;
	}
	public String getNomeDetalhadoDoCurso() {
		return nomeDetalhadoDoCurso;
	}
	public void setNomeDetalhadoDoCurso(String nomeDetalhadoDoCurso) {
		this.nomeDetalhadoDoCurso = nomeDetalhadoDoCurso;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
	public Integer getMatriculas2014() {
		return matriculas2014;
	}
	public void setMatriculas2014(Integer matriculas2014) {
		this.matriculas2014 = matriculas2014;
	}
	public Integer getMatriculas2015() {
		return matriculas2015;
	}
	public void setMatriculas2015(Integer matriculas2015) {
		this.matriculas2015 = matriculas2015;
	}
	public Integer getMatriculas2016() {
		return matriculas2016;
	}
	public void setMatriculas2016(Integer matriculas2016) {
		this.matriculas2016 = matriculas2016;
	}
	public Integer getMatriculas2017() {
		return matriculas2017;
	}
	public void setMatriculas2017(Integer matriculas2017) {
		this.matriculas2017 = matriculas2017;
	}
	public Integer getMatriculas2018() {
		return matriculas2018;
	}
	public void setMatriculas2018(Integer matriculas2018) {
		this.matriculas2018 = matriculas2018;
	}
	public Integer getMatriculas2019() {
		return matriculas2019;
	}
	public void setMatriculas2019(Integer matriculas2019) {
		this.matriculas2019 = matriculas2019;
	}
	public Integer getMatriculas2020() {
		return matriculas2020;
	}
	public void setMatriculas2020(Integer matriculas2020) {
		this.matriculas2020 = matriculas2020;
	}
	public Integer getMatriculas2021() {
		return matriculas2021;
	}
	public void setMatriculas2021(Integer matriculas2021) {
		this.matriculas2021 = matriculas2021;
	}
	public Integer getMatriculas2022() {
		return matriculas2022;
	}
	public void setMatriculas2022(Integer matriculas2022) {
		this.matriculas2022 = matriculas2022;
	}
}
