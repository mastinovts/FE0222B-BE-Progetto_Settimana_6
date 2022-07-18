package it.business;

import it.data.ContoCorrente;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Session Bean implementation class CCDAOImpl
 */
@Stateless
@LocalBean
public class CCDAOImpl implements CCDAO {

	@PersistenceContext(unitName="ContoC")
	private EntityManager em;
	
    public CCDAOImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean esiste(String numero) {
		ContoCorrente contocorrente = getContoCorrente(numero);
		if(contocorrente != null) {
			return true;
		}return false;
	}

	@Override
	public ContoCorrente getContoCorrente(String numero) {
		return em.find(ContoCorrente.class, numero);
	}

	@Override
	public boolean versa(String numero, float quantita) {
		if(esiste(numero)) {
			ContoCorrente contoc = getContoCorrente(numero);
			contoc.setSaldo(contoc.getSaldo() + quantita);
			em.merge(contoc);
			return true;
		}return false;
	}

	@Override
	public boolean preleva(String numero, float quantita) {
		if(esiste(numero)) {
			ContoCorrente contoc = getContoCorrente(numero);
			if(contoc.getSaldo() > quantita) {
				contoc.setSaldo(contoc.getSaldo() - quantita);
				em.merge(contoc);
				return true;
			}
		}return false;
	}

	

	

}
