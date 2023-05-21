package Model;

public class Notizia {

	private String img;
	private String titolo;
	private String descrizione;
	private String autore;
	
	
	public Notizia(String img, String titolo, String descrizione, String autore)
	{
		super();
		this.img = img;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.autore = autore;
	}
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	
	
}
