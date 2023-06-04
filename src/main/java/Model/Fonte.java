package Model;

public class Fonte {

	private int id_Fonte;
	private String nome;
	private String urlRicerca;
	private String urlFonte;
	private float indice;
	public Fonte(int id_Fonte, String nome, String urlRicerca, float indice) {
		super();
		this.id_Fonte = id_Fonte;
		this.nome = nome;
		this.urlRicerca = urlRicerca;
		this.indice = indice;
	}
	//per inserimento
	public Fonte(String urlFonte) {
		super();
		this.urlFonte = urlFonte;
	}
	
	public Fonte() {
		super();
	}
	public int getId_Fonte() {
		return id_Fonte;
	}
	public void setId_Fonte(int id_Fonte) {
		this.id_Fonte = id_Fonte;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrlRicerca() {
		return urlRicerca;
	}
	public void setUrlRicerca(String urlRicerca) {
		this.urlRicerca = urlRicerca;
	}
	public float getIndice() {
		return indice;
	}
	public void setIndice(float indice) {
		this.indice = indice;
	}
	public String getUrlFonte() {
		return urlFonte;
	}
	public void setUrlFonte(String urlFonte) {
		this.urlFonte = urlFonte;
	}
	@Override
	public String toString() {
		return "Fonte [id_Fonte=" + id_Fonte + ", nome=" + nome + ", urlRicerca=" + urlRicerca + ", urlFonte="
				+ urlFonte + ", indice=" + indice + "]";
	}
	
	
	

	
	
}
