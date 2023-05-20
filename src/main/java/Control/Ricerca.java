package Control;
import java.net.*;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
public class Ricerca {
	
	public ArrayList<String> ricercaNotizia(String testo) throws IOException {
		ArrayList<String> risultati = new ArrayList<String>();
		String url = "\"https://www.bufale.net/?s=";
		
		//effettuo la ricerca
		Document doc = Jsoup.connect("https://www.bufale.net/?s=" + testo).get();
		
		//recupero gli elementi html contenuti nella div con la classe sopracitata
		 Elements factCheckCards = doc.getElementsByClass("main-post-archive");
         // Scansiona gli elementi trovati
         for (Element card : factCheckCards) {
             // Estrai le informazioni di interesse da ogni card
             String titolo = card.getElementsByClass("title").text();
             String descrizione = card.getElementsByClass("description hidden-xs hidden-sm").text();
             String publisher = card.getElementsByClass("author").text();
             // Stampa le informazioni
             System.out.println("Titolo: " + titolo);
             System.out.println("Affermazione: " + descrizione);
             System.out.println("Editore: " + publisher);
            // System.out.println("Data: " + date);
             System.out.println();
             
             //salvo i dati nel vettore e ritorno
             risultati.add(titolo);
             risultati.add(descrizione);
             risultati.add(publisher);
         }
		 
		return risultati;
	}
}
