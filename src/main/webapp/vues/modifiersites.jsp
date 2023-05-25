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
				<div class="row g-2 align-items-center">
					<div class="col-auto">
						<label class="col-form-label" for="name">Nom :</label>
					</div>
					<div class="col-auto">
						<input class="form-control" type="text" id="name" name="name" required>
					</div>
				</div>
				<div class="row g-2 align-items-center">
					<div class="col-auto">
						<label class="col-form-label" for="city">Ville :</label>
					</div>
					<div class="col-auto">
						<input class="form-control" type="text" id="city" name="city" required>
					</div>
				</div>
				<div class="row g-2 align-items-center">
					<div class="col-auto">
						<label class="col-form-label" for="category">Catégorie :</label>
					</div>
					<div class="col-auto">
						<select class="form-select" id="category" name="category" required>
							<option value="stade">Stade</option>
							<option value="salle_de_spectacle">Salle de spectacle</option>
							<option value="lieu_public">Lieu public</option>
							<option value="centre_aquatique">Centre aquatique</option>
						</select>
					</div>
				</div>
				<button class="btn btn-outline-primary" type="submit">Ajouter</button>
			</form>
		</section>
		
		<section>
			<h2>Modifier un site</h2>
			<form id="editSiteForm">
				<div class="row g-2 align-items-center">
					<div class="col-auto">
						<label class="col-form-label" for="editId">ID du site :</label>
					</div>
					<div class="col-auto">
						<input class="form-control"type="text" id="editId" name="editId" required>
					</div>
				</div>
				<div class="row g-2 align-items-center">
					<div class="col-auto">
						<label class="col-form-label" for="newName">Nouveau nom :</label>
					</div>
					<div class="col-auto">
						<input class="form-control" type="text" id="newName" name="newName" required>
					</div>
				</div>
				<div class="row g-2 align-items-center">
					<div class="col-auto">
						<label class="col-form-label" for="newCity">Nouvelle ville :</label>
					</div>
					<div class="col-auto">
						<input class="form-control" type="text" id="newCity" name="newCity" required>
					</div>
				</div>
				<div class="row g-2 align-items-center">
					<div class="col-auto">
						<label class="col-form-label" for="newCategory">Nouvelle catégorie :</label>
					</div>
					<div class="col-auto">		
						<select class="form-select" id="newCategory" name="newCategory" required>
							<option value="stade">Stade</option>
							<option value="salle_de_spectacle">Salle de spectacle</option>
							<option value="lieu_public">Lieu public</option>
							<option value="centre_aquatique">Centre aquatique</option>
						</select>
					</div>
				</div>

				<button class="btn btn-outline-primary" type="submit">Modifier</button>
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