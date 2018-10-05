package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import interfaces.GestorReceitas;

public class CliGestorReceitas {

	public static void main(String[] args) throws RemoteException, InterruptedException{
		
		System.out.println("O médico foi adicionado com sucesso? " + adicionarMedico() + ".");
		
	}
	
				
	public static boolean adicionarMedico(){
		boolean sucess=false;
		GestorReceitas g;
		System.setSecurityManager(new SecurityManager());
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduza o nome do médico: ");
		String nome = scanner.nextLine(); 
		
		System.out.println("Introduza o número de identificação do médico: ");
		String bi = scanner.nextLine();
		
		System.out.println("Introduza o NIF: ");
		String nif = scanner.nextLine();
		
		System.out.println("Introduza a morada: ");
		String morada = scanner.nextLine();
		
		System.out.println("Introduza o código postal: ");
		String cp = scanner.nextLine();
		
		scanner.close();
		
		try {
			g=(GestorReceitas) Naming.lookup("rmi://localhost/gestorR");			
			sucess=g.addMedico(nome, bi, nif, morada, cp);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
		return sucess;
	}
	

}
