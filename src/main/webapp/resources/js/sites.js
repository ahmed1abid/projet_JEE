async function loadSites() {
    const sitesData = await fetch('http://localhost:8080/projet_JEE/api/site-management/sites');
    try {
        const sites = await sitesData.json();
        const siteList = document.getElementById("sitelist");
        console.log(sites);
        let tbodySite = document.createElement("tbody");
        siteList.appendChild(tbodySite);
        
        for (const site of sites) {
            let tr = document.createElement("tr");
            let td_name = document.createElement("td");
            td_name.append(site.name);
            let td_city = document.createElement("td");
            td_city.append(site.city);
            let td_category = document.createElement("td");
            td_category.append(site.category);

            tr.append(td_name, td_city, td_category);
            tbodySite.appendChild(tr);
        }
    } catch (error) {
        alert("Erreur lors du chargement des sites :" + error);
    }
}

window.onload = loadSites();