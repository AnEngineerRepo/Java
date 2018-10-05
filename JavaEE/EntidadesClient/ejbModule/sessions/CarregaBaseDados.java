package sessions;

import java.util.ArrayList;

import javax.ejb.Remote;

/*Esta interface disponibiliza e inicializa remotamente os dados referentes aos médicos,
utentes,medicamentos e farmacias carregados atraves de ficheiros externos
*/

@Remote
public interface CarregaBaseDados {
	public void Carrega();

	boolean addReceita(String bi_medico, String bi_utente, ArrayList<String> medicamentos, ArrayList<Integer> qtd);
}
