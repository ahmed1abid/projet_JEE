/**
 * 
 */
async function loadDisciplines(){
	const disciplinesData = await fetch('http://localhost:8080/projet_JEE/api/discipline-management/disciplines');
	try {
		const disciplines = await disciplinesData.json();
		const disciplineList = document.getElementById("disciplinelist");
		let tbodyDiscipline = document.createElement("tbody");
		disciplineList.appendChild(tbodyDiscipline);
		
		for (const discipline of disciplines) {
			let tr = document.createElement("tr");
			let td_name = document.createElement("td");
			td_name.append(discipline.name);
			let td_flag = document.createElement("td");
			td_flag.append(discipline.flag);
			
			tr.append(td_name, td_flag);
			tbodyDiscipline.append(tr);
		}
	} catch (error) {
		alert("Error lors du chargement des disciplines: " + error);
	}
}

window.onload = loadDisciplines();