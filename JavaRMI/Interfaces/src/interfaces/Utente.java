package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface Utente extends Remote{
	public String getNome() throws RemoteException;
	public void setNome(String nome) throws RemoteException;
	public String getBi() throws RemoteException;
	public void setBi(String bi) throws RemoteException;
	public String getNif() throws RemoteException;
	public void setNif(String nif) throws RemoteException;
	public String getMorada() throws RemoteException;
	public void setMorada(String morada) throws RemoteException;
	public String getCp() throws RemoteException;
	public void setCp(String cp) throws RemoteException;
	public ArrayList<Medico> getMedicos() throws RemoteException;
	public void setMedicos(ArrayList<Medico> medicos) throws RemoteException;
	public ArrayList<Receita> getReceitas()throws RemoteException;
	public void setReceitas(ArrayList<Receita> receitas) throws RemoteException;
	public ArrayList<Medicamento> getMedicamentos() throws RemoteException;
	public void setMedicamentos(ArrayList<Medicamento> medicamentos) throws RemoteException;
	
	public void addMedicamento(Medicamento m) throws RemoteException;
	public void addMedico(Medico med) throws RemoteException;
	public void addReceita(Receita r) throws RemoteException;
}
