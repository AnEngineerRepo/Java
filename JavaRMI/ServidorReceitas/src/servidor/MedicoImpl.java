package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import interfaces.Medicamento;
import interfaces.Medico;
import interfaces.Receita;
import interfaces.Utente;

@SuppressWarnings("serial")
public class MedicoImpl extends UnicastRemoteObject implements Medico {
	
	private String nome;
	private String bi;
	private String nif;
	private String morada;
	private String cp;
	private ArrayList<Utente> utentes;
	private ArrayList<Receita> receitas;
	private ArrayList<Medicamento> medicamentos;
	
	public MedicoImpl() throws RemoteException{

	}

	public MedicoImpl(String nome, String bi, String nif, String morada, String cp, ArrayList<Utente> utentes,
			ArrayList<Receita> receitas, ArrayList<Medicamento> medicamentos) throws RemoteException{
	
		this.nome = nome;
		this.bi = bi;
		this.nif = nif;
		this.morada = morada;
		this.cp = cp;
		this.utentes = utentes;
		this.receitas = receitas;
		this.medicamentos = medicamentos;
	}

	public String getNome() throws RemoteException{
		return nome;
	}

	public void setNome(String nome) throws RemoteException{
		this.nome = nome;
	}

	public String getBi() throws RemoteException{
		return bi;
	}

	public void setBi(String bi) throws RemoteException{
		this.bi = bi;
	}

	public String getNif() throws RemoteException{
		return nif;
	}

	public void setNif(String nif) throws RemoteException{
		this.nif = nif;
	}

	public String getMorada() throws RemoteException{
		return morada;
	}

	public void setMorada(String morada) throws RemoteException{
		this.morada = morada;
	}

	public String getCp() throws RemoteException{
		return cp;
	}

	public void setCp(String cp) throws RemoteException{
		this.cp = cp;
	}

	public ArrayList<Utente> getUtentes() throws RemoteException{
		return utentes;
	}

	public void setUtentes(ArrayList<Utente> utentes) throws RemoteException{
		this.utentes = utentes;
	}

	public ArrayList<Receita> getReceitas() throws RemoteException{
		return receitas;
	}

	public void setReceitas(ArrayList<Receita> receitas) throws RemoteException{
		this.receitas = receitas;
	}

	public ArrayList<Medicamento> getMedicamentos() throws RemoteException{
		return medicamentos;
	}

	public void setMedicamentos(ArrayList<Medicamento> medicamentos) throws RemoteException{
		this.medicamentos = medicamentos;
	}

	@Override
	public void addReceita(Receita r) throws RemoteException {
		// TODO Auto-generated method stub
		this.receitas.add(r);
	}

	@Override
	public void addUtente(Utente u) throws RemoteException {
		// TODO Auto-generated method stub
		this.utentes.add(u);
	}

	@Override
	public void addMedicamento(Medicamento m) throws RemoteException {
		// TODO Auto-generated method stub
		this.medicamentos.add(m);
	}

}

