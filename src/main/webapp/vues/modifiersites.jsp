<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/>
<title>Gestion des sites</title>
<body>
	<header>
		<h1>Gestion des sites</h1>
	</header>

	<main>
		<section>
			<h2>Ajouter un site</h2>
			<form id="addSiteForm">
				<label for="name">Nom :</label>
				<input type="text" id="name" name="name" required>
				<label for="city">Ville :</label>
				<input type="text" id="city" name="city" required>
				<label for="category">Catégorie :</label>
				<select id="category" name="category" required>
					<option value="stade">Stade</option>
					<option value="salle_de_spectacle">Salle de spectacle</option>
					<option value="lieu_public">Lieu public</option>
					<option value="centre_aquatique">Centre aquatique</option>
				</select>
				<button id="btnAddSite" type="submit">Ajouter</button>
			</form>
		</section>
		
		<section>
			<h2>Modifier un site</h2>
			<form id="editSiteForm">
				<label for="siteId">ID du site :</label>
				<input type="text" id="siteId" name="siteId" required>
				<label for="newName">Nouveau nom :</label>
				<input type="text" id="newName" name="newName" required>
				<label for="newCity">Nouvelle ville :</label>
				<input type="text" id="newCity" name="newCity" required>
				<label for="newCategory">Nouvelle catégorie :</label>
				<select id="newCategory" name="newCategory" required>
					<option value="stadium">Stade</option>
					<option value="performance_hall">Salle de spectacle</option>
					<option value="public_place">Lieu public</option>
					<option value="aquatic_center">Centre aquatique</option>
				</select>
				<button type="submit">Modifier</button>
			</form>
		</section>
		
		<section>
			<h2>Supprimer un site</h2>
			<form id="deleteSiteForm">
				<label for="siteId">ID du site :</label>
				<input type="text" id="siteId" name="siteId" required>
				<button type="submit">Supprimer</button>
			</form>
		</section>
		
		<nav>
			<ul>
				<li><a href="index.jsp">Retour</a></li>
			</ul>
		</nav>
	</main>

	<script src="../resources/js/modifiersites.js">

	</script>
<jsp:include page="includes/footer.jsp" />