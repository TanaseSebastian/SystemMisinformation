package View;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Control.ControllerUtente;
import Model.Segnalazione;
import Model.Utente;

/**
 * Servlet implementation class GestoreUtente
 */
@WebServlet("/ViewSegnalazioni")
public class ViewSegnalazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSegnalazioni() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String username,email,pw;
		
		//Prendo il comando dell'utente
		String userAction = request.getParameter("UserAction");
		//System.out.println(userAction);
		
		switch(userAction) {
		case "login": 
			//Recupero campi inseriti
			Utente user = null;
			user = richiestaLogin(request);
			//se l'utente è stato trovato
			if( user != null) 
			{
				if(user.getRuolo() == 1) {
					//l'utente è un utente standard per cui può vedere le sue segnalazioni e inserirne una
					System.out.println(user.toString());
					request.getSession().setAttribute("isLogged", true);
					response.sendRedirect("index.jsp");
				}
				else if(user.getRuolo() == 2){
					//se l'utente è un moderatore lo mando sulla pagina delle segnalazioni
					System.out.println(user.toString());
					request.getSession().setAttribute("isLogged", true);
					response.sendRedirect("segnalazioni.jsp");
				}
				
			}
			else 
			{
				System.out.println("Non trovato");
			}
			break;
		case "Registra":
			int esito = richiestaReg(request); 
			System.out.println(esito);

		}
		
		
	}
public Utente richiestaLogin(HttpServletRequest request) {
	Utente user = null;
	String username = request.getParameter("username");
	String pw = request.getParameter("pw");
	ControllerUtente log = new ControllerUtente();
	try {
		//Invio dati inseriti alla classe che fa il login e ricevo l'utente corrispondente
		user = log.effettuaLogin(username, pw);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return user;
}




public int richiestaReg(HttpServletRequest request) {
	int esito = 0;
	//recupero dati dal form
	String username = request.getParameter("username");
	String pw = request.getParameter("pw");
	String email = request.getParameter("email");
	String ruolo = request.getParameter("ruolo");
	
	//creazione utente
	Utente user = new Utente(username, email, pw, Integer.parseInt(ruolo));
	ControllerUtente reg = new ControllerUtente();
	esito = reg.registraUtente(user);
	return esito;
}
}
