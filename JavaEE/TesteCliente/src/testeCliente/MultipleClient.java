package testeCliente;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import sessions.CarregaBaseDados;
import sessions.MedicoOp;

/*
Esta classe MultipleClient permite que várias receitas sejam prescritas em simultaneo, ou seja, 
implementa o controlo de concorrência.
 */

/*
 Para cada uma das 10threads criadas, o método AddReceita vai ser percorrido 5 vezes, permitindo assim a inserção de 50 receitas na sua base de dados.
 */
class teste implements Runnable{
	private static final int iter=5;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("starting Thread..");
			for (int i=0;i<iter;i++){
				addReceita();
			}
			System.out.println("Thread Check!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addReceita(){
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			MedicoOp m = (MedicoOp)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.MedicoOp");
			ArrayList<String> meds = new ArrayList<String>();
			ArrayList<Integer> qtd = new ArrayList<Integer>();
			meds.add("4");	qtd.add(2);
			meds.add("7");  qtd.add(3);
			m.addReceita("19906401","3273117",meds,qtd);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/*
A base de dados carrega e são criadas 10 threads que representam 10 clientes.  
 */

public class MultipleClient {
	static final int NT=10;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loading();
		for (int i=0;i<NT;i++){
			teste c=new teste();
			Thread t=new Thread(c);
			t.start();
		}
		
	}

		public static void loading(){
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("Loading Db...");
			CarregaBaseDados db = (CarregaBaseDados)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.CarregaBaseDados");
			db.Carrega();
			System.out.println("Succeed!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}




