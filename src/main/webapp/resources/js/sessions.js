/**
 * 
 */
async function loadSessions() {
	const sessionsData = await fetch("/projet_JEE/session-management/sessions");
	try {
		const sessions = sessionsData.json();
		const sessionList = document.createElement("sessionlist");
		let tbodySession = document.createElement("tbody");
		sessionList.append(tbodySession);
		
		for (const session of sessions) {
			let tr = document.createElement("tr");
			let td_code = document.createElement("td");
			td_code.append(session.code);
			let td_date = document.createElement("td");
			td_date.append(session.date);
			let td_start_time = document.createElement("td");
			td_start_time.append(session.start_time);
			let td_end_time = document.createElement("td");
			td_end_time.append(session.end_time);
			let td_discipline = document.createElement("td");
			td_discipline.append(session.discipline);
			let td_site_name = document.createElement("td");
			td_site_name.append(session.site_name);
			let td_site_city = document.createElement("td");
			td_site_city.append(session.site_city);
			let td_description = document.createElement("td");
			td_description.append(session.description);
			let td_type = document.createElement("td");
			td_type.append(session.type);
			let td_category = document.createElement("td");
			td_category.append(session.category);
			
			tr.append(td_code, td_date, td_start_time, td_end_time, td_discipline, td_site_name, td_site_city, td_description, td_type, td_category);
			tbodySession.append(tr);
		} 
	} catch (error) {
		console.log("Erreur lors du chargement des sessions: " + error);
	}
	
}

window.onload = loadSessions