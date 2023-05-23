package Control;

import java.io.IOException;
import java.util.ArrayList;
import Model.Notizia;
public class CalcoloAttendibilitàNotizia {

	
public ArrayList<Notizia> calcoloAttendibilitàNotizia(String testo) {
	ArrayList<Notizia> risultati = new ArrayList<>();
	RicercaMultimediale ric = new RicercaMultimediale();
	
	//Recupero informazioni principali e controlli vari
	
	
	//chiamo la classe Ricerca ed eseguo la ricerca su web
	try {
		risultati = ric.ricercaNotizia(testo);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.err.println("Error on scraping");
	}
	//Controllo che ci sono risultati
	if (risultati.isEmpty()) 
	{
		System.out.println("Nessuna notizia trovata");
	//	risultati.add("Nessun risultato");
	
	}
	return risultati;
}

}