package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestorReceitas extends Remote{
	boolean addMedico(String nome, String bi, String nif, String morada, String cp) throws RemoteException;
	boolean addUtente(String nome, String bi, String nif, String morada, String cp) throws RemoteException;
	int NumeroMedicos() throws RemoteException;
	int NumeroUtentes() throws RemoteException;
	int NumeroReceitas() throws RemoteException;
	int NumeroReceitasPorAno(String ano) throws RemoteException;
	double ReceitasporMedico() throws RemoteException;
	double ReceitasporUtente() throws RemoteException;
	double MedicosporUtente() throws RemoteException;
	double UtentesporMedico() throws RemoteException;
	
}
