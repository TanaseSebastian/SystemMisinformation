<%@ page language="java" import="java.util.*,Model.*,Control.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp"%>
<%
DBManager db = new DBManager();
ArrayList<Fonte> elencoFonti = db.getFonti();
Utente user = (Utente) session.getAttribute("utente");
ArrayList<Fonte> fontiBloccate = db.getFontiBloccate(user);
Fonte f;

%>
<div class="container">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
  	  		<li class="breadcrumb-item active" aria-current="page">Fonti bloccate</li>
  		</ol>
	</nav>
	<form action="ViewUtente" method = "POST">
  <div class="form-group">
    <label for="exampleFormControlSelect1">Seleziona Fonte</label>
    <select class="form-control" id="exampleFormControlSelect1" name ="idFonteDaBloccare">
     <option value="" disabled selected hidden>Seleziona la fonte</option>
							<% for(int i=0;i<elencoFonti.size();i++) 
						    {
								boolean trovata = false;
							 f=(Fonte)elencoFonti.get(i);
								for(int j = 0; j < fontiBloccate.size(); j++)
								{
									Fonte bloccata = fontiBloccate.get(j);
									if(bloccata.getId_Fonte() == f.getId_Fonte())
										trovata = true;
								}
								if(trovata == false)
								{
									 %>
							<option name="fonteDaBloccare" value=<%=f.getId_Fonte()%>><%=f.getNome()%></option>
						 <%
						    }
						 %>
						
							<%
							 trovata = false;
						    }
						 %>
						</select>
	
  </div>
  <input type="submit" class="btn btn-outline-secondary" name="UserAction" value = "Blocca Fonte" id="button-addon2">
  </form>
	<div class="table-responsive-xl">
  		<table class="table">
    		<tr>
    			<th>FONTE</th>
   				 <th>INDICE</th>
    		</tr>
							<% for(int i=0;i<fontiBloccate.size();i++) 
						    {
							 f=(Fonte)fontiBloccate.get(i);
						 %>
							<tr>
								<td><%=f.getNome() %> </td>
								<td><%=f.getIndice() %> </td>
							</tr>
							<%
						    }
						 %>    		
    		
  		</table>
</div>
</div>
</body>
</html>