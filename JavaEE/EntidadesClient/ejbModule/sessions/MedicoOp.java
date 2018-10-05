package sessions;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.Remote;

/*Esta interface define as opera��es que o m�dico tem autoriza��o para efetuar no sistema
 Adicionar uma receita e analise das receitas e medicamentos associados a um determinado
 utente atraves da inser�ao do bi
 */

@Remote
public interface MedicoOp {
	public boolean addReceita(String bi_medico, String bi_utente, 
			ArrayList<String> medicamentos, ArrayList<Integer> qtd); 
	public Set<String> DadosRecUtente(String bi_utente); //obten�ao das receitas associados a um utente
	public Set<String> DadosMedicUtente(String bi_utente); //obten�ao dos medicamentos associados a um utente
}
