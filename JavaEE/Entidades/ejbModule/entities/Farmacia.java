package entities;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Farmacia
 *
 */
@Entity

public class Farmacia implements Serializable {

	
	@Id
	private String id;
	private String nome;
	private String morada;
	private String cp;   
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = EAGER)
	private Set<Utente> utentes;

	@OneToMany(fetch = EAGER)
	private Set<Medicamento> medicamentos;
	
	@OneToMany(fetch = EAGER)
	private Set<Receita> receitas_aviadas;
	
	public Farmacia() {
		super();
		utentes = new HashSet<Utente>();           //utentes servidos pela farmacia
		medicamentos = new HashSet<Medicamento>(); //medicamentos existentes na farmacia
		receitas_aviadas = new HashSet<Receita>(); //receitas aviadas na farmacia
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getMorada() {
		return this.morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}   
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public Set<Utente> getUtentes() {
		return utentes;
	}
	public void setUtentes(Set<Utente> utentes) {
		this.utentes = utentes;
	}
	public Set<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(Set<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public Set<Receita> getReceitas_aviadas() {
		return receitas_aviadas;
	}
	public void setReceitas_aviadas(Set<Receita> receitas_aviadas) {
		this.receitas_aviadas = receitas_aviadas;
	}
	
}
