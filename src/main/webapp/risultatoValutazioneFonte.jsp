<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Model.Fonte"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%@include file="header.jsp"%>
<%
Fonte f = (Fonte ) session.getAttribute("fonte-valutata");
%>
<div class="container">
  <form>
  	<h1>Hai cercato:  <strong><%=f.getNome()%></strong></h1>
 
  	<div class="w3-light-grey">
   	 	<div class="w3-container w3-blue" id = "indexBar" style="width:<%=f.getIndice()%>%"><%=f.getIndice()%></div>
 	</div>
 	
	<script type="text/javascript" src = "js/progressBarColor.js"></script>
</form>
</div>
</body>
</html>