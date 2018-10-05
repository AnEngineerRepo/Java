package sessions;

import java.util.Set;
import javax.ejb.Remote;

/*
Esta interface define o conjunto de operaçoes que uma farmacia pode executar no sistema.
 */

@Remote
public interface FarmaciaOp {
	
		public Set<String> receitasUtente(String bi_utente); //Verificar as receitas associadas a um dado utente.
		public Set<String> ReceitasparaAviar(String bi_utente); //Verificar as receitas por aviar
		public boolean AviarReceita(String id_receita, String id_farmacia); 
}
