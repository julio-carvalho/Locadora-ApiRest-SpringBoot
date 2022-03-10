package br.com.locadora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_JOGO")
@SequenceGenerator(allocationSize = 1, name = "jogo", sequenceName = "sq_jogo")
public class Jogo {
	
	@Id
	@GeneratedValue(generator = "jogo", strategy = GenerationType.IDENTITY)
	@Column(name = "id_jogo")
	private Long id;
	
	@Column(name = "nm_nome")
	private String nome;
	
	@Column(name = "ds_genero")
	private String genero;
	
	@Column(name = "ds_classificacao")
	private String classificacao;
	
	@Column(name = "ds_ano")
	private int ano;
	
	@Column(name = "ds_plataforma")
	private String plataforma;
	
	@Column(name = "ds_valor")
	private double valor;
	
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(name="dt_aluguel")
	private String dataAluguel;
	
	private boolean disponivel;

	public Jogo() {
		super();
	}

	public Jogo(Long id, String nome, String genero, String classificacao, int ano, String plataforma, double valor, Long idCliente, String dataAluguel, boolean disponivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.classificacao = classificacao;
		this.ano = ano;
		this.plataforma = plataforma;
		this.valor = valor;
		this.idCliente = idCliente;
		this.dataAluguel = dataAluguel;
		this.disponivel = disponivel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(String dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}
