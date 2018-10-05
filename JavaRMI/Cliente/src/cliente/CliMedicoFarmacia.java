package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import interfaces.FarmaciaOp;
import interfaces.MedicoOp;
import interfaces.Receita;
import interfaces.Utente;

public class CliMedicoFarmacia{
	
	public static void main(String[] args) throws RemoteException, InterruptedException {
		
	if (adicionarReceita()){
		System.out.println("A receita foi adicionada com sucesso.");
	} else {
		System.out.println("Não foi possível adicionar a receita.");
	}
	DadosUtente();

	if (adicionarReceita()){
		System.out.println("A receita foi adicionada com sucesso.");
	} else {
		System.out.println("Não foi possível adicionar a receita.");
	}
	
	GetReceitasUtente();
	Receita r=GetReceitaUtente();
	System.out.println(Aviar(r));
	GetReceitaUtente();
	System.out.println(Aviar(r));
	System.out.println(Aviar(r));
	}

	
		public static boolean adicionarReceita(){
			boolean sucess=false;
			MedicoOp m;
			System.setSecurityManager(new SecurityManager());
			try {
				m=(MedicoOp) Naming.lookup("rmi://localhost/medico");			
				ArrayList<String> lista = new ArrayList<>(); 
				lista.add("1002"); //Adicionam-se dois medicamentos.
				lista.add("1003");
				ArrayList<Integer> qtd = new ArrayList<>();
				qtd.add(3); //Quantidades para os respetivos medicamentos.
				qtd.add(6);
				String u="14325788"; //Joaquim Resende
				String med="19466766"; //Paulo Gama
				sucess=m.addreceita(lista,qtd,u,med);
			} catch (RemoteException | MalformedURLException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return sucess;
			}
			
			public static Utente DadosUtente(){
				MedicoOp m;
				System.setSecurityManager(new SecurityManager());
				Utente u = null;
				try {
					m=(MedicoOp) Naming.lookup("rmi://localhost/medico");		
					u = m.verDadosUtente("14325788");
					System.out.println("O nome do utente é " + u.getNome() +" e o seu código é "
							+ "" + u.getReceitas().get(0).getCod() + ".");			
				} catch (RemoteException | MalformedURLException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return u;
			}

			public static HashMap<String,Receita> GetReceitasUtente(){
				HashMap<String,Receita> receita=new HashMap<>();
				FarmaciaOp f;
				System.setSecurityManager(new SecurityManager());
				try {
					f=(FarmaciaOp) Naming.lookup("rmi://localhost/farmacia");			
					receita=f.ReceitasPorUtente("14325788");
					System.out.println("O médico é " + receita.get("1").getMedico().getNome());
					System.out.println("O utente é " + receita.get("2").getUtente().getNome());
				} catch (RemoteException | MalformedURLException | NotBoundException e) {
					e.printStackTrace();
				}
				return receita;
				
			}	
			public static Receita GetReceitaUtente(){
				Receita receita = null;
				FarmaciaOp f;
				System.setSecurityManager(new SecurityManager());
				try {
					f=(FarmaciaOp) Naming.lookup("rmi://localhost/farmacia");			
					receita=f.ReceitaPorCod("2");
					System.out.println("A receita tem o código " + receita.getCod());
					System.out.println("A receita já foi aviada?  " + receita.isAviado());
				} catch (RemoteException | MalformedURLException | NotBoundException e) {
					e.printStackTrace();
				}
				return receita;
			}	
			public static boolean Aviar(Receita r) throws InterruptedException{
				boolean receita = false;
				FarmaciaOp f;
				System.setSecurityManager(new SecurityManager());
				try {
					f=(FarmaciaOp) Naming.lookup("rmi://localhost/farmacia");			
					receita=f.AviarReceita("farmacia1",r);
					System.out.println("Conseguiu-se aviar a receita?" +receita);
				} catch (RemoteException | MalformedURLException | NotBoundException e) {
					e.printStackTrace();
				}
				return receita;
				
			}	
			
}

