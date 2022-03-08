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
		
	@Column(name = "ds_valor")
	private double valor;
	
	@Column(name = "id_cliente")
	private Long idCliente;
	
	private boolean disponivel;

	public Jogo() {
		super();
	}

	public Jogo(Long id, String nome, String genero, String classificacao, int ano, boolean disponivel, double valor, Long idCliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.classificacao = classificacao;
		this.ano = ano;
		this.disponivel = disponivel;
		this.valor = valor;
		this.idCliente = idCliente;
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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
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
}
