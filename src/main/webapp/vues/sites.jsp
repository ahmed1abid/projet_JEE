<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<jsp:include page="includes/header.jsp" />
	<script src="../resources/js/sites.js"></script>
	<title>Sites</title>
	<header>
		<h1>Sites</h1>
	</header>

	<main>
		<section>
			<h2>Liste des sites</h2>
			<a class="btn btn-outline-primary" href="modifiersites.jsp" role="button">Modifier les sites</a>
			<!-- Conteneur pour afficher les sites -->
			<div id="sitesContainer">
				<table class="table table-hover" id="sitelist">
					<thead>
						<tr>
							<td scope="col">N° </td>
							<th scope="col">Nom du site</th>
							<th scope="col">Ville</th>
							<th scope="col">Catégorie</th>
						</tr>
					</thead>
					<!-- tbody crée par le script -->
				</table>
			</div>
		</section>

		<nav>
			<ul>
				<li><a href="index.jsp">Retour</a></li>
			</ul>
		</nav>
	</main>
	<jsp:include page="includes/footer.jsp" />