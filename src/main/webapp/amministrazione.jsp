<%@ page language="java" import="java.util.*,Model.*,Control.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!ArrayList<Utente> elenco;
	int i;
	Utente u;%>
<%
String righe = (String) session.getAttribute("numeroRighe");
Utente user = (Utente) session.getAttribute("utente");
if (righe == null) {
	righe = "10";
}
DBManager db = new DBManager();
elenco = db.getUtenti();
//elenco = (ArrayList<Segnalazione>)request.getAttribute("ELENCO_SEGNALAZIONI");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/login.css">
<link href="app/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
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
<meta charset="ISO-8859-1">
<title>Amministrazione</title>
</head>
<body>

	<%@include file="header.jsp"%>


	<div class="content" style="padding: 5%;">
		<div class="block">
			<!-- Dynamic Table Full -->
			<div class="block">
				<div class="block-header block-header-default">
					<h3 class="block-title">Tabella UTENTI</h3>
				</div>
				<div class="table-responsive">

					<!-- DataTables -->
					<table class="table table-bordered table-striped table-vcenterd"
						id="dataTable" width="100%" cellspacing="0"
						data-page-length=<%=righe%>>
						<%
						request.getSession().setAttribute("numeroRighe", "10");
						%>
						<thead>

							<div style="margin-bottom: 10px; margin-top: 20px;"">
								<button type="submit" class="btn btn-primary" name="UserAction"
									value="inserisciUtente">
									<a style="text-decoration: none; color: white;"
										href="inserimentoUtente.jsp">Inserisci nuovo utente</a>
								</button>
								<!-- <button type="submit" class="btn btn-danger" name="UserAction"
									value="inserisciUtente">
									<a style="text-decoration: none; color: white;">Elimina
										utente</a>
								</button> -->
							</div>
							<div style="margin-bottom: 10px; margin-top: 20px;""></div>
							<tr>

								<!-- <input type="checkbox" id="checkboxAll"
									onclick='$(".check").prop("checked",$ (this).prop("checked"));'>Seleziona
									tutto</th> -->
								<th>USERNAME</th>
								<th>EMAIL</th>
								<th>RUOLO</th>
								<!--<th>modifica</th> -->
							</tr>
						</thead>
						<tbody>
							<%
							for (i = 0; i < elenco.size(); i++) {
								u = (Utente) elenco.get(i);
								if (!u.getUsername().equals(user.getUsername())) {
							%>
							<tr>
								<!--  <td><input type="checkbox" class="check" name="check"
									value="<%//u.getUsername()%>"></td>-->
								<td><%=u.getUsername()%></td>
								<td><%=u.getEmail()%></td>
								<%
								if (u.getRuolo() == 0) {
								%>
								<td>utente standard</td>
								<%
								} else if (u.getRuolo() == 1) {
								%>
								<td>utente moderatore</td>
								<%
								} else if (u.getRuolo() == 2) {
								%>
								<td>utente amministratore</td>
								<%
								}
								%>
								<!-- <td><a
									href="viewUtenti?cmd=modifica&id=<%//u.getUsername()%>"><i
										class="fa fa-info-circle" aria-hidden="true"></i>modifica
										utente</a></td> -->

							</tr>

							<%
							}
							}
							%>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
	<!-- END Dynamic Table Full -->
	</div>
	<!-- END Page Content -->

	</div>
</body>
</html>