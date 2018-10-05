package servidorfarmacia;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.FarmaciaJaExisteException;
import Exceptions.MedicamentoNaoExisteException;
import Exceptions.QuantidadeEmStockInsuficienteException;
import interfaces.BaseDadosFarmacia;
import interfaces.BaseDadosReceitas;
import interfaces.ContadorMedicamentos;
import interfaces.Farmacia;
import interfaces.FarmaciaOp;
import interfaces.GestorFarmacia;
import interfaces.Fornecedor;
import interfaces.Medicamento;
import interfaces.Receita;


@SuppressWarnings("serial")
public class GestaoFarmaciaImplementacao extends UnicastRemoteObject implements Fornecedor,FarmaciaOp,BaseDadosFarmacia,GestorFarmacia,Serializable{

	static HashMap<String,Medicamento> stock = new HashMap<>();
	static HashMap<String,Medicamento> stockView = new HashMap<>();
	static HashMap<String,Medicamento> stockFarm = new HashMap<>();
	static HashMap<String,Medicamento> stockFarmView = new HashMap<>();
	static HashMap<String,Farmacia> farmacias = new HashMap<>();
	
	protected GestaoFarmaciaImplementacao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	//ACRESCENTA UMA CHAVE GERADA PELO CONTADOR AO HASHMAP DOS MEDICAMENTOS
	public String gen_cod()throws RemoteException{
		String codigo="";
		ContadorMedicamentos c;
		System.setSecurityManager(new SecurityManager());
		try {
			c=(ContadorMedicamentos) Naming.lookup("rmi://localhost/contadorM");
			codigo=c.contador_medicamentos();
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			
			//e.printStackTrace();
		}
		return codigo;
	}
	//FUNÇÂO QUE LÊ OS MEDICAMENTOS DA FARMÁCIA
	@Override
	public void loadBaseDadosFarmacia() throws RemoteException {
		BufferedReader br = null;
		String line = "";
		String SplitBy = ";"; 
		String[] aux;
		try {
		br = new BufferedReader(new FileReader("C:/Users/joaov/Desktop/Eclipse-AD-Teste/Medicamentos.txt"));
		while ((line = br.readLine()) != null) {
			aux = line.split(SplitBy);
			String codigo=gen_cod();
			Medicamento med = new MedicamentoImpl(codigo,aux[0],aux[1],aux[2],aux[3],aux[4],aux[5],aux[6], 10);
			Medicamento medView = new MedicamentoImpl(codigo,aux[0],aux[1],aux[2],aux[3],aux[4],aux[5],aux[6]
					,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
			stock.put(med.getCod(), med);
			stockView.put(medView.getCod(), medView);
		}
		for(int i=0;i<10;i++){
			String a = Integer.toString(i);
			Farmacia f= new FarmaciaImpl("farmacia"+a,"Rua n"+a,"1234-123",new ArrayList<>(),new ArrayList<>());
			farmacias.put(f.getNome(), f);
			stockFarm=GetStockFarm();
			stockFarmView=stockView;
		}
		br.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public HashMap<String, Medicamento> GetStockFarm() throws RemoteException {
		return stock;
	}


	
	public HashMap<String, Medicamento> GetStock() throws RemoteException {
		return stockFarmView;
	}
	//RETORNA RECEITAS POR AVIAR DANDO UM NUMERO DE CC
	@Override
	public HashMap<String, Receita> ReceitasPorUtente(String bi) throws RemoteException {
		BaseDadosReceitas c;
		HashMap<String,Receita> lista=new HashMap<>();
		System.setSecurityManager(new SecurityManager());
		try {
			c=(BaseDadosReceitas) Naming.lookup("rmi://localhost/database");			
			lista=c.getReceitas(bi);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}	
	//RETORNA RECEITA A PARTIR DO SEU ID
	@Override
	public Receita ReceitaPorCod(String codigo) throws RemoteException {
		BaseDadosReceitas c;
		Receita r=null;
		System.setSecurityManager(new SecurityManager());
		try {
			c=(BaseDadosReceitas) Naming.lookup("rmi://localhost/database");			
			r=c.getReceita(codigo);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	//VERIFICA A CONDIÇÃO: STOCK DO MEDICAMENTO < QTD MEDICAMENTO NA RECEITA
	public boolean VerificarStock(Receita r) throws RemoteException{
		boolean succeed=true;
		for (int i=0;i<r.getMedicamentos().size();i++){
			String cod=r.getMedicamentos().get(i).getCod();
			if (r.getQtd().get(i) > stockFarm.get(cod).getQtd()){
				succeed=false;
			}
			
		}
		return succeed;
	}
	//AVIA A RECEITA OU NAO, MAS SE O FIZER ATUALIZA A QUANTIDADE DE MEDICAMENTO EM STOCK/ARMAZEM NA FARMACIA
	@Override
	public synchronized boolean AviarReceita(String farm,Receita r) throws RemoteException, InterruptedException {
		boolean success=false;
		String cod="";
		int qtd_i,result;
		boolean succeed=VerificarStock(r);
		try {
			if (farmacias.get(farm)!=null){
				//while(success=false){
					//synchronized(stockFarm){
						if (succeed){
							for (int i=0;i<r.getMedicamentos().size();i++){
								cod=r.getMedicamentos().get(i).getCod();
								qtd_i = stockFarm.get(cod).getQtd();
								
								result=qtd_i-r.getQtd().get(i);
								stockFarm.get(cod).setQtd(result);
								
							}
							r.setAviado(true);
							farmacias.get(farm).addReceitas_aviadas(r);
							farmacias.get(farm).addUtente(r.getUtente());
							success=true;
							
						} else
							throw new QuantidadeEmStockInsuficienteException();
							//stockFarm.wait();
				}
			//}	
					
				//}
				} catch (QuantidadeEmStockInsuficienteException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return success;
			}
	// ADICONA UMA FARMÁCIA (COM CERTOS ATRIBUTOS) AO SISTEMA 
	@Override
	public synchronized boolean addFarmacia(String nome, String morada, String cp) throws RemoteException {
		boolean success=false;
		try {
		if(farmacias.containsKey(nome)){
				throw new FarmaciaJaExisteException();
		}else{
			Farmacia f= new FarmaciaImpl(nome,morada,cp,new ArrayList<>(),new ArrayList<>());
			farmacias.put(nome, f);
			success=true;
		}
			} catch (FarmaciaJaExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return success;
	}

	// ADICIONA MEDICAMENTOS (COM CERTOS ATRIBUTOS) AO SISTEMA 
	@Override
	public synchronized boolean addMedicamento(String nome_generico, String nome, String forma, String dosagem,
			String autorizacao, String generico, String titular, int qtd) throws RemoteException {
		synchronized(stockFarm){
			String codigo = gen_cod();
			Medicamento m = new MedicamentoImpl(codigo,nome_generico,nome,forma,dosagem,autorizacao,
					generico,titular,qtd);
			Medicamento mview = new MedicamentoImpl(codigo,nome_generico,nome,forma,dosagem,autorizacao,
					generico,titular,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
			stockFarmView.put(codigo, mview);
			stockFarm.put(codigo, m);
			stockFarm.notifyAll();
			stockFarmView.notifyAll();
		}
		
		return true;
	}

	// ATUALIZA O STOCK DE MEDICAMENTOS DE UMA FARMÁCIA. PODE SER FEITO POR UM GESTOR DE FARMÁCIA OU POR UM FORNECEDOR 
	@Override
	public synchronized boolean AtualizaStock(String cod, int qtd_adicional) throws RemoteException {
		synchronized(stockFarm){
			try {
				if(stockFarm.containsKey(cod)){
					int result=stockFarm.get(cod).getQtd()+qtd_adicional;
					stockFarm.get(cod).setQtd(result);
					stockFarm.notifyAll();
					return true;
				}else{
						throw new MedicamentoNaoExisteException();}
					} catch (MedicamentoNaoExisteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		
			return false;
		}

	// DAQUI PARA BAIXO TODAS OS MÉTODOS POSSUEM UMA UTILIDADE ESTATÍSTICA
	@Override
	public synchronized double ReceitasporFarmacia() throws RemoteException {
		int sum=0;
		int count=0;
		double result=0;
		for(Farmacia f:farmacias.values()){
			sum=sum+ f.getReceitas_aviadas().size();
			count++;
		}
		if (count!=0){
			result=sum/count;
		}else{
			result=0;
		}
		return result;
	}

	@Override
	public synchronized double UtentesporFarmacia() throws RemoteException {
		int sum=0;
		int count=0;
		double result=0;
		for(Farmacia f:farmacias.values()){
			sum=sum+ f.getFichas_utente().size();
			count++;
		}
		if (count!=0){
			result=sum/count;
		}else{
			result=0;
		}
		return result;
	}


	@Override
	public int NumeroMedicamentos() throws RemoteException {
		return stockFarmView.size();
	}
}	