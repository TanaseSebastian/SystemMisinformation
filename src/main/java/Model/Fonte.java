package Model;

public class Fonte {

	private int id_Fonte;
	public Fonte() {
		super();
	}
	private String nome;
	private String url;
	private float indice;
	public Fonte(int id_Fonte, String nome, String url, float indice) {
		super();
		this.id_Fonte = id_Fonte;
		this.nome = nome;
		this.url = url;
		this.indice = indice;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public float getIndice() {
		return indice;
	}
	public void setIndice(float indice) {
		this.indice = indice;
	}
	
	
}
