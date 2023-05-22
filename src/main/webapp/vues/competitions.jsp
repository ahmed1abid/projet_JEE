<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<jsp:include page="includes/header.jsp" />

	<body>
		<header>
			<h1>Compétitions</h1>
		</header>

		<main>
			<section>
				<h2>Liste des compétitions</h2>
				<!-- Conteneur pour afficher les compétitions -->
				<div id="competitionsContainer"></div>
			</section>

			<nav>
				<ul>
					<li><a href="index.jsp">Retour</a></li>
				</ul>
			</nav>
		</main>

		<script>
			// Fonction pour charger les compétitions depuis l'API
			function loadCompetitions() {
				fetch('/projet_JEE/api/discipline-management/disciplines')
					.then(response => response.json())
					.then(data => {
						const competitionsContainer = document.getElementById('competitionsContainer');
						// Parcourir les données des compétitions et les afficher
						data.forEach(competition => {
							const competitionElement = document.createElement('div');
							competitionElement.textContent = competition.nom; // Remplacez 'nom' par le nom de l'attribut contenant le nom de la compétition dans vos données

							competitionsContainer.appendChild(competitionElement);
						});
					})
					.catch(error => console.error('Erreur lors du chargement des compétitions:', error));
			}

			// Charger les compétitions au chargement de la page
			window.addEventListener('load', loadCompetitions);
		</script>
		<jsp:include page="includes/footer.jsp" />