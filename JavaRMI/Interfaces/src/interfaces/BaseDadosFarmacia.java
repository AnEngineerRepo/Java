package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface BaseDadosFarmacia extends Remote {
	public void loadBaseDadosFarmacia() throws RemoteException;
	public HashMap<String,Medicamento> GetStock() throws RemoteException;
}
