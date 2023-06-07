package Model;

public class FonteDiv {

	private String url;
	private String divNotizie;
	private String	divNotizia; 
	private String	divTitolo ;
	private String	divAutore ;
	private String	divData ;
	private String	divDescrizione;
	private String	divImg;
	private int idFonte;
	public FonteDiv(int idFonte, String divNotizie, String divNotizia, String divTitolo, String divAutore, String divData,
			String divDescrizione, String divImg) {
		super();
		this.divNotizie = divNotizie;
		this.divNotizia = divNotizia;
		this.divTitolo = divTitolo;
		this.divAutore = divAutore;
		this.divData = divData;
		this.divDescrizione = divDescrizione;
		this.divImg = divImg;
		this.idFonte = idFonte;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDivNotizie() {
		return divNotizie;
	}
	public void setDivNotizie(String divNotizie) {
		this.divNotizie = divNotizie;
	}
	public String getDivNotizia() {
		return divNotizia;
	}
	public void setDivNotizia(String divNotizia) {
		this.divNotizia = divNotizia;
	}
	public String getDivTitolo() {
		return divTitolo;
	}
	public void setDivTitolo(String divTitolo) {
		this.divTitolo = divTitolo;
	}
	public String getDivAutore() {
		return divAutore;
	}
	public void setDivAutore(String divAutore) {
		this.divAutore = divAutore;
	}
	public String getDivData() {
		return divData;
	}
	public void setDivData(String divData) {
		this.divData = divData;
	}
	public String getDivDescrizione() {
		return divDescrizione;
	}
	public void setDivDescrizione(String divDescrizione) {
		this.divDescrizione = divDescrizione;
	}
	public String getDivImg() {
		return divImg;
	}
	public void setDivImg(String divImg) {
		this.divImg = divImg;
	}
	public int getIdFonte() {
		return idFonte;
	}
	public void setIdFonte(int idFonte) {
		this.idFonte = idFonte;
	}
	@Override
	public String toString() {
		return "FonteDiv [url=" + url + ", divNotizie=" + divNotizie + ", divNotizia=" + divNotizia + ", divTitolo="
				+ divTitolo + ", divAutore=" + divAutore + ", divData=" + divData + ", divDescrizione=" + divDescrizione
				+ ", divImg=" + divImg + ", idFonte=" + idFonte + "]";
	}
	
	
}
