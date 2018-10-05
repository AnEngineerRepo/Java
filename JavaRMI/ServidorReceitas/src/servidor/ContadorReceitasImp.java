package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.*;

@SuppressWarnings("serial")
public class ContadorReceitasImp extends UnicastRemoteObject implements ContadorReceitas {
	
	private int c=0;
	
	public ContadorReceitasImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String contador_receitas() throws RemoteException {
		c++;
		String contador=Integer.toString(c);
		return contador;
	}
}