<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Document</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/loginform.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</head>
<body>
  <%@include file="header.jsp"%>
    
    <!--  <div class="login-div">
     
        <form action = "ViewUtente" method = "POST">
            <br>
            <div class="form-group">
              <label for="exampleInputEmail1">Username</label>
              <input type="text" class="form-control" name = "username" id="exampleInputEmail1"  placeholder="Enter email">
            </div>
            <br>
            <div class="form-group">
              <label for="exampleInputPassword1">Password</label>
              <input type="password" class="form-control" name = "pw" id="exampleInputPassword1" placeholder="Password">
            </div>

            <center><input type="submit" class="btn btn-primary" name= "UserAction" value="login"></center>
            
          </form>
          <br>
           <center><h6>Non sei ancora registrato? <a href="reg.html">Clicca qui</a></h6></center>
    </div>-->
     <%@include file="loginDisplayMsg.jsp"%>
<div class="login-box">
 
  <form action = "ViewUtente" method = "POST">
    <div class="user-box">
      <input type="text" name="username" required="">
      <label>Username</label>
    </div>
    <div class="user-box">
      <input type="password" name="pw" required="">
      <label>Password</label>
    </div><center>

    <input type="submit" class = "input-btn" name= "UserAction" value="login">
    </center>
    <br>
    <center><h6 style="color:white">Non sei ancora registrato? <a href="reg.html" style="color:#fff" >Clicca qui</a></h6>< </center>
  </form>
</div>
<%@include file="footer.jsp"%>
</body>
</html>