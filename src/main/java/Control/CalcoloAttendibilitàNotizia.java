package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import Model.Notizia;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
public class CalcoloAttendibilitàNotizia {

	
public ArrayList<Notizia> calcoloAttendibilitàNotizia(String testo)
{
	ArrayList<Notizia> risultati = new ArrayList<>();
	ArrayList<Notizia> risultatiFiltrati = new ArrayList<>();
	RicercaMultimediale ric = new RicercaMultimediale();
	String info = estraiInformazioni(testo);
	//Recupero informazioni principali e controlli vari
	
	
	//chiamo la classe Ricerca ed eseguo la ricerca su web
	try {
		risultati = ric.ricercaNotizia(testo);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.err.println("Error on scraping");
	}

	//filtro
	if(!risultati.isEmpty()) {
		for (int i = 0; i < risultati.size(); i++) {
			Notizia notizia = (Notizia) risultati.get(i);
			boolean corrisponde = filtraNotizia(notizia, info);
			if(corrisponde == true)
			{
				risultatiFiltrati.add(notizia);
			}
			
		}
	}
	return risultatiFiltrati;
}

public String estraiInformazioni(String notizia) {
    // Notizia da verificare
    //String notizia = "Silvio Berlusconi è morto nel 1950 a Milano.";
    String informazioni = "";
    // Creazione del pipeline di Stanford CoreNLP
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
    }
    
   /* System.out.println("Predicati verbali:");
    for (CoreMap sentence : sentences) {
        for (CoreMap token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
            String word = token.get(CoreAnnotations.TextAnnotation.class);
            String posTag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if (posTag.startsWith("VB")) {
                System.out.println(word);
            }
        }
    }*/
    return informazioni;
}

public boolean filtraNotizia(Notizia n,String info) {
	boolean hasValidInfo = false;
	//suddivido le info ottenute in token
	StringTokenizer stInfo = new StringTokenizer(info," ");
	
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
	}
	return  hasValidInfo;
}
}