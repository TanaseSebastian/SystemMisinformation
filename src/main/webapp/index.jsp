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
<footer class="d-flex flex-wrap justify-content-between align-items-center border-top" style="position: absolute;top:90%;right:0;left:0;bottom:0; background-color:black; margin:0 ">
    <div class="col-md-4 d-flex align-items-center" style = "padding:0 ">
      <a href="/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
        <svg class="bi" width="30" height="24"><use xlink:href="#bootstrap"></use></svg>
      </a>
      <span class="text-muted">© 2023 SIFF, Inc</span>
    </div>

    <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
      <li class="ms-3"><a class="text-muted" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#twitter"></use></svg></a></li>
      <li class="ms-3"><a class="text-muted" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#instagram"></use></svg></a></li>
      <li class="ms-3"><a class="text-muted" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#facebook"></use></svg></a></li>
    </ul>
  </footer>
<script type="text/javascript" src="js/searchSelector.js"></script>
</body>
</html>