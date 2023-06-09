package ricerche;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class FiltroStringhe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean hasValidInfo = false;
		int parolePresenti = 0;
		
		String testo1 = "";
		String testo2 = "";
		ArrayList<String> notiziaEstratta = estraiInformazioni(testo1);
		ArrayList<String> info = estraiInformazioni(testo2);

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
		
		System.out.println(hasValidInfo);
	}
	public static ArrayList<String> estraiInformazioni(String notizia) {

		ArrayList<String> informazioni =  new ArrayList<>();
	    StringTokenizer st = new StringTokenizer(notizia," ");
	    while(st.hasMoreElements()) {
	    	informazioni.add(st.nextToken());
	    }
	    return informazioni;
	}
}
