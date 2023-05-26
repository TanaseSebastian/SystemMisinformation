package Model;

public class Segnalazione {
		int idSegnalazione;
		String titolo;
		String descrizione;
		String mittente;
		int idFonteSegnalata;
		int stato;
		public int getIdSegnalazione() {
			return idSegnalazione;
		}
		public void setIdSegnalazione(int idSegnalazione) {
			this.idSegnalazione = idSegnalazione;
		}
		public String getTitolo() {
			return titolo;
		}
		public void setTitolo(String titolo) {
			this.titolo = titolo;
		}
		public String getDescrizione() {
			return descrizione;
		}
		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}
		public String getMittente() {
			return mittente;
		}
		public void setMittente(String mittente) {
			this.mittente = mittente;
		}
		public int getIdFonteSegnalata() {
			return idFonteSegnalata;
		}
		public void setIdFonteSegnalata(int idFonteSegnalata) {
			this.idFonteSegnalata = idFonteSegnalata;
		}
		public int getStato() {
			return stato;
		}
		public void setStato(int stato) {
			this.stato = stato;
		}
	
		public Segnalazione(int idSegnalazione, String titolo, String descrizione, String mittente,
				int idFonteSegnalata, int stato) {
			super();
			this.idSegnalazione = idSegnalazione;
			this.titolo = titolo;
			this.descrizione = descrizione;
			this.mittente = mittente;
			this.idFonteSegnalata = idFonteSegnalata;
			this.stato = stato;
		}
		public Segnalazione() {
			super();
		}
		@Override
		public String toString() {
			return "Segnalazione [idSegnalazione=" + idSegnalazione + ", titolo=" + titolo + ", descrizione="
					+ descrizione + ", mittente=" + mittente + ", idFonteSegnalata=" + idFonteSegnalata + ", stato="
					+ stato + "]";
		}
		
}
