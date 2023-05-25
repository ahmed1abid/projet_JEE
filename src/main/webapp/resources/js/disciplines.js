/**
 * 
 */
async function loadDisciplines(){
	const disciplinesData = await fetch('/projet_JEE/discipline-management/disciplines');
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
			if (discipline.flag) {
				td_flag.append("Oui");
			} else {
				td_flag.append("Non");
			}
			
			tr.append(td_name, td_flag);
			tbodyDiscipline.append(tr);
		}
	} catch (error) {
		alert("Error lors du chargement des disciplines: " + error);
	}
}

window.onload = loadDisciplines();