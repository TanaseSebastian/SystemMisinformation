package Control;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
// import java.util.Properties;
import java.util.Vector;

import Model.Fonte;
import Model.FonteDiv;
import Model.Segnalazione;
import Model.Utente;
public class DBManager {

	private Connection connessione;
	private Statement query;
	//private static Properties prop;
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
	ps.setString(1, user.getUsername());
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
			s.setStato(rs.getInt("stato"));
			elenco.add(s);
		}

		System.out.println("SEGNALAZIONI CARICATE: " + elenco.size());

		return elenco;
	}
	
	
	
	//funzione che ritorna elenco segnalazioni
		public ArrayList<Segnalazione> getSegnalazioniUtente(Utente user) throws Exception 
		{
			ArrayList<Segnalazione> elenco = new ArrayList<Segnalazione>();
			String sql="SELECT * FROM Segnalazione WHERE mittente = '"+user.getUsername()+"'";
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
				s.setStato(rs.getInt("stato"));
				elenco.add(s);
			}

			System.out.println("SEGNALAZIONI CARICATE PER L'UTENTE " + user.getUsername()+ "  : " + elenco.size());

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
						f.setUrlRicerca(rs.getString("url"));
						f.setIndice(rs.getFloat("indice"));
						
						elenco.add(f);
					}

					System.out.println("FONTI CARICATE : " + elenco.size());
					for(int i=0; i<elenco.size();i++){
						System.out.println(elenco.get(i).getNome());
					}
					return elenco;
				}
				

				public Segnalazione getSegnalazionebyId(String id) throws SQLException {
					Segnalazione s = new Segnalazione();
					String sql="SELECT * FROM Segnalazione where id_segnalazione = "+id;
					System.out.println("sql che sto eseguendo: "+ sql);
					rs=query.executeQuery(sql);
					while(rs.next())
					{
						s.setDescrizione(rs.getString("descrizione"));
						s.setIdFonteSegnalata(rs.getInt("fonte_segnalata"));
						s.setIdSegnalazione(rs.getInt("id_segnalazione"));
						s.setMittente(rs.getString("mittente"));
						s.setStato(rs.getInt("stato"));
						s.setTitolo(rs.getString("titolo"));
					}
					System.out.println("SEGNALAZIONE TROVATA");
					return s;
				}
				
				
				public String getNomeFontebyId(int id) throws SQLException {
					String s = null;
					String sql="SELECT nome FROM fonte where id_fonte = "+id;
					System.out.println("sql che sto eseguendo: "+ sql);
					rs=query.executeQuery(sql);
					while(rs.next())
					{
						s = rs.getString("nome");
						
					}
					System.out.println("SEGNALAZIONE TROVATA");
					return s;
				}

				public int modificaStatoSegnalazione(int id, int i) throws SQLException {
					// TODO Auto-generated method stub
					int nrighe=0;	
					String sqlcommand1 = "UPDATE segnalazione set stato = ?  where id_segnalazione = ?;";
					PreparedStatement ps = connessione.prepareStatement(sqlcommand1);
					ps.setInt(1, i);
					ps.setInt(2, id);
					System.out.println("query che sto eseguendo : "+ps);
					nrighe = ps.executeUpdate();
					
					if(nrighe >= 1) 
						System.out.println("update eseguito correttamente"); 
					else
						 System.out.println("update non eseguito");;
		
						
					return nrighe;
				}
				
				public ArrayList<Utente> getUtenti() throws SQLException{
					ArrayList<Utente> elenco = new ArrayList<Utente>();
					String sql="SELECT * FROM Utente";
					rs=query.executeQuery(sql);
					Utente u;

					while(rs.next())
					{
						u=new Utente();
						u.setEmail(rs.getString("email"));
						u.setUsername(rs.getString("username"));
						u.setRuolo(rs.getInt("ruolo"));
						
						elenco.add(u);
					}

					System.out.println("UTENTI CARICATI : " + elenco.size());
					for(int i=0; i<elenco.size();i++){
						System.out.println(elenco.get(i).getUsername());
					}
					return elenco;
				}

	public Fonte getFonteByName(String nomeFonte) throws SQLException {
		Fonte fonte = null;
		//System.out.println("Nome passato nel db" + nomeFonte);
		String cmd = "SELECT id_fonte,nome,url,indice FROM Fonte WHERE nome = '" + nomeFonte + "'";
		rs=query.executeQuery(cmd);
		while(rs.next()) {
			fonte = new Fonte(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4));
			//System.out.println(fonte.toString());
			}
		return fonte;
		
	}
	public ArrayList<Fonte> getFontiPerValutazione() throws SQLException {
		ArrayList<Fonte> fonti = new ArrayList<>();
	//	System.out.println("SITI WEB PER VALUTAZIONE TROVATI");
		String cmd = "SELECT valutazioniFonti.urlBlackList,fonte.nome FROM"
				+ " valutazioniFonti join fonte"
				+ " on valutazioniFonti.fonte = fonte.id_fonte";
		rs=query.executeQuery(cmd);
		while(rs.next()) {
			Fonte f = new Fonte(rs.getString(1));
			f.setNome(rs.getString(2));
			fonti.add(f);
		//	System.out.println(f.toString());
			}
		return fonti;		
	}
	
	public void inserisciFonte(Fonte f) throws SQLException
	{
		String cmd = "Insert into fonte(nome,url,indice) values (?,?,?)";
		int nrighe = 0;
		PreparedStatement ps = connessione.prepareStatement(cmd);
		ps.setString(1, f.getNome());
		ps.setString(2, f.getUrlFonte());
		ps.setInt(3, (int) f.getIndice());
		nrighe = ps.executeUpdate();	
		if(nrighe >= 1) 
			System.out.println("update eseguito correttamente"); 
		else
			 System.out.println("update non eseguito");;
	}


	public ArrayList<FonteDiv> getFontiRicercaTestuale(Utente user) throws SQLException {
		ArrayList<FonteDiv> elenco = new ArrayList<FonteDiv>();
		String sql= "SELECT * FROM divFonte where fonte not in ( select fonte from filtroFonti where utente = '" + user.getUsername() +"')";
		rs=query.executeQuery(sql);
		FonteDiv f;
		while(rs.next()) {
			f = new FonteDiv(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
			elenco.add(f);
		}
		return elenco;
	}
	public void bloccaFontePerUtente(Fonte f,Utente user) throws SQLException {
		String sql = "INSERT INTO filtroFonti values (?,?)";
		PreparedStatement ps = connessione.prepareStatement(sql);
		ps.setInt(1, f.getId_Fonte());
		ps.setString(2, user.getUsername());
		ps.executeUpdate();	
		
	}
	public ArrayList<Fonte> getFontiBloccate(Utente user) throws SQLException {
		ArrayList<Fonte> elenco = new ArrayList<Fonte>();
		String sql= " select id_fonte,nome,indice from fonte join filtroFonti\r\n"
				+ " on fonte.id_fonte = filtroFonti.fonte\r\n"
				+ " where filtroFonti.utente = '"+user.getUsername()+"';";
		rs=query.executeQuery(sql);
		Fonte f;
		while(rs.next())
		{
			f = new Fonte(rs.getInt(1),rs.getString(2)," ",rs.getFloat(3));
			elenco.add(f);
		}
		return elenco;
	}

	public void sbloccaFontePerUtente(Fonte f, Utente user) throws SQLException {
		String sql = "DELETE FROM filtroFonti where fonte = '" + f.getId_Fonte() +"' and utente = '"+user.getUsername() + "'";
		PreparedStatement ps = connessione.prepareStatement(sql);
		ps.executeUpdate();
		
	}	







//-----------------------------------------------


}