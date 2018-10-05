package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Medicamento extends Remote{
	public String getCod() throws RemoteException;
	public void setCod(String cod) throws RemoteException;
	public String getNome_generico() throws RemoteException;
	public void setNome_generico(String nome_generico) throws RemoteException;
	public String getNome() throws RemoteException;
	public void setNome(String nome) throws RemoteException;
	public String getForma() throws RemoteException;
	public void setForma(String forma) throws RemoteException;
	public String getDosagem() throws RemoteException;
	public void setDosagem(String dosagem) throws RemoteException;
	public String getAutorizacao() throws RemoteException;
	public void setAutorizacao(String autorizacao) throws RemoteException;
	public String getGenerico() throws RemoteException;
	public void setGenerico(String generico) throws RemoteException;
	public String getTitular() throws RemoteException;
	public void setTitular(String titular) throws RemoteException;
	public int getQtd() throws RemoteException;
	public void setQtd(int qtd) throws RemoteException;
	public ArrayList<Utente> getUtentes() throws RemoteException;
	public void setUtentes(ArrayList<Utente> utentes) throws RemoteException;
	public ArrayList<Medico> getMedicos() throws RemoteException;
	public void setMedicos(ArrayList<Medico> medicos) throws RemoteException;
	public ArrayList<Receita> getReceitas() throws RemoteException;
	public void setReceitas(ArrayList<Receita> receitas) throws RemoteException;
	
	
	public void AddReceita(Receita r) throws RemoteException;
	public void AddUtente(Utente u) throws RemoteException;
	public void AddMedico(Medico m) throws RemoteException;
}
