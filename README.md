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

## Captures d’écran
-résultat du lab 1 

<img width="707" height="423" alt="resultat lab1" src="https://github.com/user-attachments/assets/aeab2068-e6d9-4528-bba7-6d7dd5989d19" />

-résultat affichage d'un nom qui existe 

<img width="577" height="86" alt="resultat recherche un nom qui exite " src="https://github.com/user-attachments/assets/9df21cc7-cb95-462e-b648-0726b9f470fa" />

-résultat affichage d'un nom qui n'existe  pas

<img width="582" height="94" alt="resulta recherche d&#39;un nom qui n&#39;existe pas " src="https://github.com/user-attachments/assets/9ba31a6d-15b2-4dc5-9b2e-5e7b1e15b40c" />

-résultat TestMachine 

<img width="881" height="691" alt="resultat TestMachine" src="https://github.com/user-attachments/assets/04c4386e-02f2-42d4-98a4-a02446d8a742" />

-résultat TestEmploye 
<img width="1131" height="681" alt="resultat TestEmploye" src="https://github.com/user-attachments/assets/4cf65a0f-e827-4df3-a09e-a6027e1e0633" />

-résultat TestValidation
<img width="1249" height="828" alt="resultat TestValidation" src="https://github.com/user-attachments/assets/08ebc325-7e35-42af-ac64-7979c818fc6f" />

-résultat TestTransaction

<img width="837" height="706" alt="resultat TestTransaction" src="https://github.com/user-attachments/assets/dee41cc2-5058-409e-bfb8-afbefd215fca" />

-résultat TestTransactionEchec

<img width="1763" height="752" alt="resultat TestTransactionEchec" src="https://github.com/user-attachments/assets/73c1e0a4-a938-41b2-9340-b32197c13cf3" />

-résultat TestMachinesparEmploye
<img width="1729" height="709" alt="resultat MachinesparEmploye" src="https://github.com/user-attachments/assets/ff0bf882-c4f0-4c58-9f3e-4a4cc0219da0" />

