package Control;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import Model.Fonte;
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
		public ArrayList<Segnalazione> getSegnalazioniUtente(Utente user) throws Exception 
		{
			ArrayList<Segnalazione> elenco = new ArrayList<Segnalazione>();
			String sql="SELECT * FROM Segnalazione WHERE mittente = '"+user.getNome()+"'";
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

			System.out.println("SEGNALAZIONI CARICATE PER L'UTENTE " + user.getNome()+ "  : " + elenco.size());

			return elenco;
		}

		public int inserisciSegnalazione(Segnalazione segnalazione) throws Exception {
			int nrighe=0;	
			String sqlcommand1 = "insert into segnalazione(titolo, descrizione, mittente, fonte_segnalata, stato) values(?,?,?,?,?);";
			PreparedStatement ps = connessione.prepareStatement(sqlcommand1);
			ps.setString(1, segnalazione.getTitolo());
			ps.setString(2, segnalazione.getDescrizione());
			ps.setString(3, segnalazione.getMittente());
			ps.setInt(4, segnalazione.getIdFonteSegnalata());
			ps.setInt(5, 0);
			nrighe = ps.executeUpdate();
			return nrighe;
			}
		
		
			//funzione che ritorna elenco fonti
				public ArrayList<Fonte> getFonti() throws Exception 
				{
					ArrayList<Fonte> elenco = new ArrayList<Fonte>();
					String sql="SELECT * FROM Fonte";
					rs=query.executeQuery(sql);
					Fonte f;

					while(rs.next())
					{
						
						f= new Fonte();
						f.setId_Fonte(rs.getInt("id_fonte"));
						f.setNome(rs.getString("nome"));
						f.setUrl(rs.getString("url"));
						f.setIndice(rs.getFloat("indice"));
						
						elenco.add(f);
					}

					System.out.println("FONTI CARICATE : " + elenco.size());
					for(int i=0; i<elenco.size();i++){
						System.out.println(elenco.get(i).getNome());
					}
					return elenco;
				}









//-----------------------------------------------


}