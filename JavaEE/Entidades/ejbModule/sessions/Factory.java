package sessions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Farmacia;
import entities.Medicamento;
import entities.Medico;
import entities.Receita;
import entities.Utente;
import exceptions.FarmaciaJaExisteException;
import exceptions.FarmaciaNaoExisteException;
import exceptions.MedicamentoNaoExisteException;
import exceptions.MedicoJaExisteException;
import exceptions.MedicoNaoExisteException;
import exceptions.QuantidadeInsuficienteException;
import exceptions.ReceitaJaAviada;
import exceptions.ReceitaNaoExisteException;
import exceptions.UtenteJaExisteException;
import exceptions.UtenteNaoExisteException;

/**
 * Session Bean implementation class GestorSess
 */
@Stateless
@LocalBean
public class Factory implements GestorOp,CarregaBaseDados,MedicoOp,FarmaciaOp {

	  /**
     * @generated DT_ID=none
     */
	   @PersistenceContext(unitName="Entidades")
       private EntityManager em;

	  public Factory(){
		  
	  }
	  
//-------------------------------------------------
	@Override
	public void Carrega() {
		BufferedReader br = null;
		String line = "";
		String SplitBy = ":"; // use : as separator
		String[] aux;
		
/*
É através do BufferedReader que é possível ler os ficheiros externos.
 */
		try {
		//Criar Utentes através das informações presentes no ficheiros externo.
			br=new BufferedReader(new FileReader("C:/FicheirosTP2/utentes.txt"));
			while ((line = br.readLine()) != null) {
				aux = line.split(SplitBy);
				addUtente(aux[1],aux[0],aux[2],aux[3],aux[4]);
			}
			br.close();		
		//Criar Medicos:
			br=new BufferedReader(new FileReader("C:/FicheirosTP2/medicos.txt"));
			while ((line = br.readLine()) != null) {
				aux = line.split(SplitBy);
				addMedico(aux[1],aux[0],aux[2],aux[3],aux[4]);
			}
			br.close();		
			//Criar Medicamentos
			String SplitBy2 = ";"; // use ; as separator
			br = new BufferedReader(new FileReader("C:/FicheirosTP2/medicamentos.txt"));
			while ((line = br.readLine()) != null) {
				aux = line.split(SplitBy2);
				addMedicamento(aux[0],aux[1],aux[2],aux[3],aux[4],aux[5],aux[6], 10);
			}
			br.close();	
			//Criar Farmacias:
			br=new BufferedReader(new FileReader("C:/FicheirosTP2/farmacias.txt"));
			while ((line = br.readLine()) != null) {
				aux = line.split(SplitBy);
				addFarmacia(aux[0],aux[1],aux[2],aux[3]);
			}
			br.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}	

	/*
	O synchronized permite o acesso de uma thread de cada vez ao serviço pretendido.
	 */
	@Override
	public synchronized boolean addUtente(String bi, String nome, String nif, String morada, String cp) {
		try {
		Utente ut = em.find(Utente.class, bi);
		if (ut!=null) throw new UtenteJaExisteException();
			
		Utente u = new Utente();
		u.setBi(bi);
		u.setNome(nome);
		u.setNif(nif);
		u.setMorada(morada);
		u.setCp(cp);
		em.persist(u);
		// TODO Auto-generated method stub
		return true;
			} catch (UtenteJaExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}

	@Override
	public synchronized boolean addMedico(String bi,String nome,String nif,String morada,String cp) {
		try {
		Medico me = em.find(Medico.class, bi);
		if (me!=null) throw new MedicoJaExisteException();
	
		Medico m=new Medico();
		m.setBi(bi);
		m.setNome(nome);
		m.setNif(nif);
		m.setMorada(morada);
		m.setCp(cp);
		em.persist(m);
		// TODO Auto-generated method stub
		return true;
		} catch (MedicoJaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public synchronized boolean addMedicamento(String nome_gen, String nome, String forma, String dosagem, String autorizacao,
			String generico, String titular, Integer qtd_stock) {
		
		Medicamento med = new Medicamento();
		med.setNome_gen(nome_gen);
		med.setNome(nome);
		med.setForma(forma);
		med.setDosagem(dosagem);
		med.setAutorizacao(autorizacao);
		med.setGenerico(generico);
		med.setTitular(titular);
		med.setQtd_stock(qtd_stock);
		em.persist(med);
		return true;
		
	}
	
	@Override
	public synchronized boolean addFarmacia(String id, String nome, String morada, String cp) {
		try {
			Farmacia farm = em.find(Farmacia.class, id);
			if (farm!=null) throw new FarmaciaJaExisteException();
		
		Farmacia f = new Farmacia();
		f.setId(id);
		f.setNome(nome);
		f.setMorada(morada);
		f.setCp(cp);
		em.persist(f);
		return true;
		} catch (FarmaciaJaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
		
/*
 Método que possibilita a criação de uma receita por parte do medico. Recebe como argumentos 
 o id do medico (medico q vai criar a receita), o id do utente (a quem vai ser prescrita a receita)
 um arraylist de medicamentos (lista de medicamentos que se vai receitar ao utente) e um
 arraylist com as quantidades que se pretende atribuir a cada medicamento presente no arraylist anterior.
 */
	@Override
	public synchronized boolean addReceita(String bi_medico, String bi_utente, ArrayList<String> medicamentos,
			ArrayList<Integer> qtd) {
		
/*
Faz-se primeiro a procura dos ids de cada entidade e caso nao existam na base de dados, são lançadas
exceções.
 */
		
		try{
		Utente u =em.find(Utente.class, bi_utente);
		if (u==null)	throw new UtenteNaoExisteException();
		
		Medico m=em.find(Medico.class, bi_medico);
		if (m==null)	throw new MedicoNaoExisteException();
		
/*
Criação de um HashSet Medicamento com o nome meds. Se o medicamento existir na base de dados,
o medicamento vai ser adicionado ao hashset. Caso contrario, é lançada a exceção.
 */
		
		Set<Medicamento> meds = new HashSet<Medicamento>();
		for (String s: medicamentos){
			Medicamento med=em.find(Medicamento.class, s);
			if(med==null){
				throw new MedicamentoNaoExisteException();	
			}else{
				meds.add(med);
			}
		}

/*
 Criação da receita. Preenchem-se as informações necessarias(utente, medico, data...).
 Inicialmento o valor booleano de aviado é falso e só será verdadeiro quando for levantado
 numa farmácia, tal como o campo da farmácia que se encontra nulo, sendo preenchido apenas 
 no acto do levantamento.
 */
		
		Receita r = new Receita();
		r.setMedico(m);
		r.setUtente(u);
		r.setMedicamentos(meds);
		r.setQuantidade(qtd);
		r.setAviado(false);
		r.setData(new GregorianCalendar());
		r.setFarmacia(null);
		em.persist(r);
		System.out.println(r.getDataString());
		
		u.getMedicos().add(m);
		u.getReceitas().add(r);
		m.getUtentes().add(u);
		m.getReceitas().add(r);
		
		for(Medicamento x:meds){
			u.getMedicamentos().add(x);
			m.getMedicamentos().add(x);
			x.getMedicos().add(m);
			x.getUtentes().add(u);
			x.getReceitas().add(r);
		}
		
		return true;
		} catch (MedicamentoNaoExisteException | UtenteNaoExisteException | MedicoNaoExisteException e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public synchronized Set<String> receitasUtente(String bi_utente) {
		Utente u =em.find(Utente.class, bi_utente);
		Set<String> receitas=new HashSet<String>();
		try {
		if (u==null)	throw new UtenteNaoExisteException();

		for (Receita r : u.getReceitas()){
			receitas.add(r.getId());
		}
		} catch (UtenteNaoExisteException e) {
			e.printStackTrace();
		}
		return receitas;
	}

	@Override
	public synchronized Set<String> DadosMedicUtente(String bi_utente) {
		Utente u =em.find(Utente.class, bi_utente);
		Set<String> medicamentos=new HashSet<String>();
		try {
		if (u==null)	throw new UtenteNaoExisteException();

		for (Medicamento med : u.getMedicamentos()){
			medicamentos.add(med.getNome());
		}
		} catch (UtenteNaoExisteException e) {
			e.printStackTrace();
		}
		return medicamentos;
	}

	@Override
	public synchronized boolean atualizaStock(String id_medicamento, Integer qtd_atualizada) {
		Medicamento med =em.find(Medicamento.class, id_medicamento);
		try {
		if (med==null){
			throw new MedicamentoNaoExisteException();
		}else{
			int result = med.getQtd_stock() + qtd_atualizada;
			med.setQtd_stock(result);
			return true;
		}
		
		
		} catch (MedicamentoNaoExisteException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public synchronized Set<String> ReceitasparaAviar(String bi_utente) {
		Utente u =em.find(Utente.class, bi_utente);
		Set<String> receitas=new HashSet<String>();
		try {
		if (u==null)	throw new UtenteNaoExisteException();

		for (Receita r : u.getReceitas()){
			if (r.getAviado()==false) receitas.add(r.getId());
		}
		} catch (UtenteNaoExisteException e) {
			e.printStackTrace();
		}
		return receitas;
	}

	@Override
	public synchronized boolean AviarReceita(String id_receita, String id_farmacia) {
		try {
		Receita r =em.find(Receita.class, id_receita);
		if (r==null) throw new ReceitaNaoExisteException();
		
		Farmacia f=em.find(Farmacia.class, id_farmacia);
		if (f==null) throw new FarmaciaNaoExisteException();
		
		if (r.getAviado()==true) throw new ReceitaJaAviada();
		
		Utente u = r.getUtente();
		ArrayList<Medicamento> lista = new ArrayList<Medicamento>(r.getMedicamentos());
		boolean verify=true;
		
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).getQtd_stock()<r.getQuantidade().get(i)){
				verify=false;
			}
		}
		if(verify){
		r.setAviado(true);
		
		for(int i=0;i<lista.size();i++){
			int resultado=lista.get(i).getQtd_stock() - r.getQuantidade().get(i);
			lista.get(i).setQtd_stock(resultado);
		}
		
		//Atualizar Fields
		r.setFarmacia(f);
		f.getReceitas_aviadas().add(r);
		f.getUtentes().add(u);
		u.getFarmacias().add(f);
		
			for (Medicamento med: lista){
				med.getFarmacias().add(f);
				f.getMedicamentos().add(med);
			}
		return true;
		}else{
			throw new QuantidadeInsuficienteException();
		}
		
		} catch (ReceitaNaoExisteException | ReceitaJaAviada | FarmaciaNaoExisteException | QuantidadeInsuficienteException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Set<String> DadosRecUtente(String bi_utente) {
		// TODO Auto-generated method stub
		return null;
	}
}
