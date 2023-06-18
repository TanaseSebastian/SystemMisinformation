package Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Model.Notizia;
import Model.Utente;
import Model.Fonte;
import Model.FonteDiv;


import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
public class GestoreNotizie {
	private DBFonti db;
	private RicercaMultimediale ricerca;
	
	public GestoreNotizie() throws Exception {
		super();
		this.db = new DBFonti();
		this.ricerca = new RicercaMultimediale();
	}

	

public ArrayList<Notizia> calcoloAttendibilitàNotiziaMultimediale(String link) throws IOException
{
	ArrayList<Notizia> risultati = new ArrayList<>();
	//inizializzazione notizia
	Notizia n = new Notizia();
	n.setImg(link);
	n.setTitolo(link);
	int indice = 0;
	  String credentialsToEncode = "acc_26e4afc7aab2c9b" + ":" + "428c2c61c4aae66f0fc82d46ab0377ee";
	  String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

	  String endpoint_url = "https://api.imagga.com/v2/tags";
	  String image_url = n.getImg();

	  String url = endpoint_url + "?image_url=" + image_url;
	  URL urlObject = new URL(url);
	  HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

	  connection.setRequestProperty("Authorization", "Basic " + basicAuth);

	  int responseCode = connection.getResponseCode();

	  System.out.println("\nSending 'GET' request to URL : " + url);
	  System.out.println("Response Code : " + responseCode);

	  BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

	  String jsonResponse = connectionInput.readLine();

	  connectionInput.close();
	  //dato il file json come rispsota,leggo l'indice
	  indice = estrattoreIndice(jsonResponse);
	 // System.out.println(jsonResponse);
	  n.setIndice(indice);
	  
	  //Faccio visualizzare la fonte da qui proviene la verifica
	  Notizia valutatore = new Notizia("https://imagga.com/static/images/logo_white.svg", "imagga", " ","IMAGGA", " ", 100);
	  
	  risultati.add(n);
	  risultati.add(valutatore);
	  
	  return risultati;

	}
private ArrayList<String> estraiInformazioni(String notizia) {

	/*ArrayList<String> informazioni =  new ArrayList<>();
    StringTokenizer st = new StringTokenizer(notizia," ");
    while(st.hasMoreElements()) {
    	informazioni.add(st.nextToken());
    }
    return informazioni;*/
	String testo = notizia;
    String[] paroleArray = testo.split("[^a-zA-Z']+");
    ArrayList<String> parole = new ArrayList<>();

    for (String parola : paroleArray) {
        if (!parola.isEmpty()) {
            parole.add(parola);
        }
    }
    return parole;
}
private boolean filtraNotizia2_0(Notizia n,ArrayList<String> info) {

	//inizializzazione parametri
	boolean hasValidInfo = false;
	int parolePresenti = 0;
	ArrayList<String> notiziaEstratta = estraiInformazioni(n.getTitolo());
	//per ogni parola di un titolo della notizia, controllo che almeno ce ne siano un tot ripsetto a quella cercata
	for(int i = 0; i < notiziaEstratta.size(); i++)
	{
		for(int j = 0; j < info.size(); j++) {
			String daNotiziaTrovata = notiziaEstratta.get(i);
			String daNotiziaCercata = info.get(j);
			if(daNotiziaTrovata.equalsIgnoreCase(daNotiziaCercata))
				parolePresenti++;
		}
	}
	if(parolePresenti >= 2 || parolePresenti == notiziaEstratta.size())
		hasValidInfo = true;
	
	return  hasValidInfo;

}

private int estrattoreIndice(String jsonFile) {
	int indice = 0;
	ObjectMapper objectMapper = new ObjectMapper();
	try {
      // Parsing del JSON
      JsonNode jsonNode = objectMapper.readTree(jsonFile);

      // Ottenere l'array "tags"
      JsonNode tagsNode = jsonNode.path("result").path("tags");

      if (tagsNode.isArray()) {
          // Iterare attraverso i valori dell'array "tags"
          for (JsonNode tagNode : tagsNode) {
              // Ottenere il valore "confidence"
              double confidence = tagNode.path("confidence").asDouble();
              System.out.println("Indice: " + confidence);
              indice = (int) confidence;
              break;
          }
      }
      
  }
	catch (IOException e)
	{
      e.printStackTrace();
  
	}
	return indice;
	
}
private ArrayList<Notizia> controllaNotizia2(Notizia notizia) throws SQLException{
	//verifico se la notizia è un link o meno
    try {
        // Scarica il documento HTML dal URL
        Document document = null;
        try {
			document = Jsoup.connect(notizia.getTitolo()).get();
			// Estrae il titolo della pagina
	        notizia.setTitolo(document.title());
		} catch (Exception e) {
			System.out.println("La notizia inserita non è un link");
		}
        // Stampa il titolo
        System.out.println("Titolo della pagina: " + notizia.getTitolo());
    } catch (Exception e) {
        System.out.println("La notizia inserita non è un link");
    }
	
	ArrayList<Notizia> filtrate = new ArrayList<>();
	 boolean corrisponde;
	 ArrayList<Notizia> ricercaWeb = ricerca.ricercaWeb(notizia.getTitolo());
	//effettuo la ricerca web della notizia
	 //notizie = ricerca.ricercaWeb(n.getTitolo());
	
	//estraggo il contenuto della notizia
	ArrayList<String> info = new ArrayList<>();
	info = estraiInformazioni(notizia.getTitolo());
	//filtro
	if(!ricercaWeb.isEmpty())
	{
		for(int i = 0; i < ricercaWeb.size(); i++)
		{
			//inizializzazione
			Notizia temp = ricercaWeb.get(i);
			corrisponde = false;
			
			//vedo se la notizia riguarda l'argomento cercato
			corrisponde = filtraNotizia2_0(temp, info);
			if(corrisponde == true)
			{
				filtrate.add(temp);
			}
		}		
	}
	return filtrate;

}


private String EstraiFonteLink(Notizia notizia) {
	Fonte autoreNotizia = null;
	Fonte[] risElaborazioneAutore;
	
	
	String fonte = "no-link";
	//verifico che la notiza sia un link, recupero la fonte
	try {
		fonte = GestoreFonti.getHostByUrl(notizia.getTitolo());
		//testo = temp;
		
	} 
	catch (MalformedURLException e)
	{
		System.out.println("Not a link");
		fonte = "no-link";
		
	}
	//se il link è valido, recupero la fonte e la notizia
	if(!fonte.equals("no-link"))
	{
		//recupero il titolo della notizai
        try {
            // Scarica il documento HTML dal URL
            Document document = Jsoup.connect(notizia.getTitolo()).get();

            // Estrae il titolo della pagina
             notizia.setTitolo(document.title());

            // Stampa il titolo
            System.out.println("Titolo della pagina: " + notizia.getTitolo());
        } catch (IOException e) {
            e.printStackTrace();
        }
        autoreNotizia = new Fonte(fonte);
        //cerco la valutazione della fonte
        GestoreFonti gesF = new GestoreFonti();
        risElaborazioneAutore = gesF.elaboraValutazioneFonte(autoreNotizia);
        //stampo
        try {
            if(risElaborazioneAutore != null)
            {
            	System.out.println(risElaborazioneAutore[0].toString());
            	System.out.println(risElaborazioneAutore[1].toString());
            	notizia.setIndice((int) (risElaborazioneAutore[0].getIndice()));
            	notizia.setDescrizione("La notizia proviene dalla black list di uno dei nostri siti esterni "
            				+ "Nonostante l'indice di attendibilità possa risultare alto, attenzione! \n");
            }       	
        }catch (Exception e) {
			// TODO: handle exception
		}

	}
	System.out.println("Estratta = " + fonte);
	return fonte;
}
private String EstraiFonteLink(String notizia) {
	
	String fonte = "no-link";
	//verifico che la notiza sia un link, recupero la fonte
	try {
		fonte = GestoreFonti.getHostByUrl(notizia);
		//testo = temp;
		
	} 
	catch (MalformedURLException e)
	{
		System.out.println("Not a link");
		fonte = "no-link";
		
	}
	return fonte;
}

public ArrayList<Notizia> calcoloAttendibilitàNotiziaTestuale2(String testo,Utente user) throws Exception{
	//inizializzazione
	ArrayList<Notizia> ricercaWeb = new ArrayList<>();
	ArrayList<Notizia> risultati = new ArrayList<>();
	ArrayList<Notizia> filtroRisultati = new ArrayList<>();
	ArrayList<Fonte> fontiAmmesse = db.getFontiRicercaTestuale(user);
	Notizia cercata = new Notizia();
	cercata.setTitolo(testo);
	
	int minFonti = 4;
	
	//ricerca su web
	//ricercaWeb = ricerca.ricercaWeb(testo);
	
	//estraggo i contenuti della notizia
    ArrayList<String> contenutiEstratti = estraiInformazioni(testo);
	
	//effettuo il filtro delle notizie
	ricercaWeb = controllaNotizia2(cercata);
	
	if(!ricercaWeb.isEmpty())
	{
	
		//se i risultati non sono vuoti,per ogni verifico le fonti da qui provengono
		for(int i = 0; i < ricercaWeb.size(); i++) {
			Notizia temp = ricercaWeb.get(i);
			//estraggo immagine
			try {
	    		 Document document = Jsoup.connect(temp.getDescrizione()).get();
	      		 temp.setDescrizione(" ");
	             Element imageElement = document.selectFirst("article img");

	             if (imageElement != null) {
	                 String imageUrl = imageElement.absUrl("src");

	                 System.out.println("URL dell'immagine dell'articolo: " + imageUrl);
	                 temp.setImg(imageUrl);
	             } else {
	                 System.out.println("Nessuna immagine dell'articolo trovata.");
	             }				
				
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			//verifico che la fonte è ammessa
			for(int j = 0; j < fontiAmmesse.size(); j++)
			{
				Fonte f = fontiAmmesse.get(j);
				//verifico, se è ammessa aggiungo
				if(f.getNome().equalsIgnoreCase(temp.getAutore()) ||f.getUrlRicerca().equalsIgnoreCase(temp.getAutore())) 
				{
					risultati.add(temp);
				}
			}
		}
		
		//verifico i risultati rimasti, se sono pochi passo al fact checking
		System.out.println(risultati.size());
		if(risultati.size() < minFonti)
		{
			//svuoto
			risultati.clear();
			//Recupero struttura per ricerca
			ArrayList<FonteDiv> fonti = db.getFontiScraping(user);
			//per ogni fonte, effettuo la ricerca
			for(int i = 0; i < fonti.size(); i++)
			{
				//recupero fonte per ricerca
				FonteDiv divfonte = fonti.get(i);
				
				//recupero fonte dalla struttura div
				String nomefonte = db.getNomeFontebyId(divfonte.getIdFonte());
				Fonte fonteRicerca = db.getFonteByName(nomefonte);
				
				//effettuo la ricerca e salvo i risultati
				try 
				{
					risultati.addAll(ricerca.ricercaNotizia(testo,divfonte,fonteRicerca));
					
				} 
				catch (IOException e) {
					System.err.println("Error on scraping");
				}
				

			}
			//effettuo il filtro delle notizie trovate
			if(!risultati.isEmpty())
			{
				for (int i = 0; i < risultati.size(); i++) {
					Notizia notizia2 = (Notizia) risultati.get(i);
					boolean corrisponde = filtraNotizia2_0(notizia2, contenutiEstratti);
					if(corrisponde == true)
					filtroRisultati.add(notizia2);	
				}
				if(filtroRisultati.size() >= 3) {
					cercata.setIndice(0);
					cercata.setDescrizione("La notizia non è attendibile!");
				}
				filtroRisultati.add(0,cercata);
				return filtroRisultati;
			}
		}
		//se è stato raggiunto il numero di risultati necessario,la notizia è ritenuta attendibile
		else
		{
			cercata.setIndice(100);
			cercata.setDescrizione("La notizia è attendibile!");
			risultati.add(0, cercata);
			return risultati;
		}
	}
	// se non sono stati trovati riscontri, ritorno che non è stato possibile verificare la notizia
	cercata.setIndice(50);
	cercata.setDescrizione("Impossibile verificare la notizia");
	risultati.add(cercata);
	return risultati;
	

}
}