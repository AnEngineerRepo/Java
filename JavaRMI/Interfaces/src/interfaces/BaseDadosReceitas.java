package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface BaseDadosReceitas extends Remote {
	
	public void loadBaseDadosReceitas() throws RemoteException;
	public HashMap<String,Receita> getReceitas(String bi) throws RemoteException;
	public Receita getReceita(String cod) throws RemoteException;

}
