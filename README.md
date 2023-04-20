#Projet_JEE

##Distribution de tâches 

Membre 1 : Backend

    Création de l'API REST pour la gestion des compétitions, des sites, des disciplines et des sessions
    Implémentation des règles de gestion sur les dates pour les sessions
    Import du fichier CSV des athlètes dans la base de données
    Implémentation des droits d'accès avec login/password

Étapes nécessaires :

    Création de l'architecture du projet avec le framework Spring Boot
    Définition des endpoints pour les différentes entités : compétitions, sites, disciplines et sessions
    Création des services pour chaque entité, avec les méthodes pour récupérer, créer, mettre à jour et supprimer les données
    Implémentation des règles de gestion pour les sessions, en vérifiant les dates et en proposant des heures alternatives si nécessaire
    Mise en place de l'authentification avec Spring Security
    Import du fichier CSV des athlètes avec la librairie OpenCSV

Membre 2 : Frontend

    Conception de l'interface utilisateur pour la gestion des compétitions, des sites, des disciplines et des sessions
    Création des vues avec les formulaires pour la création/édition des données
    Affichage des messages d'erreur en cas de saisie incorrecte

Étapes nécessaires :

    Conception de l'interface utilisateur avec HTML/CSS/JS
    Création des vues pour chaque entité, avec les formulaires pour la création/édition des données
    Ajout de la logique JavaScript pour la vérification de la saisie et l'affichage des messages d'erreur
    Intégration avec l'API REST pour la récupération et la manipulation des données

Membre 3 : Statistiques et interface grand public

    Conception de l'interface utilisateur pour les statistiques et la consultation des sessions
    Mise en place de la pagination pour l'affichage des athlètes
    Génération des classements des sites et des disciplines

Étapes nécessaires :

    Conception de l'interface utilisateur avec HTML/CSS/JS
    Création des vues pour les statistiques et la consultation des sessions, avec l'affichage des données nécessaires
    Implémentation de la pagination pour l'affichage de la liste des athlètes
    Calcul des classements pour les sites et les disciplines, avec les requêtes SQL appropriées
    Intégration avec l'API REST pour la récupération des données nécessaires

Enfin, la création de la base de données peut être réalisée en amont, par n'importe quel membre de l'équipe. Voici les étapes nécessaires :

    Création du schéma de la base de données avec MySQL
    Définition des tables pour les compétitions, les sites, les disciplines, les sessions et les athlètes
    Définition des relations entre les tables, avec les clés étrangères
    Insertion des données initiales pour les compétitions, les sites et les disciplines (facultatif)




## Base de donnée : 

Tables :

    Competition
    Site
    Discipline
    Session
    Athlete
    User

Relations :

    Une compétition a lieu sur un site.
    Une compétition peut comporter plusieurs disciplines.
    Une discipline peut être présente dans plusieurs compétitions.
    Une session est associée à une compétition, un site et une discipline.
    Une session peut accueillir plusieurs athlètes.
    Un utilisateur peut se connecter à plusieurs sessions.

Attributs :

    Competition
        id (clé primaire)
        name
        start_date
        end_date
    Site
        id (clé primaire)
        name
        address
    Discipline
        id (clé primaire)
        name
    Session
        id (clé primaire)
        competition_id (clé étrangère vers la table Competition)
        site_id (clé étrangère vers la table Site)
        discipline_id (clé étrangère vers la table Discipline)
        start_datetime
        end_datetime
    Athlete
        id (clé primaire)
        first_name
        last_name
        birth_date
        gender
        nationality
    User
        id (clé primaire)
        username
        password
        role
