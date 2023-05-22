package Model;

public class Notizia {

	private String img;
	private String titolo;
	private String descrizione;
	private String autore;
	private String data;
	
	public Notizia() {
		
	}
	public Notizia(String img, String titolo, String descrizione, String autore,String data)
	{
		super();
		this.img = img;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.autore = autore;
		this.data = data;
	}
	
	
	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
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
