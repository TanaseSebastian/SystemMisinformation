<%@ page language="java" import="java.util.*,Model.*,Control.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%!
  ArrayList<Segnalazione> elenco;
  int i;
  Segnalazione s;
%>
<%
String righe=(String)session.getAttribute("numeroRighe");
String username=(String)session.getAttribute("username");
if(righe==null){
	righe="10";
}
	DBManager db=new DBManager();
	elenco = db.getSegnalazioniUtente(username);
	//elenco = (ArrayList<Segnalazione>)request.getAttribute("ELENCO_SEGNALAZIONI");
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/login.css">
    <link href="app/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Segnalazioni utente</title>
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">MYSINFORMATION</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item active">
              <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Notizie</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="segnalazioni.jsp">Segnalazioni</a>
            </li>
             <li class="nav-item">
            <%
			boolean logged = false;
			if(session.getAttribute("isLogged") != null)
			{
				logged = (boolean) session.getAttribute("isLogged");
			}

		if(logged == false){
		%>

              <a class="nav-link" href="login.html">Accedi</a>
		<%} 
		else{
		%>
			<a class="nav-link" href="#">Account di <%= username %></a>
			<li>
			<a class="nav-link" href="logout">Logout</a>
			</li>
		<%
		}%>
            </li>
          </ul>
        </div>
      </nav>
      
      <div class = "searchPage">
     	 <div class="input-group mb-3">
  			<input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
  			<div class="input-group-append">
   			 <button class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button>
  			</div>
		</div>
		<!-- Page Content -->
	<div class="content">
	
	        
	

		<!-- Dynamic Table Full -->
		<div class="block">
			<div class="block-header block-header-default">
				<h3 class="block-title">
					Tabella SEGNALAZIONI
				</h3>
			</div>
			<div class="table-responsive">
			<form id="form" method="post">
				<!-- DataTables -->
				<table class="table table-bordered table-striped table-vcenterd"
					id="dataTable" width="100%" cellspacing="0"
					data-page-length=<%=righe%>>
					<%request.getSession().setAttribute("numeroRighe", "10"); %>
					<thead>
											
						<div style="margin-bottom: 10px; margin-top: 20px;"">
							<button type="button"
								class="col-md-3 btn btn-outline-primary ml-10 "
								data-target="#chooseEntries" data-toggle="modal"
								data-id="visualizzaDipendenti.jsp" id="changeEntriesButton"><i class="fa fa-eye" aria-hidden="true"></i> Cambia numero
									righe
						</div>
						<div style="margin-bottom: 10px; margin-top: 20px;"">
						</div>
						<tr>
				
							<th><input type="checkbox" id="checkboxAll" onclick='$(".check").prop("checked",$ (this).prop("checked"));'>Seleziona tutto</th>
							<th>Dettagli</th>
							<th>Codice Segnalazione</th>
							<th>Titolo</th>
							<th>Descrizione</th>
							<th>Mittente</th>
							<th>Fonte Segnalata</th>
							<th>valida</th>
						</tr>
					</thead>
					<tbody>
						<% 										  
										  for(i=0;i<elenco.size();i++) 
										    {
											 s=(Segnalazione)elenco.get(i);
										    
										 %>
						<tr>
 							<td><input type="checkbox" class="check" name="check" value="<%=s.getIdSegnalazione()%>"></td>
 													
 							<td><a href="gestutenti?cmd=dettagli&id=<%=s.getIdSegnalazione()%>"><i class="fa fa-info-circle" aria-hidden="true"></i></a></td>
							<td><%=s.getIdSegnalazione()%></td>
							<td><%=s.getTitolo()%></td>
							<td><%=s.getDescrizione()%></td>
							<td><%= s.getMittente()%></td>
							<td><%=s.getIdFonteSegnalata()%></td>
							<td><a href="gestutenti?cmd=aggiorna&tipoutente=dipendente&id=<%=s.getIdSegnalazione()%>"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
						</tr>

						<%
										     }
										 %>
					</tbody>
				</table>
			</form>
			</div>
		</div>
		<!-- END Dynamic Table Full -->
	</div>
	<!-- END Page Content -->
		
	</div>
</body>
</html>