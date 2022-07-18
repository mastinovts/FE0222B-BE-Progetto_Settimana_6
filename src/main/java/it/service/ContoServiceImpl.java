package it.service;

import it.business.CCDAO;
import it.data.ContoCorrente;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class ContoServiceImpl
 */
@Stateless
@LocalBean
public class ContoServiceImpl implements ContoService {

    @EJB
    CCDAO contodao;
    public ContoServiceImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean controllaOperazione(String operazione, String numero, float quantita) {
		if(operazione.equals("preleva") && quantita > 0 && contodao.esiste(numero) && contodao.getContoCorrente(numero).getSaldo() > quantita) {
			return true;
		}
		else if(operazione.equals("versa") && quantita > 0 && contodao.esiste(numero)) {
			return true;
		}
		return false;
	}

	@Override
	public ContoCorrente getContoCorrente(String numero) {
		return contodao.getContoCorrente(numero);
	}

	@Override
	public boolean versa(String numero, float quantita) {
		if(controllaOperazione("versa", numero, quantita)) {
			contodao.versa(numero, quantita);
			return true;
		}
		return false;
	}

	@Override
	public boolean preleva(String numero, float quantita) {
		if(controllaOperazione("preleva", numero, quantita)) {
			contodao.preleva(numero, quantita);
			return true;
		}
		return false;
	}

}
