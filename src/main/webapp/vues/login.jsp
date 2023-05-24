<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>
	<jsp:include page="includes/header.jsp" />

    <h1>Connexion</h1>

    <form action="UserServlet" method="post">
        <label for="username">Nom d'utilisateur :</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Mot de passe :</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Se connecter">
    </form>
    
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="register">
        <input type="submit" value="Créer un compte">
    </form>
    
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="delete">
        <label for="deleteUsername">Supprimer un utilisateur :</label>
        <input type="text" id="deleteUsername" name="deleteUsername" required><br>
        <input type="submit" value="Supprimer">
    </form>

	<jsp:include page="includes/footer.jsp" />
	</body>
</html>
