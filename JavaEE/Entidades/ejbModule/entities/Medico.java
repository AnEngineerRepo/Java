package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;


/**
 * Entity implementation class for Entity: Medico
 *
 */
@Entity

public class Medico implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3689824315724492103L;
	@Id
	private String bi;    //primary key
	private String nome;
	private String nif;
	private String morada;
	private String cp;
	
	@OneToMany(fetch = EAGER)
	private Set<Utente> utentes;     //
	@OneToMany(fetch = EAGER)
	private Set<Receita> receitas;
	@OneToMany(fetch = EAGER)
	private Set<Medicamento> medicamentos;
	
	public Medico() {
		super();
		utentes= new HashSet<Utente>();             //utentes consultados pelo medico
		receitas=new HashSet<Receita>();            //receitas prescritas pelo medico
		medicamentos=new HashSet<Medicamento>();    //medicamentos prescritos pelo medico
	}   
	
	
	public String getBi() {
		return this.bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}   
	
	public String getNome() {
		return this.nome;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
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
	
	public Set<Utente> getUtentes() {
		return utentes;
	}
	public void setUtentes(Set<Utente> utentes) {
		this.utentes = utentes;
	}
	
	public Set<Receita> getReceitas() {
		return receitas;
	}
   
	public void setReceitas(Set<Receita> receitas){
		this.receitas = receitas;
	}


	public Set<Medicamento> getMedicamentos() {
		return medicamentos;
	}


	public void setMedicamentos(Set<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	
}
