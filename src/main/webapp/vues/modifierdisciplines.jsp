<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
<main>
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
		</main>
		<script src="../resources/js/modifierdisciplines.js">
		
	</script>
<jsp:include page="includes/footer.jsp" />