<%@ page language="java" import="java.util.*,Model.*,Control.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
  DBManager db = new DBManager();
ArrayList<Fonte> elencoFonti = db.getFonti();
Fonte f;
Utente user=(Utente)session.getAttribute("utente");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserimento segnalazione</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</head>
<body>

	<%@include file="header.jsp"%>
	<form action= "ViewUtente" METHOD = "POST">
	
	<div class="form-group">
    <label for="mittente">Mittente</label>
    <input class="form-control" name="mittente" value=<%= user.getUsername()%> readonly></input>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">Seleziona Fonte</label>
    <select class="form-control" id="exampleFormControlSelect1" name ="fonteSegnalata">
     <option value="" disabled selected hidden>Seleziona la fonte</option>
							<% for(int i=0;i<elencoFonti.size();i++) 
						    {
							 f=(Fonte)elencoFonti.get(i);
						 %>
							<option name="fonteSegnalata" value=<%=f.getId_Fonte()%>><%=f.getNome()%></option>
							<%
						    }
						 %>
						</select>
    </select>
  </div>

 <label for="titolo">Titolo</label>
    <input class="form-control" name="titolo"></input>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">Inserisci Motivazione</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name = "descrizione"></textarea>
  </div>
  <input type="submit" class="btn btn-primary" name= "UserAction" value="inviaSegnalazione">
</form>
	
</body>
</html>