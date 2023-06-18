package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Fonte;
import Model.FonteDiv;
import Model.Utente;

public class DBFonti extends DBManager {
	private ResultSet rs;
	private Statement query;
	private Connection connessione;
	private String urlDB="";
	private String userDB;
	private String pwdDB;
	public DBFonti() throws Exception {
		super();
		urlDB="jdbc:mysql://localhost:3306/MYSINFORMATION_DB?serverTimezone=UTC";
		userDB="root";
		pwdDB=null;
		
		// TODO Auto-generated constructor stub
		connessione=DriverManager.getConnection(urlDB, userDB, pwdDB);
		query = connessione.createStatement();
		
	}

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
	public ArrayList<FonteDiv> getFontiScraping(Utente user) throws SQLException {
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
	public ArrayList<Fonte> getFontiRicercaTestuale(Utente user) throws SQLException{
		ArrayList<Fonte> elenco = new ArrayList<Fonte>();
		String sql= "SELECT * from fonte where id_fonte not in ( select fonte from blacklist )"
				+ " and id_fonte not in (select fonte from filtroFonti where utente ='"+user.getUsername()+"')";
		rs=query.executeQuery(sql);
		Fonte f;
		while(rs.next())
		{
			f = new Fonte(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			elenco.add(f);
		}
		
		return elenco;
	}

	public int diminuisciIndiceFonte(String nomeFonte) throws SQLException {
		// TODO Auto-generated method stub
		int nrighe=0;	
		String sqlcommand1 = "UPDATE fonte set indice = indice - 1 where nome = ?;";
		PreparedStatement ps = connessione.prepareStatement(sqlcommand1);
		ps.setString(1, nomeFonte);
		System.out.println("query che sto eseguendo : "+ps);
		nrighe = ps.executeUpdate();
		
		if(nrighe >= 1) 
			System.out.println("update eseguito correttamente"); 
		else
			 System.out.println("update non eseguito");;

			
		return nrighe;
	}
}
