package servidorfarmacia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.*;

@SuppressWarnings("serial")
public class ContadorMedicamentosImp extends UnicastRemoteObject implements ContadorMedicamentos {
	
	private int c=1000;
	
	public ContadorMedicamentosImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String contador_medicamentos() throws RemoteException {
		c++;
		String contador=Integer.toString(c);
		return contador;
	}
}