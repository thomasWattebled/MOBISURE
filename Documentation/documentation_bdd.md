<h1> BDD </h1>

<h3> Connexion </h3>
Dans une invite de commande : <strong> ssh root@45.147.99.35" </strong> 

mot de passe : <strong> "vrlVOZhEAF" </strong>

Il faut ensuite tapez cette commande : ``` mysql -u root -p ```

et enfin : ```use Mobisure;```

Il est possible de consulté les differentes bases de données avec la commande ```show tables;```


<h3> Table Client </h3>

| id  | civilite | numero_client | mot_de_passe | date_naissance | nom | prenom | email | adresse | phone_number | date_creation |
| :---------------: |:---------------:| :-----:| :-----:| :-----:| :-----:| :-----:| :-----:| :-----:| :-----:| :-----:|
| 1 | M.| CL1 | client1| 2000-01-01 | client | client | client.client@example.com | 1 rue client, Paris | 0606060606 | 2024-12-09 09:29:23 |

On retrouve ici les informations principales d'un Client. On a un champ date_creation qui va nous permettre de savoir quand de nouveaux clients sont suceptibles de contracté un contrat.

<h3> Requête </h3>

| id  | client_id | titre | description | statut | date_creation | date_modification | 
| :---------------: |:---------------:| :-----:| :-----:| :-----:| :-----:| :-----:| 



Une requete est associé à un client, on retrouve egalement un statut, la demande peut être En attente, En cours, terminé, Annulée. Par default à la création le statut de la requête est En attente. On a également un champ date_modification qui va nous permettre de savoir quand le status de requête évolue 