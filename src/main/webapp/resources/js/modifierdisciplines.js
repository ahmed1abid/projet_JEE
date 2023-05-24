// Soumettre le formulaire d'ajout de discipline
document.getElementById('addDisciplineForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const form = event.target;
    const name = form.elements['name'].value;
    const flag = form.elements['flag'].value;

    fetch('/projet_JEE/api/discipline-management/new', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `name=${encodeURIComponent(name)}&flag=${encodeURIComponent(flag)}`
    })
        .then(response => {
            if (response.ok) {
                form.reset();
            } else {
                console.error("Erreur lors de l'ajout de la discipline");
            }
        })
        .catch(error => console.error("Erreur lors de l'ajout de la discipline:", error));
});

// Soumettre le formulaire de modification du drapeau de discipline
document.getElementById('editDisciplineForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const form = event.target;
    const disciplineName = form.elements['disciplineName'].value;
    const newFlag = form.elements['newFlag'].value;

    fetch(`/projet_JEE/api/discipline-management/edit?name=${encodeURIComponent(disciplineName)}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `name=${encodeURIComponent(disciplineName)}&new_flag=${encodeURIComponent(newFlag)}`
    })
        .then(response => {
            if (response.ok) {
                form.reset();
            } else {
                console.error('Erreur lors de la modification de la discipline');
            }
        })
        .catch(error => console.error('Erreur lors de la modification de la discipline:', error));
});

// Soumettre le formulaire de suppression de discipline
document.getElementById('deleteDisciplineForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const form = event.target;
    const disciplineName = form.elements['disciplineName'].value;

    fetch(`/projet_JEE/api/discipline-management/delete?name=${encodeURIComponent(disciplineName)}`, {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                form.reset();
            } else {
                console.error('Erreur lors de la suppression de la discipline');
            }
        })
        .catch(error => console.error('Erreur lors de la suppression de la discipline:', error));
});