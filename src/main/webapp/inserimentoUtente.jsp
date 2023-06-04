<%@ page language="java" import="java.util.*,Model.*,Control.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
DBManager db = new DBManager();
Utente user = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserimento utente</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>

</head>
<body>

	<%@include file="header.jsp"%>
	<div class="content" style="padding: 5%;">
		<div class="block">
			<form action="ViewUtente" METHOD="POST">
				<label for="titolo">Username</label> <input class="form-control"
					name="titolo"></input>
				<div class="form-group">
					<label for="titolo">email</label> <input class="form-control"
						name="titolo"></input>
					<div class="form-group">
						<label for="exampleFormControlSelect1">Seleziona Ruolo</label> <select
							class="form-control" id="exampleFormControlSelect1"
							name="fonteSegnalata">
							<option value="" disabled selected hidden>Seleziona il ruolo</option>
							<option name="fonteSegnalata" value=0>Utente standard</option>
							<option name="fonteSegnalata" value=1>Utente moderatore</option>
							<option name="fonteSegnalata" value=2>Utente
								amministratore</option>
						</select> </select>
					</div>
					<input type="submit" class="btn btn-primary" name="UserAction"
						value="inviaSegnalazione">
			</form>
		</div>
	</div>
</body>
</html>