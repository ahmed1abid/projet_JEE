<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
<script src="../resources/js/sessions.js"></script>
<title>Sessions</title>
	<header>
		<h1>Gestion des sessions</h1>
	</header>

	<main>
		<section>
			<h2>Liste des sessions</h2>
			<a class="btn btn-outline-primary" href="modifiersessions.jsp" role="button">Modifier les sessions</a>
			<!-- Conteneur pour afficher les sessions -->
			<div id="sessionsContainer">
				<table class="table table-hover" id="sessionlist">
					<thead>
						<tr>
							<th scope="col">Code</th>
							<th scope="col">Date</th>
							<th scope="col">Début</th>
							<th scope="col">Fin</th>
							<th scope="col">Discipline</th>
							<th scope="col">Nom du site</th>
							<th scope="col">Ville</th>
							<th scope="col">Description</th>
							<th scope="col">Type</th>
							<th scope="col">Catégorie</th>
						</tr>
					</thead>
					<!-- tbody crée par le script -->
				</table>
			</div>
		</section>

	</main>
<jsp:include page="includes/footer.jsp" />
