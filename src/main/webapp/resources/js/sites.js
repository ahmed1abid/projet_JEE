async function loadSites() {
    const sitesData = await fetch('/projet_JEE/site-management/sites');
    try {
        const sites = await sitesData.json();
        const siteList = document.getElementById("sitelist");
        let tbodySite = document.createElement("tbody");
        siteList.appendChild(tbodySite);
        
        for (const site of sites) {
            let tr = document.createElement("tr");
            let td_id = document.createElement("td");
            td_id.append(site.id);
            let td_name = document.createElement("td");
            td_name.append(site.name);
            let td_city = document.createElement("td");
            td_city.append(site.city);
            let td_category = document.createElement("td");
            switch(site.category) {
				case "stade":
					td_category.append("Stade");
					break;
				case "salle_de_spectacle":
					td_category.append("Salle de spectacle");
					break;
				case "lieu_public":
					td_category.append("Lieu public");
					break;
				case "centre_aquatique":
					td_category.append("Centre aquatique");
					break;
				default:
					td_category.append("");
			}

            tr.append(td_id, td_name, td_city, td_category);
            tbodySite.appendChild(tr);
        }
    } catch (error) {
        alert("Erreur lors du chargement des sites :" + error);
    }
}

window.onload = loadSites();