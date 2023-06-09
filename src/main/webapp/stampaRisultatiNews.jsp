<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Notizia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>


<% 
int pag = 0;
int pageSize = 5;
int strIndex;
int endIndex;
ArrayList<Notizia> risultati = (ArrayList<Notizia>)session.getAttribute("risultatiNotizia"); 
Notizia cercata = (Notizia) session.getAttribute("notiziaCercata"); 
String tipologia = request.getParameter("tiporisultato");
%>
<center>	<div class="card text-white bg-dark mb-3" style="max-width: 30rem;">
  <div class="card-header">Hai cercato:</div>
  <div class="card-body">
  <%if(tipologia.equals("img")){ %>
  	<img alt="" src="<%=cercata.getImg()%>" width="300" height="300">
  <%}
    else {%>
    <h5 class="card-title"><%=cercata.getTitolo()%></h5>
    <%} %>
   <div class="w3-container w3-blue" id = "indexBar" style="width:<%=cercata.getIndice()%>%"><%=cercata.getIndice()%></div>
  </div>
</div></center>
	<%=cercata.getDescrizione()%>
<%if(risultati.isEmpty()) { %>

<div class="alert alert-danger" role="alert">
  Nessun risultato trovato
</div>
<%} 
else {

	try{
		pag = Integer.parseInt(request.getParameter("page"));
		strIndex = (pag - 1) * pageSize;
		endIndex = Math.min(strIndex + pageSize, risultati.size());
		List<Notizia> visualizzaRisultati = risultati.subList(strIndex, endIndex);
		%>
		
		<div class="alert alert-success" role="alert">
  		Risultati trovati: <%=risultati.size()%>
		</div>
	
<div class="container" style="width:100%;text-align: center">
	<nav aria-label="...">

  <ul class="pagination pagination-lg">
<%
	int pagineTot = (int) Math.ceil((double)risultati.size() / pageSize);
	for (int i = 1; i <= pagineTot; i++) 
		{ %>
    <li class="page-item"><a class="page-link" href="RisultatiNews.jsp?page=<%= i %>&tiporisultato=txt"><%= i %></a></li>

		
	<% } %>
	  </ul>
</nav>
</div>		
		<%
		int offset = 0;
		int rows = visualizzaRisultati.size() / 3;
		int newsDeck = 0;
		for(int i = 0; i < visualizzaRisultati.size(); i++) 
		{		
			Notizia news = (Notizia)visualizzaRisultati.get(i);
			%>

			<div class="card" style="float:left" >
			  <div class="card-body">
			    <h5 class="card-title"><%=news.getTitolo()%></h5>
			    <!--  <p class="card-text"><%=news.getDescrizione()%></p>-->
			    <p class="card-text"><small class="text-muted"><%=news.getData()%></small></p>
			  </div>
			  <img class="card-img-bottom" src="<%=news.getImg()%>" alt="Img non disponibile" width="500" height="500" >
			  <a href="#" class="btn btn-primary">Di: <%=news.getAutore()%></a>
			</div>
			
			<%}%>
		
<%} catch(Exception e){} %>

<% } %>
	<script type="text/javascript" src = "js/progressBarColor.js"></script>
