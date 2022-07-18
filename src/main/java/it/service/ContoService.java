package it.service;

import it.data.ContoCorrente;
import jakarta.ejb.Local;

@Local
public interface ContoService {
	boolean controllaOperazione(String operazione, String numero, float quantita);
	public ContoCorrente getContoCorrente(String numero);
	public boolean versa(String numero, float quantita);
	public boolean preleva(String numero, float quantita);
}
