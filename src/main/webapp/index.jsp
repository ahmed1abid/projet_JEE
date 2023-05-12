<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />
<body>
<script src="script.js"/>></script>
<title>Statistiques</title>
</body>
<main>
	<section>
		<h2>Statistiques par discipline</h2>
		<!-- Tableau des statistiques par discipline -->
		<table id="disciplinelist">
			<thead>
				<tr>
					<th>Discipline</th>
					<th>Nb sessions</th>
					<th>Nb athlètes</th>
					<th>Moyenne d'âge</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				</tr>
				<!-- Les autres lignes de données ici -->
			</tbody>
		</table>
	</section>

	<section>
		<h2>Statistiques par site</h2>
		<!-- Tableau des statistiques par site -->
		<table>
			<thead>
				<tr>
					<th>Site</th>
					<th>Nb sessions</th>
					<th>Nb athlètes</th>
					<th>Moyenne d'âge</th>
				</tr>
			</thead>
			<tbody>
				<tr>

				</tr>
				<tr>

				</tr>
				<!-- Les autres lignes de données ici -->
			</tbody>
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
<jsp:include page="footer.jsp" />