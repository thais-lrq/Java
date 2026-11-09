# Jeu de l'oie 

### Atteinte des objectifs

Note sur les GooseCell :

Dans certains cas particuliers, la logique de déplacement en chaîne sur plusieurs GooseCell peut ne pas se comporter exactement comme prévu.

Pour l’améliorer, il faudrait gérer correctement les cellules occupées et éviter les rebonds infinis.

Ces cas sont rares, donc le jeu fonctionne correctement dans la majorité des parties.


### Le sujet

On doit faire un jeu de l'oie avec plusieurs types de cases dont la case "goose", "Teleport","Trap" ...
Pour finir le jeu, il faut arrivé à la dernière case du plateau.

### Commandes:

Se mettre dans le dossier principal pour l'utilisation des commandes

#### Générer la documentation:

Nous pouvons générer la documentation de l'ensemble des classes dans un dossier docs en exécutant cette commande : 

```bash
javadoc -sourcepath src -subpackages goosegame -d docs
```

##### Comment compiler les classes du projet ?

Nous pouvons compiler les classes du projet en exécutant cette commande :  

```bash
 javac -sourcepath src src/goosegame/*.java -d classes

```

### Comment compiler les tests du projet ?

```bash
 javac -classpath junit-console.jar:classes test/goosegame/*.java
```

### Comment exécuter les tests du projet ?

```bash
java -jar junit-console.jar -classpath test:classes -scan-classpath
```

##### Comment tester l'exécution du programme ?

Ne mettre aucun paramètre pour un board classique

Sans paramètre:
```bash
java -classpath classes goosegame.ClassicalBoardMain
```

### Comment créer le jar exécutable 

```bash
jar cvfe clBoard.jar goosegame.ClassicalBoardMain -C classes .
```


### Exécution du jar exécutable

```bash
 java -jar clBoard.jar
 ```
