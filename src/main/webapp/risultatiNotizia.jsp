<%@page import="java.util.ArrayList"%>
<%@page import="Model.Notizia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<% ArrayList<Notizia> risultati = (ArrayList<Notizia>)session.getAttribute("risultatiNotizia"); %>
<%if(risultati.isEmpty()) { %>

<div class="alert alert-danger" role="alert">
  Nessun risultato trovato
</div>
<%} 
else {
	try{%>
	<div class="alert alert-success" role="alert">
  	Risultati trovati: 1 
	</div>
	<% Notizia news = (Notizia)risultati.get(0); %>
	<div class="card" style="width: 18rem; width: 100%">
 	<img class="card-img-top" src="<%=news.getImg() %>" alt="Card image cap">
 			<div class="card-body">
    		<h5 class="card-title"><%=news.getTitolo()%></h5>
    		<p class="card-text"><%=news.getDescrizione()%></p>
    		<a href="#" class="btn btn-primary" ><%=news.getAutore()%></a>
  	    </div>
  </div>

<%} catch(Exception e){}} %>
