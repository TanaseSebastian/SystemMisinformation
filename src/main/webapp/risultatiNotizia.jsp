<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<% ArrayList<String> risultati = (ArrayList<String>)session.getAttribute("risultatiNotizia"); %>

<% for(int i = 0; i < risultati.size(); i++) {%>
<h4><%=risultati.get(i) %></h4>

<%} %>
