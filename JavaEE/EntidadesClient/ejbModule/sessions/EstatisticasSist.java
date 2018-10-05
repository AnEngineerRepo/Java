package sessions;

import java.util.Set;

import javax.ejb.Remote;

/*
Esta interface permite a obtençao de diversos dados estatísticos associados ao sistema.
 */

@Remote
public interface EstatisticasSist {
	
	public Integer ReceitasporAno(String ano); 
	public double MedicosporUtente();
	public double UtentesporFarmacia();
}

