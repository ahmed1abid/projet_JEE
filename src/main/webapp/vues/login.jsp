<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-pzjw8w+uaBWp6UE6TEe5PA2L+y0x7dPj7fugx6py0F/pSmlz+H+6Fci4nxl+6nQ1" crossorigin="anonymous">
</head>
<body>
    <jsp:include page="includes/header.jsp" />

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title text-center">Connexion</h3>
                        <form action="UserServlet" method="post">
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
                        <form action="UserServlet" method="post">
                            <input type="hidden" name="action" value="register">
                            <div class="text-center mt-3">
                                <button type="submit" class="btn btn-secondary">Créer un compte</button>
                            </div>
                        </form>
                        <form action="UserServlet" method="post">
                            <input type="hidden" name="action" value="delete">
                            <div class="form-group mt-3">
                                <label for="deleteUsername">Supprimer un utilisateur :</label>
                                <input type="text" class="form-control" id="deleteUsername" name="deleteUsername"
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
