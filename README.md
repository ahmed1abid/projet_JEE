#Projet_JEE

#informations importantes  : 

groupe 9 (Laurent, Ahmed, Axel)

l'url d'accueil : http://localhost:8080/projet_JEE/vues/index.jsp

les comptes à utiliser : 
 - admin : ndc : admin   ||    password : admin 
 - gesC : ndc : test1    ||    password : test1
 - gesS : ndc : test2    ||    password : test2
 




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
    get pour nombre de sessions, athlètes, et moyenne d'âge pour une discipline donné

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

    Site
    Discipline
    Session
    Athlete
    User

Relations :

   

Attributs :

    Site
    
        name (primary key)
        city (primary key)
        category
        
    Discipline
    
        name (primary key)
        flag
        
    Session
    
        code (primary key)
        date
        start_time
        end_time
        discipline
        site_name
        site_city
        description
        type
        category
        
    Athlete
    
        first_name (primary key)
        last_name (primary key)
        birth_date
        gender
        nationality
        
    User
    
        username (primary key)
        password
        role
