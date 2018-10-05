package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

import interfaces.Medicamento;
import interfaces.Medico;
import interfaces.Receita;
import interfaces.Utente;

@SuppressWarnings("serial")
public class ReceitaImpl extends UnicastRemoteObject implements Receita{
	
	private String cod;
	private ArrayList<Medicamento> medicamentos;
	private ArrayList<Integer> qtd;
	private Utente utente;
	private Medico medico;
	private boolean aviado;
	private Calendar data;
	
	protected ReceitaImpl() throws RemoteException {
		
	}

	protected ReceitaImpl(String cod, ArrayList<Medicamento> medicamentos, ArrayList<Integer> qtd, Utente utente,
			Medico medico, Calendar data, boolean aviado) throws RemoteException{
		
		this.cod = cod;
		this.medicamentos = medicamentos;
		this.qtd = qtd;
		this.utente = utente;
		this.medico = medico;
		this.aviado = aviado;
		this.data = data;
		
	}
	protected ReceitaImpl(ReceitaImpl o) throws RemoteException{ // Construtor para Clonagem
		this.cod = o.cod;
		this.medicamentos = o.medicamentos;
		this.qtd = o.qtd;
		this.utente = o.utente;
		this.medico = o.medico;
		this.aviado = o.aviado;
		this.data = o.data;
	}



	
	public String getAno() throws RemoteException{
		return Integer.toString(data.get(Calendar.YEAR));
	}
	
	public String getDataString() throws RemoteException{
		return data.getTime().toString();
	}
	public String getCod() throws RemoteException{
		return cod;
	}

	public void setCod(String cod) throws RemoteException{
		this.cod = cod;
	}

	public ArrayList<Medicamento> getMedicamentos() throws RemoteException{
		return medicamentos;
	}

	public void setMedicamentos(ArrayList<Medicamento> medicamentos) throws RemoteException{
		this.medicamentos = medicamentos;
	}

	public ArrayList<Integer> getQtd() throws RemoteException{
		return qtd;
	}

	public void setQtd(ArrayList<Integer> qtd) throws RemoteException{
		this.qtd = qtd;
	}

	public Utente getUtente() throws RemoteException{
		return utente;
	}

	public void setUtente(Utente utente) throws RemoteException{
		this.utente = utente;
	}

	public Medico getMedico() throws RemoteException{
		return medico;
	}

	public void setMedico(Medico medico) throws RemoteException{
		this.medico = medico;
	}

	public boolean isAviado() throws RemoteException{
		return aviado;
	}

	public void setAviado(boolean aviado) throws RemoteException{
		this.aviado = aviado;
	}

	@Override
	public Calendar getData() throws RemoteException {
		return data;
	}

}