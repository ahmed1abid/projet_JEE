<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestion des disciplines</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script src="script.js"></script>
</head>
<body>
	<header>
		<h1>Gestion des disciplines</h1>
	</header>

	<main>
		<section>
			<h2>Liste des disciplines</h2>
			<!-- Conteneur pour afficher les disciplines -->
			<div id="disciplinesContainer"></div>
		</section>
		
		<section>
			<h2>Ajouter une discipline</h2>
			<form id="addDisciplineForm">
				<label for="name">Nom :</label>
				<input type="text" id="name" name="name" required>
				<label for="flag">Drapeau :</label>
				<select id="flag" name="flag" required>
					<option value="1">Actif</option>
					<option value="0">Inactif</option>
				</select>
				<button type="submit">Ajouter</button>
			</form>
		</section>
		
		<section>
			<h2>Modifier le drapeau d'une discipline</h2>
			<form id="editDisciplineForm">
				<label for="disciplineName">Nom de la discipline :</label>
				<input type="text" id="disciplineName" name="disciplineName" required>
				<label for="newFlag">Nouveau drapeau :</label>
				<select id="newFlag" name="newFlag" required>
					<option value="1">Actif</option>
					<option value="0">Inactif</option>
				</select>
				<button type="submit">Modifier</button>
			</form>
		</section>
		
		<section>
			<h2>Supprimer une discipline</h2>
			<form id="deleteDisciplineForm">
				<label for="disciplineName">Nom de la discipline :</label>
				<input type="text" id="disciplineName" name="disciplineName" required>
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
		// Fonction pour charger les disciplines depuis l'API
		function loadDisciplines() {
			fetch('/projet_JEE/api/discipline-management/disciplines')  
				.then(response => response.json())
				.then(data => {
					const disciplinesContainer = document.getElementById('disciplinesContainer');
					// Parcourir les données des disciplines et les afficher
					data.forEach(discipline => {
						const disciplineElement = document.createElement('div');
						disciplineElement.textContent = discipline.name; // Remplacez 'name' par le nom de l'attribut contenant le nom de la discipline dans vos données

						disciplinesContainer.appendChild(disciplineElement);
					});
				})
				.catch(error => console.error('Erreur lors du chargement des disciplines:', error));
		}

		// Charger les disciplines au chargement de la page
		window.addEventListener('load', loadDisciplines);

		// Soumettre le formulaire d'ajout de discipline
		document.getElementById('addDisciplineForm').addEventListener('submit', function(event) {
			event.preventDefault();
			const form = event.target;
			const name = form.elements['name'].value;
			const flag = form.elements['flag'].value;

			fetch('/projet_JEE/api/discipline-management/new', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body: `name=${encodeURIComponent(name)}&flag=${encodeURIComponent(flag)}`
			})
				.then(response => {
					if (response.ok) {
						form.reset();
						loadDisciplines();
					} else {
						console.error('Erreur lors de la création de la discipline');
					}
				})
				.catch(error => console.error('Erreur lors de la création de la discipline:', error));
		});

		// Soumettre le formulaire de modification du drapeau de discipline
		document.getElementById('editDisciplineForm').addEventListener('submit', function(event) {
			event.preventDefault();
			const form = event.target;
			const disciplineName = form.elements['disciplineName'].value;
			const newFlag = form.elements['newFlag'].value;

			fetch(`/projet_JEE/api/discipline-management/edit?name=${encodeURIComponent(disciplineName)}`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body: `flag=${encodeURIComponent(newFlag)}`
			})
				.then(response => {
					if (response.ok) {
						form.reset();
						loadDisciplines();
					} else {
						console.error('Erreur lors de la modification du drapeau de la discipline');
					}
				})
				.catch(error => console.error('Erreur lors de la modification du drapeau de la discipline:', error));
		});

		// Soumettre le formulaire de suppression de discipline
		document.getElementById('deleteDisciplineForm').addEventListener('submit', function(event) {
			event.preventDefault();
			const form = event.target;
			const disciplineName = form.elements['disciplineName'].value;

			fetch(`/projet_JEE/api/discipline-management/delete?name=${encodeURIComponent(disciplineName)}`, {
				method: 'POST'
			})
				.then(response => {
					if (response.ok) {
						form.reset();
						loadDisciplines();
					} else {
						console.error('Erreur lors de la suppression de la discipline');
					}
				})
				.catch(error => console.error('Erreur lors de la suppression de la discipline:', error));
		});
	</script>
</body>
</html>
