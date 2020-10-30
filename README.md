Ce logiciel permet de calculer le prix par élève des sorties scolaires selon l'âge des élèves, le moyen de transport et d'autres contraintes (voir la section fichier de sortie plus bas). Le logiciel tient également des statistiques sur le nombre total d'activité, le coût de le plus élevé, le nombre d'activité fait en métro, etc. (voir section Statistiques plus bas).

# Guide utilisateur
Trois actions sont disponibles à l'exécution du programme.

## Avant l'exécution du programme
Assurez-vous que le fichier d'entrée existe déjà .

Le fichier de sortie n'a pas besoin d'être déjà existant, il est créé après chaque exécution.

## Commande disponible
#### Calculer les frais de sortie par élève
Deux arguments sont nécessaires. Le premier argument est le fichier d'entrée et le deuxième, le fichier de sortie. 

Ces fichiers doivent être spécifiés et finir avec l'extension ***.json*** :

```
java -jar target/equipe23-1.0-RELEASE.jar votreRepertoire/fichierEntree.json votreRepertoire/fichierSortie.json
```

## Options disponibles
#### Afficher les statistiques Ã  l'écran
```
java -jar target/equipe23-1.0-RELEASE.jar -S
```

#### Réinitialiser les statistiques Ã  zéro
```
java -jar target/equipe23-1.0-RELEASE.jar -SR
```

## Fichier d'entrée
Ce fichier doit être en format JSON.

###### En voici un exemple :
```
{
    "nombre_eleves": 25,
    "age": 5,
    "activites": [
        {
            "description": "Cinema",
            "nombre_parents_accompagnateurs": 2,
            "prix_unitaire_enfant": "5.85 $",
            "prix_unitaire_adulte": "9.85 $",
            "transport": 0,
            "distance": 6,
            "date": "2019-10-03"
        },
        {
            "description": "Promenade au Mont-Royal",
            "nombre_parents_accompagnateurs": 6,
            "prix_unitaire_enfant": "0.00 $",
            "prix_unitaire_adulte": "0.00 $",
            "transport": 1,
            "distance": 19,
            "date": "2019-11-12"
        }
    ]
}
```

#### Gestion des erreurs
Les conditions ci-dessous doivent être respectées dans ce fichier :

- Pour une activité, la valeur du champ ***"description"*** est unique et n'est pas vide.
- Le nombre d'élèves se situe entre 1 et 32 inclusivement. 
- L'âge des élèves se situe entre 4 et 17 ans inclusivement.
- Pour une activité, le nombre d'adultes ne dépasse pas le nombre d'enfants.
- Les montants d'argent sont positifs et contiennent deux décimales, un espace et un signe de dollar.
- La distance est positive et ne dépasse pas 120 KM.
- Le format de date respecte la norme ISO 8601.
- Les types de transport possibles sont 0, 1 ou 2.
- Toutes les proprités doivent être présentes.

Lorsqu'une des conditions précédentes n'est pas respectée, les calculs ne sont pas réalisés, le fichier de sortie est généré avec un message d'erreur (voir ci-dessous la rubrique ***Fichier de sortie : Gestion des erreurs*** du guide de l'utilisateur) et un message d'erreur est affiché Ã  la console.

###### En voici un exemple :
```
L'âge des élèves est inférieur à 4 ans.
```

## Fichier de sortie
Ce fichier est également en format JSON. Chaque activité est inscrite avec sa description et le prix par élève de celle-ci. 

###### En voici un exemple :
```
{
    "activites": [
        {
            "description": "Cinema",
            "prix_par_eleve": "18.85 $"
        },
        {
            "description": "Promenade au Mont-Royal",
            "prix_par_eleve": "15.45 $"
        }
    ],
    "recommandations": [
        "Une des activités par marche dépasse les 8 KM permis pour les élèves de moins de 10 ans.",
    ]
}
```
Des recommandations sont présentés à la fin du fichier lorsque certaines des contraintes ci-dessous ne sont pas respectées :

- Le prix d'une activité ne dépasse pas 45.00 $.
- Au moins une activité est en dehors de l'école.
- L'écart maximal entre deux activités est de deux mois.
- L'écart minimal entre deux activités est de trois semaines.
- L'ensemble des coûts par élève sur une année complète ne dépasse pas 300.00 $.
- Le transport par autobus pour une activité ne dépasse pas 80 KM de distance.
- Le transport par métro pour une activité ne dépasse pas 40 KM de distance.
- Le transport par marche pour une activité ne dépasse pas 8 KM pour les élèves de moins de 10 ans et 12 KM de distance pour les autres.

#### Gestion des erreurs
Lorsqu'une des conditions présentée à la rubrique ***Fichier d'entrÃ©e : Gestion des erreurs*** n'est pas respectée dans le fichier d'entrée, le fichier de sortie est généré avec un message d'erreur.

###### En voici un exemple :
```
{
    "message": "L'âge des élèves est inférieur à 4 ans."
}
```

## Statistiques
Les statistiques sont accumulées d'une exécution à l'autre, à  moins de les réinitialiser et peuvent être affichées à  la console.

###### En voici un exemple :
```

---------------------------------------------------
Nombre total d'activités soumises: 4
  
Nombre total d'activités de mois de 20$ : 2
Nombre total d'activités entre 20 et 40$ : 1 
Nombre total d'activités de plus de 40$ : 1
  
Nombre total d'activités par marche : 1
Nombre total d'activités par métro: 1
Nombre total d'activités par autobus: 2
  
Distance maximale soumise : 45 KM
Prix par élève maximal pour une activité : 57.65 $
---------------------------------------------------

```

Lorsqu'il y a une erreur avec le fichier d'entrée, les statistiques ne sont pas calculées.
