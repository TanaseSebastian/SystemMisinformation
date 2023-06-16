package Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	//inizializzo notizia
	Notizia notizia = new Notizia();
	Fonte autoreNotizia = null;
	Fonte[] risElaborazioneAutore;
	notizia.setIndice(50);
	
	String fonte = "no-link";
	//verifico che la notiza sia un link, recupero la fonte
	try {
		fonte = GestoreFonti.getHostByUrl(testo);
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
            Document document = Jsoup.connect(testo).get();

            // Estrae il titolo della pagina
            testo = document.title();

            // Stampa il titolo
            System.out.println("Titolo della pagina: " + testo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        autoreNotizia = new Fonte(fonte);
        //cerco la valutazione della fonte
        GestoreFonti gesF = new GestoreFonti();
        risElaborazioneAutore = gesF.elaboraValutazioneFonte(autoreNotizia);
        //stampo
        if(risElaborazioneAutore != null)
        {
        	System.out.println(risElaborazioneAutore[0].toString());
        	System.out.println(risElaborazioneAutore[1].toString());
        	notizia.setIndice((int) (risElaborazioneAutore[0].getIndice()));
        	notizia.setDescrizione("La notizia proviene dalla black list di uno dei nostri siti esterni "
        				+ "Nonostante l'indice di attendibilità possa risultare alto, attenzione! \n");
        }
	}
	
	notizia.setTitolo(testo);
	RicercaMultimediale ric = new RicercaMultimediale();
	
	//estrazione informazioni principali
	 ArrayList<String> info = estraiInformazioni(notizia.getTitolo());
	
	 
	 //Verifica esistenza notizia ed eventuale restituzione
	 
	 boolean trovata = false;
	ArrayList<Fonte> fontiRicerca = db.getFontiRicercaTestuale();
	if(!fontiRicerca.isEmpty()) {
		
	}
	 
	 
	//Recupero fonti per ricerca
	fonti = db.getFontiScraping(user);
	
	for(int i = 0; i < fonti.size(); i++) {
		//recupero fonte per ricerca
		FonteDiv divfonte = fonti.get(i);
		String nomefonte = db.getNomeFontebyId(divfonte.getIdFonte());
		Fonte fonteRicerca = db.getFonteByName(nomefonte);
		
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
			boolean corrisponde = filtraNotizia(notizia2, info);
			if(corrisponde == true)
			{
				risultatiFiltrati.add(notizia2);
			}
			
		}
	}
	//calcoloIndice di attendibilità
	if(risultati.isEmpty())
	{
		notizia.setDescrizione("Non è stato possibile verificare la notizia inserita:\n"
				+ "La notizia potrebbe essere inventata;\n"
				+ "Non sono momentaneamente disponibili le fonti per verificare \n"
				+ "Hai bloccato tutte le fonti per verificare la notizia");
		notizia.setIndice(50);
	}
	else if(!risultatiFiltrati.isEmpty())
	{
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
public ArrayList<String> estraiInformazioni(String notizia) {
    
    //String notizia = "Silvio Berlusconi è morto nel 1950 a Milano.";

	
  /*  // Creazione del pipeline di Stanford CoreNLP
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    // Creazione di un oggetto Annotation per la notizia
    Annotation annotation = new Annotation(notizia);

    // Esecuzione dell'analisi
    pipeline.annotate(annotation);

    // Estrazione delle entità nominate
    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
    System.out.println("Entità nominate:");
    for (CoreMap sentence : sentences)
    {
        for (CoreMap token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
            String word = token.get(CoreAnnotations.TextAnnotation.class);
            String nerTag = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            
           
            if (!nerTag.equals("O")) {
                System.out.println(word + " [" + nerTag + "]");
                informazioni += " " + word;
            }

        }
    }*/
	ArrayList<String> informazioni =  new ArrayList<>();
    StringTokenizer st = new StringTokenizer(notizia," ");
    while(st.hasMoreElements()) {
    	informazioni.add(st.nextToken());
    }
    return informazioni;
}

public boolean filtraNotizia(Notizia n,ArrayList<String> info) {
	//suddivido le info ottenute in token
	/*StringTokenizer stInfo = new StringTokenizer(info," ");
	
	//per ogni info, verifico che sia presente nel titolo della news almeno una di  esse
	while(stInfo.hasMoreElements() && hasValidInfo == false) {
		
		String info2 = stInfo.nextToken();
		//suddivido il titolo della notizia
		StringTokenizer stTitle = new StringTokenizer(n.getTitolo()," ");
		
		while(stTitle.hasMoreElements()) {
			
			String title = stTitle.nextToken();
			
			//verifico se le info inserite corrispondono
			if(title.equalsIgnoreCase(info2))
			{
				hasValidInfo = true;
				break;
			}
		}
	}*/
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
	if(parolePresenti >= 2)
		hasValidInfo = true;
	
	return  hasValidInfo;
}

public int estrattoreIndice(String jsonFile) {
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


}