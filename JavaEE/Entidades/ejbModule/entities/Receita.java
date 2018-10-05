package entities;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Receita
 *
 */
@Entity

public class Receita implements Serializable {

	   
	@Id
	@GeneratedValue
	private String id;      //codigo de identificaçao gerado para a receita
	private Calendar data;
	private Boolean aviado; // estado da receita(aviada ou nao)
	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch = EAGER)
	private Medico medico;
	@OneToOne(fetch = EAGER)
	private Utente utente;
	
	@OneToMany(fetch = EAGER)
	private Set<Medicamento> medicamentos;  //medicamentos prescritos por um medico
	
	@OneToOne(fetch = EAGER)
	private Farmacia farmacia;     //farmacia onde a receita é levantada
	
	private ArrayList<Integer> quantidade; //quantidade de medicamentos prescrita por um médico
	
	
	public Receita() {
		super();
		 medico = new Medico();
		 utente = new Utente();
		 medicamentos = new HashSet<Medicamento>();
		 farmacia = new Farmacia();
		 quantidade = new ArrayList<Integer>();
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public Calendar getData() {
		return this.data;
	}
	
	public String getDataAno() {
		return Integer.toString(data.get(Calendar.YEAR));
	}
	
	public String getDataString() {
		return data.getTime().toString();
	}

	public void setData(Calendar data) {
		this.data = data;
	}   
	public Boolean getAviado() {
		return this.aviado;
	}

	public void setAviado(Boolean aviado) {
		this.aviado = aviado;
	}
	public ArrayList<Integer> getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(ArrayList<Integer> quantidade) {
		this.quantidade = quantidade;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Set<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(Set<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public Farmacia getFarmacia() {
		return farmacia;
	}
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
   
}
