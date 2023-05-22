<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<jsp:include page="includes/header.jsp" />
	<script src="../resources/js/index.js"></script>
	<title>Statistiques</title>

	<main>
		<section>
			<h2>Statistiques par discipline</h2>
			<!-- Tableau des statistiques par discipline -->
			<table class="table table-hover" id="disciplinelist">
				<thead>
					<tr>
						<th scope="col">Discipline</th>
						<th scope="col">Nb sessions</th>
						<th scope="col">Nb athlètes</th>
						<th scope="col">Moyenne d'âge</th>
					</tr>
				</thead>
				<!-- tbody crée par le script -->
			</table>
		</section>

		<section>
			<h2>Statistiques par site</h2>
			<!-- Tableau des statistiques par site -->
			<table class=" table table-hover" id="sitelist">
				<thead>
					<tr>
						<th scope="col">Site</th>
						<th scope="col">Nb sessions</th>
						<th scope="col">Nb athlètes</th>
						<th scope="col">Moyenne d'âge</th>
					</tr>
				</thead>
				<!-- tobdy crée par le script -->
			</table>
		</section>

		<section>
			<h2>Liste des sessions</h2>
			<!-- Liste des sessions avec pagination -->
			<ul>
				<li><a href="#">Session 1</a></li>
				<li><a href="#">Session 2</a></li>
				<li><a href="#">Session 3</a></li>
				<li><a href="#">Session 4</a></li>
				<li><a href="#">Session 5</a></li>
				<!-- ... -->
			</ul>
	</main>
	<jsp:include page="includes/footer.jsp" />