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

    <div class="topDiv">

        <center><div class="cardCercata">
            <div class="cardCercata__content">
                <h2 class = "titleSearched">HAI CERCATO:</h2> 
                 <%if(tipologia.equals("img")){ %>
  					<img alt="" src="<%=cercata.getImg()%>" width="300" height="300">
  				<%}
                 else {%>
                <h3 class="titleNews"><%=cercata.getTitolo()%></h3>
                 <%} %>
               <h4 class="titleNews"><%=cercata.getDescrizione() %></h4>
                <br>
                <center><div class="w3-container w3-blue" id = "indexBar" style="width:<%=cercata.getIndice()%>%"><%=cercata.getIndice()%></div></center>
            </div>
            
        </div>
   		</center>

    </div>
    <div class="rightDiv" >
        <br>
        <center>
        <button class="button">
            <span class="button_lg">
                <span class="button_sl"></span>
                <span class="button_text">INDIETRO</span>
            </span>
        </button>
    </center>
        <br>


   
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
	
	<nav aria-label="...">

  <center><ul class="pagination pagination-lg">
<%
	int pagineTot = (int) Math.ceil((double)risultati.size() / pageSize);
	for (int i = 1; i <= pagineTot; i++) 
		{ %>
    <li class="page-item"><a class="page-link" href="RisultatiNews.jsp?page=<%= i %>&tiporisultato=txt"><%= i %></a></li>

		
	<% } %>
	  </ul></center>
</nav>
		
		<%
		int offset = 0;
		int rows = visualizzaRisultati.size() / 3;
		int newsDeck = 0;
		for(int i = 0; i < visualizzaRisultati.size(); i++) 
		{		
			Notizia news = (Notizia)visualizzaRisultati.get(i);
			%>

        <div class="news" style=" background-color: rgba(0, 0, 0, 0.842)">
          <div class="card">
            <div class="card2" style="background-image: url(<%=news.getImg()%>)">

            </div>
          </div> 
          <div class="new-info">
          <h4 class="title"> <%=news.getTitolo()%></h4> 
          <h4 class="title"> <%=news.getDescrizione()%></h4>             
          <a href="" class="linkFonte">DI <strong><%=news.getAutore()%></strong></a>
        </div>
        </div>			
			<%}%>
		
<%} catch(Exception e){} %>

<% } %>
 </div>
	<script type="text/javascript" src = "js/progressBarColor.js"></script>
