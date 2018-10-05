package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceFornecedor extends Remote{

	boolean AtualizaStock(String cod,int qtd_adicional) throws RemoteException;
}
