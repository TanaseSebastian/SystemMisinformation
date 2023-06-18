package View;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Control.*;
import Model.Segnalazione;
import Model.Utente;
import util.LogoutController;
import Model.Fonte;
import Model.Notizia;
/**
 * Servlet implementation class GestoreUtente
 */
@WebServlet("/viewmoderatori")
public class ViewModeratori extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewModeratori() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Prendo il comando dell'utente
		String userAction = request.getParameter("cmd");
		switch(userAction) {
		case "valida":
			//recupero la notizia
			String id_segnalazione = request.getParameter("id");
			System.out.println("faccio la retrieve della segnalazione con id:  "+ id_segnalazione);
			 GestoreSegnalazioni controller = new GestoreSegnalazioni();
			 try {
				 Segnalazione s = controller.retrieveSegnalazionebyId(id_segnalazione);
				 System.out.println("ho trovato la segnalazione: "+ s.toString());
				 request.getSession().setAttribute("segnalazione", s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			CalcoloAttendibilitàNotizia cff = new CalcoloAttendibilitàNotizia();
//			//faccio la ricerca
//			ArrayList<Notizia> risultati = cff.calcoloAttendibilitàNotizia(notizia);
//			request.getSession().setAttribute("risultatiNotizia", risultati);
			//reindirizzo 
			response.sendRedirect("validaSegnalazione.jsp");
			break;
		case "conferma e valida segnalazione":
			//mi prendo id segnalazione
			String id_segnalazione1 = request.getParameter("id");
			String nomeFonte = request.getParameter("fonte");
			//deve cambiare lo stato della segnalazione
			DBFonti db = null;
			try {
				db = new DBFonti();
				db.modificaStatoSegnalazione(Integer.parseInt(id_segnalazione1), 1);
				db.diminuisciIndiceFonte(nomeFonte);
				//rimandiamo su segnalazioni
				response.sendRedirect("segnalazioni.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "declina segnalazione":
			//mi prendo id segnalazione
			String id_segnalazione2 = request.getParameter("id");
			DBManager db2 = null;
			//deve cambiare lo stato della segnalazione
			try {
				db2 = new DBManager();
				db2.modificaStatoSegnalazione(Integer.parseInt(id_segnalazione2), 2);
				//rimandiamo su segnalazioni
				response.sendRedirect("segnalazioni.jsp");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//Prendo il comando dell'utente
				String userAction = request.getParameter("UserAction");
				System.out.println("la userAction mandata in post request: "+userAction);
				switch(userAction) {
				case "conferma e valida segnalazione":
					//mi prendo id segnalazione
					String id_segnalazione1 = request.getParameter("id");
					//deve cambiare lo stato della segnalazione
					DBManager db = null;
					try {
						db = new DBManager();
						db.modificaStatoSegnalazione(Integer.parseInt(id_segnalazione1), 1);
						//rimandiamo su segnalazioni
						response.sendRedirect("segnalazioni.jsp");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "declina segnalazione":
					//mi prendo id segnalazione
					String id_segnalazione2 = request.getParameter("id");
					//deve cambiare lo stato della segnalazione
					try {
						db = new DBManager();
						db.modificaStatoSegnalazione(Integer.parseInt(id_segnalazione2), 2);
						//rimandiamo su segnalazioni
						response.sendRedirect("segnalazioni.jsp");
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}



			}
}
