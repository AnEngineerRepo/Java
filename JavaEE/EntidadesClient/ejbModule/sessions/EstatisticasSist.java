package sessions;

import java.util.Set;

import javax.ejb.Remote;

/*
Esta interface permite a obten�ao de diversos dados estat�sticos associados ao sistema.
 */

@Remote
public interface EstatisticasSist {
	
	public Integer ReceitasporAno(String ano); 
	public double MedicosporUtente();
	public double UtentesporFarmacia();
}

