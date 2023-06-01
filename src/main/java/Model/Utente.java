package Model;

public class Utente {

	private String username;
	private String email;
	private String pw;
	private int ruolo;
	
	
	public Utente(String username, String email, String pw, int ruolo) {
		super();
		this.username = username;
		this.email = email;
		this.pw = pw;
		this.ruolo = ruolo;
	}


	public Utente() {
		// TODO Auto-generated constructor stub
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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
		return "Utente [username=" + username + ", email=" + email + ", pw=" + pw + ", ruolo=" + ruolo + "]";
	}
	
	
}
