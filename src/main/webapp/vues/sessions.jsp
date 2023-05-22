<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestion des sessions</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script src="script.js"></script>
</head>
<body>
	<header>
		<h1>Gestion des sessions</h1>
	</header>

	<main>
		<section>
			<h2>Liste des sessions</h2>
			<!-- Conteneur pour afficher les sessions -->
			<div id="sessionsContainer"></div>
		</section>
		
		<section>
			<h2>Ajouter une session</h2>
			<form id="addSessionForm">
				<label for="code">Code :</label>
				<input type="text" id="code" name="code" required>
				<label for="date">Date :</label>
				<input type="date" id="date" name="date" required>
				<label for="startTime">Heure de début :</label>
				<input type="time" id="startTime" name="startTime" required>
				<label for="endTime">Heure de fin :</label>
				<input type="time" id="endTime" name="endTime" required>
				<label for="discipline">Discipline :</label>
				<input type="text" id="discipline" name="discipline" required>
				<label for="site">Site :</label>
				<input type="text" id="site" name="site" required>
				<label for="description">Description :</label>
				<textarea id="description" name="description" required></textarea>
				<label for="type">Type :</label>
				<select id="type" name="type" required>
					<option value="COURS">Cours</option>
					<option value="SEMINAIRE">Séminaire</option>
					<option value="ATELIER">Atelier</option>
				</select>
				<label for="category">Catégorie :</label>
				<select id="category" name="category" required>
					<option value="PRINCIPALE">Principale</option>
					<option value="COMPLEMENTAIRE">Complémentaire</option>
				</select>
				<button type="submit">Ajouter</button>
			</form>
		</section>
		
		<section>
			<h2>Modifier une session</h2>
			<form id="editSessionForm">
				<label for="sessionCode">Code de la session :</label>
				<input type="text" id="sessionCode" name="sessionCode" required>
				<label for="newDate">Nouvelle date :</label>
				<input type="date" id="newDate" name="newDate" required>
				<label for="newStartTime">Nouvelle heure de début :</label>
				<input type="time" id="newStartTime" name="newStartTime" required>
				<label for="newEndTime">Nouvelle heure de fin :</label>
				<input type="time" id="newEndTime" name="newEndTime" required>
				<label for="newDiscipline">Nouvelle discipline :</label>
				<input type="text" id="newDiscipline" name="newDiscipline" required>
				<label for="newSite">Nouveau site :</label>
				<input type="text" id="newSite" name="newSite" required>
				<label for="newDescription">Nouvelle description :</label>
				<textarea id="newDescription" name="newDescription" required></textarea>
				<label for="newType">Nouveau type :</label>
				<select id="newType" name="newType" required>
					<option value="COURS">Cours</option>
					<option value="SEMINAIRE">Séminaire</option>
					<option value="ATELIER">Atelier</option>
				</select>
				<label for="newCategory">Nouvelle catégorie :</label>
				<select id="newCategory" name="newCategory" required>
					<option value="PRINCIPALE">Principale</option>
					<option value="COMPLEMENTAIRE">Complémentaire</option>
				</select>
				<button type="submit">Modifier</button>
			</form>
		</section>
		
		<section>
			<h2>Supprimer une session</h2>
			<form id="deleteSessionForm">
				<label for="sessionCodeToDelete">Code de la session :</label>
				<input type="text" id="sessionCodeToDelete" name="sessionCodeToDelete" required>
				<button type="submit">Supprimer</button>
			</form>
		</section>
	</main>
</body>
</html>
