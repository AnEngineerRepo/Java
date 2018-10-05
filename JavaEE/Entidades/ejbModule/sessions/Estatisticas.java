package sessions;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Farmacia;
import entities.Medico;
import entities.Receita;
import entities.Utente;

/**
 * Session Bean implementation class StatisticSess
 */
@Stateless
@LocalBean
public class Estatisticas implements EstatisticasSist{

	 /**
     * @generated DT_ID=none
     */
	   @PersistenceContext(unitName="Entidades")
       private EntityManager em;
	   
    public Estatisticas() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public synchronized Integer ReceitasporAno(String ano) {
		int count=0;
		Query q = em.createNativeQuery("Select r.id from receita r");	
		List<String> receitas=q.getResultList();

		for(String w: receitas){
			Receita r= em.find(Receita.class, w);
			if (r.getDataAno().equals(ano)){
				count++;
			}
		}
		return count;
	}

	
	@Override
	public synchronized double MedicosporUtente() {
		int count=0;
		int sum=0;
		double result=0;
		Query q = em.createNativeQuery("Select u.bi from utente u");	
		List<String> utentes=q.getResultList();

		for(String w: utentes){
			Utente m= em.find(Utente.class, w);
			sum = sum + m.getMedicos().size();
			count++;
		}
		if(count!=0) result = sum/count;
		return result;
	}


	@Override
	public synchronized double UtentesporFarmacia() {
		int count=0;
		int sum=0;
		double result=0;
		Query q = em.createNativeQuery("Select f.id from farmacia f");	
		List<String> farm=q.getResultList();

		for(String w: farm){
			Farmacia f= em.find(Farmacia.class, w);
			sum = sum + f.getUtentes().size();
			count++;
		}
		if(count!=0) result = sum/count;
		return result;
	}

}
