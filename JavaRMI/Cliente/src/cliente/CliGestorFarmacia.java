package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import interfaces.GestorFarmacia;

public class CliGestorFarmacia {

	public static void main(String[] args) throws RemoteException, InterruptedException{
		
	System.out.println("Adicionei a Farm�cia? " + addFarmacia() + ".");
	
	}
	
	public static boolean addFarmacia(){
		boolean success=false;
		GestorFarmacia gs;
		System.setSecurityManager(new SecurityManager());
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduza o nome da farm�cia que pretende adicionar: ");
		String nomeFarm = scanner.nextLine(); 
		
		System.out.println("Introduza a morada da farm�cia: ");
		String moradaFarm = scanner.nextLine();
		
		System.out.println("Introduza o c�digo postal da farm�cia: ");
		String cpFarm = scanner.nextLine();
		
		scanner.close();
		
		try {
			gs=(GestorFarmacia) Naming.lookup("rmi://localhost/gestorS");			
			success= gs.addFarmacia(nomeFarm,moradaFarm,cpFarm);
			System.out.println("A farm�cia " +nomeFarm+ " foi adicionada com sucesso.");
			
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

}

