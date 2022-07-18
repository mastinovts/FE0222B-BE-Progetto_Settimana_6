package it.business;

import it.data.ContoCorrente;
import jakarta.ejb.Local;

@Local
public interface CCDAO {
	public boolean esiste(String numero);
	public ContoCorrente getContoCorrente(String numero);
	boolean versa(String numero, float quantita);
	public boolean preleva(String numero, float quantita);
	
}
