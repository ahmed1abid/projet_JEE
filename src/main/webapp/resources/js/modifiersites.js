
// Soumettre le formulaire d'ajout de site
document.getElementById('addSiteForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const form = event.target;
    const name = form.elements['name'].value;
    const city = form.elements['city'].value;
    const category = form.elements['category'].value;

    fetch('/projet_JEE/api/site-management/new', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `name=${encodeURIComponent(name)}&city=${encodeURIComponent(city)}&category=${encodeURIComponent(category)}`
    })
        .then(response => {
            if (response.ok) {
                form.reset();
                loadSites();
            } else {
                console.error('Erreur lors de la création du site');
            }
        })
        .catch(error => console.error('Erreur lors de la création du site:', error));
});

// Soumettre le formulaire de modification de site
document.getElementById('editSiteForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const form = event.target;
    const siteId = form.elements['siteId'].value;
    const newName = form.elements['newName'].value;
    const newCity = form.elements['newCity'].value;
    const newCategory = form.elements['newCategory'].value;

    fetch(`/projet_JEE/api/site-management/edit?id=${encodeURIComponent(siteId)}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `newName=${encodeURIComponent(newName)}&newCity=${encodeURIComponent(newCity)}&newCategory=${encodeURIComponent(newCategory)}`
    })
        .then(response => {
            if (response.ok) {
                form.reset();
                loadSites();
            } else {
                console.error('Erreur lors de la modification du site');
            }
        })
        .catch(error => console.error('Erreur lors de la modification du site:', error));
});

// Soumettre le formulaire de suppression de site
document.getElementById('deleteSiteForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const form = event.target;
    const siteId = form.elements['siteId'].value;

    fetch(`/projet_JEE/api/site-management/delete?id=${encodeURIComponent(siteId)}`, {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                form.reset();
                loadSites();
            } else {
                console.error('Erreur lors de la suppression du site');
            }
        })
        .catch(error => console.error('Erreur lors de la suppression du site:', error));
});