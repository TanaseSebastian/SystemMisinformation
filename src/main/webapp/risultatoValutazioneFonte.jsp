<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Model.Fonte"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/circleBar.css">
<title>Insert title here</title>
</head>
<%@include file="header.jsp"%>
<%
Fonte[] f = (Fonte[] ) session.getAttribute("fonte-valutata");
Fonte fonteCercata = f[0];
Fonte valutatore = f[1];
%>
<div class="container">
  <form>
  	<h1>Hai cercato:  <strong><%=fonteCercata.getNome()%></strong></h1>
 	<% if(fonteCercata.getIndice() < 0)
 		{
 		%> 
 		<div class="alert alert-danger" role="alert">
  			Fonte non trovata
		</div> 
 	<% } else {%>
  	<div class="w3-light-grey">
   	 	<div class="w3-container w3-blue" id = "indexBar" style="width:<%=fonteCercata.getIndice()%>%"><%=fonteCercata.getIndice()%></div>
 	</div>
 <div class="jumbotron">
  <h1 class="display-4">Fonte verificata da</h1>
  <p class="lead"><%=valutatore.getNome()%></p>
  <hr class="my-4">
   	<div class="w3-light-grey">
   	 	<div class="w3-container w3-blue" style="width:<%=valutatore.getIndice()%>%"><%=valutatore.getIndice()%></div>
 	</div> 
 
</div>	

	<script type="text/javascript" src = "js/progressBarColor.js"></script>
	<%} %>
</form>
</div>
</body>
</html>