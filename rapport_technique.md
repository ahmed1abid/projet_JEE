# Rapport technique - Projet de développement web Java : 

## 1. Introduction

Le projet vise à développer un site web de gestion des événements des Jeux Olympiques de Paris 2024. Le site permettra aux utilisateurs d'accéder aux informations sur les compétitions, les sites, les disciplines, les sessions sportives, les athlètes et les statistiques. Le développement du site utilise Java pour la partie backend, HTML/CSS/JavaScript pour la partie frontend et une base de données MySQL pour le stockage des données.

## 2. Architecture du projet

Le projet suit une architecture classique de développement web  pour la partie backend et des technologies web standards pour la partie frontend. Voici la structure des fichiers utilisés :

   - org/projetjee : Le package contenant les classes Java pour la gestion des athlètes, des disciplines, des sessions, des sites et des utilisateurs.

   -  AppConfig.java : La classe de configuration de l'application Spring Boot.

    - DBManager.java : La classe de gestion de la connexion à la base de données MySQL.

    - config.properties : Le fichier de configuration contenant les informations de connexion à la base de données.

    - webapp : Le répertoire contenant les ressources web.
        - META-INF : Le répertoire pour les fichiers de configuration META-INF.
        - WEB-INF/lib : Le répertoire pour les bibliothèques et les dépendances du projet.
        - resources/js : Le répertoire pour les fichiers JavaScript utilisés dans le frontend.
        - vues : Le répertoire contenant les pages JSP pour les différentes vues du site.
           - includes : Le répertoire pour les fichiers JSP réutilisables inclus dans d'autres pages.
           - accueil.jsp : La page d'accueil du site.
           - athletes.jsp : La page d'affichage des athlètes.
           - competitions.jsp : La page de gestion des compétitions.
           - connexion.jsp : La page de connexion pour l'authentification.
           - disciplines.jsp : La page de gestion des disciplines.
           - index.jsp : La page principale du site affichant les statistiques et la liste des sessions.
           - login.jsp : La page de connexion pour les utilisateurs.
           - modifierdisciplines.jsp : La page de modification des disciplines.
           - modifiersessions.jsp : La page de modification des sessions.
           - modifiersites.jsp : La page de modification des sites.
           - sessions.jsp : La page de gestion des sessions.
           - sites.jsp : La page de gestion des sites.

## 3. Fonctionnalités du site

Le site offre les fonctionnalités suivantes :

   - Affichage des statistiques par discipline : La page index.jsp affiche un tableau des statistiques par discipline, comprenant le nombre de sessions, le nombre d'athlètes et la moyenne d'âge.
   - Affichage des statistiques par site : La page index.jsp affiche un tableau des statistiques par site, comprenant le nombre de sessions, le nombre d'athlètes et la moyenne d'âge.
    Consultation des sessions : La page index.jsp affiche une liste des sessions disponibles avec des liens pour afficher les détails de chaque session.
   -  Gestion des athlètes : La page athletes.jsp permet d'afficher la liste des athlètes et de modifier leurs informations.
   - Gestion des compétitions : La page competitions.jsp permet d'ajouter, modifier et supprimer des compétitions.
   - Gestion des disciplines : La page disciplines.jsp permet d'ajouter, modifier et supprimer des disciplines.
   - Gestion des sessions : La page sessions.jsp permet d'ajouter, modifier et supprimer des sessions.
   - Gestion des sites : La page sites.jsp permet d'ajouter, modifier et supprimer des sites.
   - Authentification des utilisateurs : La page login.jsp permet aux utilisateurs de se connecter avec leurs identifiants.

## 4. Conclusion

Le projet de développement web Java pour le site de gestion des événements des Jeux Olympiques de Paris 2024 offre un ensemble de fonctionnalités pour la gestion des compétitions, des sites, des disciplines, des sessions et des athlètes. En utilisant Java pour la partie backend et des technologies web standards pour la partie frontend, le site offre une expérience utilisateur intuitive et efficace pour les utilisateurs