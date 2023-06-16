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
public class CalcoloAttendibilitàNotizia {
	private DBManager db;
	
	
	public CalcoloAttendibilitàNotizia() throws Exception {
		super();
		this.db = new DBManager();
	}

	
public ArrayList<Notizia> calcoloAttendibilitàNotiziaTestuale(String testo,Utente user) throws Exception
{
	//inizializzo array
	ArrayList<Notizia> risultati = new ArrayList<>();
	ArrayList<Notizia> risultatiFiltrati = new ArrayList<>();
	ArrayList<FonteDiv> fonti = new ArrayList<>();
	ArrayList<String> info = new ArrayList<>();
	Notizia web = new Notizia();
	 
	//inizializzazione iniziale
	Notizia notizia = new Notizia();
	notizia.setIndice(50);
	notizia.setTitolo(testo);
	notizia.setDescrizione(" ");
	RicercaMultimediale ric = new RicercaMultimediale();
	//Verifica se è un link
	try {
		EstraiFonteLink(notizia);
	} catch (Exception e) {
		notizia.setTitolo(testo);
	}
	
	
	//estrazione informazioni principali
	info = estraiInformazioni(notizia.getTitolo());
	
	 
	 //Verifica esistenza notizia ed eventuale restituzione
	 web = controllaNotizia(notizia, info);
	// web.setTitolo("no-found");
	 //se la notizia non è stata trovata
	 if(web.getTitolo().equals("no-found") || web.getTitolo().equals("error") )
	 {
		 //proseguo con il web scraping
		 
			//Recupero fonti per ricerca
			fonti = db.getFontiScraping(user);
			
			for(int i = 0; i < fonti.size(); i++) {
				//recupero fonte per ricerca
				FonteDiv divfonte = fonti.get(i);
				String nomefonte = db.getNomeFontebyId(divfonte.getIdFonte());
				Fonte fonteRicerca = db.getFonteByName(nomefonte);
				System.out.println(divfonte.toString());
				System.out.println(fonteRicerca.toString());
				try {
					//effettuo la ricerca e salvo i risultati
					risultati.addAll(ric.ricercaNotizia(testo,divfonte,fonteRicerca));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("Error on scraping");
				}		
			}
			
			//filtraggio news
			if(!risultati.isEmpty())
			{
				for (int i = 0; i < risultati.size(); i++) {
					Notizia notizia2 = (Notizia) risultati.get(i);

						risultatiFiltrati.add(notizia2);	
				}
				
				//decremento indice della notizia
				if(!risultatiFiltrati.isEmpty())
				{
					//risultatiFiltrati.add(0, notizia);
					
					int decremento = 5;
					for (int i = 0; i < risultatiFiltrati.size(); i++)
					{
						int indice = notizia.getIndice();
						notizia.setIndice(indice - decremento);
					}
					if(notizia.getIndice() < 0)
					{
						notizia.setIndice(0);
					}
				}
				//se non sono presenti risultati inerenti all'argomento
				else {
					notizia.setDescrizione("Non è stato possibile verificare la notizia inserita:\n"
							+ "-La notizia potrebbe essere inventata;\n"
							+ "-Non sono momentaneamente disponibili le fonti per verificare \n"
							+ "-Hai bloccato tutte le fonti per verificare la notizia");
					notizia.setIndice(50);
				}
				
			}
			//se non ci sono stati risultati sull'argomento
			else
			{
				notizia.setDescrizione("Non è stato possibile verificare la notizia inserita:\n"
						+ "-La notizia potrebbe essere inventata;\n"
						+ "-Non sono momentaneamente disponibili le fonti per verificare \n"
						+ "-Hai bloccato tutte le fonti per verificare la notizia");
				notizia.setIndice(50);
			}

			
	 }
	 else
	 {
		//risultatiFiltrati.add( notizia);
		 risultatiFiltrati.add(web);
	 }
	 
	risultatiFiltrati.add(0, notizia);
	
	return risultatiFiltrati;
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
private boolean filtraNotizia1_0(Notizia n,ArrayList<String> info) {
	
	ArrayList<String> informazioni =  new ArrayList<>();
    StringTokenizer st = new StringTokenizer(n.getTitolo()," ");
    while(st.hasMoreElements()) {
    	informazioni.add(st.nextToken());
    }
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

private Notizia controllaNotizia(Notizia n,ArrayList<String> info) throws SQLException
{
	boolean trovata = false;
	ArrayList<Fonte> fontiRicerca = db.getFontiRicercaTestuale();
	Notizia notiziaWeb = new Notizia(" ", "no-found", " ", " ", " ", 0);
	Notizia retNotizia = new Notizia();
	int trovate = 0;
	
	if(!fontiRicerca.isEmpty()) {
		try {
        	String apiKey = "AIzaSyBOnSAuSkYJttXij0JgLpwm15SfmcPej5c";
            String searchEngineId = "d187f3d40d0174fdd";
          //  String query = "berlusconi è morto";
            
            String encodedQuery = URLEncoder.encode(n.getTitolo(), "UTF-8");
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
                for (JsonElement item : items) {
                	
                	//per ogni risultato, estraggo il titolo e la fonte da qui proviene 
                    JsonObject result = item.getAsJsonObject();
                    String titoloWeb = result.get("title").getAsString();
                    String linkFonteWeb = result.get("link").getAsString();
                    notiziaWeb = new Notizia(" ",linkFonteWeb," ",linkFonteWeb," ",0);
                    String fonte = EstraiFonteLink(notiziaWeb);
                    notiziaWeb = new Notizia(" ",titoloWeb," ",linkFonteWeb," ",0);
                    //filtro il testo web e verifico che corrisponda a quello che abbiamo cercato
                    boolean corrisponde = false;
                    boolean fonteValida = false;
                    corrisponde = filtraNotizia2_0(notiziaWeb, info);
                    
                    //se c'è corrispondenza, trovo la fonte da qui proviene 
                     if(corrisponde == true) {
                    	
                    	for(int i = 0; i < fontiRicerca.size(); i++) {
                    		Fonte f = fontiRicerca.get(i);
                    		
                    		
                    		
                    		if(f.getNome().equalsIgnoreCase(fonte) || f.getUrlRicerca().equalsIgnoreCase(fonte) )
                    		{
                    			fonteValida = true;
                    			notiziaWeb.setIndice((int)f.getIndice());
                    			;
                    			
                    		}

                    	}
                    }
                    
                    System.out.println("Title: " + titoloWeb);
                    System.out.println("Link: " + linkFonteWeb);
                    System.out.println("Corrispondenza = " + corrisponde);
                    System.out.println("fonteValida = " + fonteValida);
                    if(fonteValida == true) {
                    	trovata = true;
                    	if(trovate == 0) {
                    		retNotizia = new Notizia("", titoloWeb, " ", linkFonteWeb, fonte, notiziaWeb.getIndice());
                    		try {
                       		// URL websiteUrl = new URL(url);
                    		
                       		 Document document = Jsoup.connect(linkFonteWeb).get();
                             Element imageElement = document.selectFirst("article img");

                             if (imageElement != null) {
                                 String imageUrl = imageElement.absUrl("src");

                                 System.out.println("URL dell'immagine dell'articolo: " + imageUrl);
                                 retNotizia.setImg(imageUrl);
                             } else {
                                 System.out.println("Nessuna immagine dell'articolo trovata.");
                             }							
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

                    	}
                    	
                    	trovate++;
                    	
                    }
                }
            }
            else 
            {
                System.out.println("Error: " + responseCode);
                notiziaWeb = new Notizia(" ", "error", " ", " ", " ", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            notiziaWeb = new Notizia(" ", "error", " ", " ", " ", 0);
        }	
	}
	if(trovata == false || trovate < 5) {
		System.out.println("Non è stato possibile verificare l'esistenza della notizia");
		notiziaWeb = new Notizia(" ", "no-found", " ", " ", " ", 0);
	}
	else if(trovate >= 5 && trovata == true) {
		return retNotizia;
	}

	return notiziaWeb;
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

}