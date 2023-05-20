package Control;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerFakeFight {

	
public ArrayList<String> calcoloAttendibilitàNotizia(String testo) {
	ArrayList<String> risultati = new ArrayList<>();
	Ricerca ric = new Ricerca();
	
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
		risultati.add("Nessun risultato");
	
	}
	return risultati;
}
}