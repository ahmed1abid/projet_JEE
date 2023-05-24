/**
 * 
 */
async function loadStat(){
	const disciplinesData = await fetch('http://localhost:8080/projet_JEE/api/discipline-management/disciplines');
	const sitesData = await fetch('http://localhost:8080/projet_JEE/api/site-management/sites');
	try {
		const disciplines = await disciplinesData.json();
		const sites = await sitesData.json();
		process(disciplines, sites);

		// Disciplines
		const disciplineList = document.getElementById("disciplinelist");
	
		let tbodyDiscipline = document.createElement("tbody");
		disciplineList.appendChild(tbodyDiscipline);
		
		for (const discipline of disciplines){
			let tr = document.createElement("tr");
			let td_name = document.createElement("td");
			td_name.append(discipline.name);
			
			const stats = await getStats(discipline.name);
			let td_nbSessions = document.createElement("td");
			td_nbSessions.append(stats.nbSessions);

			let td_nbAthletes = document.createElement("td");
			td_nbAthletes.append(stats.nbAtheletes);

			let td_averageAge = document.createElement("td");
			td_averageAge.append(stats.averageAge);
			
			tr.append(td_name, td_nbSessions, td_nbAthletes, td_averageAge);
			tbodyDiscipline.appendChild(tr);
		}

		//Sites
		const siteList = document.getElementById("sitelist");
		let tbodySite = document.createElement("tbody");
		siteList.appendChild(tbodySite);
		
		for (const site of sites) {
			let tr = document.createElement("tr");
			let td_name = document.createElement("td");
			td_name.append(site.name);
			
			tr.appendChild(td_name);
			tbodySite.appendChild(tr);
		}
	
	} catch (error) {
		alert("Erreur :" + error);
	}
}

async function getStats(discipline) {
	const statsData = await fetch(`http://localhost:8080/projet_JEE/api/discipline-management/statistics?discipline=${discipline}`, {
		method: 'GET'
	});
	const stats = statsData.json();
	return stats;
} 

window.onload = loadStat();