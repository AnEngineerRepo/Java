package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContadorReceitas extends Remote{
		String contador_receitas() throws RemoteException;
}
