package Control;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

import Model.Fonte;
import Model.Notizia;
public class RicercaMultimediale {

	public ArrayList<Notizia> ricercaNotizia(String testo) throws IOException {
		ArrayList<Notizia> risultati = new ArrayList<Notizia>();

		risultati.addAll(BufaleSearch(testo));
		risultati.addAll(ButacSearch(testo));
		return risultati;
	}

	public ArrayList<Notizia> BufaleSearch(String testo) throws IOException{
		String url = "\"https://www.bufale.net/?s=";
		ArrayList<Notizia> risultati = new ArrayList<Notizia>();
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
			Notizia news = new Notizia(img, titolo, descrizione, autore, "");
			risultati.add(news);
		}

		return risultati;
	}
	public ArrayList<Notizia> ButacSearch(String testo)

	{
		String url = "https://www.butac.it/search/" + testo;
		ArrayList<Notizia> risultati = new ArrayList<Notizia>();	
		Document  doc2 = null;
		String descrizione = "";
		String title = "";
		String data = "";
		String imgURL = "";
		try {

			//effettuo le ricerche delle varie notizie
			Document doc = Jsoup.connect(url).get();
			Elements factCheckCards = doc.getElementsByClass("title j-title");
			for (Element card : factCheckCards) {
				Notizia news = new Notizia();
				// Estraggo il link per connettermi alla pagine e recuperare le info
				String link = card.getElementsByAttribute("href").attr("href");


				// Stampa il link
				System.out.println("Link: " + link);
				
				//Mi connetto alla pagina principale della notizia
				doc2 = Jsoup.connect(link).get();
				
				//Inizio dall'header delle notizie
				Elements notizie = doc2.getElementsByClass("previewArticle j-previewArticle");
				for (Element card2 : notizie) {

					//ottengo titolo,data e autore
					title = card2.getElementsByClass("titleArticle j-Article").text();
					title += ", " + card2.getElementsByClass("subtitleArticle").text();
					data = card2.getElementsByAttribute("datetime").text();
					news.setTitolo(title);
					news.setData(data);
					//Prendo l'immagine del post
					Element img = doc2.getElementById("post-thumbnail");
					try {
						imgURL = img.getAllElements().attr("data-src");	
						System.out.println(imgURL);
						news.setImg(imgURL);
					} catch (Exception e) {
						// TODO: handle exception
					}
				
					
					//recupero la descrizione del post
					Elements descrizioni = doc2.getElementsByTag("p");
					int maxRighe = 4;
					int i = 0;
					for (Element card3 : descrizioni) {

						if(i < maxRighe)
						{
							descrizione += card3.getAllElements().text() + "\n";
							i++;
						}
						

					}
					news.setDescrizione(descrizione);
				
					risultati.add(news);
					//reinizialliza parametri
					i = 0;
					descrizione = "";
					
				}
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Stampo
/*		System.out.println("Titolo = " + title);
		System.out.println("Data Post = " + data);
		System.out.println("Descrizione = " + descrizione);
		System.out.println("Img = " + imgURL);
*/
		return risultati;
	}
	public boolean cercaPerURL(String url) throws IOException {
		boolean esisteLink = false;
		Document doc = Jsoup.connect(url).get();
		//if(doc.)
		return esisteLink;
	}
	public Fonte recuperaValutazioneFonte(Fonte fonteDaValutare, Fonte ricerca) throws IOException {
		//effettuo la ricerca, dato l'url della black list
		Document doc = Jsoup.connect(ricerca.getUrlFonte()).postDataCharset("UTF-8").get();
		//recupero le fonti presenti nella pagina
		Elements lista = doc.getElementsByTag("li");
		String nomeFonte = fonteDaValutare.getNome().toLowerCase();
		//per ogni fonte trovata, la salviamo in una var stringa
		for (Element fonte : lista)
		{
			//recupero una fonte elencata
			String fonteInLista = fonte.text();
			//System.out.println(fonteInLista);
			//cerchiamo la fonte nella lista
			if(nomeFonte.toLowerCase().equals(fonteInLista.toLowerCase()))
			{
				// se la trovo,termino e impoto l'indice a 0
				fonteDaValutare.setIndice(0);
				return fonteDaValutare;
			}
		
		}
		//se non la trova, imposta l'indice a -1
		fonteDaValutare.setIndice(-1);
		return fonteDaValutare;
	}
}
