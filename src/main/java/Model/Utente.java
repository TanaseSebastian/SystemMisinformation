package Model;

public class Utente {

	private String nome;
	private String email;
	private String pw;
	private int ruolo;
	
	
	public Utente(String nome, String email, String pw, int ruolo) {
		super();
		this.nome = nome;
		this.email = email;
		this.pw = pw;
		this.ruolo = ruolo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public int getRuolo() {
		return ruolo;
	}


	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}


	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", email=" + email + ", pw=" + pw + ", ruolo=" + ruolo + "]";
	}
	
	
}
