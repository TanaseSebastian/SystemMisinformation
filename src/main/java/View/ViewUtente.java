package View;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Control.ControllerFakeFight;
import Control.ControllerUtente;
import Model.Segnalazione;
import Model.Utente;
import util.LogoutController;

/**
 * Servlet implementation class GestoreUtente
 */
@WebServlet("/ViewUtente")
public class ViewUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Prendo il comando dell'utente
		String userAction = request.getParameter("UserAction");
		switch(userAction) {
		case "Verifica Notizia":
			//recupero la notizia
			String notizia = request.getParameter("notizia");
			ControllerFakeFight cff = new ControllerFakeFight();
			//faccio la ricerca
			ArrayList<String> risultati = cff.calcoloAttendibilitàNotizia(notizia);
			request.getSession().setAttribute("risultatiNotizia", risultati);
			//reindirizzo 
			response.sendRedirect("index.jsp");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String username = request.getParameter("username");
		
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
				System.out.println("UTENTE TROVATO CON RUOLO: "+user.getRuolo());
				
			/*	if(user.getRuolo() == 1) {
					//l'utente è un utente standard per cui può vedere le sue segnalazioni e inserirne una
					System.out.println(user.toString());
					response.sendRedirect("segnalazioniUtente.jsp");
				}
				else if(user.getRuolo() == 2){
					//se l'utente è un moderatore lo mando sulla pagina delle segnalazioni
					System.out.println(user.toString());
					response.sendRedirect("segnalazioni.jsp");
				}
				*/
				System.out.println("METTO L'UTENTE IN SESSIONE");
			/*	request.getSession().setAttribute("isLogged", true);
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("ruolo", user.getRuolo());*/
				request.getSession().setAttribute("isLogged", true);
				request.getSession().setAttribute("utente",user);
				response.sendRedirect("index.jsp");
				
			}
			else 
			{
				System.out.println("Non trovato");
				response.sendRedirect("login.jsp");
			}
			break;
		case "Registra":
			//effettuo la registrazione e recupero l'esito
			int esito = richiestaReg(request); 
			System.out.println(esito);
			//reindirizzo in base all esito
			switch(esito) {
			case 1: //caso registrazione avvenuta
				System.out.println("registrato con successo");
				response.sendRedirect("index.jsp");
				break;
				
			case 0: //utente già inserito
				System.out.println();
				System.out.println("username già presente");
				response.sendRedirect("index.jsp");
				break;
			case -1: //errore nel db
				System.out.println();
				System.out.println("Errore nel db");
				response.sendRedirect("index.jsp");
				break;
			}
			break;
	
			
			
		

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
public int richiestaInvioSegnalazione(HttpServletRequest request) {
	int esito = 0;
	String username = (String) request.getSession().getAttribute("username");
	String fonte = (String) request.getAttribute("fonteSegnalata");
	String motivo = (String) request.getAttribute("motivo");
	return esito;
}
}
