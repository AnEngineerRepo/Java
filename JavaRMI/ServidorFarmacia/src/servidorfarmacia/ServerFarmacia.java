package servidorfarmacia;

import java.rmi.Naming;

import interfaces.BaseDadosFarmacia;
import interfaces.ContadorMedicamentos;
import interfaces.FarmaciaOp;
import interfaces.GestorFarmacia;


public class ServerFarmacia {
	
	
	public static void main(String[] argv) {
		try{
			System.setSecurityManager(new SecurityManager());
			
			ContadorMedicamentos c = new ContadorMedicamentosImp();
			Naming.rebind("rmi://localhost/contadorM",c);
			
			BaseDadosFarmacia fd = new GestaoFarmaciaImplementacao();
			Naming.rebind("rmi://localhost/databaseFarm",fd);
			fd.loadBaseDadosFarmacia();
			
			FarmaciaOp f = new GestaoFarmaciaImplementacao();
			Naming.rebind("rmi://localhost/farmacia",f);

			System.out.println("O servidor da farmácia está pronto.");
			
			GestorFarmacia g = new GestaoFarmaciaImplementacao();
			Naming.rebind("rmi://localhost/gestorS",g);
			
		}catch(Exception e){
			System.out.println("A ligação ao servidor não foi efetuada com sucesso: " + e);
		}

	}

}