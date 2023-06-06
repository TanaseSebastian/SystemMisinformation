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



	public ArrayList<Notizia> ricercaNotizia(String testo,Fonte fonte) throws IOException {
		ArrayList<Notizia> risultati = new ArrayList<Notizia>();
		
		

		return risultati;
	}



	public boolean cercaPerURL(String url) throws IOException
	{
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
