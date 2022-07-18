package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import it.data.ContoCorrente;
import it.service.ContoService;


public class ContoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static final String paramOp = "operazione";
    private static final String paramNum = "numero";
    private static final String paramInt = "intestatario";
    private static final String paramImp = "importo";
	
    
    @EJB
    ContoService service;
    public ContoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void errorRedirect(String msg, String destination, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("errorSession", msg);
		try {
			request.getServletContext().getRequestDispatcher(destination).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numeroCC = request.getParameter(paramNum);
		ContoCorrente CC = service.getContoCorrente(numeroCC);

		if (CC != null) {
			HttpSession session = request.getSession();
			session.setAttribute("saldoSession", CC);
			request.getServletContext().getRequestDispatcher("/visualizzasaldo.jsp").forward(request, response);
		} else {
			errorRedirect("Non esiste alcun conto corrente con questo numero, riprova.", "/operazionefallita.jsp",
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int operazione = Integer.parseInt(request.getParameter(paramOp));
		if (operazione == 0) {
			String numeroCC = request.getParameter(paramNum);
			float importo = Float.parseFloat(request.getParameter(paramImp));
			boolean opRiuscita = service.preleva(numeroCC, importo);

			if (!opRiuscita) {
				errorRedirect("Operazione di prelievo non riuscita, riprova", "/operazionefallita.jsp", request,
						response);
			} else {
				ContoCorrente CC = service.getContoCorrente(numeroCC);
				HttpSession session = request.getSession();
				session.setAttribute("operazioneRiuscitaSession", "Operazione riuscita");
				session.setAttribute("prelievoSession", CC);

				request.getServletContext().getRequestDispatcher("/prelievo.jsp").forward(request, response);
			}

		} else if (operazione == 1) {
			String numeroCC = request.getParameter(paramNum);
			float importo = Float.parseFloat(request.getParameter(paramImp));
			boolean opRiuscita = service.versa(numeroCC, importo);

			if (!opRiuscita) {
				errorRedirect("Operazione di versamento non riuscita, riprova", "/operazionefallita.jsp", request,
						response);
			} else {
				ContoCorrente CC = service.getContoCorrente(numeroCC);
				HttpSession session = request.getSession();
				session.setAttribute("versamentoSession", CC);

				request.getServletContext().getRequestDispatcher("/versamento.jsp").forward(request, response);
			}
		}
	}

}
