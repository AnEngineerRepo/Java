package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Farmacia extends Remote {
	public String getNome() throws RemoteException;
	public void setNome(String nome) throws RemoteException;
	public String getMorada() throws RemoteException;
	public void setMorada(String morada) throws RemoteException;
	public String getCp() throws RemoteException;
	public void setCp(String cp) throws RemoteException;
	public ArrayList<Utente> getFichas_utente() throws RemoteException;
	public void setFichas_utente(ArrayList<Utente> fichas_utente) throws RemoteException;
	public ArrayList<Receita> getReceitas_aviadas() throws RemoteException;
	public void setReceitas_aviadas(ArrayList<Receita> receitas_aviadas) throws RemoteException;

	public void addReceitas_aviadas(Receita r) throws RemoteException;
	public void addUtente(Utente u) throws RemoteException;
}
