package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;


/**
 * Entity implementation class for Entity: Utente
 *
 */
@Entity

public class Utente implements Serializable {

	   
	@Id
	private String bi;
	private String nome;
	private String nif;
	private String morada;
	private String cp;
	
	@OneToMany(fetch = EAGER)
	private Set<Medico> medicos;  
	@OneToMany(fetch = EAGER)
	private Set<Receita> receitas; 
	
	@OneToMany(fetch = EAGER)
	private Set<Medicamento> medicamentos;  
	
	@OneToMany(fetch = EAGER)
	private Set<Farmacia> farmacias;
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	public Utente() {
		super();
		medicos= new HashSet<Medico>();  //medicos que já consultaram o utente
		receitas = new HashSet<Receita>(); //receitas que já foram prescritas ao utente
		medicamentos = new HashSet<Medicamento>(); //medicamentos já recomendados ao utente
		farmacias = new HashSet<Farmacia>();
	}   
	
	public String getBi() {
		return this.bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public Set<Medico> getMedicos() {
		return medicos;
	}
	public void setMedicos(Set<Medico> medicos) {
		this.medicos = medicos;
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

	public Set<Farmacia> getFarmacias() {
		return farmacias;
	}

	public void setFarmacias(Set<Farmacia> farmacias) {
		this.farmacias = farmacias;
	}
	
}
