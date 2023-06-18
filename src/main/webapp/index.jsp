<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<html>
<head>

</head>

   <!-- FORM  RICERCA MULTIMEDIALE -->
  <%@include file="header.jsp"%>
<div class = "searchPage">
<div style = "margin-top: 9%; text-align: center">
<div class="btn-group  btn-group-lg" role="group" aria-label="Basic example">
  <button type="button" class="btn btn-light" onclick= "changeSearch('news')">Notizie</button>
  <button type="button" class="btn btn-dark" onclick= "changeSearch('source')">Fonti</button>
</div>	
 </div>
<form action="ViewUtente" action="GET" id = "newsSearch">

<!--  BARRA DI RICERCA -->
	  	     <div class="input-group mb-3" style = "padding-top: 10%;">
  	<input type="text" style="background-color: #ffffff00;color: white;  " class="form-control"  aria-label="Verifica una notizia" aria-describedby="button-addon2" name="notizia">
 
  			<div class="input-group-append">
   				<input type="submit" class="btn btn-info" name="UserAction" value = "Verifica Notizia" id="button-addon2" onsubmit = "showLoading()">
  			</div>
  			
		</div>
	<center>
	<div class="form-check form-check-inline" style= "color:white">
  		<input class="form-check-input" type="radio" name="opzioniRicerca" id="inlineRadio1" value="testuale" checked="checked">
 		 <label class="form-check-label" for="inlineRadio1">TESTUALE</label>
	</div>
	<div class="form-check form-check-inline" style= "color:white">
 	 <input class="form-check-input" type="radio" name="opzioniRicerca" id="inlineRadio2" value="multimedia">
 	 <label class="form-check-label" for="inlineRadio2">MULTIMEDIALE</label>
	</div>
	</center>
</form>
 <!-- FORM  RICERCA FONTE -->
 <form action="ViewUtente" action="GET" id = "sourceSearch">

  	     <div class="input-group mb-3" style = "padding-top: 10%;">
  			<input type="text" style="background-color: #ffffff00;color: white;" class="form-control" placeholder="Verifica una fonte" aria-label="Verifica una fonte aria-describedby="button-addon2" name="fonte">
  			<div class="input-group-append">
   				<input type="submit" class="btn btn-info" name="UserAction" value = "Verifica Fonte" id="button-addon2" onClick = "showLoading()">
  			</div>
		</div>
	
<script>
var formSource = document.getElementById("sourceSearch");
	formSource.style.display = "none";
</script>
</form>

</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="js/searchSelector.js"></script>
</body>
</html>