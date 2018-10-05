package servidorfarmacia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import interfaces.Farmacia;
import interfaces.Receita;
import interfaces.Utente;

@SuppressWarnings("serial")
public class FarmaciaImpl extends UnicastRemoteObject implements Farmacia {
	private String Nome;
	private String Morada;
	private String cp;
	private ArrayList<Utente> fichas_utente = new ArrayList<>();
	private ArrayList<Receita> receitas_aviadas = new ArrayList<>();
	
	protected FarmaciaImpl() throws RemoteException {
		super();
	}

	public FarmaciaImpl(String nome, String morada, String cp, ArrayList<Utente> fichas_utente,
			ArrayList<Receita> receitas_aviadas) throws RemoteException {
		super();
		Nome = nome;
		Morada = morada;
		this.cp = cp;
		this.fichas_utente = fichas_utente;
		this.receitas_aviadas = receitas_aviadas;
	}

	public String getNome() throws RemoteException{
		return Nome;
	}

	public void setNome(String nome) throws RemoteException{
		Nome = nome;
	}

	public String getMorada() throws RemoteException{
		return Morada;
	}

	public void setMorada(String morada) throws RemoteException{
		Morada = morada;
	}

	public String getCp() throws RemoteException{
		return cp;
	}

	public void setCp(String cp) throws RemoteException{
		this.cp = cp;
	}
	@Override
	public ArrayList<Utente> getFichas_utente() throws RemoteException {
		return fichas_utente;
	}

	@Override
	public void setFichas_utente(ArrayList<Utente> fichas_utente) throws RemoteException {
		this.fichas_utente = fichas_utente;
	}

	@Override
	public ArrayList<Receita> getReceitas_aviadas() throws RemoteException {
		return receitas_aviadas;
	}

	@Override
	public void setReceitas_aviadas(ArrayList<Receita> receitas_aviadas) throws RemoteException {
		this.receitas_aviadas = receitas_aviadas;
		
	}

	public void setReceitas_aviadas(HashMap<String, Receita> receitas_aviadas) throws RemoteException{
		
	}

	@Override
	public void addReceitas_aviadas(Receita r) throws RemoteException {
		this.receitas_aviadas.add(r);
	}

	@Override
	public void addUtente(Utente u) throws RemoteException {
		this.fichas_utente.add(u);
		
	}

	

}
