package Control;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import Model.Segnalazione;
import Model.Utente;
public class DBManager {

	private Connection connessione;
	private Statement query;
	private static Properties prop;
	private String urlDB="";
	private String userDB;
	private String pwdDB;
	private ResultSet rs;
	public DBManager() throws Exception{
		urlDB="jdbc:mysql://localhost:3306/MYSINFORMATION_DB?serverTimezone=UTC";
		userDB="root";
		pwdDB=null;
		//Creazione della connessione
		//Registrazione dei Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Registrazione driver sql effettuata");
		connessione=DriverManager.getConnection(urlDB, userDB, pwdDB);
		System.out.println("Connessione instaurata con il database MYSINFORMATIONDB");
		query = connessione.createStatement();
		
		/*
		
		// Leggo le propriety da file properties
		ReadPropertyFileFromClassPath obj = new ReadPropertyFileFromClassPath();
		prop = obj.loadProperties("DB.properties");

		urlDB = prop.getProperty("Url");
		userDB = prop.getProperty("Username");
		pwdDB = prop.getProperty("Pasword");
		
		// creazione della connessione
		// registrazione dei driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// instauro connessione al db
		connessione = DriverManager.getConnection(urlDB, userDB, pwdDB);
		
		// creo la query
		query = connessione.createStatement();
		*/
		
	}
	
public Utente trovaUtente(String username,String pw) throws Exception {
		Utente user = null;
		String sqlcommand = "select * from utente where username = '" + username + "' and pw = '" + pw + "'";
		rs=query.executeQuery(sqlcommand);
		while(rs.next()) {
			user = new Utente(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
		}

		System.out.println("(DBMANAGER)Letto: " +rs.getRow());
		rs.close();
		return user;
		
	}
public Utente controllaUtenteEsistente(String username,String email) throws Exception {
	Utente user = null;
	String sqlcommand = "select * from utente where username = '" + username + "' and email = '"+ email +"'";
	rs=query.executeQuery(sqlcommand);
	while(rs.next()) {
		user = new Utente(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
	}

	System.out.println("(DBMANAGER)Letto: " +rs.getRow());
	rs.close();
	return user;
	
}

public void ChiudiDB() throws Exception {
		query.close();
		connessione.close();
	}	
public int inserisciUtente(Utente user) throws Exception {
	int nrighe=0;	
	String sqlcommand1 = "insert into utente values(?,?,?,?);";
	PreparedStatement ps = connessione.prepareStatement(sqlcommand1);
	ps.setString(1, user.getNome());
	ps.setString(2, user.getEmail());
	ps.setString(3, user.getPw());
	ps.setInt(4, user.getRuolo());
	nrighe = ps.executeUpdate();
	return nrighe;
	}




//------------ GESTIONE SEGNALAZIONI---------------

//funzione che ritorna elenco segnalazioni
	public ArrayList<Segnalazione> getSegnalazioni() throws Exception 
	{
		ArrayList<Segnalazione> elenco = new ArrayList<Segnalazione>();
		String sql="SELECT * FROM Segnalazione;";
		rs=query.executeQuery(sql);
		Segnalazione s;

		while(rs.next())
		{
			
			s= new Segnalazione();
			s.setIdSegnalazione(rs.getInt("id_segnalazione"));
			s.setTitolo(rs.getString("titolo"));
			s.setDescrizione(rs.getString("descrizione"));
			s.setMittente(rs.getString("mittente"));
			s.setIdFonteSegnalata(rs.getInt("fonte_segnalata"));
			
			elenco.add(s);
		}

		System.out.println("SEGNALAZIONI CARICATE: " + elenco.size());

		return elenco;
	}
	
	
	
	//funzione che ritorna elenco segnalazioni
		public ArrayList<Segnalazione> getSegnalazioniUtente(String username) throws Exception 
		{
			ArrayList<Segnalazione> elenco = new ArrayList<Segnalazione>();
			String sql="SELECT * FROM Segnalazione WHERE mittente = '"+username+"'";
			rs=query.executeQuery(sql);
			Segnalazione s;

			while(rs.next())
			{
				
				s= new Segnalazione();
				s.setIdSegnalazione(rs.getInt("id_segnalazione"));
				s.setTitolo(rs.getString("titolo"));
				s.setDescrizione(rs.getString("descrizione"));
				s.setMittente(rs.getString("mittente"));
				s.setIdFonteSegnalata(rs.getInt("fonte_segnalata"));
				
				elenco.add(s);
			}

			System.out.println("SEGNALAZIONI CARICATE PER L'UTENTE " + username+ "  : " + elenco.size());

			return elenco;
		}









//-----------------------------------------------


}