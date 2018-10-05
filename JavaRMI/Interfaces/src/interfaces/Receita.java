package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

//ENTIDADE RECEITA

public interface Receita extends Remote{
	public String getAno()throws RemoteException;
	public String getDataString() throws RemoteException;
	public Calendar getData() throws RemoteException;
	public String getCod() throws RemoteException;
	public void setCod(String cod) throws RemoteException;
	public ArrayList<Medicamento> getMedicamentos() throws RemoteException;
	public void setMedicamentos(ArrayList<Medicamento> medicamentos) throws RemoteException;
	public ArrayList<Integer> getQtd() throws RemoteException;
	public void setQtd(ArrayList<Integer> qtd) throws RemoteException;
	public Utente getUtente() throws RemoteException;
	public void setUtente(Utente utente) throws RemoteException;
	public Medico getMedico() throws RemoteException;
	public void setMedico(Medico medico) throws RemoteException;
	public boolean isAviado() throws RemoteException;
	public void setAviado(boolean aviado) throws RemoteException;
	
}
