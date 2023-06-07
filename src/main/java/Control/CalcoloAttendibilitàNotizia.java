package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import Model.Notizia;
import Model.Utente;
import Model.Fonte;
import Model.FonteDiv;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
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
	notizia.setTitolo(testo);
	notizia.setIndice(50);
	
	RicercaMultimediale ric = new RicercaMultimediale();
	//estrazione informazioni principali
	//String info = estraiInformazioni(notizia.getTitolo());
	
	//Recupero fonti per ricerca
	fonti = db.getFontiRicercaTestuale(user);
	
	for(int i = 0; i < fonti.size(); i++) {
		//recupero fonte per ricerca
		FonteDiv divfonte = fonti.get(i);
		String nomefonte = db.getNomeFontebyId(divfonte.getIdFonte());
		Fonte fonte = db.getFonteByName(nomefonte);
		
		try {
			//effettuo la ricerca e salvo i risultati
			risultati.addAll(ric.ricercaNotizia(testo,divfonte,fonte));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error on scraping");
		}		
	}
	
	//filtraggio news
	/*if(!risultati.isEmpty())
	{
		for (int i = 0; i < risultati.size(); i++) {
			Notizia notizia2 = (Notizia) risultati.get(i);
			boolean corrisponde = filtraNotizia(notizia2, info);
			if(corrisponde == true)
			{
				risultatiFiltrati.add(notizia2);
			}
			
		}
	}*/
	//calcoloIndice di attendibilità
	if(risultati.isEmpty())
	{
		notizia.setDescrizione("Non è stato possibile verificare la notizia inserita:\n"
				+ "La notizia potrebbe essere inventata;\n"
				+ "Non sono momentaneamente disponibili le fonti per verificare \n"
				+ "Hai bloccato tutte le fonti per verificare la notizia");
		notizia.setIndice(50);
	}
	else if(!risultati.isEmpty())
	{
		int decremento = 5;
		for (int i = 0; i < risultati.size(); i++)
		{
			int indice = notizia.getIndice();
			notizia.setIndice(indice - decremento);
		}
		if(notizia.getIndice() < 0)
		{
			notizia.setIndice(0);
		}
	}
	risultati.add(0, notizia);
	return risultati;
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

/*public Notizia algCalcoloIndice(Notizia n, ArrayList<Notizia> risultati)
{
	
}*/
}