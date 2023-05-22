<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestion des sites</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		<h1>Gestion des sites</h1>
	</header>

	<main>
		<section>
			<h2>Liste des sites</h2>
			<!-- Conteneur pour afficher les sites -->
			<div id="sitesContainer"></div>
		</section>
		
		<section>
			<h2>Ajouter un site</h2>
			<form id="addSiteForm">
				<label for="name">Nom :</label>
				<input type="text" id="name" name="name" required>
				<label for="city">Ville :</label>
				<input type="text" id="city" name="city" required>
				<label for="category">Catégorie :</label>
				<select id="category" name="category" required>
					<option value="stadium">Stade</option>
					<option value="performance_hall">Salle de spectacle</option>
					<option value="public_place">Lieu public</option>
					<option value="aquatic_center">Centre aquatique</option>
				</select>
				<button type="submit">Ajouter</button>
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

	<script>
		// Fonction pour charger les sites depuis l'API
		function loadSites() {
			fetch('/projet_JEE/api/site-management/sites')  
					.then(response => response.json())
					.then(data => {
						const sitesContainer = document.getElementById('sitesContainer');
						// Parcourir les données des sites et les afficher
						data.forEach(site => {
							const siteElement = document.createElement('div');
							siteElement.textContent = site.name; // Remplacez 'name' par le nom de l'attribut contenant le nom du site dans vos données

							sitesContainer.appendChild(siteElement);
						});
					})
					.catch(error => console.error('Erreur lors du chargement des sites:', error));
		}

		// Charger les sites au chargement de la page
		window.addEventListener('load', loadSites);

		// Soumettre le formulaire d'ajout de site
		document.getElementById('addSiteForm').addEventListener('submit', function(event) {
			event.preventDefault();
			const form = event.target;
			const name = form.elements['name'].value;
			const city = form.elements['city'].value;
			const category = form.elements['category'].value;

			fetch('/projet_JEE/api/site-management/new', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body: `name=${encodeURIComponent(name)}&city=${encodeURIComponent(city)}&category=${encodeURIComponent(category)}`
			})
				.then(response => {
					if (response.ok) {
						form.reset();
						loadSites();
					} else {
						console.error('Erreur lors de la création du site');
					}
				})
				.catch(error => console.error('Erreur lors de la création du site:', error));
		});

		// Soumettre le formulaire de modification de site
		document.getElementById('editSiteForm').addEventListener('submit', function(event) {
			event.preventDefault();
			const form = event.target;
			const siteId = form.elements['siteId'].value;
			const newName = form.elements['newName'].value;
			const newCity = form.elements['newCity'].value;
			const newCategory = form.elements['newCategory'].value;

			fetch(`/projet_JEE/api/site-management/edit?id=${encodeURIComponent(siteId)}`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body: `newName=${encodeURIComponent(newName)}&newCity=${encodeURIComponent(newCity)}&newCategory=${encodeURIComponent(newCategory)}`
			})
				.then(response => {
					if (response.ok) {
						form.reset();
						loadSites();
					} else {
						console.error('Erreur lors de la modification du site');
					}
				})
				.catch(error => console.error('Erreur lors de la modification du site:', error));
		});

		// Soumettre le formulaire de suppression de site
		document.getElementById('deleteSiteForm').addEventListener('submit', function(event) {
			event.preventDefault();
			const form = event.target;
			const siteId = form.elements['siteId'].value;

			fetch(`/projet_JEE/api/site-management/delete?id=${encodeURIComponent(siteId)}`, {
				method: 'POST'
			})
				.then(response => {
					if (response.ok) {
						form.reset();
						loadSites();
					} else {
						console.error('Erreur lors de la suppression du site');
					}
				})
				.catch(error => console.error('Erreur lors de la suppression du site:', error));
		});
	</script>
</body>
</html>
