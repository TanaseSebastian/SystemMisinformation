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
ArrayList<Notizia> risultati = (ArrayList<Notizia>)session.getAttribute("risultatiNotizia"); %>
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
		<%

		for(int i = 0; i < visualizzaRisultati.size(); i++) 
		{%>
			<% Notizia news = (Notizia)visualizzaRisultati.get(i); %>
			<div class="card" style="width: 18rem; width: 100%">
 				<img class="card-img-top" src="<%=news.getImg() %>" alt="Card image cap">
 				<div class="card-body">
    				<h5 class="card-title"><%=news.getTitolo()%></h5>
    				<p class="card-text"><%=news.getDescrizione()%></p>
    				<a href="#" class="btn btn-primary" ><%=news.getAutore()%>  <%=news.getData() %></a>
  	   		 	</div>
  			</div>
	<%}%>
<%} catch(Exception e){} %>
<%
	int pagineTot = (int) Math.ceil((double)risultati.size() / pageSize);
	for (int i = 1; i <= pagineTot; i++) 
		{ %>
		<a href="RisultatiNews.jsp?page=<%= i %>"><%= i %></a>
	<% } %>

<% } %>
