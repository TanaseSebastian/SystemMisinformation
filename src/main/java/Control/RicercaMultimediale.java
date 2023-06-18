package Control;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import Model.Fonte;
import Model.FonteDiv;
import Model.Notizia;
public class RicercaMultimediale {



	public ArrayList<Notizia> ricercaNotizia(String testo,FonteDiv fonte,Fonte f) throws IOException {
		ArrayList<Notizia> risultati = new ArrayList<Notizia>();
		//System.out.println(f.toString());
		//System.out.println(fonte.toString());
		
		//imposta div per scraping
    	String divNotizie = fonte.getDivNotizie();
    	String divNotizia = fonte.getDivNotizia();
    	String divTitolo = fonte.getDivTitolo();
    	String divAutore = "news__author";
    	String divData = fonte.getDivData();
    	String divDescrizione = fonte.getDivDescrizione();
    	String divImg = fonte.getDivImg();
    	//creo parametri della notizia
    	String titolo = null;
    	String data = null;
    	String img = null;
    	String desc = null;
    	//conversione testo per ricerca e preparazione link
    	testo = URLEncoder.encode(testo, "UTF-8");
    	String url = f.getUrlRicerca() + testo;

    	//ricerca
    	Document doc = Jsoup.connect(url).get();
    	
    	//recupero risultati
    	Elements lista = doc.getElementsByClass(divNotizie);
    	
    	for (Element notizia : lista) {
    		//per ogni notizia, recupero relativo url
    		String urlNotizia = notizia.getElementsByTag("a").attr("href");
			
    		//apro la notizia
    		Document pagNotizia = Jsoup.connect(urlNotizia).get();
    		//recupero contenuto notizia
    		Elements listaNotizia = pagNotizia.getElementsByClass(divNotizia);
    		
    		//recupero elementi notizia
			for (Element info : listaNotizia)
			{
				titolo = info.getElementsByClass(divTitolo).first().text();
				data = info.getElementsByClass(divData).text(); //autore + data
				img = info.getElementsByClass(divImg).attr("src");
				try {
					desc = notizia.getElementsByTag("p").first().text();
				} catch (Exception e) {
					// TODO: handle exception
				}
				/*System.out.println("Titolo = " + titolo);
				System.out.println("Data = " + data);
				System.out.println("img = " + img);
				System.out.println("Descrizione = " + desc);*/
			}  
			//creo oggetto notizia e salvo su db
			Notizia n = new Notizia(img, titolo, desc, f.getNome(), data,(int)f.getIndice());
			risultati.add(n);
    	}
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
	public ArrayList<Notizia> ricercaWeb(String notizia){
		ArrayList<Notizia> notizie = new ArrayList<>();
        try {
        	String apiKey = "AIzaSyBOnSAuSkYJttXij0JgLpwm15SfmcPej5c";
            String searchEngineId = "d187f3d40d0174fdd";
            

            String encodedQuery = URLEncoder.encode(notizia, "UTF-8");
            String url = "https://www.googleapis.com/customsearch/v1?key=" + apiKey +
                    "&cx=" + searchEngineId + "&q=" + encodedQuery;

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Gson gson = new Gson(); // Instantiate the Gson object

                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
                JsonArray items = jsonObject.getAsJsonArray("items");
                for (JsonElement item : items)
                {
                    JsonObject result = item.getAsJsonObject();
                    System.out.println(result.toString());
                    String title = result.get("title").getAsString();
                    String fonte = result.get("displayLink").getAsString();
                    String descr = result.get("link").getAsString();
                    String data = result.get("snippet").getAsString();
                    /*System.out.println("Title: " + title);
                    System.out.println("Link: " + link);*/
                    Notizia notiziaWeb = new Notizia(" ", title,descr,fonte, data, 0);
                    System.out.println(notiziaWeb);
                    notizie.add(notiziaWeb);
                    System.out.println();
                }
            } 
            else
            {
                System.out.println("Error: " + responseCode);
            }
            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return notizie;
	}
}
