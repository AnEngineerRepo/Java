package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestorFarmacia extends Remote {
	boolean addFarmacia(String nome, String morada, String cp) throws RemoteException;	
	boolean addMedicamento(String nome_generico, String nome, String forma, String dosagem, String autorizacao,
			String generico, String titular, int qtd) throws RemoteException;
	boolean AtualizaStock(String cod,int qtd_adicional) throws RemoteException;
	int NumeroMedicamentos() throws RemoteException;
	double ReceitasporFarmacia() throws RemoteException;
	double UtentesporFarmacia() throws RemoteException;
}
