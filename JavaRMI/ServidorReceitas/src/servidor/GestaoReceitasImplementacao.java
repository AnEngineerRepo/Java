package servidor;

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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import exceptions.MedicamentoNaoExisteException;
import exceptions.MedicoJaExisteException;
import exceptions.MedicoNaoExisteException;
import exceptions.UtenteJaExisteException;
import exceptions.UtenteNaoExisteException;
import interfaces.BaseDadosFarmacia;
import interfaces.BaseDadosReceitas;
import interfaces.ContadorReceitas;
import interfaces.Farmacia;
import interfaces.GestorReceitas;
import interfaces.Medicamento;
import interfaces.Medico;
import interfaces.MedicoOp;
import interfaces.Receita;
import interfaces.Utente;

@SuppressWarnings("serial")
public class GestaoReceitasImplementacao extends UnicastRemoteObject implements BaseDadosReceitas,MedicoOp,GestorReceitas,Serializable {
	
	
	static  HashMap<String,Utente> utentes = new HashMap<>();
	static HashMap<String,Medico> medicos = new HashMap<>();
	static  HashMap<String,Receita> receitas = new HashMap<>();
	static HashMap<String,Medicamento> medicamentos = new HashMap<>();
	//private int count_r=1000;

	protected GestaoReceitasImplementacao() throws RemoteException {
		super();
	}

	
	//FUNÇÃO QUE LÈ OS UTENTES E MÉDICOS DE UM FICHEIRO E DISPONIBILIZA-OS
	@Override
	public void loadBaseDadosReceitas() throws RemoteException {
		BufferedReader br = null;
		String line = "";
		String SplitBy = ":";
		String[] aux;
		

		try {
		
			br=new BufferedReader(new FileReader("C:/Users/joaov/Desktop/Eclipse-AD-Teste/utentesf.txt"));
			while ((line = br.readLine()) != null) {
				aux = line.split(SplitBy);
				Utente u = new UtenteImpl(aux[0],aux[1],aux[2],aux[3],aux[4],
						new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
				utentes.put(u.getBi(), u);
			}
			br.close();		
		
			br=new BufferedReader(new FileReader("C:/Users/joaov/Desktop/Eclipse-AD-Teste/medicosf.txt"));
			while ((line = br.readLine()) != null) {
				aux = line.split(SplitBy);
				Medico m = new MedicoImpl(aux[0],aux[1],aux[2],aux[3],aux[4],
						new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
				medicos.put(m.getBi(), m);
			}
			br.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}	
	
	//ACRESCENTA UMA CHAVE GERADA PELO CONTADOR AO HASHMAP DOS RECEITAS
	public String gen_cod(){
		String codigo="";
		ContadorReceitas c;
		System.setSecurityManager(new SecurityManager());
		try {
			c=(ContadorReceitas) Naming.lookup("rmi://localhost/contador");
			codigo=c.contador_receitas();
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			
			//e.printStackTrace();
		}
		return codigo;
	}
	public void Atualizacao(Receita r) throws RemoteException{
		//TANTO O MÉDICO COMO O UTENTE VÃO TER UMA NOVA RECEITA
		String bi_u=r.getUtente().getBi();
		String bi_m=r.getMedico().getBi();

		utentes.get(bi_u).addReceita(r);
		medicos.get(bi_m).addReceita(r);
		
		

		
		//O UTENTE TEM UM NOVO MÉDICO ASSOCIADO
		if(utentes.get(bi_u).getMedicos().isEmpty()){
			utentes.get(bi_u).addMedico(r.getMedico());
		}else{
			boolean jaexiste=false;
			for(Medico m2:utentes.get(bi_u).getMedicos()){
				if (bi_m==m2.getBi()){
					jaexiste=true;
					break;
					}
			}
			if(jaexiste==false){
				utentes.get(bi_u).addMedico(r.getMedico());
			}
		}
		
		//O MÉDICO TEM UM NOVO UTENTE
		if(medicos.get(bi_m).getUtentes().isEmpty()){
			medicos.get(bi_m).addUtente(r.getUtente());
		}else{
			boolean jaexiste=false;
			for(Utente u2:medicos.get(bi_m).getUtentes()){
				if (bi_u==u2.getBi()){
					jaexiste=true;
					break;
					}
			}
			if(jaexiste==false){
				medicos.get(bi_m).addUtente(r.getUtente());
			}
		}
//---		
		//O MEDICAMENTO VAI TER UM UTENTE ADICIONADO
		
		for(Medicamento med:r.getMedicamentos()){
			if(med.getUtentes().isEmpty()){
				med.AddUtente(r.getUtente());
			}else{
				boolean jaexiste=false;
				for(Utente u2:medicamentos.get(med.getCod()).getUtentes()){
					if (bi_u==u2.getBi()){
						jaexiste=true;
						break;
						}
				}
				if(jaexiste==false){
					med.AddUtente(r.getUtente());
				}
			}
		}
		
		//O MEDICAMENTO VAI ESTAR ASSOCIADO A MEDICOS
		
				for(Medicamento med:r.getMedicamentos()){
					if(med.getMedicos().isEmpty()){
						med.AddMedico(r.getMedico());
					}else{
						boolean jaexiste=false;
						for(Medico m2:medicamentos.get(med.getCod()).getMedicos()){
							if (bi_m==m2.getBi()){
								jaexiste=true;
								//break;
								}
						}
						if(jaexiste==false){
							med.AddMedico(r.getMedico());
						}
					}
				}
			//O MEDICAMENTO VAI TER MAIS RECEITA(S) ADICIONADAS
			for(Medicamento med:r.getMedicamentos()){
				med.AddReceita(r);
			}

		//O UTENTE VAI TER MEDICAMENTOS ASSOCIADOS
		if(utentes.get(bi_u).getMedicamentos().isEmpty()){
			for(Medicamento med:r.getMedicamentos()){
				utentes.get(bi_u).addMedicamento(med);
			}
		}else{
			boolean jaexiste;
			ArrayList<Medicamento> aux=utentes.get(bi_u).getMedicamentos();
			for(Medicamento med:r.getMedicamentos()){
				jaexiste=false;
				for(int i=0;i<1;i++){
					for(Medicamento med2:aux){
						if (med.getCod()==med2.getCod()){
							jaexiste=true;
							//break;
						}
					}
				}
				if(jaexiste==false){
					utentes.get(bi_u).addMedicamento(med);
				}
			}
		}
		
		//O MÉDICO VAI TER MEDICAMENTOS ASSOCIADOS
				if(medicos.get(bi_m).getMedicamentos().isEmpty()){
					for(Medicamento med:r.getMedicamentos()){
						medicos.get(bi_m).addMedicamento(med);
					}
				}else{
					boolean jaexiste;
					ArrayList<Medicamento> aux=medicos.get(bi_m).getMedicamentos();
					for(Medicamento med:r.getMedicamentos()){
						jaexiste=false;
						for(int i=0;i<1;i++){
							for(Medicamento med2:aux){
								if (med.getCod()==med2.getCod()){
									jaexiste=true;
									//break;
								}
							}
						}
						if(jaexiste==false){
							medicos.get(bi_m).addMedicamento(med);
						}
					}
				}
	}		
	@Override
	public synchronized  boolean addreceita(ArrayList<String> meds, ArrayList<Integer> qtd, String u, String m)
			throws RemoteException {
		boolean sucess=false;
		try {
			Medico medico = null;
			Utente utente = null;
			ArrayList<Medicamento> lista = new ArrayList<>();
			boolean fail=true;
			
		for(Utente ut:utentes.values()){
			if (ut.getBi().equals(u)){
			    utente=ut;
			    fail=false;
			    break;
			} 
		}		
		if (fail) throw new UtenteNaoExisteException();
				
		
		fail=true;
		for(Medico med:medicos.values()){
			if (med.getBi().equals(m)){
				medico=med;
				fail=false;
				break;
			}
		}
		if(fail) throw new MedicoNaoExisteException();
				
		fail=true;
		
		BaseDadosFarmacia bd_farm;
		System.setSecurityManager(new SecurityManager());
		
		bd_farm=(BaseDadosFarmacia) Naming.lookup("rmi://localhost/databaseFarm");			
		medicamentos=bd_farm.GetStock();
		for(int i=0;i<meds.size();i++){
			fail=true;
			for(Medicamento mdt:medicamentos.values()){
				if (mdt.getCod().equals(meds.get(i))){
					lista.add(mdt);
					fail=false;
					break;
				}
					
			}
			if (fail) throw new MedicamentoNaoExisteException();
			
		}
		//INSERE A RECEITA NOVA NAS RECEITAS QUE JÁ EXISTEM
		Calendar d=  new GregorianCalendar();
		Receita r = new ReceitaImpl(gen_cod(),lista,qtd,utente,medico,d,false);
		receitas.put(r.getCod(), r);
		Atualizacao(r);
		sucess=true;
		
		}catch (MalformedURLException | NotBoundException | UtenteNaoExisteException | MedicamentoNaoExisteException | MedicoNaoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return sucess;
	}
	//PERMITE CONSULTAR/VISUALIZAR OS DADOS DE UM UTENTE PELO SEU NÚMERO DE CC
	@Override
	public Utente verDadosUtente(String bi) throws RemoteException {
		try {
		if (utentes.get(bi)!=null){ return utentes.get(bi);}
		else{
			throw new UtenteNaoExisteException();
		}} catch (UtenteNaoExisteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ADICIONA MÉDICO (COM CERTOS ATRIBUTOS) AO SISTEMA (CASO NÃO SEJA IGUAL A UM MÉDICO JA INSERIDO)
	@Override
	public synchronized boolean addMedico(String nome, String bi, String nif, String morada, String cp) throws RemoteException {
		boolean sucess=false;
		boolean jaexiste=false;
		for(Medico med:medicos.values()){
			if (med.getBi().equals(bi)){
				jaexiste=true;
				break;
				}
		}
		try{
		if(jaexiste==false){
			Medico m = new MedicoImpl(nome,bi,nif,morada,cp,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
			medicos.put(m.getBi(), m);
			sucess=true;
			
		}else throw new MedicoJaExisteException();	
		}catch (MedicoJaExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sucess;
	}
	// ADICIONA UTENTE (COM CERTOS ATRIBUTOS) AO SISTEMA (CASO NÃO SEJA IGUAL A UM UTENTE JA INSERIDO)
	@Override
	public synchronized boolean addUtente(String nome, String bi, String nif, String morada, String cp) throws RemoteException {
		boolean sucess=false;
		boolean jaexiste=false;
		for(Utente u:utentes.values()){
			if (u.getBi().equals(bi)){
				jaexiste=true;
				break;
				}
		}
		try{
		if(jaexiste==false){
			Utente ut = new UtenteImpl(nome,bi,nif,morada,cp,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
			utentes.put(ut.getBi(), ut);
			sucess=true;
			
		}else throw new UtenteJaExisteException();	
		}catch (UtenteJaExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return sucess;
	}	
	// MÉTODO QUE RETORNA AS RECEITAS POR AVIAR
	@Override
	public synchronized HashMap<String, Receita> getReceitas(String bi) throws RemoteException {
		HashMap<String,Receita> nova_receitas = new HashMap<>();
		for (Receita r:receitas.values()){
			if (r.getUtente().getBi().equalsIgnoreCase(bi) && r.isAviado()==false){
				nova_receitas.put(r.getCod(), r);
			}
		}
		return nova_receitas;
	}


	// RETORNA AS RECEITAS (TODAS E NÃO SÓ AS AVIADAS) PARA UM ID DE RECEITA
	@Override
	public Receita getReceita(String cod) throws RemoteException {
			if (receitas.get(cod)!=null){ 
				return receitas.get(cod);}
			else{
				return null;
			}
		}

	//DAQUI PARA BAIXO TODAS AS FUNÇÕES POSSUEM UTILIZADE ESTATÍSTICA

	@Override
	public int NumeroMedicos() throws RemoteException {
		// TODO Auto-generated method stub
		return medicos.size();
	}



	@Override
	public int NumeroUtentes() throws RemoteException {
		// TODO Auto-generated method stub
		return utentes.size();
	}



	@Override
	public int NumeroReceitas() throws RemoteException {
		// TODO Auto-generated method stub
		return receitas.size();
	}



	@Override
	public double ReceitasporMedico() throws RemoteException {
		int sum=0;
		int count=0;
		double result=0;
		for(Medico m:medicos.values()){
			sum=sum+ m.getReceitas().size();
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
	public double ReceitasporUtente() throws RemoteException {
		int sum=0;
		int count=0;
		double result=0;
		for(Utente u:utentes.values()){
			sum=sum+ u.getReceitas().size();
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
	public double MedicosporUtente() throws RemoteException {
		int sum=0;
		int count=0;
		double result=0;
		for(Utente u:utentes.values()){
			sum=sum+ u.getMedicos().size();
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
	public double UtentesporMedico() throws RemoteException {
		int sum=0;
		int count=0;
		double result=0;
		for(Medico m:medicos.values()){
			sum=sum+ m.getUtentes().size();
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
	public int NumeroReceitasPorAno(String ano) throws RemoteException {
		int count=0;
		for(Receita r:receitas.values()){
			if(r.getAno().equals(ano)){
				count++;
			}
		}
		return count;
	}
}
