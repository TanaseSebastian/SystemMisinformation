<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>
<body>
      
      <form action="ViewUtente" method="GET" > 
      <div class = "searchPage">
     	 <div class="input-group mb-3">
  			<input type="text" class="form-control" placeholder="Verifica una notizia" aria-label="Verifica una notizia" aria-describedby="button-addon2" name="notizia">
  			<div class="input-group-append">
   			 <input type="submit" class="btn btn-outline-secondary" name="UserAction" value = "Verifica Notizia" id="button-addon2" >
  			</div>
		</div>
	</div>
	</form>
	<div class="risultatiNews">
	
	</div>
</body>
</html>