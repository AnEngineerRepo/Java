package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContadorMedicamentos extends Remote{
	String contador_medicamentos() throws RemoteException;
}
