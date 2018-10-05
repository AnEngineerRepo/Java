package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//INTERFACE MEDICO-SISTEMA
public interface MedicoOp extends Remote{
	boolean  addreceita(ArrayList<String> meds, ArrayList<Integer> qtd, String u, String m) throws RemoteException;
	Utente verDadosUtente(String bi) throws RemoteException;
}
