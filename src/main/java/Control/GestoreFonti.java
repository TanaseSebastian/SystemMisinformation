package Control;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Fonte;
public class GestoreFonti {
	private RicercaMultimediale search;
	private DBManager db;
	
	
	public GestoreFonti() {
		super();
		this.search = new RicercaMultimediale();
		
	}

	public String getHostByUrl(String fonte) throws MalformedURLException {
		URL url = new URL(fonte);
		return url.getHost();
	}
	
	public Fonte elaboraValutazioneFonte(Fonte f) {
		boolean isLink = false;
		String nomeFonte;
		Fonte verificata;
		Fonte fontedaDB = new Fonte();
		try
		{
			//recupero il nome della fonte dal link inserito
			nomeFonte = getHostByUrl(f.getUrlFonte());
			f.setNome(nomeFonte);
			isLink = true;
			//se non è un link, imposto il nome della fonte uguale a cio che l'utente ha inserito
		} catch (Exception e)
		{
			System.out.println("Not a link");
			f.setNome(f.getUrlFonte());
		}
		//se è un link,controllo che il link inserito esista davvero
		if(isLink == true) {
			try 
			{
				search.cercaPerURL(f.getUrlFonte());
			} 
			catch (IOException e) 
			{
				/*se non è stato trovato, significa che l'host 
				 * inserito non esiste oppure il link è stato modificato
				 *  e non effettua la ricerca
				 */
				System.out.println("Invalid link");
				f.setNome("invalid-link");
				return f;
				//System.out.println(e.getMessage());
			}
		}
		/*Una volta estratto il nome della fonte e / o verificato il link
		 *cerco la fonte nel db*/
		try {
			db = new DBManager();
			//System.out.println(f.toString());
			fontedaDB = db.getFonteByName(f.getNome());
			
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		//se la fonte non è stata trovata nel db, eseguo la ricerca online
		if(fontedaDB  == null) {
			
			//recupero fonti per ricerca online dal sistema
			ArrayList<Fonte> fontiPerValutazione = new ArrayList<>();
			try 
			{
				fontiPerValutazione = db.getFontiPerValutazione();
			} 
			catch (SQLException e)
			{
				System.out.println(e.getMessage());
			}
			//se ci sono fonti per effettuare la ricerca
			if(!fontiPerValutazione.isEmpty())
			{
				int i = 0;
				while ( i < fontiPerValutazione.size()) 
				{
					//prendo la fonte per effettuare la ricerca
					Fonte valutaFonte = fontiPerValutazione.get(i);
					try
					{
						//cerco la valutazione
						verificata = search.recuperaValutazioneFonte(f, valutaFonte);
						//se la fonte è stata trovata,termino
						if(verificata.getIndice() != -1) {
							System.out.println("trovata");
							return verificata;
						}
					} 
					catch (IOException e)
					{
						System.out.println(e.getMessage());
					}
					i++;
				}
			}
			
			
			
		}
		else {
			return fontedaDB;
		}
		return f;
	}
	
}
