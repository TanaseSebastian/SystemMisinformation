package Control;

import Model.Utente;

/**
 * Classe che si occupa di effettuare il login tra i vari utenti nel sistema
 */
public class ControllerUtente {

	public ControllerUtente() {
		
	}
	
	/**
	 * Effettua il login, risultato:
	 * 1 = utente trovato
	 * 0 = utente non trovato
	 * @throws Exception 
	 */
	public Utente effettuaLogin(String username, String pw)
	{
		Utente user = null;
		DBManager db;
		try {
			db = new DBManager();
			user = db.trovaUtente(username, pw);
			db.ChiudiDB();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	/**
	 * 
	 * @param user
	 * @return 1: operazione andata a buon fine
	 * @return 0: username /email già inserito
	 * @return -1: errore nel db
	 */
	public int registraUtente(Utente user) {

		DBManager db = null;
		Utente userFromDB = null;
		//Cerco un utente che abbia lo stesso username
		try {
			db = new DBManager();
			userFromDB = db.controllaUtenteEsistente(user.getNome(), user.getEmail());

		} catch (Exception e)
		{

			System.out.println(e.getMessage());
		}	
		//se l'ha trovato
		if(userFromDB != null) {
			// se username uguali, non inserisce
			if(userFromDB.getNome() == user.getNome() || userFromDB.getEmail() == user.getEmail() )
			{
				return 0;
			}
			//se le precedenti operazioni sono risultate false, inserisco l'utente nel db				
		}
		else {
			try
			{
				//inserimento utente nel db
				int righe = db.inserisciUtente(user);
				db.ChiudiDB();
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());

				return -1;
			}
		}
		
		return 1;

	}
	
	public String convertiRuoloInString(int ruolo) {
		String[] ruoli= {"utente","moderatore","amministratore"};
		
		//controlla che il ruolo sia nel range giusto
		if(ruolo > ruoli.length || ruolo < 0 )
		{
			System.err.println("Index ruolo out of bounds");
			return "error-on-rules";
		}
		
		return ruoli[ruolo];

	}
}
