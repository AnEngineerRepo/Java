package testeCliente;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import sessions.CarregaBaseDados;
import sessions.FarmaciaOp;
import sessions.GestorOp;
import sessions.MedicoOp;
import sessions.EstatisticasSist;


public class Main {

	public static void main(String[] args) {
		loading();
		addReceita();
		getMedicamentos();
		getReceitas();
		getReceitasPorAviar();
		atualizarStock();
		AviarReceita();
		getReceitasPorAviar();
		
		
		//Estatísticas
		
		receitasAno();
	}

	public static void loading(){
		InitialContext ctx;
		try {	
			ctx = new InitialContext();
			System.out.println("start loading DB");
			CarregaBaseDados db = (CarregaBaseDados)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.CarregaBaseDados");
			db.Carrega();
			System.out.println("check DB!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		public static void addReceita(){
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("start adicionar Receita");
			MedicoOp m = (MedicoOp)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.MedicoOp");
			ArrayList<String> meds = new ArrayList<String>();
			ArrayList<Integer> qtd = new ArrayList<Integer>();
			meds.add("4");	qtd.add(2);
			meds.add("7");  qtd.add(3);
			m.addReceita("19906401","3273117",meds,qtd);
			System.out.println("Succeed!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getMedicamentos(){
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("start getMedicamentos de Utente");
			MedicoOp m = (MedicoOp)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.MedicoOp");
			System.out.println(m.DadosMedicUtente("3273117"));
			System.out.println("succeed!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getReceitas(){
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("start getReceitas de Utente");
			MedicoOp m = (MedicoOp)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.MedicoOp");
			System.out.println(m.DadosRecUtente("3273117"));
			System.out.println("succeed!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void atualizarStock(){
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("start atualizarStock de Medicamento 4 em 5 unidades");
			GestorOp m = (GestorOp)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.GestorOp");
			System.out.println(m.atualizaStock("4",5));
			System.out.println("succeed!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getReceitasPorAviar(){
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("start getReceitasPorAviar de Utente");
			FarmaciaOp f = (FarmaciaOp)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.FarmaciaOp");
			System.out.println(f.ReceitasparaAviar("3273117"));
			System.out.println("succeed!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void AviarReceita(){
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("start AviarReceita do Utente");
			FarmaciaOp f = (FarmaciaOp)ctx.lookup("java:global/EntidadesEAR/Entidades/Factory!sessions.FarmaciaOp");
			System.out.println(f.AviarReceita("39","F100"));
			System.out.println("succeed!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Estatísticas
	
	private static void receitasAno() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("Statistics: Receitas no ano 2015");
			EstatisticasSist f = (EstatisticasSist)ctx.lookup("java:global/EntidadesEAR/Entidades/Estatisticas!sessions.EstatisticasSist");
			System.out.println(f.ReceitasporAno("2015"));
			System.out.println("succeed!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		


}
	
	