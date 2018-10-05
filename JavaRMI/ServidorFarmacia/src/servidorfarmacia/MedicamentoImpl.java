package servidorfarmacia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import interfaces.Medicamento;
import interfaces.Medico;
import interfaces.Receita;
import interfaces.Utente;

@SuppressWarnings("serial")
public class MedicamentoImpl extends UnicastRemoteObject implements Medicamento{
	private String cod;
	private String nome_generico;
	private String nome;
	private String forma;
	private String dosagem;
	private String autorizacao;
	private String generico;
	private String titular;
	private int qtd;
	private ArrayList<Utente> utentes;
	private ArrayList<Medico> medicos;
	private ArrayList<Receita> receitas;
	
	public MedicamentoImpl() throws RemoteException{
	
	}
	
	
	public MedicamentoImpl(String cod, String nome_generico, String nome, String forma, String dosagem, String autorizacao,
			String generico, String titular, int qtd) throws RemoteException{
	
		this.cod = cod;
		this.nome_generico = nome_generico;
		this.nome = nome;
		this.forma = forma;
		this.dosagem = dosagem;
		this.autorizacao = autorizacao;
		this.generico = generico;
		this.titular = titular;
		this.qtd = qtd;
	}
	public MedicamentoImpl(String cod, String nome_generico, String nome, String forma, String dosagem, String autorizacao,
			String generico, String titular, ArrayList<Utente> utentes, ArrayList<Medico> medicos,
			ArrayList<Receita> receitas) throws RemoteException {

		this.cod = cod;
		this.nome_generico = nome_generico;
		this.nome = nome;
		this.forma = forma;
		this.dosagem = dosagem;
		this.autorizacao = autorizacao;
		this.generico = generico;
		this.titular = titular;
		this.utentes = utentes;
		this.medicos = medicos;
		this.receitas = receitas;
	}
	public String getCod() throws RemoteException{
		return cod;
	}
	public void setCod(String cod) throws RemoteException{
		this.cod = cod;
	}
	public String getNome_generico() throws RemoteException{
		return nome_generico;
	}
	public void setNome_generico(String nome_generico) throws RemoteException{
		this.nome_generico = nome_generico;
	}
	public String getNome() throws RemoteException{
		return nome;
	}
	public void setNome(String nome) throws RemoteException{
		this.nome = nome;
	}
	public String getForma() throws RemoteException{
		return forma;
	}
	public void setForma(String forma) throws RemoteException{
		this.forma = forma;
	}
	public String getDosagem() throws RemoteException{
		return dosagem;
	}
	public void setDosagem(String dosagem) throws RemoteException{
		this.dosagem = dosagem;
	}
	public String getAutorizacao() throws RemoteException{
		return autorizacao;
	}
	public void setAutorizacao(String autorizacao) throws RemoteException{
		this.autorizacao = autorizacao;
	}
	public String getGenerico() throws RemoteException{
		return generico;
	}
	public void setGenerico(String generico) throws RemoteException{
		this.generico = generico;
	}
	public String getTitular() throws RemoteException{
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public ArrayList<Utente> getUtentes() throws RemoteException{
		return utentes;
	}
	public void setUtentes(ArrayList<Utente> utentes) {
		this.utentes = utentes;
	}
	public ArrayList<Medico> getMedicos() throws RemoteException{
		return medicos;
	}
	public void setMedicos(ArrayList<Medico> medicos) {
		this.medicos = medicos;
	}
	public ArrayList<Receita> getReceitas() throws RemoteException{
		return receitas;
	}
	public void setReceitas(ArrayList<Receita> receitas) {
		this.receitas = receitas;
	}


	@Override
	public void AddMedico(Medico m) throws RemoteException {
		this.medicos.add(m);
		// TODO Auto-generated method stub
		
	}


	@Override
	public void AddReceita(Receita r) throws RemoteException {
		// TODO Auto-generated method stub
		this.receitas.add(r);
	}


	@Override
	public void AddUtente(Utente u) throws RemoteException {
		// TODO Auto-generated method stub
		this.utentes.add(u);
	}
}
