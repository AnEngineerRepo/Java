package entities;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

/**
 * Entity implementation class for Entity: Medicamento
 *
 */
@Entity

public class Medicamento implements Serializable {

//Esta entidade inclui os medicamentos disponíveis para prescrição.
	   
	@Id
	@GeneratedValue
	private String cod;
	private String nome_gen;
	private String nome;
	private String forma;
	private String dosagem;
	private String autorizacao;
	private String generico;
	private String titular;
	private Integer qtd_stock;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = EAGER)
	private Set<Medico> medicos;
	
	@OneToMany(fetch = EAGER)
	private Set<Utente> utentes;

	@OneToMany(fetch = EAGER)
	private Set<Receita> receitas;
	
	@OneToMany(fetch = EAGER)
	private Set<Farmacia> farmacias;
	

	public Medicamento() {
		
		//Os seguintes hashsets representam os medicos, utentes, receitas e farmacias
		//associados a cada medicamento.
		super();
		medicos = new HashSet<Medico>();
		utentes = new HashSet<Utente>();
		receitas = new HashSet<Receita>();	
		farmacias = new HashSet<Farmacia>();
	}   
	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}   
	public String getNome_gen() {
		return this.nome_gen;
	}

	public void setNome_gen(String nome_gen) {
		this.nome_gen = nome_gen;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getForma() {
		return this.forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}   
	public String getDosagem() {
		return this.dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}   
	public String getAutorizacao() {
		return this.autorizacao;
	}

	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}   
	public String getGenerico() {
		return this.generico;
	}

	public void setGenerico(String generico) {
		this.generico = generico;
	}   
	public String getTitular() {
		return this.titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}   
	public Integer getQtd_stock() {
		return this.qtd_stock;
	}

	public void setQtd_stock(Integer qtd_stock) {
		this.qtd_stock = qtd_stock;
	}
	
	public Set<Medico> getMedicos() {
		return medicos;
	}
	public void setMedicos(Set<Medico> medicos) {
		this.medicos = medicos;
	}
	public Set<Utente> getUtentes() {
		return utentes;
	}
	public void setUtentes(Set<Utente> utentes) {
		this.utentes = utentes;
	}
	public Set<Receita> getReceitas() {
		return receitas;
	}
	public void setReceitas(Set<Receita> receitas) {
		this.receitas = receitas;
	}
	public Set<Farmacia> getFarmacias() {
		return farmacias;
	}
	public void setFarmacias(Set<Farmacia> farmacias) {
		this.farmacias = farmacias;
	}
   
}
