<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Index Page</title>
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">MYSINFORMATION</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item active">
              <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Notizie</a>
            </li>
            
            <%
			boolean logged = false;
			if(session.getAttribute("isLogged") != null && session.getAttribute("username")!= null)
			{
				logged = (boolean) session.getAttribute("isLogged");
			}

		if(logged == false){
		%>
				<li class="nav-item">
              <a class="nav-link" href="login.jsp">Accedi</a>
		<%} 
		else{
		%>
			<li class="nav-item">
              <a class="nav-link" href="segnalazioniUtente.jsp">Segnalazioni</a>
            </li>
			<li class="nav-item">
			<a class="nav-link" href="#">Account di <%= session.getAttribute("username") %></a>
			</li>
			<li>
			<a class="nav-link" href="logout">Logout</a>
			</li>
		<%
		}%>
            
          </ul>
        </div>
      </nav>