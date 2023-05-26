<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>
<body style = " 
/*background-image: url(img/background.jpg)*/;
background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;">
   <!-- FORM  RICERCA MULTIMEDIALE -->

<div class = "searchPage">
  <center>
<div class="btn-group  btn-group-lg" role="group" aria-label="Basic example">
  <button type="button" class="btn btn-secondary" onclick= "changeSearch('news')">Notizie</button>
  <button type="button" class="btn btn-secondary" onclick= "changeSearch('source')">Fonti</button>
</div>	
</center>	 
<form action="ViewUtente" action="GET" id = "newsSearch">
	<div class="form-check form-check-inline">
  		<input class="form-check-input" type="radio" name="opzioniRicerca" id="inlineRadio1" value="testuale" checked="checked">
 		 <label class="form-check-label" for="inlineRadio1">Testuale</label>
	</div>
	<div class="form-check form-check-inline">
 	 <input class="form-check-input" type="radio" name="opzioniRicerca" id="inlineRadio2" value="url">
 	 <label class="form-check-label" for="inlineRadio2">Link File Multimediale</label>
	</div>


	<div class="jumbotron" style = "">
  		<h1 class="display-4">Individua una fake news</h1>
 		 <p class="lead">Inserisci una notizia e scopri subito se è una fake news</p>
 		 <hr class="my-4">
 		 <p>MYSINFORMATION FIGHT SYSTEM collabora con enti esterni indipendenti che hanno in comune la lotta alle fake news</p>
  	     <div class="input-group mb-3">
  			<input type="text" class="form-control" placeholder="Verifica una notizia" aria-label="Verifica una notizia" aria-describedby="button-addon2" name="notizia">
  			<div class="input-group-append">
   				<input type="submit" class="btn btn-outline-secondary" name="UserAction" value = "Verifica Notizia" id="button-addon2" >
  			</div>
		</div>
	</div>

</form>
 <!-- FORM  RICERCA FONTE -->
 <form action="ViewUtente" action="GET" id = "sourceSearch">
	<div class="jumbotron" style = "">
  		<h1 class="display-4">Trova una fonte</h1>
 		 <p class="lead">Non sai se fidarti di una fonte? inserisci qui il nome</p>
 		 <hr class="my-4">
 		 <p>Il sistema si occupa di verificare se la fonte è affidabile o meno</p>
  	     <div class="input-group mb-3">
  			<input type="text" class="form-control" placeholder="Verifica una notizia" aria-label="Verifica una notizia" aria-describedby="button-addon2" name="notizia">
  			<div class="input-group-append">
   				<input type="submit" class="btn btn-outline-secondary" name="UserAction" value = "Verifica Notizia" id="button-addon2" >
  			</div>
		</div>
	</div>

</form>
</div>
<script type="text/javascript" src="js/searchSelector.js"></script>
</body>
</html>