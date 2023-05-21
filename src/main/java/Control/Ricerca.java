package Control;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

import Model.Notizia;
public class Ricerca {
	
	public ArrayList<Notizia> ricercaNotizia(String testo) throws IOException {
		ArrayList<Notizia> risultati = new ArrayList<Notizia>();
		String url = "\"https://www.bufale.net/?s=";
		
		//effettuo la ricerca
		Document doc = Jsoup.connect("https://www.bufale.net/?s=" + testo).postDataCharset("UTF-8").get();
		//recupero gli elementi html contenuti nella div con la classe sopracitata
		 Elements factCheckCards = doc.getElementsByClass("main-post-archive");
         // Scansiona gli elementi trovati
         for (Element card : factCheckCards) {
             // Estrai le informazioni di interesse da ogni card
             String titolo = card.getElementsByClass("title").text();
             String descrizione = card.getElementsByClass("description hidden-xs hidden-sm").text();
             String autore = card.getElementsByClass("author").text();
             Elements imgURL = card.getElementsMatchingText(url);
             // Stampa le informazioni
             System.out.println("Titolo: " + titolo);
             System.out.println("Affermazione: " + descrizione);
             System.out.println("Editore: " + autore);
             String img = card.getElementsByClass("max-responsive").attr("src");
            // System.out.println("Data: " + date);
             System.out.println();
           
             //salvo i dati nel vettore e ritorno
             Notizia news = new Notizia(img, titolo, descrizione, autore);
             risultati.add(news);
         }
		 
		return risultati;
	}
}
