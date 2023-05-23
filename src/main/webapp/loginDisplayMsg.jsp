<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 	
		String[] messages = {"","Utente non trovato o credenziali errate"};
		
		if(session.getAttribute("login-error") != null)
		{
			int msgIndex = (int) session.getAttribute("login-error");
			if(msgIndex < messages.length && msgIndex != 0){
			%>
			<div class="alert alert-danger" role="alert">
			<%=messages[msgIndex]%>  		
			</div>
			<%} 
			session.removeAttribute("login-error");%>
			
		<%} %>
</body>
</html>