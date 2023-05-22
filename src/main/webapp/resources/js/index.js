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
	
	} catch (error) {
		alert("Erreur :" + error);
	}
}

function process(disciplines, sites) {
	const disciplineList = document.getElementById("disciplinelist");
	
	let tbodyDiscipline = document.createElement("tbody");
	disciplineList.appendChild(tbodyDiscipline);
	
	for (const discipline of disciplines){
		let tr = document.createElement("tr");
		let td_name = document.createElement("td");
		td_name.append(discipline.name);
		//var td_nbSession = document.createElement("td");
		//td_nbSession.append("10");
		
		tr.appendChild(td_name);
		//tr.appendChild(td_nbSession);
		tbodyDiscipline.appendChild(tr);
	}
	
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
}

window.onload = loadStat();