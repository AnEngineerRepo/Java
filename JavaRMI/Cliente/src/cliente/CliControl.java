/*package cliente;

import java.rmi.Naming;
import java.util.ArrayList;

import interfaces.MedicoOp;

class teste implements Runnable{
		private static final int iter=10;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				MedicoOp m;
				m=(MedicoOp) Naming.lookup("rmi://localhost/medico");
				ArrayList<String> lista = new ArrayList<>();
				lista.add("1002");
				lista.add("1003");
				ArrayList<Integer> qtd = new ArrayList<>();
				qtd.add(3);
				qtd.add(5);
				String u="14325788"; //Joaquim Resende
				String med="19466766"; //Paulo Gama
				for (int i=0;i<iter;i++){
					m.addreceita(lista, qtd, u, med);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public class CliControl {
		
		static final int NT=10;
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			System.setSecurityManager(new SecurityManager());
			for (int i=0;i<NT;i++){
				teste c=new teste();
				Thread t=new Thread(c);
				t.start();
			}
			
		}
	}

*/
	
package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import interfaces.GestorFarmacia;
import interfaces.MedicoOp;

class teste implements Runnable{
		@Override
		public void run() {
			GestorFarmacia gs;
			System.setSecurityManager(new SecurityManager());
			try {
				gs=(GestorFarmacia) Naming.lookup("rmi://localhost/gestorS");	
				int i;
				for (i=0;i<5;i++){
					gs.addFarmacia("","morada", "cp");
				}
			} catch (RemoteException | MalformedURLException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	public class CliControl {
		
		static final int NT=5;
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			System.setSecurityManager(new SecurityManager());
			for (int i=0;i<NT;i++){
				teste c=new teste();
				Thread t=new Thread(c);
				t.start();
				System.out.println(t);
			}
			
		}
	}