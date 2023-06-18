<%@ page language="java" import="java.util.*,Model.*,Control.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
Segnalazione segnalazione=(Segnalazione)session.getAttribute("segnalazione");
  DBFonti db = new DBFonti();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Valida segnalazione</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</head>
<body>

	<%@include file="header.jsp"%>
	
	<div class="content" style="padding:5%;">
	<div class="block">
	<form action= "viewSegnalazioni" METHOD = "POST" onSubmit="if(!confirm('Sei sicuro di voler confermare questa operazione?')){return false;}">
	<input class="form-control" name="id" value=<%= segnalazione.getIdSegnalazione()%> hidden readonly></input>
	<div class="form-group">
    <label for="mittente">Mittente</label>
    <input class="form-control" name="mittente" value=<%= segnalazione.getMittente()%> readonly></input>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">Fonte</label>
    <input class="form-control" name="fonte" value=<%=db.getNomeFontebyId(segnalazione.getIdFonteSegnalata())%> readonly></input>
  </div>

 <label for="titolo">Titolo</label>
    <input class="form-control" name="titolo" value=<%= segnalazione.getTitolo()%> readonly></input>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">Motivazione</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name = "descrizione" value=<%=segnalazione.getDescrizione()%> readonly><%=segnalazione.getDescrizione() %> </textarea>
  </div>
  <input type="submit" class="btn btn-danger" name= "UserAction" value="declina segnalazione">
  <input type="submit" class="btn btn-success" name= "UserAction" value="conferma e valida segnalazione">
</form>
</div>
</div>
	
</body>
</html>