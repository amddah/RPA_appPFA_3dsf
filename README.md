# Application de Traitement des Emails

Cette application est conçue pour automatiser la gestion des emails entrants en offrant diverses fonctionnalités telles que l'extraction des pièces jointes, la réponse automatique aux emails et la gestion des flux de travail liés aux emails.

## Fonctionnalités Principales

- **Récupération des Emails** : Récupère automatiquement les emails entrants de la boîte de réception configurée.
- **Extraction des Pièces Jointes** : Extrait et enregistre les pièces jointes des emails entrants dans des dossiers appropriés.
- **Réponse Automatique aux Emails** : Génère automatiquement des réponses aux emails en utilisant un modèle d'intelligence artificielle.
- **Sauvegarde des Emails** : Sauvegarde les emails traités dans une base de données pour une consultation ultérieure.
- **Gestion des Notifications** : Notifie l'utilisateur à chaque événement pertinent, comme la réception d'un nouvel email ou l'extraction d'une pièce jointe.

## Prérequis

Avant de pouvoir exécuter l'application, assurez-vous d'avoir installé les prérequis suivants :

- **JDK 19** : La dernière version de Java est nécessaire pour exécuter l'application Spring Boot.

- **MySQL** : Base de données utilisée pour stocker les emails traités et les informations pertinentes.

## Repositories associés
- Front-end de l'application : [Rpa_FrontEnd](https://github.com/amddah/Rpa_FrontEnd)
- Application d'envoi des réponses aux emails : [EmailSender](https://github.com/amddah/EmailSender.git)
= application de generation des reponse : [Api Flask](https://github.com/amddah/Rpa_FrontEnd)


## Installation

1. Clonez le dépôt Git :
    ```bash
    git clone https://github.com/username/RPA_appPFA_3dsf.git
    ```
2. Accédez au dossier de l'application :
    ```bash
    cd RPA_appPFA_3dsf
    ```
3. Configurez les paramètres de la base de données MySQL et de l'email dans le fichier `application.properties`.



## Utilisation

1. Connectez-vous à l'application via la page de connexion.
2. Configurez vos informations d'email et votre mot de passe d'application.
3. L'application commence à surveiller votre boîte de réception pour les emails entrants et traite automatiquement les pièces jointes et les réponses.

## Perspectives d'Avenir

L'application peut évoluer en intégrant de nouvelles fonctionnalités comme la génération de rapports automatiques, la gestion des processus financiers, et une classification plus avancée des emails basée sur le contenu.

## Contributeurs

- **Nom** : Abdelkbir Amddah
