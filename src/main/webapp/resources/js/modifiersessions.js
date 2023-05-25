/**
 * 
 */
// Soumettre le formulaire d'ajout de sessions
document.getElementById('addSessionForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const form = event.target;
    const code = form.elements['code'].value;
    const date = form.elements['date'].value;
    let start_time = form.elements['startTime'].value;
    start_time = start_time + ":00";
    let end_time = form.elements['endTime'].value;
    end_time = end_time + ":00";
    const discipline = form.elements['discipline'].value;
    const site = form.elements['site'].value;
    const description = form.elements['description'].value;
    const type = form.elements['type'].value;
    const category = form.elements['category'].value;

    fetch('/projet_JEE/session-management/new', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `code=${encodeURIComponent(code)}&date=${encodeURIComponent(date)}&start_time=${start_time}&end_time=${end_time}&discipline=${discipline}&site=${site}&description=${description}&type=${type}&category=${encodeURIComponent(category)}`
    })
        .then(response => {
            if (response.ok) {
                form.reset();
            } else {
                console.error('Erreur lors de la création du site');
            }
        })
        .catch(error => console.error('Erreur lors de la création du site:', error));
});

// Soumettre le formulaire de modification de site
document.getElementById('editSessionForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const form = event.target;
    const code = form.elements['code'].value;
    const date = form.elements['date'].value;
    let start_time = form.elements['startTime'].value;
    start_time = start_time + ":00";
    let end_time = form.elements['endTime'].value;
    end_time = end_time + ":00";
    const discipline = form.elements['discipline'].value;
    const site = form.elements['site'].value;
    const description = form.elements['description'].value;
    const type = form.elements['type'].value;
    const category = form.elements['category'].value;

    fetch(`/projet_JEE/site-management/edit`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `code=${encodeURIComponent(code)}&date=${encodeURIComponent(date)}&start_time=${start_time}&end_time=${end_time}&discipline=${discipline}&site=${site}&description=${description}&type=${type}&category=${encodeURIComponent(category)}`
    })
        .then(response => {
            if (response.ok) {
                form.reset();
            } else {
                console.error('Erreur lors de la modification du site');
            }
        })
        .catch(error => console.error('Erreur lors de la modification du site:', error));
});

// Soumettre le formulaire de suppression de site
document.getElementById('deleteSessionForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const form = event.target;
    const code = form.elements['sessionCodeToDelete'].value;

    fetch(`/projet_JEE/session-management/delete`, {
        method: 'POST',
        headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: `code=${encodeURIComponent(code)}`
    })
        .then(response => {
            if (response.ok) {
                form.reset();
            } else {
                console.error('Erreur lors de la suppression du site');
            }
        })
        .catch(error => console.error('Erreur lors de la suppression du site:', error));
});