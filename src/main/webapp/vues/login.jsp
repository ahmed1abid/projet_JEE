<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-pzjw8w+uaBWp6UE6TEe5PA2L+y0x7dPj7fugx6py0F/pSmlz+H+6Fci4nxl+6nQ1" crossorigin="anonymous">
        <style>
.confirmation-message {
    background-color: lightgreen;
    padding: 10px;
    margin-bottom: 10px;
    text-align: center;
}
.suppression-message {
    back	ground-color: red;
    padding: 10px;
    margin-bottom: 10px;
    text-align: center;
}
</style>
        
</head>
<body>
    <jsp:include page="includes/header.jsp" />
    <% String message = request.getParameter("message"); %>
							<% if (message != null && !message.isEmpty() && message =="Le compte a été créé avec succès !" ) { %>
  								  <div class="confirmation-message">
      								  <h3><%= message %></h3>
    							  </div>
							<% } %>
	   <% String message2 = request.getParameter("message"); %>
							<% if (message2 != null && !message2.isEmpty() && message2 !="Le compte a été créé avec succès !" ) { %>
  								  <div class="suppression-message">
      								  <h3><%= message2 %></h3>
    							  </div>
							<% } %>
    
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title text-center">Connexion</h3>
                        <form action="/projet_JEE/user-management/login" method="post">
                            <div class="form-group">
                                <label for="username">Nom d'utilisateur :</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Mot de passe :</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Se connecter</button>
                            </div>
                        </form>
                        <form action="/projet_JEE/user-management/registration" method="post">
                            <input type="hidden" name="action" value="register">
                            <div class="form-group">
                                <label for="registerUsername">Nom d'utilisateur :</label>
                                <input type="text" class="form-control" id="registerUsername" name="registerUsername" required>
                            </div>
                            <div class="form-group">
                                <label for="registerPassword">Mot de passe :</label>
                                <input type="password" class="form-control" id="registerPassword" name="registerPassword" required>
                            </div>
                            <div class="form-group">
                                <label for="registerRole">Rôle :</label>
                                <input type="text" class="form-control" id="registerRole" name="registerRole" required>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-secondary">Créer un compte</button>
                            </div>
                           

                        </form>
                        	
                        <form action="/projet_JEE/user-management/delete" method="post">
                            <input type="hidden" name="action" value="delete">
                            <div class="form-group mt-3">
                                <label for="deleteUsername">Nom d'utilisateur :</label>
                                <input type="text" class="form-control" id="deleteUsername" name="deleteUsername"
                                    required>
                            </div>
                            <div class="form-group">
                                <label for="deletePassword">Mot de passe :</label>
                                <input type="password" class="form-control" id="deletePassword" name="deletePassword"
                                    required>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-danger">Supprimer</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="includes/footer.jsp" />
</body>
</html>
