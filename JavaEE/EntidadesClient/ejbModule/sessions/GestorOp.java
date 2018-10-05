package sessions;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface GestorOp {
	public boolean addUtente(String bi,String nome,String nif,String morada,String cp);
	public boolean addMedico(String bi,String nome,String nif,String morada,String cp);
	public boolean addMedicamento(String nome_gen, String nome, String forma,
			String dosagem, String autorizacao, String generico, String titular,
			Integer qtd_stock);
	public boolean addFarmacia(String id, String nome, String morada, String cp);
	
	
	public boolean atualizaStock(String id_medicamento,Integer qtd_atualizada );
	boolean addReceita(String bi_medico, String bi_utente, ArrayList<String> medicamentos, ArrayList<Integer> qtd);
	
}
