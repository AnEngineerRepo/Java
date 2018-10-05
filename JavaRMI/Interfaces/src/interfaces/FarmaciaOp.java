package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

//INTERFACE ENTRE CLIENTE FARMACIA(FUNCIONARIO) 

public interface FarmaciaOp extends Remote{
	public HashMap<String,Receita> ReceitasPorUtente(String bi) throws RemoteException;
	public Receita ReceitaPorCod(String codigo) throws RemoteException;
	public boolean AviarReceita (String farm,Receita r) throws RemoteException, InterruptedException;
}