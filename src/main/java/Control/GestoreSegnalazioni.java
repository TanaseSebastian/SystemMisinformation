package Control;

import java.util.ArrayList;

import Model.Utente;
import Model.Segnalazione;

/**
 * Classe che si occupa di effettuare il login tra i vari utenti nel sistema
 */
public class GestoreSegnalazioni {

	public GestoreSegnalazioni() {
		
	}
	
	public Segnalazione retrieveSegnalazionebyId(String id) throws Exception {
		DBManager db = new DBManager();
		Segnalazione s = db.getSegnalazionebyId(id);
		
		return s;
	}
	
	
	
	

}
