/**
 * 
 */

function load(){
	loadStat();
}
async function loadStat(){
	const response = await fetch('http://localhost:8080/projet_JEE/api/discipline-management/disciplines');
	console.log(response);
	try {
		var disciplines = await response.json();
		process(disciplines)
	} catch (error) {
		alert("Erreur :" + error);
	}
	/*
	fetch(url,{
		body: JSON.stringify(requestBody)
	})
	.then(response => response.json())
	.then(response => process(response))
	.catch(error => alert("Erreur : " + error));
	*/
}

function process(disciplines) {
	var disciplineList = document.getElementById("disciplinelist");
	
	if (disciplineList.hasChildNodes()){
		disciplineList.removeChild(disciplineList.firstChild);
	}
	
	var table = document.createElement("table");
	disciplineList.appendChild(table);
	
	console.log(disciplines);
	for (var discipline of disciplines){
		console.log(discipline);
		var tr = document.createElement("tr");
		var td_nom = document.createElement("td");
		td_nom.append(discipline.name);
		//var td_nbSession = document.createElement("td");
		//td_nbSession.append("10");
		
		tr.appendChild(td_nom);
		//tr.appendChild(td_nbSession);
		table.appendChild(tr);
	}
}

window.onload = load();