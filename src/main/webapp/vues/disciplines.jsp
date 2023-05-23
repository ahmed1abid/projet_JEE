<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
	<script src="../resources/js/disciplines.js"></script>
<title>Gestion des disciplines</title>
	<header>
		<h1>Gestion des disciplines</h1>
	</header>

	<main>
		<section>
			<h2>Liste des disciplines</h2>
			<a class="btn btn-outline-primary" href="modifierdisciplines.jsp" role="button">Modifier les disciplines</a>
			<!-- Conteneur pour afficher les disciplines -->
			<div id="disciplinesContainer">
				<table class="table table-hover" id="disciplinelist">
					<thead>
						<tr>
							<th scope="col">Nom de la discipline</th>
							<th scope="col">Présente aux jeux paralympiques</th>
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
