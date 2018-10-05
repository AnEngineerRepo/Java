package servidor;

import java.rmi.Naming;

import interfaces.BaseDadosReceitas;
import interfaces.ContadorReceitas;
import interfaces.GestorReceitas;
import interfaces.MedicoOp;


public class ServerReceitas {
	public static void main(String[] argv) {
		try{
			System.setSecurityManager(new SecurityManager());
			
			ContadorReceitas c = new ContadorReceitasImp();
			Naming.rebind("rmi://localhost/contador",c);
			
			BaseDadosReceitas b = new GestaoReceitasImplementacao();
			Naming.rebind("rmi://localhost/database",b);
			b.loadBaseDadosReceitas();
			MedicoOp m = new GestaoReceitasImplementacao();
			Naming.rebind("rmi://localhost/medico",m);
			
			GestorReceitas g = new GestaoReceitasImplementacao();
			Naming.rebind("rmi://localhost/gestorR",g);
			
			System.out.println("O servidor das receitas está pronto.");
			
			
		}catch(Exception e){
			System.out.println("Server failed: " + e);
		}

	}
}

