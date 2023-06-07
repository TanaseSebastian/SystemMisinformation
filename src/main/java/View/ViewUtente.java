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

import Control.CalcoloAttendibilitàNotizia;
import Control.GestoreAutenticazione;
import Control.GestoreFonti;
import Control.DBManager;
import Model.Segnalazione;
import Model.Utente;
import util.LogoutController;
import Model.Fonte;
import Model.Notizia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * Servlet implementation class GestoreUtente
 */
@WebServlet("/ViewUtente")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ViewUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreFonti gesF;
	private Fonte fonte;
	private Fonte[] valutazioneFonte;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewUtente() {
		super();
		gesF = new GestoreFonti();
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
			
			//recupero la notizia e la tipologia
			String tipoRicerca = request.getParameter("opzioniRicerca");
			String notizia = request.getParameter("notizia");
			if(tipoRicerca.equals("testuale")) {
				//eseguo ricerca testuale
				CalcoloAttendibilitàNotizia cff = null;
				try {
					cff = new CalcoloAttendibilitàNotizia();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//faccio la ricerca
				ArrayList<Notizia> risultati = new ArrayList<>();
				try {
					Utente user;
					user= (Utente) request.getSession().getAttribute("utente");
					if(user == null) {
						user = new Utente();
						user.setUsername("guest");
					}
					/*else {
						user = (Utente) request.getAttribute("utente");
					}*/
					risultati = cff.calcoloAttendibilitàNotiziaTestuale(notizia,user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Notizia cercata = risultati.remove(0);
				request.getSession().setAttribute("notiziaCercata", cercata);
				request.getSession().setAttribute("risultatiNotizia", risultati);
				//reindirizzo 
				response.sendRedirect("RisultatiNews.jsp?page=1");				
			}
			else if (tipoRicerca.equals("url")) {
				
			}
			break;
		case "Verifica Fonte":
			//recupero la fonte inserita
			String nomefonte = request.getParameter("fonte");
			

			//creazione fonte
			fonte = new Fonte(nomefonte);
			//inizio elaborazione
			valutazioneFonte = gesF.elaboraValutazioneFonte(fonte);
			//System.out.println(fonte.toString());
			request.getSession().setAttribute("fonte-valutata", valutazioneFonte);
			response.sendRedirect("risultatoValutazioneFonte.jsp");
			

		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String username = request.getParameter("username");
		System.out.println("sono entrato nella view");

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
				request.getSession().setAttribute("username",username);
				request.getSession().setAttribute("ruolo",user.getRuolo());
				response.sendRedirect("index.jsp");

			}
			else 
			{
				// login error 1 = utente non trovato
				System.out.println("Non trovato");
				request.getSession().setAttribute("login-error", 1);
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
			case "inviaSegnalazione":
			try {
				richiestaInvioSegnalazione(request);
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
			case "Inserisci Utente":
				System.out.println("sono entrato nel metodo giusto");
				richiestaInserimentoUtente(request, response);
				break;
				
			case "Blocca Fonte":
				int idFonte = Integer.parseInt(request.getParameter("idFonteDaBloccare"));
				Utente user2 = (Utente)request.getSession().getAttribute("utente");
						
			DBManager db;
			try {
				db = new DBManager();
				String nome = db.getNomeFontebyId(idFonte);
				Fonte f = db.getFonteByName(nome);
			
				db.bloccaFontePerUtente(f, user2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("gestioneFontiUtente.jsp");
			break;
			
				
				
		      
		    }
		}
		


	


	private void richiestaInserimentoUtente(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String ruolo = request.getParameter("ruolo");

		Utente u = new Utente(username, email, password, Integer.parseInt(ruolo));
		System.out.println("sto inserendo l'utente nel db");
		DBManager db;
		try {
			db = new DBManager();
			db.inserisciUtente(u);
			response.sendRedirect("amministrazione.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("inserisciUtente.jsp");
		}
		System.out.println(u.toString());
		
	}

	public Utente richiestaLogin(HttpServletRequest request) {
		Utente user = null;
		String username = request.getParameter("username");
		String pw = request.getParameter("pw");
		GestoreAutenticazione log = new GestoreAutenticazione();
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
		GestoreAutenticazione reg = new GestoreAutenticazione();
		esito = reg.registraUtente(user);
		return esito;
	}



	public int richiestaInvioSegnalazione(HttpServletRequest request) throws Exception {
		int esito = 0;
		String mittente = (String) request.getParameter("mittente");
		System.out.println(mittente);
		String descrizione = (String) request.getParameter("descrizione");
		System.out.println(descrizione);
		String titolo = (String) request.getParameter("titolo");
		System.out.println(titolo);
		String fonte = (String) request.getParameter("fonteSegnalata");
		System.out.println(fonte);
		Segnalazione s = new Segnalazione();
		s.setIdFonteSegnalata(Integer.parseInt(fonte));
		s.setDescrizione(descrizione);
		s.setTitolo(titolo);
		s.setMittente(mittente);
		System.out.println("richiesta invio segnalazione: "+ s.toString());
		DBManager db = new DBManager();
		db.inserisciSegnalazione(s);
		System.out.println("inserimento segnalazione eseguito con successo");
		return esito;
	}
}
