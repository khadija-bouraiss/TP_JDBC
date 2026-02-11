# TP JDBC – Gestion de Base de Données MySQL

Nom :Bouraiss 
Établissement :FSTM 
Date :11 Février 2025  

---

## Description

Ce dépôt contient les travaux pratiques JDBC réalisés dans le cadre du module "Java Avancée : Bases de Données, Réseau, Concurrence et Interfaces".  
Les TP portent sur la connexion à une base MySQL et la manipulation des données en Java avec JDBC.

---

## Contenu du projet

- **Lab 1 : ExoJDBC**  
  Statistiques sur les développeurs à partir d’une base de données.

- **Lab 2 : Gestion Machines & Employés**  
  Application JDBC utilisant une architecture en couches (DAO / Service).

---
## Lab 1 – ExoJDBC
### Objectifs
- Connexion à MySQL avec JDBC  
- Utilisation de "Statement" et "PreparedStatement"  
- Requêtes SQL avec fonctions statistiques  
- Saisie utilisateur avec "Scanner"
### Fonctionnalités
- Affichage du maximum de scripts par jour  
- Classement des développeurs selon le nombre de scripts  
- Calcul du total et de la moyenne des scripts  
- Recherche sécurisée par nom de développeur  

---

## Lab 2 – Gestion Machines & Employés

### Objectifs
- Mise en place d’une architecture en couches  
- Gestion des entités **Employé** et **Machine**  
- Implémentation des opérations CRUD  
- Gestion des relations et des transactions

### Architecture
- util → Connexion à la base (Singleton)
- entities → Classes métier
- dao → Accès aux données
- service → Logique métier
- app → Classes de test

